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


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;


/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.justj.codegen.model.ModelFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel copyrightText='Copyright (c) 2020 Eclipse contributors and others.\n\nThis program and the accompanying materials\nare made available under the terms of the Eclipse Public License 2.0\nwhich accompanies this distribution, and is available at\nhttps://www.eclipse.org/legal/epl-2.0/\n\nSPDX-License-Identifier: EPL-2.0'"
 * @generated
 */
public interface ModelPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "model";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/just/2020/Model";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "model";

  /**
   * The package content type ID.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eCONTENT_TYPE = "org.eclipse.justj.codegen";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ModelPackage eINSTANCE = org.eclipse.justj.codegen.model.impl.ModelPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.justj.codegen.model.impl.CopyrightableImpl <em>Copyrightable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.justj.codegen.model.impl.CopyrightableImpl
   * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getCopyrightable()
   * @generated
   */
  int COPYRIGHTABLE = 3;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COPYRIGHTABLE__ANNOTATIONS = 0;

  /**
   * The feature id for the '<em><b>Copyright Holder</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COPYRIGHTABLE__COPYRIGHT_HOLDER = 1;

  /**
   * The feature id for the '<em><b>Copyright Year</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COPYRIGHTABLE__COPYRIGHT_YEAR = 2;

  /**
   * The feature id for the '<em><b>Copyright Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COPYRIGHTABLE__COPYRIGHT_TEXT = 3;

  /**
   * The number of structural features of the '<em>Copyrightable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COPYRIGHTABLE_FEATURE_COUNT = 4;

  /**
   * The number of operations of the '<em>Copyrightable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COPYRIGHTABLE_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.justj.codegen.model.impl.ModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.justj.codegen.model.impl.ModelImpl
   * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getModel()
   * @generated
   */
  int MODEL = 0;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__ANNOTATIONS = COPYRIGHTABLE__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Copyright Holder</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__COPYRIGHT_HOLDER = COPYRIGHTABLE__COPYRIGHT_HOLDER;

  /**
   * The feature id for the '<em><b>Copyright Year</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__COPYRIGHT_YEAR = COPYRIGHTABLE__COPYRIGHT_YEAR;

  /**
   * The feature id for the '<em><b>Copyright Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__COPYRIGHT_TEXT = COPYRIGHTABLE__COPYRIGHT_TEXT;

  /**
   * The feature id for the '<em><b>Touchpoints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__TOUCHPOINTS = COPYRIGHTABLE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__NAME = COPYRIGHTABLE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__LABEL = COPYRIGHTABLE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Provider</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__PROVIDER = COPYRIGHTABLE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Provider Image Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__PROVIDER_IMAGE_NAME = COPYRIGHTABLE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Provider Image Data</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__PROVIDER_IMAGE_DATA = COPYRIGHTABLE_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>About URL</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__ABOUT_URL = COPYRIGHTABLE_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Target</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__TARGET = COPYRIGHTABLE_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__SOURCE = COPYRIGHTABLE_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Local Cache</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__LOCAL_CACHE = COPYRIGHTABLE_FEATURE_COUNT + 9;

  /**
   * The feature id for the '<em><b>JV Ms</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__JV_MS = COPYRIGHTABLE_FEATURE_COUNT + 10;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_FEATURE_COUNT = COPYRIGHTABLE_FEATURE_COUNT + 11;

  /**
   * The number of operations of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OPERATION_COUNT = COPYRIGHTABLE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.justj.codegen.model.impl.JVMImpl <em>JVM</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.justj.codegen.model.impl.JVMImpl
   * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getJVM()
   * @generated
   */
  int JVM = 1;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__ANNOTATIONS = COPYRIGHTABLE__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Copyright Holder</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__COPYRIGHT_HOLDER = COPYRIGHTABLE__COPYRIGHT_HOLDER;

  /**
   * The feature id for the '<em><b>Copyright Year</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__COPYRIGHT_YEAR = COPYRIGHTABLE__COPYRIGHT_YEAR;

  /**
   * The feature id for the '<em><b>Copyright Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__COPYRIGHT_TEXT = COPYRIGHTABLE__COPYRIGHT_TEXT;

  /**
   * The feature id for the '<em><b>Touchpoints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__TOUCHPOINTS = COPYRIGHTABLE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__NAME = COPYRIGHTABLE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__VERSION = COPYRIGHTABLE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__LABEL = COPYRIGHTABLE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__DESCRIPTION = COPYRIGHTABLE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Model</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__MODEL = COPYRIGHTABLE_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>About Text Extra</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__ABOUT_TEXT_EXTRA = COPYRIGHTABLE_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Variants</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM__VARIANTS = COPYRIGHTABLE_FEATURE_COUNT + 7;

  /**
   * The number of structural features of the '<em>JVM</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM_FEATURE_COUNT = COPYRIGHTABLE_FEATURE_COUNT + 8;

  /**
   * The number of operations of the '<em>JVM</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JVM_OPERATION_COUNT = COPYRIGHTABLE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.justj.codegen.model.impl.VariantImpl <em>Variant</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.justj.codegen.model.impl.VariantImpl
   * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getVariant()
   * @generated
   */
  int VARIANT = 2;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT__ANNOTATIONS = COPYRIGHTABLE__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Copyright Holder</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT__COPYRIGHT_HOLDER = COPYRIGHTABLE__COPYRIGHT_HOLDER;

  /**
   * The feature id for the '<em><b>Copyright Year</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT__COPYRIGHT_YEAR = COPYRIGHTABLE__COPYRIGHT_YEAR;

  /**
   * The feature id for the '<em><b>Copyright Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT__COPYRIGHT_TEXT = COPYRIGHTABLE__COPYRIGHT_TEXT;

  /**
   * The feature id for the '<em><b>Touchpoints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT__TOUCHPOINTS = COPYRIGHTABLE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Os</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT__OS = COPYRIGHTABLE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Arch</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT__ARCH = COPYRIGHTABLE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT__LABEL = COPYRIGHTABLE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT__SOURCE = COPYRIGHTABLE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>JVM</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT__JVM = COPYRIGHTABLE_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Variant</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_FEATURE_COUNT = COPYRIGHTABLE_FEATURE_COUNT + 6;

  /**
   * The number of operations of the '<em>Variant</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIANT_OPERATION_COUNT = COPYRIGHTABLE_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.justj.codegen.model.impl.TouchpointImpl <em>Touchpoint</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.justj.codegen.model.impl.TouchpointImpl
   * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getTouchpoint()
   * @generated
   */
  int TOUCHPOINT = 4;

  /**
   * The feature id for the '<em><b>Phase</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOUCHPOINT__PHASE = 0;

  /**
   * The feature id for the '<em><b>Instructions</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOUCHPOINT__INSTRUCTIONS = 1;

  /**
   * The number of structural features of the '<em>Touchpoint</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOUCHPOINT_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Touchpoint</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOUCHPOINT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.justj.codegen.model.Touchable <em>Touchable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.justj.codegen.model.Touchable
   * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getTouchable()
   * @generated
   */
  int TOUCHABLE = 5;

  /**
   * The feature id for the '<em><b>Touchpoints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOUCHABLE__TOUCHPOINTS = 0;

  /**
   * The number of structural features of the '<em>Touchable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOUCHABLE_FEATURE_COUNT = 1;

  /**
   * The number of operations of the '<em>Touchable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOUCHABLE_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.justj.codegen.model.impl.AnnotationImpl <em>Annotation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.justj.codegen.model.impl.AnnotationImpl
   * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getAnnotation()
   * @generated
   */
  int ANNOTATION = 6;

  /**
   * The feature id for the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__SOURCE = 0;

  /**
   * The feature id for the '<em><b>Details</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__DETAILS = 1;

  /**
   * The number of structural features of the '<em>Annotation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Annotation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.justj.codegen.model.impl.DetailImpl <em>Detail</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.justj.codegen.model.impl.DetailImpl
   * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getDetail()
   * @generated
   */
  int DETAIL = 7;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DETAIL__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DETAIL__VALUE = 1;

  /**
   * The number of structural features of the '<em>Detail</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DETAIL_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Detail</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DETAIL_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.justj.codegen.model.Phase <em>Phase</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.justj.codegen.model.Phase
   * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getPhase()
   * @generated
   */
  int PHASE = 8;

  /**
   * Returns the meta object for class '{@link org.eclipse.justj.codegen.model.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see org.eclipse.justj.codegen.model.Model
   * @generated
   */
  EClass getModel();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Model#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.justj.codegen.model.Model#getName()
   * @see #getModel()
   * @generated
   */
  EAttribute getModel_Name();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Model#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.justj.codegen.model.Model#getLabel()
   * @see #getModel()
   * @generated
   */
  EAttribute getModel_Label();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.justj.codegen.model.Model#getJVMs <em>JV Ms</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>JV Ms</em>'.
   * @see org.eclipse.justj.codegen.model.Model#getJVMs()
   * @see #getModel()
   * @generated
   */
  EReference getModel_JVMs();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Model#getProvider <em>Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Provider</em>'.
   * @see org.eclipse.justj.codegen.model.Model#getProvider()
   * @see #getModel()
   * @generated
   */
  EAttribute getModel_Provider();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Model#getProviderImageName <em>Provider Image Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Provider Image Name</em>'.
   * @see org.eclipse.justj.codegen.model.Model#getProviderImageName()
   * @see #getModel()
   * @generated
   */
  EAttribute getModel_ProviderImageName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Model#getProviderImageData <em>Provider Image Data</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Provider Image Data</em>'.
   * @see org.eclipse.justj.codegen.model.Model#getProviderImageData()
   * @see #getModel()
   * @generated
   */
  EAttribute getModel_ProviderImageData();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Model#getAboutURL <em>About URL</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>About URL</em>'.
   * @see org.eclipse.justj.codegen.model.Model#getAboutURL()
   * @see #getModel()
   * @generated
   */
  EAttribute getModel_AboutURL();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Model#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target</em>'.
   * @see org.eclipse.justj.codegen.model.Model#getTarget()
   * @see #getModel()
   * @generated
   */
  EAttribute getModel_Target();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Model#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source</em>'.
   * @see org.eclipse.justj.codegen.model.Model#getSource()
   * @see #getModel()
   * @generated
   */
  EAttribute getModel_Source();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Model#getLocalCache <em>Local Cache</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Local Cache</em>'.
   * @see org.eclipse.justj.codegen.model.Model#getLocalCache()
   * @see #getModel()
   * @generated
   */
  EAttribute getModel_LocalCache();

  /**
   * Returns the meta object for class '{@link org.eclipse.justj.codegen.model.JVM <em>JVM</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JVM</em>'.
   * @see org.eclipse.justj.codegen.model.JVM
   * @generated
   */
  EClass getJVM();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.JVM#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.justj.codegen.model.JVM#getName()
   * @see #getJVM()
   * @generated
   */
  EAttribute getJVM_Name();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.JVM#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Version</em>'.
   * @see org.eclipse.justj.codegen.model.JVM#getVersion()
   * @see #getJVM()
   * @generated
   */
  EAttribute getJVM_Version();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.JVM#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.justj.codegen.model.JVM#getLabel()
   * @see #getJVM()
   * @generated
   */
  EAttribute getJVM_Label();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.JVM#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.eclipse.justj.codegen.model.JVM#getDescription()
   * @see #getJVM()
   * @generated
   */
  EAttribute getJVM_Description();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.justj.codegen.model.JVM#getVariants <em>Variants</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variants</em>'.
   * @see org.eclipse.justj.codegen.model.JVM#getVariants()
   * @see #getJVM()
   * @generated
   */
  EReference getJVM_Variants();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.justj.codegen.model.JVM#getModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Model</em>'.
   * @see org.eclipse.justj.codegen.model.JVM#getModel()
   * @see #getJVM()
   * @generated
   */
  EReference getJVM_Model();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.JVM#getAboutTextExtra <em>About Text Extra</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>About Text Extra</em>'.
   * @see org.eclipse.justj.codegen.model.JVM#getAboutTextExtra()
   * @see #getJVM()
   * @generated
   */
  EAttribute getJVM_AboutTextExtra();

  /**
   * Returns the meta object for class '{@link org.eclipse.justj.codegen.model.Variant <em>Variant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variant</em>'.
   * @see org.eclipse.justj.codegen.model.Variant
   * @generated
   */
  EClass getVariant();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Variant#getOs <em>Os</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Os</em>'.
   * @see org.eclipse.justj.codegen.model.Variant#getOs()
   * @see #getVariant()
   * @generated
   */
  EAttribute getVariant_Os();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Variant#getArch <em>Arch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arch</em>'.
   * @see org.eclipse.justj.codegen.model.Variant#getArch()
   * @see #getVariant()
   * @generated
   */
  EAttribute getVariant_Arch();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.justj.codegen.model.Variant#getJVM <em>JVM</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>JVM</em>'.
   * @see org.eclipse.justj.codegen.model.Variant#getJVM()
   * @see #getVariant()
   * @generated
   */
  EReference getVariant_JVM();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Variant#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.justj.codegen.model.Variant#getLabel()
   * @see #getVariant()
   * @generated
   */
  EAttribute getVariant_Label();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Variant#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source</em>'.
   * @see org.eclipse.justj.codegen.model.Variant#getSource()
   * @see #getVariant()
   * @generated
   */
  EAttribute getVariant_Source();

  /**
   * Returns the meta object for class '{@link org.eclipse.justj.codegen.model.Copyrightable <em>Copyrightable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Copyrightable</em>'.
   * @see org.eclipse.justj.codegen.model.Copyrightable
   * @generated
   */
  EClass getCopyrightable();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.justj.codegen.model.Copyrightable#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.eclipse.justj.codegen.model.Copyrightable#getAnnotations()
   * @see #getCopyrightable()
   * @generated
   */
  EReference getCopyrightable_Annotations();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Copyrightable#getCopyrightHolder <em>Copyright Holder</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Copyright Holder</em>'.
   * @see org.eclipse.justj.codegen.model.Copyrightable#getCopyrightHolder()
   * @see #getCopyrightable()
   * @generated
   */
  EAttribute getCopyrightable_CopyrightHolder();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Copyrightable#getCopyrightYear <em>Copyright Year</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Copyright Year</em>'.
   * @see org.eclipse.justj.codegen.model.Copyrightable#getCopyrightYear()
   * @see #getCopyrightable()
   * @generated
   */
  EAttribute getCopyrightable_CopyrightYear();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Copyrightable#getCopyrightText <em>Copyright Text</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Copyright Text</em>'.
   * @see org.eclipse.justj.codegen.model.Copyrightable#getCopyrightText()
   * @see #getCopyrightable()
   * @generated
   */
  EAttribute getCopyrightable_CopyrightText();

  /**
   * Returns the meta object for class '{@link org.eclipse.justj.codegen.model.Touchpoint <em>Touchpoint</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Touchpoint</em>'.
   * @see org.eclipse.justj.codegen.model.Touchpoint
   * @generated
   */
  EClass getTouchpoint();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Touchpoint#getPhase <em>Phase</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Phase</em>'.
   * @see org.eclipse.justj.codegen.model.Touchpoint#getPhase()
   * @see #getTouchpoint()
   * @generated
   */
  EAttribute getTouchpoint_Phase();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.justj.codegen.model.Touchpoint#getInstructions <em>Instructions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Instructions</em>'.
   * @see org.eclipse.justj.codegen.model.Touchpoint#getInstructions()
   * @see #getTouchpoint()
   * @generated
   */
  EAttribute getTouchpoint_Instructions();

  /**
   * Returns the meta object for class '{@link org.eclipse.justj.codegen.model.Touchable <em>Touchable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Touchable</em>'.
   * @see org.eclipse.justj.codegen.model.Touchable
   * @generated
   */
  EClass getTouchable();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.justj.codegen.model.Touchable#getTouchpoints <em>Touchpoints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Touchpoints</em>'.
   * @see org.eclipse.justj.codegen.model.Touchable#getTouchpoints()
   * @see #getTouchable()
   * @generated
   */
  EReference getTouchable_Touchpoints();

  /**
   * Returns the meta object for class '{@link org.eclipse.justj.codegen.model.Annotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation</em>'.
   * @see org.eclipse.justj.codegen.model.Annotation
   * @generated
   */
  EClass getAnnotation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.justj.codegen.model.Annotation#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source</em>'.
   * @see org.eclipse.justj.codegen.model.Annotation#getSource()
   * @see #getAnnotation()
   * @generated
   */
  EAttribute getAnnotation_Source();

  /**
   * Returns the meta object for the map '{@link org.eclipse.justj.codegen.model.Annotation#getDetails <em>Details</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Details</em>'.
   * @see org.eclipse.justj.codegen.model.Annotation#getDetails()
   * @see #getAnnotation()
   * @generated
   */
  EReference getAnnotation_Details();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>Detail</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Detail</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EString"
   *        valueDataType="org.eclipse.emf.ecore.EString"
   *        valueAnnotation="http://www.eclipse.org/emf/2002/GenModel propertyMultiLine='true'"
   *        valueExtendedMetaData="kind='element'"
   * @generated
   */
  EClass getDetail();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getDetail()
   * @generated
   */
  EAttribute getDetail_Key();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getDetail()
   * @generated
   */
  EAttribute getDetail_Value();

  /**
   * Returns the meta object for enum '{@link org.eclipse.justj.codegen.model.Phase <em>Phase</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Phase</em>'.
   * @see org.eclipse.justj.codegen.model.Phase
   * @generated
   */
  EEnum getPhase();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ModelFactory getModelFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
    	 * The meta object literal for the '{@link org.eclipse.justj.codegen.model.impl.ModelImpl <em>Model</em>}' class.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @see org.eclipse.justj.codegen.model.impl.ModelImpl
    	 * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getModel()
    	 * @generated
    	 */
    EClass MODEL = eINSTANCE.getModel();

    /**
    	 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute MODEL__NAME = eINSTANCE.getModel_Name();

    /**
    	 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute MODEL__LABEL = eINSTANCE.getModel_Label();

    /**
    	 * The meta object literal for the '<em><b>JV Ms</b></em>' containment reference list feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EReference MODEL__JV_MS = eINSTANCE.getModel_JVMs();

    /**
    	 * The meta object literal for the '<em><b>Provider</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute MODEL__PROVIDER = eINSTANCE.getModel_Provider();

    /**
    	 * The meta object literal for the '<em><b>Provider Image Name</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute MODEL__PROVIDER_IMAGE_NAME = eINSTANCE.getModel_ProviderImageName();

    /**
    	 * The meta object literal for the '<em><b>Provider Image Data</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute MODEL__PROVIDER_IMAGE_DATA = eINSTANCE.getModel_ProviderImageData();

    /**
    	 * The meta object literal for the '<em><b>About URL</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute MODEL__ABOUT_URL = eINSTANCE.getModel_AboutURL();

    /**
    	 * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute MODEL__TARGET = eINSTANCE.getModel_Target();

    /**
    	 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute MODEL__SOURCE = eINSTANCE.getModel_Source();

    /**
    	 * The meta object literal for the '<em><b>Local Cache</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute MODEL__LOCAL_CACHE = eINSTANCE.getModel_LocalCache();

    /**
    	 * The meta object literal for the '{@link org.eclipse.justj.codegen.model.impl.JVMImpl <em>JVM</em>}' class.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @see org.eclipse.justj.codegen.model.impl.JVMImpl
    	 * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getJVM()
    	 * @generated
    	 */
    EClass JVM = eINSTANCE.getJVM();

    /**
    	 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute JVM__NAME = eINSTANCE.getJVM_Name();

    /**
    	 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute JVM__VERSION = eINSTANCE.getJVM_Version();

    /**
    	 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute JVM__LABEL = eINSTANCE.getJVM_Label();

    /**
    	 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
    	 * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute JVM__DESCRIPTION = eINSTANCE.getJVM_Description();

    /**
    	 * The meta object literal for the '<em><b>Variants</b></em>' containment reference list feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EReference JVM__VARIANTS = eINSTANCE.getJVM_Variants();

    /**
    	 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EReference JVM__MODEL = eINSTANCE.getJVM_Model();

    /**
    	 * The meta object literal for the '<em><b>About Text Extra</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute JVM__ABOUT_TEXT_EXTRA = eINSTANCE.getJVM_AboutTextExtra();

    /**
    	 * The meta object literal for the '{@link org.eclipse.justj.codegen.model.impl.VariantImpl <em>Variant</em>}' class.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @see org.eclipse.justj.codegen.model.impl.VariantImpl
    	 * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getVariant()
    	 * @generated
    	 */
    EClass VARIANT = eINSTANCE.getVariant();

    /**
    	 * The meta object literal for the '<em><b>Os</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute VARIANT__OS = eINSTANCE.getVariant_Os();

    /**
    	 * The meta object literal for the '<em><b>Arch</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute VARIANT__ARCH = eINSTANCE.getVariant_Arch();

    /**
    	 * The meta object literal for the '<em><b>JVM</b></em>' container reference feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EReference VARIANT__JVM = eINSTANCE.getVariant_JVM();

    /**
    	 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute VARIANT__LABEL = eINSTANCE.getVariant_Label();

    /**
    	 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute VARIANT__SOURCE = eINSTANCE.getVariant_Source();

    /**
    	 * The meta object literal for the '{@link org.eclipse.justj.codegen.model.impl.CopyrightableImpl <em>Copyrightable</em>}' class.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @see org.eclipse.justj.codegen.model.impl.CopyrightableImpl
    	 * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getCopyrightable()
    	 * @generated
    	 */
    EClass COPYRIGHTABLE = eINSTANCE.getCopyrightable();

    /**
    	 * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
    	 * <!-- begin-user-doc -->
    	 * <!-- end-user-doc -->
    	 * @generated
    	 */
    EReference COPYRIGHTABLE__ANNOTATIONS = eINSTANCE.getCopyrightable_Annotations();

    /**
    	 * The meta object literal for the '<em><b>Copyright Holder</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute COPYRIGHTABLE__COPYRIGHT_HOLDER = eINSTANCE.getCopyrightable_CopyrightHolder();

    /**
    	 * The meta object literal for the '<em><b>Copyright Year</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute COPYRIGHTABLE__COPYRIGHT_YEAR = eINSTANCE.getCopyrightable_CopyrightYear();

    /**
    	 * The meta object literal for the '<em><b>Copyright Text</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute COPYRIGHTABLE__COPYRIGHT_TEXT = eINSTANCE.getCopyrightable_CopyrightText();

    /**
    	 * The meta object literal for the '{@link org.eclipse.justj.codegen.model.impl.TouchpointImpl <em>Touchpoint</em>}' class.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @see org.eclipse.justj.codegen.model.impl.TouchpointImpl
    	 * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getTouchpoint()
    	 * @generated
    	 */
    EClass TOUCHPOINT = eINSTANCE.getTouchpoint();

    /**
    	 * The meta object literal for the '<em><b>Phase</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute TOUCHPOINT__PHASE = eINSTANCE.getTouchpoint_Phase();

    /**
    	 * The meta object literal for the '<em><b>Instructions</b></em>' attribute list feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute TOUCHPOINT__INSTRUCTIONS = eINSTANCE.getTouchpoint_Instructions();

    /**
    	 * The meta object literal for the '{@link org.eclipse.justj.codegen.model.Touchable <em>Touchable</em>}' class.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @see org.eclipse.justj.codegen.model.Touchable
    	 * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getTouchable()
    	 * @generated
    	 */
    EClass TOUCHABLE = eINSTANCE.getTouchable();

    /**
    	 * The meta object literal for the '<em><b>Touchpoints</b></em>' containment reference list feature.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @generated
    	 */
    EReference TOUCHABLE__TOUCHPOINTS = eINSTANCE.getTouchable_Touchpoints();

    /**
    	 * The meta object literal for the '{@link org.eclipse.justj.codegen.model.impl.AnnotationImpl <em>Annotation</em>}' class.
    	 * <!-- begin-user-doc -->
    	 * <!-- end-user-doc -->
    	 * @see org.eclipse.justj.codegen.model.impl.AnnotationImpl
    	 * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getAnnotation()
    	 * @generated
    	 */
    EClass ANNOTATION = eINSTANCE.getAnnotation();

    /**
    	 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
    	 * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute ANNOTATION__SOURCE = eINSTANCE.getAnnotation_Source();

    /**
    	 * The meta object literal for the '<em><b>Details</b></em>' map feature.
    	 * <!-- begin-user-doc -->
    	 * <!-- end-user-doc -->
    	 * @generated
    	 */
    EReference ANNOTATION__DETAILS = eINSTANCE.getAnnotation_Details();

    /**
    	 * The meta object literal for the '{@link org.eclipse.justj.codegen.model.impl.DetailImpl <em>Detail</em>}' class.
    	 * <!-- begin-user-doc -->
    	 * <!-- end-user-doc -->
    	 * @see org.eclipse.justj.codegen.model.impl.DetailImpl
    	 * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getDetail()
    	 * @generated
    	 */
    EClass DETAIL = eINSTANCE.getDetail();

    /**
    	 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
    	 * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute DETAIL__KEY = eINSTANCE.getDetail_Key();

    /**
    	 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
    	 * <!-- begin-user-doc -->
    	 * <!-- end-user-doc -->
    	 * @generated
    	 */
    EAttribute DETAIL__VALUE = eINSTANCE.getDetail_Value();

    /**
    	 * The meta object literal for the '{@link org.eclipse.justj.codegen.model.Phase <em>Phase</em>}' enum.
    	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    	 * @see org.eclipse.justj.codegen.model.Phase
    	 * @see org.eclipse.justj.codegen.model.impl.ModelPackageImpl#getPhase()
    	 * @generated
    	 */
    EEnum PHASE = eINSTANCE.getPhase();

  }

} //ModelPackage
