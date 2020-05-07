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
package org.eclipse.justj.codegen.templates.releng.parent;

import org.eclipse.justj.codegen.model.*;

public class ProjectXML
{
  protected static String nl;
  public static synchronized ProjectXML create(String lineSeparator)
  {
    nl = lineSeparator;
    ProjectXML result = new ProjectXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = ".parent</name>";
  protected static final String _2 = "</buildSpec>";
  protected static final String _3 = "</filter>";
  protected static final String _4 = "</filteredResources>";
  protected static final String _5 = "</matcher>";
  protected static final String _6 = "</natures>";
  protected static final String _7 = "</projectDescription>";
  protected static final String _8 = "</projects>";
  protected static final String _9 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _10 = "<arguments>1.0-projectRelativePath-matches-true-false-target</arguments>";
  protected static final String _11 = "<buildSpec>";
  protected static final String _12 = "<comment></comment>";
  protected static final String _13 = "<filter>";
  protected static final String _14 = "<filteredResources>";
  protected static final String _15 = "<id>1396938000000</id>";
  protected static final String _16 = "<id>org.eclipse.ui.ide.multiFilter</id>";
  protected static final String _17 = "<matcher>";
  protected static final String _18 = "<name>";
  protected static final String _19 = "<name></name>";
  protected static final String _20 = "<natures>";
  protected static final String _21 = "<projectDescription>";
  protected static final String _22 = "<projects>";
  protected static final String _23 = "<type>10</type>";
  protected final String NL_1 = NL + "\t";
  protected final String NL_2 = NL + "\t\t";
  protected final String NL_3 = NL + "\t\t\t";
  protected final String NL_4 = NL + "\t\t\t\t";
  protected final String _24 = _9 + NL + _21 + NL_1 + _18;
  protected final String _25 = _1 + NL_1 + _12 + NL_1 + _22 + NL_1 + _8 + NL_1 + _11 + NL_1 + _2 + NL_1 + _20 + NL_1 + _6 + NL_1 + _14 + NL_2 + _13 + NL_3 + _15 + NL_3 + _19 + NL_3 + _23 + NL_3 + _17 + NL_4 + _16 + NL_4 + _10 + NL_3 + _5 + NL_2 + _3 + NL_1 + _4 + NL + _7;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String name = model.getName();
    builder.append(_24);
    builder.append(name);
    builder.append(_25);
    builder.append(NL);
    return builder.toString();
  }
}
