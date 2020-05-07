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


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.justj.codegen.model.Copyrightable;
import org.eclipse.justj.codegen.model.ModelPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Copyrightable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.CopyrightableImpl#getCopyrightHolder <em>Copyright Holder</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.CopyrightableImpl#getCopyrightYear <em>Copyright Year</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.CopyrightableImpl#getCopyrightText <em>Copyright Text</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CopyrightableImpl extends MinimalEObjectImpl.Container implements Copyrightable
{
  /**
   * The default value of the '{@link #getCopyrightHolder() <em>Copyright Holder</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCopyrightHolder()
   * @generated
   * @ordered
   */
  protected static final String COPYRIGHT_HOLDER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCopyrightHolder() <em>Copyright Holder</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCopyrightHolder()
   * @generated
   * @ordered
   */
  protected String copyrightHolder = COPYRIGHT_HOLDER_EDEFAULT;

  /**
   * The default value of the '{@link #getCopyrightYear() <em>Copyright Year</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCopyrightYear()
   * @generated
   * @ordered
   */
  protected static final String COPYRIGHT_YEAR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCopyrightYear() <em>Copyright Year</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCopyrightYear()
   * @generated
   * @ordered
   */
  protected String copyrightYear = COPYRIGHT_YEAR_EDEFAULT;

  /**
   * The default value of the '{@link #getCopyrightText() <em>Copyright Text</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCopyrightText()
   * @generated
   * @ordered
   */
  protected static final String COPYRIGHT_TEXT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCopyrightText() <em>Copyright Text</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCopyrightText()
   * @generated
   * @ordered
   */
  protected String copyrightText = COPYRIGHT_TEXT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CopyrightableImpl()
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
    return ModelPackage.Literals.COPYRIGHTABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getCopyrightHolder()
  {
    return copyrightHolder;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setCopyrightHolder(String newCopyrightHolder)
  {
    String oldCopyrightHolder = copyrightHolder;
    copyrightHolder = newCopyrightHolder;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.COPYRIGHTABLE__COPYRIGHT_HOLDER, oldCopyrightHolder, copyrightHolder));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getCopyrightYear()
  {
    return copyrightYear;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setCopyrightYear(String newCopyrightYear)
  {
    String oldCopyrightYear = copyrightYear;
    copyrightYear = newCopyrightYear;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.COPYRIGHTABLE__COPYRIGHT_YEAR, oldCopyrightYear, copyrightYear));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getCopyrightText()
  {
    return copyrightText;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setCopyrightText(String newCopyrightText)
  {
    String oldCopyrightText = copyrightText;
    copyrightText = newCopyrightText;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.COPYRIGHTABLE__COPYRIGHT_TEXT, oldCopyrightText, copyrightText));
    }
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
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_HOLDER:
        return getCopyrightHolder();
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_YEAR:
        return getCopyrightYear();
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_TEXT:
        return getCopyrightText();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_HOLDER:
        setCopyrightHolder((String)newValue);
        return;
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_YEAR:
        setCopyrightYear((String)newValue);
        return;
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_TEXT:
        setCopyrightText((String)newValue);
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
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_HOLDER:
        setCopyrightHolder(COPYRIGHT_HOLDER_EDEFAULT);
        return;
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_YEAR:
        setCopyrightYear(COPYRIGHT_YEAR_EDEFAULT);
        return;
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_TEXT:
        setCopyrightText(COPYRIGHT_TEXT_EDEFAULT);
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
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_HOLDER:
        return COPYRIGHT_HOLDER_EDEFAULT == null ? copyrightHolder != null : !COPYRIGHT_HOLDER_EDEFAULT.equals(copyrightHolder);
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_YEAR:
        return COPYRIGHT_YEAR_EDEFAULT == null ? copyrightYear != null : !COPYRIGHT_YEAR_EDEFAULT.equals(copyrightYear);
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_TEXT:
        return COPYRIGHT_TEXT_EDEFAULT == null ? copyrightText != null : !COPYRIGHT_TEXT_EDEFAULT.equals(copyrightText);
    }
    return super.eIsSet(featureID);
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
    result.append(" (copyrightHolder: ");
    result.append(copyrightHolder);
    result.append(", copyrightYear: ");
    result.append(copyrightYear);
    result.append(", copyrightText: ");
    result.append(copyrightText);
    result.append(')');
    return result.toString();
  }

} //CopyrightableImpl
