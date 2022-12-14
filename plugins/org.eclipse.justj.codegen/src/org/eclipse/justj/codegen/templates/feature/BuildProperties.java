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
  protected static final String _2 = "bin.includes = feature.xml,\\";
  protected static final String _3 = "feature.properties,\\";
  protected static final String _4 = "p2.inf";
  protected final String NL_1 = NL + "               ";
  protected final String _5 = "";
  protected final String _6 = NL + _1 + NL + NL + _2 + NL_1 + _3 + NL_1 + _4;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    builder.append(_5);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_6);
    builder.append(NL);
    return builder.toString();
  }
}
