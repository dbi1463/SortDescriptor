/* SortDescriptorsBuilder.java created on Feb 1, 2015
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class provides a set of methods to organize sort descriptors together
 * to sort elements.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 * @param <InputType> the type of the elements to be sorted
 */
public class SortDescriptorsBuilder<InputType> {

	private List<SortDescriptor<InputType>> _descriptors;

	/**
	 * Create a <code>SortDescriptorsBuilder</code> instance and wrap the given
	 * sort descriptor as the first descriptor.
	 * 
	 * @param descriptor the first sort descriptor
	 * @param <InputType> the type of the elements to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public static <InputType> SortDescriptorsBuilder<InputType> of(SortDescriptor<InputType> descriptor) {
		SortDescriptorsBuilder<InputType> builder = new SortDescriptorsBuilder<InputType>();
		builder.add(descriptor);
		return builder;
	}

	/**
	 * Create a <code>SortDescriptorsBuilder</code> instance and wrap the given
	 * transformer as the first sort descriptor to sort elements <em>ascending</em>.
	 * 
	 * @param transformer the transformer to be the first sort descriptor
	 * @param <InputType> the type of the elements to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public static <InputType> SortDescriptorsBuilder<InputType> of(Transformer<InputType, Comparable<?>> transformer) {
		return of(transformer, true);
	}

	/**
	 * Create a <code>SortDescriptorsBuilder</code> instance and wrap the given
	 * transformer as the first sort descriptor.
	 * 
	 * @param transformer the transformer to be the first sort descriptor
	 * @param ascending to sort elements ascending or descending
	 * @param <InputType> the type of the elements to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public static <InputType> SortDescriptorsBuilder<InputType> of(Transformer<InputType, Comparable<?>> transformer, boolean ascending) {
		SortDescriptorsBuilder<InputType> builder = new SortDescriptorsBuilder<InputType>();
		builder.add(transformer, ascending);
		return builder;
	}

	/**
	 * Create a <code>SortDescriptorsBuilder</code> instance and wrap a sort descriptor
	 * to sort elements <em>ascending</em> based on the property name as the first sort
	 * descriptor.
	 * 
	 * @param propertyName the property name to sort
	 * @param <InputType> the type of the elements to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public static <InputType> SortDescriptorsBuilder<InputType> of(String propertyName) {
		return of(propertyName, true);
	}

	/**
	 * Create a <code>SortDescriptorsBuilder</code> instance and wrap a sort descriptor
	 * to sort elements based on the property name as the first sort descriptor.
	 * 
	 * @param propertyName the property name to sort
	 * @param ascending to sort elements ascending or descending
	 * @param <InputType> the type of the elements to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public static <InputType> SortDescriptorsBuilder<InputType> of(String propertyName, boolean ascending) {
		return of(propertyName, false, ascending);
	}

	/**
	 * Create a <code>SortDescriptorsBuilder</code> instance and wrap a sort descriptor
	 * to sort elements based on the property name as the first sort descriptor. The name
	 * of the getter method can be customized by the {@code methodPrefix}.
	 * 
	 * @param propertyName the property name to sort
	 * @param methodPrefix the customized getter method prefix
	 * @param ascending to sort elements ascending or descending
	 * @param <InputType> the type of the elements to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public static <InputType> SortDescriptorsBuilder<InputType> of(String propertyName, String methodPrefix, boolean ascending) {
		SortDescriptorsBuilder<InputType> builder = new SortDescriptorsBuilder<InputType>();
		builder.add(propertyName, methodPrefix, ascending);
		return builder;
	}

	/**
	 * Create a <code>SortDescriptorsBuilder</code> instance and wrap a sort descriptor
	 * to sort elements based on the property name as the first sort descriptor. If the
	 * {@code isBooleanProperty} is {@code true}, the getter prefix will use <code>is</code>
	 * instead of <code>get</code>.
	 * 
	 * @param propertyName the property name to sort
	 * @param isBooleanProperty to indicate whether the property is boolean property
	 * @param ascending to sort elements ascending or descending
	 * @param <InputType> the type of the elements to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public static <InputType> SortDescriptorsBuilder<InputType> of(String propertyName, boolean isBooleanProperty, boolean ascending) {
		SortDescriptorsBuilder<InputType> builder = new SortDescriptorsBuilder<InputType>();
		builder.add(propertyName, isBooleanProperty, ascending);
		return builder;
	}

	/**
	 * Construct a <code>SortDescriptorsBuilder</code> instance.
	 */
	public SortDescriptorsBuilder() {
		_descriptors = new ArrayList<SortDescriptor<InputType>>();
	}

