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
  protected static final String _2 = "# Declare a capability that can be used to ensure mutually exclusive installation of IUs that set the -vm option.";
  protected static final String _3 = "# Ensure that the applicable implementation fragment gets installed automatically.";
  protected static final String _4 = "# Exclude all other providers of this capability; the site depends on all the feautres so exclude this requirement at build time.";
  protected static final String _5 = "# Exclude the synthetic a.jre IUs from the p2 repositories; Tycho gets confused by negative requirements so filter this to avoid it at build time.";
  protected static final String _6 = "# Exclude the synthetic a.jre.javase IUs from the p2 repositories; Tycho gets confused by negative requirements so filter this to avoid it at build time.";
  protected static final String _7 = "' && x.namespace == 'org.eclipse.equinox.p2.iu')";
  protected static final String _8 = ")(!(";
  protected static final String _9 = ")(osgi.arch=";
  protected static final String _10 = ".";
  protected static final String _11 = ".buildtime=true))";
  protected static final String _12 = ".buildtime=true)))";
  protected static final String _13 = ".filter = (!(";
  protected static final String _14 = ".filter = (&(osgi.os=";
  protected static final String _15 = ".matchExp = providedCapabilities.exists(x | x.name == 'jre' && x.namespace == 'org.eclipse.justj') && !providedCapabilities.exists(x | x.name == '";
  protected static final String _16 = ".max = 0";
  protected static final String _17 = ".min = 0";
  protected static final String _18 = ".name = ";
  protected static final String _19 = ".name = a.jre";
  protected static final String _20 = ".name = a.jre.javase";
  protected static final String _21 = ".namespace = org.eclipse.equinox.p2.iu";
  protected static final String _22 = ".range = [$version$,$version$]";
  protected static final String _23 = "properties.0.name = org.eclipse.justj.model";
  protected static final String _24 = "properties.0.value = ";
  protected static final String _25 = "provides.0.name = jre";
  protected static final String _26 = "provides.0.namespace = org.eclipse.justj";
  protected static final String _27 = "provides.0.version = $version$";
  protected static final String _28 = "requires.";
  protected final String _29 = "";
  protected final String _30 = NL + _1 + NL + NL + _3 + NL;
  protected final String _31 = NL + _28;
  protected final String _32 = _21 + NL + _28;
  protected final String _33 = _22 + NL + _28;
  protected final String _34 = _12 + NL;
  protected final String _35 = NL + _5;
  protected final String _36 = _19 + NL + _28;
  protected final String _37 = _17 + NL + _28;
  protected final String _38 = _16 + NL + _28;
  protected final String _39 = _11 + NL + NL + _6;
  protected final String _40 = _20 + NL + _28;
  protected final String _41 = _11 + NL + NL + _2 + NL + _26 + NL + _25 + NL + _27 + NL + NL + _4;
  protected final String _42 = _7 + NL + _28;
  protected final String _43 = _11 + NL + NL + _23 + NL + _24;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmName = jvm.getName();
  Model model = jvm.getModel();
  String modelName = model.getName();
  String fullName = modelName + '.' + jvmName;
    builder.append(_29);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_30);
    int count = 0;
  for (Variant variant : jvm.getVariants()) {
    ++count;
    String os = variant.getOs();
    String arch = variant.getArch();
    builder.append(_31);
    builder.append(count);
    builder.append(_32);
    builder.append(count);
    builder.append(_18);
    builder.append(fullName);
    builder.append(_10);
    builder.append(os);
    builder.append(_10);
    builder.append(arch);
    builder.append(_31);
    builder.append(count);
    builder.append(_33);
    builder.append(count);
    builder.append(_14);
    builder.append(os);
    builder.append(_9);
    builder.append(arch);
    builder.append(_8);
    builder.append(modelName);
    builder.append(_34);
    }
    builder.append(_35);
    ++count;
    builder.append(_31);
    builder.append(count);
    builder.append(_32);
    builder.append(count);
    builder.append(_36);
    builder.append(count);
    builder.append(_37);
    builder.append(count);
    builder.append(_38);
    builder.append(count);
    builder.append(_13);
    builder.append(modelName);
    builder.append(_39);
    ++count;
    builder.append(_31);
    builder.append(count);
    builder.append(_32);
    builder.append(count);
    builder.append(_40);
    builder.append(count);
    builder.append(_37);
    builder.append(count);
    builder.append(_38);
    builder.append(count);
    builder.append(_13);
    builder.append(modelName);
    builder.append(_41);
    ++count;
    builder.append(_31);
    builder.append(count);
    builder.append(_15);
    builder.append(fullName);
    builder.append(_42);
    builder.append(count);
    builder.append(_37);
    builder.append(count);
    builder.append(_38);
    builder.append(count);
    builder.append(_13);
    builder.append(modelName);
    builder.append(_43);
    builder.append(Generator.getModelXMLAsPropertyValue(jvm, NL));
    builder.append(NL);
    return builder.toString();
  }
}
