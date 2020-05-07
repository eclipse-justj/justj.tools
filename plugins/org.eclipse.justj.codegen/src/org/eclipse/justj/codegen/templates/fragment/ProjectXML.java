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

import org.eclipse.justj.codegen.model.*;

public class ProjectXML
{
  protected static String nl;
  public static synchronized ProjectXML create(String lineSeparator)
  {
    nl = lineSeparator;
    ProjectXML result = new ProjectXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = ".";
  protected static final String _2 = "</arguments>";
  protected static final String _3 = "</buildCommand>";
  protected static final String _4 = "</buildSpec>";
  protected static final String _5 = "</filter>";
  protected static final String _6 = "</filteredResources>";
  protected static final String _7 = "</matcher>";
  protected static final String _8 = "</name>";
  protected static final String _9 = "</natures>";
  protected static final String _10 = "</projectDescription>";
  protected static final String _11 = "</projects>";
  protected static final String _12 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _13 = "<arguments>";
  protected static final String _14 = "<arguments>1.0-projectRelativePath-matches-true-false-target</arguments>";
  protected static final String _15 = "<buildCommand>";
  protected static final String _16 = "<buildSpec>";
  protected static final String _17 = "<comment></comment>";
  protected static final String _18 = "<filter>";
  protected static final String _19 = "<filteredResources>";
  protected static final String _20 = "<id>1396938000000</id>";
  protected static final String _21 = "<id>org.eclipse.ui.ide.multiFilter</id>";
  protected static final String _22 = "<matcher>";
  protected static final String _23 = "<name>";
  protected static final String _24 = "<name></name>";
  protected static final String _25 = "<name>org.eclipse.pde.ManifestBuilder</name>";
  protected static final String _26 = "<nature>org.eclipse.pde.PluginNature</nature>";
  protected static final String _27 = "<natures>";
  protected static final String _28 = "<projectDescription>";
  protected static final String _29 = "<projects>";
  protected static final String _30 = "<type>10</type>";
  protected final String NL_1 = NL + "\t";
  protected final String NL_2 = NL + "\t\t";
  protected final String NL_3 = NL + "\t\t\t";
  protected final String NL_4 = NL + "\t\t\t\t";
  protected final String _31 = _12 + NL + _28 + NL_1 + _23;
  protected final String _32 = _8 + NL_1 + _17 + NL_1 + _29 + NL_1 + _11 + NL_1 + _16 + NL_2 + _15 + NL_3 + _25 + NL_3 + _13 + NL_3 + _2 + NL_2 + _3 + NL_1 + _4 + NL_1 + _27 + NL_2 + _26 + NL_1 + _9 + NL_1 + _19 + NL_2 + _18 + NL_3 + _20 + NL_3 + _24 + NL_3 + _30 + NL_3 + _22 + NL_4 + _21 + NL_4 + _14 + NL_3 + _7 + NL_2 + _5 + NL_1 + _6 + NL + _10;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Variant variant = (Variant)argument;
  String os = variant.getOs();
  String arch = variant.getArch();
  JVM jvm = variant.getJVM();
  String jvmName = jvm.getName();
  Model model = jvm.getModel();
  String modelName = model.getName();
    builder.append(_31);
    builder.append(modelName);
    builder.append(_1);
    builder.append(jvmName);
    builder.append(_1);
    builder.append(os);
    builder.append(_1);
    builder.append(arch);
    builder.append(_32);
    builder.append(NL);
    return builder.toString();
  }
}
