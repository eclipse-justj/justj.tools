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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.metadata.BasicVersion;
import org.eclipse.equinox.internal.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.internal.repository.tools.AbstractApplication;
import org.eclipse.equinox.p2.internal.repository.tools.CompositeRepositoryApplication;
import org.eclipse.equinox.p2.internal.repository.tools.MirrorApplication;
import org.eclipse.equinox.p2.internal.repository.tools.RepositoryDescriptor;
import org.eclipse.equinox.p2.internal.repository.tools.SlicingOptions;
import org.eclipse.equinox.p2.internal.repository.tools.XZCompressor;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.p2.metadata.IRequirement;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.ICompositeRepository;
import org.eclipse.equinox.p2.repository.IRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactDescriptor;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.artifact.spi.AbstractArtifactRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.spi.AbstractMetadataRepository;


/**
 * A utility class that uses p2's repository tools to manage the update sites for Tycho builds.
 *
 */
@SuppressWarnings("restriction")
public class UpdateSiteGenerator
{
  /**
   * The files and folders that comprise a simple update site.
   */
  private static final List<String> UPDATE_SITE_CONTENT = Arrays.asList(
    new String []{ "binary", "features", "plugins", "artifacts.jar", "artifacts.xml.xz", "content.jar", "content.xml.xz", "p2.index" });

  /**
   * The valid values for {@code publish.build.type}.
   */
  public static final List<String> BUILD_TYPES = Arrays.asList(new String []{ "nightly", "milestone", "release" });

  /**
   * The prefix qualifier associated with the {@link #BUILD_TYPES}.
   */
  public static final List<String> BUILD_TYPE_QUALIFIERS = Arrays.asList(new String []{ "N", "S", "R" });

  /**
   * Where the downloads are expected to be using https.
   */
  private static final String HTTPS_DOWNLOAD_ECLIPSE_ORG = "https://download.eclipse.org/";

  /**
   * Where the downloads are expected to be using http.
   */
  private static final String HTTP_DOWNLOAD_ECLIPSE_ORG = "http://download.eclipse.org/";

  /**
   * The label used to identify the project name.
   */
  private final String projectLabel;

  /**
   * The root folder of the project.
   */
  private final Path projectRoot;

  /**
   * The number of retained nightly builds.
   * When there are more, they older ones are deleted.
   */
  private final int retainedNightlyBuilds;

  /**
   * The root location of the update site structure.
   */
  private final Path updateSiteRoot;

  /**
   * A cache of the information used to populated bread crumbs.
   * It's a map from label to URL.
   */
  private final Map<String, String> breadcrumbs;

  /**
   * The URL of the site's favicon.
   */
  private final String favicon;

  /**
   * The URL of the title image.
   */
  private final String titleImage;

  /**
   * The URL of the build that produces these sites.
   */
  private final String buildURL;

  /**
   * The URL of the image used in the body text.
   */
  private final String bodyImage;

  /**
   * The URL at which this site will live once promoted.
   */
  private final String targetURL;

  /**
   * Whether to print logging information.
   */
  private final boolean verbose;

  /**
   *  Creates an instance.
   *
   * @param projectLabel the label used to identify the project name.
   * @param buildURL the URL of the site that produces these builds.
   * @param projectRoot the root location of the site.
   * @param relativeTargetFolder the relative location below the root at which to targe the generation.
   * @param targetURL the URL at which the site will live once promoted.
   * @param retainedNightlyBuilds the number of nightly builds to retain.
   * @param breadcrumbs a map from label to URL for populating the site's bread crubms.
   * @param favicon the URL of the site's favicon.
   * @param titleImage the URL of the site's title image.
   * @param bodyImage the URL if the image used in the body.
   * @param verbose whether to print logging information.
   * @throws IOException
   */
  public UpdateSiteGenerator(
    String projectLabel,
    String buildURL,
    Path projectRoot,
    Path relativeTargetFolder,
    String targetURL,
    int retainedNightlyBuilds,
    Map<String, String> breadcrumbs,
    String favicon,
    String titleImage,
    String bodyImage,
    boolean verbose) throws IOException
  {
    this.projectLabel = projectLabel;
    this.buildURL = buildURL;
    this.targetURL = targetURL;
    this.breadcrumbs = breadcrumbs;
    this.favicon = favicon;
    this.titleImage = titleImage;
    this.bodyImage = bodyImage;
    this.verbose = verbose;
    Assert.isTrue(!relativeTargetFolder.isAbsolute(), "The relative target folder '" + relativeTargetFolder + "' must be relative");

    this.projectRoot = getCanonicalPath(projectRoot);
    this.retainedNightlyBuilds = retainedNightlyBuilds;
    updateSiteRoot = projectRoot.resolve(relativeTargetFolder);
  }

  /**
   * Returns the label used to identify the project name.
   * @return the label used to identify the project name.
   */
  public String getProjectLabel()
  {
    return projectLabel;
  }

  /**
   * Returns the URL of the site that produces these builds.
   * @return the URL of the site that produces these builds.
   */
  public String getBuildURL()
  {
    return buildURL;
  }

  /**
   * Returns the URL of the site's favicon.
   * @return the URL of the site's favicon.
   */
  public String getFavicon()
  {
    return favicon;
  }

  /**
   * Return the URL of the site's title image.
   * @return the URL of the site's title image.
   */
  public String getTitleImage()
  {
    return titleImage;
  }

  /**
   * The URL of the image used in the body text.
   * @return the URL of the image used in the body text.
   */
  public String getBodyImage()
  {
    return bodyImage;
  }

