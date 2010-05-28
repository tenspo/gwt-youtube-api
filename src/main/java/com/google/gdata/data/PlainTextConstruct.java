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
 * Plain text variant of {@link TextConstruct}.
 * 
 * 
 */
public class PlainTextConstruct extends TextConstruct {

	/** Class constructor. */
	public PlainTextConstruct() {
	}

	/**
	 * Class constructor specifying the plain text content for this text construct to contain.
	 */
	public PlainTextConstruct(String text) {
		this.text = text;
	}

	/**
	 * Class constructor specifying the plain text content for this text construct to contain, plus the human language
	 * that the text is written in.
	 */
	public PlainTextConstruct(String text, String lang) {
		this.text = text;
		this.lang = lang;
	}

	/** @return the type (TEXT) of this text construct */
	@Override
	public int getType() {
		return Type.TEXT;
	}

	@Override
	/** @return {@code true} if this text construct has no contents */
	public boolean isEmpty() {
		return getText() == null;
	}

	/** Plain text contents. */
	protected String text;

	/** @return the plain text contents of this text construct */
	public String getText() {
		return text;
	}

	/** Specifies the plain text contents of this text construct. */
	public void setText(String v) {
		text = v;
	}

	/**
	 * @return a plain-text representation of this text construct or {@code null} if there is no text content.
	 */
	@Override
	public String getPlainText() {
		return text != null ? new String(text) : null;
	}
}