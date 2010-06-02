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
import sk.seges.acris.json.client.extension.ExtensionPoint;

public abstract class AbstractFreeTextExtension extends ExtensionPoint {

	@Field
	private String content;

	/** Creates an empty tag. */
	protected AbstractFreeTextExtension() {
	}

	/**
	 * Creates a tag and initializes its content.
	 * 
	 * @param content
	 */
	protected AbstractFreeTextExtension(String content) {
		this.content = content;
	}

	/** Gets the content string. */
	public String getContent() {
		return content;
	}

	/** Sets the content string. */
	public void setContent(String content) {
		this.content = content;
	}

}
