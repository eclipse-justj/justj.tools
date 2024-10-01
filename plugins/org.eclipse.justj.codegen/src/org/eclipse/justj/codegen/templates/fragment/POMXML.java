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
package org.eclipse.justj.codegen.templates.fragment;

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
  protected static final String _6 = "</arch>";
  protected static final String _7 = "</artifactId>";
  protected static final String _8 = "</build>";
  protected static final String _9 = "</configuration>";
  protected static final String _10 = "</environment>";
  protected static final String _11 = "</environments>";
  protected static final String _12 = "</groupId>";
  protected static final String _13 = "</includes>";
  protected static final String _14 = "</os>";
  protected static final String _15 = "</parent>";
  protected static final String _16 = "</plugin>";
  protected static final String _17 = "</plugins>";
  protected static final String _18 = "</project>";
  protected static final String _19 = "</resource>";
  protected static final String _20 = "</resources>";
  protected static final String _21 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _22 = "<arch>";
  protected static final String _23 = "<artifactId>";
  protected static final String _24 = "<artifactId>target-platform-configuration</artifactId>";
  protected static final String _25 = "<build>";
  protected static final String _26 = "<configuration>";
  protected static final String _27 = "<directory>.</directory>";
  protected static final String _28 = "<environment>";
  protected static final String _29 = "<environments>";
  protected static final String _30 = "<filtering>true</filtering>";
  protected static final String _31 = "<groupId>";
  protected static final String _32 = "<groupId>org.eclipse.tycho</groupId>";
  protected static final String _33 = "<include>about.mappings</include>";
  protected static final String _34 = "<includes>";
  protected static final String _35 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _36 = "<os>";
  protected static final String _37 = "<packaging>eclipse-plugin</packaging>";
  protected static final String _38 = "<parent>";
  protected static final String _39 = "<plugin>";
  protected static final String _40 = "<plugins>";
  protected static final String _41 = "<project";
  protected static final String _42 = "<relativePath>../../releng/";
  protected static final String _43 = "<resource>";
  protected static final String _44 = "<resources>";
  protected static final String _45 = "<version>";
  protected static final String _46 = "<version>${tycho-version}</version>";
  protected static final String _47 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _48 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _49 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _50 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "        ";
  protected final String NL_5 = NL + "          ";
  protected final String NL_6 = NL + "            ";
  protected final String NL_7 = NL + "              ";
  protected final String _51 = NL + _1 + NL + _41 + NL_2 + _49 + NL_2 + _48 + NL_2 + _50 + NL_1 + _35 + NL + NL_1 + _38 + NL_2 + _31;
  protected final String _52 = _12 + NL_2 + _23;
  protected final String _53 = _4 + NL_2 + _47 + NL_2 + _42;
  protected final String _54 = _3 + NL_1 + _15 + NL + NL_1 + _31;
  protected final String _55 = _12 + NL_1 + _23;
  protected final String _56 = _7 + NL_1 + _45;
  protected final String _57 = _2 + NL_1 + _37 + NL + NL_1 + _25 + NL_2 + _44 + NL_3 + _43 + NL_4 + _27 + NL_4 + _30 + NL_4 + _34 + NL_5 + _33 + NL_4 + _13 + NL_3 + _19 + NL_2 + _20 + NL_2 + _40 + NL_3 + _39 + NL_4 + _32 + NL_4 + _24 + NL_4 + _46 + NL_4 + _26 + NL_5 + _29 + NL_6 + _28 + NL_7 + _36;
  protected final String _58 = _14 + NL_7 + _22;
  protected final String _59 = _6 + NL_6 + _10 + NL_5 + _11 + NL_4 + _9 + NL_3 + _16 + NL_2 + _17 + NL_1 + _8 + NL + NL + _18;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Variant variant = (Variant)argument;
  String os = variant.getOs();
  String arch = variant.getArch();
  JVM jvm = variant.getJVM();
  String jvmName = jvm.getName();
  Model model = jvm.getModel();
  String modelName = model.getName();
  String fullName = modelName + '.' + jvmName + '.' + os + '.' + arch;
  String version = jvm.getVersion();
    builder.append(_21);
    builder.append(NL);
    builder.append(_5);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_51);
    builder.append(modelName);
    builder.append(_52);
    builder.append(modelName);
    builder.append(_53);
    builder.append(modelName);
    builder.append(_54);
    builder.append(modelName);
    builder.append(_55);
    builder.append(fullName);
    builder.append(_56);
    builder.append(version);
    builder.append(_57);
    builder.append(os);
    builder.append(_58);
    builder.append(arch);
    builder.append(_59);
    builder.append(NL);
    return builder.toString();
  }
}