  /**
   * A map from label to URL used for populating the site's bread crumbs.
   * @return a map from label to URL used for populating the site's bread crumbs.
   */
  public Map<String, String> getBreadcrumbs()
  {
    return breadcrumbs;
  }

  /**
   * The root location of the site's project.
   * @return the root location of the site's project.
   */
  public Path getProjectRoot()
  {
    return projectRoot;
  }

  /**
   * The location at which the site will exist once promoted.
   * @return the location at which the site will exist once promoted.
   */
  public String getTargetURL()
  {
    return targetURL;
  }

  /**
   * The URL relative to the target URL.
   * @param path an absolute path within the site.
   * @return the URL relative to the target URL.
   */
  public String getTargetRelativeURL(Path path)
  {
    String targetURL = getTargetURL();
    if (targetURL != null)
    {
      Path relativeFolder = getProjectRoot().relativize(path);
      return targetURL + "/" + relativeFolder.toString().replace('\\', '/');
    }
    else
    {
      return UpdateSiteGenerator.createURI(path).toString();
    }
  }

  /**
   * The root of the update site.
   * @return the root of the update site.
   */
  public Path getUpdateSiteRoot()
  {
    return updateSiteRoot;
  }

  /**
   * The number of retained nightly builds.
   * @return the number of retained nightly builds.
   */
  public int getRetainedNightlyBuilds()
  {
    return retainedNightlyBuilds;
  }

  /**
   * Returns the destination location at which to promote the give type of build with the given name.
   *
   * @param buildType the build type.
   * @param name the name of the folder.
   * @return the destination location at which to promote the give type of build with the given name.
   *
   * @throws IOException
   */
  public Path getPromoteUpdateSiteDestination(String buildType, String name) throws IOException
  {
    return getCanonicalPath(updateSiteRoot.resolve(buildType).resolve(getTargetFolder(buildType, name)));
  }

  /**
   * Mirrors the source repository location to the destination location.
   * This is used for nightly and milestone promotion.
   *
   * @param source the source repository.
   * @param buildType the build type.
   * @param buildTimestamp the build timestamp in the form yyyyMMddHHmm.
   * @return the destination location to which the repository is promoted.
   * @throws Exception
   */
  public Path promoteUpdateSite(final Path source, final String buildType, String buildTimestamp) throws Exception
  {
    final Path destination = getPromoteUpdateSiteDestination(buildType, buildTimestamp);
    return mirrorUpdateSite(source, destination, buildType);
  }

  /**
   * Mirrors the source repository to the destination repository.
   * The name of the repository is computed from the build type.
   * the <a href="https://wiki.eclipse.org/Equinox/p2/p2.mirrorsURL">p2.mirrorsURL</a> properly configured,
   * and {@code .xml.xz} formats are produced along with a {@code p2.index}.
   *
   * @param source the source repository location.
   * @param destination the destination repository location.
   * @param buildType the build type.
   * @return the destination location to which the repository is mirrored.
   * @throws Exception
   */
  public Path mirrorUpdateSite(final Path source, final Path destination, final String buildType) throws Exception
  {
    if (verbose)
    {
      System.out.println("Mirroring '" + source + "' to '" + destination);
    }

    MirrorApplication mirrorApplication = new MirrorApplication()
      {
        @Override
        protected void finalizeRepositories()
        {
          if (destinationMetadataRepository instanceof AbstractMetadataRepository)
          {
            String repositoryName = getRepositoryName(destinationMetadataRepository) + ' ' + getLabel(buildType);
            destinationMetadataRepository.setProperty(IRepository.PROP_NAME, repositoryName);

            if (destinationArtifactRepository instanceof AbstractArtifactRepository)
            {
              destinationArtifactRepository.setProperty(IRepository.PROP_NAME, repositoryName);
              if (!"nightly".equals(buildType))
              {
                String targetRelativeURL = getTargetRelativeURL(destination);
                String downloadURL = getDownloadURL(destination);
                if (!targetRelativeURL.equals(downloadURL))
                {
                  destinationArtifactRepository.setProperty("p2.mirrorsURL", downloadURL);
                }
              }
            }
          }

          super.finalizeRepositories();
        }
      };

    for (String fileName : new String []{ "artifacts.xml.xz", "content.xml.xz", "p2.index" })
    {
      Path artifacts = destination.resolve(fileName);
      if (Files.isRegularFile(artifacts))
      {
        Files.delete(artifacts);
      }
    }

    mirrorApplication.initializeFromArguments(new String []{ "-source", source.toString(), "-destination", destination.toString(), "-writeMode", "clean" });

    SlicingOptions slicingOptions = new SlicingOptions();
    slicingOptions.latestVersionOnly(true);
    mirrorApplication.setSlicingOptions(slicingOptions);

    IStatus status = mirrorApplication.run(new NullProgressMonitor());
    if (!status.isOK())
    {
      throw new CoreException(status);
    }

    xzCompress(destination);

    return destination;
  }

  /**
   * Returns the URL used for downloads of the give path at its final location.
   * @param target an absolute path for what's to be down loaded.
   * @return the URL used for downloads of the give path at its final location.
   */
  public String getDownloadURL(Path target)
  {
    String targetRelativeURL = getTargetRelativeURL(target);
    if (targetRelativeURL.startsWith(HTTPS_DOWNLOAD_ECLIPSE_ORG))
    {
      return "https://www.eclipse.org/downloads/download.php?file=/" + targetRelativeURL.substring(HTTPS_DOWNLOAD_ECLIPSE_ORG.length());
    }
    else if (targetRelativeURL.startsWith(HTTP_DOWNLOAD_ECLIPSE_ORG))
    {
      return "http://www.eclipse.org/downloads/download.php?file=/" + targetRelativeURL.substring(HTTP_DOWNLOAD_ECLIPSE_ORG.length());
    }
    else
    {
      return targetRelativeURL;
    }
  }

