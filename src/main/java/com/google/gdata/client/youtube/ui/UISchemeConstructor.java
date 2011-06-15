package com.google.gdata.client.youtube.ui;

import sk.seges.acris.widget.client.form.IconButton;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;

public interface UISchemeConstructor {
	
	Button constructButton(String label);

	PushButton constructPushButton(String label);

	IconButton constructIconButton(ImageResource image);

	TextBox constructTextBox();
}
