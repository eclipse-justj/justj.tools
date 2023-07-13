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
package org.eclipse.justj.codegen.templates.index;

import java.util.*;
import org.eclipse.justj.codegen.model.util.Indexer;
import org.eclipse.justj.codegen.model.util.Indexer.*;

public class JREIndex
{
  protected static String nl;
  public static synchronized JREIndex create(String lineSeparator)
  {
    nl = lineSeparator;
    JREIndex result = new JREIndex();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = " ";
  protected static final String _2 = " - ";
  protected static final String _3 = " = ";
  protected static final String _4 = ".toggle + .toggle-label + .toggle-content {";
  protected static final String _5 = ".toggle + .toggle-label > .toggle-state-off {";
  protected static final String _6 = ".toggle + .toggle-label > .toggle-state-on {";
  protected static final String _7 = ".toggle {";
  protected static final String _8 = ".toggle-content {";
  protected static final String _9 = ".toggle-label {";
  protected static final String _10 = ".toggle-label:hover, .toggle-label:hover * {";
  protected static final String _11 = ".toggle:checked + .toggle-label + .toggle-content {";
  protected static final String _12 = ".toggle:checked + .toggle-label > .toggle-state-off {";
  protected static final String _13 = ".toggle:checked + .toggle-label > .toggle-state-on {";
  protected static final String _14 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
  protected static final String _15 = "</a>";
  protected static final String _16 = "</a></li>";
  protected static final String _17 = "</body>";
  protected static final String _18 = "</div>";
  protected static final String _19 = "</head>";
  protected static final String _20 = "</header>";
  protected static final String _21 = "</html>";
  protected static final String _22 = "</label>";
  protected static final String _23 = "</li>";
  protected static final String _24 = "</main>";
  protected static final String _25 = "</ol>";
  protected static final String _26 = "</section>";
  protected static final String _27 = "</style>";
  protected static final String _28 = "</table>";
  protected static final String _29 = "</td>";
  protected static final String _30 = "</title>";
  protected static final String _31 = "</ul>";
  protected static final String _32 = "<a href=\"";
  protected static final String _33 = "<a href=\"https://eclipse.dev/justj?page=index\">";
  protected static final String _34 = "<a href=\"justj.manifest\" target=\"manifest\" style=\"float: right; font-size: 75%;\"><img style=\"height: 2ex; display: inline-block;\" alt=\"justj\" src=\"https://eclipse.dev/justj/justj_title.svg\"/>.manifest</a></h2>";
  protected static final String _35 = "<body id=\"body_solstice\">";
  protected static final String _36 = "<div class=\"col-sm-16 padding-left-30\">";
  protected static final String _37 = "<div class=\"col-sm-8 margin-top-15\"></div>";
  protected static final String _38 = "<div class=\"container\">";
  protected static final String _39 = "<div class=\"hidden-xs col-sm-8 col-md-6 col-lg-5\" id=\"header-left\">";
  protected static final String _40 = "<div class=\"novaContent container\" id=\"novaContent\">";
  protected static final String _41 = "<div class=\"row\" id=\"header-row\">";
  protected static final String _42 = "<div class=\"row\">";
  protected static final String _43 = "<div class=\"wrapper-logo-default\">";
  protected static final String _44 = "<div id=\"maincontent\">";
  protected static final String _45 = "<h2 style=\"\">";
  protected static final String _46 = "<h3 class=\"sr-only\">Breadcrumbs</h3>";
  protected static final String _47 = "<head>";
  protected static final String _48 = "<header role=\"banner\" id=\"header-wrapper\">";
  protected static final String _49 = "<hr>";
  protected static final String _50 = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">";
  protected static final String _51 = "<img class=\"logo-eclipse-default img-responsive hidden-xs\" style=\"height: 50px; white-space: nowrap; display: inline-block;\" alt=\"Project Logo\" src=\"https://eclipse.dev/justj/justj_title.svg\"/>";
  protected static final String _52 = "<input type=\"checkbox\" id=\"toggle-id-";
  protected static final String _53 = "<label for=\"toggle-id-";
  protected static final String _54 = "<li class=\"active\">";
  protected static final String _55 = "<li>";
  protected static final String _56 = "<li><a href=\"";
  protected static final String _57 = "<li><a href=\"https://eclipse.dev/justj?page=download\">JustJ</a></li>";
  protected static final String _58 = "<li><a href=\"https://www.eclipse.org/\">Home</a></li>";
  protected static final String _59 = "<li><a href=\"https://www.eclipse.org/projects/\">Projects</a></li>";
  protected static final String _60 = "<link href=\"//fonts.googleapis.com/css?family=Open+Sans:400,700,300,600,100\" rel=\"stylesheet\" type=\"text/css\"/>";
  protected static final String _61 = "<link rel=\"icon\" type=\"image/ico\" href=\"https://eclipse.dev/justj/justj_favicon.ico\"/>";
  protected static final String _62 = "<link rel=\"stylesheet\" href=\"https://www.eclipse.org/eclipse.org-common/themes/solstice/public/stylesheets/styles.min.css\"/>";
  protected static final String _63 = "<main class=\"no-promo\">";
  protected static final String _64 = "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>";
  protected static final String _65 = "<meta name=\"description\" content=\"JustJ's Java Runtime Environment downloads\"/>";
  protected static final String _66 = "<meta name=\"keywords\" content=\"eclipse,justj\"/>";
  protected static final String _67 = "<ol class=\"breadcrumb\">";
  protected static final String _68 = "<pre style=\"margin-top: 5px;\">jlink --add-modules=$org.eclipse.justj.modules $org.eclipse.justj.jlink.args</pre>";
  protected static final String _69 = "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>";
  protected static final String _70 = "<section class=\"hidden-print default-breadcrumbs\" id=\"breadcrumb\">";
  protected static final String _71 = "<span style=\"text-align: right;\">";
  protected static final String _72 = "<style>";
  protected static final String _73 = "<table style=\"width: 100%;\">";
  protected static final String _74 = "<td style=\"vertical-align: top; min-width: 6em; max-width: 6em;\">";
  protected static final String _75 = "<td style=\"vertical-align: top; xmin-width: 3em; xmax-width: 3em;\">";
  protected static final String _76 = "<td>";
  protected static final String _77 = "<title>";
  protected static final String _78 = "<tr>";
  protected static final String _79 = "<ul class=\"toggle-content\" style=\"margin-top: 0px;\">";
  protected static final String _80 = "<ul class=\"toggle-content\">";
  protected static final String _81 = "Compressing modules and stripping debug symbols helps to reduce the size of the JRE.";
  protected static final String _82 = "Each JRE below has been tested by using it to launch Eclipse and recording the key system properties.";
  protected static final String _83 = "MB</span>";
  protected static final String _84 = "Of course the added modules determine the available packages in the generated JRE;";
  protected static final String _85 = "The recorded properties are displayed in the details for each JRE below,";
  protected static final String _86 = "These JREs were created using jlink:";
  protected static final String _87 = "\" class=\"toggle-label\" style=\"font-size: 125%;\">";
  protected static final String _88 = "\" class=\"toggle-label\">";
  protected static final String _89 = "\" class=\"toggle\"/>";
  protected static final String _90 = "\">";
  protected static final String _91 = "\"><div class=\"col-sm-6 downloadLink-icon\"><i class=\"fa fa-download\"></i></div></a>";
  protected static final String _92 = "cursor: pointer;";
  protected static final String _93 = "display: inherit;";
  protected static final String _94 = "display: none;";
  protected static final String _95 = "font-size: 100%;";
  protected static final String _96 = "font-weight: normal;";
  protected static final String _97 = "including the properties used to invoke jlink.";
  protected static final String _98 = "margin-bottom: 0px;";
  protected static final String _99 = "max-height: 0;";
  protected static final String _100 = "max-height: none;";
  protected static final String _101 = "opacity: 0;";
  protected static final String _102 = "opacity: 1;";
  protected static final String _103 = "overflow: hidden;";
  protected static final String _104 = "padding-left: 2em;";
  protected static final String _105 = "those packages are dynamically computed by OSGi and this information is also included in the details below.";
  protected static final String _106 = "transition: all .4s ease-in-out;";
  protected static final String _107 = "}";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "       ";
  protected final String NL_5 = NL + "        ";
  protected final String NL_6 = NL + "         ";
  protected final String NL_7 = NL + "          ";
  protected final String NL_8 = NL + "           ";
  protected final String NL_9 = NL + "            ";
  protected final String NL_10 = NL + "             ";
  protected final String NL_11 = NL + "              ";
  protected final String NL_12 = NL + "               ";
  protected final String NL_13 = NL + "                ";
  protected final String NL_14 = NL + "                 ";
  protected final String _108 = _14 + NL + _50 + NL + _47 + NL_1 + _64 + NL_1 + _77;
  protected final String _109 = _30 + NL_1 + _66 + NL_1 + _65 + NL_1 + _60 + NL_1 + _62 + NL_1 + _61 + NL_1 + _69 + NL + _19 + NL + NL_1 + _35 + NL_2 + _72 + NL + _7 + NL_1 + _94 + NL + _107 + NL + NL + _9 + NL_1 + _95 + NL_1 + _98 + NL_1 + _96 + NL + _107 + NL + NL + _4 + NL_1 + _99 + NL_1 + _101 + NL_1 + _103 + NL_1 + _106 + NL + _107 + NL + NL + _6 + NL_1 + _94 + NL + _107 + NL + NL + _5 + NL_1 + _93 + NL + _107 + NL + NL + _13 + NL_1 + _93 + NL + _107 + NL + NL + _12 + NL_1 + _94 + NL + _107 + NL + NL + _11 + NL_1 + _100 + NL_1 + _102 + NL + _107 + NL + NL + _8 + NL_1 + _104 + NL + _107 + NL + NL + _10 + NL_1 + _92 + NL + _107 + NL_2 + _27 + NL + NL_2 + _48 + NL_3 + _38 + NL_5 + _41 + NL_7 + _39 + NL_9 + _43 + NL_11 + _33 + NL_13 + _51 + NL_11 + _15 + NL_9 + _18 + NL_7 + _18 + NL_5 + _18 + NL_3 + _18 + NL_2 + _20 + NL + NL_2 + _70 + NL_3 + _38 + NL_5 + _46 + NL_5 + _42 + NL_7 + _36 + NL_9 + _67 + NL_11 + _58 + NL_11 + _59 + NL_11 + _57;
  protected final String _110 = NL_11 + _54;
  protected final String _111 = NL_11 + _55;
  protected final String _112 = NL_11 + _56;
  protected final String _113 = NL_9 + _25 + NL_7 + _18 + NL_7 + _37 + NL_5 + _18 + NL_3 + _18 + NL_2 + _26 + NL + NL_2 + _63 + NL_2 + _40 + NL + NL_3 + _44 + NL_5 + _45;
  protected final String _114 = _34 + NL_3 + _86 + NL_3 + _68 + NL_3 + _82 + NL_3 + _85 + NL_3 + _97 + NL_3 + _84 + NL_3 + _105 + NL_3 + _81 + NL + NL_2 + _49;
  protected final String _115 = NL_2 + _73 + NL_3 + _78 + NL_4 + _76 + NL_6 + _52;
  protected final String _116 = _89 + NL_6 + _53;
  protected final String _117 = _22 + NL_6 + _80;
  protected final String _118 = NL_8 + _55;
  protected final String _119 = NL_8 + _23;
  protected final String _120 = NL_8 + _55 + NL_10 + _52;
  protected final String _121 = _89 + NL_10 + _53;
  protected final String _122 = _22 + NL_10 + _79;
  protected final String _123 = NL_12 + _55;
  protected final String _124 = NL_12 + _23;
  protected final String _125 = NL_10 + _31 + NL_8 + _23;
  protected final String _126 = NL_6 + _31 + NL + NL_4 + _29 + NL_4 + _75 + NL_6 + _32;
  protected final String _127 = _91 + NL_4 + _29 + NL_4 + _74 + NL_6 + _71;
  protected final String _128 = _83 + NL_4 + _29 + NL_2 + _28;
  protected final String _129 = NL + NL_3 + _18 + NL_2 + _18 + NL_2 + _24 + NL_1 + _17 + NL + _21;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    String title = "JustJ JREs";
  Indexer indexer = (Indexer)argument;
  List<JRE> jres = indexer.getJREs();
  String folderName = jres.get(0).getDownloadFolder();
  String toggleExpand = "<span style=\"font-size: 100%;\" class=\"orange toggle-state-on\">&#x25E2;</span>";
  String toggleCollapse = "<span style=\"font-size: 100%;\" class=\"orange toggle-state-off\">&#x25B7;</span>";
  String readMore = "<span style=\"font-size: 75%;\" class=\"orange toggle-state-off\">&nbsp;&nbsp;read more...</span>";
  int toggleCounter = 0;
    builder.append(_108);
    builder.append(title);
    builder.append(_109);
    for (Map.Entry<String, String> entry : indexer.getBreadcrumbs().entrySet()) {
    if (entry.getValue() == null) {
    builder.append(_110);
    builder.append(entry.getKey());
    builder.append(_23);
    } else if (entry.getValue().trim().isEmpty()) {
    builder.append(_111);
    builder.append(entry.getKey());
    builder.append(_23);
    } else {
    builder.append(_112);
    builder.append(entry.getValue());
    builder.append(_90);
    builder.append(entry.getKey());
    builder.append(_16);
    }
    }
    builder.append(_113);
    builder.append(title);
    builder.append(_2);
    builder.append(folderName);
    builder.append(_114);
    for (JRE jre : jres) {
    int mainID = ++toggleCounter;
    builder.append(_115);
    builder.append(mainID);
    builder.append(_116);
    builder.append(mainID);
    builder.append(_87);
    builder.append(toggleExpand);
    builder.append(toggleCollapse);
    builder.append(_1);
    builder.append(jre.getName());
    builder.append(_1);
    builder.append(readMore);
    builder.append(_117);
    for (Property property : jre.getProperties()) {
      List<String> children = property.getChildren();
      if (children.isEmpty()) {
    builder.append(_118);
    builder.append(NL_10);
    builder.append(property.getKey());
    builder.append(_3);
    builder.append(property.getValue());
    builder.append(_119);
    } else {
        int valueID = ++toggleCounter;
    builder.append(_120);
    builder.append(valueID);
    builder.append(_121);
    builder.append(valueID);
    builder.append(_88);
    builder.append(toggleExpand);
    builder.append(toggleCollapse);
    builder.append(_1);
    builder.append(property.getKey());
    builder.append(_122);
    for (String value : children) {
          valueID = ++toggleCounter;
    builder.append(_123);
    builder.append(NL_14);
    builder.append(value);
    builder.append(_124);
    }
    builder.append(_125);
    }
    }
    builder.append(_126);
    builder.append(jre.getDownloadURI());
    builder.append(_127);
    builder.append(jre.getSizeInMegaBytes());
    builder.append(_128);
    }
    builder.append(_129);
    return builder.toString();
  }
}
