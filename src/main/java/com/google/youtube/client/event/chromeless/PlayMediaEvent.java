package com.google.youtube.client.event.chromeless;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.event.chromeless.handler.HasPlayMediaHandler;
import com.google.youtube.client.event.chromeless.handler.PlayMediaHandler;

public class PlayMediaEvent extends GwtEvent<PlayMediaHandler> {
	
	private static Type<PlayMediaHandler> TYPE;

	public static void fire(HasPlayMediaHandler source) {
		if (TYPE != null) {
			PlayMediaEvent event = new PlayMediaEvent();
			source.fireEvent(event);
		}
	}

	public static Type<PlayMediaHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<PlayMediaHandler>());
	}

	protected PlayMediaEvent() {
	}

	@Override
	public final Type<PlayMediaHandler> getAssociatedType() {
		return (Type<PlayMediaHandler>) TYPE;
	}

	@Override
	protected void dispatch(PlayMediaHandler handler) {
		handler.onPlay(this);
	}
}