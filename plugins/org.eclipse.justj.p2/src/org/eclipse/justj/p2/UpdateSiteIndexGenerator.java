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


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.justj.codegen.model.util.Indexer.Property;
import org.eclipse.justj.p2.UpdateSiteGenerator.MavenDescriptor;
import org.eclipse.justj.p2.UpdateSiteGenerator.RepositoryAnalyzer;


/**
 * A utility class used by the {@code index.htmljet} template to generate an index.html.
 */
public class UpdateSiteIndexGenerator
{
  /**
   * The decorator used for indicating a version update.
   */
  public static final String UPDATED_DECORATOR = "\u2b06";

  /**
   * The decorator used for indicating a removed bundle.
   */
  public static final String REMOVED_DECORATOR = "\u232b";

  /**
   * The decorator used for indicating an added bundle.
   */
  public static final String ADDED_DECORATOR = "\u2a2d";

  /**
   * The ordered folders that will be indexed.
   */
  private static final String[] ROOT_FOLDERS = new String []{ "release", "milestone", "nightly" };

  /**
   * A pattern used by {@link #getShortBSN(String)} to produce shorter names.
   */
  private static final Pattern BSN_PATTERN = Pattern.compile("((?:org|com)\\.[^.]+\\.)(.*)");

  /**
   * The folder for this generator..
   */
  private final Path folder;

  /**
   * The update site generator used by this index generator.
   */
  private final UpdateSiteGenerator updateSiteGenerator;

  /**
   * The repository analyzer used to analyze the index generator's folder.
   */
  private final RepositoryAnalyzer repositoryAnalyzer;

  /**
   * The list of SDKs in this folder's repository.
   */
  private List<String> sdks;

  /**
   * The features in this folder's repository.
   */
  private Map<String, List<String>> features;

  /**
   * The bundles in this folder's repository.
   */
  private Map<String, List<String>> bundles;

  /**
   * The bundle sizes in this folder's repository.
   */
  private Map<String, Long> bundleSizes;

  /**
   * The update site index generator for the parent folder.
   */
  private final UpdateSiteIndexGenerator parent;

  /**
   * Additional properties to show for each bundle.
   */
  private Map<String, Map<String, String>> bundleDetails;

  /**
   * A cache used to generate a table summary.
   * @see #getTableChildren()
   */
  private Map<UpdateSiteIndexGenerator, Map<String, Set<IInstallableUnit>>> table;

  /**
   * An addition resource induced from the p2 metadata.
   */
  private Resource resource;

  /**
   * The cached children.
   * @see #getChildren()
   */
  private List<UpdateSiteIndexGenerator> children;

  /**
   * Creates an instance for the given folder with the given update site generator
   * @param folder
   * @param updateSiteGenerator
   */
  public UpdateSiteIndexGenerator(Path folder, UpdateSiteGenerator updateSiteGenerator)
  {
    this(folder, updateSiteGenerator, null);
  }

  /**
   * Create an instance for the give folder, update site generator and parent.
   * @param folder the folder.
   * @param updateSiteGenerator the update site generator.
   * @param parent the update site index generator of the parent folder or {@code null}.
   */
  public UpdateSiteIndexGenerator(Path folder, UpdateSiteGenerator updateSiteGenerator, UpdateSiteIndexGenerator parent)
  {
    this.updateSiteGenerator = updateSiteGenerator;

    this.folder = folder;
    this.parent = parent;

    List<Path> folders = new ArrayList<>();
    if (isRoot())
    {
      for (String child : ROOT_FOLDERS)
      {
        Path childFolder = folder.resolve(child);
        if (Files.isDirectory(childFolder))
        {
          folders.add(childFolder);
        }
      }

      // This is used for generating a super index.
      if (folders.isEmpty())
      {
        folders.add(folder);
      }
    }
    else
    {
      folders.add(folder);
    }

    folders.sort(new Comparator<Path>()
      {
        @Override
        public int compare(Path o1, Path o2)
        {
          return o2.compareTo(o1);
        }
      });

    repositoryAnalyzer = updateSiteGenerator.getRepositoryAnalyzer(folders);
  }

  /**
   * Returns the folder of this generator.
   * @return the folder of this generator.
   */
  public Path getFolder()
  {
    return folder;
  }

  /**
   * Returns the location of the resource in this folder, or {@code null}.
   * @return the location of the resource in this folder, or {@code null}.
   */
  public String getResourceURL()
  {
    Resource resource = getResource();
    return resource == null ? null : resource.getURI().lastSegment();
  }

