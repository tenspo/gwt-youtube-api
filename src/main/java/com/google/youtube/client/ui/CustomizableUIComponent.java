package com.google.youtube.client.ui;

import com.google.gdata.client.youtube.ui.DefaultUISchemeConstructor;
import com.google.gdata.client.youtube.ui.UISchemeConstructor;

public abstract class CustomizableUIComponent {

	private UISchemeConstructor uiSchemeConstructor;

	protected UISchemeConstructor getDefaultUIConstructor() {
		return new DefaultUISchemeConstructor();
	}

	public void setUiSchemeConstructor(UISchemeConstructor uiSchemeConstructor) {
		this.uiSchemeConstructor = uiSchemeConstructor;
	}

	protected UISchemeConstructor ensureUIConstructor() {
		if (uiSchemeConstructor == null) {
			uiSchemeConstructor = getDefaultUIConstructor();
		}
		return uiSchemeConstructor;
	}
}
