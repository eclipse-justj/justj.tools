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


import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class JdepsIndex
{
  private final URI repoURI;

  private final Map<String, Set<Plugin>> modulePlugins = new TreeMap<String, Set<Plugin>>();

  private final Map<Plugin, Set<String>> pluginModules = new TreeMap<>();

  private final Map<Plugin, List<String>> pluginErrors = new TreeMap<>();

  public static void main(String[] args)
  {
    try
    {
      Path folder = Paths.get(args[0]);
      URI repoURI = new URI(args[1]);
      JdepsIndex jdepsIndex = new JdepsIndex(folder, repoURI);
      String result = jdepsIndex.generate();
      Files.write(folder.resolve("index.html"), Collections.singleton(result));
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

  public JdepsIndex(Path folder, URI repoURI) throws Exception
  {
    this.repoURI = repoURI;

    for (Path path : Files.list(folder).collect(Collectors.toList()))
    {
      String fileName = path.getFileName().toString();
      if (fileName.endsWith("out-deps") || fileName.endsWith("err-deps"))
      {
        int versionSeparatorIndex = fileName.lastIndexOf('_');
        int jarStartIndex = fileName.lastIndexOf(".jar");

        String id = fileName.substring(0, versionSeparatorIndex);
        String version = fileName.substring(versionSeparatorIndex + 1, jarStartIndex);
        Plugin plugin = new Plugin(id, version, fileName);
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
                modules.add(module);
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
    for (Path foo = path; foo != null; foo = foo.getParent())
    {
      Path fileName = foo.getFileName();
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

  public static class JRE
  {

  }

  public String generate()
  {
    return null;
  }

  public class Plugin implements Comparable<Plugin>
  {
    private String version;

    private String id;

    private String anchor;

    public Plugin(String id, String version, String anchor)
    {
      this.id = id;
      this.version = version;
      this.anchor = anchor;
    }

    public String getId()
    {
      return id;
    }

    public String getVersion()
    {
      return version;
    }

    public String getAnchor()
    {
      return anchor;
    }

    @Override
    public int compareTo(Plugin other)
    {
      int result = id.compareTo(other.id);
      return result == 0 ? version.compareTo(other.version) : result;
    }
  }
}
