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

import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionPoint;

@JsonObject(group = YouTubeNamespace.PREFIX, value = "aspectRatio")
public class YtAspectRatio extends ExtensionPoint {

	public enum Value {
		WIDE_SCREEN("widescreen");

		private final String xmlName;

		private Value(String xmlName) {
			this.xmlName = xmlName;
		}

		public String getXmlName() {
			return xmlName;
		}
	}

	@Field
	private Value value;

	public YtAspectRatio() {
		value = null;
	}

	/**
	 * Creates a tag and sets the aspect ratio.
	 * 
	 * @param value
	 *            the aspect ratio
	 */
	public YtAspectRatio(Value value) {
		this.value = value;
	}

	/** Sets the aspect ratio. */
	public void setValue(Value value) {
		this.value = value;
	}

	/** Gets the aspect ratio. */
	public Value getValue() {
		return value;
	}

}
