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

package com.google.gdata.data.media;

import com.google.gdata.client.Service;
import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.BaseFeed;

/**
 * The MediaFeed class extends {@link BaseFeed} to add media-related operations for feeds that contain media content.
 * 
 * @param <F>
 *            the feed class associated with the bound subtype.
 * @param <E>
 *            the entry class associated with the bound subtype.
 * 
 */
public abstract class MediaFeed<F extends BaseFeed<E>, E extends BaseEntry> extends BaseFeed<E> {

	protected MediaFeed(Class<? extends E> entryClass) {
		super(entryClass);
	}

	protected MediaFeed(Class<? extends E> entryClass, BaseFeed<E> sourceFeed) {
		super(entryClass, sourceFeed);
	}

	@Override
	public void setService(Service v) {
		if (!(v instanceof MediaService)) {
			throw new IllegalArgumentException("Service does not support media");
		}
		super.setService(v);
	}
}