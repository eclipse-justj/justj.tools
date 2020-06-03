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
 * A representation of the model object '<em><b>Touchpoint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A description of a list of p2 touchpoint instructions used in each generated <code>p2.inf</code>.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.justj.codegen.model.Touchpoint#getPhase <em>Phase</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Touchpoint#getInstructions <em>Instructions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.justj.codegen.model.ModelPackage#getTouchpoint()
 * @model
 * @generated
 */
public interface Touchpoint extends EObject
{
  /**
   * Returns the value of the '<em><b>Phase</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.justj.codegen.model.Phase}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The p2 phase of these touchpoint instruction used in each generated <code>p2.inf</code>.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Phase</em>' attribute.
   * @see org.eclipse.justj.codegen.model.Phase
   * @see #setPhase(Phase)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getTouchpoint_Phase()
   * @model
   * @generated
   */
  Phase getPhase();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Touchpoint#getPhase <em>Phase</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Phase</em>' attribute.
   * @see org.eclipse.justj.codegen.model.Phase
   * @see #getPhase()
   * @generated
   */
  void setPhase(Phase value);

  /**
   * Returns the value of the '<em><b>Instructions</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The literal text of each touchpoint instruction, e.g., <em>org.eclipse.equinox.p2.touchpoint.eclipse.setJvm(jvm:${artifact.location}/jre/bin)</em>, used in each generated <code>p2.inf</code>.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Instructions</em>' attribute list.
   * @see org.eclipse.justj.codegen.model.ModelPackage#getTouchpoint_Instructions()
   * @model unique="false" required="true"
   *        extendedMetaData="kind='element' name='instruction'"
   * @generated
   */
  EList<String> getInstructions();

} // Touchpoint
