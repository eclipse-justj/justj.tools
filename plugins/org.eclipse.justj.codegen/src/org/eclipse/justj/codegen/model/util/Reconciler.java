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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.justj.codegen.model.Annotation;
import org.eclipse.justj.codegen.model.Copyrightable;
import org.eclipse.justj.codegen.model.JVM;
import org.eclipse.justj.codegen.model.Model;
import org.eclipse.justj.codegen.model.ModelFactory;
import org.eclipse.justj.codegen.model.ModelPackage;
import org.eclipse.justj.codegen.model.ModelPlugin;
import org.eclipse.justj.codegen.model.Phase;
import org.eclipse.justj.codegen.model.Touchable;
import org.eclipse.justj.codegen.model.Touchpoint;
import org.eclipse.justj.codegen.model.Variant;


public class Reconciler
{
  private final Model model;

  private final URIConverter uriConverter;

  private final URI source;

  private Path localCache;

  public static void main(String[] args) throws IOException, InterruptedException
  {
    ModelPackage.eINSTANCE.eClass();
    Path path = Paths.get(args[0]);
    Path realPath = path.toRealPath();
    System.out.println("Reconciling: " + realPath);
    Reconciler reconciler = new Reconciler(URI.createFileURI(realPath.toString()));
    Model reconciledModel = reconciler.reconcile(new PrintingProgressMonitor());

    Model model = reconciler.getModel();
    EcoreUtil.replace(model, reconciledModel);
    reconciledModel.eResource().save(Collections.singletonMap(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER));
  }

  public Reconciler(URI modelURI) throws IOException
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    uriConverter = resourceSet.getURIConverter();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("jregen", new ModelResourceFactoryImpl());

