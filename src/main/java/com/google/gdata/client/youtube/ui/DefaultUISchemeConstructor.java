package com.google.gdata.client.youtube.ui;

import com.google.gwt.user.client.ui.Button;

public class DefaultUISchemeConstructor implements UISchemeConstructor {

	@Override
	public Button constructButton(String label) {
		return new Button(label);
	}

}
