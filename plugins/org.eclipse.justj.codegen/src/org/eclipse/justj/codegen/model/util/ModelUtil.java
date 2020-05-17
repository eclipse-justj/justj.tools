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


import java.util.Objects;

import org.eclipse.justj.codegen.model.Annotation;
import org.eclipse.justj.codegen.model.Copyrightable;
import org.eclipse.justj.codegen.model.ModelFactory;


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
}
