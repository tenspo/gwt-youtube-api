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
import sk.seges.acris.json.client.extension.ExtensionDescription;

@JsonObject(group = MediaRssNamespace.PREFIX, value = "thumbnail")
public class MediaThumbnail extends AbstractMediaResource {

	@Field
	private NormalPlayTime time;

	/** Describes the tag to an {@link com.google.gdata.data.ExtensionProfile}. */
	public static ExtensionDescription getDefaultDescription() {
		return ExtensionDescription.getDefaultDescription(MediaThumbnail.class);
	}

	public NormalPlayTime getTime() {
		return time;
	}

	public void setTime(NormalPlayTime time) {
		this.time = time;
	}
}