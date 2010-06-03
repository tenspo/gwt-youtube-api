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

import sk.seges.acris.json.client.extension.ExtensionPoint;

/**
 * Publication control tag app:control, which usually contains app:draft.
 * 
 * The main reason for having this as a separate object is mostly so that it can be used as an extension point. The
 * draft flag is usually set using {@link BaseEntry#setDraft(Boolean)}, which will create a PubControl object when
 * necessary.
 * 
 * 
 */
public class PubControl extends ExtensionPoint {

	private Boolean draft;

	/** Creates an empty app:control tag. */
	public PubControl() {

	}

	/**
	 * Checks the value of the app:draft tag.
	 * 
	 * @return true if the entry is a draft (false by default as per the atom publishing protoc spec)
	 */
	public boolean isDraft() {
		return draft != null && draft.booleanValue();
	}

	/**
	 * Sets the value of the app:draft tag.
	 * 
	 * @param draft
	 *            sets the draft status, null to unset
	 */
	public void setDraft(Boolean draft) {
		this.draft = draft;
	}

}
