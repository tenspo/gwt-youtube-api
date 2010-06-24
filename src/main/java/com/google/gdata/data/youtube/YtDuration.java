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

/**
 * Duration class.
 *
 * This completes the duration field in
 * {@link com.google.gdata.data.media.mediarss.MediaContent} and is
 * useful when there is no MediaContent.
 *
 * This tag should go inside MediaGroup, next to {@code media:player}
 *
 * 
 */
@JsonObject(group = YouTubeNamespace.PREFIX, value = "duration")
public class YtDuration extends ExtensionPoint {
	
	@Field
	private long seconds;

	/** Creates an empty duration tag. */
	public YtDuration() {

	}

	/** Creates and initializes a duration tag. */
	public YtDuration(long seconds) {
		this.seconds = seconds;
	}

	/** Gets duration in seconds. */
	public long getSeconds() {
		return seconds;
	}

	/** Sets duration in seconds. */
	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}
}