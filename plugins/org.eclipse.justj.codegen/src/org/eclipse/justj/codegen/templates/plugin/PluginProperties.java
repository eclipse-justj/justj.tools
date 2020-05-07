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

public class PluginProperties
{
  protected static String nl;
  public static synchronized PluginProperties create(String lineSeparator)
  {
    nl = lineSeparator;
    PluginProperties result = new PluginProperties();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = " ";
  protected static final String _2 = "#";
  protected static final String _3 = "pluginName = ";
  protected static final String _4 = "providerName = ";
  protected final String _5 = "";
  protected final String _6 = NL + _2 + NL + NL + _4;
  protected final String _7 = NL + _3;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmLabel = jvm.getLabel();
  Model model = jvm.getModel();
  String modelLabel = model.getLabel();
  String provider = model.getProvider();
    builder.append(_5);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_6);
    builder.append(provider);
    builder.append(_7);
    builder.append(modelLabel);
    builder.append(_1);
    builder.append(jvmLabel);
    builder.append(NL);
    return builder.toString();
  }
}