  /**
   * Returns the latest (newest) child repository at the given repository location.
   * @param repository a repository location.
   * @return the latest child repository at the given repository location.
   */
  public Path getLatest(Path repository)
  {
    RepositoryAnalyzer repositoryAnalyzer = getRepositoryAnalyzer(Collections.singletonList(repository));
    List<Path> children = repositoryAnalyzer.getChildren();
    sort(children);
    return children.get(0);
  }

  /**
   * Creates and returns the repository analyzer for the give repositories.
   * @param repositories a list of repository locations.
   *
   * @return the repository analyzer.
   */
  public RepositoryAnalyzer getRepositoryAnalyzer(List<Path> repositories)
  {
    RepositoryAnalyzer repositoryAnalyzer = new RepositoryAnalyzer();
    RepositoryDescriptor repositoryDescriptor = new RepositoryDescriptor();
    for (Path repository : repositories)
    {
      repositoryDescriptor.setLocation(createURI(repository));
      repositoryAnalyzer.addSource(repositoryDescriptor);
    }

    return repositoryAnalyzer;
  }

  /**
   * Returns the title case label for the give build type.
   * @param buildType the build type.
   *
   * @return the title case label for the give build type.
   */
  private String getLabel(String buildType)
  {
    return Character.toUpperCase(buildType.charAt(0)) + buildType.substring(1);
  }

  /**
   * Returns the computed name from the features in the repository.
   * This will be a featur ename followed the range of versions of the installable units in the repository.
   * If there is only one version then it will be followed by only that one version.
   * If there are none, then it's just 'Unknown'.
   * @param repository the repository.
   * @return the computed name for the repository.
   */
  private String getRepositoryName(IMetadataRepository repository)
  {
    IQueryResult<IInstallableUnit> groups = repository.query(QueryUtil.createIUGroupQuery(), new NullProgressMonitor());
    List<BasicVersion> versions = new ArrayList<BasicVersion>();
    Set<String> names = new TreeSet<>();
    for (Iterator<IInstallableUnit> i = groups.iterator(); i.hasNext();)
    {
      IInstallableUnit group = i.next();
      Version iuVersion = group.getVersion();
      if (iuVersion.isOSGiCompatible() && iuVersion instanceof BasicVersion)
      {
        BasicVersion basicVersion = (BasicVersion)iuVersion;
        if (!versions.contains(basicVersion))
        {
          versions.add(basicVersion);
        }
      }

      String name = group.getProperty(IInstallableUnit.PROP_NAME, null);
      names.add(name);
    }

    String name = names.isEmpty() ? "Unknown" : names.iterator().next();
    if (versions.isEmpty())
    {
      return name;
    }
    else if (versions.size() == 1)
    {
      BasicVersion version = versions.get(0);
      return name + " " + version.getMajor() + "." + version.getMinor() + "." + version.getMicro();
    }
    else
    {
      Collections.sort(versions);
      BasicVersion minVersion = versions.get(0);
      BasicVersion maxVersion = versions.get(versions.size() - 1);
      return name + " " + minVersion.getMajor() + "." + minVersion.getMinor() + "." + minVersion.getMicro() + "-" + maxVersion.getMajor() + "." + maxVersion.getMinor() + "."
        + maxVersion.getMicro();
    }
  }

  /**
   * Returns the destination folder for the given build type and whether it is a latest composite or not.
   *
   * @param buildType the build type.
   * @param latest whether this is a composite for the latest build.
   * @return the destination folder.
   *
   * @throws IOException
   */
  public Path getCompositeUpdateSiteDestination(String buildType, boolean latest) throws IOException
  {
    Path destinationBuildsTypeFolder = updateSiteRoot.resolve(buildType);
    if (latest)
    {
      destinationBuildsTypeFolder = destinationBuildsTypeFolder.resolve("latest");
    }
    return getCanonicalPath(destinationBuildsTypeFolder);
  }

