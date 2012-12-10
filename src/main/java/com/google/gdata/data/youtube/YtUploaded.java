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
public class YtUploaded extends ExtensionPoint {
	
	@Field("$t")
	@DateTimePattern(Source.DATE_TIME_PATTERN)
	private Date Date;

	public YtUploaded() {
	}

	public YtUploaded(Date Date) {
		this.Date = Date;
	}

	/**
	 * Returns the currently set uploaded date time or null if none set.
	 */
	public Date getDateTime() {
		return Date;
	}

	/**
	 * Changes the uploaded date time.
	 */
	public void setDateTime(Date Date) {
		this.Date = Date;
	}

}
