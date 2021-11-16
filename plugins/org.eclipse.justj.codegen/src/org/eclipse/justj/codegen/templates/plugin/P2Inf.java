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

import java.util.*;
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
  protected static final String _3 = "# Define an a.jre unit that can be used as a profile ID in a Tycho build.";
  protected static final String _4 = "# Ensure that the a.jre IU is published to the update site.";
  protected static final String _5 = "# Ensure that the applicable implementation fragment gets installed automatically.";
  protected static final String _6 = "# Exclude all other providers of this capability; the site depends on all the features so exclude this requirement at build time.";
  protected static final String _7 = "# Exclude the synthetic a.jre IUs from the p2 repositories; Tycho gets confused by negative requirements so filter this to avoid it at build time.";
  protected static final String _8 = "# Exclude the synthetic a.jre.javase IUs from the p2 repositories; Tycho gets confused by negative requirements so filter this to avoid it at build time.";
  protected static final String _9 = "' && x.namespace == 'org.eclipse.equinox.p2.iu')";
  protected static final String _10 = ")(!(";
  protected static final String _11 = ")(osgi.arch=";
  protected static final String _12 = ",";
  protected static final String _13 = ".";
  protected static final String _14 = ".assembletime=true)";
  protected static final String _15 = ".buildtime=true))";
  protected static final String _16 = ".buildtime=true)))";
  protected static final String _17 = ".filter = (!(";
  protected static final String _18 = ".filter = (&(osgi.os=";
  protected static final String _19 = ".matchExp = providedCapabilities.exists(x | x.name == 'jre' && x.namespace == 'org.eclipse.justj') && !providedCapabilities.exists(x | x.name == '";
  protected static final String _20 = ".max = 0";
  protected static final String _21 = ".min = 0";
  protected static final String _22 = ".name = ";
  protected static final String _23 = ".name = a.jre";
  protected static final String _24 = ".name = a.jre.javase";
  protected static final String _25 = ".namespace = java.package";
  protected static final String _26 = ".namespace = org.eclipse.equinox.p2.iu";
  protected static final String _27 = ".namespace = osgi.ee";
  protected static final String _28 = ".range = [$version$,$version$]";
  protected static final String _29 = ".version = ";
  protected static final String _30 = ".version = 0.0.0";
  protected static final String _31 = "]";
  protected static final String _32 = "properties.0.name = org.eclipse.justj.model";
  protected static final String _33 = "properties.0.value = ";
  protected static final String _34 = "provides.0.name = jre";
  protected static final String _35 = "provides.0.namespace = org.eclipse.justj";
  protected static final String _36 = "provides.0.version = $version$";
  protected static final String _37 = "requires.";
  protected static final String _38 = "requires.0.filter = (";
  protected static final String _39 = "requires.0.greedy = true";
  protected static final String _40 = "requires.0.name = a.jre.";
  protected static final String _41 = "requires.0.namespace = org.eclipse.equinox.p2.iu";
  protected static final String _42 = "requires.0.optional = true";
  protected static final String _43 = "requires.0.range = [";
  protected static final String _44 = "units.0.id = a.jre.";
  protected static final String _45 = "units.0.provides.";
  protected static final String _46 = "units.0.provides.0.name = a.jre.";
  protected static final String _47 = "units.0.provides.0.namespace = org.eclipse.equinox.p2.iu";
  protected static final String _48 = "units.0.provides.0.version = ";
  protected static final String _49 = "units.0.version = ";
  protected final String _50 = "";
  protected final String _51 = NL + _1 + NL + NL + _3 + NL + NL + _44;
  protected final String _52 = NL + _49;
  protected final String _53 = NL + NL + _47 + NL + _46;
  protected final String _54 = NL + _48;
  protected final String _55 = NL + _45;
  protected final String _56 = _27 + NL + _45;
  protected final String _57 = _25 + NL + _45;
  protected final String _58 = _30 + NL;
  protected final String _59 = NL + _4 + NL + NL + _41 + NL + _40;
  protected final String _60 = NL + _43;
  protected final String _61 = _31 + NL + _38;
  protected final String _62 = _14 + NL + _39 + NL + _42 + NL + NL + _5 + NL;
  protected final String _63 = NL + _37;
  protected final String _64 = _26 + NL + _37;
  protected final String _65 = _28 + NL + _37;
  protected final String _66 = _16 + NL;
  protected final String _67 = NL + _7;
  protected final String _68 = _23 + NL + _37;
  protected final String _69 = _21 + NL + _37;
  protected final String _70 = _20 + NL + _37;
  protected final String _71 = _15 + NL + NL + _8;
  protected final String _72 = _24 + NL + _37;
  protected final String _73 = _15 + NL + NL + _2 + NL + _35 + NL + _34 + NL + _36 + NL + NL + _6;
  protected final String _74 = _9 + NL + _37;
  protected final String _75 = _15 + NL + NL + _32 + NL + _33;

  public String generate(Object argument)
  {
    final StringBuilder builder = new StringBuilder();
    JVM jvm = (JVM)argument;
  String jvmName = jvm.getName();
  Model model = jvm.getModel();
  String modelName = model.getName();
  String version = jvm.getVersion();
  String minorlessVersion = version.replaceAll("\\.[0-9]+\\.[0-9]+$", ".0.0");
  String fullName = modelName + '.' + jvmName;
    builder.append(_50);
    builder.append(org.eclipse.justj.codegen.model.util.Generator.getCopyright(argument, "# ", NL));
    builder.append(_51);
    builder.append(fullName);
    builder.append(_52);
    builder.append(minorlessVersion);
    builder.append(_53);
    builder.append(fullName);
    builder.append(_54);
    builder.append(minorlessVersion);
    builder.append(NL);
    int propertyCount = 1;
  for (Map.Entry<String, Set<String>> entry : Generator.getEECapabilities(version).entrySet()) {
    String eeName = entry.getKey();
    Set<String> versions = entry.getValue();
    for (String eeVersion : versions) {
      int capabilityNumber = propertyCount++;
    builder.append(_55);
    builder.append(capabilityNumber);
    builder.append(_56);
    builder.append(capabilityNumber);
    builder.append(_22);
    builder.append(eeName);
    builder.append(_55);
    builder.append(capabilityNumber);
    builder.append(_29);
    builder.append(eeVersion);
    builder.append(NL);
    }
    }
    Set<String> systemPackages = Generator.getSystemPackages(jvm);
  for (String systemPackage : systemPackages) {
    int capabilityNumber = propertyCount++;
    builder.append(_55);
    builder.append(capabilityNumber);
    builder.append(_57);
    builder.append(capabilityNumber);
    builder.append(_22);
    builder.append(systemPackage);
    builder.append(_55);
    builder.append(capabilityNumber);
    builder.append(_58);
    }
    builder.append(_59);
    builder.append(fullName);
    builder.append(_60);
    builder.append(minorlessVersion);
    builder.append(_12);
    builder.append(minorlessVersion);
    builder.append(_61);
    builder.append(modelName);
    builder.append(_62);
    int count = 0;
  for (Variant variant : jvm.getVariants()) {
    ++count;
    String os = variant.getOs();
    String arch = variant.getArch();
    builder.append(_63);
    builder.append(count);
    builder.append(_64);
    builder.append(count);
    builder.append(_22);
    builder.append(fullName);
    builder.append(_13);
    builder.append(os);
    builder.append(_13);
    builder.append(arch);
    builder.append(_63);
    builder.append(count);
    builder.append(_65);
    builder.append(count);
    builder.append(_18);
    builder.append(os);
    builder.append(_11);
    builder.append(arch);
    builder.append(_10);
    builder.append(modelName);
    builder.append(_66);
    }
    builder.append(_67);
    ++count;
    builder.append(_63);
    builder.append(count);
    builder.append(_64);
    builder.append(count);
    builder.append(_68);
    builder.append(count);
    builder.append(_69);
    builder.append(count);
    builder.append(_70);
    builder.append(count);
    builder.append(_17);
    builder.append(modelName);
    builder.append(_71);
    ++count;
    builder.append(_63);
    builder.append(count);
    builder.append(_64);
    builder.append(count);
    builder.append(_72);
    builder.append(count);
    builder.append(_69);
    builder.append(count);
    builder.append(_70);
    builder.append(count);
    builder.append(_17);
    builder.append(modelName);
    builder.append(_73);
    ++count;
    builder.append(_63);
    builder.append(count);
    builder.append(_19);
    builder.append(fullName);
    builder.append(_74);
    builder.append(count);
    builder.append(_69);
    builder.append(count);
    builder.append(_70);
    builder.append(count);
    builder.append(_17);
    builder.append(modelName);
    builder.append(_75);
    builder.append(Generator.getModelXMLAsPropertyValue(jvm, NL));
    builder.append(NL);
    return builder.toString();
  }
}
