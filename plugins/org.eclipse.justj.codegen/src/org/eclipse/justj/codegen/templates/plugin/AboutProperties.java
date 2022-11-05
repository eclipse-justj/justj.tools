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
package org.eclipse.justj.codegen.templates.plugin;

import org.eclipse.justj.codegen.model.*;
import org.eclipse.justj.codegen.model.util.Generator;

public class AboutProperties
{
  protected static String nl;
  public static synchronized AboutProperties create(String lineSeparator)
  {
    nl = lineSeparator;
    AboutProperties result = new AboutProperties();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = " ";
  protected static final String _2 = "#";
  protected static final String _3 = "Build: {0}\\n\\";
  protected static final String _4 = "Commit: {1}\\n\\";
  protected static final String _5 = "Version: {featureVersion}\\n\\";
  protected static final String _6 = "\\n";
  protected static final String _7 = "\\n\\";
  protected static final String _8 = "copyright = ";
  protected static final String _9 = "description = Contains the plug-ins and fragments for the ";
  protected static final String _10 = "featureName = ";
  protected static final String _11 = "featureText = ";
  protected static final String _12 = "providerName = Eclipse ";
  protected final String _13 = "";
  protected final String _14 = NL + _2 + NL + NL + _12;
  protected final String _15 = NL + _10;
  protected final String _16 = NL + _9;
  protected final String _17 = NL + _8;
  protected final String _18 = _6 + NL + _11;
  protected final String _19 = _7 + NL + _7 + NL + _5 + NL + _4 + NL + _3 + NL + _7;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmLabel = jvm.getLabel();
  String aboutTextExtra = jvm.getAboutTextExtra();
  Model model = jvm.getModel();
  String modelLabel = model.getLabel();
    builder.append(_13);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_14);
    builder.append(modelLabel);
    builder.append(_15);
    builder.append(modelLabel);
    builder.append(_1);
    builder.append(jvmLabel);
    builder.append(_16);
    builder.append(jvmLabel);
    builder.append(_17);
    builder.append(Generator.getCopyright(argument, "", "\\n\\" + NL));
    builder.append(_18);
    builder.append(modelLabel);
    builder.append(_1);
    builder.append(jvmLabel);
    builder.append(_19);
    builder.append(NL);
    builder.append(Generator.getCopyright(argument, "", "\\n\\" + NL));
    builder.append(_7);
    builder.append(NL);
    builder.append(Generator.composeLines(aboutTextExtra, "", "\\n\\" + NL));
    return builder.toString();
  }
}