  /**
   * Returns a resource induced from the p2 metadata.
   * @return a resource induced from the p2 metadata, or {@code null}.
   */
  public Resource getResource()
  {
    if (getChildren().isEmpty())
    {
      getBundles();
    }

    return resource;
  }

  /**
   * Returns the parent update site index generator.
   * @return the parent update site index generator.
   */
  public UpdateSiteIndexGenerator getParent()
  {
    return parent;
  }

  /**
   * Returns the root update site index generator.
   * It returns {@code this} if this is the root.
   * @return the root update site index generator.
   */
  public UpdateSiteIndexGenerator getRoot()
  {
    for (UpdateSiteIndexGenerator updateSiteIndexGenerator = this;; updateSiteIndexGenerator = updateSiteIndexGenerator.getParent())
    {
      if (updateSiteIndexGenerator.getParent() == null)
      {
        return updateSiteIndexGenerator;
      }
    }
  }

  /**
   * Returns whether this folder is the build root folder.
   * @return whether this folder is the build root folder.
   */
  public boolean isRoot()
  {
    return folder.equals(updateSiteGenerator.getUpdateSiteRoot());
  }

  /**
   * Returns whether this folder generates a summary table.
   * @return whether this folder generates a summary table.
   */
  public boolean hasSummary()
  {
    return isRoot() && updateSiteGenerator.getSummary() > 0;
  }

  /**
   * Returns the number of nightly builds that are retained.
   * @return the number of nightly builds that are retained.
   */
  public int getRetainedNightlyBuilds()
  {
    return updateSiteGenerator.getRetainedNightlyBuilds();
  }

  /**
   * Returns the build type of this folder.
   * It's not appropriate to call this method on the root folder.
   * @return the build type of this folder.
   */
  public String getBuildType()
  {
    for (String buildType : UpdateSiteGenerator.BUILD_TYPES)
    {
      if (folder.toString().replace('\\', '/').contains("/" + buildType))
      {
        return buildType;
      }
    }

    String name = folder.getFileName().toString();
    if (name.matches("[0-9]+-(03|06|09|12)"))
    {
      return name;
    }

    return "super";
  }

  /**
   * Returns whether is a latest folder.
   * @return whether is a latest folder.
   */
  public boolean isLatest()
  {
    return folder.endsWith("latest");
  }

  private UpdateSiteIndexGenerator getLatestUpdateSiteGenerator()
  {
    if (isLatest())
    {
      List<UpdateSiteIndexGenerator> siblings = getParent().getChildren();
      if (siblings.size() > 1)
      {
        return siblings.get(1);
      }
    }
    return this;
  }

  /**
   * Returns whether this folder has a download archive along with associated SHA digests.
   * @return whether this folder has a download archive along with associated SHA digests.
   */
  public boolean hasArchive()
  {
    // Because we rsync, the files might not actually exist, but ones are always created for simple repositories.
    // So return true if this is a simple repository.
    return Files.isRegularFile(getLatestUpdateSiteGenerator().folder.resolve("content.jar"));
  }

  /**
   * Returns the download archive location.
   * @return the download archive location.
   */
  public String getArchive()
  {
    UpdateSiteIndexGenerator latestUpdateSiteGenerator = getLatestUpdateSiteGenerator();
    Path archiveFile = latestUpdateSiteGenerator.updateSiteGenerator.getArchiveFile(latestUpdateSiteGenerator.folder);
    return archiveFile.toString();
  }

  /**
   * Returns the URL to be used for loading the archive.
   * @return the URL to be used for loading the archive.
   */
  public String getArchiveDownloadURL()
  {
    UpdateSiteIndexGenerator latestUpdateSiteGenerator = getLatestUpdateSiteGenerator();
    Path archiveFile = latestUpdateSiteGenerator.updateSiteGenerator.getArchiveFile(latestUpdateSiteGenerator.folder);
    return latestUpdateSiteGenerator.updateSiteGenerator.getDownloadURL(archiveFile).toString();
  }

  /**
   * Returns the digest location for the given algorithm.
   * @param algorithm the algorithm of the digest.
   * @return the digest location for the given algorithm.
   */
  public String getDigest(String algorithm)
  {
    UpdateSiteIndexGenerator latestUpdateSiteGenerator = getLatestUpdateSiteGenerator();
    Path archiveFile = latestUpdateSiteGenerator.updateSiteGenerator.getArchiveFile(latestUpdateSiteGenerator.folder);
    Path digestFile = UpdateSiteGenerator.getDigestFile(archiveFile, algorithm);
    if (isLatest())
    {
      return "../" + latestUpdateSiteGenerator.folder.getFileName() + "/" + digestFile.getFileName();
    }
    return digestFile.getFileName().toString();
  }

