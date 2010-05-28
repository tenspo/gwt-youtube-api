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

package com.google.gdata.data;

/**
 * External link type.
 */
public class Link /*extends ExtensionPoint*/ implements ILink {

	public Link() {
	}

	public Link(String rel, String type, String href) {
		this.rel = rel;
		this.type = type;
		setHref(href);
	}

	/**
	 * Link relation type. Possible values include {@code self}, {@code prev}, {@code next}, {@code enclosure}, etc.
	 */
	protected String rel;

	public String getRel() {
		return rel != null ? rel : Rel.ALTERNATE;
	}

	public void setRel(String v) {
		rel = v;
	}

	/** MIME type of the link target. */
	protected String type;

	public String getType() {
		return type;
	}

	public void setType(String v) {
		type = v;
	}

	/** Link URI. */
	protected String href;

	public String getHref() {
		return href;
	}

	public void setHref(String v) {
		href = v;
	}

	/** Language of resource pointed to by href. */
	protected String hrefLang;

	public String getHrefLang() {
		return hrefLang;
	}

	public void setHrefLang(String v) {
		hrefLang = v;
	}

	/** Link title. */
	protected String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String v) {
		title = v;
	}

	/** Language of link title. */
	protected String titleLang;

	public String getTitleLang() {
		return titleLang;
	}

	public void setTitleLang(String v) {
		titleLang = v;
	}

	/** Length of the resource pointed to by href, in bytes. */
	protected long length = -1;

	public long getLength() {
		return length;
	}

	public void setLength(long v) {
		length = v;
	}

	/** Nested atom:content element or {@code null} if no inlined link content. */
	protected Content content = null;

	public Content getContent() {
		return content;
	}

	public void setContent(Content c) {
		this.content = c;
	}

	/** Etag of linked resource, or {@code null} if unknown */
	protected String etag = null;

	public String getEtag() {
		return etag;
	}

	public void setEtag(String v) {
		etag = v;
	}

	/**
	 * Returns whether this link matches the given {@code rel} and {@code type} values.
	 * 
	 * @param relToMatch
	 *            {@code rel} value to match or {@code null} to match any {@code rel} value.
	 * @param typeToMatch
	 *            {@code type} value to match or {@code null} to match any {@code type} value.
	 */
	public boolean matches(String relToMatch, String typeToMatch) {
		return (relToMatch == null || relToMatch.equals(getRel()))
				&& (typeToMatch == null || typeToMatch.equals(this.type));
	}

}
