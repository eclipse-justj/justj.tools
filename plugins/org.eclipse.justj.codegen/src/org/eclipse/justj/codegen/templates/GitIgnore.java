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
package org.eclipse.justj.codegen.templates;

public class GitIgnore
{
  protected static String nl;
  public static synchronized GitIgnore create(String lineSeparator)
  {
    nl = lineSeparator;
    GitIgnore result = new GitIgnore();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected static final String _1 = "target/";

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    builder.append(_1);
    builder.append(NL);
    return builder.toString();
  }
}
