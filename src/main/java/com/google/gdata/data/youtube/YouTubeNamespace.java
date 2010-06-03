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

public class YouTubeNamespace {

	/** Standard namespace prefix. */
	public static final String PREFIX = "yt";

	/** Namespace URI */
	public static final String URI = "http://gdata.youtube.com/schemas/2007";

	/**
	 * Scheme used for atom:categories and media:categories.
	 */
	public static final String CATEGORY_SCHEME = URI + "/categories.cat";

	/**
	 * Custom {@code media:credit} scheme. This overrides the default value of {@code urn:ebu} for {@code media:credit}.
	 */
	public static final String CREDIT_SCHEME = "urn:youtube";

	/**
	 * Uploader role inside {@link #CREDIT_SCHEME} for {@code media:credit}.
	 */
	public static final String CREDIT_UPLOADER_ROLE = "uploader";

	/**
	 * Link Rel value for video responses links.
	 */
	public static final String RESPONSES_REL = URI + "#video.responses";

	/**
	 * Link Rel value for video ratings links.
	 */
	public static final String RATINGS_REL = URI + "#video.ratings";

	/**
	 * Link Rel value for video comments links.
	 */
	public static final String COMMENTS_REL = URI + "#comments";

	/**
	 * Link Rel value for video caption track links.
	 */
	public static final String CAPTION_TRACKS_REL = URI + "#video.captionTracks";

	/**
	 * Link Rel value for video complaints links.
	 */
	public static final String COMPLAINTS_REL = URI + "#video.complaints";

	/**
	 * Link Rel value for related videos links.
	 */
	public static final String RELATED_REL = URI + "#video.related";
}