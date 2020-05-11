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

public class CategoryXML
{
  protected static String nl;
  public static synchronized CategoryXML create(String lineSeparator)
  {
    nl = lineSeparator;
    CategoryXML result = new CategoryXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = "-->";
  protected static final String _2 = ".";
  protected static final String _3 = ".category\" label=\"%categoryLabel\">";
  protected static final String _4 = ".category\"/>";
  protected static final String _5 = "<!--";
  protected static final String _6 = "</category-def>";
  protected static final String _7 = "</feature>";
  protected static final String _8 = "</site>";
  protected static final String _9 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _10 = "<category name=\"";
  protected static final String _11 = "<category-def name=\"";
  protected static final String _12 = "<description>%categoryDescription</description>";
  protected static final String _13 = "<feature id=\"";
  protected static final String _14 = "<site>";
  protected static final String _15 = "\">";
  protected final String NL_1 = NL + "   ";
  protected final String NL_2 = NL + "     ";
  protected final String NL_3 = NL + "      ";
  protected final String _16 = NL + _1 + NL + _14;
  protected final String _17 = NL_1 + _13;
  protected final String _18 = _15 + NL_3 + _10;
  protected final String _19 = _4 + NL_1 + _7;
  protected final String _20 = NL_1 + _11;
  protected final String _21 = _3 + NL_2 + _12 + NL_1 + _6 + NL + _8;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
    builder.append(_9);
    builder.append(NL);
    builder.append(_5);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_16);
    for (JVM jvm : model.getJVMs()) {
    builder.append(_17);
    builder.append(name);
    builder.append(_2);
    builder.append(jvm.getName());
    builder.append(_18);
    builder.append(name);
    builder.append(_19);
    }
    builder.append(_20);
    builder.append(name);
    builder.append(_21);
    builder.append(NL);
    return builder.toString();
  }
}