  /**
   * Creates a composite that references the given source repositories, at a location determined by the build type and the latest indicator.
   * @param sources the source repositories.
   * @param buildType the build type.
   * @param latest whether this is is a composite referring to the latest.
   *
   * @throws Exception
   */
  public Path composeUpdateSites(List<Path> sources, final String buildType, final boolean latest) throws Exception
  {
    Path destination = getCompositeUpdateSiteDestination(buildType, latest);
    final URI destinationURI = createURI(destination.toFile());

    // We must set the user dir to ensure that we produce a composite that uses relative URIs!
    //
    String oldUserDir = System.getProperty("user.dir");
    try
    {
      System.setProperty("user.dir", destination.toString());

      CompositeRepositoryApplication compositeRepositoryApplication = new CompositeRepositoryApplication()
        {
          @Override
          protected void finalizeRepositories()
          {
            // Compute an appropriate name for the repository after it has been populated.
            if (destinationMetadataRepository instanceof ICompositeRepository<?>)
            {
              String repositoryName = getRepositoryName(destinationMetadataRepository) + ' ' + getLabel(buildType) + (latest ? " Latest" : " Composite");
              destinationMetadataRepository.setProperty(IRepository.PROP_NAME, repositoryName);
              save((ICompositeRepository<?>)destinationMetadataRepository);

              if (destinationArtifactRepository instanceof ICompositeRepository<?>)
              {
                destinationArtifactRepository.setProperty(IRepository.PROP_NAME, repositoryName);
                save((ICompositeRepository<?>)destinationArtifactRepository);
              }
            }

            super.finalizeRepositories();

            Path p2INF = destination.resolve("p2.inf");
            try
            {
              Files.write(
                p2INF,
                Arrays.asList(
                  new String []{ "version=1", "metadata.repository.factory.order=compositeContent.xml,!", "artifact.repository.factory.order=compositeArtifacts.xml,!" }));
            }
            catch (IOException exception)
            {
              throw new RuntimeException(exception);
            }
          }

          private void save(ICompositeRepository<?> repository)
          {
            // Unfortunately p2 provides no API for saving the repository after changing the name.
            // The repository is saved only when children are added or removed.
            // So this a good opportunity to double check that all the children are using a relative URI.
            List<URI> children = repository.getChildren();
            repository.removeAllChildren();
            for (URI child : children)
            {
              URI relativeSourceURI = relativize(child, destinationURI);
              if (relativeSourceURI == child)
              {
                throw new IllegalArgumentException("The URI '" + child + "' cannot be made relative to '" + destinationURI + "'");
              }
              repository.addChild(relativeSourceURI);
            }
          }
        };
      compositeRepositoryApplication.setRemoveAll(true);

      RepositoryDescriptor destinationRepositoryDescriptor = new RepositoryDescriptor();
      destinationRepositoryDescriptor.setLocation(destinationURI);
      compositeRepositoryApplication.addDestination(destinationRepositoryDescriptor);

      for (Path source : sources)
      {
        RepositoryDescriptor childRepositoryDescriptor = new RepositoryDescriptor();
        URI sourceURI = createURI(source);
        URI relativeSourceURI = relativize(sourceURI, destinationURI);
        if (relativeSourceURI == sourceURI)
        {
          // We must use relative URIs!
          throw new IllegalArgumentException("The URI '" + sourceURI + "' cannot be made relative to '" + destinationURI + "'");
        }

        childRepositoryDescriptor.setLocation(relativeSourceURI);
        compositeRepositoryApplication.addChild(childRepositoryDescriptor);
      }

      IStatus status = compositeRepositoryApplication.run(new NullProgressMonitor());
      if (!status.isOK())
      {
        throw new CoreException(status);
      }
    }
    finally
    {
      System.setProperty("user.dir", oldUserDir);
    }

    return destination;
  }

  /**
   * Returns the relative URI that the target can use to reference the source.
   *
   * @param sourceURI the source URI.
   * @param targetURI the target URI.
   * @return the relative URI.
   */
  private static URI relativize(URI sourceURI, URI targetURI)
  {
    URI relativeSourceURI = targetURI.relativize(sourceURI);
    if (relativeSourceURI == sourceURI)
    {
      URI parentTargetURI = targetURI.resolve("..");
      if (parentTargetURI != targetURI)
      {
        URI parentRelativeSourceURI = relativize(relativeSourceURI, parentTargetURI);
        if (parentRelativeSourceURI != relativeSourceURI)
        {
          String string = parentRelativeSourceURI.toString();
          int index = string.indexOf('/');
          relativeSourceURI = URI.create(".." + string.substring(index));
        }
      }
    }
    return relativeSourceURI;
  }

  /**
   * Produces the {@code .xml.zx} forms as well as the {@code p2.index}.
   *
   * @param targetRepository
   * @throws IOException
   */
  private void xzCompress(Path targetRepository) throws IOException
  {
    XZCompressor xzCompressor = new XZCompressor();
    xzCompressor.setRepoFolder(targetRepository.toString());
    xzCompressor.compressRepo();
  }

  /**
   * Returns the qualified target folder depending on the build type and the give target subfolder.
   *
   * @param buildType the build type.
   * @param targetSubfolder the target subfolder.
   * @return the qualified target folder depending on the build type and the give target subfolder.
   */
  private String getTargetFolder(String buildType, String targetSubfolder)
  {
    return BUILD_TYPE_QUALIFIERS.get(BUILD_TYPES.indexOf(buildType)) + targetSubfolder;
  }

  /**
   * Create a URI for the path.
   * @param path the path.
   * @return the corresponding URI.
   */
  public static URI createURI(Path path)
  {
    return createURI(path.toFile());
  }

  /**
   * Create a URI for the file.
   * @param file the file.
   * @return the corresponding URI.
   */
  public static URI createURI(File file)
  {
    try
    {
      // Java has a bad habit of adding a trailing "/" if the file exists as a directory.
      // We never want that because it gives different results depending on existence.
      URI uri = file.getCanonicalFile().toURI();
      String literal = uri.toString();
      return literal.endsWith("/") ? new URI(literal.substring(0, literal.length() - 1)) : uri;
    }
    catch (Exception exception)
    {
      throw new IllegalArgumentException(exception);
    }
  }

  /**
   * Generates the index.html for the target repository, as well as recursively for the children.
   * @param target the target repository.
   * @throws Exception
   */
  public void generateIndex(Path target) throws Exception
  {
    UpdateSiteIndexGenerator updateSiteIndexGenerator = new UpdateSiteIndexGenerator(target, this);
    generateIndex(updateSiteIndexGenerator);
  }

  /**
   * Generates the index.html for the target repository, as well as recursively for the children.
   * @param updateSiteIndexGenerator the update site index generator used to generate the sites.
   * @throws Exception
   */
  private void generateIndex(UpdateSiteIndexGenerator updateSiteIndexGenerator) throws Exception
  {
    String indexHTML = new UpdateSiteIndex().generate(updateSiteIndexGenerator);
    try (OutputStream out = Files.newOutputStream(updateSiteIndexGenerator.getFolder().resolve("index.html")))
    {
      byte[] bytes = indexHTML.getBytes("UTF-8");
      out.write(bytes);
    }

    for (UpdateSiteIndexGenerator child : updateSiteIndexGenerator.getChildren())
    {
      generateIndex(child);

    }
  }

