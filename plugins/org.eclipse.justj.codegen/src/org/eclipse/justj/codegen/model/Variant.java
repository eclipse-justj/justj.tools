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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An operating-system-specific, architecture-specific variant of a Java Virtual Machine.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.justj.codegen.model.Variant#getOs <em>Os</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Variant#getArch <em>Arch</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Variant#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Variant#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Variant#getJVM <em>JVM</em>}</li>
 * </ul>
 *
 * @see org.eclipse.justj.codegen.model.ModelPackage#getVariant()
 * @model
 * @generated
 */
public interface Variant extends Copyrightable, Touchable
{
  /**
   * Returns the value of the '<em><b>Os</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * An operating system key, e.g., win32, used in combination with the {@link #getArch() architecture} and the {@link JVM#getName() JVM name} to induce the name of each generated fragment as well as its filters.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Os</em>' attribute.
   * @see #setOs(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getVariant_Os()
   * @model required="true"
   * @generated
   */
  String getOs();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Variant#getOs <em>Os</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Os</em>' attribute.
   * @see #getOs()
   * @generated
   */
  void setOs(String value);

  /**
   * Returns the value of the '<em><b>Arch</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * An architecture key, e.g., x86_64, used in combination with the {@link #getOs() operating system key} and the {@link JVM#getName() JVM name} to induce the name of each generated fragment as well as its filters.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Arch</em>' attribute.
   * @see #setArch(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getVariant_Arch()
   * @model required="true"
   * @generated
   */
  String getArch();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Variant#getArch <em>Arch</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arch</em>' attribute.
   * @see #getArch()
   * @generated
   */
  void setArch(String value);

  /**
   * Returns the value of the '<em><b>JVM</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.justj.codegen.model.JVM#getVariants <em>Variants</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The containing Java Virtual Machine whose attributes are used in combination with this variant's attributes.
   * <!-- end-model-doc -->
   * @return the value of the '<em>JVM</em>' container reference.
   * @see #setJVM(JVM)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getVariant_JVM()
   * @see org.eclipse.justj.codegen.model.JVM#getVariants
   * @model opposite="variants" resolveProxies="false"
   *        extendedMetaData="name='jvm' kind='element'"
   * @generated
   */
  JVM getJVM();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Variant#getJVM <em>JVM</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>JVM</em>' container reference.
   * @see #getJVM()
   * @generated
   */
  void setJVM(JVM value);

  /**
   * Returns the value of the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The label suffix e.g., <em>Windows 64 bit</em>, used in combination with the {@link JVM#getLabel() JVM label} and the {@link Model#getLabel() model label} to induce the user-facing name of each generated fragment.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getVariant_Label()
   * @model required="true"
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Variant#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

  /**
   * Returns the value of the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The URL of a <code>*.tar.gz</code> containing the JRE that will be embedded in the generated fragment.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Source</em>' attribute.
   * @see #setSource(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getVariant_Source()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   * @generated
   */
  String getSource();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Variant#getSource <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' attribute.
   * @see #getSource()
   * @generated
   */
  void setSource(String value);

} // Variant
