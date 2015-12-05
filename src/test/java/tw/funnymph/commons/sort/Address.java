/* Address.java created on Dec 5, 2015
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
 * This class is a data class for recording an address.
 * 
 * @author Pin-Ying Tu
 * @version 1.0
 * @since 1.0
 */
public class Address implements Comparable<Address> {

	private String _country;
	private String _city;
	private String _street;

	/**
	 * Construct a <code>Address</code> instance with the country, city,
	 * and the street information.
	 * 
	 * @param country the country part of an address
	 * @param city the city part of an address
	 * @param street the street part of an address
	 */
	public Address(String country, String city, String street) {
		_country = country;
		_city = city;
		_street = street;
	}

	/**
	 * Get the country.
	 * 
	 * @return the country
	 */
	public String getCountry() {
		return _country;
	}

	/**
	 * Get the city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return _city;
	}

	/**
	 * Get the street.
	 * 
	 * @return the street
	 */
	public String getStreet() {
		return _street;
	}

	/**
	 * Get the full description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return String.format("%1$s, %2$s, %3$s", _street, _city, _country);
	}

	@Override
	public int compareTo(Address o) {
		return getDescription().compareTo(o.getDescription());
	}
}
