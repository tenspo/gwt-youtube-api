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

package com.google.gdata.data.extensions;

import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionDescription;

import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.Link;
import com.google.gdata.util.ContentType;
import com.google.gdata.util.Namespaces;

/**
 * The FeedLink class defines the object model for a link entity that refers to a GData feed. The feed content may be
 * included inline via child elements of the feed link or only included by reference.
 * 
 * @param <F>
 *            Nested feed type.
 */
@JsonObject(group = Namespaces.gAlias, value = "feedLink")
public class FeedLink<F extends BaseFeed<?>> extends Link {

	/**
	 * Constructs a feed link that points to a {@link Feed}.
	 */
	@SuppressWarnings("unchecked")
	public FeedLink() {
		this((Class<F>) Feed.class);
	}

	/**
	 * Constructs a feed link that points to the given feed type.
	 * 
	 * @param feedClass
	 *            Feed class.
	 */
	public FeedLink(Class<F> feedClass) {
		this.feedClass = feedClass;
	}

	/** Read only flag. */
	protected boolean readOnly = false;

	public boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean v) {
		readOnly = v;
	}

	/** Count hint. */
	protected Integer countHint;

	public Integer getCountHint() {
		return countHint;
	}

	public void setCountHint(Integer v) {
		countHint = v;
	}

	/** Nested feed (optional). */
	protected BaseFeed<?> feed;

	@SuppressWarnings("unchecked")
	public F getFeed() {
		return (F) feed;
	}

	public void setFeed(F v) {
		feed = v;
	}

	/** Nested feed class. */
	protected final Class<F> feedClass;

	public Class<F> getFeedClass() {
		return feedClass;
	}

	/** Returns the suggested extension description. */
	public static ExtensionDescription getDefaultDescription() {
		return ExtensionDescription.getDefaultDescription(FeedLink.class);
	}

	@Override
	public String getType() {
		return ContentType.getAtomFeed().toString();
	}

}
