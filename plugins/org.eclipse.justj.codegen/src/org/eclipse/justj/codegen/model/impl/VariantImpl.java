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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.justj.codegen.model.JVM;
import org.eclipse.justj.codegen.model.ModelPackage;
import org.eclipse.justj.codegen.model.Touchable;
import org.eclipse.justj.codegen.model.Touchpoint;
import org.eclipse.justj.codegen.model.Variant;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.VariantImpl#getTouchpoints <em>Touchpoints</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.VariantImpl#getOs <em>Os</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.VariantImpl#getArch <em>Arch</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.VariantImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.VariantImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.VariantImpl#getJVM <em>JVM</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariantImpl extends CopyrightableImpl implements Variant
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
   * The default value of the '{@link #getOs() <em>Os</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOs()
   * @generated
   * @ordered
   */
  protected static final String OS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOs() <em>Os</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOs()
   * @generated
   * @ordered
   */
  protected String os = OS_EDEFAULT;

  /**
   * The default value of the '{@link #getArch() <em>Arch</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArch()
   * @generated
   * @ordered
   */
  protected static final String ARCH_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getArch() <em>Arch</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArch()
   * @generated
   * @ordered
   */
  protected String arch = ARCH_EDEFAULT;

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
   * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected static final String SOURCE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected String source = SOURCE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VariantImpl()
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
    return ModelPackage.Literals.VARIANT;
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
      touchpoints = new EObjectContainmentEList<Touchpoint>(Touchpoint.class, this, ModelPackage.VARIANT__TOUCHPOINTS);
    }
    return touchpoints;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getOs()
  {
    return os;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setOs(String newOs)
  {
    String oldOs = os;
    os = newOs;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.VARIANT__OS, oldOs, os));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getArch()
  {
    return arch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setArch(String newArch)
  {
    String oldArch = arch;
    arch = newArch;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.VARIANT__ARCH, oldArch, arch));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public JVM getJVM()
  {
    if (eContainerFeatureID() != ModelPackage.VARIANT__JVM)
    {
      return null;
    }
    return (JVM)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetJVM(JVM newJVM, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newJVM, ModelPackage.VARIANT__JVM, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setJVM(JVM newJVM)
  {
    if (newJVM != eInternalContainer() || (eContainerFeatureID() != ModelPackage.VARIANT__JVM && newJVM != null))
    {
      if (EcoreUtil.isAncestor(this, newJVM))
      {
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      }
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
      {
        msgs = eBasicRemoveFromContainer(msgs);
      }
      if (newJVM != null)
      {
        msgs = ((InternalEObject)newJVM).eInverseAdd(this, ModelPackage.JVM__VARIANTS, JVM.class, msgs);
      }
      msgs = basicSetJVM(newJVM, msgs);
      if (msgs != null)
      {
        msgs.dispatch();
      }
    }
    else if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.VARIANT__JVM, newJVM, newJVM));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.VARIANT__LABEL, oldLabel, label));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getSource()
  {
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setSource(String newSource)
  {
    String oldSource = source;
    source = newSource;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.VARIANT__SOURCE, oldSource, source));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ModelPackage.VARIANT__JVM:
        if (eInternalContainer() != null)
        {
          msgs = eBasicRemoveFromContainer(msgs);
        }
        return basicSetJVM((JVM)otherEnd, msgs);
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
      case ModelPackage.VARIANT__TOUCHPOINTS:
        return ((InternalEList<?>)getTouchpoints()).basicRemove(otherEnd, msgs);
      case ModelPackage.VARIANT__JVM:
        return basicSetJVM(null, msgs);
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
      case ModelPackage.VARIANT__JVM:
        return eInternalContainer().eInverseRemove(this, ModelPackage.JVM__VARIANTS, JVM.class, msgs);
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
      case ModelPackage.VARIANT__TOUCHPOINTS:
        return getTouchpoints();
      case ModelPackage.VARIANT__OS:
        return getOs();
      case ModelPackage.VARIANT__ARCH:
        return getArch();
      case ModelPackage.VARIANT__LABEL:
        return getLabel();
      case ModelPackage.VARIANT__SOURCE:
        return getSource();
      case ModelPackage.VARIANT__JVM:
        return getJVM();
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
      case ModelPackage.VARIANT__TOUCHPOINTS:
        getTouchpoints().clear();
        getTouchpoints().addAll((Collection<? extends Touchpoint>)newValue);
        return;
      case ModelPackage.VARIANT__OS:
        setOs((String)newValue);
        return;
      case ModelPackage.VARIANT__ARCH:
        setArch((String)newValue);
        return;
      case ModelPackage.VARIANT__LABEL:
        setLabel((String)newValue);
        return;
      case ModelPackage.VARIANT__SOURCE:
        setSource((String)newValue);
        return;
      case ModelPackage.VARIANT__JVM:
        setJVM((JVM)newValue);
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
      case ModelPackage.VARIANT__TOUCHPOINTS:
        getTouchpoints().clear();
        return;
      case ModelPackage.VARIANT__OS:
        setOs(OS_EDEFAULT);
        return;
      case ModelPackage.VARIANT__ARCH:
        setArch(ARCH_EDEFAULT);
        return;
      case ModelPackage.VARIANT__LABEL:
        setLabel(LABEL_EDEFAULT);
        return;
      case ModelPackage.VARIANT__SOURCE:
        setSource(SOURCE_EDEFAULT);
        return;
      case ModelPackage.VARIANT__JVM:
        setJVM((JVM)null);
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
      case ModelPackage.VARIANT__TOUCHPOINTS:
        return touchpoints != null && !touchpoints.isEmpty();
      case ModelPackage.VARIANT__OS:
        return OS_EDEFAULT == null ? os != null : !OS_EDEFAULT.equals(os);
      case ModelPackage.VARIANT__ARCH:
        return ARCH_EDEFAULT == null ? arch != null : !ARCH_EDEFAULT.equals(arch);
      case ModelPackage.VARIANT__LABEL:
        return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
      case ModelPackage.VARIANT__SOURCE:
        return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
      case ModelPackage.VARIANT__JVM:
        return getJVM() != null;
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
        case ModelPackage.VARIANT__TOUCHPOINTS:
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
          return ModelPackage.VARIANT__TOUCHPOINTS;
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
    result.append(" (os: ");
    result.append(os);
    result.append(", arch: ");
    result.append(arch);
    result.append(", label: ");
    result.append(label);
    result.append(", source: ");
    result.append(source);
    result.append(')');
    return result.toString();
  }

} //VariantImpl
