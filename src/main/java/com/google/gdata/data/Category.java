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

import com.google.gwt.json.client.JSONObject;

/**
 * Category type.
 * <p>
 * For the purposes of comparison, two Category instances are considered to be identical if they have matching schemes
 * and terms. The label attributes <em>are not</em> used for the purpose of testing equality.
 * 
 * 
 */
public class Category extends JSON implements ICategory, IJSONProcessor {

	/**
	 * The character used to prefix any (optional) scheme in the compound scheme+term Category format.
	 */
	public static final char SCHEME_PREFIX = '{';

	/**
	 * The character used to suffix any (optional) scheme in the compound scheme+term Category format.
	 */
	public static final char SCHEME_SUFFIX = '}';

	public Category() {
	}

	/**
	 * Constructs a new category.
	 */
	public Category(String scheme, String term) {
		this(scheme, term, null);
	}

	/**
	 * Constructs a new category.
	 */
	public Category(String scheme, String term, String label) {
		this.scheme = scheme;
		if (term == null) {
			throw new NullPointerException("Invalid term. Cannot be null");
		}
		this.term = term;
		this.label = label;
	}

	/** Scheme (domain). */
	protected String scheme;

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String v) {
		scheme = v;
	}

	/** Term. */
	protected String term;

	public String getTerm() {
		return term;
	}

	public void setTerm(String v) {
		term = v;
	}

	/** Human-readable label. */
	protected String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String v) {
		label = v;
	}

	/** Language. */
	protected String labelLang;

	public String getLabelLang() {
		return labelLang;
	}

	public void setLabelLang(String v) {
		labelLang = v;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		if (scheme != null) {
			sb.append(SCHEME_PREFIX);
			sb.append(scheme);
			sb.append(SCHEME_SUFFIX);
		}
		// Label syntax is not in the query model, so no need to define
		// public constants for the delimiters.
		sb.append(term);
		if (label != null) {
			sb.append("(");
			sb.append(label);
			sb.append(")");
		}
		return sb.toString();
	}

	// identical scheme/term values for all user-defined labels. The label
	// attribute is being used for the user label. This seems somewhat counter
	// to Atom semantics, where the scheme is a namespace and the term
	// identifies membership in the categories in that scheme. Needs review,
	// but until this is done the label must be taken into account for
	// equals()/hashCode()
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Category)) {
			return false;
		}

		return toString().equals(obj.toString());
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + ((scheme != null) ? scheme.hashCode() : 0);
		result = 37 * result + term.hashCode();
		result = 37 * result + ((label != null) ? label.hashCode() : 0);
		return result;
	}

	private static final String TERM_ATTRIBUTE = "term";
	private static final String SCHEME_ATTRIBUTE = "scheme";
	private static final String LABEL_ATTRIBUTE = "label";
	
	@Override
	public void get(JSONObject jsonObject) {
		term = getString(jsonObject, TERM_ATTRIBUTE);
	}
}