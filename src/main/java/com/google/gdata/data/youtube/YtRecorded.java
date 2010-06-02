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

import org.gwttime.time.DateTime;

import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionPoint;

@JsonObject(group = YouTubeNamespace.PREFIX, value = "recorded")
public class YtRecorded extends ExtensionPoint {
	private DateTime date;

	public YtRecorded() {
	}

	public YtRecorded(DateTime date) {
		setDate(date);
	}

	/**
	 * Sets the date.
	 * 
	 * @param date
	 *            a {@link DateTime} that contains only a date. See {@link DateTime#setDateOnly}.
	 */
	public void setDate(DateTime date) {
		if (date != null && !date.isDateOnly()) {
			throw new IllegalStateException("Object should be only a date, not a date and a time");
		}
		this.date = date;
	}

	/**
	 * Gets the date.
	 * 
	 * @return a date or {@code null}.
	 */
	public DateTime getDate() {
		return date;
	}

}
