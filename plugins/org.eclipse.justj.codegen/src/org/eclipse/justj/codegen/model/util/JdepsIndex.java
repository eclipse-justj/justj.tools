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
package org.eclipse.justj.codegen.model.util;


import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class JdepsIndex
{
  private static Pattern MODULE_NAME_PATTERN = Pattern.compile("\\p{Alpha}\\p{Alnum}*(\\.\\p{Alpha}\\p{Alnum}*)*");

  private static Pattern SPLIT_PACKAGE_WARNING_PATTERN = Pattern.compile("Warning: split package: ([^ ]+) jrt:/([^ ]+) ([^ ]+)");

  private final URI repoURI;

  private final Map<String, Set<Plugin>> modulePlugins = new TreeMap<String, Set<Plugin>>();

  private final Map<Plugin, Set<String>> pluginModules = new TreeMap<>();

  private final Map<Plugin, List<String>> pluginErrors = new TreeMap<>();

  private final Map<ModulePluginPair, Set<String>> splitPackages = new HashMap<>();

  private final Map<Path, URI> indices = new TreeMap<>();

  private final Map<Path, Set<String>> modules = new TreeMap<>();

  public static void main(String[] args)
  {
    try
    {
      String arg0 = args[0];
      if ("-index".equals(arg0))
      {
        Path folder = Paths.get(args[1]);
        JdepsIndex jdepsIndex = new JdepsIndex(folder);
        String result = jdepsIndex.generate();
        Path index = folder.resolve("index.html");
        System.out.println("Generated index: " + index);
        Files.write(index, Collections.singleton(result));
        Set<String> allModules = new TreeSet<String>();
        for (Set<String> modules : jdepsIndex.modules.values())
        {
          allModules.addAll(modules);
        }
        Files.write(folder.resolve("justj.modules"), allModules);
      }
      else
      {
        Path folder = Paths.get(arg0);
        URI repoURI = new URI(args[1]);
        Path jdepsFolder = folder;

        // This is intended purely for local testing of the results produced by
        // https://ci.eclipse.org/justj/job/build-jdeps/lastSuccessfulBuild/artifact/*zip*/archive.zip
        if (args.length > 2)
        {
          URL url = new URL(args[2]);
          String path = repoURI.getPath();
          String relativePath = path.replaceAll("^/", "");
          Path target = Files.createDirectories(folder.resolve(relativePath));
          Path tempDirectory = Files.createTempDirectory("jdeps");

          try (InputStream input = url.openStream(); ZipInputStream zipInputStream = new ZipInputStream(input))
          {
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry())
            {
              String name = zipEntry.getName();
              Files.copy(zipInputStream, tempDirectory.resolve(Paths.get(name).getFileName()));
            }
          }

          folder = target;
          jdepsFolder = tempDirectory;
        }

        JdepsIndex jdepsIndex = new JdepsIndex(jdepsFolder, repoURI);
        String result = jdepsIndex.generate();
        Path index = folder.resolve("index.html");
        System.out.println("Generated index: " + index);
        Files.write(index, Collections.singleton(result));
        Files.write(folder.resolve("justj.modules"), jdepsIndex.modulePlugins.keySet());
      }
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
      System.exit(1);
    }
  }

  public JdepsIndex()
  {
    throw new UnsupportedOperationException("Cannot create without arguments");
  }

  public JdepsIndex(Path folder) throws Exception
  {
    repoURI = new URI("https://download.eclipse.org/");
    for (Path path : Files.list(folder).collect(Collectors.toList()))
    {
      visit(folder, path);
    }

    for (Path path : indices.keySet())
    {
      Path justjModules = folder.resolve(path).getParent().resolve("justj.modules");
      Set<String> repoModules = new TreeSet<>(Files.readAllLines(justjModules));
      modules.put(path, repoModules);
    }
  }

  public JdepsIndex(Path folder, URI repoURI) throws Exception
  {
    this.repoURI = repoURI;

    for (Path path : Files.list(folder).collect(Collectors.toList()))
    {
      String fileName = path.getFileName().toString();
      if (fileName.endsWith("out-deps") || fileName.endsWith("error-deps"))
      {
        int versionSeparatorIndex = fileName.lastIndexOf('_');
        int jarStartIndex = fileName.lastIndexOf(".jar");

        String id = fileName.substring(0, versionSeparatorIndex);
        String version = fileName.substring(versionSeparatorIndex + 1, jarStartIndex);
        Plugin plugin = new Plugin(id, version);
        List<String> lines = Files.readAllLines(path);

        if (fileName.endsWith("out-deps"))
        {
          Set<String> modules = new TreeSet<>();
          for (String line : lines)
          {
            for (String module : line.split(","))
            {
              if (!module.isEmpty())
              {
                if (MODULE_NAME_PATTERN.matcher(module).matches())
                {
                  modules.add(module);
                }
                else
                {
                  Matcher matcher = SPLIT_PACKAGE_WARNING_PATTERN.matcher(module);
                  if (matcher.matches())
                  {
                    String packageName = matcher.group(1);
                    String moduleName = matcher.group(2);
                    ModulePluginPair modulePluginPair = new ModulePluginPair(moduleName, plugin);
                    Set<String> packages = splitPackages.get(modulePluginPair);
                    if (packages == null)
                    {
                      packages = new TreeSet<>();
                      splitPackages.put(modulePluginPair, packages);
                    }
                    packages.add(packageName);
                  }
                  else
                  {
                    System.err.println("Unrecongnized jdeps output '" + module + "'");
                  }
                }
              }
            }
          }
          pluginModules.put(plugin, modules);

          for (String module : modules)
          {
            Set<Plugin> plugins = modulePlugins.get(module);
            if (plugins == null)
            {
              plugins = new TreeSet<>();
              modulePlugins.put(module, plugins);
            }
            plugins.add(plugin);
          }
        }
        else
        {
          pluginErrors.put(plugin, lines);
          if (!pluginModules.containsKey(plugin))
          {
            pluginModules.put(plugin, Collections.emptySet());
          }
        }
      }
    }
  }

  public Set<String> getModules(Path path)
  {
    return modules.get(path);
  }

  protected void visit(Path root, Path path) throws Exception
  {
    if (Files.isDirectory(path))
    {
      for (Path child : Files.list(path).collect(Collectors.toList()))
      {
        visit(root, child);
      }
    }
    else if (Files.isRegularFile(path) && path.endsWith("index.html"))
    {
      Path relativePath = root.relativize(path);
      Path parent = relativePath.getParent();
      if (parent != null)
      {
        indices.put(relativePath, new URI("https://download.eclipse.org/" + parent.toString().replace('\\', '/')));
      }
    }
  }

  public Set<String> getSplitPackages(String module, Plugin plugin)
  {
    Set<String> packages = splitPackages.get(new ModulePluginPair(module, plugin));
    return packages == null ? Collections.emptySet() : packages;
  }

  public String getToggleExpand(boolean visible)
  {
    String visibleCSS = visible ? "" : " visibility: hidden;";
    return "<span style=\"font-size: 100%;" + visibleCSS + "\" class=\"orange toggle-state-on\">&#x25E2;</span>" + "<span style=\"font-size: 100%;" + visibleCSS
      + "\" class=\"orange toggle-state-off\">&#x25B7;</span>";
  }

  public Map<String, Set<Plugin>> getModulePlugins()
  {
    return modulePlugins;
  }

  public Map<Plugin, Set<String>> getPluginModules()
  {
    return pluginModules;
  }

  public Map<String, String> getBreadcrumbs()
  {
    // Compute the labels in the right order continuing only as far as the project root.
    List<String> labels = new ArrayList<String>();
    Path path = Paths.get(repoURI.getPath());
    for (Path visitedPath = path; visitedPath != null; visitedPath = visitedPath.getParent())
    {
      Path fileName = visitedPath.getFileName();
      if (fileName == null)
      {
        break;
      }
      else
      {
        String name = fileName.toString();
        labels.add(0, name);
      }
    }

    // Compute the up-links in the reverse order.
    Map<String, String> links = new LinkedHashMap<String, String>();
    String link = null;
    for (int i = labels.size() - 1; i >= 0; --i)
    {
      String label = labels.get(i);
      links.put(label, link);

      if (link == null)
      {
        link = "../";
      }
      else
      {
        link = "../" + link;
      }
    }

    // Build another map in the right order.
    Map<String, String> breadcumbs = new LinkedHashMap<String, String>();
    for (String label : labels)
    {
      breadcumbs.put(label, links.get(label));
    }
    return breadcumbs;
  }

  public String getDownloadFolder()
  {
    return "";
  }

  public String generate()
  {
    return null;
  }

  public static final class ModulePluginPair
  {
    private final String module;

    private final Plugin plugin;

    public ModulePluginPair(String module, Plugin plugin)
    {
      this.module = module;
      this.plugin = plugin;
    }

    @Override
    public int hashCode()
    {
      return Objects.hash(module, plugin);
    }

    @Override
    public boolean equals(Object obj)
    {
      if (this == obj)
      {
        return true;
      }
      if (obj == null)
      {
        return false;
      }
      if (getClass() != obj.getClass())
      {
        return false;
      }
      ModulePluginPair other = (ModulePluginPair)obj;
      return Objects.equals(module, other.module) && Objects.equals(plugin, other.plugin);
    }

    @Override
    public String toString()
    {
      return module + "->" + plugin;
    }
  }

  public static final class Plugin implements Comparable<Plugin>
  {
    private String version;

    private String id;

    public Plugin(String id, String version)
    {
      this.id = id;
      this.version = version;
    }

    public String getId()
    {
      return id;
    }

    public String getVersion()
    {
      return version;
    }

    @Override
    public int compareTo(Plugin other)
    {
      int result = id.compareTo(other.id);
      return result == 0 ? version.compareTo(other.version) : result;
    }

    @Override
    public int hashCode()
    {
      return Objects.hash(id, version);
    }

    @Override
    public boolean equals(Object obj)
    {
      if (this == obj)
      {
        return true;
      }
      if (obj == null)
      {
        return false;
      }
      if (getClass() != obj.getClass())
      {
        return false;
      }
      Plugin other = (Plugin)obj;
      return Objects.equals(id, other.id) && Objects.equals(version, other.version);
    }

    @Override
    public String toString()
    {
      return id + "_" + version;
    }
  }
}
