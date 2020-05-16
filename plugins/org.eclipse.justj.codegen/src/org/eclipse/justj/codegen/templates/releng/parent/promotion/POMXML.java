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
  protected static final String _5 = "-breadcrumb \"JREs\"";
  protected static final String _6 = "-breadcrumb \"JustJ https://www.eclipse.org/justj/?page=download\"";
  protected static final String _7 = "-build-url ${org.eclipse.justj.p2.manager.build.url}";
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
  protected static final String _21 = ".parent</artifactId>";
  protected static final String _22 = ".promote</artifactId>";
  protected static final String _23 = "<!--";
  protected static final String _24 = "</appArgLine>";
  protected static final String _25 = "</build>";
  protected static final String _26 = "</configuration>";
  protected static final String _27 = "</dependencies>";
  protected static final String _28 = "</dependency>";
  protected static final String _29 = "</execution>";
  protected static final String _30 = "</executions>";
  protected static final String _31 = "</goals>";
  protected static final String _32 = "</groupId>";
  protected static final String _33 = "</parent>";
  protected static final String _34 = "</plugin>";
  protected static final String _35 = "</plugins>";
  protected static final String _36 = "</project>";
  protected static final String _37 = "</properties>";
  protected static final String _38 = "</repositories>";
  protected static final String _39 = "</repository>";
  protected static final String _40 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _41 = "<appArgLine>";
  protected static final String _42 = "<argLine></argLine>";
  protected static final String _43 = "<artifactId>";
  protected static final String _44 = "<artifactId>org.apache.felix.scr</artifactId>";
  protected static final String _45 = "<artifactId>org.eclipse.justj.p2</artifactId>";
  protected static final String _46 = "<artifactId>tycho-eclipserun-plugin</artifactId>";
  protected static final String _47 = "<build.type>nightly</build.type>";
  protected static final String _48 = "<build>";
  protected static final String _49 = "<configuration>";
  protected static final String _50 = "<dependencies>";
  protected static final String _51 = "<dependency>";
  protected static final String _52 = "<eclipse.repo>https://download.eclipse.org/releases/2020-06</eclipse.repo>";
  protected static final String _53 = "<execution>";
  protected static final String _54 = "<executionEnvironment>JavaSE-1.8</executionEnvironment>";
  protected static final String _55 = "<executions>";
  protected static final String _56 = "<goal>eclipse-run</goal>";
  protected static final String _57 = "<goals>";
  protected static final String _58 = "<groupId>";
  protected static final String _59 = "<groupId>org.eclipse.tycho.extras</groupId>";
  protected static final String _60 = "<id>eclipse.repo</id>";
  protected static final String _61 = "<id>justj.tools.repo</id>";
  protected static final String _62 = "<id>promote</id>";
  protected static final String _63 = "<justj.tools.repo>https://download.eclipse.org/justj/sandbox/tools/updates/nightly/latest</justj.tools.repo>";
  protected static final String _64 = "<layout>p2</layout>";
  protected static final String _65 = "<maven.build.timestamp.format>yyyyMMddHHmm</maven.build.timestamp.format>";
  protected static final String _66 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _67 = "<org.eclipse.justj.p2.manager.args>-remote ${org.eclipse.storage.user}@projects-storage.eclipse.org:/home/data/httpd/download.eclipse.org/justj</org.eclipse.justj.p2.manager.args>";
  protected static final String _68 = "<org.eclipse.justj.p2.manager.build.url>http://www.example.com/</org.eclipse.justj.p2.manager.build.url>";
  protected static final String _69 = "<org.eclipse.justj.p2.manager.relative>sandbox-test/jres/updates</org.eclipse.justj.p2.manager.relative>";
  protected static final String _70 = "<org.eclipse.storage.user>genie.justj</org.eclipse.storage.user>";
  protected static final String _71 = "<packaging>pom</packaging>";
  protected static final String _72 = "<parent>";
  protected static final String _73 = "<phase>generate-sources</phase>";
  protected static final String _74 = "<plugin>";
  protected static final String _75 = "<plugins>";
  protected static final String _76 = "<project";
  protected static final String _77 = "<properties>";
  protected static final String _78 = "<relativePath>..</relativePath>";
  protected static final String _79 = "<repositories>";
  protected static final String _80 = "<repository>";
  protected static final String _81 = "<type>eclipse-plugin</type>";
  protected static final String _82 = "<url>${eclipse.repo}</url>";
  protected static final String _83 = "<url>${justj.tools.repo}</url>";
  protected static final String _84 = "<version>${tycho-version}</version>";
  protected static final String _85 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _86 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _87 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _88 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "        ";
  protected final String NL_5 = NL + "          ";
  protected final String NL_6 = NL + "            ";
  protected final String NL_7 = NL + "              ";
  protected final String NL_8 = NL + "                ";
  protected final String _89 = NL + _2 + NL + _76 + NL_2 + _87 + NL_2 + _86 + NL_2 + _88 + NL_1 + _66 + NL + NL_1 + _72 + NL_2 + _58;
  protected final String _90 = _32 + NL_2 + _43;
  protected final String _91 = _21 + NL_2 + _85 + NL_2 + _78 + NL_1 + _33 + NL + NL_1 + _58;
  protected final String _92 = _32 + NL_1 + _43;
  protected final String _93 = _22 + NL_1 + _85 + NL_1 + _71 + NL + NL_1 + _77 + NL_2 + _52 + NL_2 + _63 + NL_2 + _70 + NL_2 + _67 + NL_2 + _69 + NL_2 + _65 + NL_2 + _68 + NL_2 + _47 + NL_1 + _37 + NL + NL_1 + _48 + NL_2 + _75 + NL_3 + _74 + NL_4 + _59 + NL_4 + _46 + NL_4 + _84 + NL_4 + _49 + NL_5 + _54 + NL_5 + _50 + NL_6 + _51 + NL_7 + _45 + NL_7 + _81 + NL_6 + _28 + NL_6 + _51 + NL_7 + _44 + NL_7 + _81 + NL_6 + _28 + NL_5 + _27 + NL_5 + _79 + NL_6 + _80 + NL_7 + _60 + NL_7 + _64 + NL_7 + _82 + NL_6 + _39 + NL_6 + _80 + NL_7 + _61 + NL_7 + _64 + NL_7 + _83 + NL_6 + _39 + NL_5 + _38 + NL_4 + _26 + NL_4 + _55 + NL_5 + _53 + NL_6 + _62 + NL_6 + _57 + NL_7 + _56 + NL_6 + _31 + NL_6 + _73 + NL_6 + _49 + NL_7 + _42 + NL_7 + _41 + NL_8 + _8 + NL_8 + _3 + NL_8 + _9 + NL_8 + _12 + NL_8 + _1 + NL_8 + _15 + NL_8 + _11 + NL_8 + _7 + NL_8 + _16 + NL_8 + _14 + NL_8 + _17 + NL_8 + _13 + NL_8 + _18 + NL_8 + _20 + NL_8 + _6 + NL_8 + _5 + NL_8 + _10 + NL_8 + _19 + NL_8 + _4 + NL_7 + _24 + NL_6 + _26 + NL_5 + _29 + NL_4 + _30 + NL_3 + _34 + NL_2 + _35 + NL_1 + _25 + NL + NL + _36;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
    builder.append(_40);
    builder.append(NL);
    builder.append(_23);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_89);
    builder.append(name);
    builder.append(_90);
    builder.append(name);
    builder.append(_91);
    builder.append(name);
    builder.append(_92);
    builder.append(name);
    builder.append(_93);
    builder.append(NL);
    return builder.toString();
  }
}
