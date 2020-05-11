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

public class P2Inf
{
  protected static String nl;
  public static synchronized P2Inf create(String lineSeparator)
  {
    nl = lineSeparator;
    P2Inf result = new P2Inf();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = "#";
  protected static final String _2 = "properties.0.name = org.eclipse.justj.model";
  protected static final String _3 = "properties.0.value = ";
  protected final String _4 = "";
  protected final String _5 = NL + _1 + NL + NL + _2 + NL + _3;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
    builder.append(_4);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_5);
    builder.append(Generator.getModelXMLAsPropertyValue(jvm, NL));
    builder.append(NL);
    return builder.toString();
  }
}
