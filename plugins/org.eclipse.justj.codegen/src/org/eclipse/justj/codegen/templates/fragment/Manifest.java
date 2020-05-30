package org.eclipse.justj.codegen.templates.fragment;

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
  protected static final String _3 = ".qualifier";
  protected static final String _4 = ";bundle-version=\"";
  protected static final String _5 = ";singleton:=true";
  protected static final String _6 = "Automatic-Module-Name: ";
  protected static final String _7 = "Bundle-Localization: fragment";
  protected static final String _8 = "Bundle-ManifestVersion: 2";
  protected static final String _9 = "Bundle-Name: %pluginName";
  protected static final String _10 = "Bundle-SymbolicName: ";
  protected static final String _11 = "Bundle-Vendor: %providerName";
  protected static final String _12 = "Bundle-Version: ";
  protected static final String _13 = "Eclipse-BundleShape: dir";
  protected static final String _14 = "Eclipse-PlatformFilter: (& (osgi.os=";
  protected static final String _15 = "Fragment-Host: ";
  protected static final String _16 = "Manifest-Version: 1.0";
  protected static final String _17 = "\"";
  protected final String _18 = _16 + NL + _8 + NL + _10;
  protected final String _19 = _5 + NL + _12;
  protected final String _20 = _3 + NL + _7 + NL + _9 + NL + _11 + NL + _15;
  protected final String _21 = _17 + NL + _13 + NL + _14;
  protected final String _22 = _2 + NL + _6;

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
    builder.append(_18);
    builder.append(fullName);
    builder.append(_19);
    builder.append(version);
    builder.append(_20);
    builder.append(hostName);
    builder.append(_4);
    builder.append(Generator.getVersionRange(version));
    builder.append(_21);
    builder.append(os);
    builder.append(_1);
    builder.append(arch);
    builder.append(_22);
    builder.append(fullName);
    builder.append(NL);
    return builder.toString();
  }
}