	/**
	 * Add the given sort descriptor.
	 * 
	 * @param descriptor the sort descriptor
	 * @return the builder to organize other sort descriptors
	 */
	public SortDescriptorsBuilder<InputType> add(SortDescriptor<InputType> descriptor) {
		_descriptors.add(descriptor);
		return this;
	}

	/**
	 * Wrap the given transformer as a sort descriptor to sort elements <em>ascending</em>.
	 * 
	 * @param transformer the transformer to be the sort descriptor
	 * @return the builder to organize other sort descriptors
	 */
	public SortDescriptorsBuilder<InputType> add(Transformer<InputType, Comparable<?>> transformer) {
		return add(transformer, true);
	}

	/**
	 * Wrap the given transformer as a first sort descriptor.
	 * 
	 * @param transformer the transformer to be the sort descriptor
	 * @param ascending to sort elements ascending or descending
	 * @return the builder to organize other sort descriptors
	 */
	public SortDescriptorsBuilder<InputType> add(Transformer<InputType, Comparable<?>> transformer, boolean ascending) {
		_descriptors.add(new SimpleSortDescriptor<InputType>(transformer, ascending));
		return this;
	}

	/**
	 * Wrap a sort descriptor to sort elements <em>ascending</em> based on the property name.
	 * 
	 * @param propertyName the property name to sort
	 * @return the builder to organize other sort descriptors
	 */
	public SortDescriptorsBuilder<InputType> add(String propertyName) {
		return add(propertyName, false, true);
	}

	/**
	 * Wrap a sort descriptor to sort elements based on the property name.
	 * 
	 * @param propertyName the property name to sort
	 * @param ascending to sort elements ascending or descending
	 * @return the builder to organize other sort descriptors
	 */
	public SortDescriptorsBuilder<InputType> add(String propertyName, boolean ascending) {
		return add(propertyName, false, ascending);
	}

	/**
	 * Wrap a sort descriptor to sort elements based on the property name. If the
	 * {@code isBooleanProperty} is {@code true}, the getter prefix will use <code>is</code>
	 * instead of <code>get</code>.
	 * 
	 * @param propertyName the property name to sort
	 * @param isBooleanProperty to indicate whether the property is boolean property
	 * @param ascending to sort elements ascending or descending
	 * @return the builder to organize other sort descriptors
	 */
	public SortDescriptorsBuilder<InputType> add(String propertyName, boolean isBooleanProperty, boolean ascending) {
		PropertySortDescriptor<InputType> descriptor = new PropertySortDescriptor<InputType>(propertyName, isBooleanProperty, ascending);
		_descriptors.add(descriptor);
		return this;
	}

	/**
	 * Wrap a sort descriptor to sort elements based on the property name as. The name of
	 * the getter method can be customized by the {@code methodPrefix}.
	 * 
	 * @param propertyName the property name to sort
	 * @param methodPrefix the customized getter method prefix
	 * @param ascending to sort elements ascending or descending
	 * @return the builder to organize other sort descriptors
	 */
	public SortDescriptorsBuilder<InputType> add(String propertyName, String methodPrefix, boolean ascending) {
		PropertySortDescriptor<InputType> descriptor = new PropertySortDescriptor<InputType>(propertyName, ascending);
		descriptor.setGetterMethodPrefix(methodPrefix);
		_descriptors.add(descriptor);
		return this;
	}

	/**
	 * Return a sorted elements from the given elements (remain unchanged) with the
	 * sort descriptors organized by the builder.
	 * 
	 * @param items the items to sort
	 * @return the sorted items
	 */
	public Collection<InputType> sort(Collection<InputType> items) {
		return SortUtils.sort(items, _descriptors);
	}

	/**
	 * Sort the given elements with the sort descriptors organized by the builder.
	 * 
	 * @param items the items to sort
	 */
	public void sort(List<InputType> items) {
		SortUtils.sort(items, _descriptors);
	}
}
