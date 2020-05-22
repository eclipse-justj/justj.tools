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
  protected static final String _16 = ": ";
  protected static final String _17 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
  protected static final String _18 = "</a>";
  protected static final String _19 = "</a></li>";
  protected static final String _20 = "</body>";
  protected static final String _21 = "</div>";
  protected static final String _22 = "</h2>";
  protected static final String _23 = "</h4>";
  protected static final String _24 = "</head>";
  protected static final String _25 = "</header>";
  protected static final String _26 = "</html>";
  protected static final String _27 = "</label>";
  protected static final String _28 = "</li>";
  protected static final String _29 = "</main>";
  protected static final String _30 = "</ol>";
  protected static final String _31 = "</p>";
  protected static final String _32 = "</script>";
  protected static final String _33 = "</section>";
  protected static final String _34 = "</span></a>";
  protected static final String _35 = "</style>";
  protected static final String _36 = "</title>";
  protected static final String _37 = "</ul>";
  protected static final String _38 = "<a href=\"#";
  protected static final String _39 = "<a href=\"https://www.eclipse.org/justj?page=index\">";
  protected static final String _40 = "<a href=\"justj.modules\" target=\"modules\" style=\"float: right; font-size: 75%;\"><img style=\"height: 2ex; display: inline-block;\" alt=\"justj\" src=\"https://www.eclipse.org/justj/justj_title.svg\"/>.modules</a>";
  protected static final String _41 = "<a style=\"font-size: 66%;\" href=\"";
  protected static final String _42 = "<body id=\"body_solstice\">";
  protected static final String _43 = "<br/>";
  protected static final String _44 = "<div class=\"col-sm-16 padding-left-30\">";
  protected static final String _45 = "<div class=\"col-sm-8 margin-top-15\"></div>";
  protected static final String _46 = "<div class=\"container\">";
  protected static final String _47 = "<div class=\"filter-target\">";
  protected static final String _48 = "<div class=\"hidden-xs col-sm-8 col-md-6 col-lg-5\" id=\"header-left\">";
  protected static final String _49 = "<div class=\"novaContent container\" id=\"novaContent\">";
  protected static final String _50 = "<div class=\"row\" id=\"header-row\">";
  protected static final String _51 = "<div class=\"row\">";
  protected static final String _52 = "<div class=\"wrapper-logo-default\">";
  protected static final String _53 = "<div id=\"maincontent\">";
  protected static final String _54 = "<div id=\"mod-deps\">";
  protected static final String _55 = "<div id=\"plugin-deps\">";
  protected static final String _56 = "<h2 style=\"\">";
  protected static final String _57 = "<h3 class=\"sr-only\">Breadcrumbs</h3>";
  protected static final String _58 = "<h4>";
  protected static final String _59 = "<head>";
  protected static final String _60 = "<header role=\"banner\" id=\"header-wrapper\">";
  protected static final String _61 = "<hr/>";
  protected static final String _62 = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">";
  protected static final String _63 = "<img class=\"logo-eclipse-default img-responsive hidden-xs\" style=\"height: 50px; space: nowrap; display: inline-block;\" alt=\"Project Logo\" src=\"https://www.eclipse.org/justj/justj_title.svg\"/>";
  protected static final String _64 = "<input name=\"module-split-packages\" type=\"checkbox\" onclick=\"filter(this.checked, 'mod-deps', 'split-package');\" onload=\"this.value = '';\"/>";
  protected static final String _65 = "<input name=\"plugin-errors\" type=\"checkbox\" onclick=\"filter(this.checked, 'plugin-deps', 'jdeps-error');\" onload=\"this.value = '';\"/>";
  protected static final String _66 = "<input type=\"checkbox\" id=\"toggle-id-";
  protected static final String _67 = "<label for=\"module-split-packages\" style=\"font-size: 75%;\">Show Only Split Packages</label>";
  protected static final String _68 = "<label for=\"plugin-errors\" style=\"font-size: 75%;\">Show Only Errors</label>";
  protected static final String _69 = "<label for=\"toggle-id-";
  protected static final String _70 = "<li class=\"active\">";
  protected static final String _71 = "<li class=\"filter-target\">";
  protected static final String _72 = "<li style=\"color: FireBrick; font-size: 80%;\" class=\"jdeps-error\">";
  protected static final String _73 = "<li>";
  protected static final String _74 = "<li><a href=\"";
  protected static final String _75 = "<li><a href=\"https://download.eclipse.org/justj/jdeps\">Jdeps</a></li>";
  protected static final String _76 = "<li><a href=\"https://www.eclipse.org/\">Home</a></li>";
  protected static final String _77 = "<li><a href=\"https://www.eclipse.org/justj?page=download\">JustJ</a></li>";
  protected static final String _78 = "<li><a href=\"https://www.eclipse.org/projects/\">Projects</a></li>";
  protected static final String _79 = "<link href=\"//fonts.googleapis.com/css?family=Open+Sans:400,700,300,600,100\" rel=\"stylesheet\" type=\"text/css\"/>";
  protected static final String _80 = "<link rel=\"icon\" type=\"image/ico\" href=\"https://www.eclipse.org/justj/justj_favicon.ico\"/>";
  protected static final String _81 = "<link rel=\"stylesheet\" href=\"https://www.eclipse.org/eclipse.org-common/themes/solstice/public/stylesheets/styles.min.css\"/>";
  protected static final String _82 = "<main class=\"no-promo\">";
  protected static final String _83 = "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>";
  protected static final String _84 = "<meta name=\"description\" content=\"JustJ's JDeps Report\"/>";
  protected static final String _85 = "<meta name=\"keywords\" content=\"eclipse,justj\"/>";
  protected static final String _86 = "<ol class=\"breadcrumb\">";
  protected static final String _87 = "<p>";
  protected static final String _88 = "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>";
  protected static final String _89 = "<script>";
  protected static final String _90 = "<section class=\"hidden-print default-breadcrumbs\" id=\"breadcrumb\">";
  protected static final String _91 = "<span style=\"color: DarkOliveGreen; font-size: 90%;\">";
  protected static final String _92 = "<style>";
  protected static final String _93 = "<title>";
  protected static final String _94 = "<ul class=\"toggle-content filter-target\" style=\"margin-left: 1em; margin-bottom: 0;\">";
  protected static final String _95 = "<ul style=\"margin-left: -2em; margin-top: 0; margin-bottom: 0; color: #E8BB14;\" class=\"split-package filter-target\">";
  protected static final String _96 = "<ul style=\"margin-left: -2em; margin-top: 0; margin-bottom: 0; color: #E8BB14;\">";
  protected static final String _97 = "Filters are provided to show only these issues.";
  protected static final String _98 = "Module Dependencies";
  protected static final String _99 = "Plugin Dependencies";
  protected static final String _100 = "The jdeps analysis of a plugin may produce warnings about split packages,";
  protected static final String _101 = "The plugin dependencies of each module and the module dependencies of each plugin are summarized below.";
  protected static final String _102 = "The plugins of this repository have been analyzed for module dependencies using jdeps.";
  protected static final String _103 = "\" class=\"plain\">";
  protected static final String _104 = "\" class=\"toggle-label\" id=\"";
  protected static final String _105 = "\" class=\"toggle-label\">";
  protected static final String _106 = "\" class=\"toggle\" onclick=\"expand_collapse('toggle-id-";
  protected static final String _107 = "\" class=\"toggle\"/>";
  protected static final String _108 = "\">";
  protected static final String _109 = "border: 1px solid black;";
  protected static final String _110 = "color: inherit;";
  protected static final String _111 = "cursor: pointer;";
  protected static final String _112 = "display: inherit;";
  protected static final String _113 = "display: none;";
  protected static final String _114 = "document.getElementsByTag";
  protected static final String _115 = "font-size: 100%;";
  protected static final String _116 = "font-weight: normal;";
  protected static final String _117 = "for (index = 0; index < inputs.length; ++index) {";
  protected static final String _118 = "for (var i = 0; i < filterTargets.length; i++) {";
  protected static final String _119 = "for (var i = 0; i < targets.length; i++) {";
  protected static final String _120 = "for (var parent = target.parentNode; parent != e; parent = parent.parentNode) {";
  protected static final String _121 = "function expand_collapse(id, targetId) {";
  protected static final String _122 = "function filter(filter, id, targetClass) {";
  protected static final String _123 = "i.e., if the jar exports packages that are the same as exported by the module on which it depends,";
  protected static final String _124 = "if (filter) {";
  protected static final String _125 = "if (filterTargetsArray.includes(parent)) {";
  protected static final String _126 = "if(oldOnload != null) {";
  protected static final String _127 = "input.checked = checked;";
  protected static final String _128 = "input.checked = false;";
  protected static final String _129 = "label:target {";
  protected static final String _130 = "margin-bottom: 0px;";
  protected static final String _131 = "max-height: 0;";
  protected static final String _132 = "max-height: none;";
  protected static final String _133 = "oldUnload();";
  protected static final String _134 = "opacity: 0;";
  protected static final String _135 = "opacity: 1;";
  protected static final String _136 = "or it may fail, i.e., if the jar has module dependencies that don't resolve.";
  protected static final String _137 = "overflow: hidden;";
  protected static final String _138 = "padding-left: 2em;";
  protected static final String _139 = "parent.style.display = parent.localName = \"li\" ? \"\" : \"block\";";
  protected static final String _140 = "target.style.display = !filter ? target.localName = \"li\" ? \"\" : \"block\" : \"none\";";
  protected static final String _141 = "target.style.display = \"block\";";
  protected static final String _142 = "transition: all .4s ease-in-out;";
  protected static final String _143 = "var checked = e.checked;";
  protected static final String _144 = "var e = document.getElementById(id);";
  protected static final String _145 = "var filterTargets = e.getElementsByClassName('filter-target');";
  protected static final String _146 = "var filterTargetsArray = [].slice.call(filterTargets);";
  protected static final String _147 = "var input = inputs[index];";
  protected static final String _148 = "var input = targets[i];";
  protected static final String _149 = "var inputs = document.getElementsByTagName('input');";
  protected static final String _150 = "var oldOnload = window.onload;";
  protected static final String _151 = "var target = document.getElementById(targetId);";
  protected static final String _152 = "var target = filterTargets[i];";
  protected static final String _153 = "var target = targets[i];";
  protected static final String _154 = "var targets = e.getElementsByClassName(targetClass);";
  protected static final String _155 = "var targets = target.getElementsByTagName('input');";
  protected static final String _156 = "window.onload = function() {";
  protected static final String _157 = "}";
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
  protected final String _158 = _17 + NL + _62 + NL + _59 + NL_1 + _83 + NL_1 + _93;
  protected final String _159 = _36 + NL_1 + _85 + NL_1 + _84 + NL_1 + _79 + NL_1 + _81 + NL_1 + _80 + NL_1 + _88 + NL_1 + _89 + NL_3 + _121 + NL_4 + _144 + NL_4 + _143 + NL_4 + _151 + NL_4 + _155 + NL_4 + _119 + NL_6 + _148 + NL_6 + _127 + NL_4 + _157 + NL_3 + _157 + NL + NL_3 + _122 + NL_4 + _144 + NL_4 + _145 + NL_4 + _118 + NL_6 + _152 + NL_6 + _140 + NL_4 + _157 + NL_4 + _124 + NL_6 + _146 + NL_6 + _154 + NL_6 + _119 + NL_8 + _153 + NL_8 + _141 + NL_8 + _120 + NL_9 + _125 + NL_10 + _139 + NL_9 + _157 + NL_8 + _157 + NL_6 + _157 + NL_4 + _157 + NL_3 + _157 + NL + NL_3 + _150 + NL_3 + _156 + NL_4 + _114 + NL_4 + _149 + NL_4 + _117 + NL_6 + _147 + NL_6 + _128 + NL_4 + _157 + NL_4 + _126 + NL_6 + _133 + NL_4 + _157 + NL_3 + _157 + NL_1 + _32 + NL + _24 + NL + NL_1 + _42 + NL_2 + _92 + NL + _9 + NL_1 + _113 + NL + _157 + NL + NL + _11 + NL_1 + _115 + NL_1 + _130 + NL_1 + _116 + NL + _157 + NL + NL + _6 + NL_1 + _131 + NL_1 + _134 + NL_1 + _137 + NL_1 + _142 + NL + _157 + NL + NL + _8 + NL_1 + _113 + NL + _157 + NL + NL + _7 + NL_1 + _112 + NL + _157 + NL + NL + _15 + NL_1 + _112 + NL + _157 + NL + NL + _14 + NL_1 + _113 + NL + _157 + NL + NL + _13 + NL_1 + _132 + NL_1 + _135 + NL + _157 + NL + NL + _10 + NL_1 + _138 + NL + _157 + NL + NL + _12 + NL_1 + _111 + NL + _157 + NL + NL + _5 + NL_1 + _110 + NL + _157 + NL + NL + _129 + NL_1 + _109 + NL + _157 + NL_2 + _35 + NL + NL_2 + _60 + NL_3 + _46 + NL_4 + _50 + NL_6 + _48 + NL_8 + _52 + NL_9 + _39 + NL_10 + _63 + NL_9 + _18 + NL_8 + _21 + NL_6 + _21 + NL_4 + _21 + NL_3 + _21 + NL_2 + _25 + NL + NL_2 + _90 + NL_3 + _46 + NL_4 + _57 + NL_4 + _51 + NL_6 + _44 + NL_8 + _86 + NL_9 + _76 + NL_9 + _78 + NL_9 + _77 + NL_9 + _75;
  protected final String _160 = NL_9 + _70;
  protected final String _161 = NL_9 + _73;
  protected final String _162 = NL_9 + _74;
  protected final String _163 = NL_8 + _30 + NL_6 + _21 + NL_6 + _45 + NL_4 + _21 + NL_3 + _21 + NL_2 + _33 + NL + NL_2 + _82 + NL_2 + _49 + NL + NL_3 + _53 + NL_4 + _56;
  protected final String _164 = _16 + NL_6 + _41;
  protected final String _165 = _18 + NL_6 + _40 + NL_4 + _22 + NL_4 + _87 + NL_3 + _102 + NL_3 + _101 + NL_4 + _31 + NL_4 + _87 + NL_3 + _100 + NL_3 + _123 + NL_3 + _136 + NL_3 + _97 + NL_4 + _31 + NL + NL_2 + _61 + NL_2 + _58 + NL_3 + _98 + NL_3 + _66;
  protected final String _166 = _3 + NL_3 + _69;
  protected final String _167 = NL_3 + _64 + NL_3 + _67;
  protected final String _168 = NL_2 + _23 + NL + NL_2 + _54;
  protected final String _169 = NL_3 + _47 + NL_4 + _66;
  protected final String _170 = _107 + NL_4 + _69;
  protected final String _171 = _27 + NL_4 + _94;
  protected final String _172 = NL_6 + _71 + NL_8 + _38;
  protected final String _173 = NL_8 + _95;
  protected final String _174 = NL_8 + _37;
  protected final String _175 = NL_6 + _28;
  protected final String _176 = NL_4 + _37 + NL_3 + _21;
  protected final String _177 = NL_2 + _21 + NL + NL_2 + _61 + NL_2 + _58 + NL_3 + _99 + NL_3 + _66;
  protected final String _178 = _4 + NL_3 + _69;
  protected final String _179 = NL_3 + _65 + NL_3 + _68;
  protected final String _180 = NL_2 + _23 + NL + NL_2 + _55;
  protected final String _181 = NL_5 + _91;
  protected final String _182 = _27 + NL_5 + _94;
  protected final String _183 = NL_8 + _96;
  protected final String _184 = NL_7 + _72;
  protected final String _185 = NL_2 + _21 + NL + NL_3 + _21 + NL_2 + _21 + NL_2 + _29 + NL_1 + _20 + NL + _26;

  private final URI repoURI;

  private final Map<String, Set<Plugin>> modulePlugins = new TreeMap<String, Set<Plugin>>();

  private final Map<Plugin, Set<String>> pluginModules = new TreeMap<>();

  private final Map<Plugin, List<String>> pluginErrors = new TreeMap<>();

  private final Map<ModulePluginPair, Set<String>> splitPackages = new HashMap<>();

  public static void main(String[] args)
  {
    try
    {
      Path folder = Paths.get(args[0]);
      URI repoURI = new URI(args[1]);
      JdepsIndex jdepsIndex = new JdepsIndex(folder, repoURI);
      String result = jdepsIndex.generate();
      Files.write(folder.resolve("index.html"), Collections.singleton(result));
      Files.write(folder.resolve("justj.modules"), jdepsIndex.modulePlugins.keySet());
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

  private static Pattern MODULE_NAME_PATTERN = Pattern.compile("\\p{Alpha}\\p{Alnum}*(\\.\\p{Alpha}\\p{Alnum}*)*");

  private static Pattern SPLIT_PACKAGE_WARNING_PATTERN = Pattern.compile("Warning: split package: ([^ ]+) jrt:/([^ ]+) ([^ ]+)");

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

  public String generate()
  {
    final StringBuilder builder = new StringBuilder();
    String title = "JustJ Jdeps";
  int toggleCounter = 0;
    builder.append(_158);
    builder.append(title);
    builder.append(_159);
    for (Map.Entry<String, String> entry : getBreadcrumbs().entrySet()) {
    if (entry.getValue() == null) {
    builder.append(_160);
    builder.append(entry.getKey());
    builder.append(_28);
    } else if (entry.getValue().trim().isEmpty()) {
    builder.append(_161);
    builder.append(entry.getKey());
    builder.append(_28);
    } else {
    builder.append(_162);
    builder.append(entry.getValue());
    builder.append(_108);
    builder.append(entry.getKey());
    builder.append(_19);
    }
    }
    builder.append(_163);
    builder.append(NL_6);
    builder.append(title);
    builder.append(_164);
    builder.append(repoURI);
    builder.append(_108);
    builder.append(repoURI);
    builder.append(_165);
    builder.append(++toggleCounter);
    builder.append(_106);
    builder.append(toggleCounter);
    builder.append(_166);
    builder.append(toggleCounter);
    builder.append(_105);
    builder.append(getToggleExpand(true));
    builder.append(_27);
    if (!splitPackages.isEmpty()) {
    builder.append(_167);
    }
    builder.append(_168);
    for (Map.Entry<String, Set<Plugin>> entry : modulePlugins.entrySet()) {
    String module = entry.getKey();
    int mainID = ++toggleCounter;
    builder.append(_169);
    builder.append(mainID);
    builder.append(_170);
    builder.append(mainID);
    builder.append(_104);
    builder.append(module);
    builder.append(_108);
    builder.append(getToggleExpand(true));
    builder.append(_1);
    builder.append(module);
    builder.append(_171);
    for (Plugin plugin : entry.getValue()) {
    builder.append(_172);
    builder.append(plugin);
    builder.append(_103);
    builder.append(plugin.getId());
    builder.append(_2);
    builder.append(plugin.getVersion());
    builder.append(_34);
    Set<String> splitPackages = getSplitPackages(module, plugin);
      if (!splitPackages.isEmpty()) {
    builder.append(_173);
    for (String packageName : splitPackages) {
    builder.append(_161);
    builder.append(packageName);
    builder.append(_28);
    }
    builder.append(_174);
    }
    builder.append(_175);
    }
    builder.append(_176);
    }
    builder.append(_177);
    builder.append(++toggleCounter);
    builder.append(_106);
    builder.append(toggleCounter);
    builder.append(_178);
    builder.append(toggleCounter);
    builder.append(_105);
    builder.append(getToggleExpand(true));
    builder.append(_27);
    if (!pluginErrors.isEmpty()) {
    builder.append(_179);
    }
    builder.append(_180);
    for (Map.Entry<Plugin, Set<String>> entry : pluginModules.entrySet()) {
    Plugin plugin = entry.getKey();
    Set<String> modules = entry.getValue();
    List<String> errors = pluginErrors.get(plugin);
    int mainID = ++toggleCounter;
    builder.append(_169);
    builder.append(mainID);
    builder.append(_170);
    builder.append(mainID);
    builder.append(_104);
    builder.append(plugin);
    builder.append(_108);
    builder.append(NL_5);
    builder.append(getToggleExpand(!modules.isEmpty() || errors != null));
    builder.append(NL_5);
    builder.append(plugin.getId());
    builder.append(_181);
    builder.append(plugin.getVersion());
    builder.append(_182);
    for (String module : modules) {
    builder.append(_172);
    builder.append(module);
    builder.append(_103);
    builder.append(module);
    builder.append(_18);
    Set<String> splitPackages = getSplitPackages(module, plugin);
      if (!splitPackages.isEmpty()) {
    builder.append(_183);
    for (String packageName : splitPackages) {
    builder.append(_161);
    builder.append(packageName);
    builder.append(_28);
    }
    builder.append(_174);
    }
    builder.append(_175);
    }
    if (errors != null) {
    builder.append(_184);
    for (String error : errors) {
    builder.append(NL_7);
    builder.append(error);
    builder.append(_43);
    }
    builder.append(_175);
    }
    builder.append(_176);
    }
    builder.append(_185);
    return builder.toString();
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
