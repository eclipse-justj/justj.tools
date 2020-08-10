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
  protected static final String _3 = ".assembletime>";
  protected static final String _4 = ".assembletime>true</";
  protected static final String _5 = ".parent</artifactId>";
  protected static final String _6 = ".parent</relativePath>";
  protected static final String _7 = ".site</artifactId>";
  protected static final String _8 = "<";
  protected static final String _9 = "<!--";
  protected static final String _10 = "</build>";
  protected static final String _11 = "</configuration>";
  protected static final String _12 = "</dependency-resolution>";
  protected static final String _13 = "</groupId>";
  protected static final String _14 = "</parent>";
  protected static final String _15 = "</plugin>";
  protected static final String _16 = "</plugins>";
  protected static final String _17 = "</profileProperties>";
  protected static final String _18 = "</project>";
  protected static final String _19 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _20 = "<artifactId>";
  protected static final String _21 = "<artifactId>target-platform-configuration</artifactId>";
  protected static final String _22 = "<artifactId>tycho-p2-repository-plugin</artifactId>";
  protected static final String _23 = "<build>";
  protected static final String _24 = "<configuration>";
  protected static final String _25 = "<dependency-resolution>";
  protected static final String _26 = "<groupId>";
  protected static final String _27 = "<groupId>org.eclipse.tycho</groupId>";
  protected static final String _28 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _29 = "<packaging>eclipse-repository</packaging>";
  protected static final String _30 = "<parent>";
  protected static final String _31 = "<plugin>";
  protected static final String _32 = "<plugins>";
  protected static final String _33 = "<profileProperties>";
  protected static final String _34 = "<project";
  protected static final String _35 = "<relativePath>../";
  protected static final String _36 = "<repositoryName>";
  protected static final String _37 = "<version>${tycho-version}</version>";
  protected static final String _38 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _39 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _40 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _41 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String NL_3 = NL + "      ";
  protected final String NL_4 = NL + "        ";
  protected final String NL_5 = NL + "          ";
  protected final String NL_6 = NL + "            ";
  protected final String NL_7 = NL + "              ";
  protected final String _42 = NL + _2 + NL + _34 + NL_2 + _40 + NL_2 + _39 + NL_2 + _41 + NL_1 + _28 + NL + NL_1 + _30 + NL_2 + _26;
  protected final String _43 = _13 + NL_2 + _20;
  protected final String _44 = _5 + NL_2 + _38 + NL_2 + _35;
  protected final String _45 = _6 + NL_1 + _14 + NL + NL_1 + _26;
  protected final String _46 = _13 + NL_1 + _20;
  protected final String _47 = _7 + NL_1 + _38 + NL_1 + _29 + NL + NL_1 + _23 + NL_2 + _32 + NL_3 + _31 + NL_4 + _27 + NL_4 + _22 + NL_4 + _37 + NL_4 + _24 + NL_5 + _36;
  protected final String _48 = _1 + NL_5 + _33 + NL_6 + _8;
  protected final String _49 = _3 + NL_5 + _17 + NL_4 + _11 + NL_3 + _15 + NL_3 + _31 + NL_4 + _27 + NL_4 + _21 + NL_4 + _37 + NL_4 + _24 + NL_5 + _25 + NL_6 + _33 + NL_7 + _8;
  protected final String _50 = _3 + NL_6 + _17 + NL_5 + _12 + NL_4 + _11 + NL_3 + _15 + NL_2 + _16 + NL_1 + _10 + NL + _18;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
  String label = model.getLabel();
    builder.append(_19);
    builder.append(NL);
    builder.append(_9);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_42);
    builder.append(name);
    builder.append(_43);
    builder.append(name);
    builder.append(_44);
    builder.append(name);
    builder.append(_45);
    builder.append(name);
    builder.append(_46);
    builder.append(name);
    builder.append(_47);
    builder.append(label);
    builder.append(_48);
    builder.append(name);
    builder.append(_4);
    builder.append(name);
    builder.append(_49);
    builder.append(name);
    builder.append(_4);
    builder.append(name);
    builder.append(_50);
    builder.append(NL);
    return builder.toString();
  }
}
