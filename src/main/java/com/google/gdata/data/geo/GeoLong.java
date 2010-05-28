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


package com.google.gdata.data.geo;

import com.google.gdata.data.ValueConstruct;
import com.google.gwt.i18n.client.NumberFormat;

/**
 * Extension for a W3C geo:long element.  It contains the getter/setter for
 * specifying the longitude of a geo-coordinate.  The coordinate is contained
 * within the long element as: {@literal <geo:long>coordinate</geo:long>}.
 * Note that the longitude element does not need to be a child of the geo:Point
 * element. Please see the W3C document
 * <a href="http://www.w3.org/2003/01/geo">http://www.w3.org/2003/01/geo</a> for
 * more information.
 *
 * @see com.google.gdata.data.geo.impl.W3CPoint
 * 
 */
public class GeoLong extends ValueConstruct {

  /**
   * The maximmum acceptable value of a longitude coordinate in degrees.
   */
  public static final double MAX_LONG = 180.0d;

  /**
   * The minimum acceptable value of a longitude coordinate in degrees.
   */
  public static final double MIN_LONG = -180.0d;

  /**
   * This denotes the number of significant digits after the decimal point
   * for a coordinate when represented by a string.
   */
  public static final int COORDINATE_PRECISION = 6;

  private static final NumberFormat NUM_FORMAT = NumberFormat.getFormat("000.000000");

//  static {
//    NUM_FORMAT.setMaximumFractionDigits(COORDINATE_PRECISION);
//    NUM_FORMAT.setMinimumFractionDigits(COORDINATE_PRECISION);
//  }

  private Double lon = null;

  /**
   * Creates an instance of the GeoLong extension without a
   * longitude value set.
   */
  public GeoLong() {
    this(null);
  }

  /**
   * Creates an immutable instance of GeoLong extension with the coordinate
   * set to the value passed in.
   *
   * @param lon The longitude coordinate reprensented by this element.
   * @throws IllegalArgumentException if the longitude is not between
   *        -180 and 180 degrees.
   */
  public GeoLong(Double lon) throws IllegalArgumentException {
    super("long", null);
    setRequired(true);
    if (lon != null) {
      setLongitude(lon);
    }
  }

  /**
   * Returns the actual double coordinate for longitude.  Note this value
   * is not rounded unlike the value returned by getValue().
   *
   * @return the longitude represented by this element.
   */
  public Double getLongitude() {
    return lon;
  }

  /**
   * Sets the longitude represented by this element.
   *
   * @param longitude the longitude of this element.
   */
  public void setLongitude(Double longitude) {
    // Note longitude might be null.
    lon = longitude;
    String value = null;

    if (longitude != null) {
      if (longitude.compareTo(MIN_LONG) < 0 ||
          longitude.compareTo(MAX_LONG) > 0) {
        throw new IllegalArgumentException("Longitude must be between " +
            "-180 and 180 degrees.");
      }

      // Format the string so that it has a consistent number of digits
      // after the decimal place.
      value = NUM_FORMAT.format(longitude);
    }

    super.setValue(value);
  }

  /**
   * Overrides base implementation by validating that the string represents
   * a longitude coordinate between -180 and 180 degrees.  Also formats the
   * the string so that it has a consistent number of significant digits
   * after the decimal point.
   */
  @Override
  public void setValue(String value) {
    Double d = null;
    if (value != null) {
      // First ensure that the string is actually a double.
      try {
        d = Double.parseDouble(value);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("'value' must be a double.");
      }
    }

    setLongitude(d);
  }
}
