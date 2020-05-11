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
import org.eclipse.justj.codegen.model.util.Generator;

public class SiteProperties
{
  protected static String nl;
  public static synchronized SiteProperties create(String lineSeparator)
  {
    nl = lineSeparator;
    SiteProperties result = new SiteProperties();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = " Java Runtime Environments";
  protected static final String _2 = "#";
  protected static final String _3 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _4 = "\\n\\";
  protected static final String _5 = "categoryDescription = Provides JREs that are generated from a JustJ JRE Generator model.\\n\\";
  protected static final String _6 = "categoryLabel = ";
  protected final String _7 = NL + _2 + NL + NL + _6;
  protected final String _8 = _1 + NL + _5 + NL + _4;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Model model = (Model)argument;
  String label = model.getLabel();
    builder.append(_3);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_7);
    builder.append(label);
    builder.append(_8);
    builder.append(NL);
    builder.append(Generator.getModelXMLAsPropertyValue(model, NL));
    builder.append(NL);
    return builder.toString();
  }
}
