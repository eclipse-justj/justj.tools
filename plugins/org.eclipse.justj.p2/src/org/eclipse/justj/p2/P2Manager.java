/**
 * Copyright (c) 2020 Eclipse contributors and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.justj.p2;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.Assert;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.justj.p2.UpdateSiteGenerator.RepositoryAnalyzer;


/**
 * <p>
 * This is used to maintain, update, and manage the integrity of a public update site:
 */
public class P2Manager
{
  private static final Pattern PLATFORM_VERSION_PATTERN = Pattern.compile("4\\.([0-9]+)(\\..*)?");

  /**
   * The update site generator used by this manager.
   */
  protected final UpdateSiteGenerator updateSiteGenerator;

  /**
   * Whether to print progress during the activities.
   */
  protected final boolean verbose;

  /**
   * Whether to delete folders or simply mark them as deleted.
   * @see #delete(Path)
   */
  protected boolean delete;

  /**
   * Create an instance that uses the given update site generator.
   * @param updateSiteGenerator the update site generator.
   * @param verbose whether to print progress during activities.
   * @param delete whether to delete folder or simply mark them as deleted.
   */
  public P2Manager(UpdateSiteGenerator updateSiteGenerator, boolean verbose, boolean delete)
  {
    this.updateSiteGenerator = updateSiteGenerator;
    this.verbose = verbose;
    this.delete = delete;
  }

  /**
   * Promotes the given source repository to the appropriate target location.
   * @param buildType the type of build being promoted.
   * @param buildTimestamp the time stamp of this build.
   * @param sourceRepository the repository to promote
   */
  public void promoteUpdateSite(String buildType, String buildTimestamp, Path sourceRepository) throws Exception
  {
    if (verbose)
    {
      System.out.println("Promoting " + buildType + " " + sourceRepository + " to " + updateSiteGenerator.getProjectRoot());
    }

    if (!Files.isRegularFile(sourceRepository.resolve("content.jar")) && !Files.isRegularFile(sourceRepository.resolve("content.xml"))
      && !Files.isRegularFile(sourceRepository.resolve("content.xml.xz")))
    {
      throw new RuntimeException("The source repository is not a simple repository: " + sourceRepository);
    }

    Assert.isNotNull(buildTimestamp, "The build time stamp must not be null");

    try
    {
      new SimpleDateFormat("yyyyMMddHHmm").parse(buildTimestamp);
    }
    catch (ParseException exception)
    {
      Assert.isTrue(false, "The publish.build.timestamp value '" + buildTimestamp + "'must be of the form yyyyMMddHHmm");
    }

    Assert.isTrue(buildTimestamp.matches("20\\d{10}"), "The build time stamp '" + buildTimestamp + "'must be of the form 'yyyyMMddHHmm' and must be in the century 2000");

    Assert.isTrue(UpdateSiteGenerator.BUILD_TYPES.contains(buildType), "The build type '" + buildType + "' must be one of " + UpdateSiteGenerator.BUILD_TYPES);

    Assert.isTrue(!"release".equals(buildType), "The build type must not be 'release'");

    if (verbose)
    {
      System.out.println("Promoting update site to " + updateSiteGenerator.getPromoteUpdateSiteDestination(buildType, buildTimestamp));
    }

    Path promotedUpdateSite = updateSiteGenerator.promoteUpdateSite(sourceRepository, buildType, buildTimestamp);

    // Gather the existing simple repository children.
    List<Path> children = new ArrayList<>();
    Path promotedUpdateSiteParent = promotedUpdateSite.getParent();
    try (Stream<Path> list = Files.list(promotedUpdateSiteParent))
    {
      List<Path> compositeChildren = list.collect(Collectors.toList());
      for (Path child : compositeChildren)
      {
        if (Files.isDirectory(child) && Files.isReadable(child.resolve("content.jar")))
        {
          children.add(child.toRealPath());
        }
      }
    }

    // Sort to ensure that the most recent update site is first in the list.
    UpdateSiteGenerator.sort(children);

    Path compositePath = updateSiteGenerator.getCompositeUpdateSiteDestination(buildType, false);
    if (verbose)
    {
      System.out.println("Composing update site " + compositePath);
    }
    updateSiteGenerator.composeUpdateSites(children, buildType, false);

    Path latestCompositePath = updateSiteGenerator.getCompositeUpdateSiteDestination(buildType, true);
    if (verbose)
    {
      System.out.println("Composing update site " + latestCompositePath);
    }
    updateSiteGenerator.composeUpdateSites(Collections.singletonList(promotedUpdateSite.toRealPath()), buildType, true);
  }

  /**
   * Promotes (mirrors) the latest milestone build to be the release build.
   * The source is {@code milestone/latest} and the target is a release repository child.
   * @throws Exception
   */
  public void promoteLatestMilestoneUpdateSiteToReleaseUpdateSite() throws Exception
  {
    Path milestoneLatest = updateSiteGenerator.getCompositeUpdateSiteDestination("milestone", true);
    Path source = updateSiteGenerator.getLatest(milestoneLatest);
    ensureSourceMilestoneExists(source);
    String version = updateSiteGenerator.getVersion(source, false);
    Path target = updateSiteGenerator.getPromoteUpdateSiteDestination("release", version);
    Path primaryTarget = target;
    if (Files.exists(target))
    {
      version = updateSiteGenerator.getVersion(source, true);
      target = updateSiteGenerator.getPromoteUpdateSiteDestination("release", version);
      Assert.isTrue(!Files.exists(target), "The release '" + target + "' already exists and so does '" + primaryTarget + "'");
    }
    updateSiteGenerator.mirrorUpdateSite(source, target, "release");

    Path milestone = updateSiteGenerator.getCompositeUpdateSiteDestination("milestone", false);
    try (Stream<Path> list = Files.list(milestone))
    {
      for (Path child : list.collect(Collectors.toList()))
      {
        String name = child.getFileName().toString();
        if (!"latest".equals(name) && Files.isDirectory(child) && Files.isRegularFile(child.resolve("content.jar")) && !Files.isRegularFile(child.resolve("DELETED"))
          && !Files.isRegularFile(child.resolve("STALE")))
        {
          Files.createFile(child.resolve("STALE"));
        }
      }
    }
  }

