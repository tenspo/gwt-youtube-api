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

import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.Extension;
import sk.seges.acris.json.client.extension.ExtensionDescription;
import sk.seges.acris.json.client.extension.ExtensionPoint;

import com.google.gdata.util.Namespaces;

@JsonObject
public class Comments extends ExtensionPoint implements Extension {

	/** Comments feed link. */
	@Field
	protected FeedLink<?> feedLink;

	public FeedLink<?> getFeedLink() {
		return feedLink;
	}

	public void setFeedLink(FeedLink<?> v) {
		feedLink = v;
	}

	/** Returns the suggested extension description. */
	public static ExtensionDescription getDefaultDescription() {
		ExtensionDescription desc = new ExtensionDescription();
		desc.setExtensionClass(Comments.class);
		desc.setPointName(Namespaces.gAlias + "$comments");
		desc.setRepeatable(false);
		return desc;
	}

}
