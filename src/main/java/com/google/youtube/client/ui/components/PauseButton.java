package com.google.youtube.client.ui.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.youtube.client.event.chromeless.PauseMediaEvent;
import com.google.youtube.client.event.chromeless.handler.PauseMediaHandler;
import com.google.youtube.client.ui.components.api.IPauseWidget;

public class PauseButton extends AbstractButtonWrapper implements IPauseWidget {

	public PauseButton(Button button) {
		super(button);
	}

	@Override
	public HandlerRegistration addPauseHandler(PauseMediaHandler handler) {
		return addHandler(handler, PauseMediaEvent.getType());
	}

	@Override
	protected void fireEvent(ClickEvent event) {
		PauseMediaEvent.fire(PauseButton.this);
	}
}