  /**
   * Generates a zip and SHA hashes for all simple repositories nested in the target folder.
   *
   * @param target the target folder.
   * @throws Exception
   */
  public void generateDownloads(Path target) throws Exception
  {
    if (Files.isDirectory(target))
    {
      if (Files.isRegularFile(target.resolve("content.jar")))
      {
        Path archiveFile = getArchiveFile(target);

        List<Callable<Path>> callables = new ArrayList<>();
        for (String digest : new String []{ "SHA-256", "SHA-512" })
        {
          if (!Files.isRegularFile(getDigestFile(archiveFile, digest)))
          {
            callables.add(() -> createDigest(archiveFile, digest));
          }
        }

        // Because we will generally rsync the changes, we don't want to download the archives from the server.
        // So we only download the shas and if only if they are not present to do we generate an archive to send to the server.
        //
        if (!callables.isEmpty())
        {
          createArchive(target);
          for (Callable<Path> callable : callables)
          {
            callable.call();
          }
        }
      }
      else
      {
        for (Path child : Files.list(target).collect(Collectors.toList()))
        {
          if (Files.isDirectory(child))
          {
            generateDownloads(child);
          }
        }
      }
    }
  }

  /**
   * Sorts the list of repository folders to ensure that they are semantically order.
   *
   * @param repositories the repository folders.
   */
  public static void sort(List<Path> repositories)
  {
    Map<Long, Path> orderedRepositories = new TreeMap<>();
    for (Path repository : repositories)
    {
      String name = repository.getFileName().toString();
      if ("release".equals(name))
      {
        orderedRepositories.put(0L, repository);
      }
      else if ("milestone".equals(name))
      {
        orderedRepositories.put(1L, repository);
      }
      else if ("nightly".equals(name))
      {
        orderedRepositories.put(2L, repository);
      }
      else if ("latest".equals(name))
      {
        orderedRepositories.put(Long.MIN_VALUE, repository);
      }
      else if (name.charAt(0) == 'N' || name.charAt(0) == 'S' || name.charAt(0) == 'R')
      {
        orderedRepositories.put(-Long.parseLong(name.substring(1).replaceAll("[-_]", "")), repository);
      }
      else
      {
        throw new IllegalArgumentException(repository.toString());
      }
    }

    repositories.clear();
    repositories.addAll(orderedRepositories.values());
  }

  public static Path getCanonicalPath(Path path) throws IOException
  {
    try
    {
      return path.toRealPath();
    }
    catch (IOException exception)
    {
      return path.toFile().getCanonicalFile().toPath();
    }
  }

  /**
   * Returns the name of the zipped archive for the give simple repository.
   *
   * @param repository the simple repository.
   * @return
   */
  public Path getArchiveFile(Path repository)
  {
    String name = projectLabel.replace(" ", "-") + "-Updates-" + repository.getFileName() + ".zip";
    return repository.resolve(name);
  }

  /**
   * Creates a zip archive for the simple repository.
   *
   * @param repository the simple repository
   * @return the created archive.
   *
   * @throws IOException
   */
  public Path createArchive(Path repository) throws IOException
  {
    Path archiveFile = getArchiveFile(repository);
    Path folder = archiveFile.getParent();
    if (!Files.isDirectory(folder) || !Files.isRegularFile(folder.resolve("content.jar")))
    {
      throw new IllegalStateException(repository + "is not a valid p2 repository");
    }

    boolean delete = false;
    FileOutputStream fileOutputStream = null;
    ZipOutputStream zipOutputStream = null;
    try
    {
      fileOutputStream = new FileOutputStream(archiveFile.toFile());
      zipOutputStream = new ZipOutputStream(fileOutputStream);
      for (Path file : Files.list(folder).collect(Collectors.toList()))
      {
        String name = file.getFileName().toString();
        if (UPDATE_SITE_CONTENT.contains(name))
        {
          visit(zipOutputStream, folder, name);
        }
      }
    }
    catch (IOException exception)
    {
      delete = true;
    }
    finally
    {
      if (zipOutputStream != null)
      {
        zipOutputStream.close();
      }
      if (fileOutputStream != null)
      {
        fileOutputStream.close();
      }
      if (delete)
      {
        Files.delete(archiveFile);
      }
    }

    return archiveFile;
  }

  /**
   * Visits the folders recursively to zip all files.
   *
   * @param zipOutputStream the target archive.
   * @param root the root at which we started visiting.
   * @param path the relative path we are currently visiting.
   *
   * @throws IOException
   */
  private static void visit(ZipOutputStream zipOutputStream, Path root, String path) throws IOException
  {
    Path file = root.resolve(path);
    if (Files.isRegularFile(file))
    {
      ZipEntry zipEntry = new ZipEntry(path);
      zipOutputStream.putNextEntry(zipEntry);
      InputStream fileInputStream = null;
      try
      {
        fileInputStream = Files.newInputStream(file);
        byte[] bytes = new byte [10000];
        for (int length = fileInputStream.read(bytes); length != -1; length = fileInputStream.read(bytes))
        {
          zipOutputStream.write(bytes, 0, length);
        }
        zipOutputStream.closeEntry();
      }
      finally
      {
        if (fileInputStream != null)
        {
          fileInputStream.close();
        }
      }
    }
    else
    {
      for (Path child : Files.list(file).collect(Collectors.toList()))
      {
        visit(zipOutputStream, root, path + "/" + child.getFileName());
      }
    }
  }

