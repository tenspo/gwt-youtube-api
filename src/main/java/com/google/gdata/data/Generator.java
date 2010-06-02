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

import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;

/**
 * Atom generator type.
 * 
 * 
 */
@JsonObject
public class Generator implements IGenerator {

	/** Version. */
	@Field
	protected String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String v) {
		version = v;
	}

	/** URI. */
	@Field
	protected String uri;

	public String getUri() {
		return uri;
	}

	public String getHref() {
		return uri;
	}

	public void setUri(String v) {
		uri = v;
	}

	/** Generator name. */
	@Field("$t")
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String v) {
		name = v;
	}
}