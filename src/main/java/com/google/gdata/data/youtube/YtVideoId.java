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

@JsonObject(group = YouTubeNamespace.PREFIX, value = "videoid")
public class YtVideoId extends ExtensionPoint {
	
	@Field("$t")
	private String videoId;

	/** Creates an empty video ID tag. */
	public YtVideoId() {

	}

	/** Creates and initializes a video ID tag. */
	public YtVideoId(String videoId) {
		this.videoId = videoId;
	}

	/** Gets video ID. */
	public String getVideoId() {
		return videoId;
	}

	/** Sets video ID. */
	public void setVideoId(String id) {
		this.videoId = id;
	}
}