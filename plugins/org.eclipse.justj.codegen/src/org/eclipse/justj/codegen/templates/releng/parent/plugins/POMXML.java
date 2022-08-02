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
package org.eclipse.justj.codegen.templates.releng.parent.plugins;

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
  protected static final String _2 = ".";
  protected static final String _3 = ".parent</artifactId>";
  protected static final String _4 = ".plugins</artifactId>";
  protected static final String _5 = "<!--";
  protected static final String _6 = "</activation>";
  protected static final String _7 = "</build>";
  protected static final String _8 = "</configuration>";
  protected static final String _9 = "</execution>";
  protected static final String _10 = "</executions>";
  protected static final String _11 = "</goals>";
  protected static final String _12 = "</groupId>";
  protected static final String _13 = "</module>";
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
  protected static final String _27 = "<artifactId>tycho-source-plugin</artifactId>";
  protected static final String _28 = "<build>";
  protected static final String _29 = "<configuration>";
  protected static final String _30 = "<defaultP2Metadata>false</defaultP2Metadata>";
  protected static final String _31 = "<execution>";
  protected static final String _32 = "<executions>";
  protected static final String _33 = "<goal>p2-metadata</goal>";
  protected static final String _34 = "<goal>plugin-source</goal>";
  protected static final String _35 = "<goal>sign</goal>";
  protected static final String _36 = "<goals>";
  protected static final String _37 = "<groupId>";
  protected static final String _38 = "<groupId>org.eclipse.cbi.maven.plugins</groupId>";
  protected static final String _39 = "<groupId>org.eclipse.tycho</groupId>";
  protected static final String _40 = "<id>attach-p2-metadata</id>";
  protected static final String _41 = "<id>attach-source</id>";
  protected static final String _42 = "<id>pack-and-sign</id>";
  protected static final String _43 = "<id>sign</id>";
  protected static final String _44 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _45 = "<module>../../../plugins/";
  protected static final String _46 = "<modules>";
  protected static final String _47 = "<name>PACK_AND_SIGN</name>";
  protected static final String _48 = "<packaging>pom</packaging>";
  protected static final String _49 = "<parent>";
  protected static final String _50 = "<phase>package</phase>";
  protected static final String _51 = "<plugin>";
  protected static final String _52 = "<plugins>";
  protected static final String _53 = "<profile>";
  protected static final String _54 = "<profiles>";
  protected static final String _55 = "<project";
  protected static final String _56 = "<property>";
  protected static final String _57 = "<relativePath>..</relativePath>";
  protected static final String _58 = "<value>true</value>";
  protected static final String _59 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _60 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _61 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _62 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "        ";
  protected final String NL_5 = NL + "          ";
  protected final String NL_6 = NL + "            ";
  protected final String NL_7 = NL + "              ";
  protected final String NL_8 = NL + "                ";
  protected final String NL_9 = NL + "                  ";
  protected final String _63 = NL + _1 + NL + _55 + NL_2 + _61 + NL_2 + _60 + NL_2 + _62 + NL_1 + _44 + NL + NL_1 + _49 + NL_2 + _37;
  protected final String _64 = _12 + NL_2 + _24;
  protected final String _65 = _3 + NL_2 + _59 + NL_2 + _57 + NL_1 + _15 + NL + NL_1 + _37;
  protected final String _66 = _12 + NL_1 + _24;
  protected final String _67 = _4 + NL_1 + _59 + NL_1 + _48 + NL + NL_1 + _46;
  protected final String _68 = NL_2 + _45;
  protected final String _69 = NL_1 + _14 + NL + NL_1 + _28 + NL_2 + _52 + NL_3 + _51 + NL_4 + _39 + NL_4 + _27 + NL_4 + _32 + NL_5 + _31 + NL_6 + _41 + NL_6 + _36 + NL_7 + _34 + NL_6 + _11 + NL_5 + _9 + NL_4 + _10 + NL_3 + _16 + NL_2 + _17 + NL_1 + _7 + NL + NL_1 + _54 + NL_2 + _53 + NL_3 + _42 + NL_3 + _23 + NL_4 + _56 + NL_5 + _47 + NL_5 + _58 + NL_4 + _21 + NL_3 + _6 + NL_3 + _28 + NL_4 + _52 + NL_5 + _51 + NL_6 + _38 + NL_6 + _25 + NL_6 + _32 + NL_7 + _31 + NL_8 + _43 + NL_8 + _36 + NL_9 + _35 + NL_8 + _11 + NL_7 + _9 + NL_6 + _10 + NL_5 + _16 + NL_5 + _51 + NL_6 + _39 + NL_6 + _26 + NL_6 + _32 + NL_7 + _31 + NL_8 + _40 + NL_8 + _50 + NL_8 + _36 + NL_9 + _33 + NL_8 + _11 + NL_7 + _9 + NL_6 + _10 + NL_6 + _29 + NL_7 + _30 + NL_6 + _8 + NL_5 + _16 + NL_4 + _17 + NL_3 + _7 + NL_2 + _18 + NL_1 + _19 + NL + NL + _20;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
    builder.append(_22);
    builder.append(NL);
    builder.append(_5);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_63);
    builder.append(name);
    builder.append(_64);
    builder.append(name);
    builder.append(_65);
    builder.append(name);
    builder.append(_66);
    builder.append(name);
    builder.append(_67);
    for (JVM jvm : model.getJVMs()) {
    builder.append(_68);
    builder.append(name);
    builder.append(_2);
    builder.append(jvm.getName());
    builder.append(_13);
    for (Variant variant : jvm.getVariants()) {
    builder.append(_68);
    builder.append(name);
    builder.append(_2);
    builder.append(jvm.getName());
    builder.append(_2);
    builder.append(variant.getOs());
    builder.append(_2);
    builder.append(variant.getArch());
    builder.append(_13);
    }
    }
    builder.append(_69);
    builder.append(NL);
    return builder.toString();
  }
}
