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

public class AboutIni
{
  protected static String nl;
  public static synchronized AboutIni create(String lineSeparator)
  {
    nl = lineSeparator;
    AboutIni result = new AboutIni();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = "#";
  protected static final String _2 = "# Property \"aboutText\" contains blurb for \"About\" dialog (translated)";
  protected static final String _3 = "# Property \"appName\" contains name of the application (translated)";
  protected static final String _4 = "# Property \"featureImage\" contains path to feature image (32x32)";
  protected static final String _5 = "aboutText=%featureText";
  protected static final String _6 = "appName=%featureName";
  protected static final String _7 = "featureImage=";
  protected final String _8 = "";
  protected final String _9 = NL + _1 + NL + NL + _2 + NL + _5 + NL + NL + _4 + NL + _7;
  protected final String _10 = NL + NL + _3 + NL + _6;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  Model model = jvm.getModel();
  String providerImageName = model.getProviderImageName();
    builder.append(_8);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_9);
    builder.append(providerImageName);
    builder.append(_10);
    builder.append(NL);
    return builder.toString();
  }
}
