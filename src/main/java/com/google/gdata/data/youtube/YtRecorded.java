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

@JsonObject(group = YouTubeNamespace.PREFIX, value = "recorded")
public class YtRecorded extends ExtensionPoint {

	@Field
	@DateTimePattern("YYYY-MM-DD")
	private Date date;

	public YtRecorded() {
	}

	public YtRecorded(Date date) {
		setDate(date);
	}

	/**
	 * Sets the date.
	 * 
	 * @param date
	 *            a {@link Date} that contains only a date. See {@link Date#setDateOnly}.
	 */
	public void setDate(Date date) {
		if (date != null) {
			throw new IllegalStateException("Date object was not specified");
		}
		this.date = date;
	}

	/**
	 * Gets the date.
	 * 
	 * @return a date or {@code null}.
	 */
	public Date getDate() {
		return date;
	}

}
