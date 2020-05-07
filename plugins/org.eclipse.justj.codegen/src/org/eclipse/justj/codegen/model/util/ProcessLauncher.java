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


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;


public class ProcessLauncher
{
  public static void main(String[] args) throws IOException, InterruptedException
  {
    ProcessBuilder builder = new ProcessBuilder(args);

    // This is needed on Windows to find gzip and won't hurt elsewhere.
    Map<String, String> environment = builder.environment();
    Path executable = Paths.get(args[0]);
    String path = environment.get("PATH");
    environment.put("PATH", path + File.pathSeparator + executable.getParent());

    Process process = builder.start();
    OutputStream stdin = process.getOutputStream();
    stdin.close();

    InputStream stderr = process.getErrorStream();
    new StreamHandler(stderr, System.err::println).start();

    Set<String> result = new TreeSet<>();
    InputStream stdout = process.getInputStream();
    new StreamHandler(stdout, line ->
      {
        // Consume the results.
      }).start();

    process.waitFor();

    result.stream().forEach(System.out::println);

    System.exit(process.exitValue());
  }

  private static class StreamHandler extends Thread
  {
    private final InputStream input;

    private final Consumer<String> consumer;

    public StreamHandler(InputStream input, Consumer<String> consumer)
    {
      this.input = input;
      this.consumer = consumer;
    }

    @Override
    public void run()
    {
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8")))
      {
        reader.lines().forEach(consumer);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }
  }
}
