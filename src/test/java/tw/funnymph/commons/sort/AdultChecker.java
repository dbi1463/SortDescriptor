/* AdultChecker.java created on Feb 1, 2015
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
 * This class check whether the given person is an adult or not.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 */
public class AdultChecker implements Transformer<Person, Boolean> {

	public static final int DEFAULT_ADULT_AGE = 18;

	private int _adultAge;

	/**
	 * Construct <code>AdultTransformer</code> instance.
	 */
	public AdultChecker() {
		this(DEFAULT_ADULT_AGE);
	}

	/**
	 * Construct <code>AdultTransformer</code> instance with the minimum
	 * age to become an adult.
	 */
	public AdultChecker(int age) {
		_adultAge = age;
	}

	@Override
	public Boolean transform(Person input) {
		return Boolean.valueOf(input.getAge() >= _adultAge);
	}
}
