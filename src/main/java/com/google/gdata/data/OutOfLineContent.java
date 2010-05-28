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

package com.google.gdata.data;

import com.google.gdata.util.ContentType;

/**
 * Variant of {@link Content} for entries that reference external content.
 * 
 * 
 */
public class OutOfLineContent extends Content implements IOutOfLineContent {

	/** @return the type of this content */
	@Override
	public int getType() {
		return Content.Type.MEDIA;
	}

	/** MIME Content type. */
	protected ContentType mimeType;

	/** @return the MIME content type */
	public ContentType getMimeType() {
		return mimeType;
	}

	/** Specifies the MIME Content type. */
	public void setMimeType(ContentType v) {
		mimeType = v;
	}

	/**
	 * Language derived from the current state of {@code xml:lang}.
	 */
	protected String lang;

	@Override
	public String getLang() {
		return lang;
	}

	/** Specifies the human language that this content is written in. */
	public void setLang(String v) {
		lang = v;
	}

	/**
	 * External URI.
	 */
	protected String uri;

	/** @return the external URI */
	public String getUri() {
		return uri;
	}

	/** Specifies the external URI. */
	public void setUri(String v) {
		uri = v;
	}

	/**
	 * Content length. Value will be -1 if unknown.
	 */
	protected long length;

	/** @return the content length. */
	public long getLength() {
		return length;
	}

	public void setLength(long v) {
		length = v;
	}

	/**
	 * ETag for the referenced content. Value will be {@code null} if unknown.
	 */
	protected String etag;

	public String getEtag() {
		return etag;
	}

	public void setEtag(String v) {
		etag = v;
	}

}