  /**
   * This call-out is called right before this repository will be loaded and can be used to transfer the contents from a remote location to a local location..
   * @param source the repository to be loaded
   * @throws IOException
   */
  protected void ensureSourceMilestoneExists(Path source) throws IOException
  {
  }

  /**
   * Manages the integrity of the the composites {@code release}, {@code release/latest}, {@code milestone}, {@code milestone/latest}, {@code nightly}, and {@code nightly/latest}.
   *
   * @throws Exception
   */
  public void recomposeComposites(boolean simrelAlias) throws Exception
  {
    Path targetRoot = updateSiteGenerator.getUpdateSiteRoot();
    if (verbose)
    {
      System.out.println("Recompose composites: " + targetRoot);
    }

    Map<String, Path> latestVersions = new LinkedHashMap<>();
    Map<String, Path> buildTypeRepos = new LinkedHashMap<>();
    for (String buildType : UpdateSiteGenerator.BUILD_TYPES)
    {
      buildTypeRepos.put(buildType, null);
    }

    try (Stream<Path> list = Files.list(targetRoot))
    {
      for (Path child : list.collect(Collectors.toList()))
      {
        String buildType = child.getFileName().toString();
        if (UpdateSiteGenerator.BUILD_TYPES.contains(buildType) && Files.isDirectory(child))
        {
          buildTypeRepos.put(buildType, child);
        }
      }
    }

    for (Map.Entry<String, Path> entry : buildTypeRepos.entrySet())
    {
      Path child = entry.getValue();
      if (child != null)
      {
        String buildType = entry.getKey();
        if (verbose)
        {
          System.out.println("Recompose: " + child);
        }
        List<Path> children = new ArrayList<>();
        for (Path grandChild : Files.list(child).collect(Collectors.toList()))
        {
          String name = grandChild.getFileName().toString();
          if (!"latest".equals(name) && Files.isDirectory(grandChild) && Files.isRegularFile(grandChild.resolve("content.jar"))
            && !Files.isRegularFile(grandChild.resolve("DELETED")))
          {
            children.add(grandChild.toRealPath());
            if (verbose)
            {
              System.out.println("  <- " + name);
            }
          }
        }

        UpdateSiteGenerator.sort(children);

        Path compositePath = updateSiteGenerator.getCompositeUpdateSiteDestination(buildType, false);
        if (verbose)
        {
          System.out.println("Composing update site " + compositePath);
        }
        cleanupComposite(compositePath);
        updateSiteGenerator.composeUpdateSites(children, buildType, false);

        List<Path> latestUpdateSite = new ArrayList<>();
        if (!children.isEmpty())
        {
          Path latest = children.get(0);
          if (simrelAlias)
          {
            String version = updateSiteGenerator.getVersion(latest, false);
            Matcher matcher = PLATFORM_VERSION_PATTERN.matcher(version);
            if (matcher.matches())
            {
              int offset = Integer.valueOf(matcher.group(1)) - 11;
              int year = 2019 + offset / 4;
              String quarter;
              switch (offset % 4)
              {
                case 0:
                {
                  quarter = "03";
                  break;
                }
                case 1:
                {
                  quarter = "06";
                  break;
                }
                case 2:
                {
                  quarter = "09";
                  break;
                }
                default:
                {
                  quarter = "12";
                  break;
                }
              }
              String alias = year + "-" + quarter;
              latestVersions.put(alias, latest);
            }
          }
          latestUpdateSite.add(latest);
        }

        Path latestCompositePath = updateSiteGenerator.getCompositeUpdateSiteDestination(buildType, true);
        if (verbose)
        {
          System.out.println("Composing update site " + latestCompositePath);
        }
        cleanupComposite(latestCompositePath);
        updateSiteGenerator.composeUpdateSites(latestUpdateSite, buildType, true);
      }
    }

    for (Map.Entry<String, Path> entry : latestVersions.entrySet())
    {
      String alias = entry.getKey();
      Path latest = entry.getValue();
      Path aliasCompositePath = updateSiteGenerator.getCompositeUpdateSiteDestination(alias, true);
      if (verbose)
      {
        System.out.println("Composing alias update site " + aliasCompositePath + " for " + latest);
      }
      cleanupComposite(aliasCompositePath);
      updateSiteGenerator.composeUpdateSites(List.of(latest), alias, false);
    }
  }

