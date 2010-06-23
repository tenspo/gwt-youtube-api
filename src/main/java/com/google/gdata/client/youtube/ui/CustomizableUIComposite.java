package com.google.gdata.client.youtube.ui;

import com.google.gwt.user.client.ui.Composite;

public abstract class CustomizableUIComposite extends Composite {

	private UISchemeConstructor uiSchemeConstrucor;

	public void setUISchemeConstrucor(UISchemeConstructor uiSchemeConstrucor) {
		this.uiSchemeConstrucor = uiSchemeConstrucor;
	}

	protected UISchemeConstructor ensureUIConstructor() {
		if (uiSchemeConstrucor == null) {
			uiSchemeConstrucor = new DefaultUISchemeConstructor();
		}
		return uiSchemeConstrucor;
	}

}