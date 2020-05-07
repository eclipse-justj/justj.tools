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
package org.eclipse.justj.codegen.templates;

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
  protected static final String _2 = ".parent</module>";
  protected static final String _3 = "<!--";
  protected static final String _4 = "</groupId>";
  protected static final String _5 = "</modules>";
  protected static final String _6 = "</project>";
  protected static final String _7 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _8 = "<artifactId>org.eclipse.justj.root</artifactId>";
  protected static final String _9 = "<groupId>";
  protected static final String _10 = "<modelVersion>4.0.0</modelVersion>";
  protected static final String _11 = "<module>releng/";
  protected static final String _12 = "<modules>";
  protected static final String _13 = "<packaging>pom</packaging>";
  protected static final String _14 = "<project";
  protected static final String _15 = "<version>1.0.0-SNAPSHOT</version>";
  protected static final String _16 = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
  protected static final String _17 = "xmlns=\"http://maven.apache.org/POM/4.0.0\"";
  protected static final String _18 = "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String _19 = NL + _1 + NL + _14 + NL_2 + _17 + NL_2 + _16 + NL_2 + _18 + NL_1 + _10 + NL + NL_1 + _9;
  protected final String _20 = _4 + NL_1 + _8 + NL_1 + _15 + NL_1 + _13 + NL + NL_1 + _12 + NL_2 + _11;
  protected final String _21 = _2 + NL_1 + _5 + NL + NL + _6;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
    builder.append(_7);
    builder.append(NL);
    builder.append(_3);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_19);
    builder.append(model.getName());
    builder.append(_20);
    builder.append(model.getName());
    builder.append(_21);
    builder.append(NL);
    return builder.toString();
  }
}
