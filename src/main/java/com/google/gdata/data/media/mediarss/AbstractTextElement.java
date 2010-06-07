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
import sk.seges.acris.json.client.extension.Extension;

import com.google.gdata.data.HtmlTextConstruct;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.TextConstruct;

/**
 * A media element with a 'type' attribute and text content.
 * 
 * 
 */
public abstract class AbstractTextElement implements Extension {

	@Field("$t")
	private TextConstruct content;

	public void setContent(TextConstruct content) {
		this.content = content;
	}

	public boolean isEmpty() {
		return content.isEmpty();
	}

	public TextConstruct getContent() {
		return content;
	}

	public void setHtmlContent(String html) {
		this.content = new HtmlTextConstruct(html);
	}

	public void setPlainTextContent(String text) {
		this.content = new PlainTextConstruct(text);
	}

	public String getPlainTextContent() {
		if (content == null) {
			return null;
		}
		return content.getPlainText();
	}

}