  /**
   * Returns the file location at which a digest for the given algorithm will be generated.
   * @param target the target file for the digest.
   * @param algorithm the algorithm used to compute the digest.
   * @return the file location at which a digest for the given algorithm will be generated.
   */
  public static Path getDigestFile(Path target, String algorithm)
  {
    Path result = target.getParent().resolve(target.getFileName() + "." + algorithm.toLowerCase().replaceAll("-", ""));
    return result;
  }

  /**
   * Creates a file containing the digest for the given file using the given algorithm.
   *
   * @param file the file to digest.
   * @param algorithm the algorithm to use for digesting.
   * @return the location of the digest file.
   *
   * @throws IOException
   */
  private static Path createDigest(Path file, String algorithm) throws IOException
  {
    Path result = getDigestFile(file, algorithm);
    try
    {
      MessageDigest instance = MessageDigest.getInstance(algorithm);
      InputStream in = null;
      OutputStream out = null;
      try
      {
        in = Files.newInputStream(file);
        byte[] bytes = new byte [10000];
        for (int length = in.read(bytes); length != -1; length = in.read(bytes))
        {
          instance.update(bytes, 0, length);
        }
        byte[] digest = instance.digest();

        StringBuilder body = new StringBuilder();
        for (int i = 0; i < digest.length; ++i)
        {
          body.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
        }

        body.append(" *");
        body.append(file.getFileName());
        out = Files.newOutputStream(result);
        out.write(body.toString().getBytes("UTF-8"));
      }
      finally
      {
        if (in != null)
        {
          in.close();
        }

        if (out != null)
        {
          out.close();
        }
      }
    }
    catch (NoSuchAlgorithmException exception)
    {
      throw new IllegalArgumentException(exception);
    }

    return result;
  }

  /**
   * A utility class used to load a repository in order to analyze its contents.
   * This needs to be reworked to be more general.
   */
  public static class RepositoryAnalyzer extends AbstractApplication
  {
    /**
     * The pattern for finding the commit ID in a branding plugin's {@code about.mappings}.
     */
    private static final Pattern COMMIT_ID_PATTERN = Pattern.compile("^1=(.*)$", Pattern.MULTILINE);

    /**
     * The pattern for a well-formed Git commit ID.
     */
    private static final Pattern VALID_COMMIT_ID_PATTERN = Pattern.compile("^([0-9a-fA-F]+)$");

    /**
     * The pattern for finding the commit ID in a branding plugin's {@code about.mappings}.
     */
    private static final Pattern BUILD_ID_PATTERN = Pattern.compile("^0=(.*)$", Pattern.MULTILINE);

    /**
     * The pattern for a well-formed build ID.
     */
    private static final Pattern VALID_BUILD_ID_PATTERN = Pattern.compile("^[MNRS]?(\\d{12})$");

    /**
     * The pattern for finding a child location in a {@code compositeContent.xml}.
     */
    private static final Pattern CHILD_LOCATION_PATTERN = Pattern.compile("<child location='([^']*)'");

    /**
     * Returns the repository name.
     *
     * @return the repository name.
     */
    public String getName()
    {
      return getMetadataRepository().getName();
    }

    /**
     * Returns the two-segment version of the largest version of the {@code org.eclipse.emf.sdk.feature.group} in the repository.
     *
     * @return the version of the EMF SDK.
     *
     * @throws ProvisionException
     */
    public String getVersion() throws ProvisionException
    {
      IMetadataRepository repository = getCompositeMetadataRepository();
      IQueryResult<IInstallableUnit> query = repository.query(QueryUtil.createIUQuery("org.eclipse.emf.sdk.feature.group"), new NullProgressMonitor());
      BasicVersion maxVersion = null;
      for (Iterator<IInstallableUnit> i = query.iterator(); i.hasNext();)
      {
        IInstallableUnit iu = i.next();
        Version version = iu.getVersion();
        if (version instanceof BasicVersion)
        {
          BasicVersion basicVersion = (BasicVersion)version;
          if (maxVersion == null || maxVersion.compareTo(basicVersion) < 0)
          {
            maxVersion = basicVersion;
          }
        }
      }

      return maxVersion.getMajor() + "." + maxVersion.getMinor();
    }

    /**
     * Returns the list of child locations in the composite, or {@code null}, if the repository isn't a composite.
     * The URIs in the repository must be file locations and those are the locations returned.
     *
     * @return the list of child locations in the composite, or {@code null}, if the repository isn't a composite.
     */
    public List<Path> getChildren()
    {
      IMetadataRepository metadataRepository = getMetadataRepository();
      URI location = metadataRepository.getLocation();
      File file = new File(new File(location), "compositeContent.jar");
      if (file.isFile() && metadataRepository instanceof ICompositeRepository<?>)
      {
        List<Path> result = new ArrayList<>();
        ICompositeRepository<?> compositeRepository = (ICompositeRepository<?>)metadataRepository;
        List<URI> children = compositeRepository.getChildren();
        for (URI uri : children)
        {
          try
          {
            result.add(Paths.get(uri));
          }
          catch (Exception exception)
          {
            throw new IllegalStateException("The child '" + uri + "' of '" + location + "' is not a file in the file system.", exception);
          }
        }
        return result;
      }
      else
      {
        return null;
      }
    }

