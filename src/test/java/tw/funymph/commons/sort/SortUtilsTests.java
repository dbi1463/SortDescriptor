/* SortUtilsTests.java created on Dec 5, 2015.
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

import java.util.ArrayList;

import org.junit.Test;

import tw.funymph.commons.sort.SortDescriptor;
import tw.funymph.commons.sort.SortUtils;

/**
 * This class tests the functionalities of {@link SortUtils}.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 */
public class SortUtilsTests {

	@Test
	public void testSortWithoutItems() {
		new SortUtils();
		boolean exceptionThrown = false;
		try {
			SortUtils.sort(null, new ArrayList<SortDescriptor<Person>>());
			fail("illegal argument exception should thrown");
		}
		catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	@Test
	public void testSortWithoutDescriptors() {
		boolean exceptionThrown = false;
		try {
			SortUtils.sort(new ArrayList<Person>(), null);
			fail("illegal argument exception should thrown");
		}
		catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	@Test
	public void testSortedListWithoutItems() {
		boolean exceptionThrown = false;
		try {
			SortUtils.sortedList(null, new ArrayList<SortDescriptor<Person>>());
			fail("illegal argument exception should thrown");
		}
		catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	@Test
	public void testSortedListWithoutDescriptors() {
		boolean exceptionThrown = false;
		try {
			SortUtils.sortedList(new ArrayList<Person>(), null);
			fail("illegal argument exception should thrown");
		}
		catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
}
