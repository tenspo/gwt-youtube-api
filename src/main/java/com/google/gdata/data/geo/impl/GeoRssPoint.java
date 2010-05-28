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

package com.google.gdata.data.geo.impl;

import com.google.gdata.data.geo.Point;

/**
 * Extension for a Geo RSS georss:point element. It contains the getter/setter
 * methods for specifying the longitude and latitude of a geo-coordinate. Please
 * see the Geo RSS Simple document at <a
 * href="http://www.georss.org/simple.html">simple georss</a> for more
 * information.
 * 
 * 
 */
public final class GeoRssPoint extends PointConstruct {

	static final String NAME = "point";

	/**
	 * Constructs an empty georss:point element.
	 */
	public GeoRssPoint() {
		super(NAME);
	}

	/**
	 * Constructs a georss:point element with the given latitude and longitude.
	 */
	public GeoRssPoint(Double lat, Double lon) {
		super(NAME, lat, lon);
	}

	/**
	 * Constructs a georss:point element by copying it from the given point. If
	 * the given point is null an empty point will be created.
	 */
	public GeoRssPoint(Point copyFrom) {
		super(NAME, copyFrom);
	}

}
