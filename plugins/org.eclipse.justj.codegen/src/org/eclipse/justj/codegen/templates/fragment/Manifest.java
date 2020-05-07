package org.eclipse.justj.codegen.templates.fragment;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.justj.codegen.model.*;
import org.eclipse.justj.codegen.model.util.Generator;

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
  protected static final String _1 = ") (osgi.arch=";
  protected static final String _2 = "))";
  protected static final String _3 = ",";
  protected static final String _4 = ".qualifier";
  protected static final String _5 = ";bundle-version=\"";
  protected static final String _6 = ";singleton:=true";
  protected static final String _7 = "Automatic-Module-Name: ";
  protected static final String _8 = "Bundle-Localization: fragment";
  protected static final String _9 = "Bundle-ManifestVersion: 2";
  protected static final String _10 = "Bundle-Name: %pluginName";
  protected static final String _11 = "Bundle-SymbolicName: ";
  protected static final String _12 = "Bundle-Vendor: %providerName";
  protected static final String _13 = "Bundle-Version: ";
  protected static final String _14 = "Eclipse-BundleShape: dir";
  protected static final String _15 = "Eclipse-PlatformFilter: (& (osgi.os=";
  protected static final String _16 = "Fragment-Host: ";
  protected static final String _17 = "Manifest-Version: 1.0";
  protected static final String _18 = "Provide-Capability: ";
  protected static final String _19 = "\"";
  protected static final String _20 = "\";version:Version=\"";
  protected static final String _21 = "osgi.ee;osgi.ee=\"";
  protected final String NL_1 = NL + " ";
  protected final String _22 = _17 + NL + _9 + NL + _11;
  protected final String _23 = _6 + NL + _13;
  protected final String _24 = _4 + NL + _8 + NL + _10 + NL + _12 + NL + _16;
  protected final String _25 = _19 + NL + _14 + NL + _15;
  protected final String _26 = _2 + NL + _7;
  protected final String _27 = NL + _18;
  protected final String _28 = NL_1 + _21;

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
  String hostName = modelName + '.' + jvmName;
  String fullName = hostName + '.' + os + '.' + arch;
  String version = jvm.getVersion();
    builder.append(_22);
    builder.append(fullName);
    builder.append(_23);
    builder.append(version);
    builder.append(_24);
    builder.append(hostName);
    builder.append(_5);
    builder.append(Generator.getVersionRange(version));
    builder.append(_25);
    builder.append(os);
    builder.append(_1);
    builder.append(arch);
    builder.append(_26);
    builder.append(fullName);
    builder.append(_27);
    
    for (Iterator<Entry<String, Set<String>>> it = Generator.getEECapabilities(version).entrySet().iterator(); it.hasNext();) {
    Entry<String, Set<String>> entry = it.next();
    for (Iterator<String> it2 = entry.getValue().iterator(); it2.hasNext();) {
      String eeVersion = it2.next();
    builder.append(_28);
    builder.append(entry.getKey());
    builder.append(_20);
    builder.append(eeVersion);
    builder.append(_19);
    if (it.hasNext() || it2.hasNext()) {
    builder.append(_3);
    }
    }
    }
    builder.append(NL);
    return builder.toString();
  }
}
