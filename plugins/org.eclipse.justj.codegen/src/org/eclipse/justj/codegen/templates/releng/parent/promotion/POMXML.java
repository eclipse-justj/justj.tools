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
package org.eclipse.justj.codegen.templates.releng.parent.promotion;

import org.eclipse.justj.codegen.model.*;

public class POMXML
{
  protected static String nl;
  public static synchronized POMXML create(String lineSeparator)
  {
    nl = lineSeparator;
    POMXML result = new POMXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = "${org.eclipse.justj.p2.manager.args}";
  protected static final String _2 = "-->";
  protected static final String _3 = "-application org.eclipse.justj.p2.manager";
  protected static final String _4 = "-body-image https://www.eclipse.org/justj/justj_incubation.svg";
  protected static final String _5 = "-breadcrumb \"JustJ https://www.eclipse.org/justj/?page=download\"";
  protected static final String _6 = "-build-url ${org.eclipse.justj.p2.manager.build.url}";
  protected static final String _7 = "-commit https://git.eclipse.org/c/justj/justj.git/commit/?id=${git.commit}";
  protected static final String _8 = "-consoleLog";
  protected static final String _9 = "-data @None";
  protected static final String _10 = "-favicon https://www.eclipse.org/justj/justj_favicon.ico";
  protected static final String _11 = "-label \"JustJ JREs\"";
  protected static final String _12 = "-nosplash";
  protected static final String _13 = "-promote ${project.basedir}/../../org.eclipse.justj.site/target/repository";
  protected static final String _14 = "-relative ${org.eclipse.justj.p2.manager.relative}";
  protected static final String _15 = "-retain 5";
  protected static final String _16 = "-root ${project.build.directory}/justj-sync";
  protected static final String _17 = "-target-url https://download.eclipse.org/justj";
  protected static final String _18 = "-timestamp ${build.timestamp}";
  protected static final String _19 = "-title-image https://www.eclipse.org/justj/justj_title.svg";
  protected static final String _20 = "-type ${build.type}";
  protected static final String _21 = "-version-iu org.eclipse.justj.";
  protected static final String _22 = ".parent</artifactId>";
  protected static final String _23 = ".promote</artifactId>";
  protected static final String _24 = "<!--";
  protected static final String _25 = "</appArgLine>";
  protected static final String _26 = "</build>";
  protected static final String _27 = "</configuration>";
  protected static final String _28 = "</dependencies>";
  protected static final String _29 = "</dependency>";
  protected static final String _30 = "</execution>";
  protected static final String _31 = "</executions>";
  protected static final String _32 = "</goals>";
  protected static final String _33 = "</groupId>";
  protected static final String _34 = "</parent>";
  protected static final String _35 = "</plugin>";
  protected static final String _36 = "</plugins>";
  protected static final String _37 = "</project>";
  protected static final String _38 = "</properties>";
  protected static final String _39 = "</repositories>";
  protected static final String _40 = "</repository>";
  protected static final String _41 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _42 = "<appArgLine>";
  protected static final String _43 = "<argLine></argLine>";
  protected static final String _44 = "<artifactId>";
  protected static final String _45 = "<artifactId>org.apache.felix.scr</artifactId>";
  protected static final String _46 = "<artifactId>org.eclipse.justj.p2</artifactId>";
  protected static final String _47 = "<artifactId>tycho-eclipserun-plugin</artifactId>";
  protected static final String _48 = "<build.type>nightly</build.type>";
  protected static final String _49 = "<build>";
  protected static final String _50 = "<configuration>";
  protected static final String _51 = "<dependencies>";
  protected static final String _52 = "<dependency>";
  protected static final String _53 = "<eclipse.repo>https://download.eclipse.org/releases/2020-06</eclipse.repo>";
  protected static final String _54 = "<execution>";
  protected static final String _55 = "<executionEnvironment>JavaSE-1.8</executionEnvironment>";
  protected static final String _56 = "<executions>";
  protected static final String _57 = "<goal>eclipse-run</goal>";
  protected static final String _58 = "<goals>";
  protected static final String _59 = "<groupId>";
  protected static final String _60 = "<groupId>org.eclipse.tycho.extras</groupId>";
  protected static final String _61 = "<id>eclipse.repo</id>";
  protected static final String _62 = "<id>justj.tools.repo</id>";
  protected static final String _63 = "<id>promote</id>";
  protected static final String _64 = "<justj.tools.repo>https://download.eclipse.org/justj/tools/updates/nightly/latest</justj.tools.repo>";
  protected static final String _65 = "<layout>p2</layout>";
  protected static final String _66 = "<maven.build.timestamp.format>yyyyMMddHHmm</maven.build.timestamp.format>";
  protected static final String _67 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _68 = "<org.eclipse.justj.p2.manager.args>-remote ${org.eclipse.storage.user}@projects-storage.eclipse.org:/home/data/httpd/download.eclipse.org/justj</org.eclipse.justj.p2.manager.args>";
  protected static final String _69 = "<org.eclipse.justj.p2.manager.build.url>http://www.example.com/</org.eclipse.justj.p2.manager.build.url>";
  protected static final String _70 = "<org.eclipse.justj.p2.manager.relative>sandbox-test/jres/updates</org.eclipse.justj.p2.manager.relative>";
  protected static final String _71 = "<org.eclipse.storage.user>genie.justj</org.eclipse.storage.user>";
  protected static final String _72 = "<packaging>pom</packaging>";
  protected static final String _73 = "<parent>";
  protected static final String _74 = "<phase>generate-sources</phase>";
  protected static final String _75 = "<plugin>";
  protected static final String _76 = "<plugins>";
  protected static final String _77 = "<project";
  protected static final String _78 = "<properties>";
  protected static final String _79 = "<relativePath>..</relativePath>";
  protected static final String _80 = "<repositories>";
  protected static final String _81 = "<repository>";
  protected static final String _82 = "<type>eclipse-plugin</type>";
  protected static final String _83 = "<url>${eclipse.repo}</url>";
  protected static final String _84 = "<url>${justj.tools.repo}</url>";
  protected static final String _85 = "<version>${tycho-version}</version>";
  protected static final String _86 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _87 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _88 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _89 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "        ";
  protected final String NL_5 = NL + "          ";
  protected final String NL_6 = NL + "            ";
  protected final String NL_7 = NL + "              ";
  protected final String NL_8 = NL + "                ";
  protected final String _90 = NL + _2 + NL + _77 + NL_2 + _88 + NL_2 + _87 + NL_2 + _89 + NL_1 + _67 + NL + NL_1 + _73 + NL_2 + _59;
  protected final String _91 = _33 + NL_2 + _44;
  protected final String _92 = _22 + NL_2 + _86 + NL_2 + _79 + NL_1 + _34 + NL + NL_1 + _59;
  protected final String _93 = _33 + NL_1 + _44;
  protected final String _94 = _23 + NL_1 + _86 + NL_1 + _72 + NL + NL_1 + _78 + NL_2 + _53 + NL_2 + _64 + NL_2 + _71 + NL_2 + _68 + NL_2 + _70 + NL_2 + _66 + NL_2 + _69 + NL_2 + _48 + NL_1 + _38 + NL + NL_1 + _49 + NL_2 + _76 + NL_3 + _75 + NL_4 + _60 + NL_4 + _47 + NL_4 + _85 + NL_4 + _50 + NL_5 + _55 + NL_5 + _51 + NL_6 + _52 + NL_7 + _46 + NL_7 + _82 + NL_6 + _29 + NL_6 + _52 + NL_7 + _45 + NL_7 + _82 + NL_6 + _29 + NL_5 + _28 + NL_5 + _80 + NL_6 + _81 + NL_7 + _61 + NL_7 + _65 + NL_7 + _83 + NL_6 + _40 + NL_6 + _81 + NL_7 + _62 + NL_7 + _65 + NL_7 + _84 + NL_6 + _40 + NL_5 + _39 + NL_4 + _27 + NL_4 + _56 + NL_5 + _54 + NL_6 + _63 + NL_6 + _58 + NL_7 + _57 + NL_6 + _32 + NL_6 + _74 + NL_6 + _50 + NL_7 + _43 + NL_7 + _42 + NL_8 + _8 + NL_8 + _3 + NL_8 + _9 + NL_8 + _12 + NL_8 + _1 + NL_8 + _15 + NL_8 + _11 + NL_8 + _6 + NL_8 + _16 + NL_8 + _14 + NL_8 + _21 + NL_8 + _7 + NL_8 + _17 + NL_8 + _13 + NL_8 + _18 + NL_8 + _20 + NL_8 + _5 + NL_8 + _10 + NL_8 + _19 + NL_8 + _4 + NL_7 + _25 + NL_6 + _27 + NL_5 + _30 + NL_4 + _31 + NL_3 + _35 + NL_2 + _36 + NL_1 + _26 + NL + NL + _37;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
    builder.append(_41);
    builder.append(NL);
    builder.append(_24);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_90);
    builder.append(name);
    builder.append(_91);
    builder.append(name);
    builder.append(_92);
    builder.append(name);
    builder.append(_93);
    builder.append(name);
    builder.append(_94);
    builder.append(NL);
    return builder.toString();
  }
}
