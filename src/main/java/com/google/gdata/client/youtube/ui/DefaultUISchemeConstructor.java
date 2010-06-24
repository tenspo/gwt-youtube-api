package com.google.gdata.client.youtube.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

public class DefaultUISchemeConstructor implements UISchemeConstructor {

	@Override
	public Button constructButton(String label) {
		return new Button(label);
	}

	@Override
	public TextBox constructTextBox() {
		return new TextBox();
	}

}
