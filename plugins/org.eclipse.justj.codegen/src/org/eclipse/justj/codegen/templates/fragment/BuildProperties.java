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
package org.eclipse.justj.codegen.templates.fragment;

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
  protected static final String _3 = "about.mappings,\\";
  protected static final String _4 = "bin.includes = META-INF/,\\";
  protected static final String _5 = "fragment.properties,\\";
  protected static final String _6 = "jre/";
  protected final String NL_1 = NL + "               ";
  protected final String _7 = "";
  protected final String _8 = NL + _1 + NL + NL + _4 + NL_1 + _2 + NL_1 + _3 + NL_1 + _5 + NL_1 + _6;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    builder.append(_7);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_8);
    builder.append(NL);
    return builder.toString();
  }
}
