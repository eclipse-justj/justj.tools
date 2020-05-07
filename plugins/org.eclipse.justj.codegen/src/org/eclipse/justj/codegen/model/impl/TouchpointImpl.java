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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.justj.codegen.model.ModelPackage;
import org.eclipse.justj.codegen.model.Phase;
import org.eclipse.justj.codegen.model.Touchpoint;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Touchpoint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.TouchpointImpl#getPhase <em>Phase</em>}</li>
 *   <li>{@link org.eclipse.justj.codegen.model.impl.TouchpointImpl#getInstructions <em>Instructions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TouchpointImpl extends MinimalEObjectImpl.Container implements Touchpoint
{
  /**
   * The default value of the '{@link #getPhase() <em>Phase</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPhase()
   * @generated
   * @ordered
   */
  protected static final Phase PHASE_EDEFAULT = Phase.INSTALL;

  /**
   * The cached value of the '{@link #getPhase() <em>Phase</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPhase()
   * @generated
   * @ordered
   */
  protected Phase phase = PHASE_EDEFAULT;

  /**
   * The cached value of the '{@link #getInstructions() <em>Instructions</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstructions()
   * @generated
   * @ordered
   */
  protected EList<String> instructions;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TouchpointImpl()
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
    return ModelPackage.Literals.TOUCHPOINT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Phase getPhase()
  {
    return phase;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setPhase(Phase newPhase)
  {
    Phase oldPhase = phase;
    phase = newPhase == null ? PHASE_EDEFAULT : newPhase;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOUCHPOINT__PHASE, oldPhase, phase));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<String> getInstructions()
  {
    if (instructions == null)
    {
      instructions = new EDataTypeEList<String>(String.class, this, ModelPackage.TOUCHPOINT__INSTRUCTIONS);
    }
    return instructions;
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
      case ModelPackage.TOUCHPOINT__PHASE:
        return getPhase();
      case ModelPackage.TOUCHPOINT__INSTRUCTIONS:
        return getInstructions();
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
      case ModelPackage.TOUCHPOINT__PHASE:
        setPhase((Phase)newValue);
        return;
      case ModelPackage.TOUCHPOINT__INSTRUCTIONS:
        getInstructions().clear();
        getInstructions().addAll((Collection<? extends String>)newValue);
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
      case ModelPackage.TOUCHPOINT__PHASE:
        setPhase(PHASE_EDEFAULT);
        return;
      case ModelPackage.TOUCHPOINT__INSTRUCTIONS:
        getInstructions().clear();
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
      case ModelPackage.TOUCHPOINT__PHASE:
        return phase != PHASE_EDEFAULT;
      case ModelPackage.TOUCHPOINT__INSTRUCTIONS:
        return instructions != null && !instructions.isEmpty();
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
    result.append(" (phase: ");
    result.append(phase);
    result.append(", instructions: ");
    result.append(instructions);
    result.append(')');
    return result.toString();
  }

} //TouchpointImpl
