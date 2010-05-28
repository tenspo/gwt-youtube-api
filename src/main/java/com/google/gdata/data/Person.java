/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gdata.data;

/**
 * Person type used for feed and entry authors and contributors. It may also be used by services' custom elements.
 * 
 * 
 */
public class Person implements IPerson {

	/**
	 * Class constructor.
	 */
	public Person() {
	}

	/**
	 * Constructs a new Person instance with the specified name.
	 */
	public Person(String name) {
		if (name == null)
			throw new NullPointerException("Name must have a value");
		this.name = name;
	}

	/**
	 * Constructs a new Person instance with the specified name, URI, and email address.
	 */
	public Person(String name, String uri, String email) {
		this(name);
		this.uri = uri;
		this.email = email;
	}

	/** Human-readable name. */
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String v) {
		name = v;
	}

	/** Language of name. Derived from the current state of {@code xml:lang}. */
	protected String nameLang;

	public String getNameLang() {
		return nameLang;
	}

	public void setNameLang(String v) {
		nameLang = v;
	}

	/** URI associated with the person. */
	protected String uri;

	public String getUri() {
		return uri;
	}

	public void setUri(String v) {
		uri = v;
	}

	/** Email address. */
	protected String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String v) {
		email = v;
	}
}