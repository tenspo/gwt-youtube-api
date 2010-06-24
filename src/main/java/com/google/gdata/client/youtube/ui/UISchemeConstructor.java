package com.google.gdata.client.youtube.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

public interface UISchemeConstructor {
	
	Button constructButton(String label);

	TextBox constructTextBox();
}