  /**
   * Tests that the {@code nightly} composite is well formed.
   * It deletes older builds to ensure that there are at most {@link UpdateSiteGenerator#getRetainedNightlyBuilds()} nightly builds,
   * updating the composite to reference only remaining builds.
   *
   * @throws Exception
   */
  public void cleanNightlyComposite() throws Exception
  {
    Path nightlyRepository = updateSiteGenerator.getCompositeUpdateSiteDestination("nightly", false);
    RepositoryAnalyzer repositoryAnalyzer = updateSiteGenerator.getRepositoryAnalyzer(Collections.singletonList(nightlyRepository));
    List<Path> children = repositoryAnalyzer.getChildren();
    Assert.isTrue(!children.isEmpty(), "The folder '" + nightlyRepository + "' is an empty composite. You must do at least one nightly build.");

    for (Path child : children)
    {
      Assert.isTrue(child.getParent().equals(nightlyRepository), "The folder '" + child + "' is expected to be a direct child of '" + nightlyRepository);
    }

    for (String child : repositoryAnalyzer.getRawChildren())
    {
      Assert.isTrue(!URI.create(child).isAbsolute(), "The raw child '" + child + "' is expected to be a relative URI");
    }

    int retainedNightlyBuilds = updateSiteGenerator.getRetainedNightlyBuilds();
    if (children.size() > retainedNightlyBuilds)
    {
      UpdateSiteGenerator.sort(children);
      List<Path> childrenToDelete = children.subList(retainedNightlyBuilds, children.size());
      for (Path childToDelete : childrenToDelete)
      {
        delete(childToDelete);
      }

      cleanupComposite(nightlyRepository);
      updateSiteGenerator.composeUpdateSites(children.subList(0, retainedNightlyBuilds), "nightly", false);
    }
  }

  /**
   * Tests that the {@code milestone} composite is well formed.
   * It deletes older builds that have been marked as stale after a release build.
   * In other words, milestone builds are delete as soon as there is a milestone build after a release.
   *
   * @throws Exception
   */
  public void cleanMilestoneComposite() throws Exception
  {
    Path milestoneRepository = updateSiteGenerator.getCompositeUpdateSiteDestination("milestone", false);
    if (verbose)
    {
      System.out.println("Cleaning milestones " + milestoneRepository);
    }

    if (Files.isDirectory(milestoneRepository))
    {
      RepositoryAnalyzer repositoryAnalyzer = updateSiteGenerator.getRepositoryAnalyzer(Collections.singletonList(milestoneRepository));
      List<Path> children = repositoryAnalyzer.getChildren();
      Assert.isTrue(!children.isEmpty(), "The folder '" + milestoneRepository + "' is an empty composite. You must do at least one milestone build.");

      List<Path> childrenToDelete = new ArrayList<>();
      for (Path child : children)
      {
        if (Files.exists(child.resolve("STALE")))
        {
          childrenToDelete.add(child);
        }
      }

      for (Path child : children)
      {
        Assert.isTrue(child.getParent().equals(milestoneRepository), "The folder '" + child + "' is expected to be a direct child of '" + milestoneRepository);
      }

      for (String child : repositoryAnalyzer.getRawChildren())
      {
        Assert.isTrue(!URI.create(child).isAbsolute(), "The raw child '" + child + "' is expected to be a relative URI");
      }

      if (!childrenToDelete.isEmpty() && childrenToDelete.size() != children.size())
      {
        for (Path childToDelete : childrenToDelete)
        {
          if (verbose)
          {
            System.out.println("Deleting old milestone build: " + childToDelete);
          }
          delete(childToDelete);
          children.remove(childToDelete);
        }

        cleanupComposite(milestoneRepository);
        updateSiteGenerator.composeUpdateSites(children, "milestone", false);
      }
    }
  }

  /**
   * Produces zipped downloads for each simple repository, along with associated SHA-256 and SHA-512 hashes.
   *
   * @throws Exception
   */
  public void generateDownloads() throws Exception
  {
    updateSiteGenerator.generateDownloads(updateSiteGenerator.getUpdateSiteRoot());
  }

  /**
   * Generates an {@code index.html} in each folder.
   *
   * @throws Exception
   */
  public void generateUpdateSiteIndex() throws Exception
  {
    updateSiteGenerator.generateIndex(updateSiteGenerator.getUpdateSiteRoot());
  }

  /**
   * Generates the super index of all the releases in the sub-folders.
   *
   * @throws Exception
   */
  public void generateSuperUpdateSiteIndex() throws Exception
  {
    updateSiteGenerator.generateSuperIndex();
  }

  /**
   * Removes the metadata for a composite, otherwise p2 tries to load it and at this point these files are not valid.
   *
   * @param target the repository composite folder to clean.
   */
  static void cleanupComposite(Path target) throws IOException
  {
    for (String file : new String []{ "compositeContent.jar", "compositeContent.xml", "compositeArtifacts.jar", "compositeArtifacts.xml", "p2.index" })
    {
      Files.deleteIfExists(target.resolve(file));
    }
  }

  /**
   * Deletes a folder and all of its children, or just mark the folder as deleted.
   *
   * @param folder the folder to delete.
   * @throws IOException
   */
  private void delete(Path folder) throws IOException
  {
    if (verbose)
    {
      System.out.println("Deleting " + folder);
    }

    if (delete)
    {
      deleteLocal(folder);
    }
    else
    {
      // We just mark it as deleted.
      Path marker = folder.resolve("DELETED");
      if (!Files.isRegularFile(marker))
      {
        Files.createFile(marker);
      }
    }
  }

