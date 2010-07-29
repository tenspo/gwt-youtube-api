package com.google.gdata.client.youtube.ui;

import sk.seges.acris.widget.client.IconButton;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PushButton;
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

	@Override
	public PushButton constructPushButton(String label) {
		return new PushButton(label);
	}

	@Override
	public IconButton constructIconButton(ImageResource image) {
		IconButton iconButton = new IconButton();
		iconButton.setImage(image);
		return iconButton;
	}

}
