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

@JsonObject(group = MediaRssNamespace.PREFIX, value = "text")
public class MediaText extends AbstractTextElement {

	@Field
	private NormalPlayTime start;
	
	@Field
	private NormalPlayTime end;
	
	@Field
	private String lang;

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public NormalPlayTime getEnd() {
		return end;
	}

	public void setEnd(NormalPlayTime end) {
		this.end = end;
	}

	public NormalPlayTime getStart() {
		return start;
	}

	public void setStart(NormalPlayTime start) {
		this.start = start;
	}
}