  public static class Application implements IApplication
  {
    @Override
    public Object start(IApplicationContext context) throws Exception
    {
      P2Plugin.INSTANCE.log("Starting the p2 manager");

      List<String> args = new ArrayList<>(Arrays.asList((String[])context.getArguments().get("application.args")));

      if ("true".equals(System.getProperty("org.eclipse.justj.p2.P2Manager.Application.dump")))
      {
        for (int i = 0; i < args.size(); i++)
        {
          String string = args.get(i);
          System.out.println("arg[" + i + "]='" + string + "'");
        }

        if (Boolean.TRUE)
        {
          return 0;
        }
      }

      boolean verbose = !getArgument("-quiet", args);
      boolean latestVersionOnly = getArgument("-latestVersionOnly", args);
      int retainedNightlyBuilds = Integer.parseInt(getArgument("-retain", args, "7"));
      String projectLabel = getArgument("-label", args, "Project");
      String buildURL = getArgument("-build-url", args, null);
      String projectRoot = getArgument("-root", args, null);
      String relativeTargetFolder = getArgument("-relative", args, null);
      String remote = getArgument("-remote", args, null);
      String sourceRepository = getArgument("-promote", args, null);
      String productsFolder = getArgument("-promote-products", args, null);
      List<String> downloads = getArguments("-downloads", args, Collections.emptyList());
      String buildTimestamp = getArgument("-timestamp", args, null);
      String buildType = getArgument("-type", args, "nightly");
      String favicon = getArgument("-favicon", args, "https://www.eclipse.org/eclipse.org-common/themes/solstice/public/images/favicon.ico");
      String titleImage = getArgument("-title-image", args, "https://www.eclipse.org/eclipse.org-common/themes/solstice/public/images/logo/eclipse-426x100.png");
      String bodyImage = getArgument("-body-image", args, null);
      String targetURL = getArgument("-target-url", args, null);
      String baselineURL = getArgument("-baseline-url", args, null);
      String versionIU = getArgument("-version-iu", args, null);
      String versionIUPattern = getArgument("-version-iu-pattern", args, null);
      String iuFilterPattern = getArgument("-iu-filter-pattern", args, null);
      String excludedCategoriesPattern = getArgument("-excluded-categories-pattern", args, null);
      String commit = getArgument("-commit", args, null);
      String superTargetFolder = getArgument("-super", args, null);
      boolean simrelAlias = getArgument("-simrel-alias", args);
      int summary = Integer.parseInt(getArgument("-summary", args, "0"));
      Pattern summaryIUPattern = Pattern.compile(getArgument("-summary-iu-pattern", args, ".*(?<!\\.source|\\.feature\\.group|\\.feature\\.jar)"));

      if (buildTimestamp == null)
      {
        buildTimestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
      }
      else if (buildTimestamp.endsWith("Z"))
      {
        try
        {
          buildTimestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(buildTimestamp));
        }
        catch (ParseException exception)
        {
          Assert.isTrue(false, "The publish.build.timestamp value '" + buildTimestamp + "'must be of the form yyyy-MM-dd'T'HH:mm:ss'Z' or yyyyMMddHHmm");
        }
      }

      Map<Pattern, String> mavenWrappedMappings = new HashMap<>();
      for (String mapping = getArgument("-maven-wrapped-mapping", args, null); mapping != null; mapping = getArgument("-maven-wrapped-mapping", args, null))
      {
        String[] parts = mapping.split("->");
        if (parts.length == 2)
        {
          mavenWrappedMappings.put(Pattern.compile(parts[0]), parts[1]);
        }
        else
        {
          mavenWrappedMappings.put(Pattern.compile(parts[0]), null);
        }
      }

      Map<String, String> nameMappings = new HashMap<>();
      nameMappings.put("jres", "JREs");
      for (String mapping = getArgument("-mapping", args, null); mapping != null; mapping = getArgument("-mapping", args, null))
      {
        String[] parts = mapping.split("->");
        if (parts.length == 2)
        {
          nameMappings.put(parts[0], parts[1]);
        }
      }

      Map<Pattern, String> commitMappings = new HashMap<>();
      for (String mapping = getArgument("-commit-mapping", args, null); mapping != null; mapping = getArgument("-commit-mapping", args, null))
      {
        String[] parts = mapping.split("->");
        if (parts.length == 2)
        {
          commitMappings.put(Pattern.compile(parts[0]), parts[1]);
        }
      }

      Path relativeTargetFolderPath = Paths.get(relativeTargetFolder);
      Assert.isTrue(!relativeTargetFolderPath.isAbsolute(), "The relative target folder '" + relativeTargetFolder + "' must be relative");

      Path relativeSuperTargetFolderPath = superTargetFolder == null ? null : Paths.get(superTargetFolder);
      if (relativeSuperTargetFolderPath != null)
      {
        Assert.isTrue(!relativeSuperTargetFolderPath.isAbsolute(), "The relative super target folder '" + superTargetFolder + "' must be relative");
        Assert.isTrue(
          relativeTargetFolderPath.startsWith(relativeSuperTargetFolderPath),
          "The relative super target folder '" + superTargetFolder + "' must be parent of the target folder '" + relativeTargetFolder + "'");
      }

      Map<String, String> breadcrumbs = new LinkedHashMap<>();
      for (String breadcrumb = getArgument("-breadcrumb", args, null); breadcrumb != null; breadcrumb = getArgument("-breadcrumb", args, null))
      {
        int index = breadcrumb.lastIndexOf(' ');
        if (index == -1)
        {
          breadcrumbs.put(breadcrumb, "");
        }
        else
        {
          breadcrumbs.put(breadcrumb.substring(0, index), breadcrumb.substring(index + 1));
        }
      }

