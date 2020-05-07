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


import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;


/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.justj.codegen.model.util.ModelResourceImpl
 * @generated
 */
public class ModelResourceFactoryImpl extends ResourceFactoryImpl
{
  /**
   * Creates an instance of the resource factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelResourceFactoryImpl()
  {
    super();
  }

  /**
   * Creates an instance of the resource.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public Resource createResource(URI uri)
  {
    XMLResource result = new ModelResourceImpl(uri);

    Map<Object, Object> defaultSaveOptions = result.getDefaultSaveOptions();
    defaultSaveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
    defaultSaveOptions.put(XMLResource.OPTION_LINE_WIDTH, 10);
    defaultSaveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    defaultSaveOptions.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);

    Map<Object, Object> defaultLoadOptions = result.getDefaultLoadOptions();
    defaultLoadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
    defaultLoadOptions.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    defaultLoadOptions.put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);

    return result;
  }

} //ModelResourceFactoryImpl
