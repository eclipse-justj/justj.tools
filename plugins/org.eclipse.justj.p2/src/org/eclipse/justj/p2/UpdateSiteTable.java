/*
 * Copyright (c) 2023 Eclipse contributors and others.
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.justj.p2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UpdateSiteTable
{
  protected static String nl;
  public static synchronized UpdateSiteTable create(String lineSeparator)
  {
    nl = lineSeparator;
    UpdateSiteTable result = new UpdateSiteTable();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" + NL + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">" + NL + "<head>" + NL + "  <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>" + NL + "  <title>";
  protected final String TEXT_2 = " Summary</title>" + NL + "  <meta name=\"keywords\" content=\"eclipse,";
  protected final String TEXT_3 = "\"/>" + NL + "  <meta name=\"description\" content=\"";
  protected final String TEXT_4 = " of p2 Update Sites\"/>" + NL + "  <link href=\"//fonts.googleapis.com/css?family=Open+Sans:400,700,300,600,100\" rel=\"stylesheet\" type=\"text/css\"/>" + NL + "  <link rel=\"stylesheet\" href=\"https://www.eclipse.org/eclipse.org-common/themes/solstice/public/stylesheets/styles.min.css\"/>" + NL + "  <link rel=\"icon\" type=\"image/ico\" href=\"";
  protected final String TEXT_5 = "\"/>" + NL + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>" + NL + "</head>" + NL + "" + NL + "  <body id=\"body_solstice\">" + NL + "    <style>" + NL + "      table, th, td {" + NL + "        border: 1px solid black;" + NL + "        border-collapse: collapse;" + NL + "        padding: 0ex 1ex;" + NL + "      }" + NL + "" + NL + "      th {" + NL + "        padding: 0.5ex 1ex;" + NL + "      }" + NL + "" + NL + "      tr:nth-child(even) {" + NL + "        background-color: OldLace;" + NL + "      }" + NL + "" + NL + "      .long-segment {" + NL + "        font-size: 85%;" + NL + "      }" + NL + "" + NL + "      .qualifier {" + NL + "        font-size: 75%;" + NL + "      }" + NL + "" + NL + "      .bsn-prefix {" + NL + "        font-size: 85%;" + NL + "      }" + NL + "    </style>" + NL + "    <script>" + NL + "    </script>" + NL + "" + NL + "    <header role=\"banner\" id=\"header-wrapper\">" + NL + "      <div class=\"container\">" + NL + "        <div class=\"row\" id=\"header-row\">" + NL + "          <div class=\"hidden-xs col-sm-8 col-md-6 col-lg-5\" id=\"header-left\">" + NL + "            <div class=\"wrapper-logo-default\">" + NL + "              <a href=\"";
  protected final String TEXT_6 = "\">" + NL + "                <img class=\"logo-eclipse-default img-responsive hidden-xs\" style=\"height: 50px; white-space: nowrap; display: inline-block;\" alt=\"Project Logo\" src=\"";
  protected final String TEXT_7 = "\"/>" + NL + "              </a>" + NL + "            </div>" + NL + "          </div>" + NL + "        </div>" + NL + "      </div>" + NL + "    </header>" + NL + "" + NL + "    <section class=\"hidden-print default-breadcrumbs\" id=\"breadcrumb\">" + NL + "      <div class=\"container\">" + NL + "        <h3 class=\"sr-only\">Breadcrumbs</h3>" + NL + "        <div class=\"row\">" + NL + "          <div class=\"col-sm-16 padding-left-30\">" + NL + "            <ol class=\"breadcrumb\">" + NL + "              <li><a href=\"https://www.eclipse.org/\">Home</a></li>" + NL + "              <li><a href=\"https://www.eclipse.org/projects/\">Projects</a></li>";
  protected final String TEXT_8 = NL + "              <li class=\"active\">";
  protected final String TEXT_9 = "</li>";
  protected final String TEXT_10 = NL + "              <li>";
  protected final String TEXT_11 = NL + "              <li><a href=\"";
  protected final String TEXT_12 = "\">";
  protected final String TEXT_13 = "</a></li>";
  protected final String TEXT_14 = NL + "            </ol>" + NL + "          </div>" + NL + "          <div class=\"col-sm-8 margin-top-15\"></div>" + NL + "        </div>" + NL + "      </div>" + NL + "    </section>" + NL + "" + NL + "    <main class=\"no-promo\">" + NL + "      <div id=\"novaContent\" style=\"display: flex; justify-content: center;\">" + NL + "        <div>" + NL + "          <h2 style=\"text-align: center;\">";
  protected final String TEXT_15 = "</h2>";
  protected final String TEXT_16 = NL + "          <img style=\"padding-left: .5em; float: right; min-height: 64px; max-height: 96pt;\" src=\"";
  protected final String TEXT_17 = "\" alt=\"\"/>" + NL + "          <br/>";
  protected final String TEXT_18 = NL + "           <p>This is a tabular summary of the bundle versions available in the p2 update sites for ";
  protected final String TEXT_19 = ".</p>";
  protected final String TEXT_20 = NL + "           <p>A column header or version decorated with <b>";
  protected final String TEXT_21 = "</b> indicates a version increase relative to the next/older column.</p>";
  protected final String TEXT_22 = NL + "           <p>A cell decorated with <b>";
  protected final String TEXT_23 = "</b> indicates a removal relative to the next/older column.</p>";
  protected final String TEXT_24 = NL + "           <p>Where applicable, each version is link to the Maven Central source.</p>";
  protected final String TEXT_25 = NL + NL + "           <table>" + NL + "" + NL + "             <tr>" + NL + "               <th>Bundle Symbolic Name</th>";
  protected final String TEXT_26 = NL + "               <th><a href=\"";
  protected final String TEXT_27 = "</a>";
  protected final String TEXT_28 = "</th>";
  protected final String TEXT_29 = NL + "            </tr>";
  protected final String TEXT_30 = NL + "            <tr>" + NL + "              <td>";
  protected final String TEXT_31 = "</td>";
  protected final String TEXT_32 = NL + "              <td>";
  protected final String TEXT_33 = NL + "          </table>" + NL + "        </div>" + NL + "      </div>" + NL + "    </main>" + NL + "  </body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    UpdateSiteIndexGenerator parent = (UpdateSiteIndexGenerator)argument;
  String title = parent.getTableTitle();
  List<UpdateSiteIndexGenerator> tableChildren = parent.getTableChildren();
  Map<UpdateSiteIndexGenerator, String> decorators = tableChildren.stream().collect(Collectors.toMap(Function.identity(), child -> child.getTableChildFolderDecoration(parent)));
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
    for (Map.Entry<String, String> entry : parent.getBreadcrumbs(false).entrySet()) {
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
    stringBuffer.append(title);
    stringBuffer.append(TEXT_15);
    if (parent.getBodyImage() != null) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(parent.getBodyImage());
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    stringBuffer.append(parent.getProjectLabel());
    stringBuffer.append(TEXT_19);
    if (parent.getTableChildren().size() > 1) {
    if (decorators.values().stream().anyMatch(decorator -> decorator.contains(UpdateSiteIndexGenerator.UPDATED_DECORATOR))) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(UpdateSiteIndexGenerator.UPDATED_DECORATOR);
    stringBuffer.append(TEXT_21);
    }
    if (decorators.values().stream().anyMatch(decorator -> decorator.contains(UpdateSiteIndexGenerator.REMOVED_DECORATOR))) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(UpdateSiteIndexGenerator.REMOVED_DECORATOR);
    stringBuffer.append(TEXT_23);
    }
    }
    if (parent.hasMavenDescriptors()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    for (UpdateSiteIndexGenerator tableChild : tableChildren) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(parent.getRelativeIndexURL(tableChild));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(tableChild.getTableChildFolderName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(decorators.get(tableChild));
    stringBuffer.append(TEXT_28);
    }
    stringBuffer.append(TEXT_29);
    for (String bsn : parent.getTableBundles()) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(parent.getShortBSN(bsn));
    stringBuffer.append(TEXT_31);
    for (UpdateSiteIndexGenerator tableChild : tableChildren) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(parent.getVersions(bsn, tableChild));
    stringBuffer.append(TEXT_31);
    }
    stringBuffer.append(TEXT_29);
    }
    stringBuffer.append(TEXT_33);
    return stringBuffer.toString();
  }
}
