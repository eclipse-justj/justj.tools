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

public class Manifest
{
  protected static String nl;
  public static synchronized Manifest create(String lineSeparator)
  {
    nl = lineSeparator;
    Manifest result = new Manifest();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = ".qualifier";
  protected static final String _2 = ";singleton:=true";
  protected static final String _3 = "Automatic-Module-Name: ";
  protected static final String _4 = "Bundle-ClassPath: .";
  protected static final String _5 = "Bundle-Localization: plugin";
  protected static final String _6 = "Bundle-ManifestVersion: 2";
  protected static final String _7 = "Bundle-Name: %pluginName";
  protected static final String _8 = "Bundle-SymbolicName: ";
  protected static final String _9 = "Bundle-Vendor: %providerName";
  protected static final String _10 = "Bundle-Version: ";
  protected static final String _11 = "Manifest-Version: 1.0";
  protected final String _12 = _11 + NL + _6 + NL + _8;
  protected final String _13 = _2 + NL + _10;
  protected final String _14 = _1 + NL + _4 + NL + _7 + NL + _9 + NL + _5 + NL + _3;

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
    builder.append(fullName);
    builder.append(_13);
    builder.append(version);
    builder.append(_14);
    builder.append(fullName);
    builder.append(NL);
    return builder.toString();
  }
}
