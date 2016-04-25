SortDescriptor
================
[![License](http://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)
[![CI Status](https://travis-ci.org/dbi1463/SortDescriptor.svg?branch=master)](https://travis-ci.org/dbi1463/SortDescriptor)
[![Code coverage by codecov.io](https://codecov.io/github/dbi1463/SortDescriptor/coverage.svg?branch=master)](https://codecov.io/github/dbi1463/SortDescriptor?branch=master)

A NSSortDescriptor-like implementation on Java.

## Features
* Sort objects with multiple conditions
* Sort objects with object's persistent properties without writing comparator
* Sort objects with object's transient properties by writing transformers
* Object's persistent properties and transient properties can be used together as the sort conditions

## Donwload

Download the latest JAR [![Download](https://api.bintray.com/packages/dbi1463/maven/SortDescriptor/images/download.svg)](https://bintray.com/dbi1463/maven/SortDescriptor/_latestVersion) or grab via Maven:

```xml
<dependency>
  <groupId>tw.funymph</groupId>
  <artifactId>sort-descriptor</artifactId>
  <version>1.0.0</version>
</dependency>
```
or Gradle:

```gradle
compile 'tw.funymph:sort-descriptor:1.0.0'
```

## How to use
The `SortDescriptors` can be use to organize the sort conditions. Given a person object that has first name, last name, full name, age, and gender properties (see the Person.java in test code). For example, if we would like to sort persons first by whether the person is adult or not, than by the gender, and last by his/her first name. There is no need to write complicated comparator. With `SortDescriptors`, the following code combines three sort descriptors together. The first sort descriptor wraps a transformer (`AdultChecker`) to sort transient property from a person object. The third statement adds a property sort descriptor by just simply declares the property name in the first parameter. The second parameter is used to control the ordering: `true` for ascending or `false` for descending (default is `true` if the parameter is not provided).

```java
// Sort the persons by whether the person is adult or not, the gender, and the first name
List<Person> result = SortDescriptors
	.startWith(new AdultChecker(), true)
	.thenWith("gender", false)
	.thenWith("firstName", true)
	.sortedList(persons);
```

The `AdultChecker` is a very simple class that implements `Transformer` to return `true` for a person whose age is larger than `18`; otherwise return `false`.

```java
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
```

Of course, with Java 8, the `AdultChecker` can be replaced with single-staement lamda expression like this.

```java
List<Person> result = SortDescriptors
	.startWith((Person p) -> { return p.getAge() >= 18; }, true)
	.thenWith("gender", false)
	.thenWith("firstName", true)
	.sortedList(persons);
```

Here is the complete example, that uses `SortDescriptors` to ogranize three sort descriptors and then sorts 7 objects in expected order.

```java
// Given a lot of persons
List<Person> persons = new ArrayList<Person>();
// getBirthday(age, month, day)
persons.add(new Person("Joe", "Lai", Gender.Male, getBirthday(13, 1, 3)));
persons.add(new Person("Jessica", "Lee", Gender.Female, getBirthday(13, 11, 23)));
persons.add(new Person("Mike", "Cheng", Gender.Male, getBirthday(18, 9, 3)));
persons.add(new Person("Richard", "Wang", Gender.Male, getBirthday(16, 7, 13)));
persons.add(new Person("Cathy", "Feng", Gender.Female, getBirthday(21, 5, 9)));
persons.add(new Person("Bill", "Lin", Gender.Male, getBirthday(26, 3, 22)));
persons.add(new Person("Zoe", "Kuan", Gender.Female, getBirthday(34, 4, 30)));

// Sort the persons by whether the person is adult or not, the gender, and the first name
List<Person> result = SortDescriptors
	.startWith(new AdultChecker(), true)
	.thenWith("gender", false)
	.thenWith("firstName")
	.sortedList(persons);

// Assert the ordering
// The following are not adult, female first and then male
assertEquals("Jessica Lee", result.get(0).getFullName());
assertEquals("Joe Lai", result.get(1).getFullName());
assertEquals("Richard Wang", result.get(2).getFullName());

// The following are adult, female first and then male
assertEquals("Cathy Feng", result.get(3).getFullName());
assertEquals("Zoe Kuan", result.get(4).getFullName());
assertEquals("Bill Lin", result.get(5).getFullName());
assertEquals("Mike Cheng", result.get(6).getFullName());
```

## Maven Site
Maven site on [GitHub Pages](http://dbi1463.github.io/SortDescriptor/)

## License
The MIT License (MIT)

Copyright (c) 2015 Pin-Ying Tu <dbi1463@gmail.com>

This file is part of SortDescriptor under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
