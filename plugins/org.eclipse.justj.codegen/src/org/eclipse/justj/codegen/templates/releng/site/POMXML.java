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
package org.eclipse.justj.codegen.templates.releng.site;

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
  protected static final String _1 = " Build ${build.id}</repositoryName>";
  protected static final String _2 = "-->";
  protected static final String _3 = ".parent</artifactId>";
  protected static final String _4 = ".parent</relativePath>";
  protected static final String _5 = ".site</artifactId>";
  protected static final String _6 = "<!--";
  protected static final String _7 = "</build>";
  protected static final String _8 = "</configuration>";
  protected static final String _9 = "</groupId>";
  protected static final String _10 = "</parent>";
  protected static final String _11 = "</plugin>";
  protected static final String _12 = "</plugins>";
  protected static final String _13 = "</project>";
  protected static final String _14 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _15 = "<artifactId>";
  protected static final String _16 = "<artifactId>tycho-p2-repository-plugin</artifactId>";
  protected static final String _17 = "<build>";
  protected static final String _18 = "<configuration>";
  protected static final String _19 = "<groupId>";
  protected static final String _20 = "<groupId>org.eclipse.tycho</groupId>";
  protected static final String _21 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _22 = "<packaging>eclipse-repository</packaging>";
  protected static final String _23 = "<parent>";
  protected static final String _24 = "<plugin>";
  protected static final String _25 = "<plugins>";
  protected static final String _26 = "<project";
  protected static final String _27 = "<relativePath>../";
  protected static final String _28 = "<repositoryName>";
  protected static final String _29 = "<version>${tycho-version}</version>";
  protected static final String _30 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _31 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _32 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _33 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "         ";
  protected final String NL_5 = NL + "            ";
  protected final String _34 = NL + _2 + NL + _26 + NL_2 + _32 + NL_2 + _31 + NL_2 + _33 + NL_1 + _21 + NL + NL_1 + _23 + NL_2 + _19;
  protected final String _35 = _9 + NL_2 + _15;
  protected final String _36 = _3 + NL_2 + _30 + NL_2 + _27;
  protected final String _37 = _4 + NL_1 + _10 + NL + NL_1 + _19;
  protected final String _38 = _9 + NL_1 + _15;
  protected final String _39 = _5 + NL_1 + _30 + NL_1 + _22 + NL + NL_1 + _17 + NL_2 + _25 + NL_3 + _24 + NL_4 + _20 + NL_4 + _16 + NL_4 + _29 + NL_4 + _18 + NL_5 + _28;
  protected final String _40 = _1 + NL_4 + _8 + NL_3 + _11 + NL_2 + _12 + NL_1 + _7 + NL + _13;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
  String label = model.getLabel();
    builder.append(_14);
    builder.append(NL);
    builder.append(_6);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_34);
    builder.append(name);
    builder.append(_35);
    builder.append(name);
    builder.append(_36);
    builder.append(name);
    builder.append(_37);
    builder.append(name);
    builder.append(_38);
    builder.append(name);
    builder.append(_39);
    builder.append(label);
    builder.append(_40);
    builder.append(NL);
    return builder.toString();
  }
}
