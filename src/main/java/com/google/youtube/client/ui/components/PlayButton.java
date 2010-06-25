package com.google.youtube.client.ui.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.youtube.client.event.chromeless.PlayMediaEvent;
import com.google.youtube.client.event.chromeless.handler.PlayMediaHandler;
import com.google.youtube.client.ui.components.api.IPlayWidget;

public class PlayButton extends AbstractButtonWrapper implements IPlayWidget {

	public PlayButton(Button button) {
		super(button);
	}

	@Override
	protected void fireEvent(ClickEvent event) {
		PlayMediaEvent.fire(this);
	}

	@Override
	public HandlerRegistration addPlayHandler(PlayMediaHandler handler) {
		return addHandler(handler, PlayMediaEvent.getType());
	}

}