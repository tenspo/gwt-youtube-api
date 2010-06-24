package com.google.youtube.client.event.chromeless;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.event.chromeless.handler.HasPlayHandler;
import com.google.youtube.client.event.chromeless.handler.PlayHandler;

public class PlayEvent extends GwtEvent<PlayHandler> {
	
	private static Type<PlayHandler> TYPE;

	public static void fire(HasPlayHandler source) {
		if (TYPE != null) {
			PlayEvent event = new PlayEvent();
			source.fireEvent(event);
		}
	}

	public static Type<PlayHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<PlayHandler>());
	}

	protected PlayEvent() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Type<PlayHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(PlayHandler handler) {
		handler.onPlay(this);
	}
}