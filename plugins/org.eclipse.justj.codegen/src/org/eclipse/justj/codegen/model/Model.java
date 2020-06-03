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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The root model description of JustJ's generator infrastructure.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.justj.codegen.model.Model#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Model#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Model#getProvider <em>Provider</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Model#getProviderImageName <em>Provider Image Name</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Model#getProviderImageData <em>Provider Image Data</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Model#getAboutURL <em>About URL</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Model#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Model#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Model#getLocalCache <em>Local Cache</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.Model#getJVMs <em>JV Ms</em>}</li>
 * </ul>
 *
 * @see org.eclipse.justj.codegen.model.ModelPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends Copyrightable, Touchable
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The symbolic name prefix, e.g., org.example, used to induce the symbolic names of each generated feature, plugin, and fragment.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getModel_Name()
   * @model required="true"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Model#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The label prefix, e.g., My Project, used to induce the user-facing names of each generated feature, plugin, and fragment.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getModel_Label()
   * @model required="true"
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Model#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

  /**
   * Returns the value of the '<em><b>JV Ms</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.justj.codegen.model.JVM}.
   * It is bidirectional and its opposite is '{@link org.eclipse.justj.codegen.model.JVM#getModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The list of this model's Java Virtual Machines.
   * <!-- end-model-doc -->
   * @return the value of the '<em>JV Ms</em>' containment reference list.
   * @see org.eclipse.justj.codegen.model.ModelPackage#getModel_JVMs()
   * @see org.eclipse.justj.codegen.model.JVM#getModel
   * @model opposite="model" containment="true" required="true"
   *        extendedMetaData="name='jvm' kind='element'"
   * @generated
   */
  EList<JVM> getJVMs();

  /**
   * Returns the value of the '<em><b>Provider</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The provider label e.g., Example.org My Project, used to induce the the user-facing provider name of each generated feature, plugin, and fragment.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Provider</em>' attribute.
   * @see #setProvider(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getModel_Provider()
   * @model required="true"
   * @generated
   */
  String getProvider();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Model#getProvider <em>Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Provider</em>' attribute.
   * @see #getProvider()
   * @generated
   */
  void setProvider(String value);

  /**
   * Returns the value of the '<em><b>Provider Image Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The branding image name, e.g., MyProject.png, used in combination with the {@link #getProviderImageData() provider image data} to generate the branding image of each plugin.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Provider Image Name</em>' attribute.
   * @see #setProviderImageName(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getModel_ProviderImageName()
   * @model required="true"
   * @generated
   */
  String getProviderImageName();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Model#getProviderImageName <em>Provider Image Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Provider Image Name</em>' attribute.
   * @see #getProviderImageName()
   * @generated
   */
  void setProviderImageName(String value);

  /**
   * Returns the value of the '<em><b>Provider Image Data</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The base64 encoded representation of the branding image used in combination with the {@link #getProviderImageName() provider image name} to generate the branding image of each plugin.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Provider Image Data</em>' attribute.
   * @see #setProviderImageData(byte[])
   * @see org.eclipse.justj.codegen.model.ModelPackage#getModel_ProviderImageData()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Base64Binary" required="true"
   *        extendedMetaData="kind='element'"
   * @generated
   */
  byte[] getProviderImageData();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Model#getProviderImageData <em>Provider Image Data</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Provider Image Data</em>' attribute.
   * @see #getProviderImageData()
   * @generated
   */
  void setProviderImageData(byte[] value);

  /**
   * Returns the value of the '<em><b>About URL</b></em>' attribute.
   * The default value is <code>"https://www.eclipse.org/legal/epl/epl-2.0/about.html"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The URL to a resource used to populate the contents of the generated about.html of each plugin and fragment.
   * <!-- end-model-doc -->
   * @return the value of the '<em>About URL</em>' attribute.
   * @see #setAboutURL(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getModel_AboutURL()
   * @model default="https://www.eclipse.org/legal/epl/epl-2.0/about.html"
   * @generated
   */
  String getAboutURL();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Model#getAboutURL <em>About URL</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>About URL</em>' attribute.
   * @see #getAboutURL()
   * @generated
   */
  void setAboutURL(String value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The absolute or relative URL of the file system location where the generator will generate results.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Target</em>' attribute.
   * @see #setTarget(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getModel_Target()
   * @model required="true"
   * @generated
   */
  String getTarget();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Model#getTarget <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' attribute.
   * @see #getTarget()
   * @generated
   */
  void setTarget(String value);

  /**
   * Returns the value of the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The URL of a justj.manifest used to reconcile the model against a set of externally-provided JREs.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Source</em>' attribute.
   * @see #setSource(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getModel_Source()
   * @model
   * @generated
   */
  String getSource();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Model#getSource <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' attribute.
   * @see #getSource()
   * @generated
   */
  void setSource(String value);

  /**
   * Returns the value of the '<em><b>Local Cache</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * The absolute or relative URL of the file system location where the reconciler and generator will cache externally-provided JREs.
   * <!-- end-model-doc -->
   * @return the value of the '<em>Local Cache</em>' attribute.
   * @see #setLocalCache(String)
   * @see org.eclipse.justj.codegen.model.ModelPackage#getModel_LocalCache()
   * @model
   * @generated
   */
  String getLocalCache();

  /**
   * Sets the value of the '{@link org.eclipse.justj.codegen.model.Model#getLocalCache <em>Local Cache</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Local Cache</em>' attribute.
   * @see #getLocalCache()
   * @generated
   */
  void setLocalCache(String value);

} // Model
