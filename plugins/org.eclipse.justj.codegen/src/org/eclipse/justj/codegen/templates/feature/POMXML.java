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
package org.eclipse.justj.codegen.templates.feature;

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
  protected static final String _3 = ".features</artifactId>";
  protected static final String _4 = ".features</groupId>";
  protected static final String _5 = ".parent/features</relativePath>";
  protected static final String _6 = "<!--";
  protected static final String _7 = "</artifactId>";
  protected static final String _8 = "</groupId>";
  protected static final String _9 = "</parent>";
  protected static final String _10 = "</project>";
  protected static final String _11 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _12 = "<artifactId>";
  protected static final String _13 = "<groupId>";
  protected static final String _14 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _15 = "<packaging>eclipse-feature</packaging>";
  protected static final String _16 = "<parent>";
  protected static final String _17 = "<project";
  protected static final String _18 = "<relativePath>../../releng/";
  protected static final String _19 = "<version>";
  protected static final String _20 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _21 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _22 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _23 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String _24 = NL + _1 + NL + _17 + NL_2 + _22 + NL_2 + _21 + NL_2 + _23 + NL_1 + _14 + NL + NL_1 + _16 + NL_2 + _13;
  protected final String _25 = _8 + NL_2 + _12;
  protected final String _26 = _3 + NL_2 + _20 + NL_2 + _18;
  protected final String _27 = _5 + NL_1 + _9 + NL + NL_1 + _13;
  protected final String _28 = _4 + NL_1 + _12;
  protected final String _29 = _7 + NL_1 + _19;
  protected final String _30 = _2 + NL_1 + _15 + NL + _10;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmName = jvm.getName();
  Model model = jvm.getModel();
  String modelName = model.getName();
  String fullName = modelName + '.' + jvmName;
  String version = jvm.getVersion();
    builder.append(_11);
    builder.append(NL);
    builder.append(_6);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_24);
    builder.append(modelName);
    builder.append(_25);
    builder.append(modelName);
    builder.append(_26);
    builder.append(modelName);
    builder.append(_27);
    builder.append(modelName);
    builder.append(_28);
    builder.append(fullName);
    builder.append(_29);
    builder.append(version);
    builder.append(_30);
    builder.append(NL);
    return builder.toString();
  }
}
