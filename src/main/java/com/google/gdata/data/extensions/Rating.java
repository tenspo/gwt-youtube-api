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

package com.google.gdata.data.extensions;

import sk.seges.acris.json.client.extension.ExtensionDescription;
import sk.seges.acris.json.client.extension.ExtensionPoint;

import com.google.gdata.util.Namespaces;

public class Rating extends ExtensionPoint {

	/**
	 * Rating type. Describes the meaning of this rating.
	 */
	public static final class Rel {
		/** Overall rating. Higher values mean better ratings. */
		public static final String OVERALL = null;
		/** Quality rating. Higher values mean better quality. */
		public static final String QUALITY = Namespaces.gPrefix + "quality";
		/** Price rating. Higher values mean higher prices. */
		public static final String PRICE = Namespaces.gPrefix + "price";
	}

	/**
	 * Constructs an empty {@code Rating}.
	 */
	public Rating() {
		this(null);
	}

	/**
	 * Constructs a {@code Rating} class with a specified rating.
	 * 
	 * @param rating
	 *            the value of the rating
	 */
	public Rating(Integer rating) {
		this.rating = rating;
	}

	/** Rating type. */
	protected String rel;

	public String getRel() {
		return rel;
	}

	public void setRel(String v) {
		rel = v;
	}

	/** Rating value (required). */
	protected Integer rating;

	public Integer getValue() {
		return rating;
	}

	public void setValue(Integer r) {
		rating = r;
	}

	/** Minimum rating value on rating scale (optional). */
	protected Integer min;

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer r) {
		min = r;
	}

	/** Maximum rating value on rating scale (optional). */
	protected Integer max;

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer r) {
		max = r;
	}

	/** Number of ratings that was taken into account (for average ratings). */
	protected Integer numRaters;

	public Integer getNumRaters() {
		return numRaters;
	}

	public void setNumRaters(Integer r) {
		numRaters = r;
	}

	/** Average ratings, output with a fixed a 2 digit precision. */
	protected Float average;

	public Float getAverage() {
		return average;
	}

	public void setAverage(Float r) {
		average = r;
	}

	/**
	 * Returns the suggested extension description with configurable repeatability.
	 */
	public static ExtensionDescription getDefaultDescription(boolean repeatable) {
		ExtensionDescription desc = new ExtensionDescription();
		desc.setExtensionClass(Rating.class);
		desc.setPointName(Namespaces.gNs + "$rating");
		desc.setRepeatable(repeatable);
		return desc;
	}

	/** Returns the suggested extension description and is repeatable. */
	public static ExtensionDescription getDefaultDescription() {
		return getDefaultDescription(true);
	}

}
