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

@JsonObject(group = MediaRssNamespace.PREFIX, value = "content")
public class MediaContent extends AbstractMediaResource {
	@Field
	private long fileSize;
	@Field
	private String type;
	@Field
	private String medium;
	@Field
	private boolean isDefault;
	@Field
	private Expression expression;
	@Field
	private int bitrate;
	@Field
	private int framerate;
	@Field
	private int samplingrate;
	@Field
	private int channels;
	@Field
	private int duration;
	@Field
	private String language;

	/**
	 * Describes the tag to an {@link com.google.gdata.data.ExtensionProfile}.
	 * 
	 * @param repeat
	 *            if true, the description will be repeatable (MediaContent can be repeated when inside MediaGroup, but
	 *            not when inside BaseEntry.)
	 */
	public static ExtensionDescription getDefaultDescription(boolean repeat) {
		ExtensionDescription retval = ExtensionDescription.getDefaultDescription(MediaContent.class);
		retval.setRepeatable(repeat);
		return retval;
	}

	public int getBitrate() {
		return bitrate;
	}

	public void setBitrate(int bitrate) {
		this.bitrate = bitrate;
	}

	public int getChannels() {
		return channels;
	}

	public void setChannels(int channels) {
		this.channels = channels;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getFramerate() {
		return framerate;
	}

	public void setFramerate(int framerate) {
		this.framerate = framerate;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean aDefault) {
		isDefault = aDefault;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public int getSamplingrate() {
		return samplingrate;
	}

	public void setSamplingrate(int samplingrate) {
		this.samplingrate = samplingrate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/** Values for the expression attribute: sample, full and nonstop. */
	public enum Expression {
		SAMPLE, FULL, NONSTOP
	}
}
