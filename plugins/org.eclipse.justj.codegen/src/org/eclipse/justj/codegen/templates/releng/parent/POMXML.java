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
package org.eclipse.justj.codegen.templates.releng.parent;

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
  protected static final String _1 = "-->";
  protected static final String _2 = ".buildtime>";
  protected static final String _3 = ".buildtime>true</";
  protected static final String _4 = ".parent</artifactId>";
  protected static final String _5 = ".site</module>";
  protected static final String _6 = "<";
  protected static final String _7 = "<!--";
  protected static final String _8 = "<!--executionEnvironmentDefault>JavaSE-${javaVersion}</executionEnvironmentDefault-->";
  protected static final String _9 = "</activation>";
  protected static final String _10 = "</additionalFileSets>";
  protected static final String _11 = "</build>";
  protected static final String _12 = "</configuration>";
  protected static final String _13 = "</dependencies>";
  protected static final String _14 = "</dependency-resolution>";
  protected static final String _15 = "</dependency>";
  protected static final String _16 = "</environment>";
  protected static final String _17 = "</environments>";
  protected static final String _18 = "</extraRequirements>";
  protected static final String _19 = "</fileSet>";
  protected static final String _20 = "</groupId>";
  protected static final String _21 = "</includes>";
  protected static final String _22 = "</modules>";
  protected static final String _23 = "</plugin>";
  protected static final String _24 = "</pluginManagement>";
  protected static final String _25 = "</pluginRepositories>";
  protected static final String _26 = "</pluginRepository>";
  protected static final String _27 = "</plugins>";
  protected static final String _28 = "</profile>";
  protected static final String _29 = "</profileProperties>";
  protected static final String _30 = "</profiles>";
  protected static final String _31 = "</project>";
  protected static final String _32 = "</properties>";
  protected static final String _33 = "</property>";
  protected static final String _34 = "</repositories>";
  protected static final String _35 = "</repository>";
  protected static final String _36 = "</requirement>";
  protected static final String _37 = "</sourceReferences>";
  protected static final String _38 = "</target>";
  protected static final String _39 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _40 = "<activation>";
  protected static final String _41 = "<additionalFileSets>";
  protected static final String _42 = "<arch>x86_64</arch>";
  protected static final String _43 = "<artifactId>";
  protected static final String _44 = "<artifactId>eclipse-jarsigner-plugin</artifactId>";
  protected static final String _45 = "<artifactId>target-platform-configuration</artifactId>";
  protected static final String _46 = "<artifactId>tycho-buildtimestamp-jgit</artifactId>";
  protected static final String _47 = "<artifactId>tycho-maven-plugin</artifactId>";
  protected static final String _48 = "<artifactId>tycho-p2-plugin</artifactId>";
  protected static final String _49 = "<artifactId>tycho-p2-publisher-plugin</artifactId>";
  protected static final String _50 = "<artifactId>tycho-pack200a-plugin</artifactId>";
  protected static final String _51 = "<artifactId>tycho-pack200b-plugin</artifactId>";
  protected static final String _52 = "<artifactId>tycho-packaging-plugin</artifactId>";
  protected static final String _53 = "<artifactId>tycho-source-feature-plugin</artifactId>";
  protected static final String _54 = "<artifactId>tycho-source-plugin</artifactId>";
  protected static final String _55 = "<artifactId>tycho-sourceref-jgit</artifactId>";
  protected static final String _56 = "<build>";
  protected static final String _57 = "<configuration>";
  protected static final String _58 = "<dependencies>";
  protected static final String _59 = "<dependency-resolution>";
  protected static final String _60 = "<dependency>";
  protected static final String _61 = "<directory>${project.build.outputDirectory}</directory>";
  protected static final String _62 = "<environment>";
  protected static final String _63 = "<environments>";
  protected static final String _64 = "<extensions>true</extensions>";
  protected static final String _65 = "<extraRequirements>";
  protected static final String _66 = "<fileSet>";
  protected static final String _67 = "<format>'v'yyyyMMdd-HHmm</format>";
  protected static final String _68 = "<generate>true</generate>";
  protected static final String _69 = "<groupId>";
  protected static final String _70 = "<groupId>org.eclipse.cbi.maven.plugins</groupId>";
  protected static final String _71 = "<groupId>org.eclipse.tycho.extras</groupId>";
  protected static final String _72 = "<groupId>org.eclipse.tycho</groupId>";
  protected static final String _73 = "<id>eclipse-cbi-releases</id>";
  protected static final String _74 = "<id>eclipse-maven-releases</id>";
  protected static final String _75 = "<id>license-feature</id>";
  protected static final String _76 = "<id>modules</id>";
  protected static final String _77 = "<id>org.eclipse.license.feature.group</id>";
  protected static final String _78 = "<include>about.mappings</include>";
  protected static final String _79 = "<includePackedArtifacts>true</includePackedArtifacts>";
  protected static final String _80 = "<includes>";
  protected static final String _81 = "<jarsigner-version>1.1.7</jarsigner-version>";
  protected static final String _82 = "<jgit.dirtyWorkingTree>warning</jgit.dirtyWorkingTree>";
  protected static final String _83 = "<jgit.ignore>pom.xml</jgit.ignore>";
  protected static final String _84 = "<layout>p2</layout>";
  protected static final String _85 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _86 = "<module>../";
  protected static final String _87 = "<module>features</module>";
  protected static final String _88 = "<module>plugins</module>";
  protected static final String _89 = "<modules>";
  protected static final String _90 = "<name>MAVEN_BUILD</name>";
  protected static final String _91 = "<os-jvm-flags/>";
  protected static final String _92 = "<os>linux</os>";
  protected static final String _93 = "<os>macosx</os>";
  protected static final String _94 = "<os>win32</os>";
  protected static final String _95 = "<packaging>pom</packaging>";
  protected static final String _96 = "<plugin>";
  protected static final String _97 = "<pluginManagement>";
  protected static final String _98 = "<pluginRepositories>";
  protected static final String _99 = "<pluginRepository>";
  protected static final String _100 = "<plugins>";
  protected static final String _101 = "<profile>";
  protected static final String _102 = "<profileProperties>";
  protected static final String _103 = "<profiles>";
  protected static final String _104 = "<project";
  protected static final String _105 = "<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>";
  protected static final String _106 = "<properties>";
  protected static final String _107 = "<property>";
  protected static final String _108 = "<repositories>";
  protected static final String _109 = "<repository>";
  protected static final String _110 = "<requirement>";
  protected static final String _111 = "<skip>true</skip>";
  protected static final String _112 = "<sourceReferences>";
  protected static final String _113 = "<target>";
  protected static final String _114 = "<timestampProvider>jgit</timestampProvider>";
  protected static final String _115 = "<tycho-version>1.6.0</tycho-version>";
  protected static final String _116 = "<tycho.scmUrl>scm:git:https://git.eclipse.org/r/p/justj/org.eclipse.justj.git</tycho.scmUrl>";
  protected static final String _117 = "<type>p2-installable-unit</type>";
  protected static final String _118 = "<url>http://download.eclipse.org/cbi/updates/license/</url>";
  protected static final String _119 = "<url>https://repo.eclipse.org/content/repositories/cbi-releases</url>";
  protected static final String _120 = "<url>https://repo.eclipse.org/content/repositories/releases</url>";
  protected static final String _121 = "<value>!false</value>";
  protected static final String _122 = "<version>${jarsigner-version}</version>";
  protected static final String _123 = "<version>${tycho-version}</version>";
  protected static final String _124 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _125 = "<versionRange>0.0.0</versionRange>";
  protected static final String _126 = "<ws>cocoa</ws>";
  protected static final String _127 = "<ws>gtk</ws>";
  protected static final String _128 = "<ws>win32</ws>";
  protected static final String _129 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _130 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _131 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "        ";
  protected final String NL_5 = NL + "          ";
  protected final String NL_6 = NL + "            ";
  protected final String NL_7 = NL + "              ";
  protected final String NL_8 = NL + "                ";
  protected final String NL_9 = NL + "                  ";
  protected final String NL_10 = NL + "                    ";
  protected final String _132 = NL + _1 + NL + _104 + NL_2 + _130 + NL_2 + _129 + NL_2 + _131 + NL_1 + _85 + NL + NL_1 + _69;
  protected final String _133 = _20 + NL_1 + _43;
  protected final String _134 = _4 + NL_1 + _124 + NL_1 + _95 + NL + NL_1 + _106 + NL_2 + _105 + NL_2 + _116 + NL_2 + _115 + NL_2 + _81 + NL_2 + _91 + NL_1 + _32 + NL + NL_1 + _98 + NL_2 + _99 + NL_3 + _74 + NL_3 + _120 + NL_2 + _26 + NL_2 + _99 + NL_3 + _73 + NL_3 + _119 + NL_2 + _26 + NL_1 + _25 + NL + NL_1 + _108 + NL_2 + _109 + NL_3 + _75 + NL_3 + _118 + NL_3 + _84 + NL_2 + _35 + NL_1 + _34 + NL + NL_1 + _56 + NL_2 + _100 + NL_3 + _96 + NL_4 + _72 + NL_4 + _47 + NL_4 + _64 + NL_3 + _23 + NL_3 + _96 + NL_4 + _72 + NL_4 + _49 + NL_4 + _123 + NL_4 + _57 + NL_5 + _111 + NL_4 + _12 + NL_3 + _23 + NL_2 + _27 + NL_2 + _97 + NL_3 + _100 + NL_4 + _96 + NL_5 + _72 + NL_5 + _47 + NL_5 + _123 + NL_5 + _64 + NL_4 + _23 + NL_4 + _96 + NL_5 + _72 + NL_5 + _52 + NL_5 + _123 + NL_5 + _57 + NL_6 + _67 + NL_6 + _112 + NL_7 + _68 + NL_6 + _37 + NL_6 + _114 + NL_6 + _83 + NL_6 + _82 + NL_6 + _41 + NL_7 + _66 + NL_8 + _61 + NL_8 + _80 + NL_9 + _78 + NL_8 + _21 + NL_7 + _19 + NL_6 + _10 + NL_5 + _12 + NL_5 + _58 + NL_6 + _60 + NL_7 + _71 + NL_7 + _55 + NL_7 + _123 + NL_6 + _15 + NL_6 + _60 + NL_7 + _71 + NL_7 + _46 + NL_7 + _123 + NL_6 + _15 + NL_5 + _13 + NL_4 + _23 + NL_5 + _96 + NL_6 + _72 + NL_6 + _45 + NL_6 + _123 + NL_6 + _57 + NL_7 + _113 + NL_7 + _38 + NL + NL_7 + _8 + NL_7 + _79 + NL_7 + _63 + NL_8 + _62 + NL_9 + _94 + NL_9 + _128 + NL_9 + _42 + NL_8 + _16 + NL_8 + _62 + NL_9 + _93 + NL_9 + _126 + NL_9 + _42 + NL_8 + _16 + NL_8 + _62 + NL_9 + _92 + NL_9 + _127 + NL_9 + _42 + NL_8 + _16 + NL_7 + _17 + NL_7 + _59 + NL_8 + _65 + NL_9 + _110 + NL_10 + _117 + NL_10 + _77 + NL_10 + _125 + NL_9 + _36 + NL_8 + _18 + NL_8 + _102 + NL_9 + _6;
  protected final String _135 = _2 + NL_8 + _29 + NL_7 + _14 + NL_6 + _12 + NL_5 + _23 + NL_4 + _96 + NL_5 + _72 + NL_5 + _54 + NL_5 + _123 + NL_4 + _23 + NL_4 + _96 + NL_5 + _71 + NL_5 + _50 + NL_5 + _123 + NL_4 + _23 + NL_4 + _96 + NL_5 + _70 + NL_5 + _44 + NL_5 + _122 + NL_4 + _23 + NL_4 + _96 + NL_5 + _71 + NL_5 + _51 + NL_5 + _123 + NL_4 + _23 + NL_4 + _96 + NL_5 + _72 + NL_5 + _48 + NL_5 + _123 + NL_4 + _23 + NL_4 + _96 + NL_5 + _71 + NL_5 + _53 + NL_5 + _123 + NL_4 + _23 + NL_3 + _27 + NL_2 + _24 + NL_1 + _11 + NL + NL_1 + _103 + NL_2 + _101 + NL_3 + _76 + NL_3 + _40 + NL_4 + _107 + NL_5 + _90 + NL_5 + _121 + NL_4 + _33 + NL_3 + _9 + NL_3 + _89 + NL_4 + _87 + NL_4 + _88 + NL_4 + _86;
  protected final String _136 = _5 + NL_3 + _22 + NL_2 + _28 + NL_1 + _30 + NL + NL + _31;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
    builder.append(_39);
    builder.append(NL);
    builder.append(_7);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_132);
    builder.append(name);
    builder.append(_133);
    builder.append(name);
    builder.append(_134);
    builder.append(name);
    builder.append(_3);
    builder.append(name);
    builder.append(_135);
    builder.append(name);
    builder.append(_136);
    builder.append(NL);
    return builder.toString();
  }
}
