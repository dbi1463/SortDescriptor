/* SimpleSortDescriptor.java created on Jan 26, 2015
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
package tw.funymph.commons.sort;

/**
 * This class simply wraps a {@link Transformer} instance as a sort descriptor.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 */
public class SimpleSortDescriptor<InputType, T extends Comparable<T>> extends AbstractSortDescriptor<InputType> {

	private Transformer<InputType, T> _transformer;

	/**
	 * Construct a <code>SimpleSortDescriptor</code> instance with the transformer.
	 * 
	 * @param transformer the transformer to wrap
	 */
	public SimpleSortDescriptor(Transformer<InputType, T> transformer) {
		this(transformer, true);
	}

	/**
	 * Construct a <code>SimpleSortDescriptor</code> instance with the transformer
	 * and sorting ordering.
	 * 
	 * @param transformer the transformer to wrap
	 * @param ascending the sorting ordering
	 */
	public SimpleSortDescriptor(Transformer<InputType, T> transformer, boolean ascending) {
		super(ascending);
		_transformer = transformer;
	}

	@Override
	public T transform(InputType input) {
		return _transformer.transform(input);
	}
}