    /**
     * Returns a list of the actual value of each child element's location attribute in the composite, or {@code null} if the repository is not a composite.
     *
     * @return a list of the actual value of each child element's location attribute in the composite, or {@code null} if the repository is not a composite.
     */
    public List<String> getRawChildren()
    {
      IMetadataRepository metadataRepository = getMetadataRepository();
      URI location = metadataRepository.getLocation();
      File file = new File(new File(location), "compositeContent.jar");
      if (file.isFile())
      {
        List<String> result = new ArrayList<String>();
        ZipFile zipFile = null;
        try
        {
          zipFile = new ZipFile(file);
          ZipEntry entry = zipFile.getEntry("compositeContent.xml");
          InputStream inputStream = zipFile.getInputStream(entry);
          ByteArrayOutputStream out = new ByteArrayOutputStream();
          byte[] bytes = new byte [10000];
          for (int length = inputStream.read(bytes); length != -1; length = inputStream.read(bytes))
          {
            out.write(bytes, 0, length);
          }

          String content = new String(bytes, "UTF-8");
          for (Matcher matcher = CHILD_LOCATION_PATTERN.matcher(content); matcher.find();)
          {
            result.add(matcher.group(1));
          }
        }
        catch (Exception exception)
        {
          throw new IllegalStateException("Problems with " + file, exception);
        }
        finally
        {
          try
          {
            zipFile.close();
          }
          catch (IOException exception)
          {
            throw new IllegalStateException("Problems closing" + file, exception);
          }
        }

        return result;
      }
      else
      {
        return null;
      }
    }

    /**
     * Returns the sorted list of all the SDK features in the repository.
     * @return the sorted list of all the SDK features in the repository.
     */
    public List<String> getSDKs()
    {
      List<String> result = new ArrayList<String>();
      List<String> resultAll = new ArrayList<String>();
      IMetadataRepository repository = getCompositeMetadataRepository();
      IQueryResult<IInstallableUnit> query = repository.query(QueryUtil.createIUGroupQuery(), new NullProgressMonitor());
      for (Iterator<IInstallableUnit> i = query.iterator(); i.hasNext();)
      {
        IInstallableUnit iu = i.next();
        String name = iu.getProperty("org.eclipse.equinox.p2.name", null);
        if (!resultAll.contains(name))
        {
          resultAll.add(name);
        }

        if (iu.getId().endsWith(".sdk.feature.group") && !result.contains(name))
        {
          result.add(name);
        }
      }
      if (result.isEmpty())
      {
        Collections.sort(resultAll);
        return resultAll;
      }
      else
      {
        Collections.sort(result);
        return result;
      }
    }

    /**
     * Returns a sorted list of all the features in the repository.
     * @return a sorted list of all the features in the repository.
     */
    public List<String> getFeatures()
    {
      List<String> result = new ArrayList<String>();
      IMetadataRepository repository = getCompositeMetadataRepository();
      IQueryResult<IInstallableUnit> query = repository.query(QueryUtil.createIUGroupQuery(), new NullProgressMonitor());
      for (Iterator<IInstallableUnit> i = query.iterator(); i.hasNext();)
      {
        IInstallableUnit iu = i.next();
        if (!iu.getId().endsWith(".source.feature.group"))
        {
          String name = iu.getProperty("org.eclipse.equinox.p2.name", null);
          name += " " + iu.getVersion();
          name = name.substring(0, name.lastIndexOf('.'));
          if (!result.contains(name))
          {
            result.add(name);
          }
        }
      }
      Collections.sort(result);
      return result;
    }

    /**
     * Returns a map from bundle name to a list of information for that bundle for each bundle in the repository.
     * @return a map from bundle name to a list of information for that bundle for each bundle in the repository.
     */
    public Map<String, List<String>> getBundles()
    {
      Map<String, List<String>> result = new TreeMap<String, List<String>>();
      IMetadataRepository repository = getCompositeMetadataRepository();
      IQueryResult<IInstallableUnit> query = repository.query(QueryUtil.createIUAnyQuery(), new NullProgressMonitor());
      for (Iterator<IInstallableUnit> i = query.iterator(); i.hasNext();)
      {
        IInstallableUnit iu = i.next();
        String id = iu.getId();
        if (!id.endsWith(".source"))
        {
          List<String> lines = new ArrayList<String>();
          for (IProvidedCapability providedCapability : iu.getProvidedCapabilities())
          {
            String namespace = providedCapability.getNamespace();
            String name = providedCapability.getName();
            if ("org.eclipse.equinox.p2.eclipse.type".equals(namespace) && "bundle".equals(name))
            {
              String iuName = iu.getProperty("org.eclipse.equinox.p2.name", null);
              iuName += " " + iu.getVersion();
              iuName = iuName.substring(0, iuName.lastIndexOf('.'));
              if (!result.containsKey(iuName))
              {
                lines.add(0, "\u21D6 " + id + " " + iu.getVersion());
                result.put(iuName, lines);
              }
            }
            else if ("java.package".equals(namespace))
            {
              Version version = providedCapability.getVersion();
              lines.add("\u2196 " + name + (Version.emptyVersion.equals(version) ? "" : " " + version));
            }
          }

          for (IRequirement requirement : iu.getRequirements())
          {
            if (requirement instanceof IRequiredCapability)
            {
              IRequiredCapability requiredCapability = (IRequiredCapability)requirement;
              String namespace = requiredCapability.getNamespace();
              String line = null;
              if ("osgi.bundle".equals(namespace))
              {
                line = "\u21D8 ";
              }
              else if ("java.package".equals(namespace))
              {
                line = "\u2198 ";
              }
              if (line != null)
              {
                String name = requiredCapability.getName();
                VersionRange range = requiredCapability.getRange();

                line += name;
                if (!VersionRange.emptyRange.equals(range))
                {
                  line += " " + range;
                }

                if (requiredCapability.getMin() == 0)
                {
                  line += " optional";
                  if (requiredCapability.isGreedy())
                  {
                    line += " greedy";
                  }
                }

                lines.add(line);
              }
            }
          }
        }
      }
      return result;
    }

