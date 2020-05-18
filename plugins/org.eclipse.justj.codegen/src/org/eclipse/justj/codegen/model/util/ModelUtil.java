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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.justj.codegen.model.Annotation;
import org.eclipse.justj.codegen.model.Copyrightable;
import org.eclipse.justj.codegen.model.JVM;
import org.eclipse.justj.codegen.model.Model;
import org.eclipse.justj.codegen.model.ModelFactory;
import org.eclipse.justj.codegen.model.ModelPackage;
import org.eclipse.justj.codegen.model.Variant;


public final class ModelUtil
{
  public static final String MODEL_PROPERTIES_ANNOTATION_URI = "https://www.eclipse.org/justj/Properties";

  private ModelUtil()
  {
    throw new RuntimeException("Not instantiable");
  }

  public static Annotation getAnnotation(Copyrightable copyrightable, String sourceURI)
  {
    for (Annotation annotation : copyrightable.getAnnotations())
    {
      if (Objects.equals(sourceURI, annotation.getSource()))
      {
        return annotation;

      }
    }

    return null;
  }

  public static String getAnnotation(Copyrightable copyrightable, String sourceURI, String key)
  {
    Annotation annotation = getAnnotation(copyrightable, sourceURI);
    return annotation == null ? null : (String)annotation.getDetails().get(key);
  }

  public static void setAnnotation(Copyrightable copyrightable, String sourceURI, String key, String value)
  {
    Annotation annotation = getAnnotation(copyrightable, sourceURI);
    if (value == null)
    {
      if (annotation != null)
      {
        annotation.getDetails().removeKey(key);
      }
    }
    else
    {
      if (annotation == null)
      {
        annotation = ModelFactory.eINSTANCE.createAnnotation();
        annotation.setSource(sourceURI);
        copyrightable.getAnnotations().add(annotation);
      }
      annotation.getDetails().put(key, value);
    }
  }

  /**
   * This is used to build the model from the p2 content metadata without depending on p2 APIs.
   */
  public static class P2Processor
  {
    private final ResourceSet resourceSet;

    public P2Processor()
    {
      resourceSet = new ResourceSetImpl();
    }

    public Map<String, String> process(String id, String version, Map<String, String> properties)
    {
      String model = properties.get("org.eclipse.justj.model");
      if (model == null)
      {
        String description = properties.get("org.eclipse.equinox.p2.description");
        if (description != null)
        {
          int index = description.indexOf("<?xml");
          if (index != -1)
          {
            model = description.substring(index);
          }
        }
      }

      if (model != null)
      {
        URI uri = URI.createURI("model://" + id + "/" + version + "/model.jregen");
        Resource resource = resourceSet.createResource(uri, ModelPackage.eCONTENT_TYPE);
        try
        {
          resource.load(new URIConverter.ReadableInputStream(model), null);
          Copyrightable copyrightable = (Copyrightable)resource.getContents().get(0);
          Annotation annotation = getAnnotation(copyrightable, MODEL_PROPERTIES_ANNOTATION_URI);
          if (annotation != null)
          {
            return new TreeMap<>(annotation.getDetails().map());
          }
        }
        catch (IOException exception)
        {
          throw new RuntimeException(exception);
        }
      }

      return Collections.emptyMap();
    }

    public Resource build()
    {
      Model model = null;
      List<JVM> jvms = new ArrayList<>();
      for (Resource resource : resourceSet.getResources())
      {
        URI uri = resource.getURI();
        String authority = uri.authority();
        EObject eObject = resource.getContents().get(0);
        if (eObject instanceof Variant)
        {
          int index = authority.lastIndexOf('.');
          index = authority.lastIndexOf('.', index - 1);
          URI containerURI = URI.createURI("model://" + authority.substring(0, index)).appendSegments(uri.segments());
          Resource containerResource = resourceSet.getResource(containerURI, false);
          JVM jvm = (JVM)containerResource.getContents().get(0);
          EList<Variant> variants = jvm.getVariants();
          variants.add((Variant)eObject);
          Reconciler.sortVariants(variants);
          jvms.add(jvm);
        }
        else if (eObject instanceof Model)
        {
          model = (Model)eObject;
        }
      }

      if (model == null)
      {
        return null;
      }
      else
      {
        EList<JVM> modelJVMs = model.getJVMs();
        modelJVMs.addAll(jvms);
        Reconciler.sortJVMs(modelJVMs);
        Resource resource = model.eResource();
        return resource;
      }
    }
  }
}
