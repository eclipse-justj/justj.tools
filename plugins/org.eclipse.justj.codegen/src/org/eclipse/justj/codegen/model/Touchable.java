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
package org.eclipse.justj.codegen.model;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Touchable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An object that contains p2 touchpoints.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.justj.codegen.model.Touchable#getTouchpoints <em>Touchpoints</em>}</li>
 * </ul>
 *
 * @see org.eclipse.justj.codegen.model.ModelPackage#getTouchable()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Touchable extends EObject
{
  /**
   * Returns the value of the '<em><b>Touchpoints</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.justj.codegen.model.Touchpoint}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The p2 touchpoint instructions used in each generated <code>p2.inf</code>.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Touchpoints</em>' containment reference list.
   * @see org.eclipse.justj.codegen.model.ModelPackage#getTouchable_Touchpoints()
   * @model containment="true"
   * @generated
   */
  EList<Touchpoint> getTouchpoints();

} // Touchable
