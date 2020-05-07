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

public class BuildProperties
{
  protected static String nl;
  public static synchronized BuildProperties create(String lineSeparator)
  {
    nl = lineSeparator;
    BuildProperties result = new BuildProperties();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = "#";
  protected static final String _2 = "about.html,\\";
  protected static final String _3 = "about.ini,\\";
  protected static final String _4 = "about.mappings,\\";
  protected static final String _5 = "about.properties,\\";
  protected static final String _6 = "bin.includes = META-INF/,\\";
  protected static final String _7 = "plugin.properties,\\";
  protected final String NL_1 = NL + "               ";
  protected final String _8 = "";
  protected final String _9 = NL + _1 + NL + NL + _6 + NL_1 + _2 + NL_1 + _7 + NL_1 + _3 + NL_1 + _4 + NL_1 + _5;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  Model model = jvm.getModel();
  String providerImageName = model.getProviderImageName();
    builder.append(_8);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_9);
    builder.append(NL_1);
    builder.append(providerImageName);
    builder.append(NL);
    return builder.toString();
  }
}
