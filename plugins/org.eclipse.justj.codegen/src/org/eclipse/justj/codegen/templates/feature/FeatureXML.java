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

public class FeatureXML
{
  protected static String nl;
  public static synchronized FeatureXML create(String lineSeparator)
  {
    nl = lineSeparator;
    FeatureXML result = new FeatureXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = "%copyright";
  protected static final String _2 = "%description";
  protected static final String _3 = "%license";
  protected static final String _4 = "-->";
  protected static final String _5 = ".";
  protected static final String _6 = ".qualifier\"";
  protected static final String _7 = "<!--";
  protected static final String _8 = "</copyright>";
  protected static final String _9 = "</description>";
  protected static final String _10 = "</feature>";
  protected static final String _11 = "</license>";
  protected static final String _12 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _13 = "<copyright>";
  protected static final String _14 = "<description>";
  protected static final String _15 = "<feature";
  protected static final String _16 = "<license url=\"%licenseURL\">";
  protected static final String _17 = "<plugin";
  protected static final String _18 = "\"";
  protected static final String _19 = "arch=\"";
  protected static final String _20 = "download-size=\"0\"";
  protected static final String _21 = "fragment=\"true\"/>";
  protected static final String _22 = "id=\"";
  protected static final String _23 = "install-size=\"0\"";
  protected static final String _24 = "label=\"%featureName\"";
  protected static final String _25 = "license-feature-version=\"0.0.0\">";
  protected static final String _26 = "license-feature=\"org.eclipse.license\"";
  protected static final String _27 = "os=\"";
  protected static final String _28 = "provider-name=\"%providerName\"";
  protected static final String _29 = "unpack=\"false\"/>";
  protected static final String _30 = "version=\"";
  protected static final String _31 = "version=\"0.0.0\"";
  protected final String NL_1 = NL + "   ";
  protected final String NL_2 = NL + "      ";
  protected final String NL_3 = NL + "         ";
  protected final String _32 = NL + _4 + NL + _15 + NL_2 + _22;
  protected final String _33 = _18 + NL_2 + _24 + NL_2 + _30;
  protected final String _34 = _6 + NL_2 + _28 + NL_2 + _26 + NL_2 + _25 + NL + NL_1 + _14 + NL_2 + _2 + NL_1 + _9 + NL + NL_1 + _13 + NL_2 + _1 + NL_1 + _8 + NL + NL_1 + _16 + NL_2 + _3 + NL_1 + _11 + NL + NL_1 + _17 + NL_3 + _22;
  protected final String _35 = _18 + NL_3 + _20 + NL_3 + _23 + NL_3 + _31 + NL_3 + _29 + NL;
  protected final String _36 = NL_1 + _17 + NL_3 + _22;
  protected final String _37 = _18 + NL_3 + _27;
  protected final String _38 = _18 + NL_3 + _19;
  protected final String _39 = _18 + NL_3 + _20 + NL_3 + _23 + NL_3 + _31 + NL_3 + _21 + NL;
  protected final String _40 = NL + _10;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmName = jvm.getName();
  Model model = jvm.getModel();
  String modelName = model.getName();
  String fullName = modelName + '.' + jvmName;
  String version = jvm.getVersion();
    builder.append(_12);
    builder.append(NL);
    builder.append(_7);
    builder.append(NL);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "", NL));
    builder.append(_32);
    builder.append(fullName);
    builder.append(_33);
    builder.append(version);
    builder.append(_34);
    builder.append(fullName);
    builder.append(_35);
    for (Variant variant : jvm.getVariants()) {
    String os = variant.getOs();
    String arch = variant.getArch();
    builder.append(_36);
    builder.append(fullName);
    builder.append(_5);
    builder.append(os);
    builder.append(_5);
    builder.append(arch);
    builder.append(_37);
    builder.append(os);
    builder.append(_38);
    builder.append(arch);
    builder.append(_39);
    }
    builder.append(_40);
    builder.append(NL);
    return builder.toString();
  }
}
