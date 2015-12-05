/* SortExample.java created on Dec 3, 2015.
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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import tw.funnymph.commons.sort.Person.Gender;

/**
 * This test case provides a demonstration of the usage of sort descriptor.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 */
public class SortExample {

	@Test
	public void testExample() {
		List<Person> persons = new ArrayList<Person>();
		// getBirthday(age, month, day)
		persons.add(new Person("Joe", "Lai", Gender.Male, getBirthday(13, 1, 3)));
		persons.add(new Person("Jessica", "Lee", Gender.Female, getBirthday(13, 11, 23)));
		persons.add(new Person("Mike", "Cheng", Gender.Male, getBirthday(18, 9, 3)));
		persons.add(new Person("Richard", "Wang", Gender.Male, getBirthday(16, 7, 13)));
		persons.add(new Person("Cathy", "Feng", Gender.Female, getBirthday(21, 5, 9)));
		persons.add(new Person("Bill", "Lin", Gender.Male, getBirthday(26, 3, 22)));
		persons.add(new Person("Zoe", "Kuan", Gender.Female, getBirthday(34, 4, 30)));

		// sort the persons by whether the person is adult or not, the gender, and the first name
		List<Person> result = SortDescriptors
			.startWith(new AdultChecker(), true)
//			.of((Person p) -> { return p.getAge() >= 18; }, true) // available on Java 8 only
			.thenWith("gender", false)
			.thenWith("firstName", true)
			.sortedList(persons);

		// The following are not adult
		assertEquals("Jessica Lee", result.get(0).getFullName());
		assertEquals("Joe Lai", result.get(1).getFullName());
		assertEquals("Richard Wang", result.get(2).getFullName());

		// The following are adult
		assertEquals("Cathy Feng", result.get(3).getFullName());
		assertEquals("Zoe Kuan", result.get(4).getFullName());
		assertEquals("Bill Lin", result.get(5).getFullName());
		assertEquals("Mike Cheng", result.get(6).getFullName());
	}

	/**
	 * Get the birthday with the expected age on the given date.
	 * 
	 * @param age the expected age
	 * @param month the month; starting from 1 (January) to 12 (December)
	 * @param day the day of month
	 * @return the dirthday
	 */
	public static Date getBirthday(int age, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR) - age, month - 1, day);
		return calendar.getTime();
	}
}
