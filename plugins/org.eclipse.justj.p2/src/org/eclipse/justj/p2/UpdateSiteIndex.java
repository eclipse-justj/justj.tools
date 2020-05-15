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
  protected final String TEXT_2 = "%></title>" + NL + "  <meta name=\"keywords\" content=\"eclipse,";
  protected final String TEXT_3 = "\"/>" + NL + "  <meta name=\"description\" content=\"";
  protected final String TEXT_4 = " p2 Update Sites\"/>" + NL + "  <link href=\"//fonts.googleapis.com/css?family=Open+Sans:400,700,300,600,100\" rel=\"stylesheet\" type=\"text/css\"/>" + NL + "  <link rel=\"stylesheet\" href=\"https://www.eclipse.org/eclipse.org-common/themes/solstice/public/stylesheets/styles.min.css\"/>" + NL + "  <link rel=\"icon\" type=\"image/ico\" href=\"";
  protected final String TEXT_5 = "\"/>" + NL + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>" + NL + "</head>" + NL + "" + NL + "  <body id=\"body_solstice\">" + NL + "    <script>" + NL + "" + NL + "      function copyToClipboard(element) {" + NL + "        var $temp = $(\"<input>\");" + NL + "        $(\"body\").append($temp);" + NL + "        $temp.val($(element).text()).select();" + NL + "        document.execCommand(\"copy\");" + NL + "        $temp.remove();" + NL + "      }" + NL + "" + NL + "      function toggle(id) {" + NL + "        var e = document.getElementById(id);" + NL + "        if (e.title == 'Expand All') {" + NL + "          e.title= 'Collapse All';" + NL + "          e.innerHTML = '&#x25E2;';" + NL + "        } else {" + NL + "          e.title= 'Expand All';" + NL + "          e.innerHTML = '&#x25B7;';" + NL + "        }" + NL + "      }" + NL + "" + NL + "      function expand(id) {" + NL + "        var t = document.getElementById('all');" + NL + "        var e = document.getElementById(id);" + NL + "        var f = document.getElementById(id+\"_arrows\");" + NL + "        if (t.title == 'Collapse All'){" + NL + "          e.style.display = 'block';" + NL + "          f.innerHTML = '&#x25E2;';" + NL + "        } else {" + NL + "          e.style.display = 'none';" + NL + "          f.innerHTML = '&#x25B7;';" + NL + "        }" + NL + "      }" + NL + "" + NL + "      function expand_collapse(id) {" + NL + "        var e = document.getElementById(id);" + NL + "        var f = document.getElementById(id+\"_arrows\");" + NL + "        if (e.style.display == 'none'){" + NL + "          e.style.display = 'block';" + NL + "          f.innerHTML = '&#x25E2;';" + NL + "        } else {" + NL + "          e.style.display = 'none';" + NL + "          f.innerHTML = '&#x25B7;';" + NL + "        }" + NL + "      }" + NL + "" + NL + "    </script>" + NL + "" + NL + "    <header role=\"banner\" id=\"header-wrapper\">" + NL + "      <div class=\"container\">" + NL + "        <div class=\"row\" id=\"header-row\">" + NL + "          <div class=\"hidden-xs col-sm-8 col-md-6 col-lg-5\" id=\"header-left\">" + NL + "            <div class=\"wrapper-logo-default\">" + NL + "              <a href=\"";
  protected final String TEXT_6 = "\">" + NL + "                <img class=\"logo-eclipse-default img-responsive hidden-xs\" style=\"height: 50px; space: nowrap; display: inline-block;\" alt=\"Project Logo\" src=\"";
  protected final String TEXT_7 = "\"/>" + NL + "              </a>" + NL + "            </div>" + NL + "          </div>" + NL + "        </div>" + NL + "      </div>" + NL + "    </header>" + NL + "" + NL + "    <section class=\"hidden-print default-breadcrumbs\" id=\"breadcrumb\">" + NL + "      <div class=\"container\">" + NL + "        <h3 class=\"sr-only\">Breadcrumbs</h3>" + NL + "        <div class=\"row\">" + NL + "          <div class=\"col-sm-16 padding-left-30\">" + NL + "            <ol class=\"breadcrumb\">" + NL + "              <li><a href=\"https://www.eclipse.org/\">Home</a></li>" + NL + "              <li><a href=\"https://www.eclipse.org/projects/\">Projects</a></li>";
  protected final String TEXT_8 = NL + "              <li class=\"active\">";
  protected final String TEXT_9 = "</li>";
  protected final String TEXT_10 = NL + "              <li>";
  protected final String TEXT_11 = NL + "              <li><a href=\"";
  protected final String TEXT_12 = "\">";
  protected final String TEXT_13 = "</a></li>";
  protected final String TEXT_14 = NL + "            </ol>" + NL + "          </div>" + NL + "          <div class=\"col-sm-8 margin-top-15\"></div>" + NL + "        </div>" + NL + "      </div>" + NL + "    </section>" + NL + "" + NL + "    <main class=\"no-promo\">" + NL + "    <div class=\"novaContent container\" id=\"novaContent\">" + NL + "" + NL + "      <!-- navigation sidebar -->" + NL + "      <aside id=\"leftcol\" class=\"col-md-4\">" + NL + "        <ul id=\"leftnav\" class=\"ul-left-nav fa-ul hidden-print\">";
  protected final String TEXT_15 = NL + "          <li";
  protected final String TEXT_16 = " class=\"separator\"";
  protected final String TEXT_17 = ">";
  protected final String TEXT_18 = NL + "            <tt class=\"orange\">&#xbb;</tt>";
  protected final String TEXT_19 = NL + "            <a class=\"separator\" href=\"";
  protected final String TEXT_20 = "/index.html";
  protected final String TEXT_21 = "</a>";
  protected final String TEXT_22 = NL + "            <div style=\"float: right;\"><tt class=\"orange\">&#xab;</tt></div>";
  protected final String TEXT_23 = NL + "          </li>";
  protected final String TEXT_24 = NL + "        </ul>" + NL + "      </aside>" + NL + "" + NL + "      <div id=\"maincontent\">" + NL + "        <div id=\"midcolumn\">" + NL + "          <h2 style=\"text-align: center;\">";
  protected final String TEXT_25 = "</h2>";
  protected final String TEXT_26 = NL + "          <p style=\"font-size: 125%; text-align: center;\">" + NL + "            <b>Built: ";
  protected final String TEXT_27 = "</b>" + NL + "          </p>";
  protected final String TEXT_28 = NL + "          <p style=\"text-align: center;\">";
  protected final String TEXT_29 = NL + "            <button id=\"";
  protected final String TEXT_30 = "_arrows\" class=\"orange\" style=\"background-color: white; margin-left: 1em; border: none; padding: 0px 0px;\" onclick=\"expand_collapse('";
  protected final String TEXT_31 = "');\">&#x25B7;</button>";
  protected final String TEXT_32 = NL + "            <button title=\"Copy to Clipboard\" class=\"orange\" style=\"font-size: 150%; background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#p1')\">&#x270e;</button>" + NL + "            <span id=\"p1\" style=\"font-size: 125%\">";
  protected final String TEXT_33 = "</span>" + NL + "            <span ";
  protected final String TEXT_34 = "style=\"display: none;\" ";
  protected final String TEXT_35 = "id=\"";
  protected final String TEXT_36 = NL + "            <br/>";
  protected final String TEXT_37 = NL + "            <button title=\"Copy to Clipboard\" class=\"orange\" style=\"font-size: 125%; margin-left: 1em; background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#";
  protected final String TEXT_38 = "')\">&#x270e;</button>" + NL + "            <a href=\"";
  protected final String TEXT_39 = "/index.html\" id=\"";
  protected final String TEXT_40 = "\" style=\"font-size: 100%; font-weight: normal;\">";
  protected final String TEXT_41 = NL + "            </span>" + NL + "          </p>";
  protected final String TEXT_42 = NL + "          <br/>" + NL + "          <img style=\"float: right; height: 96pt;\" src=\"";
  protected final String TEXT_43 = "\" alt=\"\"/>";
  protected final String TEXT_44 = NL + "           <p>This is the root folder for ";
  protected final String TEXT_45 = " p2 update sites.</p>";
  protected final String TEXT_46 = NL + "           <p>These update sites are produced by <a href=\"";
  protected final String TEXT_47 = "\" target=\"_blank\">";
  protected final String TEXT_48 = "</a>.</p>";
  protected final String TEXT_49 = NL + "           <p>" + NL + "             This is a p2 update site for the ";
  protected final String TEXT_50 = "." + NL + "             Use the <button title=\"Copy to Clipboard\" class=\"orange\" style=\"background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#p1')\">&#x270e;</button> glyph to copy the update site URL to the system clipboard.";
  protected final String TEXT_51 = NL + "             Alternatively, you can download the archive of this repository from the download sidebar on the right.";
  protected final String TEXT_52 = NL + "           </p>";
  protected final String TEXT_53 = NL + "           <p>" + NL + "             This composite update site references the most recent successful nightly build." + NL + "           </p>";
  protected final String TEXT_54 = NL + "           <p>" + NL + "             This composite update site references the ";
  protected final String TEXT_55 = " most recent successful nightly builds." + NL + "           </p>";
  protected final String TEXT_56 = NL + "           <p>" + NL + "             This simple update site will be retained <b>only</b> until it is no longer one of the ";
  protected final String TEXT_57 = NL + "           <p>" + NL + "             This composite update site references the most recent successful milestone build." + NL + "           </p>";
  protected final String TEXT_58 = NL + "           <p>" + NL + "             This composite update site references ";
  protected final String TEXT_59 = " successful milestone builds." + NL + "             These sites will be retained <b>only</b> until the first successful milestone build of the next ";
  protected final String TEXT_60 = " release." + NL + "           </p>";
  protected final String TEXT_61 = NL + "           <p>" + NL + "             This simple update site will be retained <b>only</b> until the first successful milestone build of the next ";
  protected final String TEXT_62 = NL + "           <p>" + NL + "             This composite update site references the most recent release of ";
  protected final String TEXT_63 = "." + NL + "           </p>";
  protected final String TEXT_64 = " releases." + NL + "             These sites will be retained <b>permanently</b>." + NL + "           </p>";
  protected final String TEXT_65 = NL + "           <p>" + NL + "             This simple update site will be retained <b>permanently</b>." + NL + "           </p>";
  protected final String TEXT_66 = NL + "           <p>This update site provides access to the following features:</p>";
  protected final String TEXT_67 = NL + "           <ul>";
  protected final String TEXT_68 = NL + "             <li style=\"font-size: 90%;\">";
  protected final String TEXT_69 = NL + "           </ul>";
  protected final String TEXT_70 = NL + "           <p>" + NL + "             The update sites listed below provide access to specific versions of the features listed above.";
  protected final String TEXT_71 = NL + "             Use the <button title=\"Copy to Clipboard\" class=\"orange\" style=\"background-color: white; border: none; padding: 0px 0px;\">&#x270e;</button> glyph to copy the update site URL to the system clipboard.";
  protected final String TEXT_72 = NL + "           <h3><a href=\"";
  protected final String TEXT_73 = "\" style=\"font-weight: normal;\">";
  protected final String TEXT_74 = "</a></h3>" + NL + "           <p>The following is the p2 update site URL for this repository:</p>" + NL + "           <p style=\"margin-left: 1em\">" + NL + "             <button title=\"Copy to Clipboard\" class=\"orange\" style=\"font-size: 125%; background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#";
  protected final String TEXT_75 = "')\">&#x270e;</button>" + NL + "             <a href=\"";
  protected final String TEXT_76 = "\" id=\"";
  protected final String TEXT_77 = "</a>" + NL + "           </p>" + NL + "           <p>Click the header link or the update site link for more details.</p>";
  protected final String TEXT_78 = NL + "          <ul style=\"margin-left: -1em\">";
  protected final String TEXT_79 = "<b>";
  protected final String TEXT_80 = "<li style=\"font-size: 90%;\">";
  protected final String TEXT_81 = "</b>";
  protected final String TEXT_82 = NL + "          </ul>";
  protected final String TEXT_83 = NL + "           <h3>";
  protected final String TEXT_84 = " Bundles</h3>" + NL + "           <p>" + NL + "             This update site provides access to the bundles listed below." + NL + "             Each section is expandable via";
  protected final String TEXT_85 = NL + "             <span style=\"white-space: nowrap;\">the&nbsp;<button id=\"all\" title=\"Expand All\" class=\"orange\" style=\"background-color: white; border: none; padding: 0px 0px;\" onclick=\"toggle('all');";
  protected final String TEXT_86 = "\">&#x25B7;</button>&nbsp;glyph</span>";
  protected final String TEXT_87 = NL + "             to provide detailed information about the exports and imports of each bundle." + NL + "           </p>" + NL + "           <ul>" + NL + "             <li>" + NL + "               The &#x21D6; glyph denotes the exported bundle identifier along with its full version." + NL + "             </li>" + NL + "             <li>" + NL + "               The &#x2196; glyph denotes an exported java package along with its full version, if specified." + NL + "             </li>" + NL + "             <li>" + NL + "               The &#x21D8; glyph denotes a required bundle along with its version range, if specified, as well as information about whether the requirement is optional and if so, whether it's greedy." + NL + "             </li>" + NL + "             <li>" + NL + "               The &#x2198; glyph denotes a required package along with its version range, if specified." + NL + "             </li>" + NL + "           </ul>" + NL + "" + NL + "          <ul style=\"margin-left: -1em; list-style-type: none; padding: 0; margin: 0;\">";
  protected final String TEXT_88 = NL + "            <li style=\"font-size: 90%;\">" + NL + "              <button id=\"";
  protected final String TEXT_89 = "');\">&#x25B7;</button>" + NL + "              <span style=\"font-size:100%;\">";
  protected final String TEXT_90 = "</span>" + NL + "              <div id=\"";
  protected final String TEXT_91 = "\" style=\"display:none; margin-left: 2em;\">";
  protected final String TEXT_92 = NL + "                ";
  protected final String TEXT_93 = "<br/>";
  protected final String TEXT_94 = NL + "              </div>" + NL + "            </li>";
  protected final String TEXT_95 = NL + NL + "        </div>" + NL;
  protected final String TEXT_96 = NL + "        <div style=\"padding-top: 100;\" id=\"rightcolumn\">" + NL + "          <div class=\"sideitem\">" + NL + "            <h6>Downloads</h6>" + NL + "            <p>" + NL + "              <a style=\"font-size: 75%; font-weight: bold;\" href=\"";
  protected final String TEXT_97 = "</a>" + NL + "              <br/>" + NL + "              <a class=\"btn btn-warning\" style=\"margin-top: 5pt; font-size: 90%;\" href=\"";
  protected final String TEXT_98 = "\" target=\"_blank\" title=\"Download ";
  protected final String TEXT_99 = "\">Download</a>" + NL + "              <a class=\"btn btn-warning\" style=\"margin-top: 5pt; border-color: DarkGreen; background-color: OliveDrab; font-size: 90%; padding-left: 0.2em; padding-right: 0.2em;\" href=\"";
  protected final String TEXT_100 = "\">sha256</a>" + NL + "              <a class=\"btn btn-warning\" style=\"margin-top: 5pt; border-color: DarkGreen; background-color: OliveDrab; font-size: 90%; padding-left: 0.2em; padding-right: 0.2em;\" href=\"";
  protected final String TEXT_101 = "\">sha512</a>" + NL + "            </p>" + NL + "          </div>";
  protected final String TEXT_102 = NL + "          <div class=\"sideitem\">" + NL + "            <h6>Git Commits</h6>";
  protected final String TEXT_103 = NL + "            <p>" + NL + "              <button title=\"Copy Commit ID ";
  protected final String TEXT_104 = " to Clipboard\" class=\"orange\" style=\"font-size: 150%; background-color: transparent; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#";
  protected final String TEXT_105 = "_commit_id')\">&#x270e;</button>" + NL + "              <a style=\"font-size: 100%;\" href=\"";
  protected final String TEXT_106 = "\" target=\"";
  protected final String TEXT_107 = "_git\">";
  protected final String TEXT_108 = "</a>" + NL + "              <br/>" + NL + "              <tt><a id=\"";
  protected final String TEXT_109 = "_commit_id\" class=\"btn btn-warning\" style=\"text-transform: none; margin-top: 5pt; border-color: DarkGreen; background-color: OliveDrab; font-size: 70%; padding-top: 0.2ex; padding-bottom: 0.2ex; padding-left: 0.2em; padding-right: 0.2em;\" href=\"";
  protected final String TEXT_110 = "_git_commit\" title=\"";
  protected final String TEXT_111 = " Git Commit ID\">";
  protected final String TEXT_112 = "</a></tt>" + NL + "            </p>";
  protected final String TEXT_113 = NL + "          </div>";
  protected final String TEXT_114 = NL + "        </div>" + NL;
  protected final String TEXT_115 = NL + "      </div>" + NL + "     </div>" + NL + "     </main>" + NL + "  </body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    UpdateSiteIndexGenerator parent = (UpdateSiteIndexGenerator)argument;
    List<UpdateSiteIndexGenerator> children = parent.getChildren();
  String title = parent.getTitle();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(title);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(title.toLowerCase());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(title);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(parent.getFavicon());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(parent.getTitleURL());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(parent.getTitleImage());
    stringBuffer.append(TEXT_7);
    for (Map.Entry<String, String> entry : parent.getBreadcrumbs().entrySet()) {
    if (entry.getValue() == null) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(entry.getKey());
    stringBuffer.append(TEXT_9);
    } else if (entry.getValue().trim().isEmpty()) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(entry.getKey());
    stringBuffer.append(TEXT_9);
    } else {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(entry.getValue());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(entry.getKey());
    stringBuffer.append(TEXT_13);
    }
    }
    stringBuffer.append(TEXT_14);
    for (Map.Entry<String, String> entry : parent.getNavigation().entrySet()) {
    String label = entry.getValue();
    boolean isTopLevel = !label.startsWith("-");
    if (!isTopLevel)
      label = label.substring(1);
    int index = label.indexOf('@');
    if (index != -1)
      label = label.substring(0, label.length() - 1);
    stringBuffer.append(TEXT_15);
    if (isTopLevel) {
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    if (!isTopLevel) {
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(entry.getKey());
    if (!entry.getKey().startsWith("http")) {
    stringBuffer.append(TEXT_20);
    }
    stringBuffer.append(TEXT_12);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_21);
    if (index != -1) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    stringBuffer.append(parent.getTitle());
    stringBuffer.append(TEXT_25);
    if (children.isEmpty()) {
    String date = parent.getDate();
    if (date != null) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(date);
    stringBuffer.append(TEXT_27);
    }
    }
    if (!parent.isRoot()) {
    String mainID = "main-list";
    Map<String, String> repositories = parent.getRepositoryChildren(); boolean hasTwisty = repositories != null && repositories.size() > 1;
    stringBuffer.append(TEXT_28);
    if (hasTwisty) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(mainID);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(mainID);
    stringBuffer.append(TEXT_31);
    }
    stringBuffer.append(TEXT_32);
    stringBuffer.append(parent.getSiteURL());
    stringBuffer.append(TEXT_33);
    if (hasTwisty){
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_35);
    stringBuffer.append(mainID);
    stringBuffer.append(TEXT_12);
    if (repositories != null) {
      int count = 0;
      for (Map.Entry<String, String> entry : repositories.entrySet()) {
        String repository = entry.getKey();
        String relativeURL = entry.getValue();
        String id = parent.getFolderID(repository);
    if (count++ != 0 ||!hasTwisty) {
    stringBuffer.append(TEXT_36);
    }
    stringBuffer.append(TEXT_37);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(relativeURL);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(repository);
    stringBuffer.append(TEXT_21);
    }
    }
    stringBuffer.append(TEXT_41);
    }
    if (parent.getBodyImage() != null) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(parent.getBodyImage());
    stringBuffer.append(TEXT_43);
    }
    if (parent.isRoot()) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_45);
    if (parent.getBuildURL() != null) {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(parent.getBuildURL());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(parent.getBuildURL());
    stringBuffer.append(TEXT_48);
    }
    } else {
    stringBuffer.append(TEXT_49);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_50);
    if (parent.hasArchive()) {
    stringBuffer.append(TEXT_51);
    }
    stringBuffer.append(TEXT_52);
    {
      String buildType = parent.getBuildType();
      boolean isLatest = parent.isLatest();
      Map<String, String> repositoryChildren = parent.getRepositoryChildren();
      if ("nightly".equals(buildType)) {
    if (isLatest) {
    stringBuffer.append(TEXT_53);
    } else if (repositoryChildren != null) {
    stringBuffer.append(TEXT_54);
    stringBuffer.append(parent.getRepositoryChildren().size());
    stringBuffer.append(TEXT_55);
    } else {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(parent.getRetainedNightlyBuilds());
    stringBuffer.append(TEXT_55);
    }
    } else if ("milestone".equals(buildType)) {
    if (isLatest) {
    stringBuffer.append(TEXT_57);
    } else if (repositoryChildren != null) {
    stringBuffer.append(TEXT_58);
    stringBuffer.append(parent.getRepositoryChildren().size());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_60);
    } else {
    stringBuffer.append(TEXT_61);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_60);
    }
    } else {
    if (isLatest) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_63);
    } else if (repositoryChildren != null) {
    stringBuffer.append(TEXT_58);
    stringBuffer.append(parent.getRepositoryChildren().size());
    stringBuffer.append(TEXT_64);
    } else {
    stringBuffer.append(TEXT_65);
    }
    }
    }
    stringBuffer.append(TEXT_66);
    }
    if (!children.isEmpty()) {
    List<String> sdks = parent.getSDKs();
    if (!sdks.isEmpty()) {
    stringBuffer.append(TEXT_67);
    for (String sdk : sdks) {
    stringBuffer.append(TEXT_68);
    stringBuffer.append(sdk);
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_69);
    }
    stringBuffer.append(TEXT_70);
    if (parent.isRoot()) {
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(TEXT_52);
    for (UpdateSiteIndexGenerator child : children) {
      String id = parent.getFolderID(child.getFolderName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(child.getRelativeIndexURL());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(child.getTitle());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(child.getRelativeIndexURL());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(child.getSiteURL());
    stringBuffer.append(TEXT_77);
    }
    } else {
    List<String> features = parent.getFeatures();
    if (!features.isEmpty()) {
    stringBuffer.append(TEXT_78);
    for (String feature : features) {
    if (parent.isSDK(feature)) {
    stringBuffer.append(TEXT_79);
    }
    stringBuffer.append(TEXT_80);
    stringBuffer.append(feature.replace(" ", "&nbsp;"));
    stringBuffer.append(TEXT_9);
    if (parent.isSDK(feature)) {
    stringBuffer.append(TEXT_81);
    }
    }
    stringBuffer.append(TEXT_82);
    }
    Map<String, List<String>> bundles = parent.getBundles();
    if (!bundles.isEmpty()) {
    stringBuffer.append(TEXT_83);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_84);
    {
        StringBuilder onClick = new StringBuilder();
        for (String bundle : bundles.keySet()) {
          onClick.append("expand('").append(parent.getFolderID(bundle)).append("');");
        }
    stringBuffer.append(TEXT_85);
    stringBuffer.append(onClick);
    stringBuffer.append(TEXT_86);
    }
    stringBuffer.append(TEXT_87);
    for (Map.Entry<String, List<String>> entry : bundles.entrySet()) {
        String bundle = entry.getKey();
        String id = parent.getFolderID(bundle);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(bundle.replace(" ", "&nbsp;"));
    stringBuffer.append(TEXT_90);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_91);
    for (String line : entry.getValue()) {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(line);
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(TEXT_69);
    }
    }
    stringBuffer.append(TEXT_95);
    if (parent.hasArchive()) {
    String archiveName = new File(parent.getArchive()).getName();
    String sha256Name = new File(parent.getDigest("SHA-256")).getName();
    String sha512Name = new File(parent.getDigest("SHA-512")).getName();
    stringBuffer.append(TEXT_96);
    stringBuffer.append(parent.getArchiveDownloadURL());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(archiveName);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(parent.getArchiveDownloadURL());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(archiveName);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(sha256Name);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(sha256Name);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(sha512Name);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(sha512Name);
    stringBuffer.append(TEXT_101);
    {
      Map<String, String> commits = parent.getCommits();
      if (!commits.isEmpty()) {
    stringBuffer.append(TEXT_102);
    for (Map.Entry<String, String> entry : commits.entrySet()) {
          String label = entry.getKey();
          String url = entry.getValue();
          String gitRepoURL = url.substring(0, url.indexOf("commit")) + "log/";
          String id = url.substring(url.indexOf('=') + 1);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(gitRepoURL);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(url);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_112);
    }
    stringBuffer.append(TEXT_113);
    }
    }
    stringBuffer.append(TEXT_114);
    }
    stringBuffer.append(TEXT_115);
    return stringBuffer.toString();
  }
}
