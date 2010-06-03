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

package com.google.gdata.data.youtube;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionDescription;

import com.google.gdata.data.media.mediarss.MediaRating;
import com.google.gdata.data.media.mediarss.MediaRssNamespace;

@JsonObject(group = MediaRssNamespace.PREFIX, value = "rating")
public class YouTubeMediaRating extends MediaRating {

	/**
	 * State the 'country' attribute is in.
	 */
	private enum CountryState {
		/** The attribute is unset. */
		UNSET,
		/** The attribute is set to {@code all}. */
		ALL,
		/** A country set is available in {@link #countries}. */
		COUNTRIES
	}

	/**
	 * Unmodifiable set of countries, never null.
	 */
	@Field("country")
	private Set<String> countries = Collections.emptySet();

	private CountryState countryState = CountryState.UNSET;

	/**
	 * Describes the tag to an com.google.gdata.data.ExtensionProfile.
	 */
	public static ExtensionDescription getDefaultDescription() {
		return ExtensionDescription.getDefaultDescription(YouTubeMediaRating.class);
	}

	/**
	 * Checks whether a country set is set.
	 * 
	 * @return {@code true} if a country set is set, false if the rating applies to all countries
	 */
	public boolean hasCountries() {
		return countryState == CountryState.COUNTRIES;
	}

	/**
	 * Explicitely sets the attribute {@code country} to {@code all}.
	 */
	public void setAllCountries() {
		countryState = CountryState.ALL;
		countries = Collections.emptySet();
	}

	/**
	 * Clears the attribute {@code country} of any value.
	 */
	public void clearCountry() {
		countryState = CountryState.UNSET;
		countries = Collections.emptySet();
	}

	/**
	 * Defines the countries to which the rating applies.
	 * 
	 * @param countries
	 *            2-letter country code set or {@code null} to revert to the default value
	 */
	public void setCountries(Collection<String> countries) {
		if (countries == null || countries.isEmpty()) {
			clearCountry();
		} else {
			this.countryState = CountryState.COUNTRIES;
			LinkedHashSet<String> set = new LinkedHashSet<String>();
			for (String country : countries) {
				set.add(country);
			}
			this.countries = Collections.unmodifiableSet(set);
		}
	}

	/**
	 * Gets the country set.
	 * 
	 * @return country set, which may be empty but not {@code null}
	 */
	public Set<String> getCountries() {
		return countries;
	}
}