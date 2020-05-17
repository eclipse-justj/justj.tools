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
  protected static final String _3 = ".";
  protected static final String _4 = "\\n";
  protected static final String _5 = "\\n\\";
  protected static final String _6 = "copyright = ";
  protected static final String _7 = "description = Contains the plug-ins and fragments for the ";
  protected static final String _8 = "featureName = ";
  protected static final String _9 = "providerName = ";
  protected final String _10 = "";
  protected final String _11 = NL + _2 + NL + NL + _9;
  protected final String _12 = NL + _8;
  protected final String _13 = NL + _7;
  protected final String _14 = NL + _6;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmLabel = jvm.getLabel();
  String description = jvm.getDescription();
  boolean hasDescription = description != null && !description.trim().isEmpty();
  Model model = jvm.getModel();
  String modelLabel = model.getLabel();
  String provider = model.getProvider();
    builder.append(_10);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_11);
    builder.append(provider);
    builder.append(_12);
    builder.append(modelLabel);
    builder.append(_1);
    builder.append(jvmLabel);
    builder.append(_13);
    builder.append(jvmLabel);
    builder.append(_3);
    if (hasDescription) {
    builder.append(_5);
    }
    if (hasDescription) {
    builder.append(NL);
    builder.append(Generator.composeLines(description, "", "\\n\\" + NL));
    }
    builder.append(_14);
    builder.append(Generator.getCopyright(argument, "", "\\n\\" + NL));
    builder.append(_4);
    builder.append(NL);
    return builder.toString();
  }
}
