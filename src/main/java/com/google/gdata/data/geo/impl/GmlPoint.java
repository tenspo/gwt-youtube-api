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
 * Extension for a GML gml:Point element.
 * 
 * 
 */
public class GmlPoint implements Point {

	static final String NAME = "Point";

	private GmlPos gmlPos;

	/**
	 * Constructs an empty gml:Point element.
	 */
	public GmlPoint() {
	}

	/**
	 * Constructs a gml:Point element out of the given lat and lon values. This
	 * will construct a gml:pos element to hold the actual values. If the values
	 * are null then an empty gml:pos element will be created.
	 */
	public GmlPoint(Double lat, Double lon) {
		this(new GmlPos(lat, lon));
	}

	/**
	 * Constructs a gml:Point element using the given Point coordinates for the
	 * nested gml:pos element. If the point is already a gml:pos, then it will
	 * be used directly as the extension, otherwise a gml:pos element will be
	 * created as a copy of the given point.
	 */
	public GmlPoint(Point point) {
		if (point != null) {
			if (!(point instanceof GmlPos)) {
				gmlPos = new GmlPos(point);
			}
		}
	}

	/**
	 * @return the value of the gml:pos element within this Point.
	 */
	public Double getLatitude() {
		return gmlPos != null ? gmlPos.getLatitude() : null;
	}

	/**
	 * @return the value of the gml:pos element's longitude within this Point.
	 */
	public Double getLongitude() {
		return gmlPos != null ? gmlPos.getLongitude() : null;
	}

	/**
	 * Sets the latitude and longitude of the gml:pos element of this Point to
	 * the latitude and longitude coordinates specified.
	 * 
	 * @param lat
	 *            The latitude coordinate of this point.
	 * @param lon
	 *            the longitude coordinate of this point.
	 */
	public void setGeoLocation(Double lat, Double lon) {
		if (gmlPos != null) {
			if (lat == null && lon == null) {
				gmlPos = null;
			} else {
				gmlPos.setGeoLocation(lat, lon);
			}
		} else if (lat != null || lon != null) {
			gmlPos = new GmlPos();
			gmlPos.setGeoLocation(lat, lon);
		}
	}
}