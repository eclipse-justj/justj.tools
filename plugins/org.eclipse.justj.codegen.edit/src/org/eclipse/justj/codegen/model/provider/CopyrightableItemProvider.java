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
package org.eclipse.justj.codegen.model.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.justj.codegen.model.Copyrightable;
import org.eclipse.justj.codegen.model.ModelPackage;


/**
 * This is the item provider adapter for a {@link org.eclipse.justj.codegen.model.Copyrightable} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CopyrightableItemProvider extends ItemProviderAdapter
  implements
    IEditingDomainItemProvider,
    IStructuredItemContentProvider,
    ITreeItemContentProvider,
    IItemLabelProvider,
    IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CopyrightableItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addCopyrightHolderPropertyDescriptor(object);
      addCopyrightYearPropertyDescriptor(object);
      addCopyrightTextPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Copyright Holder feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCopyrightHolderPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Copyrightable_copyrightHolder_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Copyrightable_copyrightHolder_feature", "_UI_Copyrightable_type"),
        ModelPackage.Literals.COPYRIGHTABLE__COPYRIGHT_HOLDER,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Copyright Year feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCopyrightYearPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Copyrightable_copyrightYear_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Copyrightable_copyrightYear_feature", "_UI_Copyrightable_type"),
        ModelPackage.Literals.COPYRIGHTABLE__COPYRIGHT_YEAR,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Copyright Text feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCopyrightTextPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Copyrightable_copyrightText_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Copyrightable_copyrightText_feature", "_UI_Copyrightable_type"),
        ModelPackage.Literals.COPYRIGHTABLE__COPYRIGHT_TEXT,
        true,
        true,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected boolean shouldComposeCreationImage()
  {
    return true;
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getText(Object object)
  {
    String label = ((Copyrightable)object).getCopyrightHolder();
    return label == null || label.length() == 0 ? getString("_UI_Copyrightable_type") : getString("_UI_Copyrightable_type") + " " + label;
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(Copyrightable.class))
    {
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_HOLDER:
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_YEAR:
      case ModelPackage.COPYRIGHTABLE__COPYRIGHT_TEXT:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator()
  {
    return ModelEditPlugin.INSTANCE;
  }

}
