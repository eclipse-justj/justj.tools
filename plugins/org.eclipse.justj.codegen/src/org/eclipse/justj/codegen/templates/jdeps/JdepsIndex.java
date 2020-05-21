/*
 * Copyright (c) 2020 Eclipse contributors and others.
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
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
package org.eclipse.justj.codegen.templates.jdeps;


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
  protected static String nl;
  public static synchronized JdepsIndex create(String lineSeparator)
  {
    nl = lineSeparator;
    JdepsIndex result = new JdepsIndex();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? ("\n") : nl;
  protected static final String _1 = " ";
  protected static final String _2 = " <span style=\"color: DarkOliveGreen; font-size: 90%;\">";
  protected static final String _3 = "', 'mod-deps');\"/>";
  protected static final String _4 = "', 'plugin-deps');\"/>";
  protected static final String _5 = ".plain:link, .plain:visited {";
  protected static final String _6 = ".toggle + .toggle-label + .toggle-content {";
  protected static final String _7 = ".toggle + .toggle-label > .toggle-state-off {";
  protected static final String _8 = ".toggle + .toggle-label > .toggle-state-on {";
  protected static final String _9 = ".toggle {";
  protected static final String _10 = ".toggle-content {";
  protected static final String _11 = ".toggle-label {";
  protected static final String _12 = ".toggle-label:hover, .toggle-label:hover * {";
  protected static final String _13 = ".toggle:checked + .toggle-label + .toggle-content {";
  protected static final String _14 = ".toggle:checked + .toggle-label > .toggle-state-off {";
  protected static final String _15 = ".toggle:checked + .toggle-label > .toggle-state-on {";
  protected static final String _16 = ": <a style=\"font-size: 66%;\" href=\"";
  protected static final String _17 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
  protected static final String _18 = "</a>";
  protected static final String _19 = "</a></h2>";
  protected static final String _20 = "</a></li>";
  protected static final String _21 = "</body>";
  protected static final String _22 = "</div>";
  protected static final String _23 = "</h4>";
  protected static final String _24 = "</head>";
  protected static final String _25 = "</header>";
  protected static final String _26 = "</html>";
  protected static final String _27 = "</label>";
  protected static final String _28 = "</li>";
  protected static final String _29 = "</main>";
  protected static final String _30 = "</ol>";
  protected static final String _31 = "</script>";
  protected static final String _32 = "</section>";
  protected static final String _33 = "</span></a>";
  protected static final String _34 = "</style>";
  protected static final String _35 = "</title>";
  protected static final String _36 = "</ul>";
  protected static final String _37 = "<a href=\"#";
  protected static final String _38 = "<a href=\"https://www.eclipse.org/justj?page=index\">";
  protected static final String _39 = "<body id=\"body_solstice\">";
  protected static final String _40 = "<br/>";
  protected static final String _41 = "<div class=\"col-sm-16 padding-left-30\">";
  protected static final String _42 = "<div class=\"col-sm-8 margin-top-15\"></div>";
  protected static final String _43 = "<div class=\"container\">";
  protected static final String _44 = "<div class=\"hidden-xs col-sm-8 col-md-6 col-lg-5\" id=\"header-left\">";
  protected static final String _45 = "<div class=\"novaContent container\" id=\"novaContent\">";
  protected static final String _46 = "<div class=\"row\" id=\"header-row\">";
  protected static final String _47 = "<div class=\"row\">";
  protected static final String _48 = "<div class=\"wrapper-logo-default\">";
  protected static final String _49 = "<div id=\"maincontent\">";
  protected static final String _50 = "<div id=\"mod-deps\">";
  protected static final String _51 = "<div id=\"plugin-deps\">";
  protected static final String _52 = "<h2 style=\"\">";
  protected static final String _53 = "<h3 class=\"sr-only\">Breadcrumbs</h3>";
  protected static final String _54 = "<h4>";
  protected static final String _55 = "<head>";
  protected static final String _56 = "<header role=\"banner\" id=\"header-wrapper\">";
  protected static final String _57 = "<hr/>";
  protected static final String _58 = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">";
  protected static final String _59 = "<img class=\"logo-eclipse-default img-responsive hidden-xs\" style=\"height: 50px; space: nowrap; display: inline-block;\" alt=\"Project Logo\" src=\"https://www.eclipse.org/justj/justj_title.svg\"/>";
  protected static final String _60 = "<input type=\"checkbox\" id=\"toggle-id-";
  protected static final String _61 = "<label for=\"toggle-id-";
  protected static final String _62 = "<li class=\"active\">";
  protected static final String _63 = "<li style=\"color: FireBrick; font-size: 80%;\">";
  protected static final String _64 = "<li>";
  protected static final String _65 = "<li><a href=\"";
  protected static final String _66 = "<li><a href=\"https://download.eclipse.org/jdeps\">Jdeps</a></li>";
  protected static final String _67 = "<li><a href=\"https://www.eclipse.org/\">Home</a></li>";
  protected static final String _68 = "<li><a href=\"https://www.eclipse.org/justj?page=download\">JustJ</a></li>";
  protected static final String _69 = "<li><a href=\"https://www.eclipse.org/projects/\">Projects</a></li>";
  protected static final String _70 = "<link href=\"//fonts.googleapis.com/css?family=Open+Sans:400,700,300,600,100\" rel=\"stylesheet\" type=\"text/css\"/>";
  protected static final String _71 = "<link rel=\"icon\" type=\"image/ico\" href=\"https://www.eclipse.org/justj/justj_favicon.ico\"/>";
  protected static final String _72 = "<link rel=\"stylesheet\" href=\"https://www.eclipse.org/eclipse.org-common/themes/solstice/public/stylesheets/styles.min.css\"/>";
  protected static final String _73 = "<main class=\"no-promo\">";
  protected static final String _74 = "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>";
  protected static final String _75 = "<meta name=\"description\" content=\"JustJ's JDeps Report\"/>";
  protected static final String _76 = "<meta name=\"keywords\" content=\"eclipse,justj\"/>";
  protected static final String _77 = "<ol class=\"breadcrumb\">";
  protected static final String _78 = "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>";
  protected static final String _79 = "<script>";
  protected static final String _80 = "<section class=\"hidden-print default-breadcrumbs\" id=\"breadcrumb\">";
  protected static final String _81 = "<span style=\"color: DarkOliveGreen; font-size: 90%;\">";
  protected static final String _82 = "<style>";
  protected static final String _83 = "<title>";
  protected static final String _84 = "<ul class=\"toggle-content\" style=\"margin-left: 1em; margin-bottom: 0;\">";
  protected static final String _85 = "Module Dependencies";
  protected static final String _86 = "Plugin Dependencies";
  protected static final String _87 = "The plugin dependencies of each module and the module dependencies of each plugin are summarized below.";
  protected static final String _88 = "The plugins of this repository have been analyzed for module dependencies using jdeps.";
  protected static final String _89 = "\" class=\"plain\">";
  protected static final String _90 = "\" class=\"toggle-label\" id=\"";
  protected static final String _91 = "\" class=\"toggle-label\">";
  protected static final String _92 = "\" class=\"toggle\" onclick=\"expand_collapse('toggle-id-";
  protected static final String _93 = "\" class=\"toggle\"/>";
  protected static final String _94 = "\">";
  protected static final String _95 = "border: 1px solid black;";
  protected static final String _96 = "color: inherit;";
  protected static final String _97 = "cursor: pointer;";
  protected static final String _98 = "display: inherit;";
  protected static final String _99 = "display: none;";
  protected static final String _100 = "font-size: 100%;";
  protected static final String _101 = "font-weight: normal;";
  protected static final String _102 = "for (var i = 0; i < targets.length; i++) {";
  protected static final String _103 = "function expand_collapse(id, targetId) {";
  protected static final String _104 = "input.checked = checked;";
  protected static final String _105 = "label:target {";
  protected static final String _106 = "margin-bottom: 0px;";
  protected static final String _107 = "max-height: 0;";
  protected static final String _108 = "max-height: none;";
  protected static final String _109 = "opacity: 0;";
  protected static final String _110 = "opacity: 1;";
  protected static final String _111 = "overflow: hidden;";
  protected static final String _112 = "padding-left: 2em;";
  protected static final String _113 = "transition: all .4s ease-in-out;";
  protected static final String _114 = "var checked = e.checked;";
  protected static final String _115 = "var e = document.getElementById(id);";
  protected static final String _116 = "var input = targets[i];";
  protected static final String _117 = "var target = document.getElementById(targetId);";
  protected static final String _118 = "var targets = target.getElementsByTagName('input');";
  protected static final String _119 = "}";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "        ";
  protected final String NL_5 = NL + "         ";
  protected final String NL_6 = NL + "          ";
  protected final String NL_7 = NL + "           ";
  protected final String NL_8 = NL + "            ";
  protected final String NL_9 = NL + "              ";
  protected final String NL_10 = NL + "                ";
  protected final String _120 = _17 + NL + _58 + NL + _55 + NL_1 + _74 + NL_1 + _83;
  protected final String _121 = _35 + NL_1 + _76 + NL_1 + _75 + NL_1 + _70 + NL_1 + _72 + NL_1 + _71 + NL_1 + _78 + NL_1 + _79 + NL_3 + _103 + NL_4 + _115 + NL_4 + _114 + NL_4 + _117 + NL_4 + _118 + NL_4 + _102 + NL_6 + _116 + NL_6 + _104 + NL_4 + _119 + NL_3 + _119 + NL_1 + _31 + NL + _24 + NL + NL_1 + _39 + NL_2 + _82 + NL + _9 + NL_1 + _99 + NL + _119 + NL + NL + _11 + NL_1 + _100 + NL_1 + _106 + NL_1 + _101 + NL + _119 + NL + NL + _6 + NL_1 + _107 + NL_1 + _109 + NL_1 + _111 + NL_1 + _113 + NL + _119 + NL + NL + _8 + NL_1 + _99 + NL + _119 + NL + NL + _7 + NL_1 + _98 + NL + _119 + NL + NL + _15 + NL_1 + _98 + NL + _119 + NL + NL + _14 + NL_1 + _99 + NL + _119 + NL + NL + _13 + NL_1 + _108 + NL_1 + _110 + NL + _119 + NL + NL + _10 + NL_1 + _112 + NL + _119 + NL + NL + _12 + NL_1 + _97 + NL + _119 + NL + NL + _5 + NL_1 + _96 + NL + _119 + NL + NL + _105 + NL_1 + _95 + NL + _119 + NL_2 + _34 + NL + NL_2 + _56 + NL_3 + _43 + NL_4 + _46 + NL_6 + _44 + NL_8 + _48 + NL_9 + _38 + NL_10 + _59 + NL_9 + _18 + NL_8 + _22 + NL_6 + _22 + NL_4 + _22 + NL_3 + _22 + NL_2 + _25 + NL + NL_2 + _80 + NL_3 + _43 + NL_4 + _53 + NL_4 + _47 + NL_6 + _41 + NL_8 + _77 + NL_9 + _67 + NL_9 + _69 + NL_9 + _68 + NL_9 + _66;
  protected final String _122 = NL_9 + _62;
  protected final String _123 = NL_9 + _64;
  protected final String _124 = NL_9 + _65;
  protected final String _125 = NL_8 + _30 + NL_6 + _22 + NL_6 + _42 + NL_4 + _22 + NL_3 + _22 + NL_2 + _32 + NL + NL_2 + _73 + NL_2 + _45 + NL + NL_3 + _49 + NL_4 + _52;
  protected final String _126 = _19 + NL_3 + _88 + NL_3 + _87 + NL + NL_2 + _57 + NL_2 + _54 + NL_3 + _85 + NL_3 + _60;
  protected final String _127 = _3 + NL_3 + _61;
  protected final String _128 = _27 + NL_2 + _23 + NL + NL_2 + _50;
  protected final String _129 = NL_5 + _60;
  protected final String _130 = _93 + NL_5 + _61;
  protected final String _131 = _27 + NL_5 + _84;
  protected final String _132 = NL_7 + _64 + NL_7 + _37;
  protected final String _133 = _33 + NL_7 + _28;
  protected final String _134 = NL_5 + _36;
  protected final String _135 = NL_2 + _22 + NL + NL_2 + _57 + NL_2 + _54 + NL_3 + _86 + NL_3 + _60;
  protected final String _136 = _4 + NL_3 + _61;
  protected final String _137 = _27 + NL_2 + _23 + NL + NL_2 + _51;
  protected final String _138 = NL_7 + _81;
  protected final String _139 = _18 + NL_7 + _28;
  protected final String _140 = NL_7 + _63;
  protected final String _141 = NL_7 + _28;
  protected final String _142 = NL_2 + _22 + NL + NL_3 + _22 + NL_2 + _22 + NL_2 + _29 + NL_1 + _21 + NL + _26;

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
    final StringBuilder builder = new StringBuilder();
    String title = "JustJ Jdeps";
  int toggleCounter = 0;
    builder.append(_120);
    builder.append(title);
    builder.append(_121);
    for (Map.Entry<String, String> entry : getBreadcrumbs().entrySet()) {
    if (entry.getValue() == null) {
    builder.append(_122);
    builder.append(entry.getKey());
    builder.append(_28);
    } else if (entry.getValue().trim().isEmpty()) {
    builder.append(_123);
    builder.append(entry.getKey());
    builder.append(_28);
    } else {
    builder.append(_124);
    builder.append(entry.getValue());
    builder.append(_94);
    builder.append(entry.getKey());
    builder.append(_20);
    }
    }
    builder.append(_125);
    builder.append(title);
    builder.append(_16);
    builder.append(repoURI);
    builder.append(_94);
    builder.append(repoURI);
    builder.append(_126);
    builder.append(++toggleCounter);
    builder.append(_92);
    builder.append(toggleCounter);
    builder.append(_127);
    builder.append(toggleCounter);
    builder.append(_91);
    builder.append(getToggleExpand(true));
    builder.append(_128);
    for (Map.Entry<String, Set<Plugin>> entry : modulePlugins.entrySet()) {
    int mainID = ++toggleCounter;
    builder.append(_129);
    builder.append(mainID);
    builder.append(_130);
    builder.append(mainID);
    builder.append(_90);
    builder.append(entry.getKey());
    builder.append(_94);
    builder.append(getToggleExpand(true));
    builder.append(_1);
    builder.append(entry.getKey());
    builder.append(_131);
    for (Plugin plugin : entry.getValue()) {
    builder.append(_132);
    builder.append(plugin.getAnchor());
    builder.append(_89);
    builder.append(plugin.getId());
    builder.append(_2);
    builder.append(plugin.getVersion());
    builder.append(_133);
    }
    builder.append(_134);
    }
    builder.append(_135);
    builder.append(++toggleCounter);
    builder.append(_92);
    builder.append(toggleCounter);
    builder.append(_136);
    builder.append(toggleCounter);
    builder.append(_91);
    builder.append(getToggleExpand(true));
    builder.append(_137);
    for (Map.Entry<Plugin, Set<String>> entry : pluginModules.entrySet()) {
    Plugin plugin = entry.getKey();
    Set<String> modules = entry.getValue();
    List<String> errors = pluginErrors.get(plugin);
    int mainID = ++toggleCounter;
    builder.append(_129);
    builder.append(mainID);
    builder.append(_130);
    builder.append(mainID);
    builder.append(_90);
    builder.append(plugin.getAnchor());
    builder.append(_94);
    builder.append(NL_7);
    builder.append(getToggleExpand(!modules.isEmpty() || errors != null));
    builder.append(NL_7);
    builder.append(plugin.getId());
    builder.append(_138);
    builder.append(plugin.getVersion());
    builder.append(_131);
    for (String module : modules) {
    builder.append(_132);
    builder.append(module);
    builder.append(_89);
    builder.append(module);
    builder.append(_139);
    }
    if (errors != null) {
    builder.append(_140);
    for (String error : errors) {
    builder.append(NL_7);
    builder.append(error);
    builder.append(_40);
    }
    builder.append(_141);
    }
    builder.append(_134);
    }
    builder.append(_142);
    return builder.toString();
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
