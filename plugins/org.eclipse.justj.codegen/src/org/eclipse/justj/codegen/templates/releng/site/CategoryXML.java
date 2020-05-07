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
  protected static final String _3 = ".category\" label=\"";
  protected static final String _4 = ".category\"/>";
  protected static final String _5 = "<!--";
  protected static final String _6 = "</feature>";
  protected static final String _7 = "</site>";
  protected static final String _8 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _9 = "<category name=\"";
  protected static final String _10 = "<category-def name=\"";
  protected static final String _11 = "<feature id=\"";
  protected static final String _12 = "<site>";
  protected static final String _13 = "\"/>";
  protected static final String _14 = "\">";
  protected final String NL_1 = NL + "   ";
  protected final String NL_2 = NL + "      ";
  protected final String _15 = NL + _1 + NL + _12;
  protected final String _16 = NL_1 + _11;
  protected final String _17 = _14 + NL_2 + _9;
  protected final String _18 = _4 + NL_1 + _6;
  protected final String _19 = NL_1 + _10;
  protected final String _20 = _13 + NL + _7;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
  String label = model.getLabel();
    builder.append(_8);
    builder.append(NL);
    builder.append(_5);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_15);
    for (JVM jvm : model.getJVMs()) {
    builder.append(_16);
    builder.append(name);
    builder.append(_2);
    builder.append(jvm.getName());
    builder.append(_17);
    builder.append(name);
    builder.append(_18);
    }
    builder.append(_19);
    builder.append(name);
    builder.append(_3);
    builder.append(label);
    builder.append(_20);
    builder.append(NL);
    return builder.toString();
  }
}
