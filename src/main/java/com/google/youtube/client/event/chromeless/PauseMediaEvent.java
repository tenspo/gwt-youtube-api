package com.google.youtube.client.event.chromeless;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.event.chromeless.handler.HasPauseMediaHandler;
import com.google.youtube.client.event.chromeless.handler.PauseMediaHandler;

public class PauseMediaEvent extends GwtEvent<PauseMediaHandler> {
	
	private static Type<PauseMediaHandler> TYPE;

	public static void fire(HasPauseMediaHandler source) {
		if (TYPE != null) {
			PauseMediaEvent event = new PauseMediaEvent();
			source.fireEvent(event);
		}
	}

	public static Type<PauseMediaHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<PauseMediaHandler>());
	}

	protected PauseMediaEvent() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Type<PauseMediaHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(PauseMediaHandler handler) {
		handler.onPause(this);
	}
}