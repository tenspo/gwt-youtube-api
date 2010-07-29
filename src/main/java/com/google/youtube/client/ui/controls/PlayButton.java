package com.google.youtube.client.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.youtube.client.event.chromeless.PlayMediaEvent;
import com.google.youtube.client.event.chromeless.handler.PlayMediaHandler;
import com.google.youtube.client.ui.controls.api.IPlayControl;
import com.google.youtube.client.ui.controls.common.AbstractButtonBaseWrapper;

public class PlayButton extends AbstractButtonBaseWrapper implements IPlayControl {

	public PlayButton(ButtonBase button) {
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