    /**
     * Returns a map from project name to the URL for the commit ID URL in that project's branding plugin.
     * @return a map from project name to the URL for the commit ID URL in that project's branding plugin.
     */
    public Map<String, String> getCommits()
    {
      Map<String, String> result = new LinkedHashMap<String, String>();
      getIDs(result, "org.eclipse.emf", COMMIT_ID_PATTERN, VALID_COMMIT_ID_PATTERN, "http://git.eclipse.org/c/emf/org.eclipse.emf.git/commit/?id=");
      getIDs(result, "org.eclipse.xsd", COMMIT_ID_PATTERN, VALID_COMMIT_ID_PATTERN, "http://git.eclipse.org/c/xsd/org.eclipse.xsd.git/commit/?id=");
      getDate();
      return result;
    }

    /**
     * Returns the build date as determined from the EMF project's branding plugin.
     * @return the build date as determined from the EMF project's branding plugin.
     */
    public String getDate()
    {
      Map<String, String> result = new LinkedHashMap<String, String>();
      getIDs(result, "org.eclipse.emf", BUILD_ID_PATTERN, VALID_BUILD_ID_PATTERN, "");
      if (!result.isEmpty())
      {
        String value = result.values().iterator().next();
        try
        {
          Date date = new SimpleDateFormat("yyyyMMddHHmm").parse(value);
          return new SimpleDateFormat("yyyy'-'MM'-'dd' at 'HH':'mm ").format(date);
        }
        catch (ParseException e)
        {
          // Ignore.
        }
      }

      return null;
    }

    /**
     * Populates the IDs with the information from {@code about.mappings} for the branding plugin with the give UI ID.
     * @param ids the IDs to populate.
     * @param iuID the ID of a branding plugin.
     * @param idPattern the pattern for finding the ID in the {@code about.mappings}
     * @param validIDPattern the pattern for validating and extracting the ID.
     * @param prefix the prefix that will be prepended to the ID.
     */
    private void getIDs(Map<String, String> ids, String iuID, Pattern idPattern, Pattern validIDPattern, String prefix)
    {
      IMetadataRepository metadataRepository = getCompositeMetadataRepository();
      IArtifactRepository artifactRepository = getCompositeArtifactRepository();
      IQueryResult<IInstallableUnit> ius = metadataRepository.query(QueryUtil.createIUQuery(iuID), new NullProgressMonitor());
      for (Iterator<IInstallableUnit> i = ius.iterator(); i.hasNext();)
      {
        IInstallableUnit iu = i.next();
        Collection<IArtifactKey> artifacts = iu.getArtifacts();
        for (IArtifactKey artifactKey : artifacts)
        {
          IArtifactDescriptor[] artifactDescriptors = artifactRepository.getArtifactDescriptors(artifactKey);
          for (IArtifactDescriptor artifactDescriptor : artifactDescriptors)
          {
            ByteArrayOutputStream artifact = new ByteArrayOutputStream();
            artifactRepository.getArtifact(artifactDescriptor, artifact, new NullProgressMonitor());
            ZipInputStream zipInputStream = null;
            try
            {
              zipInputStream = new ZipInputStream(new ByteArrayInputStream(artifact.toByteArray()));
              for (ZipEntry entry = zipInputStream.getNextEntry(); entry != null; entry = zipInputStream.getNextEntry())
              {
                String name = entry.getName();
                if ("about.mappings".equals(name))
                {
                  ByteArrayOutputStream content = new ByteArrayOutputStream();
                  byte[] bytes = new byte [1024];
                  for (int length = zipInputStream.read(bytes); length != -1; length = zipInputStream.read(bytes))
                  {
                    content.write(bytes, 0, length);
                  }

                  content.close();
                  String value = new String(content.toByteArray(), "UTF-8");
                  for (Matcher matcher = idPattern.matcher(value); matcher.find();)
                  {
                    String id = matcher.group(1);
                    Matcher idMatcher = validIDPattern.matcher(id);
                    if (idMatcher.matches())
                    {
                      ids.put(iuID.substring(iuID.lastIndexOf('.') + 1).toUpperCase(), prefix + idMatcher.group(1));
                    }
                    break;
                  }
                  break;
                }
              }
            }
            catch (IOException exception)
            {
              // Ignore.
            }
            finally
            {
              if (zipInputStream != null)
              {
                try
                {
                  zipInputStream.close();
                }
                catch (IOException e)
                {
                  // Ignore.
                }
              }
            }
            break;
          }
        }

        break;
      }
    }

    /**
     * Returns the metadata repository of this analyzer.
     * @return the metadata repository of this analyzer.
     */
    private IMetadataRepository getMetadataRepository()
    {
      ICompositeRepository<?> compositeMetadataRepository = (ICompositeRepository<?>)getCompositeMetadataRepository();
      IMetadataRepositoryManager metadataRepositoryManager = getMetadataRepositoryManager();
      try
      {
        return metadataRepositoryManager.loadRepository(compositeMetadataRepository.getChildren().get(0), new NullProgressMonitor());
      }
      catch (Exception exception)
      {
        throw new IllegalStateException(exception);
      }
    }

    @Override
    public IStatus run(IProgressMonitor monitor) throws ProvisionException
    {
      return Status.OK_STATUS;
    }
  }
}
