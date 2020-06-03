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
package org.eclipse.justj.codegen.model.impl;


import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.justj.codegen.model.Annotation;
import org.eclipse.justj.codegen.model.Copyrightable;
import org.eclipse.justj.codegen.model.Model;
import org.eclipse.justj.codegen.model.ModelFactory;
import org.eclipse.justj.codegen.model.ModelPackage;
import org.eclipse.justj.codegen.model.Phase;
import org.eclipse.justj.codegen.model.Touchable;
import org.eclipse.justj.codegen.model.Touchpoint;
import org.eclipse.justj.codegen.model.Variant;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl implements ModelPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jvmEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variantEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass copyrightableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass touchpointEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass touchableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass detailEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum phaseEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.justj.codegen.model.ModelPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ModelPackageImpl()
  {
    super(eNS_URI, ModelFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   *
   * <p>This method is used to initialize {@link ModelPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ModelPackage init()
  {
    if (isInited)
    {
      return (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);
    }

    // Obtain or create and register package
    Object registeredModelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    ModelPackageImpl theModelPackage = registeredModelPackage instanceof ModelPackageImpl ? (ModelPackageImpl)registeredModelPackage : new ModelPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theModelPackage.createPackageContents();

    // Initialize created meta-data
    theModelPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theModelPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, theModelPackage);
    return theModelPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getModel()
  {
    return modelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getModel_Name()
  {
    return (EAttribute)modelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getModel_Label()
  {
    return (EAttribute)modelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getModel_JVMs()
  {
    return (EReference)modelEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getModel_Provider()
  {
    return (EAttribute)modelEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getModel_ProviderImageName()
  {
    return (EAttribute)modelEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getModel_ProviderImageData()
  {
    return (EAttribute)modelEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getModel_AboutURL()
  {
    return (EAttribute)modelEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getModel_Target()
  {
    return (EAttribute)modelEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getModel_Source()
  {
    return (EAttribute)modelEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getModel_LocalCache()
  {
    return (EAttribute)modelEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getJVM()
  {
    return jvmEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getJVM_Name()
  {
    return (EAttribute)jvmEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getJVM_Version()
  {
    return (EAttribute)jvmEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getJVM_Label()
  {
    return (EAttribute)jvmEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getJVM_Description()
  {
    return (EAttribute)jvmEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getJVM_Variants()
  {
    return (EReference)jvmEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getJVM_Model()
  {
    return (EReference)jvmEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getJVM_AboutTextExtra()
  {
    return (EAttribute)jvmEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getVariant()
  {
    return variantEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getVariant_Os()
  {
    return (EAttribute)variantEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getVariant_Arch()
  {
    return (EAttribute)variantEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getVariant_JVM()
  {
    return (EReference)variantEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getVariant_Label()
  {
    return (EAttribute)variantEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getVariant_Source()
  {
    return (EAttribute)variantEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getCopyrightable()
  {
    return copyrightableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getCopyrightable_Annotations()
  {
    return (EReference)copyrightableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getCopyrightable_CopyrightHolder()
  {
    return (EAttribute)copyrightableEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getCopyrightable_CopyrightYear()
  {
    return (EAttribute)copyrightableEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getCopyrightable_CopyrightText()
  {
    return (EAttribute)copyrightableEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTouchpoint()
  {
    return touchpointEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getTouchpoint_Phase()
  {
    return (EAttribute)touchpointEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getTouchpoint_Instructions()
  {
    return (EAttribute)touchpointEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTouchable()
  {
    return touchableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTouchable_Touchpoints()
  {
    return (EReference)touchableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAnnotation()
  {
    return annotationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getAnnotation_Source()
  {
    return (EAttribute)annotationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getAnnotation_Details()
  {
    return (EReference)annotationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getDetail()
  {
    return detailEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getDetail_Key()
  {
    return (EAttribute)detailEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getDetail_Value()
  {
    return (EAttribute)detailEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EEnum getPhase()
  {
    return phaseEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ModelFactory getModelFactory()
  {
    return (ModelFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated)
    {
      return;
    }
    isCreated = true;

    // Create classes and their features
    modelEClass = createEClass(MODEL);
    createEAttribute(modelEClass, MODEL__NAME);
    createEAttribute(modelEClass, MODEL__LABEL);
    createEAttribute(modelEClass, MODEL__PROVIDER);
    createEAttribute(modelEClass, MODEL__PROVIDER_IMAGE_NAME);
    createEAttribute(modelEClass, MODEL__PROVIDER_IMAGE_DATA);
    createEAttribute(modelEClass, MODEL__ABOUT_URL);
    createEAttribute(modelEClass, MODEL__TARGET);
    createEAttribute(modelEClass, MODEL__SOURCE);
    createEAttribute(modelEClass, MODEL__LOCAL_CACHE);
    createEReference(modelEClass, MODEL__JV_MS);

    jvmEClass = createEClass(JVM);
    createEAttribute(jvmEClass, JVM__NAME);
    createEAttribute(jvmEClass, JVM__LABEL);
    createEAttribute(jvmEClass, JVM__VERSION);
    createEAttribute(jvmEClass, JVM__DESCRIPTION);
    createEReference(jvmEClass, JVM__MODEL);
    createEAttribute(jvmEClass, JVM__ABOUT_TEXT_EXTRA);
    createEReference(jvmEClass, JVM__VARIANTS);

    variantEClass = createEClass(VARIANT);
    createEAttribute(variantEClass, VARIANT__OS);
    createEAttribute(variantEClass, VARIANT__ARCH);
    createEAttribute(variantEClass, VARIANT__LABEL);
    createEAttribute(variantEClass, VARIANT__SOURCE);
    createEReference(variantEClass, VARIANT__JVM);

    copyrightableEClass = createEClass(COPYRIGHTABLE);
    createEReference(copyrightableEClass, COPYRIGHTABLE__ANNOTATIONS);
    createEAttribute(copyrightableEClass, COPYRIGHTABLE__COPYRIGHT_HOLDER);
    createEAttribute(copyrightableEClass, COPYRIGHTABLE__COPYRIGHT_YEAR);
    createEAttribute(copyrightableEClass, COPYRIGHTABLE__COPYRIGHT_TEXT);

    touchpointEClass = createEClass(TOUCHPOINT);
    createEAttribute(touchpointEClass, TOUCHPOINT__PHASE);
    createEAttribute(touchpointEClass, TOUCHPOINT__INSTRUCTIONS);

    touchableEClass = createEClass(TOUCHABLE);
    createEReference(touchableEClass, TOUCHABLE__TOUCHPOINTS);

    annotationEClass = createEClass(ANNOTATION);
    createEAttribute(annotationEClass, ANNOTATION__SOURCE);
    createEReference(annotationEClass, ANNOTATION__DETAILS);

    detailEClass = createEClass(DETAIL);
    createEAttribute(detailEClass, DETAIL__KEY);
    createEAttribute(detailEClass, DETAIL__VALUE);

    // Create enums
    phaseEEnum = createEEnum(PHASE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized)
    {
      return;
    }
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    modelEClass.getESuperTypes().add(this.getCopyrightable());
    modelEClass.getESuperTypes().add(this.getTouchable());
    jvmEClass.getESuperTypes().add(this.getCopyrightable());
    jvmEClass.getESuperTypes().add(this.getTouchable());
    variantEClass.getESuperTypes().add(this.getCopyrightable());
    variantEClass.getESuperTypes().add(this.getTouchable());

    // Initialize classes, features, and operations; add parameters
    initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
      getModel_Name(),
      ecorePackage.getEString(),
      "name",
      null,
      1,
      1,
      Model.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getModel_Label(),
      ecorePackage.getEString(),
      "label",
      null,
      1,
      1,
      Model.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getModel_Provider(),
      ecorePackage.getEString(),
      "provider",
      null,
      1,
      1,
      Model.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getModel_ProviderImageName(),
      ecorePackage.getEString(),
      "providerImageName",
      null,
      1,
      1,
      Model.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getModel_ProviderImageData(),
      theXMLTypePackage.getBase64Binary(),
      "providerImageData",
      null,
      1,
      1,
      Model.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getModel_AboutURL(),
      ecorePackage.getEString(),
      "aboutURL",
      "https://www.eclipse.org/legal/epl/epl-2.0/about.html",
      0,
      1,
      Model.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getModel_Target(),
      ecorePackage.getEString(),
      "target",
      null,
      1,
      1,
      Model.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getModel_Source(),
      ecorePackage.getEString(),
      "source",
      null,
      0,
      1,
      Model.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getModel_LocalCache(),
      ecorePackage.getEString(),
      "localCache",
      null,
      0,
      1,
      Model.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEReference(
      getModel_JVMs(),
      this.getJVM(),
      this.getJVM_Model(),
      "jVMs",
      null,
      1,
      -1,
      Model.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      IS_COMPOSITE,
      !IS_RESOLVE_PROXIES,
      !IS_UNSETTABLE,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);

    initEClass(jvmEClass, org.eclipse.justj.codegen.model.JVM.class, "JVM", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
      getJVM_Name(),
      ecorePackage.getEString(),
      "name",
      null,
      1,
      1,
      org.eclipse.justj.codegen.model.JVM.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getJVM_Label(),
      ecorePackage.getEString(),
      "label",
      null,
      1,
      1,
      org.eclipse.justj.codegen.model.JVM.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getJVM_Version(),
      ecorePackage.getEString(),
      "version",
      null,
      1,
      1,
      org.eclipse.justj.codegen.model.JVM.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getJVM_Description(),
      ecorePackage.getEString(),
      "description",
      null,
      0,
      1,
      org.eclipse.justj.codegen.model.JVM.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEReference(
      getJVM_Model(),
      this.getModel(),
      this.getModel_JVMs(),
      "model",
      null,
      0,
      1,
      org.eclipse.justj.codegen.model.JVM.class,
      IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_COMPOSITE,
      !IS_RESOLVE_PROXIES,
      !IS_UNSETTABLE,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getJVM_AboutTextExtra(),
      ecorePackage.getEString(),
      "aboutTextExtra",
      null,
      0,
      1,
      org.eclipse.justj.codegen.model.JVM.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEReference(
      getJVM_Variants(),
      this.getVariant(),
      this.getVariant_JVM(),
      "variants",
      null,
      1,
      -1,
      org.eclipse.justj.codegen.model.JVM.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      IS_COMPOSITE,
      !IS_RESOLVE_PROXIES,
      !IS_UNSETTABLE,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);

    initEClass(variantEClass, Variant.class, "Variant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
      getVariant_Os(),
      ecorePackage.getEString(),
      "os",
      null,
      1,
      1,
      Variant.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getVariant_Arch(),
      ecorePackage.getEString(),
      "arch",
      null,
      1,
      1,
      Variant.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getVariant_Label(),
      ecorePackage.getEString(),
      "label",
      null,
      1,
      1,
      Variant.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getVariant_Source(),
      theXMLTypePackage.getString(),
      "source",
      null,
      0,
      1,
      Variant.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEReference(
      getVariant_JVM(),
      this.getJVM(),
      this.getJVM_Variants(),
      "jVM",
      null,
      0,
      1,
      Variant.class,
      IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_COMPOSITE,
      !IS_RESOLVE_PROXIES,
      !IS_UNSETTABLE,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);

    initEClass(copyrightableEClass, Copyrightable.class, "Copyrightable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(
      getCopyrightable_Annotations(),
      this.getAnnotation(),
      null,
      "annotations",
      null,
      0,
      -1,
      Copyrightable.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      IS_COMPOSITE,
      !IS_RESOLVE_PROXIES,
      !IS_UNSETTABLE,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getCopyrightable_CopyrightHolder(),
      ecorePackage.getEString(),
      "copyrightHolder",
      null,
      0,
      1,
      Copyrightable.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getCopyrightable_CopyrightYear(),
      ecorePackage.getEString(),
      "copyrightYear",
      null,
      0,
      1,
      Copyrightable.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getCopyrightable_CopyrightText(),
      ecorePackage.getEString(),
      "copyrightText",
      null,
      0,
      1,
      Copyrightable.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);

    initEClass(touchpointEClass, Touchpoint.class, "Touchpoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
      getTouchpoint_Phase(),
      this.getPhase(),
      "phase",
      null,
      0,
      1,
      Touchpoint.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getTouchpoint_Instructions(),
      ecorePackage.getEString(),
      "instructions",
      null,
      1,
      -1,
      Touchpoint.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      !IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);

    initEClass(touchableEClass, Touchable.class, "Touchable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(
      getTouchable_Touchpoints(),
      this.getTouchpoint(),
      null,
      "touchpoints",
      null,
      0,
      -1,
      Touchable.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      IS_COMPOSITE,
      !IS_RESOLVE_PROXIES,
      !IS_UNSETTABLE,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);

    initEClass(annotationEClass, Annotation.class, "Annotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
      getAnnotation_Source(),
      ecorePackage.getEString(),
      "source",
      null,
      0,
      1,
      Annotation.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEReference(
      getAnnotation_Details(),
      this.getDetail(),
      null,
      "details",
      null,
      0,
      -1,
      Annotation.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      IS_COMPOSITE,
      !IS_RESOLVE_PROXIES,
      !IS_UNSETTABLE,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);

    initEClass(detailEClass, Map.Entry.class, "Detail", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(
      getDetail_Key(),
      ecorePackage.getEString(),
      "key",
      null,
      0,
      1,
      Map.Entry.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);
    initEAttribute(
      getDetail_Value(),
      ecorePackage.getEString(),
      "value",
      null,
      0,
      1,
      Map.Entry.class,
      !IS_TRANSIENT,
      !IS_VOLATILE,
      IS_CHANGEABLE,
      !IS_UNSETTABLE,
      !IS_ID,
      IS_UNIQUE,
      !IS_DERIVED,
      IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(phaseEEnum, Phase.class, "Phase");
    addEEnumLiteral(phaseEEnum, Phase.INSTALL);
    addEEnumLiteral(phaseEEnum, Phase.UNINSTALL);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
    createExtendedMetaDataAnnotations();
  }

  /**
   * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createExtendedMetaDataAnnotations()
  {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
    addAnnotation(getModel_ProviderImageData(), source, new String []{ "kind", "element" });
    addAnnotation(getModel_JVMs(), source, new String []{ "name", "jvm", "kind", "element" });
    addAnnotation(getJVM_Description(), source, new String []{ "kind", "element" });
    addAnnotation(getJVM_AboutTextExtra(), source, new String []{ "kind", "element" });
    addAnnotation(getJVM_Variants(), source, new String []{ "name", "variant", "kind", "element" });
    addAnnotation(getVariant_JVM(), source, new String []{ "name", "jvm", "kind", "element" });
    addAnnotation(getCopyrightable_Annotations(), source, new String []{ "name", "annotation", "kind", "element" });
    addAnnotation(getCopyrightable_CopyrightText(), source, new String []{ "kind", "element" });
    addAnnotation(getTouchpoint_Instructions(), source, new String []{ "kind", "element", "name", "instruction" });
    addAnnotation(getAnnotation_Details(), source, new String []{ "name", "detail", "kind", "element" });
    addAnnotation(getDetail_Value(), source, new String []{ "kind", "element" });
  }

} //ModelPackageImpl
