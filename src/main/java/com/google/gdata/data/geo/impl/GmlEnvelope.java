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

import com.google.gdata.data.geo.Box;
import com.google.gdata.data.geo.Point;

/**
 * A gml:Envelope element, this is used to describe a box using the gml version
 * of our geographic information language. An envelope contains an upper and a
 * lower corner.
 * 
 * 
 */
public class GmlEnvelope implements Box {

	static final String NAME = "Envelope";

	private GmlLowerCorner gmlLowerCorner;

	private GmlUpperCorner gmlUpperCorner;

	/**
	 * Constructs an empty gml:Envelope element.
	 */
	public GmlEnvelope() {
	}

	/**
	 * Constructs a gml:Envelope with the given coordinates.
	 */
	public GmlEnvelope(Double lowerLat, Double lowerLon, Double upperLat,
			Double upperLon) {
		this(new GmlLowerCorner(lowerLat, lowerLon), new GmlUpperCorner(
				upperLat, upperLon));
	}

	/**
	 * Constructs a gml:Envelope with the given lower and upper values. If the
	 * given values are already a GmlLowerCorner and a GmlUpperCorner, they will
	 * be used direclty as the extensions, otherwise they will be copied. If
	 * both points are null an empty point will be created, otherwise if one of
	 * them is null then an IllegalArgumentException will be thrown.
	 */
	public GmlEnvelope(Point lower, Point upper) {
		setGeoLocation(lower, upper);
	}

	/**
	 * Constructs a gml:Envelope by copying from the given box. This calls the
	 * {@link #GmlEnvelope(Point, Point)} constructor with the points in the
	 * box, or with nulls if the box itself is null.
	 */
	public GmlEnvelope(Box box) {
		this(box == null ? null : box.getLowerLeft(), box == null ? null : box
				.getUpperRight());
	}

	/*
	 * Get the lower corner extension element.
	 */
	public GmlLowerCorner getLowerLeft() {
		return gmlLowerCorner;
	}

	/*
	 * Get the upper corner extension element.
	 */
	public GmlUpperCorner getUpperRight() {
		return gmlUpperCorner;
	}

	/*
	 * Sets the geo location of this envelope. If the passed in points are not
	 * GmlLowerCorner and GmlUpperCorner, they will get converted. Either both
	 * must be null or both non null or an IllegalArgumentException is thrown.
	 */
	public void setGeoLocation(Point lowerLeft, Point upperRight) {
		if (lowerLeft != null && upperRight != null) {
			if (!(lowerLeft instanceof GmlLowerCorner)) {
				gmlLowerCorner = new GmlLowerCorner(lowerLeft);
			}
			if (!(upperRight instanceof GmlUpperCorner)) {
				gmlUpperCorner = new GmlUpperCorner(upperRight);
			}
		} else if (lowerLeft != null || upperRight != null) {
			throw new IllegalArgumentException(
					"'lower' and 'upper' must either both be null or non-null.");
		} else {
			gmlLowerCorner = null;
			gmlUpperCorner = null;
		}
	}

	/*
	 * Set the upper extension element. If the passed in Point is not a
	 * GmlUpperCorner it will get converted to one.
	 */
	public void setUpperRight(Point upperRight) {
		if (upperRight == null) {
			gmlUpperCorner = null;
		} else {
			if (!(upperRight instanceof GmlUpperCorner)) {
				gmlUpperCorner = new GmlUpperCorner(upperRight);
			}
		}
	}

}
