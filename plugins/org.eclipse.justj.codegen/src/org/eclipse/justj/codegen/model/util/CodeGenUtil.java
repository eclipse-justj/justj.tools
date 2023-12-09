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


import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;


public final class CodeGenUtil
{
  private static final Path CACHE_FOLDER;
  static
  {
    String property = System.getProperty("org.eclipse.justj.cache", System.getProperty("user.home") + "/.eclipse/org.eclipse.justj/cache");
    CACHE_FOLDER = Paths.get(property).normalize();
    if (!Files.isDirectory(CACHE_FOLDER))
    {
      try
      {
        Files.createDirectories(CACHE_FOLDER);
      }
      catch (IOException ex)
      {
        throw new RuntimeException(ex);
      }
    }
  }

  private CodeGenUtil()
  {
    throw new RuntimeException("Not instantiable");
  }

  public static String encodeFileName(URI uri)
  {
    String uriString = uri.toString();
    String fileName = uriString.replace(':', '_').replace('/', '_').replace('?', '_').replace('#', '_').replace(';', '_').replace('&', '_');
    return fileName;
  }

  public static Path getCache(Path cacheFolder, URIConverter uriConverter, URI uri) throws IOException
  {
    if (cacheFolder == null)
    {
      cacheFolder = Files.createTempDirectory("justj-local-cache");
    }
    else if (!Files.isDirectory(cacheFolder))
    {
      Files.createDirectories(cacheFolder);
    }

    String fileName = encodeFileName(uri);
    Path result = cacheFolder.resolve(fileName);
    if (!Files.isRegularFile(result))
    {
      try (InputStream inputStream = uriConverter.createInputStream(uri))
      {
        Files.copy(inputStream, result);
      }
    }
    return result;
  }

  public static void untar(Path tarFile, UntarHandler handler) throws IOException
  {
    String fileName = tarFile.getFileName().toString();
    try (InputStream fileInputStream = Files.newInputStream(tarFile);
        InputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        InputStream gzipCompressorInputStream = fileName.endsWith(".gz") ? new GzipCompressorInputStream(bufferedInputStream) : bufferedInputStream;
        TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(gzipCompressorInputStream);)
    {
      for (TarArchiveEntry entry; (entry = tarArchiveInputStream.getNextEntry()) != null;)
      {
        String name = entry.getName();
        Path path = Paths.get(name).normalize();
        Set<PosixFilePermission> posixFilePermissions = getPosixFilePermissions(entry);
        boolean directory = entry.isDirectory();
        if (directory)
        {
          handler.handleDirectory(path, posixFilePermissions);
        }
        else
        {
          boolean symbolicLink = entry.isSymbolicLink();
          if (symbolicLink)
          {
            Path linkPath = Paths.get(entry.getLinkName());
            handler.handleSymbolicLink(path, linkPath);
          }
          else
          {
            boolean file = entry.isFile();
            if (file)
            {
              handler.handleRegularFile(tarArchiveInputStream, path, posixFilePermissions);
            }
            else
            {
              throw new IOException("Unhandled entry type");
            }
          }
        }
      }
    }
  }

  private static Set<PosixFilePermission> getPosixFilePermissions(TarArchiveEntry entry)
  {
    int mode = entry.getMode();
    Set<PosixFilePermission> perms = new TreeSet<>();
    if ((mode & 0400) != 0)
    {
      perms.add(PosixFilePermission.OWNER_READ);
    }
    if ((mode & 0200) != 0)
    {
      perms.add(PosixFilePermission.OWNER_WRITE);
    }
    if ((mode & 0100) != 0)
    {
      perms.add(PosixFilePermission.OWNER_EXECUTE);
    }
    if ((mode & 0040) != 0)
    {
      perms.add(PosixFilePermission.GROUP_READ);
    }
    if ((mode & 0020) != 0)
    {
      perms.add(PosixFilePermission.GROUP_WRITE);
    }
    if ((mode & 0010) != 0)
    {
      perms.add(PosixFilePermission.GROUP_EXECUTE);
    }
    if ((mode & 0004) != 0)
    {
      perms.add(PosixFilePermission.OTHERS_READ);
    }
    if ((mode & 0002) != 0)
    {
      perms.add(PosixFilePermission.OTHERS_WRITE);
    }
    if ((mode & 0001) != 0)
    {
      perms.add(PosixFilePermission.OTHERS_EXECUTE);
    }
    return perms;
  }

  public static class UntarHandler
  {
    public void handleDirectory(Path path, Set<PosixFilePermission> posixFilePermissions) throws IOException
    {
    }

    public void handleRegularFile(InputStream inputStream, Path path, Set<PosixFilePermission> posixFilePermissions) throws IOException
    {
    }

    public void handleSymbolicLink(Path path, Path linkPath) throws IOException
    {
    }
  }

  public static class UntarToTargetHandler extends UntarHandler implements Closeable
  {
    private static final boolean POSIX = Files.getFileAttributeView(Paths.get("."), PosixFileAttributeView.class) != null;

    private final Path target;

    private final Map<Path, Path> symbolicLinks = new HashMap<>();

    public UntarToTargetHandler(Path target)
    {
      this.target = target;
    }

    @Override
    public void handleDirectory(Path path, Set<PosixFilePermission> posixFilePermissions) throws IOException
    {
      Path targetPath = target.resolve(path).normalize();
      Files.createDirectories(targetPath);
      if (POSIX)
      {
        Files.setPosixFilePermissions(targetPath, posixFilePermissions);
      }
    }

    @Override
    public void handleRegularFile(InputStream inputStream, Path path, Set<PosixFilePermission> posixFilePermissions) throws IOException
    {
      Path targetPath = target.resolve(path).normalize();
      Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
      if (POSIX)
      {
        Files.setPosixFilePermissions(targetPath, posixFilePermissions);
      }
    }

    @Override
    public void handleSymbolicLink(Path path, Path linkPath) throws IOException
    {
      Path targetPath = target.resolve(path).normalize();
      if (POSIX)
      {
        Files.createSymbolicLink(targetPath, linkPath);
      }
      else
      {
        Path resolvedLinkPath = targetPath.getParent().resolve(linkPath).normalize();
        symbolicLinks.put(resolvedLinkPath, targetPath);
      }
    }

    @Override
    public void close() throws IOException
    {
      for (Map.Entry<Path, Path> entry : symbolicLinks.entrySet())
      {
        Files.copy(entry.getKey(), entry.getValue(), StandardCopyOption.REPLACE_EXISTING);
      }
    }
  }
}
