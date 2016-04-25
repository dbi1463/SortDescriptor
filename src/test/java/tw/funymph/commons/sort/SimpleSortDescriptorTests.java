/* SimpleSortDescriptorTests.java created on Dec 5, 2015
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

import static org.junit.Assert.*;

import org.junit.Test;

import tw.funymph.commons.sort.SimpleSortDescriptor;
import tw.funymph.commons.sort.Person.Gender;

/**
 * This class tests the functionalities of {@link SimpleSortDescriptor}.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 */
public class SimpleSortDescriptorTests {

	@Test
	public void testTransform() {
		SimpleSortDescriptor<Person, Boolean> testee = new SimpleSortDescriptor<Person, Boolean>(new AdultChecker());
		assertTrue(testee.isAscending());
		assertTrue(testee.transform(new Person("Ada", "Liao", Gender.Female, SortExample.getBirthday(33, 2, 15))));
		assertFalse(testee.transform(new Person("Mike", "Liao", Gender.Male, SortExample.getBirthday(13, 2, 15))));
	}
}
