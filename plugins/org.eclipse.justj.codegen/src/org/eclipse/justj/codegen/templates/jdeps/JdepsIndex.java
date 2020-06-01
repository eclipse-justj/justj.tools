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
  protected static final String _2 = " <a href=\"";
  protected static final String _3 = " <span style=\"color: DarkOliveGreen; font-size: 90%;\">";
  protected static final String _4 = " Index";
  protected static final String _5 = "#";
  protected static final String _6 = "', 'mod-deps');\"/>";
  protected static final String _7 = "', 'plugin-deps');\"/>";
  protected static final String _8 = ".plain:link, .plain:visited {";
  protected static final String _9 = ".toggle + .toggle-label + .toggle-content {";
  protected static final String _10 = ".toggle + .toggle-label > .toggle-state-off {";
  protected static final String _11 = ".toggle + .toggle-label > .toggle-state-on {";
  protected static final String _12 = ".toggle {";
  protected static final String _13 = ".toggle-content {";
  protected static final String _14 = ".toggle-label {";
  protected static final String _15 = ".toggle-label:hover, .toggle-label:hover * {";
  protected static final String _16 = ".toggle:checked + .toggle-label + .toggle-content {";
  protected static final String _17 = ".toggle:checked + .toggle-label > .toggle-state-off {";
  protected static final String _18 = ".toggle:checked + .toggle-label > .toggle-state-on {";
  protected static final String _19 = ":";
  protected static final String _20 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
  protected static final String _21 = "</a>";
  protected static final String _22 = "</a></label>";
  protected static final String _23 = "</a></li>";
  protected static final String _24 = "</body>";
  protected static final String _25 = "</div>";
  protected static final String _26 = "</h2>";
  protected static final String _27 = "</h4>";
  protected static final String _28 = "</head>";
  protected static final String _29 = "</header>";
  protected static final String _30 = "</html>";
  protected static final String _31 = "</label>";
  protected static final String _32 = "</li>";
  protected static final String _33 = "</main>";
  protected static final String _34 = "</ol>";
  protected static final String _35 = "</p>";
  protected static final String _36 = "</script>";
  protected static final String _37 = "</section>";
  protected static final String _38 = "</span></a>";
  protected static final String _39 = "</style>";
  protected static final String _40 = "</title>";
  protected static final String _41 = "</ul>";
  protected static final String _42 = "<a href=\"";
  protected static final String _43 = "<a href=\"#";
  protected static final String _44 = "<a href=\"https://www.eclipse.org/justj?page=index\">";
  protected static final String _45 = "<a href=\"justj.modules\" target=\"modules\" style=\"float: right; font-size: 75%;\"><img style=\"height: 2ex; display: inline-block;\" alt=\"justj\" src=\"https://www.eclipse.org/justj/justj_title.svg\"/>.modules</a>";
  protected static final String _46 = "<a style=\"font-size: 66%;\" href=\"";
  protected static final String _47 = "<body id=\"body_solstice\">";
  protected static final String _48 = "<br/>";
  protected static final String _49 = "<div class=\"col-sm-16 padding-left-30\">";
  protected static final String _50 = "<div class=\"col-sm-8 margin-top-15\"></div>";
  protected static final String _51 = "<div class=\"container\">";
  protected static final String _52 = "<div class=\"filter-target\">";
  protected static final String _53 = "<div class=\"hidden-xs col-sm-8 col-md-6 col-lg-5\" id=\"header-left\">";
  protected static final String _54 = "<div class=\"novaContent container\" id=\"novaContent\">";
  protected static final String _55 = "<div class=\"row\" id=\"header-row\">";
  protected static final String _56 = "<div class=\"row\">";
  protected static final String _57 = "<div class=\"wrapper-logo-default\">";
  protected static final String _58 = "<div id=\"maincontent\">";
  protected static final String _59 = "<div id=\"mod-deps\">";
  protected static final String _60 = "<div id=\"plugin-deps\">";
  protected static final String _61 = "<div>";
  protected static final String _62 = "<h2 style=\"\">";
  protected static final String _63 = "<h3 class=\"sr-only\">Breadcrumbs</h3>";
  protected static final String _64 = "<h4>";
  protected static final String _65 = "<head>";
  protected static final String _66 = "<header role=\"banner\" id=\"header-wrapper\">";
  protected static final String _67 = "<hr/>";
  protected static final String _68 = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">";
  protected static final String _69 = "<img class=\"logo-eclipse-default img-responsive hidden-xs\" style=\"height: 50px; space: nowrap; display: inline-block;\" alt=\"Project Logo\" src=\"https://www.eclipse.org/justj/justj_title.svg\"/>";
  protected static final String _70 = "<input name=\"module-split-packages\" type=\"checkbox\" onclick=\"filter(this.checked, 'mod-deps', 'split-package');\" onload=\"this.value = '';\"/>";
  protected static final String _71 = "<input name=\"plugin-errors\" type=\"checkbox\" onclick=\"filter(this.checked, 'plugin-deps', 'jdeps-error');\" onload=\"this.value = '';\"/>";
  protected static final String _72 = "<input type=\"checkbox\" id=\"toggle-id-";
  protected static final String _73 = "<label for=\"module-split-packages\" style=\"font-size: 75%;\">Show Only Split Packages</label>";
  protected static final String _74 = "<label for=\"plugin-errors\" style=\"font-size: 75%;\">Show Only Errors</label>";
  protected static final String _75 = "<label for=\"toggle-id-";
  protected static final String _76 = "<li class=\"active\">";
  protected static final String _77 = "<li class=\"filter-target\">";
  protected static final String _78 = "<li style=\"color: FireBrick; font-size: 80%;\" class=\"jdeps-error\">";
  protected static final String _79 = "<li>";
  protected static final String _80 = "<li><a href=\"";
  protected static final String _81 = "<li><a href=\"https://download.eclipse.org/justj/jdeps\">Jdeps</a></li>";
  protected static final String _82 = "<li><a href=\"https://www.eclipse.org/\">Home</a></li>";
  protected static final String _83 = "<li><a href=\"https://www.eclipse.org/justj?page=download\">JustJ</a></li>";
  protected static final String _84 = "<li><a href=\"https://www.eclipse.org/projects/\">Projects</a></li>";
  protected static final String _85 = "<link href=\"//fonts.googleapis.com/css?family=Open+Sans:400,700,300,600,100\" rel=\"stylesheet\" type=\"text/css\"/>";
  protected static final String _86 = "<link rel=\"icon\" type=\"image/ico\" href=\"https://www.eclipse.org/justj/justj_favicon.ico\"/>";
  protected static final String _87 = "<link rel=\"stylesheet\" href=\"https://www.eclipse.org/eclipse.org-common/themes/solstice/public/stylesheets/styles.min.css\"/>";
  protected static final String _88 = "<main class=\"no-promo\">";
  protected static final String _89 = "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>";
  protected static final String _90 = "<meta name=\"description\" content=\"JustJ's JDeps Report\"/>";
  protected static final String _91 = "<meta name=\"keywords\" content=\"eclipse,justj\"/>";
  protected static final String _92 = "<ol class=\"breadcrumb\">";
  protected static final String _93 = "<p>";
  protected static final String _94 = "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>";
  protected static final String _95 = "<script>";
  protected static final String _96 = "<section class=\"hidden-print default-breadcrumbs\" id=\"breadcrumb\">";
  protected static final String _97 = "<span style=\"color: DarkOliveGreen; font-size: 90%;\">";
  protected static final String _98 = "<style>";
  protected static final String _99 = "<title>";
  protected static final String _100 = "<ul class=\"toggle-content filter-target\" style=\"margin-left: 1em; margin-bottom: 0;\">";
  protected static final String _101 = "<ul class=\"toggle-content\" style=\"margin-left: 1em; margin-bottom: 0;\">";
  protected static final String _102 = "<ul style=\"margin-left: -2em; margin-top: 0; margin-bottom: 0; color: #E8BB14;\" class=\"split-package filter-target\">";
  protected static final String _103 = "<ul style=\"margin-left: -2em; margin-top: 0; margin-bottom: 0; color: #E8BB14;\">";
  protected static final String _104 = "A summary of the overall modules dependencies of each repository are displayed in detail below.";
  protected static final String _105 = "Each repository item is a link to the report for that repository and each module item is a link to that corresponding item in the corresponding repository report.";
  protected static final String _106 = "If these issues are present, filters are provided to show only these issues.";
  protected static final String _107 = "Module Dependencies";
  protected static final String _108 = "Plugin Dependencies";
  protected static final String _109 = "The jdeps analysis of a plugin may produce warnings about split packages,";
  protected static final String _110 = "The plugin dependencies of each module and the module dependencies of each plugin are summarized below.";
  protected static final String _111 = "The plugins of the following repositories have been analyzed for module dependencies using jdeps.";
  protected static final String _112 = "The plugins of this repository have been analyzed for module dependencies using jdeps.";
  protected static final String _113 = "\" class=\"plain\">";
  protected static final String _114 = "\" class=\"toggle-label\" id=\"";
  protected static final String _115 = "\" class=\"toggle-label\">";
  protected static final String _116 = "\" class=\"toggle\" onclick=\"expand_collapse('toggle-id-";
  protected static final String _117 = "\" class=\"toggle\"/>";
  protected static final String _118 = "\">";
  protected static final String _119 = "border: 1px solid black;";
  protected static final String _120 = "color: inherit;";
  protected static final String _121 = "cursor: pointer;";
  protected static final String _122 = "display: inherit;";
  protected static final String _123 = "display: none;";
  protected static final String _124 = "document.getElementsByTag";
  protected static final String _125 = "font-size: 100%;";
  protected static final String _126 = "font-weight: normal;";
  protected static final String _127 = "for (index = 0; index < inputs.length; ++index) {";
  protected static final String _128 = "for (var i = 0; i < filterTargets.length; i++) {";
  protected static final String _129 = "for (var i = 0; i < targets.length; i++) {";
  protected static final String _130 = "for (var parent = target.parentNode; parent != e; parent = parent.parentNode) {";
  protected static final String _131 = "function expand_collapse(id, targetId) {";
  protected static final String _132 = "function filter(filter, id, targetClass) {";
  protected static final String _133 = "i.e., if the jar exports packages that are the same as exported by the module on which it depends,";
  protected static final String _134 = "if (filter) {";
  protected static final String _135 = "if (filterTargetsArray.includes(parent)) {";
  protected static final String _136 = "if(oldOnload != null) {";
  protected static final String _137 = "input.checked = checked;";
  protected static final String _138 = "input.checked = false;";
  protected static final String _139 = "label:target {";
  protected static final String _140 = "margin-bottom: 0px;";
  protected static final String _141 = "max-height: 0;";
  protected static final String _142 = "max-height: none;";
  protected static final String _143 = "oldUnload();";
  protected static final String _144 = "opacity: 0;";
  protected static final String _145 = "opacity: 1;";
  protected static final String _146 = "or it may fail, i.e., if the jar has module dependencies that don't resolve.";
  protected static final String _147 = "overflow: hidden;";
  protected static final String _148 = "padding-left: 2em;";
  protected static final String _149 = "parent.style.display = parent.localName = \"li\" ? \"\" : \"block\";";
  protected static final String _150 = "target.style.display = !filter ? target.localName = \"li\" ? \"\" : \"block\" : \"none\";";
  protected static final String _151 = "target.style.display = \"block\";";
  protected static final String _152 = "transition: all .4s ease-in-out;";
  protected static final String _153 = "var checked = e.checked;";
  protected static final String _154 = "var e = document.getElementById(id);";
  protected static final String _155 = "var filterTargets = e.getElementsByClassName('filter-target');";
  protected static final String _156 = "var filterTargetsArray = [].slice.call(filterTargets);";
  protected static final String _157 = "var input = inputs[index];";
  protected static final String _158 = "var input = targets[i];";
  protected static final String _159 = "var inputs = document.getElementsByTagName('input');";
  protected static final String _160 = "var oldOnload = window.onload;";
  protected static final String _161 = "var target = document.getElementById(targetId);";
  protected static final String _162 = "var target = filterTargets[i];";
  protected static final String _163 = "var target = targets[i];";
  protected static final String _164 = "var targets = e.getElementsByClassName(targetClass);";
  protected static final String _165 = "var targets = target.getElementsByTagName('input');";
  protected static final String _166 = "window.onload = function() {";
  protected static final String _167 = "}";
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
  protected final String _168 = _20 + NL + _68 + NL + _65 + NL_1 + _89 + NL_1 + _99;
  protected final String _169 = _40 + NL_1 + _91 + NL_1 + _90 + NL_1 + _85 + NL_1 + _87 + NL_1 + _86 + NL_1 + _94 + NL_1 + _95 + NL_3 + _131 + NL_4 + _154 + NL_4 + _153 + NL_4 + _161 + NL_4 + _165 + NL_4 + _129 + NL_6 + _158 + NL_6 + _137 + NL_4 + _167 + NL_3 + _167 + NL + NL_3 + _132 + NL_4 + _154 + NL_4 + _155 + NL_4 + _128 + NL_6 + _162 + NL_6 + _150 + NL_4 + _167 + NL_4 + _134 + NL_6 + _156 + NL_6 + _164 + NL_6 + _129 + NL_8 + _163 + NL_8 + _151 + NL_8 + _130 + NL_9 + _135 + NL_10 + _149 + NL_9 + _167 + NL_8 + _167 + NL_6 + _167 + NL_4 + _167 + NL_3 + _167 + NL + NL_3 + _160 + NL_3 + _166 + NL_4 + _124 + NL_4 + _159 + NL_4 + _127 + NL_6 + _157 + NL_6 + _138 + NL_4 + _167 + NL_4 + _136 + NL_6 + _143 + NL_4 + _167 + NL_3 + _167 + NL_1 + _36 + NL + _28 + NL + NL_1 + _47 + NL_2 + _98 + NL + _12 + NL_1 + _123 + NL + _167 + NL + NL + _14 + NL_1 + _125 + NL_1 + _140 + NL_1 + _126 + NL + _167 + NL + NL + _9 + NL_1 + _141 + NL_1 + _144 + NL_1 + _147 + NL_1 + _152 + NL + _167 + NL + NL + _11 + NL_1 + _123 + NL + _167 + NL + NL + _10 + NL_1 + _122 + NL + _167 + NL + NL + _18 + NL_1 + _122 + NL + _167 + NL + NL + _17 + NL_1 + _123 + NL + _167 + NL + NL + _16 + NL_1 + _142 + NL_1 + _145 + NL + _167 + NL + NL + _13 + NL_1 + _148 + NL + _167 + NL + NL + _15 + NL_1 + _121 + NL + _167 + NL + NL + _8 + NL_1 + _120 + NL + _167 + NL + NL + _139 + NL_1 + _119 + NL + _167 + NL_2 + _39 + NL + NL_2 + _66 + NL_3 + _51 + NL_4 + _55 + NL_6 + _53 + NL_8 + _57 + NL_9 + _44 + NL_10 + _69 + NL_9 + _21 + NL_8 + _25 + NL_6 + _25 + NL_4 + _25 + NL_3 + _25 + NL_2 + _29 + NL + NL_2 + _96 + NL_3 + _51 + NL_4 + _63 + NL_4 + _56 + NL_6 + _49 + NL_8 + _92 + NL_9 + _82 + NL_9 + _84 + NL_9 + _83 + NL_9 + _81;
  protected final String _170 = NL_9 + _76;
  protected final String _171 = NL_9 + _79;
  protected final String _172 = NL_9 + _80;
  protected final String _173 = NL_8 + _34 + NL_6 + _25 + NL_6 + _50 + NL_4 + _25 + NL_3 + _25 + NL_2 + _37 + NL + NL_2 + _88 + NL_2 + _54 + NL + NL_3 + _58;
  protected final String _174 = NL_4 + _62;
  protected final String _175 = _4 + NL_6 + _45 + NL_4 + _26 + NL_4 + _93 + NL_6 + _111 + NL_6 + _104 + NL_6 + _105 + NL_4 + _35;
  protected final String _176 = NL_6 + _61 + NL_8 + _72;
  protected final String _177 = _117 + NL_8 + _75;
  protected final String _178 = _22 + NL_8 + _101;
  protected final String _179 = NL_9 + _79 + NL_10 + _42;
  protected final String _180 = _21 + NL_9 + _32;
  protected final String _181 = NL_8 + _41 + NL_6 + _25;
  protected final String _182 = _19 + NL_6 + _46;
  protected final String _183 = _21 + NL_6 + _45 + NL_4 + _26 + NL_4 + _93 + NL_3 + _112 + NL_3 + _110 + NL_4 + _35 + NL_4 + _93 + NL_3 + _109 + NL_3 + _133 + NL_3 + _146 + NL_3 + _106 + NL_4 + _35 + NL + NL_2 + _67 + NL_2 + _64 + NL_3 + _107 + NL_3 + _72;
  protected final String _184 = _6 + NL_3 + _75;
  protected final String _185 = NL_3 + _70 + NL_3 + _73;
  protected final String _186 = NL_2 + _27 + NL + NL_2 + _59;
  protected final String _187 = NL_3 + _52 + NL_4 + _72;
  protected final String _188 = _117 + NL_4 + _75;
  protected final String _189 = _31 + NL_4 + _100;
  protected final String _190 = NL_6 + _77 + NL_8 + _43;
  protected final String _191 = NL_8 + _102;
  protected final String _192 = NL_8 + _41;
  protected final String _193 = NL_6 + _32;
  protected final String _194 = NL_4 + _41 + NL_3 + _25;
  protected final String _195 = NL_2 + _25 + NL + NL_2 + _67 + NL_2 + _64 + NL_3 + _108 + NL_3 + _72;
  protected final String _196 = _7 + NL_3 + _75;
  protected final String _197 = NL_3 + _71 + NL_3 + _74;
  protected final String _198 = NL_2 + _27 + NL + NL_2 + _60;
  protected final String _199 = NL_5 + _97;
  protected final String _200 = _31 + NL_5 + _100;
  protected final String _201 = NL_8 + _103;
  protected final String _202 = NL_7 + _78;
  protected final String _203 = NL_2 + _25;
  protected final String _204 = NL_3 + _25 + NL_2 + _25 + NL_2 + _33 + NL_1 + _24 + NL + _30;

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
        Files.write(folder.resolve("index.html"), Collections.singleton(result));
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
        Files.write(folder.resolve("index.html"), Collections.singleton(result));
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
    final StringBuilder builder = new StringBuilder();
    String title = "JustJ Jdeps";
  int toggleCounter = 0;
    builder.append(_168);
    builder.append(title);
    builder.append(_169);
    for (Map.Entry<String, String> entry : getBreadcrumbs().entrySet()) {
    if (entry.getValue() == null) {
    builder.append(_170);
    builder.append(entry.getKey());
    builder.append(_32);
    } else if (entry.getValue().trim().isEmpty()) {
    builder.append(_171);
    builder.append(entry.getKey());
    builder.append(_32);
    } else {
    builder.append(_172);
    builder.append(entry.getValue());
    builder.append(_118);
    builder.append(entry.getKey());
    builder.append(_23);
    }
    }
    builder.append(_173);
    if (!indices.isEmpty()) {
    builder.append(_174);
    builder.append(NL_6);
    builder.append(title);
    builder.append(_175);
    for (Map.Entry<Path, URI> entry : indices.entrySet()) {
      Path index = entry.getKey();
      URI uri = entry.getValue();
      String repoLink = index.toString().replace('\\', '/');
      int mainID = ++toggleCounter;
    builder.append(_176);
    builder.append(mainID);
    builder.append(_177);
    builder.append(mainID);
    builder.append(_115);
    builder.append(getToggleExpand(true));
    builder.append(_2);
    builder.append(repoLink);
    builder.append(_118);
    builder.append(uri);
    builder.append(_178);
    for (String module : getModules(index)) {
    builder.append(_179);
    builder.append(repoLink);
    builder.append(_5);
    builder.append(module);
    builder.append(_118);
    builder.append(module);
    builder.append(_180);
    }
    builder.append(_181);
    }
    builder.append(NL);
    } else {
    builder.append(_174);
    builder.append(NL_6);
    builder.append(title);
    builder.append(_182);
    builder.append(repoURI);
    builder.append(_118);
    builder.append(repoURI);
    builder.append(_183);
    builder.append(++toggleCounter);
    builder.append(_116);
    builder.append(toggleCounter);
    builder.append(_184);
    builder.append(toggleCounter);
    builder.append(_115);
    builder.append(getToggleExpand(true));
    builder.append(_31);
    if (!splitPackages.isEmpty()) {
    builder.append(_185);
    }
    builder.append(_186);
    for (Map.Entry<String, Set<Plugin>> entry : modulePlugins.entrySet()) {
      String module = entry.getKey();
      int mainID = ++toggleCounter;
    builder.append(_187);
    builder.append(mainID);
    builder.append(_188);
    builder.append(mainID);
    builder.append(_114);
    builder.append(module);
    builder.append(_118);
    builder.append(getToggleExpand(true));
    builder.append(_1);
    builder.append(module);
    builder.append(_189);
    for (Plugin plugin : entry.getValue()) {
    builder.append(_190);
    builder.append(plugin);
    builder.append(_113);
    builder.append(plugin.getId());
    builder.append(_3);
    builder.append(plugin.getVersion());
    builder.append(_38);
    Set<String> splitPackages = getSplitPackages(module, plugin);
        if (!splitPackages.isEmpty()) {
    builder.append(_191);
    for (String packageName : splitPackages) {
    builder.append(_171);
    builder.append(packageName);
    builder.append(_32);
    }
    builder.append(_192);
    }
    builder.append(_193);
    }
    builder.append(_194);
    }
    builder.append(_195);
    builder.append(++toggleCounter);
    builder.append(_116);
    builder.append(toggleCounter);
    builder.append(_196);
    builder.append(toggleCounter);
    builder.append(_115);
    builder.append(getToggleExpand(true));
    builder.append(_31);
    if (!pluginErrors.isEmpty()) {
    builder.append(_197);
    }
    builder.append(_198);
    for (Map.Entry<Plugin, Set<String>> entry : pluginModules.entrySet()) {
      Plugin plugin = entry.getKey();
      Set<String> modules = entry.getValue();
      List<String> errors = pluginErrors.get(plugin);
      int mainID = ++toggleCounter;
    builder.append(_187);
    builder.append(mainID);
    builder.append(_188);
    builder.append(mainID);
    builder.append(_114);
    builder.append(plugin);
    builder.append(_118);
    builder.append(NL_5);
    builder.append(getToggleExpand(!modules.isEmpty() || errors != null));
    builder.append(NL_5);
    builder.append(plugin.getId());
    builder.append(_199);
    builder.append(plugin.getVersion());
    builder.append(_200);
    for (String module : modules) {
    builder.append(_190);
    builder.append(module);
    builder.append(_113);
    builder.append(module);
    builder.append(_21);
    Set<String> splitPackages = getSplitPackages(module, plugin);
        if (!splitPackages.isEmpty()) {
    builder.append(_201);
    for (String packageName : splitPackages) {
    builder.append(_171);
    builder.append(packageName);
    builder.append(_32);
    }
    builder.append(_192);
    }
    builder.append(_193);
    }
    if (errors != null) {
    builder.append(_202);
    for (String error : errors) {
    builder.append(NL_7);
    builder.append(error);
    builder.append(_48);
    }
    builder.append(_193);
    }
    builder.append(_194);
    }
    builder.append(_203);
    }
    builder.append(_204);
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
