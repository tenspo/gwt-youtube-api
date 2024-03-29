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

import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionDescription;
import sk.seges.acris.json.client.extension.ExtensionPoint;
import sk.seges.acris.json.client.extension.ExtensionProfile;

import com.google.gdata.data.geo.Namespaces;
import com.google.gdata.data.geo.Point;

/**
 * Extension for a GML gml:Point element.
 * 
 * 
 */
@JsonObject(group = Namespaces.GML_ALIAS, value = GmlPoint.NAME)
public class GmlPoint extends ExtensionPoint implements Point {

	static final String NAME = "Point";

	/**
	 * Constructs an empty gml:Point element.
	 */
	public GmlPoint() {
	}

	/**
	 * Constructs a gml:Point element out of the given lat and lon values. This will construct a gml:pos element to hold
	 * the actual values. If the values are null then an empty gml:pos element will be created.
	 */
	public GmlPoint(Double lat, Double lon) {
		this(new GmlPos(lat, lon));
	}

	/**
	 * Constructs a gml:Point element using the given Point coordinates for the nested gml:pos element. If the point is
	 * already a gml:pos, then it will be used directly as the extension, otherwise a gml:pos element will be created as
	 * a copy of the given point.
	 */
	public GmlPoint(Point point) {
		if (point != null) {
			if (!(point instanceof GmlPos)) {
				point = new GmlPos(point);
			}
			setExtension(point);
		}
	}

	/**
	 * Returns the suggested extension description with configurable repeatability.
	 */
	public static ExtensionDescription getDefaultDescription(boolean repeatable) {
		ExtensionDescription desc = new ExtensionDescription();
		desc.setExtensionClass(GmlPoint.class);
		desc.setPointName(Namespaces.GML_NAMESPACE + "$" + NAME);
		desc.setRepeatable(repeatable);
		return desc;
	}

	/*
	 * Declare the extensions for gml point. This contains a single element with the coordinate which is the actual
	 * point. This is for extensibility I believe, but it ends up just being an extra level of wrapping.
	 */
	@Override
	public void declareExtensions(ExtensionProfile extProfile) {
		// Declare the gml:pos extension.
		extProfile.declare(GmlPoint.class, GmlPos.getDefaultDescription(false));
		super.declareExtensions(extProfile);
	}

	/**
	 * @return the value of the gml:pos element within this Point.
	 */
	public Double getLatitude() {
		GmlPos coord = getExtension(GmlPos.class);
		return coord != null ? coord.getLat() : null;
	}

	/**
	 * @return the value of the gml:pos element's longitude within this Point.
	 */
	public Double getLongitude() {
		GmlPos coord = getExtension(GmlPos.class);
		return coord != null ? coord.getLon() : null;
	}

	/**
	 * Sets the latitude and longitude of the gml:pos element of this Point to the latitude and longitude coordinates
	 * specified.
	 * 
	 * @param lat
	 *            The latitude coordinate of this point.
	 * @param lon
	 *            the longitude coordinate of this point.
	 */
	public void setGeoLocation(Double lat, Double lon) {
		GmlPos point = getExtension(GmlPos.class);
		if (point != null) {
			if (lat == null && lon == null) {
				removeExtension(point);
			} else {
				point.setGeoLocation(lat, lon);
			}
		} else if (lat != null || lon != null) {
			point = new GmlPos();
			setExtension(point);
			point.setGeoLocation(lat, lon);
		}
	}

	@Override
	public Double getLat() {
		return getLatitude();
	}

	@Override
	public Double getLon() {
		return getLongitude();
	}

}