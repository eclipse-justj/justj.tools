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
  protected static final String _3 = ".name = ";
  protected static final String _4 = ".namespace = java.package";
  protected static final String _5 = ".namespace = osgi.ee";
  protected static final String _6 = ".version = ";
  protected static final String _7 = ".version = 0.0.0";
  protected static final String _8 = ";";
  protected static final String _9 = "\\";
  protected static final String _10 = "instructions.";
  protected static final String _11 = "properties.0.name = org.eclipse.justj.model";
  protected static final String _12 = "properties.0.value = ";
  protected static final String _13 = "provides.";
  protected final String NL_1 = NL + "  ";
  protected final String _14 = "";
  protected final String _15 = NL + _2 + NL;
  protected final String _16 = NL + _10;
  protected final String _17 = NL + _13;
  protected final String _18 = _5 + NL + _13;
  protected final String _19 = _4 + NL + _13;
  protected final String _20 = _7 + NL;
  protected final String _21 = NL + _11 + NL + _12;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Variant variant = (Variant)argument;
  JVM jvm = variant.getJVM();
  String version = jvm.getVersion();
    builder.append(_14);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_15);
    Map<String, Set<String>> touchpoints = Generator.getTouchpoints(variant);
  for (Map.Entry<String, Set<String>> entry : touchpoints.entrySet()) {
    String phase = entry.getKey();
    builder.append(_16);
    builder.append(phase);
    builder.append(_1);
    for (Iterator<String> it = entry.getValue().iterator(); it.hasNext();) {
      String instruction = it.next();
    builder.append(NL_1);
    builder.append(instruction);
    builder.append(_8);
    if (it.hasNext()) {
    builder.append(_9);
    }
    }
    builder.append(NL);
    }
    int propertyCount = 0;
  for (Map.Entry<String, Set<String>> entry : Generator.getEECapabilities(version).entrySet()) {
    String eeName = entry.getKey();
    Set<String> versions = entry.getValue();
    for (String eeVersion : versions) {
      int capabilityNumber = propertyCount++;
    builder.append(_17);
    builder.append(capabilityNumber);
    builder.append(_18);
    builder.append(capabilityNumber);
    builder.append(_3);
    builder.append(eeName);
    builder.append(_17);
    builder.append(capabilityNumber);
    builder.append(_6);
    builder.append(eeVersion);
    builder.append(NL);
    }
    }
    Set<String> systemPackages = Generator.getSystemPackages(variant);
  for (String systemPackage : systemPackages) {
    int capabilityNumber = propertyCount++;
    builder.append(_17);
    builder.append(capabilityNumber);
    builder.append(_19);
    builder.append(capabilityNumber);
    builder.append(_3);
    builder.append(systemPackage);
    builder.append(_17);
    builder.append(capabilityNumber);
    builder.append(_20);
    }
    builder.append(_21);
    builder.append(Generator.getModelXMLAsPropertyValue(variant, NL));
    return builder.toString();
  }
}
