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
package org.eclipse.justj.codegen.model.util;


import java.lang.reflect.Method;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.SAXXMIHandler;
import org.eclipse.emf.ecore.xmi.impl.XMILoadImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLString;
import org.xml.sax.helpers.DefaultHandler;


/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.justj.codegen.model.util.ModelResourceFactoryImpl
 * @generated
 */
public class ModelResourceImpl extends XMLResourceImpl
{
  /**
   * Creates an instance of the resource.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param uri the URI of the new resource.
   * @generated
   */
  public ModelResourceImpl(URI uri)
  {
    super(uri);
  }

  @Override
  protected XMLLoad createXMLLoad()
  {
    return new XMILoadImpl(createXMLHelper())
      {
        @Override
        protected DefaultHandler makeDefaultHandler()
        {
          return new SAXXMIHandler(resource, helper, options)
            {
              @Override
              protected void setFeatureValue(EObject object, EStructuralFeature feature, Object value)
              {
                if (value != null && extendedMetaData.getFeatureKind(feature) == ExtendedMetaData.ELEMENT_FEATURE && feature.getEType() == EcorePackage.Literals.ESTRING)
                {
                  // Split the string value into lines.
                  String stringValue = value.toString();
                  String[] lines = stringValue.split("\n", Integer.MAX_VALUE);
                  if (lines.length > 1)
                  {
                    // Ignore the first line if it's only white space and the last line if it's only white space.
                    int start = lines[0].trim().length() == 0 ? 1 : 0;
                    String lastLine = lines[lines.length - 1];
                    int end = lastLine.trim().length() == 0 ? lines.length - 1 : lines.length;

                    if (start < end)
                    {
                      // If the first line starts on the same line as the element start tag, ignore that line in terms of indentation computation.
                      // Compute the minimum indentation from the first non white space character of each line.
                      int minIndent = Integer.MAX_VALUE;
                      for (int i = start == 0 ? 1 : start; i < end; ++i)
                      {
                        String line = lines[i];
                        String trimmedLine = line.trim();
                        if (trimmedLine.length() > 0)
                        {
                          int indent = line.indexOf(trimmedLine);
                          if (minIndent > indent)
                          {
                            minIndent = indent;
                          }
                        }
                      }

                      // If there are only blank lines, we're a bit confused.
                      if (minIndent == Integer.MAX_VALUE)
                      {
                        minIndent = lastLine.length();

                        // Use the depth of the stack to determine how much of the whitespace in the last line can be attributed to nesting.
                        // Assume that the XML is consistently formatted/indented and that a consistent fraction of the indentation is attributable to each nesting
                        // level such that the current nesting level would need one more such fractional increment.
                        int depth = elements.size();
                        minIndent += minIndent / depth;
                      }

                      // If the first line starts on the same line as the element start tag, just add it as is.
                      StringBuilder s = new StringBuilder();
                      if (start == 0)
                      {
                        s.append(lines[0]);
                        if (++start < end)
                        {
                          s.append("\n");
                        }
                      }

                      // For all the other lines, trim off the leading indentation.
                      for (int i = start; i < end;)
                      {
                        String line = lines[i];
                        int length = line.length();
                        if (length > minIndent)
                        {
                          s.append(line, minIndent, length);
                        }

                        if (++i < end)
                        {
                          s.append("\n");
                        }
                      }

                      value = s.toString();
                    }
                  }
                }

                super.setFeatureValue(object, feature, value);
              }
            };
        }
      };
  }

  @Override
  protected XMLSave createXMLSave()
  {
    return new XMLSaveImpl(createXMLHelper())
      {
        @Override
        protected String getDatatypeValue(Object value, EStructuralFeature f, boolean isAttribute)
        {
          if (value != null && !isAttribute && f.getEType() == EcorePackage.Literals.ESTRING)
          {
            // Split what we know is a String value into lines.
            String stringValue = value.toString();
            String[] lines = stringValue.split("\r?\n", Integer.MAX_VALUE);
            if (lines.length == 1)
            {
              // If there are no line separators, serialize just the string value.
              //
              return escape == null ? stringValue : escape.convertText(stringValue);
            }

            // Compute the target format that will serialize nested under the element start/end tabs by one indentation level.
            String elementIdent = getElementIndent(1);
            String contentIndent = getElementIndent(2);
            StringBuilder s = new StringBuilder("\n");
            for (int i = 0; i < lines.length; ++i)
            {
              // Only if the line isn't empty, serialize the indentation followed by the line content.
              String line = lines[i];
              if (line.length() != 0)
              {
                s.append(contentIndent);
                s.append(line);
              }

              // Always add the line separator.
              s.append("\n");
            }

            // Add enough indentation so the closing element is indented correctly too.
            s.append(elementIdent);

            return escape == null ? s.toString() : escape.convertText(s.toString());
          }
          else
          {
            return super.getDatatypeValue(value, f, isAttribute);
          }
        }

        private String getElementIndent(int extraIndent)
        {
          try
          {
            return GET_ELEMENT_INDENT_METHOD.invoke(doc, extraIndent).toString();
          }
          catch (Exception ex)
          {
            return "";
          }
        }
      };
  }

  private static final Method GET_ELEMENT_INDENT_METHOD;

  static
  {
    try
    {
      GET_ELEMENT_INDENT_METHOD = XMLString.class.getDeclaredMethod("getElementIndent", int.class);
    }
    catch (Exception exception)
    {
      throw new RuntimeException(exception);
    }

    GET_ELEMENT_INDENT_METHOD.setAccessible(true);
  }

} //ModelResourceImpl
