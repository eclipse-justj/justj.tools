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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.provider.AttributeValueWrapperItemProvider;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IUpdateableItemText;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.justj.codegen.model.ModelPackage;
import org.eclipse.justj.codegen.model.Phase;
import org.eclipse.justj.codegen.model.Touchpoint;


/**
 * This is the item provider adapter for a {@link org.eclipse.justj.codegen.model.Touchpoint} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TouchpointItemProvider extends ItemProviderAdapter
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
  public TouchpointItemProvider(AdapterFactory adapterFactory)
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

      addPhasePropertyDescriptor(object);
      addInstructionsPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Phase feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPhasePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Touchpoint_phase_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Touchpoint_phase_feature", "_UI_Touchpoint_type"),
        ModelPackage.Literals.TOUCHPOINT__PHASE,
        true,
        false,
        false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
        null,
        null));
  }

  /**
   * This adds a property descriptor for the Instructions feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addInstructionsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(
      createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Touchpoint_instructions_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Touchpoint_instructions_feature", "_UI_Touchpoint_type"),
        ModelPackage.Literals.TOUCHPOINT__INSTRUCTIONS,
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
      childrenFeatures.add(ModelPackage.Literals.TOUCHPOINT__INSTRUCTIONS);
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
   * This returns Touchpoint.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public Object getImage(Object object)
  {
    Touchpoint touchpoint = (Touchpoint)object;
    Phase phase = touchpoint.getPhase();
    return overlayImage(object, getResourceLocator().getImage("full/obj16/" + phase.getName()));
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
    Touchpoint touchpoint = (Touchpoint)object;
    return touchpoint.getPhase().getLiteral();
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

    switch (notification.getFeatureID(Touchpoint.class))
    {
      case ModelPackage.TOUCHPOINT__PHASE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case ModelPackage.TOUCHPOINT__INSTRUCTIONS:
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

    newChildDescriptors.add(createChildParameter(ModelPackage.Literals.TOUCHPOINT__INSTRUCTIONS, ""));
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

  @Override
  public Object getCreateChildImage(Object owner, Object feature, Object child, Collection<?> selection)
  {
    List<Object> images = new ArrayList<Object>();
    images.add(getImage("full/obj16/Instruction"));
    images.add(EMFEditPlugin.INSTANCE.getImage("full/ovr16/CreateChild"));
    return new ChildCreationComposedImage(images);
  }

  @Override
  public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection)
  {
    return "Instruction";
  }

  private static final class ChildCreationComposedImage extends ComposedImage
  {
    private ChildCreationComposedImage(Collection<?> images)
    {
      super(images);
    }

    @Override
    public List<Point> getDrawPoints(Size size)
    {
      List<Point> result = super.getDrawPoints(size);
      result.get(1).x = size.width - 7;
      return result;
    }
  }

  @Override
  protected Object createWrapper(EObject object, EStructuralFeature feature, Object value, int index)
  {
    if (feature == ModelPackage.Literals.TOUCHPOINT__INSTRUCTIONS)
    {
      return new Instruction(value, object, index, adapterFactory, getResourceLocator());
    }
    else
    {
      return super.createWrapper(object, feature, value, index);
    }
  }

  private static class Instruction extends AttributeValueWrapperItemProvider implements IUpdateableItemText
  {
    public Instruction(Object value, EObject owner, int index, AdapterFactory adapterFactory, ResourceLocator resourceLocator)
    {
      super(value, owner, ModelPackage.Literals.TOUCHPOINT__INSTRUCTIONS, index, adapterFactory, resourceLocator);
    }

    @Override
    public void setText(Object object, String text)
    {
      List<IItemPropertyDescriptor> propertyDescriptors = getPropertyDescriptors(object);
      propertyDescriptors.get(0).setPropertyValue(object, text);
    }

    @Override
    public Object getImage(Object object)
    {
      return resourceLocator.getImage("full/obj16/Instruction");
    }
  }
}
