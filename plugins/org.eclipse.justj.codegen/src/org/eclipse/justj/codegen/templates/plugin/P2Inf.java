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
  protected static final String _2 = "# Ensure that the applicable implementation fragment gets installed automatically.";
  protected static final String _3 = ")(!(";
  protected static final String _4 = ")(osgi.arch=";
  protected static final String _5 = ".";
  protected static final String _6 = ".buildtime=true)))";
  protected static final String _7 = ".filter = (&(osgi.os=";
  protected static final String _8 = ".name = ";
  protected static final String _9 = ".namespace = org.eclipse.equinox.p2.iu";
  protected static final String _10 = ".range = [$version$,$version$]";
  protected static final String _11 = "requires.";
  protected final String _12 = "";
  protected final String _13 = NL + _1 + NL + _2 + NL;
  protected final String _14 = NL + NL + _11;
  protected final String _15 = _9 + NL + _11;
  protected final String _16 = NL + _11;
  protected final String _17 = _10 + NL + _11;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmName = jvm.getName();
  Model model = jvm.getModel();
  String modelName = model.getName();
  String fullName = modelName + '.' + jvmName;
    builder.append(_12);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_13);
    int count = 0;
  for (Variant variant : jvm.getVariants()) {
    ++count;
    String os = variant.getOs();
    String arch = variant.getArch();
    builder.append(_14);
    builder.append(count);
    builder.append(_15);
    builder.append(count);
    builder.append(_8);
    builder.append(fullName);
    builder.append(_5);
    builder.append(os);
    builder.append(_5);
    builder.append(arch);
    builder.append(_16);
    builder.append(count);
    builder.append(_17);
    builder.append(count);
    builder.append(_7);
    builder.append(os);
    builder.append(_4);
    builder.append(arch);
    builder.append(_3);
    builder.append(modelName);
    builder.append(_6);
    }
    builder.append(NL);
    return builder.toString();
  }
}
