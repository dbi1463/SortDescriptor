/* SortDescriptors.java created on Feb 1, 2015
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
public class SortDescriptors<InputType> {

	private List<SortDescriptor<InputType>> _descriptors;

	/**
	 * Create a <code>SortDescriptorsBuilder</code> instance and wrap the given
	 * sort descriptor as the first descriptor.
	 * 
	 * @param descriptor the first sort descriptor
	 * @param <InputType> the type of the elements to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public static <InputType> SortDescriptors<InputType> startWith(SortDescriptor<InputType> descriptor) {
		SortDescriptors<InputType> builder = new SortDescriptors<InputType>();
		builder.thenWith(descriptor);
		return builder;
	}

	/**
	 * Create a <code>SortDescriptorsBuilder</code> instance and wrap the given
	 * transformer as the first sort descriptor to sort elements <em>ascending</em>.
	 * 
	 * @param transformer the transformer to be the first sort descriptor
	 * @param <InputType> the type of the elements to be sorted
	 * @param <T> the type of the input's property to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public static <InputType, T extends Comparable<T>> SortDescriptors<InputType> startWith(Transformer<InputType, T> transformer) {
		return startWith(transformer, true);
	}

	/**
	 * Create a <code>SortDescriptorsBuilder</code> instance and wrap the given
	 * transformer as the first sort descriptor.
	 * 
	 * @param transformer the transformer to be the first sort descriptor
	 * @param ascending to sort elements ascending or descending
	 * @param <InputType> the type of the elements to be sorted
	 * @param <T> the type of the input's property to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public static <InputType, T extends Comparable<T>> SortDescriptors<InputType> startWith(Transformer<InputType, T> transformer, boolean ascending) {
		SortDescriptors<InputType> builder = new SortDescriptors<InputType>();
		builder.thenWith(transformer, ascending);
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
	public static <InputType> SortDescriptors<InputType> startWith(String propertyName) {
		return startWith(propertyName, true);
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
	public static <InputType> SortDescriptors<InputType> startWith(String propertyName, boolean ascending) {
		return startWith(propertyName, false, ascending);
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
	public static <InputType> SortDescriptors<InputType> startWith(String propertyName, String methodPrefix, boolean ascending) {
		SortDescriptors<InputType> builder = new SortDescriptors<InputType>();
		builder.thenWith(propertyName, methodPrefix, ascending);
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
	public static <InputType> SortDescriptors<InputType> startWith(String propertyName, boolean isBooleanProperty, boolean ascending) {
		SortDescriptors<InputType> builder = new SortDescriptors<InputType>();
		builder.thenWith(propertyName, isBooleanProperty, ascending);
		return builder;
	}

	/**
	 * Construct a <code>SortDescriptorsBuilder</code> instance.
	 */
	private SortDescriptors() {
		_descriptors = new ArrayList<SortDescriptor<InputType>>();
	}

	/**
	 * Add the given sort descriptor.
	 * 
	 * @param descriptor the sort descriptor
	 * @return the builder to organize other sort descriptors
	 */
	public SortDescriptors<InputType> thenWith(SortDescriptor<InputType> descriptor) {
		_descriptors.add(descriptor);
		return this;
	}

	/**
	 * Wrap the given transformer as a sort descriptor to sort elements <em>ascending</em>.
	 * 
	 * @param transformer the transformer to be the sort descriptor
	 * @param <T> the type of the input's property to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public <T extends Comparable<T>> SortDescriptors<InputType> thenWith(Transformer<InputType, T> transformer) {
		return thenWith(transformer, true);
	}

	/**
	 * Wrap the given transformer as a first sort descriptor.
	 * 
	 * @param transformer the transformer to be the sort descriptor
	 * @param ascending to sort elements ascending or descending
	 * @param <T> the type of the input's property to be sorted
	 * @return the builder to organize other sort descriptors
	 */
	public <T extends Comparable<T>> SortDescriptors<InputType> thenWith(Transformer<InputType, T> transformer, boolean ascending) {
		_descriptors.add(new SimpleSortDescriptor<InputType, T>(transformer, ascending));
		return this;
	}

	/**
	 * Wrap a sort descriptor to sort elements <em>ascending</em> based on the property name.
	 * 
	 * @param propertyName the property name to sort
	 * @return the builder to organize other sort descriptors
	 */
	public SortDescriptors<InputType> thenWith(String propertyName) {
		return thenWith(propertyName, false, true);
	}

	/**
	 * Wrap a sort descriptor to sort elements based on the property name.
	 * 
	 * @param propertyName the property name to sort
	 * @param ascending to sort elements ascending or descending
	 * @return the builder to organize other sort descriptors
	 */
	public SortDescriptors<InputType> thenWith(String propertyName, boolean ascending) {
		return thenWith(propertyName, false, ascending);
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
	public SortDescriptors<InputType> thenWith(String propertyName, boolean isBooleanProperty, boolean ascending) {
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
	public SortDescriptors<InputType> thenWith(String propertyName, String methodPrefix, boolean ascending) {
		PropertySortDescriptor<InputType> descriptor = new PropertySortDescriptor<InputType>(propertyName);
		descriptor.setAscending(ascending);
		descriptor.setGetterMethodPrefix(methodPrefix);
		_descriptors.add(descriptor);
		return this;
	}

	/**
	 * Return a sorted elements from the given elements (remain unchanged) with the
	 * sort descriptors.
	 * 
	 * @param items the items to sort
	 * @return the sorted items
	 */
	public List<InputType> sortedList(Collection<InputType> items) {
		return SortUtils.sortedList(items, _descriptors);
	}

	/**
	 * Sorted the given elements with the sort descriptors.
	 * 
	 * @param items the items to sort
	 */
	public void sort(List<InputType> items) {
		SortUtils.sort(items, _descriptors);
	}

	/**
	 * 
	 * @return
	 */
	List<SortDescriptor<InputType>> getDescriptors() {
		return _descriptors;
	}
}
