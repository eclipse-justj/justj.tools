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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.justj.codegen.model.ModelFactory;
import org.eclipse.justj.codegen.model.ModelPackage;
import org.eclipse.justj.codegen.model.Variant;


/**
 * This is the item provider adapter for a {@link org.eclipse.justj.codegen.model.Variant} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class VariantItemProvider extends CopyrightableItemProvider
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariantItemProvider(AdapterFactory adapterFactory)
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

      addOsPropertyDescriptor(object);
      addArchPropertyDescriptor(object);
      addLabelPropertyDescriptor(object);
      addSourcePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Os feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addOsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Variant_os_feature"),
        getString("_UI_Variant_os_description"),
        ModelPackage.Literals.VARIANT__OS,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Arch feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addArchPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Variant_arch_feature"),
        getString("_UI_Variant_arch_description"),
        ModelPackage.Literals.VARIANT__ARCH,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Label feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addLabelPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Variant_label_feature"),
        getString("_UI_Variant_label_description"),
        ModelPackage.Literals.VARIANT__LABEL,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Source feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSourcePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Variant_source_feature"),
        getString("_UI_Variant_source_description"),
        ModelPackage.Literals.VARIANT__SOURCE,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(ModelPackage.Literals.TOUCHABLE__TOUCHPOINTS);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   * This returns Variant.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/Variant"));
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
   * @generated NOT
   */
  @Override
  public String getText(Object object)
  {
    Variant variant = (Variant)object;
    String os = variant.getOs();
    String arch = variant.getArch();
    String label = variant.getLabel();
    return "" + os + '.' + arch + " - " + label;
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

    switch (notification.getFeatureID(Variant.class))
    {
      case ModelPackage.VARIANT__OS:
      case ModelPackage.VARIANT__ARCH:
      case ModelPackage.VARIANT__LABEL:
      case ModelPackage.VARIANT__SOURCE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case ModelPackage.VARIANT__TOUCHPOINTS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

    newChildDescriptors.add(createChildParameter(ModelPackage.Literals.TOUCHABLE__TOUCHPOINTS, ModelFactory.eINSTANCE.createTouchpoint()));
  }
}
