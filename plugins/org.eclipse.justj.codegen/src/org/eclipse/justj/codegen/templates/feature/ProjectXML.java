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
  protected static final String _1 = "-feature</name>";
  protected static final String _2 = ".";
  protected static final String _3 = "</arguments>";
  protected static final String _4 = "</buildCommand>";
  protected static final String _5 = "</buildSpec>";
  protected static final String _6 = "</filter>";
  protected static final String _7 = "</filteredResources>";
  protected static final String _8 = "</matcher>";
  protected static final String _9 = "</projectDescription>";
  protected static final String _10 = "</projects>";
  protected static final String _11 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
  protected static final String _12 = "<arguments>";
  protected static final String _13 = "<arguments>1.0-projectRelativePath-matches-true-false-target</arguments>";
  protected static final String _14 = "<buildCommand>";
  protected static final String _15 = "<buildSpec>";
  protected static final String _16 = "<comment></comment>";
  protected static final String _17 = "<filter>";
  protected static final String _18 = "<filteredResources>";
  protected static final String _19 = "<id>1396938000000</id>";
  protected static final String _20 = "<id>org.eclipse.ui.ide.multiFilter</id>";
  protected static final String _21 = "<matcher>";
  protected static final String _22 = "<name>";
  protected static final String _23 = "<name></name>";
  protected static final String _24 = "<name>org.eclipse.pde.FeatureBuilder</name>";
  protected static final String _25 = "<projectDescription>";
  protected static final String _26 = "<projects>";
  protected static final String _27 = "<type>10</type>";
  protected final String NL_1 = NL + "\t";
  protected final String NL_2 = NL + "\t\t";
  protected final String NL_3 = NL + "\t\t\t";
  protected final String NL_4 = NL + "\t\t\t\t";
  protected final String _28 = _11 + NL + _25 + NL_1 + _22;
  protected final String _29 = _1 + NL_1 + _16 + NL_1 + _26 + NL_1 + _10 + NL_1 + _15 + NL_2 + _14 + NL_3 + _24 + NL_3 + _12 + NL_3 + _3 + NL_2 + _4 + NL_1 + _5 + NL_1 + _18 + NL_2 + _17 + NL_3 + _19 + NL_3 + _23 + NL_3 + _27 + NL_3 + _21 + NL_4 + _20 + NL_4 + _13 + NL_3 + _8 + NL_2 + _6 + NL_1 + _7 + NL + _9;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmName = jvm.getName();
  Model model = jvm.getModel();
  String modelName = model.getName();
    builder.append(_28);
    builder.append(modelName);
    builder.append(_2);
    builder.append(jvmName);
    builder.append(_29);
    builder.append(NL);
    return builder.toString();
  }
}
