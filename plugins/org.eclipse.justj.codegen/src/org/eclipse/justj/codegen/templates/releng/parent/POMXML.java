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
  protected static final String _45 = "<arch>x86_64</arch>";
  protected static final String _46 = "<archive>";
  protected static final String _47 = "<artifactId>";
  protected static final String _48 = "<artifactId>eclipse-jarsigner-plugin</artifactId>";
  protected static final String _49 = "<artifactId>target-platform-configuration</artifactId>";
  protected static final String _50 = "<artifactId>tycho-buildtimestamp-jgit</artifactId>";
  protected static final String _51 = "<artifactId>tycho-maven-plugin</artifactId>";
  protected static final String _52 = "<artifactId>tycho-p2-plugin</artifactId>";
  protected static final String _53 = "<artifactId>tycho-p2-publisher-plugin</artifactId>";
  protected static final String _54 = "<artifactId>tycho-pack200a-plugin</artifactId>";
  protected static final String _55 = "<artifactId>tycho-pack200b-plugin</artifactId>";
  protected static final String _56 = "<artifactId>tycho-packaging-plugin</artifactId>";
  protected static final String _57 = "<artifactId>tycho-source-feature-plugin</artifactId>";
  protected static final String _58 = "<artifactId>tycho-source-plugin</artifactId>";
  protected static final String _59 = "<artifactId>tycho-sourceref-jgit</artifactId>";
  protected static final String _60 = "<build>";
  protected static final String _61 = "<configuration>";
  protected static final String _62 = "<dependencies>";
  protected static final String _63 = "<dependency-resolution>";
  protected static final String _64 = "<dependency>";
  protected static final String _65 = "<directory>${project.build.outputDirectory}</directory>";
  protected static final String _66 = "<environment>";
  protected static final String _67 = "<environments>";
  protected static final String _68 = "<extensions>true</extensions>";
  protected static final String _69 = "<extraRequirements>";
  protected static final String _70 = "<fileSet>";
  protected static final String _71 = "<format>'v'yyyyMMdd-HHmm</format>";
  protected static final String _72 = "<generate>true</generate>";
  protected static final String _73 = "<groupId>";
  protected static final String _74 = "<groupId>org.eclipse.cbi.maven.plugins</groupId>";
  protected static final String _75 = "<groupId>org.eclipse.tycho.extras</groupId>";
  protected static final String _76 = "<groupId>org.eclipse.tycho</groupId>";
  protected static final String _77 = "<id>eclipse-cbi-releases</id>";
  protected static final String _78 = "<id>eclipse-maven-releases</id>";
  protected static final String _79 = "<id>license-feature</id>";
  protected static final String _80 = "<id>modules</id>";
  protected static final String _81 = "<id>org.eclipse.license.feature.group</id>";
  protected static final String _82 = "<id>promote</id>";
  protected static final String _83 = "<include>about.mappings</include>";
  protected static final String _84 = "<includePackedArtifacts>true</includePackedArtifacts>";
  protected static final String _85 = "<includes>";
  protected static final String _86 = "<jarsigner-version>1.3.2</jarsigner-version>";
  protected static final String _87 = "<jgit.dirtyWorkingTree>warning</jgit.dirtyWorkingTree>";
  protected static final String _88 = "<jgit.ignore>pom.xml</jgit.ignore>";
  protected static final String _89 = "<layout>p2</layout>";
  protected static final String _90 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _91 = "<module>../";
  protected static final String _92 = "<module>features</module>";
  protected static final String _93 = "<module>plugins</module>";
  protected static final String _94 = "<module>promotion</module>";
  protected static final String _95 = "<modules>";
  protected static final String _96 = "<name>MAVEN_BUILD</name>";
  protected static final String _97 = "<name>promote</name>";
  protected static final String _98 = "<os-jvm-flags/>";
  protected static final String _99 = "<os>linux</os>";
  protected static final String _100 = "<os>macosx</os>";
  protected static final String _101 = "<os>win32</os>";
  protected static final String _102 = "<packaging>pom</packaging>";
  protected static final String _103 = "<plugin>";
  protected static final String _104 = "<pluginManagement>";
  protected static final String _105 = "<pluginRepositories>";
  protected static final String _106 = "<pluginRepository>";
  protected static final String _107 = "<plugins>";
  protected static final String _108 = "<profile>";
  protected static final String _109 = "<profileProperties>";
  protected static final String _110 = "<profiles>";
  protected static final String _111 = "<project";
  protected static final String _112 = "<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>";
  protected static final String _113 = "<properties>";
  protected static final String _114 = "<property>";
  protected static final String _115 = "<repositories>";
  protected static final String _116 = "<repository>";
  protected static final String _117 = "<requirement>";
  protected static final String _118 = "<skip>true</skip>";
  protected static final String _119 = "<sourceReferences>";
  protected static final String _120 = "<target>";
  protected static final String _121 = "<timestampProvider>jgit</timestampProvider>";
  protected static final String _122 = "<tycho-version>2.7.4</tycho-version>";
  protected static final String _123 = "<tycho.scmUrl>scm:git:https://git.eclipse.org/r/p/justj/org.eclipse.justj.git</tycho.scmUrl>";
  protected static final String _124 = "<type>p2-installable-unit</type>";
  protected static final String _125 = "<url>https://download.eclipse.org/cbi/updates/license/</url>";
  protected static final String _126 = "<url>https://repo.eclipse.org/content/repositories/cbi-releases</url>";
  protected static final String _127 = "<url>https://repo.eclipse.org/content/repositories/releases</url>";
  protected static final String _128 = "<value>!false</value>";
  protected static final String _129 = "<version>${jarsigner-version}</version>";
  protected static final String _130 = "<version>${tycho-version}</version>";
  protected static final String _131 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _132 = "<versionRange>0.0.0</versionRange>";
  protected static final String _133 = "<ws>cocoa</ws>";
  protected static final String _134 = "<ws>gtk</ws>";
  protected static final String _135 = "<ws>win32</ws>";
  protected static final String _136 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _137 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _138 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
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
  protected final String _139 = NL + _1 + NL + _111 + NL_2 + _137 + NL_2 + _136 + NL_2 + _138 + NL_1 + _90 + NL + NL_1 + _73;
  protected final String _140 = _21 + NL_1 + _47;
  protected final String _141 = _4 + NL_1 + _131 + NL_1 + _102 + NL + NL_1 + _113 + NL_2 + _112 + NL_2 + _123 + NL_2 + _122 + NL_2 + _86 + NL_2 + _98 + NL_1 + _33 + NL + NL_1 + _105 + NL_2 + _106 + NL_3 + _78 + NL_3 + _127 + NL_2 + _27 + NL_2 + _106 + NL_3 + _77 + NL_3 + _126 + NL_2 + _27 + NL_1 + _26 + NL + NL_1 + _115 + NL_2 + _116 + NL_3 + _79 + NL_3 + _125 + NL_3 + _89 + NL_2 + _36 + NL_1 + _35 + NL + NL_1 + _60 + NL_2 + _107 + NL_3 + _103 + NL_4 + _76 + NL_4 + _51 + NL_4 + _68 + NL_3 + _24 + NL_3 + _103 + NL_4 + _76 + NL_4 + _53 + NL_4 + _130 + NL_4 + _61 + NL_5 + _118 + NL_4 + _13 + NL_3 + _24 + NL_2 + _28 + NL_2 + _104 + NL_3 + _107 + NL_4 + _103 + NL_5 + _76 + NL_5 + _51 + NL_5 + _130 + NL_5 + _68 + NL_4 + _24 + NL_4 + _103 + NL_5 + _76 + NL_5 + _56 + NL_5 + _130 + NL_5 + _61 + NL_6 + _71 + NL_6 + _119 + NL_7 + _72 + NL_6 + _38 + NL_6 + _121 + NL_6 + _88 + NL_6 + _87 + NL_6 + _46 + NL_7 + _42 + NL_6 + _11 + NL_6 + _43 + NL_7 + _70 + NL_8 + _65 + NL_8 + _85 + NL_9 + _83 + NL_8 + _22 + NL_7 + _20 + NL_6 + _10 + NL_5 + _13 + NL_5 + _62 + NL_6 + _64 + NL_7 + _75 + NL_7 + _59 + NL_7 + _130 + NL_6 + _16 + NL_6 + _64 + NL_7 + _75 + NL_7 + _50 + NL_7 + _130 + NL_6 + _16 + NL_5 + _14 + NL_4 + _24 + NL_5 + _103 + NL_6 + _76 + NL_6 + _49 + NL_6 + _130 + NL_6 + _61 + NL_7 + _120 + NL_7 + _39 + NL + NL_7 + _8 + NL_7 + _84 + NL_7 + _67 + NL_8 + _66 + NL_9 + _101 + NL_9 + _135 + NL_9 + _45 + NL_8 + _17 + NL_8 + _66 + NL_9 + _100 + NL_9 + _133 + NL_9 + _45 + NL_8 + _17 + NL_8 + _66 + NL_9 + _100 + NL_9 + _133 + NL_9 + _44 + NL_8 + _17 + NL_8 + _66 + NL_9 + _99 + NL_9 + _134 + NL_9 + _45 + NL_8 + _17 + NL_8 + _66 + NL_9 + _99 + NL_9 + _134 + NL_9 + _44 + NL_8 + _17 + NL_7 + _18 + NL_7 + _63 + NL_8 + _69 + NL_9 + _117 + NL_10 + _124 + NL_10 + _81 + NL_10 + _132 + NL_9 + _37 + NL_8 + _19 + NL_8 + _109 + NL_9 + _6;
  protected final String _142 = _2 + NL_8 + _30 + NL_7 + _15 + NL_6 + _13 + NL_5 + _24 + NL_4 + _103 + NL_5 + _76 + NL_5 + _58 + NL_5 + _130 + NL_4 + _24 + NL_4 + _103 + NL_5 + _75 + NL_5 + _54 + NL_5 + _130 + NL_4 + _24 + NL_4 + _103 + NL_5 + _74 + NL_5 + _48 + NL_5 + _129 + NL_4 + _24 + NL_4 + _103 + NL_5 + _75 + NL_5 + _55 + NL_5 + _130 + NL_4 + _24 + NL_4 + _103 + NL_5 + _76 + NL_5 + _52 + NL_5 + _130 + NL_4 + _24 + NL_4 + _103 + NL_5 + _75 + NL_5 + _57 + NL_5 + _130 + NL_4 + _24 + NL_3 + _28 + NL_2 + _25 + NL_1 + _12 + NL + NL_1 + _110 + NL_2 + _108 + NL_3 + _80 + NL_3 + _41 + NL_4 + _114 + NL_5 + _96 + NL_5 + _128 + NL_4 + _34 + NL_3 + _9 + NL_3 + _95 + NL_4 + _92 + NL_4 + _93 + NL_4 + _91;
  protected final String _143 = _5 + NL_3 + _23 + NL_2 + _29 + NL_2 + _108 + NL_3 + _82 + NL_3 + _41 + NL_4 + _114 + NL_5 + _97 + NL_5 + _128 + NL_4 + _34 + NL_3 + _9 + NL_3 + _95 + NL_4 + _94 + NL_3 + _23 + NL_2 + _29 + NL_1 + _31 + NL + NL + _32;

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
    builder.append(_139);
    builder.append(name);
    builder.append(_140);
    builder.append(name);
    builder.append(_141);
    builder.append(name);
    builder.append(_3);
    builder.append(name);
    builder.append(_142);
    builder.append(name);
    builder.append(_143);
    builder.append(NL);
    return builder.toString();
  }
}
