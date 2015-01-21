/* AbstractSortDescriptor.java created on Jan 21, 2015
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

/**
 * This abstract class provides the common implementation of {@link SortDescriptor}.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractSortDescriptor<InputType> implements SortDescriptor<InputType> {

	private boolean _ascending;

	/**
	 * Constructs a <code>AbstractSortDescriptor</code> instance with the default
	 * ascending setting: {@code true}.
	 */
	protected AbstractSortDescriptor() {
		this(true);
	}

	/**
	 * Constructs a <code>AbstractSortDescriptor</code> instance with the given
	 * ascending setting.
	 * 
	 * @param ascending the ascending setting
	 */
	protected AbstractSortDescriptor(boolean ascending) {
		setAscending(ascending);
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	/**
	 * Sets the order to sort elements.
	 * 
	 * @param ascending true to sort elements ascending
	 */
	public void setAscending(boolean ascending) {
		_ascending = ascending;
	}
}