      Set<String> excludes = new LinkedHashSet<>();
      for (String exclude = getArgument("--exclude", args, null); exclude != null; exclude = getArgument("--exclude", args, null))
      {
        excludes.add(exclude);
      }

      String host;
      String hostPath;
      Path absoluteTargetPath = Paths.get(projectRoot).toAbsolutePath().resolve(relativeTargetFolderPath);
      Path absoluteSuperTargetPath = relativeSuperTargetFolderPath == null ? null : Paths.get(projectRoot).toAbsolutePath().resolve(relativeSuperTargetFolderPath);

      if (remote != null)
      {
        String[] remoteComponents = remote.split(":", 2);
        if (remoteComponents.length != 2)
        {
          throw new IllegalArgumentException("The remote '" + remote + "' must be of the form <user-id>@<host>:<absolute-path-on-host>");
        }

        host = remoteComponents[0];
        String tentativeHostPath = remoteComponents[1];

        if ("localhost".equals(host))
        {
          Path path = ProcessLauncher.getNormalizedAbsolutePath(Paths.get(tentativeHostPath).toAbsolutePath());
          hostPath = toShellPath(path);
        }
        else if (!host.contains("@") || !tentativeHostPath.startsWith("/"))
        {
          throw new IllegalArgumentException("The remote '" + remote + "' must be of the form <user-id>@<host>:<absolute-path-on-host>");
        }
        else
        {
          hostPath = tentativeHostPath;
        }
      }
      else
      {
        host = null;
        hostPath = null;
      }

      String hostPrefix = host == null ? null : "localhost".equals(host) ? "" : host + ":";

      if (host != null)
      {
        // Create the target folder on the remote host.
        // It's okay if that already exists.
        {
          ProcessLauncher processLauncher = new ProcessLauncher(
            verbose,
            "localhost".equals(host) ? "bash" : "ssh",
            "localhost".equals(host) ? "-c" : host,
            "mkdir -p " + hostPath + "/" + relativeTargetFolder);

          processLauncher.execute();
          if (verbose)
          {
            processLauncher.dump();
          }

          if (processLauncher.exitValue() != 0)
          {
            if (!verbose)
            {
              processLauncher.fullDump();
            }
            else
            {
              System.err.println("exitValue=" + processLauncher.exitValue());
            }
            return processLauncher.exitValue();
          }
        }

        // Transfer the remote host contents to the local target.
        // It's okay if that's totally empty still.
        {
          // Clean out the local folder if it already exists.
          if (Files.isDirectory(absoluteTargetPath))
          {
            deleteLocal(absoluteTargetPath);
          }

          // Recreated it.
          Files.createDirectories(absoluteTargetPath);

          // Use rsync to transfer the remote folder to the local folder.
          // Exclude the large content, i.e., we really only need the metadata of the repositories.
          Path normalizedAbsolutePath = ProcessLauncher.getNormalizedAbsolutePath(absoluteSuperTargetPath == null ? absoluteTargetPath : absoluteSuperTargetPath);
          List<String> launcherArgs = new ArrayList<>();
          launcherArgs.addAll(
            List.of(
              "rsync",
              "-avsh",
              "--exclude",
              "*.zip",
              "--exclude",
              "*.tar.gz",
              "--exclude",
              "*/features",
              "--exclude",
              "*/plugins",
              "--exclude",
              "*/binary",
              "--exclude",
              "*/.blobstore",
              "--exclude",
              "*.html",
              "--exclude",
              "*/downloads",
              "--exclude",
              "*/archive"));
          for (String exclude : excludes)
          {
            launcherArgs.add("--exclude");
            launcherArgs.add(exclude);
          }
          launcherArgs.add(hostPrefix + hostPath + "/" + (superTargetFolder == null ? relativeTargetFolder : superTargetFolder) + "/");
          launcherArgs.add(toShellPath(normalizedAbsolutePath));
          ProcessLauncher processLauncher = new ProcessLauncher(verbose, launcherArgs.toArray(String[]::new));
          processLauncher.execute();
          if (verbose)
          {
            processLauncher.dump();
          }

          if (processLauncher.exitValue() != 0)
          {
            if (!verbose)
            {
              processLauncher.fullDump();
            }
            else
            {
              System.err.println("exitValue=" + processLauncher.exitValue());
            }
            return processLauncher.exitValue();
          }
        }
      }

      List<Path> products = new ArrayList<>();
      if (productsFolder != null)
      {
        Path productsPath = Paths.get(productsFolder).toRealPath().normalize();
        try (Stream<Path> list = Files.list(productsPath))
        {
          products.addAll(list.filter(it -> Files.isRegularFile(it) && !it.toString().endsWith(".sha512")).collect(Collectors.toList()));
        }
      }

      List<Path> downloadPaths = new ArrayList<>();
      for (String download : downloads)
      {
        Path downloadPath = Paths.get(download).toRealPath().normalize();
        if (!Files.isRegularFile(downloadPath))
        {
          throw new IllegalArgumentException("The download file does not exist: " + downloadPath);
        }
        downloadPaths.add(downloadPath);
      }

      Pattern effectiveIUVersionPattern = versionIU != null
        ? Pattern.compile(Pattern.quote(versionIU) + ".*") : versionIUPattern != null ? Pattern.compile(versionIUPattern) : null;

