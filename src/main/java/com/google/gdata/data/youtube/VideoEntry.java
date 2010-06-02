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

import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionProfile;

import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.IEntry;
import com.google.gdata.data.Link;
import com.google.gdata.data.extensions.Comments;
import com.google.gdata.data.extensions.Rating;
import com.google.gdata.data.geo.impl.GeoRssWhere;
import com.google.gdata.data.geo.impl.GmlPoint;
import com.google.gdata.data.geo.impl.GmlPos;
import com.google.gdata.data.media.MediaEntry;
import com.google.gdata.data.media.mediarss.MediaRssNamespace;
import com.google.gdata.util.Namespaces;

/**
 * Video entry for the youtube feeds.
 * 
 * 
 */
@JsonObject(group=YouTubeNamespace.PREFIX, value="video")
public class VideoEntry extends MediaEntry implements IEntry {

	/**
	 * Creates an empty video entry.
	 */
	public VideoEntry() {
	}

	/** Creates a copy of another entry. */
	public VideoEntry(BaseEntry original) {
		super(original);
	}

	/**
	 * Creates a new video entry and initializes it.
	 * 
	 * ` * @param id entry atom/rss id
	 */
	public VideoEntry(String id) {
		this();
		setId(id);
	}

	  @Override
	  public void declareExtensions(ExtensionProfile extProfile) {

	    extProfile.declare(VideoEntry.class, Comments.getDefaultDescription());
	    extProfile.declare(VideoEntry.class, Rating.getDefaultDescription(false));
//	    extProfile.declareAdditionalNamespace(Namespaces.gNs);
//	    extProfile.declareAdditionalNamespace(YouTubeNamespace.NS);

	    extProfile.declare(VideoEntry.class, YtRacy.class);
	    extProfile.declare(VideoEntry.class, YtRecorded.class);
	    extProfile.declare(VideoEntry.class, YtStatistics.class);
	    extProfile.declare(VideoEntry.class, YtNoEmbed.class);
	    extProfile.declare(VideoEntry.class, YtLocation.class);

	    extProfile.declare(VideoEntry.class, YouTubeMediaGroup.class);
	    new YouTubeMediaGroup().declareExtensions(extProfile);
	    extProfile.declareAdditionalNamespace(MediaRssNamespace.NS);

	    extProfile.declare(VideoEntry.class, GeoRssWhere.getDefaultDescription(false));
	    extProfile.declareAdditionalNamespace(
	        com.google.gdata.data.geo.Namespaces.GEO_RSS_NAMESPACE);
	    
	    extProfile.declare(GeoRssWhere.class, GmlPoint.getDefaultDescription(false));
	    extProfile.declare(GmlPoint.class, GmlPos.getDefaultDescription(false));
	    extProfile.declareAdditionalNamespace(
	        com.google.gdata.data.geo.Namespaces.GML_NAMESPACE);

	    extProfile.declare(Link.class, YtToken.class);
	    
	    // Ignore unsupported XML tags instead of rejecting them.
	    // Very useful in a client.
	    extProfile.declareArbitraryXmlExtension(VideoEntry.class);
	  }
}