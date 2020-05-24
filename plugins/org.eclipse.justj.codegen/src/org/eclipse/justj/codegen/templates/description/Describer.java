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
package org.eclipse.justj.codegen.templates.description;

import java.util.List;
import org.eclipse.justj.codegen.model.util.Generator.Description.Descriptor;

public class Describer
{
  protected static String nl;
  public static synchronized Describer create(String lineSeparator)
  {
    nl = lineSeparator;
    Describer result = new Describer();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = "</a>";
  protected static final String _2 = "</code> - ";
  protected static final String _3 = "</li>";
  protected static final String _4 = "</span>";
  protected static final String _5 = "</ul>";
  protected static final String _6 = "<a href=\"";
  protected static final String _7 = "<li class=\"jre-gen-item\">";
  protected static final String _8 = "<span class=\"jre-gen-description\"><code>";
  protected static final String _9 = "<ul class=\"jre-gen-group\">";
  protected static final String _10 = "\">";
  protected final String NL_1 = NL + "  ";
  protected final String NL_2 = NL + "    ";
  protected final String _11 = NL + _9;
  protected final String _12 = NL_1 + _7;
  protected final String _13 = NL_1 + _3;
  protected final String _14 = NL + _5;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    Descriptor descriptor = (Descriptor)argument;
  String link = descriptor.getLink();
    builder.append(_8);
    if (link != null) {
    builder.append(_6);
    builder.append(link);
    builder.append(_10);
    }
    builder.append(descriptor.getName());
    if (link != null) {
    builder.append(_1);
    }
    builder.append(_2);
    builder.append(descriptor.getDescription());
    builder.append(_4);
    List<Descriptor> children = descriptor.getChildren();
  if (!children.isEmpty()) {
    builder.append(_11);
    for (Descriptor child : children) {
      String childContent = generate(child);
      String[] lines = childContent.split("\r?\n");
    builder.append(_12);
    for (String line : lines) {
    builder.append(NL_2);
    builder.append(line);
    }
    builder.append(_13);
    }
    builder.append(_14);
    }
    builder.append(NL);
    return builder.toString();
  }
}