  /**
   * Returns the map used to populate the navigation side bar.
   * @return the map used to populate the navigation side bar.
   */
  public Map<String, String> getNavigation()
  {
    Map<String, String> result = new LinkedHashMap<String, String>();

    String siteURL = getSiteURL();
    UpdateSiteIndexGenerator root = getRoot();
    String rootSiteURL = root.getSiteURL();

    Map<String, String> breadcrumbs = updateSiteGenerator.getBreadcrumbs();
    Map.Entry<String, String> tailEntry = null;
    for (Map.Entry<String, String> entry : breadcrumbs.entrySet())
    {
      if (!entry.getValue().isEmpty())
      {
        tailEntry = entry;
      }
    }

    if (tailEntry != null)
    {
      result.put(tailEntry.getValue(), tailEntry.getKey());
    }

    StringBuilder prefix = new StringBuilder();
    for (int index = siteURL.indexOf('/', rootSiteURL.length()); index != -1; index = siteURL.indexOf('/', index + 1))
    {
      prefix.append("../");
    }

    String rootLabel = rootSiteURL.substring(rootSiteURL.lastIndexOf('/') + 1);
    rootLabel = updateSiteGenerator.getFolderLabel(rootLabel);
    result.put(prefix + "index.html", rootLabel);

    if (updateSiteGenerator.getSummary() > 0)
    {
      result.put(prefix + "table.html", "<b class='orange'>Summary</b>");
    }

    Map<String, String> archives = new LinkedHashMap<>(updateSiteGenerator.getArchives());
    for (UpdateSiteIndexGenerator child : root.getChildren())
    {
      String label = child.getLabel();
      if ("Milestone".equals(label) || "Nightly".equals(label))
      {
        if (!archives.isEmpty())
        {
          result.put("", "Archive");
          for (var entry : archives.entrySet())
          {
            result.put(entry.getValue(), "-" + entry.getKey());
          }
          archives.clear();
        }
      }

      child.visit(result, prefix.toString(), 0);
    }

    URI rootSiteURI = URI.create(siteURL + "/");
    for (Map.Entry<String, String> entry : result.entrySet())
    {
      String url = entry.getKey();
      int lastSegment = url.lastIndexOf('/');
      if ((lastSegment == -1 || rootSiteURI.resolve(url.substring(0, lastSegment)).toString().equals(siteURL)))
      {
        entry.setValue(entry.getValue() + "@");
        break;
      }
    }

    return result;
  }

  /**
   * Used to compose the {@link #getNavigation() navigation} side bar.
   * @param navigation the map to populate.
   * @param prefix the prefix used to build relative URL.
   * @param depth the depth at which we're currently visiting.
   */
  private void visit(Map<String, String> navigation, String prefix, int depth)
  {
    StringBuilder label = new StringBuilder();
    for (int i = 0; i < depth; ++i)
    {
      label.append('-');
    }
    label.append(getLabel());

    String siteURL = getSiteURL();
    String rootSiteURL = getRoot().getSiteURL();
    siteURL = siteURL.substring(rootSiteURL.length() + 1);
    siteURL = prefix + siteURL + "/" + getIndexName();

    navigation.put(siteURL, label.toString());
    for (UpdateSiteIndexGenerator child : getChildren())
    {
      child.visit(navigation, prefix, depth + 1);
    }
  }

  /**
   * Returns the site URL of this folder.
   * @return the site URL of this folder.
   */
  public String getSiteURL()
  {
    return getSiteURL(folder);
  }

  /**
   * This is specialized to ensure that on the real build host we use the http: URL not the file: URL that we use locally when testing.
   * @param folder the folder for which we want the site URL.
   * @return the site URL.
   */
  private String getSiteURL(Path folder)
  {
    return updateSiteGenerator.getTargetRelativeURL(folder);
  }

  /**
   * Returns the name of the index file.
   * @return the name of the index file.
   */
  public String getIndexName()
  {
    return "index.html";
  }

  /**
   * The parent-relative URL for this folder's index.
   * @return the parent-relative URL for this folder's index.
   */
  public String getRelativeIndexURL()
  {
    return folder.getFileName().resolve(getIndexName()).toString().replace('\\', '/');
  }

