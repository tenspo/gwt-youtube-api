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

package com.google.gdata.data.media.mediarss;

import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;

@JsonObject(group = MediaRssNamespace.PREFIX, value = "credit")
public class MediaCredit extends AbstractElementWithContent {

	/**
	 * Default scheme, as defined on http://search.yahoo.com/mrss.
	 * 
	 * European Broadcasting Union codes: http://www.ebu.ch/en/technical/metadata/specifications/role_codes.php
	 */
	public static final String DEFAULT_SCHEME = "urn:ebu";

	@Field
	private String role;

	@Field
	private String scheme;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
}
