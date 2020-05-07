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
package org.eclipse.justj.codegen.templates.releng.parent.features;

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
  protected static final String _2 = "-feature</module>";
  protected static final String _3 = ".";
  protected static final String _4 = ".features</artifactId>";
  protected static final String _5 = ".parent</artifactId>";
  protected static final String _6 = "<!--";
  protected static final String _7 = "</activation>";
  protected static final String _8 = "</build>";
  protected static final String _9 = "</configuration>";
  protected static final String _10 = "</execution>";
  protected static final String _11 = "</executions>";
  protected static final String _12 = "</goals>";
  protected static final String _13 = "</groupId>";
  protected static final String _14 = "</modules>";
  protected static final String _15 = "</parent>";
  protected static final String _16 = "</plugin>";
  protected static final String _17 = "</plugins>";
  protected static final String _18 = "</profile>";
  protected static final String _19 = "</profiles>";
  protected static final String _20 = "</project>";
  protected static final String _21 = "</property>";
  protected static final String _22 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _23 = "<activation>";
  protected static final String _24 = "<artifactId>";
  protected static final String _25 = "<artifactId>eclipse-jarsigner-plugin</artifactId>";
  protected static final String _26 = "<artifactId>tycho-p2-plugin</artifactId>";
  protected static final String _27 = "<attachP2Metadata>false</attachP2Metadata>";
  protected static final String _28 = "<build>";
  protected static final String _29 = "<configuration>";
  protected static final String _30 = "<execution>";
  protected static final String _31 = "<executions>";
  protected static final String _32 = "<goal>p2-metadata</goal>";
  protected static final String _33 = "<goal>sign</goal>";
  protected static final String _34 = "<goals>";
  protected static final String _35 = "<groupId>";
  protected static final String _36 = "<groupId>org.eclipse.cbi.maven.plugins</groupId>";
  protected static final String _37 = "<groupId>org.eclipse.tycho</groupId>";
  protected static final String _38 = "<id>attach-p2-metadata</id>";
  protected static final String _39 = "<id>default-p2-metadata-default</id>";
  protected static final String _40 = "<id>pack-and-sign</id>";
  protected static final String _41 = "<id>sign</id>";
  protected static final String _42 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _43 = "<module>../../../features/";
  protected static final String _44 = "<modules>";
  protected static final String _45 = "<name>PACK_AND_SIGN</name>";
  protected static final String _46 = "<packaging>pom</packaging>";
  protected static final String _47 = "<parent>";
  protected static final String _48 = "<phase>package</phase>";
  protected static final String _49 = "<plugin>";
  protected static final String _50 = "<plugins>";
  protected static final String _51 = "<profile>";
  protected static final String _52 = "<profiles>";
  protected static final String _53 = "<project";
  protected static final String _54 = "<property>";
  protected static final String _55 = "<relativePath>..</relativePath>";
  protected static final String _56 = "<value>true</value>";
  protected static final String _57 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _58 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _59 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _60 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "        ";
  protected final String NL_5 = NL + "          ";
  protected final String NL_6 = NL + "            ";
  protected final String NL_7 = NL + "              ";
  protected final String NL_8 = NL + "                ";
  protected final String NL_9 = NL + "                  ";
  protected final String _61 = NL + _1 + NL + _53 + NL_2 + _59 + NL_2 + _58 + NL_2 + _60 + NL_1 + _42 + NL + NL_1 + _47 + NL_2 + _35;
  protected final String _62 = _13 + NL_2 + _24;
  protected final String _63 = _5 + NL_2 + _57 + NL_2 + _55 + NL_1 + _15 + NL + NL_1 + _35;
  protected final String _64 = _13 + NL_1 + _24;
  protected final String _65 = _4 + NL_1 + _57 + NL_1 + _46 + NL + NL_1 + _44;
  protected final String _66 = NL_2 + _43;
  protected final String _67 = NL_1 + _14 + NL + NL_1 + _28 + NL_2 + _50 + NL_3 + _49 + NL_4 + _37 + NL_4 + _26 + NL_4 + _31 + NL_5 + _30 + NL_6 + _39 + NL_6 + _29 + NL_7 + _27 + NL_6 + _9 + NL_5 + _10 + NL_5 + _30 + NL_6 + _38 + NL_6 + _48 + NL_6 + _34 + NL_7 + _32 + NL_6 + _12 + NL_5 + _10 + NL_4 + _11 + NL_3 + _16 + NL_2 + _17 + NL_1 + _8 + NL + NL_1 + _52 + NL_2 + _51 + NL_3 + _40 + NL_3 + _23 + NL_4 + _54 + NL_5 + _45 + NL_5 + _56 + NL_4 + _21 + NL_3 + _7 + NL_3 + _28 + NL_4 + _50 + NL_5 + _49 + NL_6 + _36 + NL_6 + _25 + NL_6 + _31 + NL_7 + _30 + NL_8 + _41 + NL_8 + _34 + NL_9 + _33 + NL_8 + _12 + NL_7 + _10 + NL_6 + _11 + NL_5 + _16 + NL_5 + _49 + NL_6 + _37 + NL_6 + _26 + NL_5 + _16 + NL_4 + _17 + NL_3 + _8 + NL_2 + _18 + NL_1 + _19 + NL + NL + _20;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
    builder.append(_22);
    builder.append(NL);
    builder.append(_6);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_61);
    builder.append(name);
    builder.append(_62);
    builder.append(name);
    builder.append(_63);
    builder.append(name);
    builder.append(_64);
    builder.append(name);
    builder.append(_65);
    for (JVM jvm : model.getJVMs()) {
    builder.append(_66);
    builder.append(name);
    builder.append(_3);
    builder.append(jvm.getName());
    builder.append(_2);
    }
    builder.append(_67);
    builder.append(NL);
    return builder.toString();
  }
}