      UpdateSiteGenerator updateSiteGenerator = new UpdateSiteGenerator(
        projectLabel,
        buildURL,
        Paths.get(projectRoot),
        relativeTargetFolderPath,
        relativeSuperTargetFolderPath,
        products,
        downloadPaths,
        targetURL,
        baselineURL,
        retainedNightlyBuilds,
        effectiveIUVersionPattern,
        iuFilterPattern == null ? null : Pattern.compile(iuFilterPattern),
        excludedCategoriesPattern == null ? null : Pattern.compile(excludedCategoriesPattern),
        commit,
        breadcrumbs,
        favicon,
        titleImage,
        bodyImage,
        mavenWrappedMappings,
        nameMappings,
        commitMappings,
        latestVersionOnly,
        summary,
        summaryIUPattern,
        verbose);

      P2Manager p2Manager = new P2Manager(updateSiteGenerator, verbose, host == null)
        {
          @Override
          protected void ensureSourceMilestoneExists(Path source) throws IOException
          {
            if (host != null)
            {
              // Use rsync to transfer the complete contents of the remote folder to the local folder.
              Path relativeTargetFolder = Paths.get(projectRoot).relativize(source);
              Path normalizedAbsolutePath = ProcessLauncher.getNormalizedAbsolutePath(source);
              ProcessLauncher processLauncher = new ProcessLauncher(
                verbose,
                "rsync",
                "-avsh",
                hostPrefix + hostPath + "/" + toShellPath(relativeTargetFolder) + "/",
                toShellPath(normalizedAbsolutePath));

              processLauncher.execute();
              if (verbose)
              {
                processLauncher.dump();
              }

              if (processLauncher.exitValue() != 0)
              {
                if (!verbose)
                {
                  processLauncher.fullDump();
                }
                else
                {
                  System.err.println("exitValue=" + processLauncher.exitValue());
                }
              }
            }
          }
        };

      // Only do a promote of a build when we're not doing a release build.
      if (buildType != null && !"release".equals(buildType) && buildTimestamp != null && sourceRepository != null)
      {
        p2Manager.promoteUpdateSite(buildType, buildTimestamp, Paths.get(sourceRepository).toRealPath().normalize());
      }

      // Promote the latest milestone build when doing a release build.
      if ("release".equals(buildType))
      {
        p2Manager.promoteLatestMilestoneUpdateSiteToReleaseUpdateSite();
      }

      p2Manager.recomposeComposites(simrelAlias);
      p2Manager.cleanNightlyComposite();
      p2Manager.cleanMilestoneComposite();
      p2Manager.generateDownloads();
      p2Manager.generateUpdateSiteIndex();
      p2Manager.generateSuperUpdateSiteIndex();

      if (host != null)
      {
        // Transfer the local target contents to the remote host content.
        {
          // Use rsync to transfer the local folder to the remote folder, excluding nothing.
          Path normalizedAbsolutePath = ProcessLauncher.getNormalizedAbsolutePath(absoluteSuperTargetPath == null ? absoluteTargetPath : absoluteSuperTargetPath);
          ProcessLauncher processLauncher = new ProcessLauncher(
            verbose,
            "rsync",
            "-avsh",
            toShellPath(normalizedAbsolutePath) + "/",
            hostPrefix + hostPath + "/" + (superTargetFolder == null ? relativeTargetFolder : superTargetFolder) + "/");

          processLauncher.execute();
          if (verbose)
          {
            processLauncher.dump();
          }

          if (processLauncher.exitValue() != 0)
          {
            if (!verbose)
            {
              processLauncher.fullDump();
            }
            else
            {
              System.err.println("exitValue=" + processLauncher.exitValue());
            }

            return processLauncher.exitValue();
          }
        }

        // Delete any folders marked as DELETED.
        {
          // Use ssh to do all this processing on the remote server.
          ProcessLauncher processLauncher = new ProcessLauncher(
            verbose,
            "localhost".equals(host) ? "bash" : "ssh",
            "localhost".equals(host) ? "-c" : host, //
            "for i in $(find " + hostPath + "/" + relativeTargetFolder + " -name DELETED); do\n" + //
              "  echo rm -rf $(dirname $i)\n" + //
              "  rm -rf $(dirname $i)\n" + //
              "done");

          processLauncher.execute();
          if (verbose)
          {
            processLauncher.dump();
          }

          if (processLauncher.exitValue() != 0)
          {
            if (!verbose)
            {
              processLauncher.fullDump();
            }
            else
            {
              System.err.println("exitValue=" + processLauncher.exitValue());
            }
            return processLauncher.exitValue();
          }
        }

        // Restore the timestamp of every simple repository folder based on the plugins folder.
        // This is to ensure that the mirrors are not invalidated by changes to the generated index information.
        {
          // Use ssh to do all this processing on the remote server.
          ProcessLauncher processLauncher = new ProcessLauncher(
            verbose,
            "localhost".equals(host) ? "bash" : "ssh",
            "localhost".equals(host) ? "-c" : host, //
            "for i in $(find " + hostPath + "/" + relativeTargetFolder + " -name plugins -a -type d); do\n" + //
              "  echo touch -r $i $(dirname $i)\n" + //
              "  touch -r $i $(dirname $i)\n" + //
              "done");

          processLauncher.execute();
          if (verbose)
          {
            processLauncher.dump();
          }

          if (processLauncher.exitValue() != 0)
          {
            if (!verbose)
            {
              processLauncher.fullDump();
            }
            else
            {
              System.err.println("exitValue=" + processLauncher.exitValue());
            }
            return processLauncher.exitValue();
          }
        }
      }

