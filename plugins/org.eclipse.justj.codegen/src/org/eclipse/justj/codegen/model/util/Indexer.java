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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.justj.codegen.templates.index.JREIndex;


public class Indexer
{
  private final URIConverter uriConverter;

  private final URI manifestURI;

  private final URI target;

  private final URI downloadURI;

  public static void main(String[] args) throws IOException
  {
    // System.out.print(JREIndex.create(null).generate(null));

    Path path = Paths.get(args[0]);
    Path realPath = path.toRealPath();
    System.out.println("Indexing: " + realPath);
    URI uri = URI.createFileURI(realPath.toString());
    URI downloadURI = args.length > 1 ? URI.createURI(args[1]) : uri;
    Indexer indexer = new Indexer(uri, downloadURI);

    indexer.generate(new Reconciler.PrintingProgressMonitor());
  }

  public Indexer(URI manifestURI, URI downloadURI) throws IOException
  {
    this.manifestURI = manifestURI;
    this.downloadURI = downloadURI;
    uriConverter = URIConverter.INSTANCE;
    target = manifestURI.trimSegments(1).appendSegment("");
  }

  void generate(IProgressMonitor monitor) throws IOException
  {
    SubMonitor overallMonitor = SubMonitor.convert(monitor);

    String content = load(manifestURI);
    List<String> jreURIs = Arrays.asList(content.split("\r?\n"));
    int size = jreURIs.size();

    overallMonitor.beginTask("Processing " + size + " JREs from " + manifestURI, size);
    List<JRE> jres = new ArrayList<>();
    for (String jreURI : jreURIs)
    {
      URI uri = URI.createURI(jreURI);
      URI resolvedURI = uri.isRelative() ? uri.resolve(target) : uri;
      overallMonitor.subTask("Processing " + resolvedURI);

      PropertiesLoader handler = new PropertiesLoader();
      Path tarFilePath = Paths.get(resolvedURI.toFileString());
      CodeGenUtil.untar(tarFilePath, handler);
      Properties properties = handler.getProperties();

      Map<String, String> propertyStrings = new TreeMap<>();
      for (Map.Entry<Object, Object> entry : properties.entrySet())
      {
        propertyStrings.put(entry.getKey().toString(), entry.getValue().toString());
      }

      long fileSize = Files.size(tarFilePath);
      URI resolvedDownloadURI = uri.isRelative() ? uri.resolve(downloadURI) : uri;
      jres.add(new JRE(resolvedURI, resolvedDownloadURI, fileSize, propertyStrings));
    }

    jres.sort((j1, j2) ->
      {
        String modules1 = j1.properties.get("org.eclipse.justj.modules");
        String modules2 = j2.properties.get("org.eclipse.justj.modules");
        int difference = modules2.split(",").length - modules1.split(",").length;
        return difference > 4 ? 1 : difference < -4 ? -1 : 0;
      });

    save(JREIndex.create(null).generate(jres), URI.createURI("index.html").resolve(target), SubMonitor.convert(monitor));
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

  public static class JRE
  {
    private final URI uri;

    private final Map<String, String> properties;

    private final long fileSize;

    private final URI downloadURI;

    public JRE(URI uri, URI downloadURI, long fileSize, Map<String, String> properties)
    {
      this.uri = uri;
      this.downloadURI = downloadURI;
      this.fileSize = fileSize;
      this.properties = properties;
    }

    public String getName()
    {
      String name = uri.lastSegment();
      int index = name.lastIndexOf("_org");
      return index == -1 ? name : name.substring(index + 1);
    }

    public String getDownloadFolder()
    {
      return downloadURI.trimSegments(1).lastSegment();
    }

    public String getSizeInMegaBytes()
    {
      float size = ((float)fileSize) / 1024 / 1024;
      return String.format(java.util.Locale.US, "%.1f", size);
    }

    public List<Property> getProperties()
    {
      return Property.create(properties);
    }

    public URI getDownloadURI()
    {
      URI effectiveDownloadURI = downloadURI;
      String fileName = downloadURI.lastSegment();
      URI uriForName = downloadURI.trimSegments(1).appendSegment(getName());
      String encodedFileName = CodeGenUtil.encodeFileName(uriForName);
      if (fileName.equals(encodedFileName))
      {
        effectiveDownloadURI = uriForName;
      }

      URI deresolvedURI = effectiveDownloadURI.deresolve(URI.createURI("https://download.eclipse.org/"));
      if (deresolvedURI != effectiveDownloadURI)
      {
        effectiveDownloadURI = URI.createURI("https://www.eclipse.org/downloads/download.php?file=" + deresolvedURI);
      }

      return effectiveDownloadURI;
    }
  }

  public static class Property
  {
    private final String key;

    private final String value;

    public Property(String key, String value)
    {
      this.key = key;
      this.value = value;
    }

    public static List<Property> create(Map<String, String> properties)
    {
      List<Property> result = new ArrayList<>();
      if (properties != null)
      {
        for (Map.Entry<String, String> entry : properties.entrySet())
        {
          result.add(new Property(entry.getKey(), entry.getValue()));
        }
      }
      return result;
    }

    protected boolean isImportant()
    {
      return "org.eclipse.justj.modules".equals(key) || "org.osgi.framework.system.packages".equals(key) || "org.eclipse.justj.jlink.args".equals(key)
        || "org.eclipse.justj.description".equals(key);
    }

    public String getKey()
    {
      if (isImportant())
      {
        return "<b>" + key + "</b>";
      }
      else
      {
        return key;
      }
    }

    public String getValue()
    {
      if (value.startsWith("http://") || value.startsWith("https://"))
      {
        return "<a href=\"" + value + "\">" + value + "</a>";
      }
      else
      {
        String result = escapeHTML(value);
        return isImportant() ? "<b>" + result + "</b>" : result;
      }
    }

    private String escapeHTML(String value)
    {
      String result = value.replace("&", "&amp;").replace("<", "&lt;");
      return result;
    }

    public List<String> getChildren()
    {
      if ("org.eclipse.justj.modules".equals(key) || "org.osgi.framework.system.packages".equals(key))
      {
        String[] children = value.split(",");
        return Arrays.asList(children);
      }
      else if ("org.osgi.framework.system.capabilities".equals(key))
      {
        List<String> children = new ArrayList<String>();

        int start = 0;
        for (int next = value.indexOf("osgi.ee; ", 1); next != -1; start = next, next = value.indexOf("osgi.ee; ", start + 1))
        {
          children.add(escapeHTML(value.substring(start, next)));
        }

        children.add(escapeHTML(value.substring(start)));

        return children;
      }

      return Collections.emptyList();
    }
  }

  private static class PropertiesLoader extends CodeGenUtil.UntarHandler
  {
    private final Properties properties;

    public PropertiesLoader()
    {
      properties = new Properties();
    }

    public Properties getProperties()
    {
      return properties;
    }

    @Override
    public void handleRegularFile(InputStream inputStream, Path path, Set<PosixFilePermission> posixFilePermissions) throws IOException
    {
      if (path.endsWith("org.eclipse.justj.properties"))
      {
        properties.load(inputStream);
      }
    }
  }
}
