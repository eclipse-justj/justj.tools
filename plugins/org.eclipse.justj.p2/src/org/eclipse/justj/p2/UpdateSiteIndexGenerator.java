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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.justj.codegen.model.util.Indexer.Property;
import org.eclipse.justj.p2.UpdateSiteGenerator.RepositoryAnalyzer;


/**
 * A utility class used by the {@code index.htmljet} template to generate an index.html.
 */
public class UpdateSiteIndexGenerator
{
  /**
   * The ordered folders that will be indexed.
   */
  private static final String[] ROOT_FOLDERS = new String []{ "release", "milestone", "nightly" };

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
   * An addition resource induced from the p2 metadata.
   */
  private Resource resource;

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

  /**
   * Returns whether this folder has a download archive along with associated SHA digests.
   * @return whether this folder has a download archive along with associated SHA digests.
   */
  public boolean hasArchive()
  {
    // Because we rsync, the files might not actually exist, but ones are always created for simple repositories.
    // So return true if this is a simple repository.
    return Files.isRegularFile(folder.resolve("content.jar"));
  }

  /**
   * Returns the download archive location.
   * @return the download archive location.
   */
  public String getArchive()
  {
    Path archiveFile = updateSiteGenerator.getArchiveFile(folder);
    return archiveFile.toString();
  }

  /**
   * Returns the URL to be used for loading the archive.
   * @return the URL to be used for loading the archive.
   */
  public String getArchiveDownloadURL()
  {
    Path archiveFile = updateSiteGenerator.getArchiveFile(folder);
    return updateSiteGenerator.getDownloadURL(archiveFile).toString();
  }

  /**
   * Returns the digest location for the given algorithm.
   * @param algorithm the algorithm of the digest.
   * @return the digest location for the given algorithm.
   */
  public String getDigest(String algorithm)
  {
    Path archiveFile = updateSiteGenerator.getArchiveFile(folder);
    Path digestFile = UpdateSiteGenerator.getDigestFile(archiveFile, algorithm);
    return digestFile.toString();
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
    rootLabel = Character.toUpperCase(rootLabel.charAt(0)) + rootLabel.substring(1);
    result.put(prefix.length() == 0 ? "." : prefix.toString().substring(0, prefix.length() - 1), isRoot() ? rootLabel + "@" : rootLabel);
    for (UpdateSiteIndexGenerator child : root.getChildren())
    {
      child.visit(result, prefix.toString(), 0);
    }

    URI rootSiteURI = URI.create(siteURL + "/");
    for (Map.Entry<String, String> entry : result.entrySet())
    {
      String url = entry.getKey();
      URI resolvedURI = rootSiteURI.resolve(url);
      if (resolvedURI.toString().equals(siteURL))
      {
        entry.setValue(entry.getValue() + "@");
      }
    }

    return result;
  }

  /**
   * Used to composite the {@link #getNavigation() navigation} side bar.
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
    siteURL = prefix + siteURL;

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
   * The parent-relative URL for this folder's index.
   * @return the parent-relative URL for this folder's index.
   */
  public String getRelativeIndexURL()
  {
    return folder.getFileName().resolve("index.html").toString();
  }

  /**
   * This is used to populate the bread crumbs.
   * @return the bread crumbs.
   */
  public Map<String, String> getBreadcrumbs()
  {
    Path root = updateSiteGenerator.getUpdateSiteRoot();
    Path projectRoot = updateSiteGenerator.getProjectRoot();

    // Compute the labels in the right order continuing only as far as the project root.
    List<String> labels = new ArrayList<String>();
    for (Path file = folder; file.getParent() != null; file = file.getParent())
    {
      if (file.equals(projectRoot))
      {
        break;
      }

      String name = file.getFileName().toString();
      labels.add(0, Character.toUpperCase(name.charAt(0)) + name.substring(1));
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

      if (link == null)
      {
        link = "../index.html";
      }
      else
      {
        link = "../" + link;
      }
    }

    // Build another map in the right order.
    Map<String, String> breadcumbs = new LinkedHashMap<String, String>(updateSiteGenerator.getBreadcrumbs());
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
    int versionQualifierIndex = name.indexOf(".v");
    if (versionQualifierIndex != -1)
    {
      name = name.substring(0, versionQualifierIndex) + "<span style='font-size: 80%'>" + name.substring(versionQualifierIndex) + "</span>";
    }
    return Character.toUpperCase(name.charAt(0)) + name.substring(1);
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
   * Returns the SDKs in this folder's repository.
   * @return the SDKs in this folder's repository.
   */
  public List<String> getSDKs()
  {
    if (sdks == null)
    {
      sdks = repositoryAnalyzer.getSDKs();
    }
    return sdks;
  }

  /**
   * Returns whether the given feature is an SDK.
   * @param feature the feature.
   * @return whether the given feature is an SDK.
   */
  public boolean isSDK(String feature)
  {
    List<String> sdks = getSDKs();
    Map<String, List<String>> features = getFeatures();
    if (sdks.size() != features.size())
    {
      for (String sdk : getSDKs())
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
      features = repositoryAnalyzer.getFeatures();
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
      bundles = repositoryAnalyzer.getBundles(bundleSizes, bundleDetails, repositoryAnalyzer.buildAdditionalDetails(resourceReference));
      resource = resourceReference.get();
    }
    return bundles;
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
    return "_" + new File(folder).getName().replace('.', '_');
  }

  /**
   * Returns the ordered index generators for the children of this folder.
   * @return the ordered index generators for the children of this folder.
   */
  public List<UpdateSiteIndexGenerator> getChildren()
  {
    List<Path> children = new ArrayList<>();
    List<UpdateSiteIndexGenerator> result = new ArrayList<UpdateSiteIndexGenerator>();
    try
    {
      for (Path child : Files.list(folder).collect(Collectors.toList()))
      {
        if (Files.isDirectory(child) && !Files.isRegularFile(child.resolve("DELETED"))
          && (Files.isRegularFile(child.resolve("compositeContent.jar")) || Files.isRegularFile(child.resolve("content.jar"))))
        {
          children.add(child);
        }
      }

      if ("super".equals(getBuildType()))
      {
        children.addAll(repositoryAnalyzer.getChildren());
      }
    }
    catch (IOException exception)
    {
      throw new RuntimeException(exception);
    }

    UpdateSiteGenerator.sort(children);

    for (Path child : children)
    {
      result.add(new UpdateSiteIndexGenerator(child, updateSiteGenerator, this));
    }

    return result;
  }

  /**
   * Returns a map from project name to the URL for the commit ID URL in that project's branding plugin.
   * @return a map from project name to the URL for the commit ID URL in that project's branding plugin.
   */
  public Map<String, String> getCommits()
  {
    return repositoryAnalyzer.getCommits();
  }

  /**
   * Returns the date string for when the IUs in the repository were built.
   * @return the date string for when the IUs in the repository were built.
   */
  public String getDate()
  {
    return repositoryAnalyzer.getDate();
  }
}
