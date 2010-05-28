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

import com.google.gdata.util.html.HtmlToText;

/**
 * HTML variant of {@link TextConstruct}.
 * 
 * 
 */
public class HtmlTextConstruct extends TextConstruct {

	/** Class constructor. */
	public HtmlTextConstruct() {
	}

	/**
	 * Class constructor specifying the HTML content for this text construct to contain.
	 */
	public HtmlTextConstruct(String html) {
		this.html = html;
	}

	/**
	 * Class constructor specifying the HTML content for this text construct to contain, plus the human language that
	 * the text is written in.
	 */
	public HtmlTextConstruct(String html, String lang) {
		this.html = html;
		this.lang = lang;
	}

	/** @return the type (HTML) of this text construct */
	@Override
	public int getType() {
		return Type.HTML;
	}

	/** @return {@code true} if this text construct has no contents */
	@Override
	public boolean isEmpty() {
		return getHtml() == null;
	}

	/** HTML contents. */
	protected String html;

	/** @return the HTML contents of this text construct */
	public String getHtml() {
		return html;
	}

	/** Specifies the HTML contents of this text construct. */
	public void setHtml(String v) {
		html = v;
	}

	/**
	 * @return a plain-text representation of this text construct or {@code null} if there is no html content.
	 */
	@Override
	public String getPlainText() {
		return !isEmpty() ? HtmlToText.htmlToPlainText(html) : null;
	}

}
