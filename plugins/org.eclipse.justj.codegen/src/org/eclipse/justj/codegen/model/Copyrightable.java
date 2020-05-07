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


import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Copyrightable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.justj.codegen.model.Copyrightable#getCopyrightHolder <em>Copyright Holder</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Copyrightable#getCopyrightYear <em>Copyright Year</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Copyrightable#getCopyrightText <em>Copyright Text</em>}</li>
 * </ul>
 *
 * @see org.eclipse.justj.codegen.model.ModelPackage#getCopyrightable()
 * @model abstract="true"
 * @generated
 */
public interface Copyrightable extends EObject
{
  /**
   * Returns the value of the '<em><b>Copyright Holder</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Copyright Holder</em>' attribute.
   * @see #setCopyrightHolder(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getCopyrightable_CopyrightHolder()
   * @model
   * @generated
   */
  String getCopyrightHolder();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Copyrightable#getCopyrightHolder <em>Copyright Holder</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Copyright Holder</em>' attribute.
   * @see #getCopyrightHolder()
   * @generated
   */
  void setCopyrightHolder(String value);

  /**
   * Returns the value of the '<em><b>Copyright Year</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Copyright Year</em>' attribute.
   * @see #setCopyrightYear(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getCopyrightable_CopyrightYear()
   * @model
   * @generated
   */
  String getCopyrightYear();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Copyrightable#getCopyrightYear <em>Copyright Year</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Copyright Year</em>' attribute.
   * @see #getCopyrightYear()
   * @generated
   */
  void setCopyrightYear(String value);

  /**
   * Returns the value of the '<em><b>Copyright Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Copyright Text</em>' attribute.
   * @see #setCopyrightText(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getCopyrightable_CopyrightText()
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel propertyMultiLine='true'"
   *        extendedMetaData="kind='element'"
   * @generated
   */
  String getCopyrightText();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Copyrightable#getCopyrightText <em>Copyright Text</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Copyright Text</em>' attribute.
   * @see #getCopyrightText()
   * @generated
   */
  void setCopyrightText(String value);

} // Copyrightable
