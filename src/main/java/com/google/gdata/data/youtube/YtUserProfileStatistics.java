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

import java.util.Date;

import sk.seges.acris.json.client.annotation.DateTimePattern;
import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionPoint;

import com.google.gdata.data.Source;

@JsonObject(group = YouTubeNamespace.PREFIX, value = "statistics")
public class YtUserProfileStatistics extends ExtensionPoint {
	/**
	 * How many time the users profile has been viewed? On the web site this field is called "channel views".
	 */
	@Field
	private long viewCount;
	
	@Field
	private long videoWatchCount;
	
	@Field
	private long subscriberCount;
	
	@Field
	@DateTimePattern(Source.DATE_TIME_PATTERN)
	private Date lastWebAccess;

	/**
	 * Returns how many times this channel/profile has been viewed.
	 * 
	 * @return number of views
	 */
	public long getViewCount() {
		return viewCount;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	public long getVideoWatchCount() {
		return videoWatchCount;
	}

	public void setVideoWatchCount(long vwc) {
		videoWatchCount = vwc;
	}

	public long getSubscriberCount() {
		return subscriberCount;
	}

	public void setSubscriberCount(long sc) {
		subscriberCount = sc;
	}

	public Date getLastWebAccess() {
		return lastWebAccess;
	}

	public void setLastWebAccess(Date lastWebAccess) {
		this.lastWebAccess = lastWebAccess;
	}
}