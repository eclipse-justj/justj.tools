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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
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


public class Generator
{
  private final Model model;

  private final URI target;

  private final Path localCache;

  private final URIConverter uriConverter;

  public static void main(String[] args) throws IOException
  {
    Path path = Paths.get(args[0]);
    Path realPath = path.toRealPath();
    System.out.println("Generating: " + realPath);
    Generator generator = new Generator(URI.createFileURI(realPath.toString()));
    generator.generate(new Reconciler.PrintingProgressMonitor());
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
  }

  public Generator(Model model)
  {
    this.model = model;
    Resource resource = model.eResource();
    uriConverter = resource.getResourceSet().getURIConverter();
    this.target = computeTarget(model);
    this.localCache = Reconciler.computeLocalCache(model);
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

    modelMonitor.beginTask("Generate Model Resources " + target, 6);

    String name = model.getName();
    modelMonitor.subTask("Generating .gitignore");
    save(org.eclipse.justj.codegen.templates.GitIgnore.create(null).generate(model), target.appendSegment(".gitignore"), modelMonitor.split(1));
    save(org.eclipse.justj.codegen.templates.POMXML.create(null).generate(model), target.appendSegment("pom.xml"), modelMonitor.split(1));

    URI siteTarget = target.appendSegment("releng").appendSegment(name + ".site");
    save(org.eclipse.justj.codegen.templates.releng.site.ProjectXML.create(null).generate(model), siteTarget.appendSegment(".project"), modelMonitor.split(1));
    save(org.eclipse.justj.codegen.templates.releng.site.CategoryXML.create(null).generate(model), siteTarget.appendSegment("category.xml"), modelMonitor.split(1));
    save(org.eclipse.justj.codegen.templates.releng.site.POMXML.create(null).generate(model), siteTarget.appendSegment("pom.xml"), modelMonitor.split(1));
    save(org.eclipse.justj.codegen.templates.releng.site.SiteProperties.create(null).generate(model), siteTarget.appendSegment("site.properties"), modelMonitor.split(1));

    URI parentTarget = target.appendSegment("releng").appendSegment(name + ".parent");
    save(org.eclipse.justj.codegen.templates.releng.parent.ProjectXML.create(null).generate(model), parentTarget.appendSegment(".project"), modelMonitor.split(1));
    save(org.eclipse.justj.codegen.templates.releng.parent.POMXML.create(null).generate(model), parentTarget.appendSegment("pom.xml"), modelMonitor.split(1));

    save(
      org.eclipse.justj.codegen.templates.releng.parent.features.POMXML.create(null).generate(model),
      parentTarget.appendSegment("features").appendSegment("pom.xml"),
      modelMonitor.split(1));

    save(
      org.eclipse.justj.codegen.templates.releng.parent.plugins.POMXML.create(null).generate(model),
      parentTarget.appendSegment("plugins").appendSegment("pom.xml"),
      modelMonitor.split(1));

    String aboutURL = model.getAboutURL();
    String aboutHTML = composeLines(load(URI.createURI(aboutURL)), "", org.eclipse.justj.codegen.templates.GitIgnore.create(null).NL);

    for (JVM jvm : jvms)
    {
      SubMonitor jvmMonitor = overallMonitor.split(1);

      EList<Variant> variants = jvm.getVariants();
      jvmMonitor.setWorkRemaining(variants.size() + 18);

      String jvmName = jvm.getName();
      URI featureTarget = target.appendSegment("features").appendSegment(name + "." + jvmName + "-feature");
      save(org.eclipse.justj.codegen.templates.feature.ProjectXML.create(null).generate(jvm), featureTarget.appendSegment(".project"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.feature.BuildProperties.create(null).generate(jvm), featureTarget.appendSegment("build.properties"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.feature.FeatureProperties.create(null).generate(jvm), featureTarget.appendSegment("feature.properties"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.feature.FeatureXML.create(null).generate(jvm), featureTarget.appendSegment("feature.xml"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.feature.P2Inf.create(null).generate(jvm), featureTarget.appendSegment("p2.inf"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.feature.POMXML.create(null).generate(jvm), featureTarget.appendSegment("pom.xml"), jvmMonitor.split(1));

      URI pluginTarget = target.appendSegment("plugins").appendSegment(name + "." + jvmName);
      save(org.eclipse.justj.codegen.templates.plugin.ProjectXML.create(null).generate(jvm), pluginTarget.appendSegment(".project"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.plugin.POMXML.create(null).generate(jvm), pluginTarget.appendSegment("pom.xml"), jvmMonitor.split(1));

      URI pluginMetaInfTarget = pluginTarget.appendSegment("META-INF");
      save(org.eclipse.justj.codegen.templates.plugin.Manifest.create(null).generate(jvm), pluginMetaInfTarget.appendSegment("MANIFEST.MF"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.plugin.EclipseInf.create(null).generate(jvm), pluginMetaInfTarget.appendSegment("eclipse.inf"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.plugin.P2Inf.create(null).generate(jvm), pluginMetaInfTarget.appendSegment("p2.inf"), jvmMonitor.split(1));

      save(aboutHTML, pluginTarget.appendSegment("about.html"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.plugin.AboutIni.create(null).generate(jvm), pluginTarget.appendSegment("about.ini"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.plugin.AboutMappings.create(null).generate(jvm), pluginTarget.appendSegment("about.mappings"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.plugin.AboutProperties.create(null).generate(jvm), pluginTarget.appendSegment("about.properties"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.plugin.BuildProperties.create(null).generate(jvm), pluginTarget.appendSegment("build.properties"), jvmMonitor.split(1));
      save(org.eclipse.justj.codegen.templates.plugin.PluginProperties.create(null).generate(jvm), pluginTarget.appendSegment("plugin.properties"), jvmMonitor.split(1));

      String providerImageName = model.getProviderImageName();
      save(model.getProviderImageData(), pluginTarget.appendSegment(providerImageName), jvmMonitor.split(1));

      for (Variant variant : variants)
      {
        SubMonitor variantMonitor = jvmMonitor.split(1);
        variantMonitor.setWorkRemaining(10);

        String os = variant.getOs();
        String arch = variant.getArch();
        URI fragmentTarget = target.appendSegment("plugins").appendSegment(name + "." + jvmName + "." + os + "." + arch);
        save(org.eclipse.justj.codegen.templates.fragment.ProjectXML.create(null).generate(variant), fragmentTarget.appendSegment(".project"), variantMonitor.split(1));

        URI fragmentMetaInfTarget = fragmentTarget.appendSegment("META-INF");
        save(org.eclipse.justj.codegen.templates.fragment.EclipseInf.create(null).generate(variant), fragmentMetaInfTarget.appendSegment("eclipse.inf"), variantMonitor.split(1));
        save(org.eclipse.justj.codegen.templates.fragment.Manifest.create(null).generate(variant), fragmentMetaInfTarget.appendSegment("MANIFEST.MF"), variantMonitor.split(1));
        save(org.eclipse.justj.codegen.templates.fragment.P2Inf.create(null).generate(variant), fragmentMetaInfTarget.appendSegment("p2.inf"), variantMonitor.split(1));

        save(aboutHTML, fragmentTarget.appendSegment("about.html"), variantMonitor.split(1));
        save(org.eclipse.justj.codegen.templates.fragment.AboutMappings.create(null).generate(variant), fragmentTarget.appendSegment("about.mappings"), variantMonitor.split(1));
        save(
          org.eclipse.justj.codegen.templates.fragment.BuildProperties.create(null).generate(variant),
          fragmentTarget.appendSegment("build.properties"),
          variantMonitor.split(1));
        save(
          org.eclipse.justj.codegen.templates.fragment.FragmentProperties.create(null).generate(variant),
          fragmentTarget.appendSegment("fragment.properties"),
          variantMonitor.split(1));
        save(org.eclipse.justj.codegen.templates.fragment.POMXML.create(null).generate(variant), fragmentTarget.appendSegment("pom.xml"), variantMonitor.split(1));

        URI fragmentJRETarget = fragmentTarget.appendSegment("jre");
        save(org.eclipse.justj.codegen.templates.fragment.GitIgnore.create(null).generate(variant), fragmentJRETarget.appendSegment(".gitignore"), variantMonitor.split(1));

        URI fragmentSettingsTarget = fragmentTarget.appendSegment(".settings");
        save(
          org.eclipse.justj.codegen.templates.fragment.PDEPrefs.create(null).generate(variant),
          fragmentSettingsTarget.appendSegment("org.eclipse.pde.prefs"),
          variantMonitor.split(1));

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
  }

  protected void save(String text, URI target, SubMonitor monitor) throws IOException
  {
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

  protected void save(byte[] source, URI target, SubMonitor monitor) throws IOException
  {
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
