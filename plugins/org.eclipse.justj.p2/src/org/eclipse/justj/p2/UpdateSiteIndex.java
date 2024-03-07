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
package org.eclipse.justj.p2;

import java.io.*;
import java.util.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.justj.codegen.model.util.Indexer.Property;

public class UpdateSiteIndex
{
  protected static String nl;
  public static synchronized UpdateSiteIndex create(String lineSeparator)
  {
    nl = lineSeparator;
    UpdateSiteIndex result = new UpdateSiteIndex();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" + NL + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">" + NL + "<head>" + NL + "  <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>" + NL + "  <title>";
  protected final String TEXT_2 = "</title>" + NL + "  <meta name=\"keywords\" content=\"eclipse,";
  protected final String TEXT_3 = "\"/>" + NL + "  <meta name=\"description\" content=\"";
  protected final String TEXT_4 = " p2 Update Sites\"/>" + NL + "  <link href=\"//fonts.googleapis.com/css?family=Open+Sans:400,700,300,600,100\" rel=\"stylesheet\" type=\"text/css\"/>" + NL + "  <link rel=\"stylesheet\" href=\"https://www.eclipse.org/eclipse.org-common/themes/solstice/public/stylesheets/styles.min.css\"/>" + NL + "  <link rel=\"icon\" type=\"image/ico\" href=\"";
  protected final String TEXT_5 = "\"/>" + NL + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>" + NL + "</head>" + NL + "" + NL + "  <body id=\"body_solstice\">";
  protected final String TEXT_6 = NL + "    <style>" + NL + ".toggle {" + NL + "  display: none;" + NL + "}" + NL + "" + NL + ".toggle-label {" + NL + "  font-size: 100%;" + NL + "  margin-bottom: 0px;" + NL + "  font-weight: normal;" + NL + "}" + NL + "" + NL + ".toggle + .toggle-label + .toggle-content {" + NL + "  max-height: 0;" + NL + "  opacity: 0;" + NL + "  overflow: hidden;" + NL + "  transition: all .4s ease-in-out;" + NL + "}" + NL + "" + NL + ".toggle + .toggle-label > .toggle-state-on {" + NL + "  display: none;" + NL + "}" + NL + "" + NL + ".toggle + .toggle-label > .toggle-state-off {" + NL + "  display: inherit;" + NL + "}" + NL + "" + NL + ".toggle:checked + .toggle-label > .toggle-state-on {" + NL + "  display: inherit;" + NL + "}" + NL + "" + NL + ".toggle:checked + .toggle-label > .toggle-state-off {" + NL + "  display: none;" + NL + "}" + NL + "" + NL + ".toggle:checked + .toggle-label + .toggle-content {" + NL + "  max-height: none;" + NL + "  opacity: 1;" + NL + "}" + NL + "" + NL + ".toggle-content {" + NL + "  padding-left: 2em;" + NL + "}" + NL + "" + NL + ".toggle-label:hover, .toggle-label:hover * {" + NL + "  cursor: pointer;" + NL + "}" + NL + "    </style>";
  protected final String TEXT_7 = NL + "    <script>" + NL + "" + NL + "      function copyToClipboard(element) {" + NL + "        var $temp = $(\"<input>\");" + NL + "        $(\"body\").append($temp);" + NL + "        $temp.val($(element).text()).select();" + NL + "        document.execCommand(\"copy\");" + NL + "        $temp.remove();" + NL + "      }" + NL + "" + NL + "      function toggle(id) {" + NL + "        var e = document.getElementById(id);" + NL + "        if (e.title == 'Expand All') {" + NL + "          e.title= 'Collapse All';" + NL + "          e.innerHTML = '&#x25E2;';" + NL + "        } else {" + NL + "          e.title= 'Expand All';" + NL + "          e.innerHTML = '&#x25B7;';" + NL + "        }" + NL + "      }" + NL + "" + NL + "      function expand(id, all_id) {" + NL + "        var t = document.getElementById(all_id);" + NL + "        var e = document.getElementById(id);" + NL + "        var f = document.getElementById(id+\"_arrows\");" + NL + "        if (t.title == 'Collapse All'){" + NL + "          e.style.display = 'block';" + NL + "          f.innerHTML = '&#x25E2;';" + NL + "        } else {" + NL + "          e.style.display = 'none';" + NL + "          f.innerHTML = '&#x25B7;';" + NL + "        }" + NL + "      }" + NL + "" + NL + "      function expand_collapse(id) {" + NL + "        var e = document.getElementById(id);" + NL + "        var f = document.getElementById(id+\"_arrows\");" + NL + "        if (e.style.display == 'none'){" + NL + "          e.style.display = 'block';" + NL + "          f.innerHTML = '&#x25E2;';" + NL + "        } else {" + NL + "          e.style.display = 'none';" + NL + "          f.innerHTML = '&#x25B7;';" + NL + "        }" + NL + "      }" + NL + "" + NL + "    </script>" + NL + "" + NL + "    <header role=\"banner\" id=\"header-wrapper\">" + NL + "      <div class=\"container\">" + NL + "        <div class=\"row\" id=\"header-row\">" + NL + "          <div class=\"hidden-xs col-sm-8 col-md-6 col-lg-5\" id=\"header-left\">" + NL + "            <div class=\"wrapper-logo-default\">" + NL + "              <a href=\"";
  protected final String TEXT_8 = "\">" + NL + "                <img class=\"logo-eclipse-default img-responsive hidden-xs\" style=\"height: 50px; white-space: nowrap; display: inline-block;\" alt=\"Project Logo\" src=\"";
  protected final String TEXT_9 = "\"/>" + NL + "              </a>" + NL + "            </div>" + NL + "          </div>" + NL + "        </div>" + NL + "      </div>" + NL + "    </header>" + NL + "" + NL + "    <section class=\"hidden-print default-breadcrumbs\" id=\"breadcrumb\">" + NL + "      <div class=\"container\">" + NL + "        <h3 class=\"sr-only\">Breadcrumbs</h3>" + NL + "        <div class=\"row\">" + NL + "          <div class=\"col-sm-16 padding-left-30\">" + NL + "            <ol class=\"breadcrumb\">" + NL + "              <li><a href=\"https://www.eclipse.org/\">Home</a></li>" + NL + "              <li><a href=\"https://www.eclipse.org/projects/\">Projects</a></li>";
  protected final String TEXT_10 = NL + "              <li class=\"active\">";
  protected final String TEXT_11 = "</li>";
  protected final String TEXT_12 = NL + "              <li>";
  protected final String TEXT_13 = NL + "              <li><a href=\"";
  protected final String TEXT_14 = "\">";
  protected final String TEXT_15 = "</a></li>";
  protected final String TEXT_16 = NL + "            </ol>" + NL + "          </div>" + NL + "          <div class=\"col-sm-8 margin-top-15\"></div>" + NL + "        </div>" + NL + "      </div>" + NL + "    </section>" + NL + "" + NL + "    <main class=\"no-promo\">" + NL + "    <div class=\"novaContent container\" id=\"novaContent\">" + NL + "" + NL + "      <!-- navigation sidebar -->" + NL + "      <aside id=\"leftcol\" class=\"col-md-4\">" + NL + "        <ul id=\"leftnav\" class=\"ul-left-nav fa-ul hidden-print\">";
  protected final String TEXT_17 = NL + "          <li";
  protected final String TEXT_18 = " class=\"separator\"";
  protected final String TEXT_19 = ">";
  protected final String TEXT_20 = NL + "            <tt class=\"orange\">&#xbb;</tt>";
  protected final String TEXT_21 = NL + "            <a class=\"separator\" href=\"";
  protected final String TEXT_22 = "</a>";
  protected final String TEXT_23 = NL + "            <div style=\"float: right;\"><tt class=\"orange\">&#xab;</tt></div>";
  protected final String TEXT_24 = NL + "          </li>";
  protected final String TEXT_25 = NL + "        </ul>" + NL + "      </aside>" + NL + "" + NL + "      <div id=\"maincontent\">" + NL + "        <div id=\"midcolumn\">" + NL + "          <h2 style=\"text-align: center;\">";
  protected final String TEXT_26 = "</h2>";
  protected final String TEXT_27 = NL + "          <p style=\"font-size: 125%; text-align: center;\">" + NL + "            <b>Built: ";
  protected final String TEXT_28 = "</b>" + NL + "          </p>";
  protected final String TEXT_29 = NL + "          <p style=\"text-align: center;\">";
  protected final String TEXT_30 = NL + "            <button id=\"";
  protected final String TEXT_31 = "_arrows\" class=\"orange\" style=\"background-color: white; margin-left: 1em; border: none; padding: 0px 0px;\" onclick=\"expand_collapse('";
  protected final String TEXT_32 = "');\">&#x25B7;</button>";
  protected final String TEXT_33 = NL + "            <button title=\"Copy to Clipboard\" class=\"orange\" style=\"font-size: 150%; background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#p1')\">&#x270e;</button>" + NL + "            <span id=\"p1\" style=\"font-size: 125%\">";
  protected final String TEXT_34 = "</span>";
  protected final String TEXT_35 = "&nbsp;<a href=\"";
  protected final String TEXT_36 = "\" target=\"_blank\" style=\"font-size: 75%\">&#x1f9f0;</a>";
  protected final String TEXT_37 = NL + "            <span ";
  protected final String TEXT_38 = "style=\"display: none;\" ";
  protected final String TEXT_39 = "id=\"";
  protected final String TEXT_40 = NL + "            <br/>";
  protected final String TEXT_41 = NL + "            <button title=\"Copy to Clipboard\" class=\"orange\" style=\"font-size: 125%; margin-left: 1em; background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#";
  protected final String TEXT_42 = "')\">&#x270e;</button>" + NL + "            <a href=\"";
  protected final String TEXT_43 = "/index.html\" id=\"";
  protected final String TEXT_44 = "\" style=\"font-size: 100%; font-weight: normal;\">";
  protected final String TEXT_45 = NL + "            </span>" + NL + "          </p>";
  protected final String TEXT_46 = NL + "          <br/>" + NL + "          <img style=\"padding-left: .5em; float: right; min-height: 64px; max-height: 96pt;\" src=\"";
  protected final String TEXT_47 = "\" alt=\"\"/>";
  protected final String TEXT_48 = NL + "           <p>This is the root folder for ";
  protected final String TEXT_49 = " p2 update sites.</p>";
  protected final String TEXT_50 = NL + "           <p>These update sites are produced by <a href=\"";
  protected final String TEXT_51 = "\" target=\"_blank\">";
  protected final String TEXT_52 = "</a>.</p>";
  protected final String TEXT_53 = NL + "           <p>" + NL + "             This is a p2 update site for ";
  protected final String TEXT_54 = "." + NL + "             Use the <button title=\"Copy to Clipboard\" class=\"orange\" style=\"background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#p1')\">&#x270e;</button> icon to copy the update site URL to the system clipboard.";
  protected final String TEXT_55 = NL + "             Alternatively, you can download the archive of this repository from the download sidebar on the right.";
  protected final String TEXT_56 = NL + "           </p>";
  protected final String TEXT_57 = NL + "           <p>" + NL + "             This composite update site references the most recent successful nightly build." + NL + "           </p>";
  protected final String TEXT_58 = NL + "           <p>" + NL + "             This composite update site references the ";
  protected final String TEXT_59 = " most recent successful nightly build";
  protected final String TEXT_60 = "s";
  protected final String TEXT_61 = "." + NL + "           </p>";
  protected final String TEXT_62 = NL + "           <p>" + NL + "             This simple update site will be retained <b>only</b> until it is no longer one of the ";
  protected final String TEXT_63 = " most recent successful nightly builds." + NL + "           </p>";
  protected final String TEXT_64 = NL + "           <p>" + NL + "             This composite update site references the most recent successful milestone build." + NL + "           </p>";
  protected final String TEXT_65 = NL + "           <p>" + NL + "             This composite update site references ";
  protected final String TEXT_66 = " successful milestone build";
  protected final String TEXT_67 = "." + NL + "             These sites will be retained <b>only</b> until the first successful milestone build of the next ";
  protected final String TEXT_68 = " release." + NL + "           </p>";
  protected final String TEXT_69 = NL + "           <p>" + NL + "             This simple update site will be retained <b>only</b> until the first successful milestone build of the next ";
  protected final String TEXT_70 = NL + "           <p>" + NL + "             This composite update site references the most recent release of ";
  protected final String TEXT_71 = " release";
  protected final String TEXT_72 = "." + NL + "             These sites will be retained <b>permanently</b>." + NL + "           </p>";
  protected final String TEXT_73 = NL + "           <p>" + NL + "             This simple update site will be retained <b>permanently</b>." + NL + "           </p>";
  protected final String TEXT_74 = NL + "           <p>" + NL + "             This composite update site references the recommended build of version ";
  protected final String TEXT_75 = " for SimRel ";
  protected final String TEXT_76 = NL + "           <div>" + NL + "             This composite update site references ";
  protected final String TEXT_77 = " releases from the following location";
  protected final String TEXT_78 = ":" + NL + "           <input type=\"checkbox\" id=\"toggle-id-";
  protected final String TEXT_79 = "\" class=\"toggle\"/>" + NL + "           <label for=\"toggle-id-";
  protected final String TEXT_80 = "\" class=\"toggle-label\" style=\"font-size: 100%;\">";
  protected final String TEXT_81 = "</label>" + NL + "           <ul class=\"toggle-content\" style=\"margin-top: 2px; white-space: normal; list-style-type: none;\">";
  protected final String TEXT_82 = NL + "             <li><a href=\"";
  protected final String TEXT_83 = "/index.html\">";
  protected final String TEXT_84 = NL + "           </ul>" + NL + "           </div>";
  protected final String TEXT_85 = NL + "           <p>" + NL + "             This type of repo should not exist." + NL + "           </p>";
  protected final String TEXT_86 = NL + "           <h3>";
  protected final String TEXT_87 = " SDKs</h3>" + NL + "           <p>This update site provides access to the following features:</p>" + NL + "           <ul>";
  protected final String TEXT_88 = NL + "             <li style=\"font-size: 90%;\">";
  protected final String TEXT_89 = NL + "           </ul>";
  protected final String TEXT_90 = NL + "           <p>" + NL + "             The update sites listed below provide access to specific versions of the features listed above.";
  protected final String TEXT_91 = NL + "             Use the <button title=\"Copy to Clipboard\" class=\"orange\" style=\"background-color: white; border: none; padding: 0px 0px;\">&#x270e;</button> icon to copy the update site URL to the system clipboard.";
  protected final String TEXT_92 = NL + "           <h3><a href=\"";
  protected final String TEXT_93 = "\" style=\"font-weight: normal;\">";
  protected final String TEXT_94 = "</a></h3>" + NL + "           <p>The following is the p2 update site URL for this repository:</p>" + NL + "           <p style=\"margin-left: 1em\">" + NL + "             <button title=\"Copy to Clipboard\" class=\"orange\" style=\"font-size: 125%; background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#";
  protected final String TEXT_95 = "')\">&#x270e;</button>" + NL + "             <a href=\"";
  protected final String TEXT_96 = "\" id=\"";
  protected final String TEXT_97 = "</a>" + NL + "           </p>" + NL + "           <p>Click the header link or the update site link for more details.</p>";
  protected final String TEXT_98 = NL + "           <p>" + NL + "             This update site's source code is generated from" + NL + "             <a href=\"";
  protected final String TEXT_99 = "\" target=\"model\">";
  protected final String TEXT_100 = "<img style=\"height: 2ex; white-space: nowrap; display: inline-block;\" alt=\"justj\" src=\"https://eclipse.dev/justj/justj_title.svg\"/>";
  protected final String TEXT_101 = "</a>." + NL + "           </p>";
  protected final String TEXT_102 = " Features</h3>" + NL + "           <p>" + NL + "             This update site provides access to the following features." + NL + "             Each section is expandable via";
  protected final String TEXT_103 = NL + "             <span style=\"white-space: nowrap;\">the&nbsp;<button id=\"all_features\" title=\"Expand All\" class=\"orange\" style=\"background-color: white; border: none; padding: 0px 0px;\" onclick=\"toggle('all_features');";
  protected final String TEXT_104 = "\">&#x25B7;</button>&nbsp;icon</span>";
  protected final String TEXT_105 = NL + "             to provide detailed information about the requirements of each feature." + NL + "           </p>" + NL + "           <ul style=\"margin-left: -1em; list-style-type: none; padding: 0; margin: 0;\">";
  protected final String TEXT_106 = NL + "             <li style=\"font-size: 90%;\">" + NL + "              <button id=\"";
  protected final String TEXT_107 = "');\">&#x25B7;</button>" + NL + "              <span style=\"font-size:100%;\">";
  protected final String TEXT_108 = "<b>";
  protected final String TEXT_109 = "</b>";
  protected final String TEXT_110 = "</span>" + NL + "              <div id=\"";
  protected final String TEXT_111 = "\" style=\"display:none; margin-left: 2em; white-space: nowrap;\">";
  protected final String TEXT_112 = NL + "                ";
  protected final String TEXT_113 = "<br/>";
  protected final String TEXT_114 = NL + "              </div>" + NL + "             </li>";
  protected final String TEXT_115 = " Products</h3>" + NL + "           <p>" + NL + "             This update site provides access to the packaged products listed below." + NL + "           </p>" + NL + "           <ul style=\"list-style-type: none; padding-left: 1em;\">";
  protected final String TEXT_116 = NL + "           <li>" + NL + "             <a href=\"";
  protected final String TEXT_117 = "</a>" + NL + "             <a class=\"btn btn-warning\" style=\"border-color: DarkGreen; background-color: OliveDrab; font-size: 60%; margin-left: 0.5em; margin-top: -0.2em; padding: 0.2em;\" href=\"";
  protected final String TEXT_118 = ".sha512\" target=\"_blank\" title=\"Download ";
  protected final String TEXT_119 = ".sha512\">sha512</a>" + NL + "           </li>";
  protected final String TEXT_120 = " Downloads</h3>" + NL + "           <p>" + NL + "             This update site provides access to the download artifacts listed below." + NL + "           </p>" + NL + "           <ul style=\"list-style-type: none; padding-left: 1em;\">";
  protected final String TEXT_121 = NL + "           <li style=\"font-size: 90%;\">" + NL + "             <a href=\"";
  protected final String TEXT_122 = "\" target=\"_blank\" title=\"Download ";
  protected final String TEXT_123 = " Bundles</h3>" + NL + "           <p>" + NL + "             This update site provides access to the bundles listed below." + NL + "             Each section is expandable via";
  protected final String TEXT_124 = NL + "             <span style=\"white-space: nowrap;\">the&nbsp;<button id=\"all\" title=\"Expand All\" class=\"orange\" style=\"background-color: white; border: none; padding: 0px 0px;\" onclick=\"toggle('all');";
  protected final String TEXT_125 = NL + "             to provide detailed information about the exports and imports of each bundle." + NL + "           </p>" + NL + "           <ul>";
  protected final String TEXT_126 = NL + "             <li>" + NL + "               The &#x21D3; icon denotes a link to the Maven Central source of the bundle. " + NL + "             </li>";
  protected final String TEXT_127 = NL + "             <li>" + NL + "               The &#x21D6; icon denotes the exported bundle identifier along with its full version." + NL + "             </li>" + NL + "             <li>" + NL + "               The &#x2196; icon denotes an exported java package along with its full version, if specified." + NL + "             </li>" + NL + "             <li>" + NL + "               The &#x21D8; icon denotes a required bundle along with its version range, if specified, as well as information about whether the requirement is optional and if so, whether it's greedy." + NL + "             </li>" + NL + "             <li>" + NL + "               The &#x2198; icon denotes a required package along with its version range, if specified." + NL + "             </li>" + NL + "           </ul>" + NL + "" + NL + "          <ul style=\"margin-left: -1em; list-style-type: none; padding: 0; margin: 0;\">";
  protected final String TEXT_128 = NL + "            <li style=\"font-size: 90%;\">" + NL + "              <button id=\"";
  protected final String TEXT_129 = "&nbsp;&nbsp;<";
  protected final String TEXT_130 = " style=\"color: SteelBlue;\">";
  protected final String TEXT_131 = "</";
  protected final String TEXT_132 = NL + "         <input type=\"checkbox\" id=\"toggle-id-";
  protected final String TEXT_133 = "\" class=\"toggle\"/>" + NL + "         <label for=\"toggle-id-";
  protected final String TEXT_134 = " Properties</label>" + NL + "         <ul class=\"toggle-content\" style=\"margin-top: 0px; white-space: normal;\">";
  protected final String TEXT_135 = NL + "           <li>";
  protected final String TEXT_136 = NL + "             ";
  protected final String TEXT_137 = " = ";
  protected final String TEXT_138 = NL + "           </li>";
  protected final String TEXT_139 = NL + "           <li>" + NL + "             <input type=\"checkbox\" id=\"toggle-id-";
  protected final String TEXT_140 = "\" class=\"toggle\"/>" + NL + "             <label for=\"toggle-id-";
  protected final String TEXT_141 = "\" class=\"toggle-label\">";
  protected final String TEXT_142 = " ";
  protected final String TEXT_143 = "</label>" + NL + "             <ul class=\"toggle-content\" style=\"margin-top: 0px;\">";
  protected final String TEXT_144 = NL + "               <li>";
  protected final String TEXT_145 = NL + "                 ";
  protected final String TEXT_146 = NL + "               </li>";
  protected final String TEXT_147 = NL + "             </ul>" + NL + "            </li>";
  protected final String TEXT_148 = NL + "         </ul>";
  protected final String TEXT_149 = NL + "              </div>" + NL + "            </li>";
  protected final String TEXT_150 = NL + NL + "        </div>";
  protected final String TEXT_151 = NL + "        <div style=\"padding-top: 100;\" id=\"rightcolumn\">" + NL + "          <div class=\"sideitem\">" + NL + "            <h6>Downloads</h6>" + NL + "            <p>" + NL + "              <a style=\"font-size: 75%; font-weight: bold;\" href=\"";
  protected final String TEXT_152 = "</a>" + NL + "              <br/>" + NL + "              <a class=\"btn btn-warning\" style=\"margin-top: 5pt; font-size: 90%;\" href=\"";
  protected final String TEXT_153 = "\">Download</a>" + NL + "              <a class=\"btn btn-warning\" style=\"margin-top: 5pt; border-color: DarkGreen; background-color: OliveDrab; font-size: 90%; padding-left: 0.2em; padding-right: 0.2em;\" href=\"";
  protected final String TEXT_154 = "\">sha256</a>" + NL + "              <a class=\"btn btn-warning\" style=\"margin-top: 5pt; border-color: DarkGreen; background-color: OliveDrab; font-size: 90%; padding-left: 0.2em; padding-right: 0.2em;\" href=\"";
  protected final String TEXT_155 = "\">sha512</a>" + NL + "            </p>" + NL + "          </div>";
  protected final String TEXT_156 = NL + "          <div class=\"sideitem\">" + NL + "            <h6>Git Commits</h6>";
  protected final String TEXT_157 = NL + "            <p>" + NL + "              <button title=\"Copy Commit ID ";
  protected final String TEXT_158 = " to Clipboard\" class=\"orange\" style=\"font-size: 150%; background-color: transparent; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#";
  protected final String TEXT_159 = "_commit_id')\">&#x270e;</button>" + NL + "              <a style=\"font-size: 100%;\" href=\"";
  protected final String TEXT_160 = "\" target=\"";
  protected final String TEXT_161 = "_git\">";
  protected final String TEXT_162 = "</a>" + NL + "              <br/>" + NL + "              <tt><a id=\"";
  protected final String TEXT_163 = "_commit_id\" class=\"btn btn-warning\" style=\"text-transform: none; margin-top: 5pt; border-color: DarkGreen; background-color: OliveDrab; font-size: 70%; padding-top: 0.2ex; padding-bottom: 0.2ex; padding-left: 0.2em; padding-right: 0.2em;\" href=\"";
  protected final String TEXT_164 = "_git_commit\" title=\"";
  protected final String TEXT_165 = " Git Commit ID\">";
  protected final String TEXT_166 = "</a></tt>" + NL + "            </p>";
  protected final String TEXT_167 = NL + "          </div>";
  protected final String TEXT_168 = NL + "        </div>" + NL;
  protected final String TEXT_169 = NL + "       </div>" + NL + "     </div>" + NL + "     </main>" + NL + "  </body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    UpdateSiteIndexGenerator parent = (UpdateSiteIndexGenerator)argument;
  List<UpdateSiteIndexGenerator> children = parent.getChildren();
  String title = parent.getTitle();
  String toggleExpand = "<span style=\"font-size: 100%;\" class=\"orange toggle-state-on\">&#x25E2;</span>";
  String toggleCollapse = "<span style=\"font-size: 100%;\" class=\"orange toggle-state-off\">&#x25B7;</span>";
  int toggleCounter = 0;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(title);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(title.toLowerCase());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(title);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(parent.getFavicon());
    stringBuffer.append(TEXT_5);
    if (parent.getChildren().isEmpty() || !parent.getSuperCompositeChildren().isEmpty()) {
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    stringBuffer.append(title);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(parent.getTitleImage());
    stringBuffer.append(TEXT_9);
    for (Map.Entry<String, String> entry : parent.getBreadcrumbs(true).entrySet()) {
    if (entry.getValue() == null) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(entry.getKey());
    stringBuffer.append(TEXT_11);
    } else if (entry.getValue().trim().isEmpty()) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(entry.getKey());
    stringBuffer.append(TEXT_11);
    } else {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(entry.getValue());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(entry.getKey());
    stringBuffer.append(TEXT_15);
    }
    }
    stringBuffer.append(TEXT_16);
    for (Map.Entry<String, String> entry : parent.getNavigation().entrySet()) {
    String label = entry.getValue();
    boolean isTopLevel = !label.startsWith("-");
    if (!isTopLevel)
      label = label.substring(1);
    int index = label.indexOf('@');
    if (index != -1)
      label = label.substring(0, label.length() - 1);
    stringBuffer.append(TEXT_17);
    if (isTopLevel) {
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    if (!isTopLevel) {
    stringBuffer.append(TEXT_20);
    }
    stringBuffer.append(TEXT_21);
    stringBuffer.append(entry.getKey());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_22);
    if (index != -1) {
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    stringBuffer.append(title);
    stringBuffer.append(TEXT_26);
    if (children.isEmpty()) {
    String date = parent.getDate();
    if (date != null) {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(date);
    stringBuffer.append(TEXT_28);
    }
    }
    if (!parent.isRoot()) {
    String mainID = "main-list";
    Map<String, String> repositories = parent.getRepositoryChildren();
    String parentSiteURL = parent.getSiteURL();
    String rawSiteURL = parentSiteURL.startsWith("https://download.eclipse.org/")
      ? "https://download.eclipse.org/justj?file=" + parentSiteURL.substring("https://download.eclipse.org".length() + 1) : null;
    boolean hasTwisty = repositories != null && repositories.size() > 1;
    stringBuffer.append(TEXT_29);
    if (hasTwisty) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(mainID);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(mainID);
    stringBuffer.append(TEXT_32);
    }
    stringBuffer.append(TEXT_33);
    stringBuffer.append(parentSiteURL);
    stringBuffer.append(TEXT_34);
    if (rawSiteURL != null) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(rawSiteURL);
    stringBuffer.append(TEXT_36);
    }
    stringBuffer.append(TEXT_37);
    if (hasTwisty) {
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    stringBuffer.append(mainID);
    stringBuffer.append(TEXT_14);
    if (repositories != null) {
      int count = 0;
      for (Map.Entry<String, String> entry : repositories.entrySet()) {
        String repository = entry.getKey();
        String relativeURL = entry.getValue();
        String id = parent.getFolderID(repository);
    if (count++ != 0 || !hasTwisty) {
    stringBuffer.append(TEXT_40);
    }
    stringBuffer.append(TEXT_41);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(relativeURL);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(repository);
    stringBuffer.append(TEXT_22);
    }
    }
    stringBuffer.append(TEXT_45);
    }
    if (parent.getBodyImage() != null) {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(parent.getBodyImage());
    stringBuffer.append(TEXT_47);
    }
    if (parent.isRoot()) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_49);
    if (parent.getBuildURL() != null) {
    stringBuffer.append(TEXT_50);
    stringBuffer.append(parent.getBuildURL());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(parent.getBuildURL());
    stringBuffer.append(TEXT_52);
    }
    } else {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_54);
    if (parent.hasArchive()) {
    stringBuffer.append(TEXT_55);
    }
    stringBuffer.append(TEXT_56);
    {
      String buildType = parent.getBuildType();
      boolean isLatest = parent.isLatest();
      Map<String, String> repositoryChildren = parent.getRepositoryChildren();
      int repositoryChildrenSize = repositoryChildren == null ? 0 : repositoryChildren.size();
      if ("nightly".equals(buildType)) {
    if (isLatest) {
    stringBuffer.append(TEXT_57);
    } else if (repositoryChildren != null) {
    stringBuffer.append(TEXT_58);
    stringBuffer.append(repositoryChildrenSize);
    stringBuffer.append(TEXT_59);
    if (repositoryChildrenSize != 1) {
    stringBuffer.append(TEXT_60);
    }
    stringBuffer.append(TEXT_61);
    } else {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(parent.getRetainedNightlyBuilds());
    stringBuffer.append(TEXT_63);
    }
    } else if ("milestone".equals(buildType)) {
    if (isLatest) {
    stringBuffer.append(TEXT_64);
    } else if (repositoryChildren != null) {
    stringBuffer.append(TEXT_65);
    stringBuffer.append(repositoryChildrenSize);
    stringBuffer.append(TEXT_66);
    if (repositoryChildrenSize != 1) {
    stringBuffer.append(TEXT_60);
    }
    stringBuffer.append(TEXT_67);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_68);
    } else {
    stringBuffer.append(TEXT_69);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_68);
    }
    } else if ("release".equals(buildType)) {
    if (isLatest) {
    stringBuffer.append(TEXT_70);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_61);
    } else if (repositoryChildren != null) {
    stringBuffer.append(TEXT_65);
    stringBuffer.append(repositoryChildrenSize);
    stringBuffer.append(TEXT_71);
    if (repositoryChildrenSize != 1) {
    stringBuffer.append(TEXT_60);
    }
    stringBuffer.append(TEXT_72);
    } else {
    stringBuffer.append(TEXT_73);
    }
    } else if (buildType.matches("[0-9]+-(03|06|09|12)")) {
    stringBuffer.append(TEXT_74);
    stringBuffer.append(parent.getVersion());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(buildType);
    stringBuffer.append(TEXT_61);
    } else {
    if (isLatest) {
    stringBuffer.append(TEXT_70);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_61);
    } else if (repositoryChildren != null || !parent.getSuperCompositeChildren().isEmpty()) {
          int mainID = ++toggleCounter;
    stringBuffer.append(TEXT_76);
    stringBuffer.append(repositoryChildrenSize);
    stringBuffer.append(TEXT_77);
    if (repositoryChildrenSize != 1) {
    stringBuffer.append(TEXT_60);
    }
    stringBuffer.append(TEXT_78);
    stringBuffer.append(mainID);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(mainID);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(toggleExpand);
    stringBuffer.append(toggleCollapse);
    stringBuffer.append(TEXT_81);
    for (UpdateSiteIndexGenerator child : parent.getSuperCompositeChildren()) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(child.getSiteURL());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(child.getLabel());
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_84);
    } else {
    stringBuffer.append(TEXT_85);
    }
    }
    }
    }
    if (!children.isEmpty()) {
    List<String> sdks = parent.getPrimaryFeatures();
    if (!sdks.isEmpty()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_87);
    for (String sdk : sdks) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(sdk);
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_89);
    }
    stringBuffer.append(TEXT_90);
    if (parent.isRoot()) {
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_56);
    for (UpdateSiteIndexGenerator child : children) {
      String id = parent.getFolderID(child.getFolderName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(child.getRelativeIndexURL());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(child.getTitle());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(child.getRelativeIndexURL());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(child.getSiteURL());
    stringBuffer.append(TEXT_97);
    }
    } else {
    String resourceURL = parent.getResourceURL();
    if (resourceURL != null) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(resourceURL);
    stringBuffer.append(TEXT_99);
    if (resourceURL.startsWith("justj.")) {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(resourceURL.substring(5));
    } else {
    stringBuffer.append(resourceURL);
    }
    stringBuffer.append(TEXT_101);
    }
    Map<String, List<String>> features = parent.getFeatures();
    if (!features.isEmpty()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_102);
    {
        StringBuilder onClick = new StringBuilder();
        for (String feature : features.keySet()) {
          onClick.append("expand('").append(parent.getFolderID(feature) + "_feature").append("', 'all_features');");
        }
    stringBuffer.append(TEXT_103);
    stringBuffer.append(onClick);
    stringBuffer.append(TEXT_104);
    }
    stringBuffer.append(TEXT_105);
    for (Map.Entry<String, List<String>> entry : features.entrySet()) {
        String feature = entry.getKey();
        String id = parent.getFolderID(feature) + "_feature";
    stringBuffer.append(TEXT_106);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_107);
    if (parent.isPrimary(feature)) {
    stringBuffer.append(TEXT_108);
    }
    stringBuffer.append(feature.replace(" ", "&nbsp;"));
    if (parent.isPrimary(feature)) {
    stringBuffer.append(TEXT_109);
    }
    stringBuffer.append(TEXT_110);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_111);
    for (String line : entry.getValue()) {
    stringBuffer.append(TEXT_112);
    stringBuffer.append(line);
    stringBuffer.append(TEXT_113);
    }
    stringBuffer.append(TEXT_114);
    }
    stringBuffer.append(TEXT_89);
    }
    {
      List<String> products = parent.getProducts();
      if (!products.isEmpty()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_115);
    for (String product : products) {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(parent.getProductDownloadURI(product));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(URI.decode(product));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(product);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(product);
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_89);
    }
    }
    {
      List<String> downloads = parent.getDownloads();
      if (!downloads.isEmpty()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_120);
    for (String download : downloads) {
    stringBuffer.append(TEXT_121);
    stringBuffer.append(parent.getArtifactDownloadURI(download));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(URI.decode(download));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(parent.getArtifactSHA512URI(download));
    stringBuffer.append(TEXT_122);
    stringBuffer.append(download);
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_89);
    }
    }
    Map<String, List<String>> bundles = parent.getBundles();
    boolean hasMavenLinks = bundles.values().stream().flatMap(Collection::stream).anyMatch(it -> it.contains("\u21d3"));
    if (!bundles.isEmpty()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_123);
    {
        StringBuilder onClick = new StringBuilder();
        for (String bundle : bundles.keySet()) {
          onClick.append("expand('").append(parent.getFolderID(bundle)).append("', 'all');");
        }
    stringBuffer.append(TEXT_124);
    stringBuffer.append(onClick);
    stringBuffer.append(TEXT_104);
    }
    stringBuffer.append(TEXT_125);
    if (hasMavenLinks) {
    stringBuffer.append(TEXT_126);
    }
    stringBuffer.append(TEXT_127);
    for (Map.Entry<String, List<String>> entry : bundles.entrySet()) {
        String bundle = entry.getKey();
        String bundleSize = parent.getBundleSize(bundle);
        String xmlTag = bundleSize.endsWith("MB") ? "b" : "span";
        String id = parent.getFolderID(bundle);
        List<Property> properties = parent.getProperties(bundle);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(bundle.replace(" ", "&nbsp;"));
    if (!bundleSize.isEmpty()) {
    stringBuffer.append(TEXT_129);
    stringBuffer.append(xmlTag);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(bundleSize);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(xmlTag);
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(TEXT_110);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_111);
    if (!properties.isEmpty()) {
          int mainID = ++toggleCounter;
    stringBuffer.append(TEXT_132);
    stringBuffer.append(mainID);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(mainID);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(toggleExpand);
    stringBuffer.append(toggleCollapse);
    stringBuffer.append(TEXT_134);
    for (Property property : properties) {
            List<String> propertyChildren = property.getChildren();
            if (propertyChildren.isEmpty()) {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(property.getKey());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(property.getValue());
    stringBuffer.append(TEXT_138);
    } else {
              int valueID = ++toggleCounter;
    stringBuffer.append(TEXT_139);
    stringBuffer.append(valueID);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(valueID);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(toggleExpand);
    stringBuffer.append(toggleCollapse);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(property.getKey());
    stringBuffer.append(TEXT_143);
    for (String value : propertyChildren) {
                valueID = ++toggleCounter;
    stringBuffer.append(TEXT_144);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_146);
    }
    stringBuffer.append(TEXT_147);
    }
    }
    stringBuffer.append(TEXT_148);
    }
    for (String line : entry.getValue()) {
    stringBuffer.append(TEXT_112);
    stringBuffer.append(line);
    stringBuffer.append(TEXT_113);
    }
    stringBuffer.append(TEXT_149);
    }
    stringBuffer.append(TEXT_89);
    }
    }
    stringBuffer.append(TEXT_150);
    if (parent.hasArchive()) {
    String archiveName = new File(parent.getArchive()).getName();
    String sha256 = parent.getDigest("SHA-256");
    String sha256Name = new File(sha256).getName();
    String sha512 = parent.getDigest("SHA-512");
    String sha512Name = new File(sha512).getName();
    stringBuffer.append(TEXT_151);
    stringBuffer.append(parent.getArchiveDownloadURL());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(archiveName);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(parent.getArchiveDownloadURL());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(archiveName);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(sha256);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(sha256Name);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(sha512);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(sha512Name);
    stringBuffer.append(TEXT_155);
    {
      Map<String, String> commits = parent.getCommits();
      if (!commits.isEmpty()) {
    stringBuffer.append(TEXT_156);
    for (Map.Entry<String, String> entry : commits.entrySet()) {
          String label = entry.getKey();
          String url = entry.getValue();
          String gitRepoURL = parent.getCommitsURL(url);
          String id = parent.getCommitID(url);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_159);
    stringBuffer.append(gitRepoURL);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(url);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_166);
    }
    stringBuffer.append(TEXT_167);
    }
    }
    stringBuffer.append(TEXT_168);
    }
    stringBuffer.append(TEXT_169);
    return stringBuffer.toString();
  }
}
