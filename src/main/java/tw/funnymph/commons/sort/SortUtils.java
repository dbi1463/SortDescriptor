/* SortUtils.java created on Jan 21, 2015
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class provides a set of methods to sort elements in Java Collection with
 * sort descriptors.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 */
public class SortUtils {

	/**
	 * Get the sorted items with the given sort descriptors. This method supports multiple sort
	 * criteria that sorts the items by the first descriptor, if there are items whose order
	 * can not be determined by the descriptor, the method uses the second descriptor in
	 * the array and so on. Each descriptor can decide to sort items ascending or descending.
	 * Note that the ordering of the items in the collection remains unchanged.
	 * 
	 * @param items the items to sort
	 * @param descriptors the sort descriptors
	 * @param <InputType> the type of the elements to be sorted
	 * @return the sort items
	 * @throws IllegalArgumentException either {@code items} is null or {@code descriptors} is null
	 */
	public static <InputType> List<InputType> sortedList(Collection<InputType> items, final List<SortDescriptor<InputType>> descriptors) {
		if (items == null || descriptors == null) {
			throw new IllegalArgumentException();
		}

		List<InputType> result = new ArrayList<InputType>(items);
		sort(result, descriptors);
		return result;
	}

	/**
	 * Sort the items with the given sort descriptors. This method supports multiple sort
	 * criteria that sorts the items by the first descriptor, if there are items whose order
	 * can not be determined by the descriptor, the method uses the second descriptor in
	 * the array and so on. Each descriptor can decide to sort items ascending or descending.
	 * 
	 * @param items the items to sort
	 * @param descriptors the sort descriptors
	 * @param <InputType> the type of the elements to be sorted
	 * @throws IllegalArgumentException either {@code items} is null or {@code descriptors} is null
	 */
	@SuppressWarnings("unchecked")
	public static <InputType> void sort(List<InputType> items, final List<SortDescriptor<InputType>> descriptors) {
		if (items == null || descriptors == null) {
			throw new IllegalArgumentException();
		}

		Collections.sort(items, new Comparator<InputType>() {

			@Override
			public int compare(InputType item1, InputType item2) {
				return compare(item1, item2, 0);
			}

			/**
			 * Compare the given two items with the descriptor specified by the index.
			 * 
			 * @param item1 the first item to compare
			 * @param item2 the second item to compare
			 * @param descriptorIndex the descriptor index in the array
			 * @return the compare result
			 */
			@SuppressWarnings({ "rawtypes" })
			private int compare(InputType item1, InputType item2, int descriptorIndex) {
				SortDescriptor<InputType> descriptor = descriptors.get(descriptorIndex);
				Comparable key1 = descriptor.transform(item1);
				Comparable key2 = descriptor.transform(item2);
				int result = 0;
				if (key1 != null && key2 == null) {
					return descriptor.isAscending()? 1 : -1;
				}
				if (key1 == null && key2 != null) {
					return descriptor.isAscending()? -1 : 1;
				}
				if (key1 != null && key2 != null) {
					result = descriptor.isAscending()? key1.compareTo(key2) : key2.compareTo(key1);
				}
				if (result == 0 && hasNextDescriptor(descriptorIndex)) {
					return compare(item1, item2, descriptorIndex + 1);
				}
				return result;
			}

			/**
			 * Check whether the given index has the next descriptor.
			 * 
			 * @param index the current descriptor index
			 * @return true if has the next descriptor
			 */
			private boolean hasNextDescriptor(int index) {
				return (index + 1) < descriptors.size();
			}
		});
	}
}