  /**
   * The relative URL to the child's index.
   * @return the relative URL to the child's index.
   */
  public String getRelativeIndexURL(UpdateSiteIndexGenerator child)
  {
    return folder.relativize(child.folder).resolve("index.html").toString().replace('\\', '/');
  }

  /**
   * This is used to populate the bread crumbs.
   * @param rawLastLink whether the final link should be one that allows a raw look at the server's file system.
   * @return the bread crumbs.
   */
  public Map<String, String> getBreadcrumbs(boolean rawLastLink)
  {
    Path root = updateSiteGenerator.getUpdateSiteRoot();
    Path projectRoot = updateSiteGenerator.getProjectRoot();

    Map<String, String> breadcumbs = new LinkedHashMap<String, String>(updateSiteGenerator.getBreadcrumbs());

    // Compute the labels in the right order continuing only as far as the project root.
    List<String> labels = new ArrayList<String>();
    for (Path file = folder; file.getParent() != null; file = file.getParent())
    {
      if (file.equals(projectRoot))
      {
        break;
      }

      String name = file.getFileName().toString();
      String titleName = updateSiteGenerator.getFolderLabel(name);
      while (breadcumbs.containsKey(titleName) || labels.contains(titleName))
      {
        // Make the label unique with zero-width whitespace.
        titleName += "&#8203;";
      }
      labels.add(0, titleName);
    }

    // Compute the up-links in the reverse order.
    Map<String, String> links = new LinkedHashMap<String, String>();
    String link = null;
    for (int i = labels.size() - 1; i >= 0; --i)
    {
      String label = labels.get(i);
      if (link != null)
      {
        Path linkFolder = folder.resolve(link).normalize().getParent();
        if (linkFolder.startsWith(root))
        {
          // Don't assume there is an index.html above the update site root.
          links.put(label, link);
        }
        else
        {
          links.put(label, link.replace("index.html", ""));
        }
      }
      else
      {
        links.put(label, null);
      }

      if (link == null)
      {
        link = "../index.html";
      }
      else
      {
        link = "../" + link;
      }
    }

    if (labels.size() > 1)
    {
      if (rawLastLink)
      {
        String targetURL = updateSiteGenerator.getTargetURL();
        if ("https://download.eclipse.org/justj".equals(targetURL))
        {
          links.put(labels.get(labels.size() - 1), "/justj/www/download.eclipse.org.php?file=" + projectRoot.relativize(folder).toString().replace('\\', '/'));
        }
        else if (targetURL != null && targetURL.startsWith("https://download.eclipse.org/"))
        {
          String prefix = targetURL.substring("https://download.eclipse.org/".length());
          links.put(labels.get(labels.size() - 1), "https://download.eclipse.org/justj?file=" + prefix + "/" + projectRoot.relativize(folder).toString().replace('\\', '/'));
        }
      }
      else
      {
        links.put(labels.get(labels.size() - 1), "index.html");
      }
    }

    // Build another map in the right order.
    for (String label : labels)
    {
      breadcumbs.put(label, links.get(label));
    }
    return breadcumbs;
  }

  /**
   * Returns the favicon URL for the site.
   * @return the favicon URL for the site.
   */
  public String getFavicon()
  {
    return updateSiteGenerator.getFavicon();
  }

  /**
   * Returns the URL of the image used in the title.
   * @return the URL of the image used in the title.
   */
  public String getTitleImage()
  {
    return updateSiteGenerator.getTitleImage();
  }

  /**
   * Returns the URL associated with the title image.
   * @return the URL associated with the title image.
   */
  public String getTitleURL()
  {
    Map<String, String> breadcrumbs = updateSiteGenerator.getBreadcrumbs();
    if (breadcrumbs.isEmpty())
    {
      return ".";
    }
    else
    {
      return breadcrumbs.values().iterator().next();
    }
  }

  /**
   * Returns the URL of the image used in the body text.
   * @return the URL of the image used in the body text.
   */
  public String getBodyImage()
  {
    return updateSiteGenerator.getBodyImage();
  }

  /**
   * Returns the title for this folder's index.
   * @return the title for this folder's index.
   */
  public String getTitle()
  {
    if (isRoot())
    {
      return updateSiteGenerator.getProjectLabel() + " Updates";
    }
    else
    {
      String name = repositoryAnalyzer.getName();
      String folderName = folder.getFileName().toString();
      if (!name.toLowerCase().contains(folderName.toLowerCase()))
      {
        name += " - " + Character.toUpperCase(folderName.charAt(0)) + folderName.substring(1);
      }
      return name;
    }
  }

