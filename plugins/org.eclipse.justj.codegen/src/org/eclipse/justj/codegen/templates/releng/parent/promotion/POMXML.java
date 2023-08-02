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
  protected static final String _2 = "${org.eclipse.justj.p2.manager.extra.args}";
  protected static final String _3 = "-->";
  protected static final String _4 = "-application org.eclipse.justj.p2.manager";
  protected static final String _5 = "-body-image https://eclipse.dev/justj/justj.svg";
  protected static final String _6 = "-breadcrumb \"JustJ https://eclipse.dev/justj/?page=download\"";
  protected static final String _7 = "-build-url ${org.eclipse.justj.p2.manager.build.url}";
  protected static final String _8 = "-commit https://github.com/eclipse-justj/justj/commit/${git.commit}";
  protected static final String _9 = "-consoleLog";
  protected static final String _10 = "-data @None";
  protected static final String _11 = "-favicon https://eclipse.dev/justj/justj_favicon.ico";
  protected static final String _12 = "-label \"JustJ JREs\"";
  protected static final String _13 = "-nosplash";
  protected static final String _14 = "-promote ${project.basedir}/../../org.eclipse.justj.site/target/repository";
  protected static final String _15 = "-relative ${org.eclipse.justj.p2.manager.relative}";
  protected static final String _16 = "-retain 5";
  protected static final String _17 = "-root ${project.build.directory}/justj-sync";
  protected static final String _18 = "-target-url https://download.eclipse.org/justj";
  protected static final String _19 = "-timestamp ${build.timestamp}";
  protected static final String _20 = "-title-image https://eclipse.dev/justj/justj_title.svg";
  protected static final String _21 = "-type ${build.type}";
  protected static final String _22 = "-version-iu org.eclipse.justj.";
  protected static final String _23 = ".parent</artifactId>";
  protected static final String _24 = ".promote</artifactId>";
  protected static final String _25 = "<!--";
  protected static final String _26 = "</appArgLine>";
  protected static final String _27 = "</build>";
  protected static final String _28 = "</configuration>";
  protected static final String _29 = "</dependencies>";
  protected static final String _30 = "</dependency>";
  protected static final String _31 = "</execution>";
  protected static final String _32 = "</executions>";
  protected static final String _33 = "</goals>";
  protected static final String _34 = "</groupId>";
  protected static final String _35 = "</parent>";
  protected static final String _36 = "</plugin>";
  protected static final String _37 = "</plugins>";
  protected static final String _38 = "</project>";
  protected static final String _39 = "</properties>";
  protected static final String _40 = "</repositories>";
  protected static final String _41 = "</repository>";
  protected static final String _42 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _43 = "<appArgLine>";
  protected static final String _44 = "<argLine></argLine>";
  protected static final String _45 = "<artifactId>";
  protected static final String _46 = "<artifactId>org.apache.felix.scr</artifactId>";
  protected static final String _47 = "<artifactId>org.eclipse.justj.p2</artifactId>";
  protected static final String _48 = "<artifactId>tycho-eclipserun-plugin</artifactId>";
  protected static final String _49 = "<build.type>nightly</build.type>";
  protected static final String _50 = "<build>";
  protected static final String _51 = "<configuration>";
  protected static final String _52 = "<dependencies>";
  protected static final String _53 = "<dependency>";
  protected static final String _54 = "<eclipse.repo>https://download.eclipse.org/releases/2023-06</eclipse.repo>";
  protected static final String _55 = "<execution>";
  protected static final String _56 = "<executionEnvironment>JavaSE-11</executionEnvironment>";
  protected static final String _57 = "<executions>";
  protected static final String _58 = "<goal>eclipse-run</goal>";
  protected static final String _59 = "<goals>";
  protected static final String _60 = "<groupId>";
  protected static final String _61 = "<groupId>org.eclipse.tycho.extras</groupId>";
  protected static final String _62 = "<id>eclipse.repo</id>";
  protected static final String _63 = "<id>justj.tools.repo</id>";
  protected static final String _64 = "<id>promote</id>";
  protected static final String _65 = "<justj.tools.repo>https://download.eclipse.org/justj/tools/updates/nightly/latest</justj.tools.repo>";
  protected static final String _66 = "<layout>p2</layout>";
  protected static final String _67 = "<maven.build.timestamp.format>yyyyMMddHHmm</maven.build.timestamp.format>";
  protected static final String _68 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _69 = "<org.eclipse.justj.p2.manager.args>-remote ${org.eclipse.storage.user}@projects-storage.eclipse.org:/home/data/httpd/download.eclipse.org/justj</org.eclipse.justj.p2.manager.args>";
  protected static final String _70 = "<org.eclipse.justj.p2.manager.build.url>http://www.example.com/</org.eclipse.justj.p2.manager.build.url>";
  protected static final String _71 = "<org.eclipse.justj.p2.manager.extra.args></org.eclipse.justj.p2.manager.extra.args>";
  protected static final String _72 = "<org.eclipse.justj.p2.manager.relative>jres-test/updates</org.eclipse.justj.p2.manager.relative>";
  protected static final String _73 = "<org.eclipse.storage.user>genie.justj</org.eclipse.storage.user>";
  protected static final String _74 = "<packaging>pom</packaging>";
  protected static final String _75 = "<parent>";
  protected static final String _76 = "<phase>generate-sources</phase>";
  protected static final String _77 = "<plugin>";
  protected static final String _78 = "<plugins>";
  protected static final String _79 = "<project";
  protected static final String _80 = "<properties>";
  protected static final String _81 = "<relativePath>..</relativePath>";
  protected static final String _82 = "<repositories>";
  protected static final String _83 = "<repository>";
  protected static final String _84 = "<type>eclipse-plugin</type>";
  protected static final String _85 = "<url>${eclipse.repo}</url>";
  protected static final String _86 = "<url>${justj.tools.repo}</url>";
  protected static final String _87 = "<version>${tycho-version}</version>";
  protected static final String _88 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _89 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _90 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _91 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "        ";
  protected final String NL_5 = NL + "          ";
  protected final String NL_6 = NL + "            ";
  protected final String NL_7 = NL + "              ";
  protected final String NL_8 = NL + "                ";
  protected final String _92 = NL + _3 + NL + _79 + NL_2 + _90 + NL_2 + _89 + NL_2 + _91 + NL_1 + _68 + NL + NL_1 + _75 + NL_2 + _60;
  protected final String _93 = _34 + NL_2 + _45;
  protected final String _94 = _23 + NL_2 + _88 + NL_2 + _81 + NL_1 + _35 + NL + NL_1 + _60;
  protected final String _95 = _34 + NL_1 + _45;
  protected final String _96 = _24 + NL_1 + _88 + NL_1 + _74 + NL + NL_1 + _80 + NL_2 + _54 + NL_2 + _65 + NL_2 + _73 + NL_2 + _69 + NL_2 + _71 + NL_2 + _72 + NL_2 + _67 + NL_2 + _70 + NL_2 + _49 + NL_1 + _39 + NL + NL_1 + _50 + NL_2 + _78 + NL_3 + _77 + NL_4 + _61 + NL_4 + _48 + NL_4 + _87 + NL_4 + _51 + NL_5 + _56 + NL_5 + _52 + NL_6 + _53 + NL_7 + _47 + NL_7 + _84 + NL_6 + _30 + NL_6 + _53 + NL_7 + _46 + NL_7 + _84 + NL_6 + _30 + NL_5 + _29 + NL_5 + _82 + NL_6 + _83 + NL_7 + _62 + NL_7 + _66 + NL_7 + _85 + NL_6 + _41 + NL_6 + _83 + NL_7 + _63 + NL_7 + _66 + NL_7 + _86 + NL_6 + _41 + NL_5 + _40 + NL_4 + _28 + NL_4 + _57 + NL_5 + _55 + NL_6 + _64 + NL_6 + _59 + NL_7 + _58 + NL_6 + _33 + NL_6 + _76 + NL_6 + _51 + NL_7 + _44 + NL_7 + _43 + NL_8 + _9 + NL_8 + _4 + NL_8 + _10 + NL_8 + _13 + NL_8 + _1 + NL_8 + _16 + NL_8 + _12 + NL_8 + _7 + NL_8 + _17 + NL_8 + _15 + NL_8 + _22 + NL_8 + _8 + NL_8 + _18 + NL_8 + _14 + NL_8 + _19 + NL_8 + _21 + NL_8 + _6 + NL_8 + _11 + NL_8 + _20 + NL_8 + _5 + NL_8 + _2 + NL_7 + _26 + NL_6 + _28 + NL_5 + _31 + NL_4 + _32 + NL_3 + _36 + NL_2 + _37 + NL_1 + _27 + NL + NL + _38;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
    builder.append(_42);
    builder.append(NL);
    builder.append(_25);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_92);
    builder.append(name);
    builder.append(_93);
    builder.append(name);
    builder.append(_94);
    builder.append(name);
    builder.append(_95);
    builder.append(name);
    builder.append(_96);
    builder.append(NL);
    return builder.toString();
  }
}
