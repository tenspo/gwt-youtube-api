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

import org.gwttime.time.DateTime;

/**
 * The MediaSource interface provides a model for accessing media content sent by the GData client or returned by a
 * GDataFeed service implementation. It extends the JavaBeans Activation Framework {@link DataSource} interface, making
 * it possible to use the MediaSource as a DataSource within the framework for media handling.
 * <p>
 * When creating new GData entries using a {@link MediaSource}, the return value of the {@link DataSource#getName()}
 * method will be used to create a Slug header for the media source. Some concrete implementations of the MediaSource
 * interface will provide the ability to set the name value and/or provide a default value based upon source attributes.
 * 
 * 
 */
public interface MediaSource {

	/**
	 * This method returns the MIME type of the data in the form of a string. It should always return a valid type. It
	 * is suggested that getContentType return "application/octet-stream" if the DataSource implementation can not
	 * determine the data type.
	 * 
	 * @return the MIME Type
	 */
	public String getType();

	public String getSubType();

	/**
	 * Return the <i>name</i> of this object where the name of the object is dependant on the nature of the underlying
	 * objects. DataSources encapsulating files may choose to return the filename of the object. (Typically this would
	 * be the last component of the filename, not an entire pathname.)
	 * 
	 * @return the name of the object.
	 */
	public String getName();

	/**
	 * Returns the length (in bytes) of the media source. A value of -1 indicates the length is unknown.
	 */
	public long getContentLength();

	/**
	 * Returns the last modification time of the media content or {@code null} if unknown.
	 */
	public DateTime getLastModified();

	/**
	 * Returns the entity tag (etag) value associated with the media content or {@code null} if unknown.
	 */
	public String getEtag();
}