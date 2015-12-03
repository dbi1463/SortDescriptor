/* Person.java created on Feb 1, 2015
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

import java.util.Calendar;
import java.util.Date;

/**
 * This class represents a person with the properties to test the
 * classes in {@link tw.funnymph.commons.sort} package.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 */
public class Person {

	public static enum Gender {
		Male,
		Female
	};

	private String _firstName;
	private String _lastName;
	private Gender _gender;
	private Date _birthday;

	/**
	 * Construct a <code>Person</code> instance with the properties.
	 * 
	 * @param firstName the first name of the person
	 * @param lastName the last name of the person
	 * @param gender the gender of the person
	 * @param birthday the birthday of the person
	 */
	public Person(String firstName, String lastName, Gender gender, Date birthday) {
		_firstName = firstName;
		_lastName = lastName;
		_gender = gender;
		_birthday = birthday;
	}

	/**
	 * Get the first name of the person
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return _firstName;
	}

	/**
	 * Get the last name of the person.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return _lastName;
	}

	/**
	 * Get the full name of the person.
	 * 
	 * @return the full name
	 */
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	/**
	 * Get the gender of the person.
	 * 
	 * @return the gender
	 */
	public Gender getGender() {
		return _gender;
	}

	/**
	 * Get the age of the person.
	 * 
	 * @return the age
	 */
	public int getAge() {
		Calendar birthday = Calendar.getInstance();  
		birthday.setTime(_birthday);  
		Calendar today = Calendar.getInstance();  
		int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);  
		if (today.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
			age--;  
		}
		else if (today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH) &&
				 today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH)) {
			age--;
		}
		return age;
	}
}
