/**
 * Copyright (c) 2021 Eclipse contributors and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.justj.codegen.model.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;


public class TemurinIndexer
{
  private static final Pattern JDK_URL = Pattern.compile(//
    "\"browser_download_url\":\"https://github.com/(adoptium/temurin[0-9]+-binaries/releases/download/jdk[0-9]*-[^/]+/OpenJDK[^-]*-jdk_[^\"]*\\.(zip|tar.gz))\"");

  private static final Pattern ASSETS_PATTERN = Pattern.compile("\"assets\":\\[([^]]+)\\]");

  private static final String BEGIN_MARKER = "// Begin generated by /org.eclipse.justj.codegen/src/org/eclipse/justj/codegen/model/util/TemurinIndexer.java";

  private static final String END_MARKER = "// End generated by /org.eclipse.justj.codegen/src/org/eclipse/justj/codegen/model/util/TemurinIndexer.java";

  private static Path cache;

  public static void main(String[] args) throws Exception
  {
    if (args.length > 1)
    {
      cache = Path.of(args[0]);
    }

    PrintStream out = System.out;

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
      {
        int start = 0;

        @Override
        public void flush() throws java.io.IOException
        {
          super.flush();
          System.out.write(buf, start, count - start);
          start = count;
        }
      };

    out = new PrintStream(byteArrayOutputStream, true, StandardCharsets.UTF_8);

    out.println(BEGIN_MARKER);

    for (String repo : new String []{ "11", "17", "18", "19", "20", "21" })
    {
      URI releasesURI = URI.createURI("https://api.github.com/repos/adoptium/temurin" + repo + "-binaries/releases");
      String content = getContent(releasesURI);
      Matcher matcher2 = ASSETS_PATTERN.matcher(content);
      matcher2.find();
      String assets = matcher2.group(1);

      List<String> jdkDownloadURLs = new ArrayList<>();
      for (Matcher matcher3 = JDK_URL.matcher(assets); matcher3.find();)
      {
        jdkDownloadURLs.add("https://github.com/" + matcher3.group(1).replace("%2B", "+"));
      }

      out.println();

      out.println("def java" + repo + "Adoptium = [");
      out.println("  label: 'Java " + repo + " (Adoptium)',");
      out.println("  BUILD_DESCRIPTION: 'Build Adoptium Java " + repo + "',");
      out.println("  PUBLISH_LOCATION_PREFIX: \"${defaultPrefix}\",");
      out.println("  JUSTJ_MANIFEST_URL: \"${justjURL}/${defaultPrefix}/" + repo + "/downloads/latest/justj.manifest\",");
      out.println("  JDK_URLS_WINDOWS: '''");
      {
        String jdkURL = getURL(jdkDownloadURLs, "x64_windows");
        if (jdkURL != null)
        {
          out.println("    " + jdkURL);
        }
      }
      out.println("    ''',");

      out.println("  JDK_URLS_MACOS: '''");
      {
        String jdkURL = getURL(jdkDownloadURLs, "x64_mac");
        if (jdkURL != null)
        {
          out.println("    " + jdkURL);
        }
      }
      out.println("    ''',");

      out.println("  JDK_URLS_MACOS_AARCH64: '''");
      {
        String jdkURL = getURL(jdkDownloadURLs, "aarch64_mac");
        if (jdkURL != null)
        {
          out.println("    " + jdkURL);
        }
      }
      out.println("    ''',");

      out.println("  JDK_URLS_LINUX: '''");
      {
        String jdkURL = getURL(jdkDownloadURLs, "x64_linux");
        if (jdkURL != null)
        {
          out.println("    " + jdkURL);
        }
      }
      out.println("    ''',");

      out.println("  JDK_URLS_LINUX_AARCH64: '''");
      {
        String jdkURL = getURL(jdkDownloadURLs, "aarch64_linux");
        if (jdkURL != null)
        {
          out.println("    " + jdkURL);
        }
      }
      out.println("    ''',");

      out.println("  JDK_URLS_LINUX_PPC64LE: '''");
      {
        String jdkURL = getURL(jdkDownloadURLs, "ppc64le_linux");
        if (jdkURL != null)
        {
          out.println("    " + jdkURL);
        }
      }
      out.println("    ''',");

      out.println("  BUILD_TYPE: 'nightly',");
      out.println("  PROMOTE: 'true'");
      out.println("]");
    }

    out.println();
    out.println(END_MARKER);

    if (args.length > 0)
    {
      Path target = Paths.get(args[args.length - 1]);
      String contents = Files.readString(target);
      String newGeneratedContents = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
      if (!contents.contains("\r\n") && newGeneratedContents.contains("\r\n"))
      {
        newGeneratedContents = newGeneratedContents.replace("\r\n", "\n");
      }
      contents = contents.replaceAll("(?s)" + Pattern.quote(BEGIN_MARKER) + ".*?" + Pattern.quote(END_MARKER) + "\r?\n", Matcher.quoteReplacement(newGeneratedContents));
      Files.writeString(target, contents);
    }
  }

  private static String getURL(List<String> downloadURLs, String type)
  {
    for (String downloadURL : downloadURLs)
    {
      if (downloadURL.contains(type))
      {
        return downloadURL;
      }
    }

    return null;
  }

  private static Path getCachePath(URI uri)
  {
    if (cache == null)
    {
      return null;
    }

    String decodedURI = URI.decode(uri.toString());
    String[] uriSegments = decodedURI.split("[:/?#&;]+");
    Path result = cache.resolve(String.join("/", uriSegments));
    if (uri.hasTrailingPathSeparator())
    {
      return result.resolve("-folder-contents");
    }
    return result;
  }

  private static String getContent(URI uri) throws IOException
  {
    Path path = getCachePath(uri);
    if (path != null && Files.isRegularFile(path))
    {
      return Files.readString(path);
    }

    try (InputStream in = URIConverter.INSTANCE.createInputStream(uri))
    {
      String content = new String(in.readAllBytes(), StandardCharsets.UTF_8);
      if (path != null)
      {
        Files.createDirectories(path.getParent());
        Files.writeString(path, content);
      }
      return content;
    }
  }
}
