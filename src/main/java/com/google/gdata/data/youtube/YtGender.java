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

import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionPoint;

@JsonObject(group = YouTubeNamespace.PREFIX, value = "gender")
public class YtGender extends ExtensionPoint {

	/** Gender Value. */
	public static enum Value {
		MALE("m"), FEMALE("f");

		private String id;

		private Value(String id) {
			this.id = id;
		}

		/** Returns a one-letter identifier. */
		public String getId() {
			return id;
		}

		/**
		 * Returns the value corresponding to an id.
		 * 
		 * @param id
		 *            identifier, 'm' or 'f'
		 * @return a value or null
		 */
		public static Value fromId(String id) {
			for (Value gender : values()) {
				if (gender.getId().equals(id)) {
					return gender;
				}
			}
			return null;
		}
	}

	private Value gender;

	/** Creates an empty tag. */
	public YtGender() {
	}

	/**
	 * Creates a tag and initializes its content.
	 * 
	 * @param gender
	 *            {@link Value#FEMALE}, {@link Value#MALE} or {@code null}
	 */
	public YtGender(Value gender) {
		this.gender = gender;
	}

	/**
	 * Gets the gender.
	 * 
	 * @return {@link Value#FEMALE}, {@link Value#MALE} or {@code null}
	 */
	public Value getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 * 
	 * @param gender
	 *            {@link Value#FEMALE}, {@link Value#MALE} or {@code null}
	 */
	public void setGender(Value gender) {
		this.gender = gender;
	}
}