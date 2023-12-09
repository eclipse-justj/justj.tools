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
  protected static final String _11 = "</archive>";
  protected static final String _12 = "</build>";
  protected static final String _13 = "</configuration>";
  protected static final String _14 = "</dependencies>";
  protected static final String _15 = "</dependency-resolution>";
  protected static final String _16 = "</dependency>";
  protected static final String _17 = "</environment>";
  protected static final String _18 = "</environments>";
  protected static final String _19 = "</extraRequirements>";
  protected static final String _20 = "</fileSet>";
  protected static final String _21 = "</groupId>";
  protected static final String _22 = "</includes>";
  protected static final String _23 = "</modules>";
  protected static final String _24 = "</plugin>";
  protected static final String _25 = "</pluginManagement>";
  protected static final String _26 = "</pluginRepositories>";
  protected static final String _27 = "</pluginRepository>";
  protected static final String _28 = "</plugins>";
  protected static final String _29 = "</profile>";
  protected static final String _30 = "</profileProperties>";
  protected static final String _31 = "</profiles>";
  protected static final String _32 = "</project>";
  protected static final String _33 = "</properties>";
  protected static final String _34 = "</property>";
  protected static final String _35 = "</repositories>";
  protected static final String _36 = "</repository>";
  protected static final String _37 = "</requirement>";
  protected static final String _38 = "</sourceReferences>";
  protected static final String _39 = "</target>";
  protected static final String _40 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _41 = "<activation>";
  protected static final String _42 = "<addMavenDescriptor>false</addMavenDescriptor>";
  protected static final String _43 = "<additionalFileSets>";
  protected static final String _44 = "<arch>aarch64</arch>";
  protected static final String _45 = "<arch>ppc64le</arch>";
  protected static final String _46 = "<arch>x86_64</arch>";
  protected static final String _47 = "<archive>";
  protected static final String _48 = "<artifactId>";
  protected static final String _49 = "<artifactId>eclipse-jarsigner-plugin</artifactId>";
  protected static final String _50 = "<artifactId>target-platform-configuration</artifactId>";
  protected static final String _51 = "<artifactId>tycho-buildtimestamp-jgit</artifactId>";
  protected static final String _52 = "<artifactId>tycho-maven-plugin</artifactId>";
  protected static final String _53 = "<artifactId>tycho-p2-plugin</artifactId>";
  protected static final String _54 = "<artifactId>tycho-p2-publisher-plugin</artifactId>";
  protected static final String _55 = "<artifactId>tycho-packaging-plugin</artifactId>";
  protected static final String _56 = "<artifactId>tycho-sourceref-jgit</artifactId>";
  protected static final String _57 = "<build>";
  protected static final String _58 = "<configuration>";
  protected static final String _59 = "<dependencies>";
  protected static final String _60 = "<dependency-resolution>";
  protected static final String _61 = "<dependency>";
  protected static final String _62 = "<directory>${project.build.outputDirectory}</directory>";
  protected static final String _63 = "<environment>";
  protected static final String _64 = "<environments>";
  protected static final String _65 = "<extensions>true</extensions>";
  protected static final String _66 = "<extraRequirements>";
  protected static final String _67 = "<fileSet>";
  protected static final String _68 = "<format>'v'yyyyMMdd-HHmm</format>";
  protected static final String _69 = "<generate>true</generate>";
  protected static final String _70 = "<groupId>";
  protected static final String _71 = "<groupId>org.eclipse.cbi.maven.plugins</groupId>";
  protected static final String _72 = "<groupId>org.eclipse.tycho.extras</groupId>";
  protected static final String _73 = "<groupId>org.eclipse.tycho</groupId>";
  protected static final String _74 = "<id>eclipse-cbi-releases</id>";
  protected static final String _75 = "<id>eclipse-maven-releases</id>";
  protected static final String _76 = "<id>license-feature</id>";
  protected static final String _77 = "<id>modules</id>";
  protected static final String _78 = "<id>org.eclipse.license.feature.group</id>";
  protected static final String _79 = "<id>promote</id>";
  protected static final String _80 = "<include>about.mappings</include>";
  protected static final String _81 = "<includes>";
  protected static final String _82 = "<jarsigner-version>1.3.2</jarsigner-version>";
  protected static final String _83 = "<jgit.dirtyWorkingTree>warning</jgit.dirtyWorkingTree>";
  protected static final String _84 = "<jgit.ignore>pom.xml</jgit.ignore>";
  protected static final String _85 = "<layout>p2</layout>";
  protected static final String _86 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _87 = "<module>../";
  protected static final String _88 = "<module>features</module>";
  protected static final String _89 = "<module>plugins</module>";
  protected static final String _90 = "<module>promotion</module>";
  protected static final String _91 = "<modules>";
  protected static final String _92 = "<name>MAVEN_BUILD</name>";
  protected static final String _93 = "<name>promote</name>";
  protected static final String _94 = "<os-jvm-flags/>";
  protected static final String _95 = "<os>linux</os>";
  protected static final String _96 = "<os>macosx</os>";
  protected static final String _97 = "<os>win32</os>";
  protected static final String _98 = "<packaging>pom</packaging>";
  protected static final String _99 = "<plugin>";
  protected static final String _100 = "<pluginManagement>";
  protected static final String _101 = "<pluginRepositories>";
  protected static final String _102 = "<pluginRepository>";
  protected static final String _103 = "<plugins>";
  protected static final String _104 = "<profile>";
  protected static final String _105 = "<profileProperties>";
  protected static final String _106 = "<profiles>";
  protected static final String _107 = "<project";
  protected static final String _108 = "<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>";
  protected static final String _109 = "<properties>";
  protected static final String _110 = "<property>";
  protected static final String _111 = "<repositories>";
  protected static final String _112 = "<repository>";
  protected static final String _113 = "<requirement>";
  protected static final String _114 = "<skip>true</skip>";
  protected static final String _115 = "<sourceReferences>";
  protected static final String _116 = "<target>";
  protected static final String _117 = "<timestampProvider>jgit</timestampProvider>";
  protected static final String _118 = "<tycho-version>4.0.4</tycho-version>";
  protected static final String _119 = "<tycho.scmUrl>scm:git:https://github.com/eclipse-justj/justj.git</tycho.scmUrl>";
  protected static final String _120 = "<type>p2-installable-unit</type>";
  protected static final String _121 = "<url>https://download.eclipse.org/cbi/updates/license/</url>";
  protected static final String _122 = "<url>https://repo.eclipse.org/content/repositories/cbi-releases</url>";
  protected static final String _123 = "<url>https://repo.eclipse.org/content/repositories/releases</url>";
  protected static final String _124 = "<value>!false</value>";
  protected static final String _125 = "<version>${jarsigner-version}</version>";
  protected static final String _126 = "<version>${tycho-version}</version>";
  protected static final String _127 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _128 = "<versionRange>0.0.0</versionRange>";
  protected static final String _129 = "<ws>cocoa</ws>";
  protected static final String _130 = "<ws>gtk</ws>";
  protected static final String _131 = "<ws>win32</ws>";
  protected static final String _132 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _133 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _134 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
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
  protected final String _135 = NL + _1 + NL + _107 + NL_2 + _133 + NL_2 + _132 + NL_2 + _134 + NL_1 + _86 + NL + NL_1 + _70;
  protected final String _136 = _21 + NL_1 + _48;
  protected final String _137 = _4 + NL_1 + _127 + NL_1 + _98 + NL + NL_1 + _109 + NL_2 + _108 + NL_2 + _119 + NL_2 + _118 + NL_2 + _82 + NL_2 + _94 + NL_1 + _33 + NL + NL_1 + _101 + NL_2 + _102 + NL_3 + _75 + NL_3 + _123 + NL_2 + _27 + NL_2 + _102 + NL_3 + _74 + NL_3 + _122 + NL_2 + _27 + NL_1 + _26 + NL + NL_1 + _111 + NL_2 + _112 + NL_3 + _76 + NL_3 + _121 + NL_3 + _85 + NL_2 + _36 + NL_1 + _35 + NL + NL_1 + _57 + NL_2 + _103 + NL_3 + _99 + NL_4 + _73 + NL_4 + _52 + NL_4 + _65 + NL_3 + _24 + NL_3 + _99 + NL_4 + _73 + NL_4 + _54 + NL_4 + _126 + NL_4 + _58 + NL_5 + _114 + NL_4 + _13 + NL_3 + _24 + NL_2 + _28 + NL_2 + _100 + NL_3 + _103 + NL_4 + _99 + NL_5 + _73 + NL_5 + _52 + NL_5 + _126 + NL_5 + _65 + NL_4 + _24 + NL_4 + _99 + NL_5 + _73 + NL_5 + _55 + NL_5 + _126 + NL_5 + _58 + NL_6 + _68 + NL_6 + _115 + NL_7 + _69 + NL_6 + _38 + NL_6 + _117 + NL_6 + _84 + NL_6 + _83 + NL_6 + _47 + NL_7 + _42 + NL_6 + _11 + NL_6 + _43 + NL_7 + _67 + NL_8 + _62 + NL_8 + _81 + NL_9 + _80 + NL_8 + _22 + NL_7 + _20 + NL_6 + _10 + NL_5 + _13 + NL_5 + _59 + NL_6 + _61 + NL_7 + _72 + NL_7 + _56 + NL_7 + _126 + NL_6 + _16 + NL_6 + _61 + NL_7 + _73 + NL_7 + _51 + NL_7 + _126 + NL_6 + _16 + NL_5 + _14 + NL_4 + _24 + NL_5 + _99 + NL_6 + _73 + NL_6 + _50 + NL_6 + _126 + NL_6 + _58 + NL_7 + _116 + NL_7 + _39 + NL + NL_7 + _8 + NL_7 + _64 + NL_8 + _63 + NL_9 + _97 + NL_9 + _131 + NL_9 + _46 + NL_8 + _17 + NL_8 + _63 + NL_9 + _96 + NL_9 + _129 + NL_9 + _46 + NL_8 + _17 + NL_8 + _63 + NL_9 + _96 + NL_9 + _129 + NL_9 + _44 + NL_8 + _17 + NL_8 + _63 + NL_9 + _95 + NL_9 + _130 + NL_9 + _46 + NL_8 + _17 + NL_8 + _63 + NL_9 + _95 + NL_9 + _130 + NL_9 + _44 + NL_8 + _17 + NL_8 + _63 + NL_9 + _95 + NL_9 + _130 + NL_9 + _45 + NL_8 + _17 + NL_7 + _18 + NL_7 + _60 + NL_8 + _66 + NL_9 + _113 + NL_10 + _120 + NL_10 + _78 + NL_10 + _128 + NL_9 + _37 + NL_8 + _19 + NL_8 + _105 + NL_9 + _6;
  protected final String _138 = _2 + NL_8 + _30 + NL_7 + _15 + NL_6 + _13 + NL_5 + _24 + NL_4 + _99 + NL_5 + _71 + NL_5 + _49 + NL_5 + _125 + NL_4 + _24 + NL_4 + _99 + NL_5 + _73 + NL_5 + _53 + NL_5 + _126 + NL_4 + _24 + NL_3 + _28 + NL_2 + _25 + NL_1 + _12 + NL + NL_1 + _106 + NL_2 + _104 + NL_3 + _77 + NL_3 + _41 + NL_4 + _110 + NL_5 + _92 + NL_5 + _124 + NL_4 + _34 + NL_3 + _9 + NL_3 + _91 + NL_4 + _88 + NL_4 + _89 + NL_4 + _87;
  protected final String _139 = _5 + NL_3 + _23 + NL_2 + _29 + NL_2 + _104 + NL_3 + _79 + NL_3 + _41 + NL_4 + _110 + NL_5 + _93 + NL_5 + _124 + NL_4 + _34 + NL_3 + _9 + NL_3 + _91 + NL_4 + _90 + NL_3 + _23 + NL_2 + _29 + NL_1 + _31 + NL + NL + _32;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
    builder.append(_40);
    builder.append(NL);
    builder.append(_7);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_135);
    builder.append(name);
    builder.append(_136);
    builder.append(name);
    builder.append(_137);
    builder.append(name);
    builder.append(_3);
    builder.append(name);
    builder.append(_138);
    builder.append(name);
    builder.append(_139);
    builder.append(NL);
    return builder.toString();
  }
}
