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
package org.eclipse.justj.codegen.templates.plugin;

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
  protected static final String _2 = "-SNAPSHOT</version>";
  protected static final String _3 = ".parent/plugins</relativePath>";
  protected static final String _4 = ".plugins</artifactId>";
  protected static final String _5 = "<!--";
  protected static final String _6 = "</artifactId>";
  protected static final String _7 = "</build>";
  protected static final String _8 = "</groupId>";
  protected static final String _9 = "</includes>";
  protected static final String _10 = "</parent>";
  protected static final String _11 = "</project>";
  protected static final String _12 = "</resource>";
  protected static final String _13 = "</resources>";
  protected static final String _14 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _15 = "<artifactId>";
  protected static final String _16 = "<build>";
  protected static final String _17 = "<directory>.</directory>";
  protected static final String _18 = "<filtering>true</filtering>";
  protected static final String _19 = "<groupId>";
  protected static final String _20 = "<include>about.mappings</include>";
  protected static final String _21 = "<includes>";
  protected static final String _22 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _23 = "<packaging>eclipse-plugin</packaging>";
  protected static final String _24 = "<parent>";
  protected static final String _25 = "<project";
  protected static final String _26 = "<relativePath>../../releng/";
  protected static final String _27 = "<resource>";
  protected static final String _28 = "<resources>";
  protected static final String _29 = "<version>";
  protected static final String _30 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _31 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _32 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _33 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "        ";
  protected final String NL_5 = NL + "          ";
  protected final String _34 = NL + _1 + NL + _25 + NL_2 + _32 + NL_2 + _31 + NL_2 + _33 + NL_1 + _22 + NL + NL_1 + _24 + NL_2 + _19;
  protected final String _35 = _8 + NL_2 + _15;
  protected final String _36 = _4 + NL_2 + _30 + NL_2 + _26;
  protected final String _37 = _3 + NL_1 + _10 + NL + NL_1 + _19;
  protected final String _38 = _8 + NL_1 + _15;
  protected final String _39 = _6 + NL_1 + _29;
  protected final String _40 = _2 + NL_1 + _23 + NL + NL_1 + _16 + NL_2 + _28 + NL_3 + _27 + NL_4 + _17 + NL_4 + _18 + NL_4 + _21 + NL_5 + _20 + NL_4 + _9 + NL_3 + _12 + NL_2 + _13 + NL_1 + _7 + NL + NL + _11;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmName = jvm.getName();
  Model model = jvm.getModel();
  String modelName = model.getName();
  String fullName = modelName + '.' + jvmName;
  String version = jvm.getVersion();
    builder.append(_14);
    builder.append(NL);
    builder.append(_5);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_34);
    builder.append(modelName);
    builder.append(_35);
    builder.append(modelName);
    builder.append(_36);
    builder.append(modelName);
    builder.append(_37);
    builder.append(modelName);
    builder.append(_38);
    builder.append(fullName);
    builder.append(_39);
    builder.append(version);
    builder.append(_40);
    builder.append(NL);
    return builder.toString();
  }
}
