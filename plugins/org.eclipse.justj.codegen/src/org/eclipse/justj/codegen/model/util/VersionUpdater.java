/**
 * Copyright (c) 2022 Eclipse contributors and others.
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
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;


public class VersionUpdater
{
  private static final Set<String> IGNORED_FOLDERS = Set.of("cache", ".git", "local-cache", "target", "jre-gen", "bin");

  private static final Set<String> IGNORED_FILE_EXTENSIONS = Set.of("svg", "gif", "png", "icns", "ico", "log");

  private static final Pattern RELEASE_TRAIN_PATTERN = Pattern.compile("(https://download.eclipse.org/releases/)20[0-9][0-9]-[01][0-9]");

  private static final Pattern ECLIPSE_UPDATES_PATTERN = Pattern.compile("(<url>https://download.eclipse.org/eclipse/updates/)[1-9]+\\.[0-9]+(-I-builds</url>)");

  private static final Pattern JARSIGNER_VERSION_PATTERN = Pattern.compile("(<jarsigner-version>)[^<]+(</jarsigner-version>)");

  private static final Pattern TYCHO_VERSION_PATTERN = Pattern.compile("(<tycho-version>)[^<]+(</tycho-version>)");

  private static final Pattern JRE_VERSION_PATTERN = Pattern.compile("[1-9][0-9]+(\\.0\\.0)(-SNAPSHOT|.qualifier)");

  private static final Pattern JRE_VERSION_RANGE_PATTERN = Pattern.compile("(Require-Bundle: org.eclipse.justj.openjdk.hotspot.jre.full;bundle-version=\"\\[)[^\"]+(\\)\")");

  private static final Pattern JRE_URL_PATTERN = Pattern.compile("(https://download.eclipse.org/justj/jres/)[1-9][0-9]+(/updates/release/latest)");

  private static final Pattern JRE_EE_PATTERN = Pattern.compile("(<executionEnvironment>org.eclipse.justj.openjdk.hotspot.jre.full-)[1-9][0-9]+(</executionEnvironment>)");

  public static void main(String[] args) throws Exception
  {
    String releaseTrainVersion = "2022-09";
    String eclipseUpdatesVersion = "4.26";

    String jarsignerVersion = "1.3.2";
    String tychoVersion = "2.7.4";

    int jreVersion = 17;

    for (String path : args)
    {
      Path root = Path.of(path);
      Files.walkFileTree(root, new SimpleFileVisitor<Path>()
        {
          @Override
          public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
          {
            return IGNORED_FOLDERS.stream().anyMatch(it -> dir.endsWith(it)) ? FileVisitResult.SKIP_SUBTREE : FileVisitResult.CONTINUE;
          }

          @Override
          public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException
          {
            if (attrs.isRegularFile())
            {
              URI uri = URI.createFileURI(path.toString());
              String fileExtension = uri.fileExtension();
              if (fileExtension == null || !IGNORED_FILE_EXTENSIONS.contains(fileExtension))
              {
                String content = Files.readString(path);
                String result = content;
                result = replace(path, result, RELEASE_TRAIN_PATTERN, "$1\\" + releaseTrainVersion);
                result = replace(path, result, ECLIPSE_UPDATES_PATTERN, "$1\\" + eclipseUpdatesVersion + "$2");

                result = replace(path, result, TYCHO_VERSION_PATTERN, "$1\\" + tychoVersion + "$2");
                result = replace(path, result, JARSIGNER_VERSION_PATTERN, "$1\\" + jarsignerVersion + "$2");

                if (uri.segmentsList().contains("epp") || "setup".equals(fileExtension))
                {
                  result = replace(path, result, JRE_VERSION_PATTERN, jreVersion + "$1$2");
                  result = replace(path, result, JRE_VERSION_RANGE_PATTERN, "$1\\" + jreVersion + ".0.0," + (jreVersion + 1) + ".0.0$2");
                  result = replace(path, result, JRE_URL_PATTERN, "$1\\" + jreVersion + "$2");
                  result = replace(path, result, JRE_EE_PATTERN, "$1\\" + jreVersion + "$2");
                }

                if (!result.equals(content))
                {
                  Files.writeString(path, result);
                  System.err.println("> " + path);
                }
              }
            }
            return FileVisitResult.CONTINUE;
          }
        });
    }
  }

  public static String replace(Path file, String content, Pattern pattern, String replacement)
  {
    Matcher matcher = pattern.matcher(content);
    if (matcher.find())
    {
      System.out.println("> " + file + " : " + pattern + " -> " + replacement);

      StringBuilder result = new StringBuilder();
      do
      {
        matcher.appendReplacement(result, replacement);
      }
      while (matcher.find());
      matcher.appendTail(result);
      return result.toString();
    }
    return content;
  }
}
