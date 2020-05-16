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
import org.eclipse.justj.codegen.model.JVM;
import org.eclipse.justj.codegen.model.ModelFactory;
import org.eclipse.justj.codegen.model.ModelPackage;


/**
 * This is the item provider adapter for a {@link org.eclipse.justj.codegen.model.JVM} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JVMItemProvider extends CopyrightableItemProvider
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JVMItemProvider(AdapterFactory adapterFactory)
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

      addNamePropertyDescriptor(object);
      addVersionPropertyDescriptor(object);
      addLabelPropertyDescriptor(object);
      addAboutTextExtraPropertyDescriptor(object);
      addVariantsPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Name feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addNamePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_JVM_name_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_JVM_name_feature", "_UI_JVM_type"),
        ModelPackage.Literals.JVM__NAME,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Version feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addVersionPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_JVM_version_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_JVM_version_feature", "_UI_JVM_type"),
        ModelPackage.Literals.JVM__VERSION,
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
        getString("_UI_JVM_label_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_JVM_label_feature", "_UI_JVM_type"),
        ModelPackage.Literals.JVM__LABEL,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Variants feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addVariantsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_JVM_variants_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_JVM_variants_feature", "_UI_JVM_type"),
        ModelPackage.Literals.JVM__VARIANTS,
        true,
        false,
        true,
        null,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the About Text Extra feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAboutTextExtraPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_JVM_aboutTextExtra_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_JVM_aboutTextExtra_feature", "_UI_JVM_type"),
        ModelPackage.Literals.JVM__ABOUT_TEXT_EXTRA,
        true,
        true,
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
      childrenFeatures.add(ModelPackage.Literals.JVM__VARIANTS);
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
   * This returns JVM.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/JVM"));
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
    JVM jvm = (JVM)object;
    String name = jvm.getName();
    String label = jvm.getLabel();
    String version = jvm.getVersion();
    return "" + name + " - " + version + " - " + label;
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

    switch (notification.getFeatureID(JVM.class))
    {
      case ModelPackage.JVM__NAME:
      case ModelPackage.JVM__VERSION:
      case ModelPackage.JVM__LABEL:
      case ModelPackage.JVM__ABOUT_TEXT_EXTRA:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case ModelPackage.JVM__TOUCHPOINTS:
      case ModelPackage.JVM__VARIANTS:
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

    newChildDescriptors.add(createChildParameter(ModelPackage.Literals.JVM__VARIANTS, ModelFactory.eINSTANCE.createVariant()));
  }

}
