/* PropertySortDescriptor.java created on Jan 26, 2015
 *
 * Copyright (c) <2015> Pin-Ying Tu <dbi1463@gmail.com>
 * 
 * This file is part of SortDescriptor under the MIT license.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package tw.funnymph.commons.sort;

import java.lang.reflect.Method;

/**
 * This class provides an implementation that uses the given property name to
 * obtain the comparable part from a object. The implementation uses the Java
 * Reflection to call the getter method named <code>get[PropertyName]</code>,
 * e.g., <code>getFirstName</code> to get the first name of a person object.
 * For the computed properties, the getter method can be customized by using
 * the {@link #setGetterMethodPrefix(String)} method.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 */
public class PropertySortDescriptor<InputType> extends AbstractSortDescriptor<InputType> {

	public static final String BOOLEAN_GETTER_PREFIX = "is";
	public static final String DEFAULT_GETTER_PREFIX = "get";

	private static final String GETTER_FORMAT = "%1s%2s";

	private String _prefix;
	private String _propertyName;

	/**
	 * Construct a <code>PropertySortDescriptor</code> instance with the
	 * property name.
	 * 
	 * @param propertyName the property name
	 */
	public PropertySortDescriptor(String propertyName) {
		this(propertyName, false, true);
	}

	/**
	 * Construct a <code>PropertySortDescriptor</code> instance with the
	 * property name. If the {@code isBooleanProperty} is {@code true},
	 * the getter method will be <code>is[PropertyName]</code>, e.g.,
	 * {@code isAdult()}.
	 * 
	 * @param propertyName the property name
	 * @param isBooleanProperty {@code true} to use <code>is[PropertyName]</code> getter
	 */
	public PropertySortDescriptor(String propertyName, boolean isBooleanProperty) {
		this(propertyName, isBooleanProperty, true);
	}

	/**
	 * Construct a <code>PropertySortDescriptor</code> instance with the
	 * property name. If the {@code isBooleanProperty} is {@code true},
	 * the getter method will be <code>is[PropertyName]</code>, e.g.,
	 * {@code isAdult()}.
	 * 
	 * @param propertyName the property name
	 * @param isBooleanProperty {@code true} to use <code>is[PropertyName]</code> getter
	 * @param ascending to determine the sort ordering
	 */
	public PropertySortDescriptor(String propertyName, boolean isBooleanProperty, boolean ascending) {
		super(ascending);
		_propertyName = propertyName;
		_prefix = isBooleanProperty? BOOLEAN_GETTER_PREFIX : DEFAULT_GETTER_PREFIX;
	}

	/**
	 * Set the getter method prefix.
	 * 
	 * @param prefix the new prefix
	 */
	public void setGetterMethodPrefix(String prefix) {
		_prefix = prefix;
	}

	/**
	 * Get the name of the property that is used to sort.
	 * 
	 * @return the property name
	 */
	public String getPropertyName() {
		return _propertyName;
	}

	@Override
	public Comparable<?> transform(InputType input) {
		Class<?> clazz = input.getClass();
		Class<?>[] parameterTypes = new Class<?>[] { };
		Object[] parameters = new Object[] { };
		try {
			Method method = clazz.getMethod(getMethodName(), parameterTypes);
			return (Comparable<?>) method.invoke(input, parameters);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get the method name based on the prefix.
	 * 
	 * @return the method name
	 */
	private String getMethodName() {
		String propertyName = isNotBlank(_prefix)? capitalizeFirstLetter(_propertyName) : _propertyName;
		return String.format(GETTER_FORMAT, _prefix, propertyName).trim();
	}

	/**
	 * Check whether the given string is not blank.
	 * 
	 * @param value the string to check
	 * @return {@code true} if the given string is not blank
	 */
	private boolean isNotBlank(String value) {
		return value != null && !value.trim().isEmpty();
	}

	/**
	 * Capitalize the first letter of the string.
	 * 
	 * @param string the string to capitalize
	 * @return the capitalized string
	 */
	private String capitalizeFirstLetter(String string) {
		String firstLetter = string.substring(0, 1).toUpperCase();
		String otherLetters = string.substring(1);
		return firstLetter + otherLetters;
	}
}