      return 0;
    }

    /**
     * Strip an argument that has no value after it.
     * @param name the name of the argument.
     * @param args the target arguments.
     * @return whether the argument was present.
     */
    private boolean getArgument(String name, List<String> args)
    {
      return args.remove(name);
    }

    /**
     * Strip an argument that has a value after it.
     * @param name  the name of the argument.
     * @param args the target arguments.
     * @param defaultValue the value to return when the argument is not present.
     * @return the value of the argument or the default value when not present.
     */
    private String getArgument(String name, List<String> args, String defaultValue)
    {
      int index = args.indexOf(name);
      if (index == -1)
      {
        Pattern pattern = Pattern.compile(Pattern.quote(name) + "\\s+" + "([^-].*)");
        for (String arg : args)
        {
          Matcher matcher = pattern.matcher(arg);
          if (matcher.matches())
          {
            args.remove(arg);
            return matcher.group(1);
          }
        }
        return defaultValue;
      }
      else
      {
        args.remove(index);
        if (index >= args.size())
        {
          throw new IllegalArgumentException("An argument value is expected after " + name);
        }
        return args.remove(index);
      }
    }

    /**
     * Strip an argument that has multiple values after it.
     * @param name  the name of the argument.
     * @param args the target arguments.
     * @param defaultValue the value to return when the argument is not present.
     * @return the values of the argument.
     */
    private List<String> getArguments(String name, List<String> args, List<String> defaultValue)
    {
      int index = args.indexOf(name);
      if (index == -1)
      {
        return defaultValue;
      }
      else
      {
        args.remove(index);
        if (index >= args.size())
        {
          throw new IllegalArgumentException("An argument value is expected after " + name);
        }

        List<String> result = new ArrayList<>();
        while (index < args.size() && !args.get(index).startsWith("-"))
        {
          result.add(args.remove(index));
        }
        return result;
      }
    }

