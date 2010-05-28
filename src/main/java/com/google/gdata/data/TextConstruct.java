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
 * Abstract base class for text construct type.
 * 
 * 
 */
public abstract class TextConstruct implements ITextConstruct {

	/**
	 * Defines the possible text construct types: TEXT and HTML.
	 */
	public static class Type {
		public static final int TEXT = 1;
		public static final int HTML = 2;
	}

	/** Returns this text construct's type (text or HTML). */
	public abstract int getType();

	/**
	 * Returns {@code true} if this text construct has no contents.
	 */
	public abstract boolean isEmpty();

	/** Returns a plain-text representation of this text construct. */
	public abstract String getPlainText();

	/** Language. Derived from the current state of {@code xml:lang}. */
	protected String lang;

	/** @return the human language that this text construct is written in */
	public String getLang() {
		return lang;
	}

	/** Specifies the human language that this text construct is written in. */
	public void setLang(String v) {
		lang = v;
	}

	/**
	 * Creates a text construct. This method is convenient for some service implementations
	 * 
	 * @param type
	 *            the type of the new text construct (TEXT or HTML)
	 * 
	 * @param textOrHtml
	 *            the contents to put in this text construct, if the type is TEXT or HTML. 
	 * 
	 * 
	 * @return a {@link TextConstruct}, or {@code null} if invalid type.
	 */
	public static TextConstruct create(int type, String textOrHtml) {

		switch (type) {

		case Type.TEXT:
			PlainTextConstruct ptc = new PlainTextConstruct(textOrHtml);
			return ptc;

		case Type.HTML:
			HtmlTextConstruct htc = new HtmlTextConstruct(textOrHtml);
			return htc;

		default:
			assert false : "Invalid type: " + String.valueOf(type);
			return null;
		}
	}

	/**
	 * Construct a new plain text content with the given text.
	 */
	public static TextConstruct plainText(String text) {
		return new PlainTextConstruct(text);
	}

	/**
	 * Construct a new html text content with the given html.
	 */
	public static TextConstruct html(String html) {
		return new HtmlTextConstruct(html);
	}

	/** Enumerates the kinds of restrictions on what HTML tags are allowed. */
	public enum RssFormat {

		/** HTML/XHTML is converted to plain text. */
		PLAIN_TEXT,

		/** All tags are allowed. */
		FULL_HTML
	}
}