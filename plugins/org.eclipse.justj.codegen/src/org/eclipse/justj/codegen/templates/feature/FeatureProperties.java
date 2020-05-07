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
import org.eclipse.justj.codegen.model.util.Generator;

public class FeatureProperties
{
  protected static String nl;
  public static synchronized FeatureProperties create(String lineSeparator)
  {
    nl = lineSeparator;
    FeatureProperties result = new FeatureProperties();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = " ";
  protected static final String _2 = "#";
  protected static final String _3 = "\\n";
  protected static final String _4 = "copyright = ";
  protected static final String _5 = "description = Contains the plug-ins and fragments for the ";
  protected static final String _6 = "featureName = ";
  protected static final String _7 = "providerName = ";
  protected final String _8 = "";
  protected final String _9 = NL + _2 + NL + NL + _7;
  protected final String _10 = NL + _6;
  protected final String _11 = NL + _5;
  protected final String _12 = NL + _4;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmLabel = jvm.getLabel();
  Model model = jvm.getModel();
  String modelLabel = model.getLabel();
  String provider = model.getProvider();
    builder.append(_8);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_9);
    builder.append(provider);
    builder.append(_10);
    builder.append(modelLabel);
    builder.append(_1);
    builder.append(jvmLabel);
    builder.append(_11);
    builder.append(jvmLabel);
    builder.append(_12);
    builder.append(Generator.getCopyright(argument, "", "\\n\\" + NL));
    builder.append(_3);
    builder.append(NL);
    return builder.toString();
  }
}
