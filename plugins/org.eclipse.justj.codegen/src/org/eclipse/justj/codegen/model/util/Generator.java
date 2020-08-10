/**
 * Copyright (c) 2020 Eclipse contributors and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.justj.codegen.model.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIConverter.WriteableOutputStream;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.justj.codegen.model.Copyrightable;
import org.eclipse.justj.codegen.model.JVM;
import org.eclipse.justj.codegen.model.Model;
import org.eclipse.justj.codegen.model.ModelPackage;
import org.eclipse.justj.codegen.model.Phase;
import org.eclipse.justj.codegen.model.Touchable;
import org.eclipse.justj.codegen.model.Touchpoint;
import org.eclipse.justj.codegen.model.Variant;
import org.eclipse.justj.codegen.model.util.Generator.Description.Descriptor;
import org.osgi.framework.Constants;


public class Generator
{
  private final Model model;

  private final URI target;

  private final Path localCache;

  private final URIConverter uriConverter;

  private final Description description;

  public static void main(String[] args) throws IOException
  {
    Path path = Paths.get(args[0]);
    Path realPath = path.toRealPath();
    if (!shouldDescribe())
    {
      System.out.println("Generating: " + realPath);
    }
    Generator generator = new Generator(URI.createFileURI(realPath.toString()));
    generator.generate(shouldDescribe() ? new NullProgressMonitor() : new Reconciler.PrintingProgressMonitor());
  }

  private static boolean shouldDescribe()
  {
    return "true".equals(System.getProperty("org.eclipse.justj.describe"));
  }

  public Generator(URI source) throws IOException
  {
    ModelPackage.eINSTANCE.eClass();

    ResourceSet resourceSet = new ResourceSetImpl();
    uriConverter = resourceSet.getURIConverter();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("jregen", new ModelResourceFactoryImpl());

    Resource resource = resourceSet.getResource(source, true);
    model = (Model)resource.getContents().get(0);
    this.target = computeTarget(model);
    this.localCache = Reconciler.computeLocalCache(model);
    this.description = new Description(target.appendSegment(""));
  }

  public Generator(Model model)
  {
    this.model = model;
    Resource resource = model.eResource();
    uriConverter = resource.getResourceSet().getURIConverter();
    this.target = computeTarget(model);
    this.localCache = Reconciler.computeLocalCache(model);
    this.description = new Description(target.appendSegment(""));
  }

  private URI computeTarget(Model model)
  {
    String target = model.getTarget();
    Resource resource = model.eResource();
    if (target == null || target.trim().isEmpty())
    {
      return CommonPlugin.resolve(resource.getURI().trimSegments(0));
    }
    else
    {
      URI targetURI = URI.createURI(target);
      if (targetURI.isRelative())
      {
        targetURI = targetURI.resolve(CommonPlugin.resolve(resource.getURI().trimSegments(0)));
      }
      return targetURI;
    }
  }

  public Model getModel()
  {
    return model;
  }

  public URI getTarget()
  {
    return target;
  }

  public void generate(IProgressMonitor monitor) throws IOException
  {
    EList<JVM> jvms = model.getJVMs();

    SubMonitor overallMonitor = SubMonitor.convert(monitor, "Generating " + model.getName() + " to " + target, jvms.size() + 1);

    SubMonitor modelMonitor = overallMonitor.split(1);
    modelMonitor.setWorkRemaining(9);

    modelMonitor.beginTask("Generate Model Resources " + target, 7);

    String name = model.getName();
    modelMonitor.subTask("Generating .gitignore");
    Descriptor descriptor = description.describe(target.appendSegment(""), "the root of the overall model scaffolding");
    save(org.eclipse.justj.codegen.templates.GitIgnore.create(null).generate(model), target.appendSegment(".gitignore"), modelMonitor.split(1), "the root Git ignore information");
    save(org.eclipse.justj.codegen.templates.POMXML.create(null).generate(model), target.appendSegment("pom.xml"), modelMonitor.split(1), "the root POM");

    URI relengTarget = target.appendSegment("releng");
    description.describe(relengTarget.appendSegment(""), "the folder for the releng-related projects");
    URI siteTarget = relengTarget.appendSegment(name + ".site");
    description.describe(siteTarget.appendSegment(""), "the site project");
    save(
      org.eclipse.justj.codegen.templates.releng.site.ProjectXML.create(null).generate(model),
      siteTarget.appendSegment(".project"),
      modelMonitor.split(1),
      "the site project information");
    save(
      org.eclipse.justj.codegen.templates.releng.site.CategoryXML.create(null).generate(model),
      siteTarget.appendSegment("category.xml"),
      modelMonitor.split(1),
      "the site category with a single category for all features");
    save(org.eclipse.justj.codegen.templates.releng.site.POMXML.create(null).generate(model), siteTarget.appendSegment("pom.xml"), modelMonitor.split(1), "the site POM");
    save(
      org.eclipse.justj.codegen.templates.releng.site.SiteProperties.create(null).generate(model),
      siteTarget.appendSegment("site.properties"),
      modelMonitor.split(1),
      "the site properties");

    URI parentTarget = relengTarget.appendSegment(name + ".parent");
    description.describe(parentTarget.appendSegment(""), "the parent project containing the bulk of the Tycho build infrastructure");
    save(
      org.eclipse.justj.codegen.templates.releng.parent.ProjectXML.create(null).generate(model),
      parentTarget.appendSegment(".project"),
      modelMonitor.split(1),
      "the parent project information");
    save(
      org.eclipse.justj.codegen.templates.releng.parent.POMXML.create(null).generate(model),
      parentTarget.appendSegment("pom.xml"),
      modelMonitor.split(1),
      "the parent POM that composes all the other POMs");

    URI relengFeaturesTarget = parentTarget.appendSegment("features");
    description.describe(relengFeaturesTarget.appendSegment(""), "the folder for the features POM which composes all the features");
    save(
      org.eclipse.justj.codegen.templates.releng.parent.features.POMXML.create(null).generate(model),
      relengFeaturesTarget.appendSegment("pom.xml"),
      modelMonitor.split(1),
      "the features POM");

    URI relengPluginsTarget = parentTarget.appendSegment("plugins");
    description.describe(relengPluginsTarget.appendSegment(""), "the folder for the plugins POM which composes all the plugins and fragments");
    save(
      org.eclipse.justj.codegen.templates.releng.parent.plugins.POMXML.create(null).generate(model),
      relengPluginsTarget.appendSegment("pom.xml"),
      modelMonitor.split(1),
      "the plugins POM");

    URI relengPromotionFolder = parentTarget.appendSegment("promotion");
    description.describe(
      relengPromotionFolder.appendSegment(""),
      "the folder for the promotion POM which manages the promotion of the p2 update site to <code>download.eclipse.org</code>");
    save(
      org.eclipse.justj.codegen.templates.releng.parent.promotion.POMXML.create(null).generate(model),
      relengPromotionFolder.appendSegment("pom.xml"),
      modelMonitor.split(1),
      "the promotion POM that uses <code>org.eclipse.justj.p2</code>");

    String aboutURL = model.getAboutURL();
    String aboutHTML = composeLines(load(URI.createURI(aboutURL)), "", org.eclipse.justj.codegen.templates.GitIgnore.create(null).NL);

    URI featuresTarget = target.appendSegment("features");
    description.describe(featuresTarget.appendSegment(""), "the folder for all the features; it will contain one feature per <code>JVM</code>");

    URI pluginsTarget = target.appendSegment("plugins");
    description.describe(
      pluginsTarget.appendSegment(""),
      "the folder for all the plugins and fragments; it will contain one main plugin per <code>JVM</code> and one or more fragments per <code>Variant</code>");

    for (JVM jvm : jvms)
    {
      SubMonitor jvmMonitor = overallMonitor.split(1);

      EList<Variant> variants = jvm.getVariants();
      jvmMonitor.setWorkRemaining(variants.size() + 18);

      String jvmName = jvm.getName();
      URI featureTarget = featuresTarget.appendSegment(name + "." + jvmName + "-feature");
      description.describe(featureTarget.appendSegment(""), "the JRE-specific feature");
      save(
        org.eclipse.justj.codegen.templates.feature.ProjectXML.create(null).generate(jvm),
        featureTarget.appendSegment(".project"),
        jvmMonitor.split(1),
        "the feature project information");
      save(
        org.eclipse.justj.codegen.templates.feature.BuildProperties.create(null).generate(jvm),
        featureTarget.appendSegment("build.properties"),
        jvmMonitor.split(1),
        "the feature build properties");
      save(
        org.eclipse.justj.codegen.templates.feature.FeatureProperties.create(null).generate(jvm),
        featureTarget.appendSegment("feature.properties"),
        jvmMonitor.split(1),
        " the feature NLS properties");
      save(
        org.eclipse.justj.codegen.templates.feature.FeatureXML.create(null).generate(jvm),
        featureTarget.appendSegment("feature.xml"),
        jvmMonitor.split(1),
        "the feature structural information; it includes one plugin and one or more of its corresponding fragments");
      save(
        org.eclipse.justj.codegen.templates.feature.P2Inf.create(null).generate(jvm),
        featureTarget.appendSegment("p2.inf"),
        jvmMonitor.split(1),
        "the directives for additional p2 feature metadata");
      save(org.eclipse.justj.codegen.templates.feature.POMXML.create(null).generate(jvm), featureTarget.appendSegment("pom.xml"), jvmMonitor.split(1), "the feature POM");

      URI pluginTarget = pluginsTarget.appendSegment(name + "." + jvmName);
      description.describe(pluginTarget.appendSegment(""), "the JRE-specific plugin");
      save(
        org.eclipse.justj.codegen.templates.plugin.ProjectXML.create(null).generate(jvm),
        pluginTarget.appendSegment(".project"),
        jvmMonitor.split(1),
        "the plugin project information");
      save(org.eclipse.justj.codegen.templates.plugin.POMXML.create(null).generate(jvm), pluginTarget.appendSegment("pom.xml"), jvmMonitor.split(1), "the plugin POM");

      URI pluginMetaInfTarget = pluginTarget.appendSegment("META-INF");
      description.describe(pluginMetaInfTarget.appendSegment(""), "the plugin manifest folder");
      save(
        org.eclipse.justj.codegen.templates.plugin.Manifest.create(null).generate(jvm),
        pluginMetaInfTarget.appendSegment("MANIFEST.MF"),
        jvmMonitor.split(1),
        "the plugin manifest");
      save(
        org.eclipse.justj.codegen.templates.plugin.EclipseInf.create(null).generate(jvm),
        pluginMetaInfTarget.appendSegment("eclipse.inf"),
        jvmMonitor.split(1),
        "the plugin Tycho build information");
      save(
        org.eclipse.justj.codegen.templates.plugin.P2Inf.create(null).generate(jvm),
        pluginMetaInfTarget.appendSegment("p2.inf"),
        jvmMonitor.split(1),
        "the directives for additional p2 plugin metadata");

      save(aboutHTML, pluginTarget.appendSegment("about.html"), jvmMonitor.split(1), "the branding HTML");
      save(
        org.eclipse.justj.codegen.templates.plugin.AboutIni.create(null).generate(jvm),
        pluginTarget.appendSegment("about.ini"),
        jvmMonitor.split(1),
        "the plugin branding initialization file");
      save(
        org.eclipse.justj.codegen.templates.plugin.AboutMappings.create(null).generate(jvm),
        pluginTarget.appendSegment("about.mappings"),
        jvmMonitor.split(1),
        "the plugin branding mappings");
      save(
        org.eclipse.justj.codegen.templates.plugin.AboutProperties.create(null).generate(jvm),
        pluginTarget.appendSegment("about.properties"),
        jvmMonitor.split(1),
        "the plugin branding properties");
      save(
        org.eclipse.justj.codegen.templates.plugin.BuildProperties.create(null).generate(jvm),
        pluginTarget.appendSegment("build.properties"),
        jvmMonitor.split(1),
        "the plugin build properties");
      save(
        org.eclipse.justj.codegen.templates.plugin.PluginProperties.create(null).generate(jvm),
        pluginTarget.appendSegment("plugin.properties"),
        jvmMonitor.split(1),
        "the plugin NLS properties");

      String providerImageName = model.getProviderImageName();
      save(model.getProviderImageData(), pluginTarget.appendSegment(providerImageName), jvmMonitor.split(1), "the plugin/feature branding image");

      for (Variant variant : variants)
      {
        SubMonitor variantMonitor = jvmMonitor.split(1);
        variantMonitor.setWorkRemaining(10);

        String os = variant.getOs();
        String arch = variant.getArch();
        URI fragmentTarget = pluginsTarget.appendSegment(name + "." + jvmName + "." + os + "." + arch);
        description.describe(fragmentTarget.appendSegment(""), "the JRE-specific, os-specific fragment");
        save(
          org.eclipse.justj.codegen.templates.fragment.ProjectXML.create(null).generate(variant),
          fragmentTarget.appendSegment(".project"),
          variantMonitor.split(1),
          "the fragment project information");

        URI fragmentMetaInfTarget = fragmentTarget.appendSegment("META-INF");
        description.describe(fragmentMetaInfTarget.appendSegment(""), "the fragment manifest folder");
        save(
          org.eclipse.justj.codegen.templates.fragment.EclipseInf.create(null).generate(variant),
          fragmentMetaInfTarget.appendSegment("eclipse.inf"),
          variantMonitor.split(1),
          "the fragment Tycho build information");
        save(
          org.eclipse.justj.codegen.templates.fragment.Manifest.create(null).generate(variant),
          fragmentMetaInfTarget.appendSegment("MANIFEST.MF"),
          variantMonitor.split(1),
          "the fragment manifest");
        save(
          org.eclipse.justj.codegen.templates.fragment.P2Inf.create(null).generate(variant),
          fragmentMetaInfTarget.appendSegment("p2.inf"),
          variantMonitor.split(1),
          "the directives for additional p2 fragment metadata");

        save(aboutHTML, fragmentTarget.appendSegment("about.html"), variantMonitor.split(1), "the branding HTML");
        save(
          org.eclipse.justj.codegen.templates.fragment.AboutMappings.create(null).generate(variant),
          fragmentTarget.appendSegment("about.mappings"),
          variantMonitor.split(1),
          "the fragment branding mappings");
        save(
          org.eclipse.justj.codegen.templates.fragment.BuildProperties.create(null).generate(variant),
          fragmentTarget.appendSegment("build.properties"),
          variantMonitor.split(1),
          "the fragment build properties");
        save(
          org.eclipse.justj.codegen.templates.fragment.FragmentProperties.create(null).generate(variant),
          fragmentTarget.appendSegment("fragment.properties"),
          variantMonitor.split(1),
          "the fragment NLS properties");
        save(
          org.eclipse.justj.codegen.templates.fragment.POMXML.create(null).generate(variant),
          fragmentTarget.appendSegment("pom.xml"),
          variantMonitor.split(1),
          "the fragment's POM");

        URI fragmentJRETarget = fragmentTarget.appendSegment("jre");
        description.describe(fragmentJRETarget.appendSegment(""), "folder containing the actual JRE");
        save(
          org.eclipse.justj.codegen.templates.fragment.GitIgnore.create(null).generate(variant),
          fragmentJRETarget.appendSegment(".gitignore"),
          variantMonitor.split(1),
          "the Git ignore of the fragment's <code>jre</code> folder");

        URI fragmentSettingsTarget = fragmentTarget.appendSegment(".settings");
        description.describe(fragmentSettingsTarget.appendSegment(""), "the fragment preferences folder");
        save(
          org.eclipse.justj.codegen.templates.fragment.PDEPrefs.create(null).generate(variant),
          fragmentSettingsTarget.appendSegment("org.eclipse.pde.prefs"),
          variantMonitor.split(1),
          "the fragment PDE preferences");

        String source = variant.getSource();
        if (source != null)
        {
          SubMonitor untarMonitor = variantMonitor.split(1);
          URI fetchURI = fragmentJRETarget.deresolve(target);
          untarMonitor.subTask("Fetching " + fetchURI);
          Path sourceTarGZ = CodeGenUtil.getCache(localCache, uriConverter, URI.createURI(source));
          untarMonitor.subTask("Untarring " + fetchURI);
          URI jreFolder = CommonPlugin.resolve(fragmentJRETarget);
          if (jreFolder.isFile() && jreFolder.scheme() != null)
          {
            Path jrePath = Paths.get(jreFolder.toFileString());
            CodeGenUtil.UntarToTargetHandler handler = new CodeGenUtil.UntarToTargetHandler(jrePath);
            CodeGenUtil.untar(sourceTarGZ, handler);
            handler.close();
          }
        }
      }
    }

    if (shouldDescribe())
    {
      descriptor.sort();
      String descriptionText = org.eclipse.justj.codegen.templates.description.Describer.create(null).generate(descriptor);
      System.out.print(descriptionText);
    }
  }

  protected void save(String text, URI target, SubMonitor monitor, String description) throws IOException
  {
    this.description.describe(target, description);
    monitor.subTask("Generating " + target.deresolve(this.target));
    try (OutputStream out = uriConverter.createOutputStream(target); PrintStream print = new PrintStream(out, true, "UTF-8"))
    {
      print.print(text);
    }
  }

  protected void save(URI source, URI target, SubMonitor monitor) throws IOException
  {
    monitor.subTask("Generating " + target.deresolve(this.target));
    try (InputStream in = uriConverter.createInputStream(source); OutputStream out = uriConverter.createOutputStream(target))
    {
      byte[] buffer = new byte [10000];
      int length = in.read(buffer);
      out.write(buffer, 0, length);
    }
  }

  protected void save(byte[] source, URI target, SubMonitor monitor, String description) throws IOException
  {
    this.description.describe(target, description);
    monitor.subTask("Generating " + target.deresolve(this.target));
    try (OutputStream out = uriConverter.createOutputStream(target); PrintStream print = new PrintStream(out, true, "UTF-8"))
    {
      out.write(source);
    }
  }

  protected String load(URI source) throws IOException
  {
    try (InputStream in = uriConverter.createInputStream(source))
    {
      byte[] buffer = new byte [100000];
      int length = in.read(buffer);
      return new String(buffer, 0, length, "UTF-8");
    }
  }

  public static String getCopyright(Object argument, String prefix, String separator)
  {
    String copyrightText = null;
    String copyrightYear = null;
    String copyrightHolder = null;
    for (Object object = argument; object instanceof Copyrightable;)
    {
      Copyrightable copyrightable = (Copyrightable)object;
      if (copyrightText == null)
      {
        copyrightText = copyrightable.getCopyrightText();
      }

      if (copyrightYear == null)
      {
        copyrightYear = copyrightable.getCopyrightYear();
      }

      if (copyrightHolder == null)
      {
        copyrightHolder = copyrightable.getCopyrightHolder();
      }

      object = copyrightable.eContainer();
    }

    if (copyrightText != null)
    {
      if (copyrightHolder != null)
      {
        copyrightText = copyrightText.replace("${copyrightHolder}", copyrightHolder);
      }

      if (copyrightYear != null)
      {
        copyrightText = copyrightText.replace("${copyrightYear}", copyrightYear);
      }

      return composeLines(copyrightText, prefix, separator);
    }

    return "";
  }

  public static Map<String, Set<String>> getTouchpoints(Object argument)
  {
    Map<String, Set<String>> result = new LinkedHashMap<String, Set<String>>();
    for (Object object = argument; object instanceof Touchable;)
    {
      Touchable touchable = (Touchable)object;
      for (Touchpoint touchpoint : touchable.getTouchpoints())
      {
        EList<String> instructions = touchpoint.getInstructions();
        if (!instructions.isEmpty())
        {
          Phase phase = touchpoint.getPhase();
          Set<String> composedInstructions = result.get(phase.getLiteral());
          if (composedInstructions == null)
          {
            composedInstructions = new LinkedHashSet<String>();
            result.put(phase.getLiteral(), composedInstructions);
          }
          composedInstructions.addAll(instructions);
        }
      }

      object = touchable.eContainer();
    }

    return result;
  }

  public static String composeLines(String body, String prefix, String separator)
  {
    if (body != null)
    {
      String[] lines = body.split("\r?\n", -1);
      StringBuilder result = new StringBuilder();
      for (int i = 0; i < lines.length; i++)
      {
        String line = lines[i];
        result.append(prefix);
        result.append(line);
        if (i != lines.length - 1)
        {
          result.append(separator);
        }
      }

      return result.toString();
    }

    return "";
  }

  public static String getVersionRange(String version)
  {
    int[] versionValue = getVersion(version);

    return "[" + version + "," + versionValue[0] + '.' + versionValue[1] + '.' + (versionValue[2] + 1) + ")";
  }

  public static Map<String, Set<String>> getEECapabilities(String version)
  {
    int[] versionValue = getVersion(version);
    Map<String, Set<String>> result = new LinkedHashMap<>();

    Set<String> osgiMinimum = new LinkedHashSet<>();
    result.put("OSGi/Minimum", osgiMinimum);
    for (int i = 0; i <= 2; ++i)
    {
      osgiMinimum.add("1." + i);
    }

    Set<String> jre = new LinkedHashSet<>();
    result.put("JRE", jre);
    for (int i = 0; i <= 1; ++i)
    {
      jre.add("1." + i);
    }

    Set<String> javaSE = new LinkedHashSet<>();
    result.put("JavaSE", javaSE);
    for (int i = 0; i <= 8; ++i)
    {
      javaSE.add("1." + i);
    }
    for (int i = 9; i <= versionValue[0]; ++i)
    {
      javaSE.add("" + i + ".0");
    }

    for (int compact = 1; compact <= 3; ++compact)
    {
      Set<String> javaCompact = new LinkedHashSet<>();
      result.put("JavaSE/compact" + compact, javaCompact);
      javaCompact.add("1.8");
      for (int i = 9; i <= versionValue[0]; ++i)
      {
        javaCompact.add("" + i + ".0");
      }
    }

    return result;
  }

  public static Set<String> getSystemPackages(JVM jvm)
  {
    Set<String> result = new TreeSet<>();
    for (Variant variant : jvm.getVariants())
    {
      result.addAll(getSystemPackages(variant));
    }
    return result;
  }

  public static Set<String> getSystemPackages(Variant variant)
  {
    String systemPackages = ModelUtil.getAnnotation(variant, ModelUtil.MODEL_PROPERTIES_ANNOTATION_URI, Constants.FRAMEWORK_SYSTEMPACKAGES);
    if (systemPackages == null)
    {
      systemPackages = ModelUtil.getAnnotation(variant.getJVM(), ModelUtil.MODEL_PROPERTIES_ANNOTATION_URI, Constants.FRAMEWORK_SYSTEMPACKAGES);
    }

    Set<String> result = new TreeSet<>();
    if (systemPackages != null)
    {
      for (String systemPackage : systemPackages.split("\\s*,\\s*"))
      {
        if (!systemPackage.isEmpty())
        {
          result.add(systemPackage);
        }
      }
    }
    return result;
  }

  public static String getModelXMLAsPropertyValue(EObject eObject, String nl)
  {
    try (StringWriter stringWriter = new StringWriter(); WriteableOutputStream writeableOutputStream = new URIConverter.WriteableOutputStream(stringWriter, "UTF-8"))
    {
      Resource resource = new ModelResourceFactoryImpl().createResource(URI.createURI("dummy.jregen"));
      EObject copy = EcoreUtil.copy(eObject);
      List<EObject> childrenToDelete = new ArrayList<>();
      for (EObject child : copy.eContents())
      {
        if (child instanceof Copyrightable)
        {
          childrenToDelete.add(child);
        }
      }

      EcoreUtil.deleteAll(childrenToDelete, false);

      resource.getContents().add(copy);

      resource.save(writeableOutputStream, null);
      String modelXML = stringWriter.toString();
      String normalizedXML = modelXML.replaceAll("\r?\n", "\n");

      Properties properties = new Properties();
      properties.put("value", normalizedXML);

      StringWriter propertiesValue = new StringWriter();
      properties.store(propertiesValue, null);
      String value = propertiesValue.toString();
      value = value.substring(value.indexOf('=') + 1);

      String transformedValue = value.replace("\\=", "=").replace("\\:", ":");

      Matcher matcher = Pattern.compile("\\\\n(.)").matcher(transformedValue);
      StringBuffer result = new StringBuffer();
      while (matcher.find())
      {
        String trailingCharacter = matcher.group(1);
        if (" ".equals(trailingCharacter))
        {
          matcher.appendReplacement(result, Matcher.quoteReplacement("\\n\\" + nl + "\\" + trailingCharacter));
        }
        else if (!"\\".equals(trailingCharacter))
        {
          matcher.appendReplacement(result, Matcher.quoteReplacement("\\n\\" + nl + " " + trailingCharacter));
        }
      }
      matcher.appendTail(result);

      Properties properties2 = new Properties();
      properties2.load(new StringReader("value=" + result));
      String foo = properties2.getProperty("value");
      if (!foo.equals(normalizedXML))
      {
        System.err.println("####!!!!");
      }

      return result.toString();
    }
    catch (IOException exception)
    {
      throw new RuntimeException(exception);
    }
  }

  public static class Application implements IApplication
  {
    @Override
    public Object start(IApplicationContext context) throws Exception
    {
      main((String[])context.getArguments().get("application.args"));
      return 0;
    }

    @Override
    public void stop()
    {
    }
  }

  public static class Description
  {
    private static String BUILD_ARTIFACTS = "https://ci.eclipse.org/justj/job/build-jres/lastSuccessfulBuild/artifact/jre-gen/";

    private final URI root;

    private final Map<URI, Descriptor> descriptors = new LinkedHashMap<>();

    public Description(URI root)
    {
      this.root = root;
    }

    public Descriptor describe(URI uri, String description)
    {
      URI relativeURI = uri.deresolve(root);
      if (relativeURI.isCurrentDocumentReference())
      {
        relativeURI = URI.createURI("/");
      }
      URI parentFolder = relativeURI.trimSegments(relativeURI.hasTrailingPathSeparator() ? 2 : 1).appendSegment("");
      Descriptor descriptor = descriptors.get(parentFolder);
      descriptor = new Descriptor(relativeURI, description, descriptor);
      descriptors.put(relativeURI, descriptor);
      return descriptor;
    }

    public static class Descriptor
    {
      private URI uri;

      private final String description;

      private final Descriptor parent;

      private final List<Descriptor> children = new ArrayList<>();

      public Descriptor(URI uri, String description, Descriptor parent)
      {
        this.uri = uri;
        this.description = description;
        this.parent = parent;
        if (parent != null)
        {
          parent.children.add(this);
        }
      }

      public String getDescription()
      {
        return description;
      }

      public Descriptor getParent()
      {
        return parent;
      }

      public List<Descriptor> getChildren()
      {
        return children;
      }

      public String getName()
      {
        String name = uri.hasTrailingPathSeparator() ? uri.trimSegments(1).lastSegment() : uri.lastSegment();
        return name == null ? "jre-gen" : name;
      }

      public String getLink()
      {
        if (uri.hasTrailingPathSeparator() || uri.segmentCount() == 0)
        {
          return BUILD_ARTIFACTS + uri;
        }
        else
        {
          String name = uri.lastSegment();
          if (name.startsWith("."))
          {
            return null;
          }
          else
          {
            return BUILD_ARTIFACTS + uri + "/*view*/";
          }
        }
      }

      public void sort()
      {
        Collections.sort(children, new Comparator<Descriptor>()
          {
            @Override
            public int compare(Descriptor o1, Descriptor o2)
            {
              boolean hasTrailingPathSeparator1 = o1.uri.hasTrailingPathSeparator();
              boolean hasTrailingPathSeparator2 = o2.uri.hasTrailingPathSeparator();
              if (hasTrailingPathSeparator1 == hasTrailingPathSeparator2)
              {
                return o1.getName().compareTo(o2.getName());
              }
              else
              {
                // Folders come first.
                return hasTrailingPathSeparator1 ? -1 : 1;
              }
            }
          });

        for (Descriptor child : children)
        {
          child.sort();
        }
      }
    }
  }

  private static Pattern VERSION_PATTERN = Pattern.compile("([0-9]+)\\.([0-9]+)\\.([0-9]+)");

  private static int[] getVersion(String version)
  {
    Matcher matcher = VERSION_PATTERN.matcher(version);
    if (!matcher.matches())
    {
      throw new IllegalArgumentException("Invalid version " + version);
    }

    return new int []{ Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)) };
  }
}
