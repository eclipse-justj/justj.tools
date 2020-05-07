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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.justj.codegen.model.JVM;
import org.eclipse.justj.codegen.model.Model;
import org.eclipse.justj.codegen.model.ModelPackage;
import org.eclipse.justj.codegen.model.Touchable;
import org.eclipse.justj.codegen.model.Touchpoint;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getTouchpoints <em>Touchpoints</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getProvider <em>Provider</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getProviderImageName <em>Provider Image Name</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getProviderImageData <em>Provider Image Data</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getAboutURL <em>About URL</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getLocalCache <em>Local Cache</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.ModelImpl#getJVMs <em>JV Ms</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelImpl extends CopyrightableImpl implements Model
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
   * The default value of the '{@link #getProvider() <em>Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvider()
   * @generated
   * @ordered
   */
  protected static final String PROVIDER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getProvider() <em>Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvider()
   * @generated
   * @ordered
   */
  protected String provider = PROVIDER_EDEFAULT;

  /**
   * The default value of the '{@link #getProviderImageName() <em>Provider Image Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProviderImageName()
   * @generated
   * @ordered
   */
  protected static final String PROVIDER_IMAGE_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getProviderImageName() <em>Provider Image Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProviderImageName()
   * @generated
   * @ordered
   */
  protected String providerImageName = PROVIDER_IMAGE_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getProviderImageData() <em>Provider Image Data</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProviderImageData()
   * @generated
   * @ordered
   */
  protected static final byte[] PROVIDER_IMAGE_DATA_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getProviderImageData() <em>Provider Image Data</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProviderImageData()
   * @generated
   * @ordered
   */
  protected byte[] providerImageData = PROVIDER_IMAGE_DATA_EDEFAULT;

  /**
   * The default value of the '{@link #getAboutURL() <em>About URL</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAboutURL()
   * @generated
   * @ordered
   */
  protected static final String ABOUT_URL_EDEFAULT = "https://www.eclipse.org/legal/epl/epl-2.0/about.html";

  /**
   * The cached value of the '{@link #getAboutURL() <em>About URL</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAboutURL()
   * @generated
   * @ordered
   */
  protected String aboutURL = ABOUT_URL_EDEFAULT;

  /**
   * The default value of the '{@link #getTarget() <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected static final String TARGET_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected String target = TARGET_EDEFAULT;

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
   * The default value of the '{@link #getLocalCache() <em>Local Cache</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocalCache()
   * @generated
   * @ordered
   */
  protected static final String LOCAL_CACHE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLocalCache() <em>Local Cache</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocalCache()
   * @generated
   * @ordered
   */
  protected String localCache = LOCAL_CACHE_EDEFAULT;

  /**
   * The cached value of the '{@link #getJVMs() <em>JV Ms</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJVMs()
   * @generated
   * @ordered
   */
  protected EList<JVM> jVMs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelImpl()
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
    return ModelPackage.Literals.MODEL;
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
      touchpoints = new EObjectContainmentEList<Touchpoint>(Touchpoint.class, this, ModelPackage.MODEL__TOUCHPOINTS);
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODEL__NAME, oldName, name));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODEL__LABEL, oldLabel, label));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<JVM> getJVMs()
  {
    if (jVMs == null)
    {
      jVMs = new EObjectContainmentWithInverseEList<JVM>(JVM.class, this, ModelPackage.MODEL__JV_MS, ModelPackage.JVM__MODEL);
    }
    return jVMs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getProvider()
  {
    return provider;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setProvider(String newProvider)
  {
    String oldProvider = provider;
    provider = newProvider;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODEL__PROVIDER, oldProvider, provider));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getProviderImageName()
  {
    return providerImageName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setProviderImageName(String newProviderImageName)
  {
    String oldProviderImageName = providerImageName;
    providerImageName = newProviderImageName;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODEL__PROVIDER_IMAGE_NAME, oldProviderImageName, providerImageName));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public byte[] getProviderImageData()
  {
    return providerImageData;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setProviderImageData(byte[] newProviderImageData)
  {
    byte[] oldProviderImageData = providerImageData;
    providerImageData = newProviderImageData;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODEL__PROVIDER_IMAGE_DATA, oldProviderImageData, providerImageData));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getAboutURL()
  {
    return aboutURL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setAboutURL(String newAboutURL)
  {
    String oldAboutURL = aboutURL;
    aboutURL = newAboutURL;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODEL__ABOUT_URL, oldAboutURL, aboutURL));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setTarget(String newTarget)
  {
    String oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODEL__TARGET, oldTarget, target));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODEL__SOURCE, oldSource, source));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getLocalCache()
  {
    return localCache;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setLocalCache(String newLocalCache)
  {
    String oldLocalCache = localCache;
    localCache = newLocalCache;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MODEL__LOCAL_CACHE, oldLocalCache, localCache));
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
      case ModelPackage.MODEL__JV_MS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getJVMs()).basicAdd(otherEnd, msgs);
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
      case ModelPackage.MODEL__TOUCHPOINTS:
        return ((InternalEList<?>)getTouchpoints()).basicRemove(otherEnd, msgs);
      case ModelPackage.MODEL__JV_MS:
        return ((InternalEList<?>)getJVMs()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case ModelPackage.MODEL__TOUCHPOINTS:
        return getTouchpoints();
      case ModelPackage.MODEL__NAME:
        return getName();
      case ModelPackage.MODEL__LABEL:
        return getLabel();
      case ModelPackage.MODEL__PROVIDER:
        return getProvider();
      case ModelPackage.MODEL__PROVIDER_IMAGE_NAME:
        return getProviderImageName();
      case ModelPackage.MODEL__PROVIDER_IMAGE_DATA:
        return getProviderImageData();
      case ModelPackage.MODEL__ABOUT_URL:
        return getAboutURL();
      case ModelPackage.MODEL__TARGET:
        return getTarget();
      case ModelPackage.MODEL__SOURCE:
        return getSource();
      case ModelPackage.MODEL__LOCAL_CACHE:
        return getLocalCache();
      case ModelPackage.MODEL__JV_MS:
        return getJVMs();
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
      case ModelPackage.MODEL__TOUCHPOINTS:
        getTouchpoints().clear();
        getTouchpoints().addAll((Collection<? extends Touchpoint>)newValue);
        return;
      case ModelPackage.MODEL__NAME:
        setName((String)newValue);
        return;
      case ModelPackage.MODEL__LABEL:
        setLabel((String)newValue);
        return;
      case ModelPackage.MODEL__PROVIDER:
        setProvider((String)newValue);
        return;
      case ModelPackage.MODEL__PROVIDER_IMAGE_NAME:
        setProviderImageName((String)newValue);
        return;
      case ModelPackage.MODEL__PROVIDER_IMAGE_DATA:
        setProviderImageData((byte[])newValue);
        return;
      case ModelPackage.MODEL__ABOUT_URL:
        setAboutURL((String)newValue);
        return;
      case ModelPackage.MODEL__TARGET:
        setTarget((String)newValue);
        return;
      case ModelPackage.MODEL__SOURCE:
        setSource((String)newValue);
        return;
      case ModelPackage.MODEL__LOCAL_CACHE:
        setLocalCache((String)newValue);
        return;
      case ModelPackage.MODEL__JV_MS:
        getJVMs().clear();
        getJVMs().addAll((Collection<? extends JVM>)newValue);
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
      case ModelPackage.MODEL__TOUCHPOINTS:
        getTouchpoints().clear();
        return;
      case ModelPackage.MODEL__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ModelPackage.MODEL__LABEL:
        setLabel(LABEL_EDEFAULT);
        return;
      case ModelPackage.MODEL__PROVIDER:
        setProvider(PROVIDER_EDEFAULT);
        return;
      case ModelPackage.MODEL__PROVIDER_IMAGE_NAME:
        setProviderImageName(PROVIDER_IMAGE_NAME_EDEFAULT);
        return;
      case ModelPackage.MODEL__PROVIDER_IMAGE_DATA:
        setProviderImageData(PROVIDER_IMAGE_DATA_EDEFAULT);
        return;
      case ModelPackage.MODEL__ABOUT_URL:
        setAboutURL(ABOUT_URL_EDEFAULT);
        return;
      case ModelPackage.MODEL__TARGET:
        setTarget(TARGET_EDEFAULT);
        return;
      case ModelPackage.MODEL__SOURCE:
        setSource(SOURCE_EDEFAULT);
        return;
      case ModelPackage.MODEL__LOCAL_CACHE:
        setLocalCache(LOCAL_CACHE_EDEFAULT);
        return;
      case ModelPackage.MODEL__JV_MS:
        getJVMs().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("null")
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ModelPackage.MODEL__TOUCHPOINTS:
        return touchpoints != null && !touchpoints.isEmpty();
      case ModelPackage.MODEL__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ModelPackage.MODEL__LABEL:
        return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
      case ModelPackage.MODEL__PROVIDER:
        return PROVIDER_EDEFAULT == null ? provider != null : !PROVIDER_EDEFAULT.equals(provider);
      case ModelPackage.MODEL__PROVIDER_IMAGE_NAME:
        return PROVIDER_IMAGE_NAME_EDEFAULT == null ? providerImageName != null : !PROVIDER_IMAGE_NAME_EDEFAULT.equals(providerImageName);
      case ModelPackage.MODEL__PROVIDER_IMAGE_DATA:
        return PROVIDER_IMAGE_DATA_EDEFAULT == null ? providerImageData != null : !PROVIDER_IMAGE_DATA_EDEFAULT.equals(providerImageData);
      case ModelPackage.MODEL__ABOUT_URL:
        return ABOUT_URL_EDEFAULT == null ? aboutURL != null : !ABOUT_URL_EDEFAULT.equals(aboutURL);
      case ModelPackage.MODEL__TARGET:
        return TARGET_EDEFAULT == null ? target != null : !TARGET_EDEFAULT.equals(target);
      case ModelPackage.MODEL__SOURCE:
        return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
      case ModelPackage.MODEL__LOCAL_CACHE:
        return LOCAL_CACHE_EDEFAULT == null ? localCache != null : !LOCAL_CACHE_EDEFAULT.equals(localCache);
      case ModelPackage.MODEL__JV_MS:
        return jVMs != null && !jVMs.isEmpty();
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
        case ModelPackage.MODEL__TOUCHPOINTS:
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
          return ModelPackage.MODEL__TOUCHPOINTS;
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
    result.append(", label: ");
    result.append(label);
    result.append(", provider: ");
    result.append(provider);
    result.append(", providerImageName: ");
    result.append(providerImageName);
    result.append(", providerImageData: ");
    result.append(providerImageData);
    result.append(", aboutURL: ");
    result.append(aboutURL);
    result.append(", target: ");
    result.append(target);
    result.append(", source: ");
    result.append(source);
    result.append(", localCache: ");
    result.append(localCache);
    result.append(')');
    return result.toString();
  }

} //ModelImpl