  /**
   * Return the branding version of this repository.
   * @return the branding version of this repository.
   */
  public String getVersion()
  {
    try
    {
      return updateSiteGenerator.getVersion(folder, false);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Returns the label for this project as used in the body text.
   * @return the label for this project as used in the body text.
   */
  public String getProjectLabel()
  {
    return updateSiteGenerator.getProjectLabel();
  }

  /**
   * Returns the URL of the build instance used to produces this folder's site.
   * @return the URL of the build instance used to produces this folder's site.
   */
  public String getBuildURL()
  {
    return updateSiteGenerator.getBuildURL();
  }

  /**
   * Returns the title case label for this folder.
   * @return the title case label for this folder.
   */
  public String getLabel()
  {
    String name = getFolderName();
    if ("jres".equals(name))
    {
      return "JREs";
    }
    else
    {
      int versionQualifierIndex = name.indexOf(".v");
      if (versionQualifierIndex != -1)
      {
        name = name.substring(0, versionQualifierIndex) + "<span style='font-size: 80%'>" + name.substring(versionQualifierIndex) + "</span>";
      }
      return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
  }

  /**
   * Returns a map from repository URIs to relative site URLs of that repository.
   * @return a map from repository URIs to relative site URLs of that repository.
   */
  public Map<String, String> getRepositoryChildren()
  {
    List<String> repositories = repositoryAnalyzer.getRawChildren();
    if (repositories != null)
    {
      Map<String, String> result = new LinkedHashMap<String, String>();
      URI siteURL = URI.create(getSiteURL() + "/");
      for (String repository : repositories)
      {
        URI uri = URI.create(repository);
        if (uri.isAbsolute() || uri.isOpaque())
        {
          throw new IllegalStateException("Bad non-relative URI" + uri);
        }

        URI resolvedURI = siteURL.resolve(uri);
        result.put(resolvedURI.toString(), repository);
      }
      return result;
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns the name of this folder.
   * @return the name of this folder.
   */
  public String getFolderName()
  {
    return folder.getFileName().toString();
  }

  /**
   * Returns the primary features in this folder's repository.
   * @return the primary features in this folder's repository.
   */
  public List<String> getPrimaryFeatures()
  {
    if (sdks == null)
    {
      sdks = repositoryAnalyzer.getPrimaryFeatures(updateSiteGenerator.getIUFilterPattern(), updateSiteGenerator.getPrimaryIUFilterPattern());
    }
    return sdks;
  }

  /**
   * Returns whether the given feature is an primary feature.
   * @param feature the feature.
   * @return whether the given feature is an primary feature.
   */
  public boolean isPrimary(String feature)
  {
    List<String> primaryFeatures = getPrimaryFeatures();
    Map<String, List<String>> features = getFeatures();
    if (primaryFeatures.size() != features.size())
    {
      for (String sdk : getPrimaryFeatures())
      {
        if (feature.startsWith(sdk))
        {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns the feature information for this folder's repository.
   * @return the feature information for this folder's repository.
   */
  public Map<String, List<String>> getFeatures()
  {
    if (features == null)
    {
      features = repositoryAnalyzer.getFeatures(updateSiteGenerator.getIUFilterPattern());
    }
    return features;
  }

  /**
   * Returns the bundle information for this folder's repository.
   * @return the bundle information for this folder's repository.
   */
  public Map<String, List<String>> getBundles()
  {
    if (bundles == null)
    {
      bundleSizes = new TreeMap<>();
      bundleDetails = new TreeMap<>();
      AtomicReference<Resource> resourceReference = new AtomicReference<>();
      bundles = repositoryAnalyzer.getBundles(
        bundleSizes,
        bundleDetails,
        repositoryAnalyzer.buildAdditionalDetails(resourceReference),
        updateSiteGenerator.getIUFilterPattern(),
        updateSiteGenerator.isBREE());
      resource = resourceReference.get();
    }
    return bundles;
  }

  /**
   * Returns a shorter name for common bundle symbolic name prefixes.
   * @param bsn the bundle symbolic name.
   * @return a shorter name for common bundle symbolic name prefixes.
   */
  public String getShortBSN(String bsn)
  {
    Matcher matcher = BSN_PATTERN.matcher(bsn);
    if (matcher.matches())
    {
      return "<span class='bsn-prefix'>" + matcher.group(1) + "</span>" + matcher.group(2);
    }
    return bsn;
  }

  /**
   * Returns the products of this site.
   * @return the products of this site.
   */
  public List<String> getProducts()
  {
    return repositoryAnalyzer.getProducts();
  }

  /**
   * Returns the URL for downloading the given product.
   * @param product the product for which a download URL is needed.
   * @return the URL for downloading the given product.
   */
  public String getProductDownloadURI(String product)
  {
    return updateSiteGenerator.getDownloadURL(folder.resolve(org.eclipse.emf.common.util.URI.decode(product)));
  }

  /**
   * Returns the downloads of this site.
   * @return the downloads of this site.
   */
  public List<String> getDownloads()
  {
    return getLatestUpdateSiteGenerator().repositoryAnalyzer.getDownloads();
  }

  /**
   * Returns the URL for downloading the given download artifact.
   * @param downloadArtifact the artifact for which a download URL is needed.
   * @return the URL for downloading the given artifact.
   */
  public String getArtifactDownloadURI(String downloadArtifact)
  {
    UpdateSiteIndexGenerator latestUpdateSiteGenerator = getLatestUpdateSiteGenerator();
    return updateSiteGenerator.getDownloadURL(latestUpdateSiteGenerator.folder.resolve("downloads/" + org.eclipse.emf.common.util.URI.decode(downloadArtifact)));
  }

  /**
   * Returns the URL for downloading the SHA512 for given download artifact.
   * @param downloadArtifact the artifact for which a SHA512 download URL is needed.
   * @return the URL for downloading the SHA512 of given artifact.
   */
  public String getArtifactSHA512URI(String downloadArtifact)
  {
    UpdateSiteIndexGenerator latestUpdateSiteGenerator = getLatestUpdateSiteGenerator();
    return folder.relativize(latestUpdateSiteGenerator.folder.resolve("downloads/" + org.eclipse.emf.common.util.URI.decode(downloadArtifact) + ".sha512")).toString();
  }

  public String getBundleSize(String bundle)
  {
    getBundles();
    Long bundleSize = bundleSizes.get(bundle);
    if (bundleSize == null)
    {
      return "";
    }
    else
    {
      float size = ((float)bundleSize) / 1024;
      if (size > 1024 * 5)
      {
        return String.format(java.util.Locale.US, "%,.1f", size / 1024) + "MB";
      }
      else
      {
        return String.format(java.util.Locale.US, "%,.1f", size) + "KB";
      }
    }
  }

  public List<Property> getProperties(String bundle)
  {
    getBundles();
    Map<String, String> details = bundleDetails.get(bundle);
    return Property.create(details);
  }

  public String getFolderID(String folder)
  {
    return "_" + new File(folder.replaceAll("<sup>.*</sup>$", "")).getName().replace('.', '_').replace(' ', '_');
  }

  /**
   * Returns the ordered index generators for the children of this folder.
   * @return the ordered index generators for the children of this folder.
   */
  public List<UpdateSiteIndexGenerator> getChildren()
  {
    if (children == null)
    {
      List<Path> directChildren = new ArrayList<>();
      List<Path> superChildren = new ArrayList<>();
      children = new ArrayList<UpdateSiteIndexGenerator>();
      try
      {
        for (Path child : Files.list(folder).collect(Collectors.toList()))
        {
          if (Files.isDirectory(child) && !Files.isRegularFile(child.resolve("DELETED"))
            && (Files.isRegularFile(child.resolve("compositeContent.jar")) || Files.isRegularFile(child.resolve("content.jar"))))
          {
            directChildren.add(child);
          }
        }

        if ("super".equals(getBuildType()) && directChildren.size() == 1 && directChildren.get(0).endsWith("latest"))
        {
          superChildren.addAll(repositoryAnalyzer.getChildren());
        }
      }
      catch (IOException exception)
      {
        throw new RuntimeException(exception);
      }

      UpdateSiteGenerator.sort(directChildren);

      for (Path child : directChildren)
      {
        children.add(new UpdateSiteIndexGenerator(child, updateSiteGenerator, this));
      }

      Path parentFolder = folder;
      for (Path child : superChildren)
      {
        children.add(new UpdateSiteIndexGenerator(child, updateSiteGenerator, this)
          {
            @Override
            public String getIndexName()
            {
              return "index_super.html";
            }

            @Override
            public String getRelativeIndexURL()
            {
              Path relativePath = parentFolder.relativize(child);
              return relativePath.resolve(getIndexName()).toString().replace('\\', '/');
            }
          });
      }
    }

    return children;
  }

  /**
   * Return the children of a super composite.
   * @return the children of a super composite.
   */
  public List<UpdateSiteIndexGenerator> getSuperCompositeChildren()
  {
    return Collections.emptyList();
  }

  /**
   * Returns a map from project name to the URL for the commit ID URL in that project's branding plugin.
   * @return a map from project name to the URL for the commit ID URL in that project's branding plugin.
   */
  public Map<String, String> getCommits()
  {
    return getLatestUpdateSiteGenerator().repositoryAnalyzer.getCommits(updateSiteGenerator.getCommitMappings());
  }

  /**
   * Extracts the URL for listings all the repository's commits.
   * @param url the base URL for a single commit.
   * @return the URL for listings all the repository's commits.
   */
  public String getCommitsURL(String url)
  {
    if (url.contains("git.eclipse.org"))
    {
      return url.substring(0, url.indexOf("commit")) + "log/";
    }
    else
    {
      return url.substring(0, url.lastIndexOf("/")) + "s";
    }
  }

  /**
   * Extracts the commit ID.
   * @param url the base URL for a single commit.
   * @return the commit ID.
   */
  public String getCommitID(String url)
  {
    if (url.contains("git.eclipse.org"))
    {
      return url.substring(url.indexOf('=') + 1);
    }
    else
    {
      return url.substring(url.lastIndexOf('/') + 1);
    }
  }

  /**
   * Returns the date string for when the repository was created.
   * @return the date string for when the repository was created.
   */
  public String getDate()
  {
    return repositoryAnalyzer.getDate();
  }

  /**
   * Returns the title for the table summary.
   * @return the title for the table summary.
   */
  public String getTableTitle()
  {
    return updateSiteGenerator.getProjectLabel() + " Summary";
  }

  /**
   * Returns the name of the update site as displayed in the table summary column for this site.
   * @return the name of the update site as displayed in the table summary column for this site.
   */
  public String getTableChildFolderName()
  {
    return folder.getParent().getFileName() + "/" + getFolderName();
  }

  /**
   * Returns decorations to indicate what types of updates are available in this table column.
   * @return decorations to indicate what types of updates are available in this table column.
   */
  public String getTableChildFolderDecoration(UpdateSiteIndexGenerator tableParent)
  {
    List<UpdateSiteIndexGenerator> tableChildren = tableParent.getTableChildren();
    int index = tableChildren.indexOf(this);
    if (index + 1 == tableChildren.size())
    {
      return "";
    }

    boolean added = false;
    boolean updated = false;
    boolean removed = false;

    LOOP: //
    for (String bsn : tableParent.getTableBundles())
    {
      Map<String, Set<IInstallableUnit>> bsns = tableParent.table.get(this);
      if (bsns != null)
      {
        Set<IInstallableUnit> olderVersions;
        Map<String, Set<IInstallableUnit>> olderBSNs = tableParent.table.get(tableChildren.get(index + 1));
        if (olderBSNs != null)
        {
          olderVersions = olderBSNs.get(bsn);
        }
        else
        {
          olderVersions = null;
        }

        Set<IInstallableUnit> versions = bsns.get(bsn);

        if (!Objects.equals(versions, olderVersions))
        {
          if (olderVersions == null)
          {
            added = true;
          }
          else if (versions == null)
          {
            removed = true;
          }
          else
          {
            updated = true;
          }
        }

        if (removed && updated)
        {
          break LOOP;
        }
      }
    }

    if (updated || removed || added)
    {
      StringBuilder result = new StringBuilder();
      result.append("&nbsp;");
      if (added)
      {
        result.append("<b>" + ADDED_DECORATOR + "</b>");
      }
      if (updated)
      {
        result.append("<b>" + UPDATED_DECORATOR + "</b>");
      }
      if (removed)
      {
        result.append("<b>" + REMOVED_DECORATOR + "</b>");
      }
      return result.toString();
    }

    return "";
  }

  /**
   * Returns the sites for which a summary column is generated in the table summary.
   * @return the sites for which a summary column is generated in the table summary.
   */
  public List<UpdateSiteIndexGenerator> getTableChildren()
  {
    if (table == null)
    {
      table = new LinkedHashMap<UpdateSiteIndexGenerator, Map<String, Set<IInstallableUnit>>>();

      if (hasSummary())
      {
        int summary = updateSiteGenerator.getSummary();
        Pattern summaryIUPattern = updateSiteGenerator.getSummaryIUPattern();
        Consumer<? super UpdateSiteIndexGenerator> accumulate = it ->
          {
            if (table.size() < summary)
            {
              table.put(it, it.repositoryAnalyzer.getBundles(summaryIUPattern));
            }
          };
        List<UpdateSiteIndexGenerator> children = getChildren();
        Collections.reverse(children);
        for (UpdateSiteIndexGenerator child : children)
        {
          String folderName = child.getFolderName();
          if ("release".equals(folderName))
          {
            child.getChildren().stream().filter(it -> !it.isLatest()).forEach(accumulate);
          }
          else if ("milestone".equals(folderName) || "nightly".equals(folderName))
          {
            child.getChildren().stream().filter(it -> it.isLatest()).forEach(accumulate);
          }
        }
      }
    }

    return new ArrayList<UpdateSiteIndexGenerator>(table.keySet());
  }

  /**
   * The sorted set of bundle symbolic names to display in the summary table.
   * @return the sorted set of bundle symbolic names to display in the summary table.
   */
  public Set<String> getTableBundles()
  {
    getTableChildren();
    return table.values().stream().map(Map::keySet).flatMap(Set::stream).collect(Collectors.toCollection(() -> new TreeSet<>(Collator.getInstance())));
  }

  /**
   * Returns information about the version information to show in each cell of the summary table.
   * @param bsn the bundle symbolic name, i.e., row of the table.
   * @param child the child site, i.e, the column of the table.
   * @return information about the version information to show in each cell of the summary table.
   */
  public String getVersions(String bsn, UpdateSiteIndexGenerator child)
  {
    List<UpdateSiteIndexGenerator> tableChildren = getTableChildren();
    Map<String, Set<IInstallableUnit>> bsns = table.get(child);
    if (bsns != null)
    {
      Set<IInstallableUnit> olderVersions;
      int index = tableChildren.indexOf(child);
      if (index + 1 < tableChildren.size())
      {
        Map<String, Set<IInstallableUnit>> olderBSNs = table.get(tableChildren.get(index + 1));
        if (olderBSNs != null)
        {
          olderVersions = olderBSNs.getOrDefault(bsn, Set.of());
        }
        else
        {
          olderVersions = Set.of();
        }
      }
      else
      {
        olderVersions = null;
      }

      Set<IInstallableUnit> versions = bsns.get(bsn);
      if (versions == null)
      {
        if (olderVersions != null && !olderVersions.isEmpty())
        {
          return "<b>" + REMOVED_DECORATOR + "</b>";
        }
      }
      else
      {
        return versions.stream().map(it ->
          {
            Version version = it.getVersion();
            StringBuilder result = new StringBuilder();
            for (int i = 0, limit = version.getSegmentCount() - 1; i <= limit; ++i)
            {
              String segment = version.getSegment(i).toString();
              if (segment.isBlank())
              {
                break;
              }

              if (i != 0)
              {
                result.append('.');
              }

              if (i == limit)
              {
                result.append("<span class='qualifier'>").append(segment).append("</span>");
              }
              else if (segment.length() >= 8)
              {
                result.append("<span class='long-segment'>").append(segment).append("</span>");
              }
              else
              {
                result.append(segment);
              }
            }

            result.append(UpdateSiteGenerator.getBREEText(updateSiteGenerator.isBREE(), it));

            if (olderVersions == null)
            {
              // No next column.
            }
            else if (olderVersions.isEmpty())
            {
              result.append("&nbsp;<b>" + ADDED_DECORATOR + "</b>");
            }
            else if (!olderVersions.contains(it))
            {
              result.append("&nbsp;<b>" + UPDATED_DECORATOR + "</b>");
            }

            MavenDescriptor descriptor = UpdateSiteGenerator.MavenDescriptor.create(it);
            if (descriptor != null)
            {
              return "<a href='" + descriptor.toURL() + "' target='maven-central'>" + result + "</a>";
            }

            return result.toString();
          }).collect(Collectors.joining("<br/>"));
      }
    }
    return "";
  }

  /**
   * Whether any bundle in the table summary has a maven descriptor.
   * @return whether any bundle in the table summary has a maven descriptor.
   */
  public boolean hasMavenDescriptors()
  {
    getTableBundles();
    return table.values().stream().map(Map::values).flatMap(Collection::stream).flatMap(Set::stream).anyMatch(it -> MavenDescriptor.create(it) != null);
  }

  @Override
  public String toString()
  {
    return folder.toString();
  }
}
