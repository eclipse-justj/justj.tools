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
import org.eclipse.justj.codegen.model.Model;
import org.eclipse.justj.codegen.model.ModelFactory;
import org.eclipse.justj.codegen.model.ModelPackage;


/**
 * This is the item provider adapter for a {@link org.eclipse.justj.codegen.model.Model} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelItemProvider extends CopyrightableItemProvider
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelItemProvider(AdapterFactory adapterFactory)
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
      addLabelPropertyDescriptor(object);
      addProviderPropertyDescriptor(object);
      addProviderImageNamePropertyDescriptor(object);
      addProviderImageDataPropertyDescriptor(object);
      addAboutURLPropertyDescriptor(object);
      addTargetPropertyDescriptor(object);
      addSourcePropertyDescriptor(object);
      addLocalCachePropertyDescriptor(object);
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
        getString("_UI_Model_name_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Model_name_feature", "_UI_Model_type"),
        ModelPackage.Literals.MODEL__NAME,
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
        getString("_UI_Model_label_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Model_label_feature", "_UI_Model_type"),
        ModelPackage.Literals.MODEL__LABEL,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Provider feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addProviderPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Model_provider_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Model_provider_feature", "_UI_Model_type"),
        ModelPackage.Literals.MODEL__PROVIDER,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Provider Image Name feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addProviderImageNamePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Model_providerImageName_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Model_providerImageName_feature", "_UI_Model_type"),
        ModelPackage.Literals.MODEL__PROVIDER_IMAGE_NAME,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Provider Image Data feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addProviderImageDataPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Model_providerImageData_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Model_providerImageData_feature", "_UI_Model_type"),
        ModelPackage.Literals.MODEL__PROVIDER_IMAGE_DATA,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the About URL feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAboutURLPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Model_aboutURL_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Model_aboutURL_feature", "_UI_Model_type"),
        ModelPackage.Literals.MODEL__ABOUT_URL,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Target feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTargetPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Model_target_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Model_target_feature", "_UI_Model_type"),
        ModelPackage.Literals.MODEL__TARGET,
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
        getString("_UI_Model_source_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Model_source_feature", "_UI_Model_type"),
        ModelPackage.Literals.MODEL__SOURCE,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Local Cache feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addLocalCachePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Model_localCache_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Model_localCache_feature", "_UI_Model_type"),
        ModelPackage.Literals.MODEL__LOCAL_CACHE,
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
      childrenFeatures.add(ModelPackage.Literals.MODEL__JV_MS);
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
   * This returns Model.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/Model"));
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
    Model model = (Model)object;
    String name = model.getName();
    String label = model.getLabel();
    return "" + name + " - " + label;
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

    switch (notification.getFeatureID(Model.class))
    {
      case ModelPackage.MODEL__NAME:
      case ModelPackage.MODEL__LABEL:
      case ModelPackage.MODEL__PROVIDER:
      case ModelPackage.MODEL__PROVIDER_IMAGE_NAME:
      case ModelPackage.MODEL__PROVIDER_IMAGE_DATA:
      case ModelPackage.MODEL__ABOUT_URL:
      case ModelPackage.MODEL__TARGET:
      case ModelPackage.MODEL__SOURCE:
      case ModelPackage.MODEL__LOCAL_CACHE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case ModelPackage.MODEL__TOUCHPOINTS:
      case ModelPackage.MODEL__JV_MS:
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

    newChildDescriptors.add(createChildParameter(ModelPackage.Literals.MODEL__JV_MS, ModelFactory.eINSTANCE.createJVM()));
  }

}
