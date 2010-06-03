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
import sk.seges.acris.json.client.extension.ExtensionDescription;

import com.google.gdata.data.media.mediarss.MediaContent;
import com.google.gdata.data.media.mediarss.MediaRssNamespace;

@JsonObject(group = MediaRssNamespace.PREFIX, value = "content")
public class YouTubeMediaContent extends MediaContent {
	/** Opaque YouTube format identifier (optional) */
	@Field("format")
	private Integer youTubeFormat;

	/**
	 * Describes the tag to an {@link com.google.gdata.data.ExtensionProfile}.
	 */
	public static ExtensionDescription getDefaultDescription() {
		return ExtensionDescription.getDefaultDescription(YouTubeMediaContent.class);
	}

	/** Gets the youtube video format. */
	public Integer getYouTubeFormat() {
		return youTubeFormat;
	}

	/** Sets the youtube video format, an opaque format number at this level. */
	public void setYouTubeFormat(Integer youTubeFormat) {
		this.youTubeFormat = youTubeFormat;
	}
}