    Resource resource = resourceSet.getResource(modelURI, true);
    model = (Model)resource.getContents().get(0);
    this.source = computeSource(model);
    this.localCache = computeLocalCache(model);
  }

  public Reconciler(Model model)
  {
    this.model = model;
    Resource resource = model.eResource();
    uriConverter = resource.getResourceSet().getURIConverter();
    this.source = computeSource(model);
    this.localCache = computeLocalCache(model);
  }

  private static URI computeSource(Model model)
  {
    String source = model.getSource();
    URI resourceURI = model.eResource().getURI();
    if (source == null || source.trim().isEmpty())
    {
      return CommonPlugin.resolve(resourceURI.trimSegments(0)).appendSegment("justj.manifest");
    }
    else
    {
      URI sourceURI = URI.createURI(source);
      if (sourceURI.isRelative())
      {
        sourceURI = sourceURI.resolve(CommonPlugin.resolve(resourceURI.trimSegments(0)));
      }
      return sourceURI;
    }
  }

  static Path computeLocalCache(Model model)
  {
    String localCache = model.getLocalCache();
    if (localCache == null)
    {
      return null;
    }
    else
    {
      Path path = Paths.get(localCache);
      if (path.isAbsolute())
      {
        return path;
      }
      else
      {
        URI resourceURI = model.eResource().getURI();
        URI resolvedURI = CommonPlugin.resolve(resourceURI).trimSegments(1);
        Path result = Paths.get(resolvedURI.toFileString()).resolve(path).normalize();
        return result;
      }
    }
  }

  public Model getModel()
  {
    return model;
  }

  public URI getSource()
  {
    return source;
  }

  public Model reconcile(IProgressMonitor monitor) throws IOException, InterruptedException
  {
    SubMonitor overallMonitor = SubMonitor.convert(monitor);
    overallMonitor.beginTask("Reconciling " + source, 10);

    Map<URI, Path> manifest = loadManifest(overallMonitor.split(9));

    Model modelCopy = EcoreUtil.copy(model);

    modelCopy.getJVMs().clear();

    Exception failure = null;
    SubMonitor manifestMonitor = overallMonitor.split(1);
    manifestMonitor.setWorkRemaining(manifest.size());
    for (Map.Entry<URI, Path> entry : manifest.entrySet())
    {
      URI jreURI = entry.getKey();
      Path localCache = entry.getValue();

      manifestMonitor.subTask("Processing " + jreURI);

      try
      {
        TarAnalyzer handler = new TarAnalyzer();
        CodeGenUtil.untar(localCache, handler);

        Variant variant = handler.reconcile(modelCopy);
        variant.setSource(jreURI.toString());
      }
      catch (Exception ex)
      {
        Files.deleteIfExists(localCache);
        ModelPlugin.INSTANCE.log(ex);
        failure = ex;
      }

      manifestMonitor.worked(1);
    }

    refactorCommonProperties(modelCopy);

    if (failure instanceof IOException)
    {
      throw (IOException)failure;
    }
    else if (failure instanceof RuntimeException)
    {
      throw (RuntimeException)failure;
    }
    else if (failure instanceof InterruptedException)
    {
      throw (InterruptedException)failure;
    }
    else if (failure != null)
    {
      throw new RuntimeException(failure);
    }

    if (Boolean.FALSE)
    {
      Resource resource = model.eResource();
      URI uri = resource.getURI();
      URI targetURI = uri.trimFileExtension().appendFileExtension("reconciled").appendFileExtension(uri.fileExtension());
      resource.setURI(targetURI);
      resource.save(null);
    }

    return modelCopy;
  }

  protected void refactorCommonProperties(Model model)
  {
    EList<JVM> jvms = model.getJVMs();
    for (JVM jvm : jvms)
    {
      EList<Variant> variants = jvm.getVariants();
      Map<String, String> commonProperties = new TreeMap<>();
      Set<String> conflictingKeys = new HashSet<String>();
      for (Variant variant : variants)
      {
        Annotation annotation = ModelUtil.getAnnotation(variant, ModelUtil.MODEL_PROPERTIES_ANNOTATION_URI);
        if (annotation == null)
        {
          commonProperties.clear();
          break;
        }

        for (Map.Entry<String, String> entry : annotation.getDetails().entrySet())
        {
          String key = entry.getKey();
          String value = entry.getValue();
          if (commonProperties.containsKey(key))
          {
            String oldValue = commonProperties.put(key, value);
            if (!Objects.equals(oldValue, value))
            {
              conflictingKeys.add(key);
            }
          }
          else
          {
            commonProperties.put(key, value);
          }
        }
      }

      commonProperties.keySet().removeAll(conflictingKeys);

      if (!commonProperties.isEmpty())
      {
        for (Variant variant : variants)
        {
          Annotation annotation = ModelUtil.getAnnotation(variant, ModelUtil.MODEL_PROPERTIES_ANNOTATION_URI);
          EMap<String, String> details = annotation.getDetails();
          details.keySet().removeAll(commonProperties.keySet());
          if (details.isEmpty())
          {
            EcoreUtil.delete(annotation);
          }
        }

        Annotation annotation = ModelFactory.eINSTANCE.createAnnotation();
        annotation.getDetails().putAll(commonProperties);
        annotation.setSource(ModelUtil.MODEL_PROPERTIES_ANNOTATION_URI);
        jvm.getAnnotations().add(annotation);
      }
    }
  }

  private static class TarAnalyzer extends CodeGenUtil.UntarHandler
  {
    private final Map<Path, Set<PosixFilePermission>> allPosixFilePermissions = new TreeMap<>();

    private final Set<Path> regularFiles = new TreeSet<>();

    private final Map<Path, Path> symbolicLinks = new TreeMap<>();

    private final Properties properties;

    public TarAnalyzer()
    {
      properties = new Properties();
    }

    @Override
    public void handleRegularFile(InputStream inputStream, Path path, Set<PosixFilePermission> posixFilePermissions) throws IOException
    {
      allPosixFilePermissions.put(path, posixFilePermissions);
      if (path.endsWith("org.eclipse.justj.properties"))
      {
        properties.load(inputStream);
      }

      regularFiles.add(path);
    }

    @Override
    public void handleDirectory(Path path, Set<PosixFilePermission> posixFilePermissions)
    {
      allPosixFilePermissions.put(path, posixFilePermissions);
    }

    @Override
    public void handleSymbolicLink(Path path, Path linkPath)
    {
      symbolicLinks.put(path, linkPath);
    }

    public Variant reconcile(Model model)
    {
      String jreName = properties.getProperty("org.eclipse.justj.name");
      String label = properties.getProperty("org.eclipse.justj.label");
      String description = properties.getProperty("org.eclipse.justj.description");

      String javaVersion = getCleanVersion(properties.getProperty("java.version"));

      JVM jvm = getJVM(model, jreName, javaVersion);
      jvm.setLabel(label);
      jvm.setDescription(description);

      TreeMap<String, String> details = new TreeMap<>();
      for (Map.Entry<Object, Object> entry : properties.entrySet())
      {
        details.put(entry.getKey().toString(), entry.getValue().toString());
      }

      String os = properties.getProperty("osgi.os");
      String arch = properties.getProperty("osgi.arch");
      Variant variant = getVariant(jvm, os, arch);

      Annotation annotation = ModelFactory.eINSTANCE.createAnnotation();
      annotation.setSource(ModelUtil.MODEL_PROPERTIES_ANNOTATION_URI);
      annotation.getDetails().putAll(details);
      variant.getAnnotations().add(annotation);

      if (jvm.getAboutTextExtra() == null)
      {
        String aboutTextExtra = "\nVisit https://eclipse.dev/justj";
        String vendor = properties.getProperty("org.eclipse.justj.url.vendor");
        aboutTextExtra += "\n\nProvides Java Runtimes derived from " + vendor + ".";
        jvm.setAboutTextExtra(aboutTextExtra);
      }

      EList<Touchpoint> touchpoints = variant.getTouchpoints();
      Touchpoint touchpoint = ModelFactory.eINSTANCE.createTouchpoint();
      touchpoint.setPhase(Phase.INSTALL);
      touchpoints.add(touchpoint);

      EList<String> instructions = touchpoint.getInstructions();
      String vmArg = properties.getProperty("org.eclipse.justj.vm.arg");
      instructions.add("org.eclipse.equinox.p2.touchpoint.eclipse.setJvm(jvm:${artifact.location}/jre/" + vmArg + ")");

      if (!"win32".equals(os))
      {
        Set<Path> ignore = new HashSet<>();
        Set<Path> executableFolders = new TreeSet<>();
        Set<Path> executableFiles = new TreeSet<>();
        for (Map.Entry<Path, Set<PosixFilePermission>> entry : allPosixFilePermissions.entrySet())
        {
          Path path = entry.getKey();
          if (ignore.add(path))
          {
            Set<PosixFilePermission> posixPermissions = entry.getValue();
            if (regularFiles.contains(path) && posixPermissions.contains(PosixFilePermission.OWNER_EXECUTE))
            {
              Path parent = path.getParent();
              if (parent != null)
              {
                boolean allDescendantsOfParentExecutable = true;
                Set<Path> allDescendants = new HashSet<>();
                for (Map.Entry<Path, Set<PosixFilePermission>> otherEntry : allPosixFilePermissions.entrySet())
                {
                  Path otherPath = otherEntry.getKey();
                  if (otherPath.startsWith(parent))
                  {
                    Set<PosixFilePermission> otherPosixFilePermissions = otherEntry.getValue();
                    if (otherPosixFilePermissions.contains(PosixFilePermission.OWNER_EXECUTE))
                    {
                      allDescendants.add(otherPath);
                    }
                    else if (!symbolicLinks.containsKey(otherPath))
                    {
                      allDescendantsOfParentExecutable = false;
                      break;
                    }
                  }
                }

                if (allDescendantsOfParentExecutable)
                {
                  executableFolders.add(parent);
                }
                else
                {
                  executableFiles.add(path);
                }

                ignore.addAll(allDescendants);
              }
            }
          }
        }

        if (executableFolders.isEmpty() || !executableFiles.isEmpty())
        {
          for (Path executableFolder : executableFolders)
          {
            String chmodTouchpointInstruction = "org.eclipse.equinox.p2.touchpoint.eclipse.chmod(targetDir:${artifact.location},targetFile:jre/"
              + executableFolder.normalize().toString().replace('\\', '/') + ",permissions:+x,options:-R)";
            instructions.add(chmodTouchpointInstruction);
          }

          for (Path executableFile : executableFiles)
          {
            String chmodTouchpointInstruction = "org.eclipse.equinox.p2.touchpoint.eclipse.chmod(targetDir:${artifact.location},targetFile:jre/"
              + executableFile.normalize().toString().replace('\\', '/') + ",permissions:+x)";
            instructions.add(chmodTouchpointInstruction);
          }
        }
      }

      return variant;
    }

    private JVM getJVM(Model model, String name, String javaVersion)
    {
      EList<JVM> jvms = model.getJVMs();
      for (JVM jvm : jvms)
      {
        if (name.equals(jvm.getName()) && javaVersion.equals(jvm.getVersion()))
        {
          return jvm;
        }
      }

      JVM jvm = ModelFactory.eINSTANCE.createJVM();
      jvm.setName(name);
      jvm.setVersion(javaVersion);
      jvms.add(jvm);

      sortJVMs(jvms);
      return jvm;
    }

    private Variant getVariant(JVM jvm, String os, String arch)
    {
      EList<Variant> variants = jvm.getVariants();
      for (Variant variant : variants)
      {
        if (os.equals(variant.getOs()) && arch.equals(variant.getArch()))
        {
          return variant;
        }
      }

      Variant variant = ModelFactory.eINSTANCE.createVariant();
      variant.setOs(os);
      variant.setArch(arch);

      String label;
      switch (os)
      {
        case "linux":
        {
          label = "Linux";
          break;
        }
        case "win32":
        {
          label = "Windows";
          break;
        }
        case "macosx":
        {
          label = "MacOS";
          break;
        }
        default:
        {
          label = os;
          break;
        }
      }

      switch (arch)
      {
        case "x86_64":
        {
          label += " x86 64 bit";
          break;
        }
        case "aarch64":
        {
          label += " aarch 64 bit";
          break;
        }
        case "ppc64le":
        {
          label += " ppc le 64 bit";
          break;
        }
        case "riscv64":
        {
          label += " riscv 64 bit";
          break;
        }
        default:
        {
          label += " " + arch;
          break;
        }
      }

      variant.setLabel(label);
      variants.add(variant);
      sortVariants(variants);
      return variant;
    }
  }

  public static void sortVariants(EList<Variant> variants)
  {
    ECollections.sort(variants, (v1, v2) -> v1.getOs().compareTo(v2.getOs()));
  }

  public static void sortJVMs(EList<JVM> jvms)
  {
    ECollections.sort(jvms, (j1, j2) -> j1.getName().compareTo(j2.getName()));
  }

  private Map<URI, Path> loadManifest(SubMonitor monitor) throws IOException, InterruptedException
  {
    String content = load(source);
    List<String> jreURIs = Arrays.asList(content.split("\r?\n"));

    int originalSize = jreURIs.size();
    monitor.subTask("Fetching " + originalSize + " JREs");
    monitor.setWorkRemaining(originalSize);

    ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);
    Map<URI, Future<Path>> jres = new LinkedHashMap<>();
    AtomicInteger size = new AtomicInteger(originalSize);
    jreURIs.forEach(jre ->
      {
        URI uri = URI.createURI(jre);
        URI jreURI = uri.isRelative() ? uri.resolve(source) : uri;
        jres.put(jreURI, executor.submit(() ->
          {
            try
            {
              monitor.subTask("Fetching " + originalSize + " JREs; starting " + jreURI);
              return CodeGenUtil.getCache(localCache, uriConverter, jreURI);
            }
            finally
            {
              synchronized (monitor)
              {
                monitor.subTask("Fetcthing " + originalSize + " JREs; " + size.decrementAndGet() + " remaining; finished " + jreURI);
                monitor.worked(1);
              }
            }
          }));
      });

    executor.shutdown();

    // Wait for at most 20 minutes.
    for (int elapsed = 0; elapsed < 60 * 20; elapsed += 2)
    {
      try
      {
        executor.awaitTermination(2, TimeUnit.SECONDS);
        monitor.checkCanceled();
        break;
      }
      catch (InterruptedException ex)
      {
        //$FALL-THROUGH$
      }
    }

    Set<Path> cachePaths = new TreeSet<>();
    Map<URI, Path> result = jres.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry ->
      {
        try
        {
          Path cache = entry.getValue().get();
          cachePaths.add(cache);
          return cache;
        }
        catch (InterruptedException | ExecutionException e)
        {
          throw new RuntimeException(e);
        }
      }));

    StringBuilder manifestContent = new StringBuilder();
    cachePaths.stream().forEach(cachePath ->
      {
        manifestContent.append(cachePath.getFileName()).append('\n');
      });

    save(manifestContent.toString(), URI.createFileURI(this.localCache.toString()).appendSegment("justj.manifest"));

    return result;
  }

  protected void save(String text, URI target) throws IOException
  {
    try (OutputStream out = uriConverter.createOutputStream(target); PrintStream print = new PrintStream(out, true, "UTF-8"))
    {
      print.print(text);
    }
  }

  protected void save(URI source, URI target) throws IOException
  {
    try (InputStream in = uriConverter.createInputStream(source); OutputStream out = uriConverter.createOutputStream(target))
    {
      byte[] buffer = new byte [10000];
      int length = in.read(buffer);
      out.write(buffer, 0, length);
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

  protected void save(byte[] source, URI target) throws IOException
  {
    try (OutputStream out = uriConverter.createOutputStream(target); PrintStream print = new PrintStream(out, true, "UTF-8"))
    {
      out.write(source);
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

  private static Pattern CLEAN_VERSION_PATTERN = Pattern.compile("([0-9]+)(?:\\.([0-9]+)(?:\\.([0-9]+))?)?(.*)");

  private static String getCleanVersion(String version)
  {
    Matcher matcher = CLEAN_VERSION_PATTERN.matcher(version);
    if (!matcher.matches())
    {
      throw new IllegalArgumentException("Invalid version " + version);
    }

    String major = matcher.group(1);
    String minor = matcher.group(2);
    String micro = matcher.group(3);

    int majorValue = Integer.parseInt(major);
    int minorValue = minor == null ? 0 : Integer.parseInt(minor);
    int microValue = micro == null ? 0 : Integer.parseInt(micro);
    return majorValue + "." + minorValue + "." + microValue;
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

  static class PrintingProgressMonitor extends NullProgressMonitor
  {
    @Override
    public void beginTask(String name, int totalWork)
    {
      System.out.println("beginTask: " + name);
    }

    @Override
    public void setTaskName(String name)
    {
      System.out.println("setTaskName: " + name);
    }

    @Override
    public void subTask(String name)
    {
      System.out.println("subTask: " + name);
    }
  }
}