    /**
     * Do nothing.
     */
    @Override
    public void stop()
    {
    }
  }

  /**
   * Deletes a file or recursively an entire folder.
   * @param folder the file or directory.
   * @throws IOException
   */
  public static void deleteLocal(Path folder) throws IOException
  {
    Files.walkFileTree(folder, new SimpleFileVisitor<Path>()
      {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException
        {
          Files.delete(file);
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path directory, IOException exception) throws IOException
        {
          if (exception == null)
          {
            Files.delete(directory);
          }
          return super.postVisitDirectory(directory, exception);
        }
      });
  }

  /**
   * Converts a path to a path that can be used in sh/bash comments.
   * @param path the path.
   * @return a path that can be used in sh/bash comments.
   */
  public static String toShellPath(Path path)
  {
    return toShellPath(path.toString());
  }

  /**
   * Converts a path to a path that can be used in sh/bash commands.
   * @param path the path.
   * @return a path that can be used in sh/bash comments.
   */
  public static String toShellPath(String path)
  {
    return path.toString().replace('\\', '/');
  }

  /**
   * A utility calls for launching processes.
   */
  private static class ProcessLauncher
  {
    /**
     * Whether to print the command before executing it.
     */
    private final boolean verbose;

    /**
     * The build used to create the process.
     */
    private final ProcessBuilder builder;

    /**
     * The created process.
     */
    private Process process;

    /**
     * The contents produced for stderr.
     */
    private List<String> err;

    /**
     * The contents produced for stdout.
     */
    private List<String> out;

    /**
     * Creates an instance to help launch a process for the args.
     * @param verbose whether to print the command before executing it.
     * @param args the args of the process.
     */
    public ProcessLauncher(boolean verbose, String... args)
    {
      this.verbose = verbose;
      builder = new ProcessBuilder(args);

      // Search the path to find the absolute path of the first argument.
      Map<String, String> environment = builder.environment();
      String path = getEnv("PATH", environment);
      String executableArg = args[0];
      Path executable = Paths.get(executableArg);
      Path absoluteExecutablePath = null;
      if (!executable.isAbsolute())
      {
        absoluteExecutablePath = search(executableArg, path);
        String fullPath = path;
        if (absoluteExecutablePath == null && File.separatorChar == '\\')
        {
          // We'll assume that on Windows, Git is installed and that we can use it.
          String extraPath = System.getProperty("org.eclipse.justj.extra.path", "C:\\Program Files\\Git\\usr\\bin");
          fullPath = extraPath + File.pathSeparator + fullPath;
          path = fullPath;
          absoluteExecutablePath = search(executableArg, extraPath);

          // This is needed on Windows for base just in case the executable wasn't really on the path.
          // And then we put it in for both case variants because a debug launch worked but a run launch did not.  Go figure.
          //
          environment.put("PATH", path);
          environment.put("Path", path);
        }
        else if (absoluteExecutablePath != null && File.separatorChar == '\\')
        {
          // Ensure that the path on which it is found is first in the search list so that other things like "find" look here first.
          //
          fullPath = absoluteExecutablePath.getParent() + File.pathSeparator + fullPath;

          // Put it in for both case variants because a debug launch worked but a run launch did not.  Go figure.
          //
          environment.put("PATH", fullPath);
          environment.put("Path", fullPath);
        }

        if (absoluteExecutablePath == null)
        {
          throw new IllegalArgumentException("Executable '" + executableArg + "' not found after searching the following PATH: " + fullPath);
        }
      }

      // Change the build to use the absolute path.
      builder.command().set(0, absoluteExecutablePath.toString());
    }

    public void execute() throws IOException
    {
      if (verbose)
      {
        System.out.println(this);
      }

      process = builder.start();
      OutputStream stdin = process.getOutputStream();
      stdin.close();

      InputStream stderr = process.getErrorStream();
      AtomicReference<IOException> stderrException = new AtomicReference<>();
      Thread stderrThread = new Thread("stderr-reader")
        {
          @Override
          public void run()
          {
            try
            {
              err = lines(stderr);
            }
            catch (IOException exception)
            {
              stderrException.set(exception);
            }
            finally
            {
              if (err == null)
              {
                err = Collections.emptyList();
              }
            }
          }
        };
      stderrThread.start();

      InputStream stdout = process.getInputStream();
      AtomicReference<IOException> stdoutException = new AtomicReference<>();
      Thread stdoutThread = new Thread("stdout-reader")
        {
          @Override
          public void run()
          {
            try
            {
              out = lines(stdout);
            }
            catch (IOException exception)
            {
              stdoutException.set(exception);
            }
            finally
            {
              if (out == null)
              {
                out = Collections.emptyList();
              }
            }
          }
        };
      stdoutThread.start();

      try
      {
        process.waitFor(2, TimeUnit.MINUTES);
        stderrThread.join(1000 * 60);
        stdoutThread.join(1000 * 60);
      }
      catch (InterruptedException exception)
      {
        System.err.println("Interrupted");
        fullDump();
        Thread.currentThread().interrupt();
        return;
      }

      if (stderrException.get() != null)
      {
        throw stderrException.get();
      }

      if (stdoutException.get() != null)
      {
        throw stdoutException.get();
      }
    }

    /**
     * Returns the exit value of the process.
     * @return the exit value of the process.
     */
    public int exitValue()
    {
      return process.exitValue();
    }

    /**
     * Returns the contents of stdout.
     * @return the contents of stdout.
     */
    public List<String> out()
    {
      return out;
    }

    /**
     * Returns the contents of stderr.
     * @return the contents of stderr.
     */
    @SuppressWarnings("unused")
    public List<String> err()
    {
      return err;
    }

    /**
     * Dumps the contents of stderr and stdout.
     */
    public void dump()
    {
      if (err != null)
      {
        err.forEach(System.err::println);
      }
      if (out != null)
      {
        out.forEach(System.out::println);
      }
    }

    /**
     * Dumps the command, the contents of stderr and stdout, and the exit value.
     */
    public void fullDump()
    {
      System.err.println(this);
      dump();
      System.err.println("exitValue=" + exitValue());
    }

    /**
     * Gets an environment variable which has to deal with case insensitively on Windows.
     * @param key the key to look up.
     * @param environment the environment in which to look it up.
     * @return the value associated with that key.
     */
    private String getEnv(String key, Map<String, String> environment)
    {
      if (File.separatorChar == '\\')
      {
        String upperCaseKey = key.toUpperCase();
        for (Map.Entry<String, String> entry : environment.entrySet())
        {
          if (entry.getKey().toUpperCase().equals(upperCaseKey))
          {
            return entry.getValue();
          }
        }
      }
      else
      {
        return environment.get(key);
      }

      return null;
    }

    /**
     * Search for the executable on the path.
     * @param executable the executable with a relative path.
     * @param path a path of folders to search.
     * @return the resolved executable or {@code null} if it can't be resolved.
     */
    private Path search(String executable, String path)
    {
      String[] paths = path.split(File.pathSeparator);
      for (String pathElement : paths)
      {
        Path absoluteExecutable = Paths.get(pathElement, executable);
        if (Files.isExecutable(absoluteExecutable) && Files.isRegularFile(absoluteExecutable))
        {
          return absoluteExecutable;
        }
        else if (File.separatorChar == '\\')
        {
          absoluteExecutable = Paths.get(pathElement, executable + ".exe");
          if (Files.isExecutable(absoluteExecutable) && Files.isRegularFile(absoluteExecutable))
          {
            return absoluteExecutable;
          }
        }
      }

      return null;
    }

    /**
     * Reads all the UTF-8 text in the stream and closed it.
     * @param input the stream to read.
     * @return the lines of UTF-8 text in the stream.
     * @throws IOException
     */
    private List<String> lines(InputStream input) throws IOException
    {
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8")))
      {
        return reader.lines().collect(Collectors.toList());
      }
    }

    @Override
    public String toString()
    {
      return builder.command().stream().collect(Collectors.joining(" "));
    }

    /**
     * Uses sh to determine how the root of a path is represented for use in shell scripts, converting the path to that form.
     * E.g., for git bash, {@code C:/file} is represented as {@code /c/file}.
     * @param path an absolute path.
     * @return the absolute path in shell compatible form.
     * @throws IOException
     */
    public static Path getNormalizedAbsolutePath(Path path) throws IOException
    {
      Path root = path.getRoot();
      ProcessLauncher processLauncher = new ProcessLauncher(false, "sh", "-ex", "-c", "cd '" + toShellPath(root) + "'; pwd");
      processLauncher.execute();
      if (processLauncher.exitValue() != 0)
      {
        processLauncher.fullDump();
        throw new RuntimeException("Failed to get normalized absolute path of '" + path + "'");
      }
      return Paths.get(processLauncher.out().get(0)).resolve(path.subpath(0, path.getNameCount()));
    }
  }
}
