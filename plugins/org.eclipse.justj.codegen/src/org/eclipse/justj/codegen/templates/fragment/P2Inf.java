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

import java.util.*;
import java.util.Iterator;
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
  protected static final String _1 = " = \\";
  protected static final String _2 = "#";
  protected static final String _3 = ";";
  protected static final String _4 = "\\";
  protected static final String _5 = "instructions.";
  protected final String NL_1 = NL + "  ";
  protected final String _6 = "";
  protected final String _7 = NL + _2 + NL;
  protected final String _8 = NL + _5;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Variant variant = (Variant)argument;
    builder.append(_6);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_7);
    Map<String, Set<String>> touchpoints = Generator.getTouchpoints(variant);
  for (Map.Entry<String, Set<String>> entry : touchpoints.entrySet()) {
    String phase = entry.getKey();
    builder.append(_8);
    builder.append(phase);
    builder.append(_1);
    for (Iterator<String> it = entry.getValue().iterator(); it.hasNext();) {
      String instruction = it.next();
    builder.append(NL_1);
    builder.append(instruction);
    builder.append(_3);
    if (it.hasNext()) {
    builder.append(_4);
    }
    }
    builder.append(NL);
    }
    return builder.toString();
  }
}
