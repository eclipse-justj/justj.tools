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


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.justj.codegen.model.JVM;
import org.eclipse.justj.codegen.model.Model;
import org.eclipse.justj.codegen.model.ModelPackage;
import org.eclipse.justj.codegen.model.Touchable;
import org.eclipse.justj.codegen.model.Touchpoint;
import org.eclipse.justj.codegen.model.Variant;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JVM</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.JVMImpl#getTouchpoints <em>Touchpoints</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.JVMImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.JVMImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.JVMImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.JVMImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.JVMImpl#getModel <em>Model</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.JVMImpl#getAboutTextExtra <em>About Text Extra</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.JVMImpl#getVariants <em>Variants</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JVMImpl extends CopyrightableImpl implements JVM
{
  /**
   * The cached value of the '{@link #getTouchpoints() <em>Touchpoints</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTouchpoints()
   * @generated
   * @ordered
   */
  protected EList<Touchpoint> touchpoints;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected static final String VERSION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected String version = VERSION_EDEFAULT;

  /**
   * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabel()
   * @generated
   * @ordered
   */
  protected static final String LABEL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabel()
   * @generated
   * @ordered
   */
  protected String label = LABEL_EDEFAULT;

  /**
   * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected static final String DESCRIPTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected String description = DESCRIPTION_EDEFAULT;

  /**
   * The default value of the '{@link #getAboutTextExtra() <em>About Text Extra</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAboutTextExtra()
   * @generated
   * @ordered
   */
  protected static final String ABOUT_TEXT_EXTRA_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAboutTextExtra() <em>About Text Extra</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAboutTextExtra()
   * @generated
   * @ordered
   */
  protected String aboutTextExtra = ABOUT_TEXT_EXTRA_EDEFAULT;

  /**
   * The cached value of the '{@link #getVariants() <em>Variants</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariants()
   * @generated
   * @ordered
   */
  protected EList<Variant> variants;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JVMImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ModelPackage.Literals.JVM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Touchpoint> getTouchpoints()
  {
    if (touchpoints == null)
    {
      touchpoints = new EObjectContainmentEList<Touchpoint>(Touchpoint.class, this, ModelPackage.JVM__TOUCHPOINTS);
    }
    return touchpoints;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.JVM__NAME, oldName, name));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getVersion()
  {
    return version;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setVersion(String newVersion)
  {
    String oldVersion = version;
    version = newVersion;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.JVM__VERSION, oldVersion, version));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getLabel()
  {
    return label;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setLabel(String newLabel)
  {
    String oldLabel = label;
    label = newLabel;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.JVM__LABEL, oldLabel, label));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getDescription()
  {
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setDescription(String newDescription)
  {
    String oldDescription = description;
    description = newDescription;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.JVM__DESCRIPTION, oldDescription, description));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Variant> getVariants()
  {
    if (variants == null)
    {
      variants = new EObjectContainmentWithInverseEList<Variant>(Variant.class, this, ModelPackage.JVM__VARIANTS, ModelPackage.VARIANT__JVM);
    }
    return variants;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Model getModel()
  {
    if (eContainerFeatureID() != ModelPackage.JVM__MODEL)
    {
      return null;
    }
    return (Model)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModel(Model newModel, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newModel, ModelPackage.JVM__MODEL, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setModel(Model newModel)
  {
    if (newModel != eInternalContainer() || (eContainerFeatureID() != ModelPackage.JVM__MODEL && newModel != null))
    {
      if (EcoreUtil.isAncestor(this, newModel))
      {
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      }
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
      {
        msgs = eBasicRemoveFromContainer(msgs);
      }
      if (newModel != null)
      {
        msgs = ((InternalEObject)newModel).eInverseAdd(this, ModelPackage.MODEL__JV_MS, Model.class, msgs);
      }
      msgs = basicSetModel(newModel, msgs);
      if (msgs != null)
      {
        msgs.dispatch();
      }
    }
    else if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.JVM__MODEL, newModel, newModel));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getAboutTextExtra()
  {
    return aboutTextExtra;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setAboutTextExtra(String newAboutTextExtra)
  {
    String oldAboutTextExtra = aboutTextExtra;
    aboutTextExtra = newAboutTextExtra;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.JVM__ABOUT_TEXT_EXTRA, oldAboutTextExtra, aboutTextExtra));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ModelPackage.JVM__MODEL:
        if (eInternalContainer() != null)
        {
          msgs = eBasicRemoveFromContainer(msgs);
        }
        return basicSetModel((Model)otherEnd, msgs);
      case ModelPackage.JVM__VARIANTS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getVariants()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ModelPackage.JVM__TOUCHPOINTS:
        return ((InternalEList<?>)getTouchpoints()).basicRemove(otherEnd, msgs);
      case ModelPackage.JVM__MODEL:
        return basicSetModel(null, msgs);
      case ModelPackage.JVM__VARIANTS:
        return ((InternalEList<?>)getVariants()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case ModelPackage.JVM__MODEL:
        return eInternalContainer().eInverseRemove(this, ModelPackage.MODEL__JV_MS, Model.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ModelPackage.JVM__TOUCHPOINTS:
        return getTouchpoints();
      case ModelPackage.JVM__NAME:
        return getName();
      case ModelPackage.JVM__VERSION:
        return getVersion();
      case ModelPackage.JVM__LABEL:
        return getLabel();
      case ModelPackage.JVM__DESCRIPTION:
        return getDescription();
      case ModelPackage.JVM__MODEL:
        return getModel();
      case ModelPackage.JVM__ABOUT_TEXT_EXTRA:
        return getAboutTextExtra();
      case ModelPackage.JVM__VARIANTS:
        return getVariants();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ModelPackage.JVM__TOUCHPOINTS:
        getTouchpoints().clear();
        getTouchpoints().addAll((Collection<? extends Touchpoint>)newValue);
        return;
      case ModelPackage.JVM__NAME:
        setName((String)newValue);
        return;
      case ModelPackage.JVM__VERSION:
        setVersion((String)newValue);
        return;
      case ModelPackage.JVM__LABEL:
        setLabel((String)newValue);
        return;
      case ModelPackage.JVM__DESCRIPTION:
        setDescription((String)newValue);
        return;
      case ModelPackage.JVM__MODEL:
        setModel((Model)newValue);
        return;
      case ModelPackage.JVM__ABOUT_TEXT_EXTRA:
        setAboutTextExtra((String)newValue);
        return;
      case ModelPackage.JVM__VARIANTS:
        getVariants().clear();
        getVariants().addAll((Collection<? extends Variant>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ModelPackage.JVM__TOUCHPOINTS:
        getTouchpoints().clear();
        return;
      case ModelPackage.JVM__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ModelPackage.JVM__VERSION:
        setVersion(VERSION_EDEFAULT);
        return;
      case ModelPackage.JVM__LABEL:
        setLabel(LABEL_EDEFAULT);
        return;
      case ModelPackage.JVM__DESCRIPTION:
        setDescription(DESCRIPTION_EDEFAULT);
        return;
      case ModelPackage.JVM__MODEL:
        setModel((Model)null);
        return;
      case ModelPackage.JVM__ABOUT_TEXT_EXTRA:
        setAboutTextExtra(ABOUT_TEXT_EXTRA_EDEFAULT);
        return;
      case ModelPackage.JVM__VARIANTS:
        getVariants().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ModelPackage.JVM__TOUCHPOINTS:
        return touchpoints != null && !touchpoints.isEmpty();
      case ModelPackage.JVM__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ModelPackage.JVM__VERSION:
        return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
      case ModelPackage.JVM__LABEL:
        return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
      case ModelPackage.JVM__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
      case ModelPackage.JVM__MODEL:
        return getModel() != null;
      case ModelPackage.JVM__ABOUT_TEXT_EXTRA:
        return ABOUT_TEXT_EXTRA_EDEFAULT == null ? aboutTextExtra != null : !ABOUT_TEXT_EXTRA_EDEFAULT.equals(aboutTextExtra);
      case ModelPackage.JVM__VARIANTS:
        return variants != null && !variants.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == Touchable.class)
    {
      switch (derivedFeatureID)
      {
        case ModelPackage.JVM__TOUCHPOINTS:
          return ModelPackage.TOUCHABLE__TOUCHPOINTS;
        default:
          return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == Touchable.class)
    {
      switch (baseFeatureID)
      {
        case ModelPackage.TOUCHABLE__TOUCHPOINTS:
          return ModelPackage.JVM__TOUCHPOINTS;
        default:
          return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy())
    {
      return super.toString();
    }

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", version: ");
    result.append(version);
    result.append(", label: ");
    result.append(label);
    result.append(", description: ");
    result.append(description);
    result.append(", aboutTextExtra: ");
    result.append(aboutTextExtra);
    result.append(')');
    return result.toString();
  }

} //JVMImpl
