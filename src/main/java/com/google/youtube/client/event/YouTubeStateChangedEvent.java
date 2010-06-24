package com.google.youtube.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.data.YouTubeVideoState;
import com.google.youtube.client.event.handler.HasYouTubeStateChangedHandler;
import com.google.youtube.client.event.handler.YouTubeStateChangedHandler;

public class YouTubeStateChangedEvent extends GwtEvent<YouTubeStateChangedHandler> {
	
	private static Type<YouTubeStateChangedHandler> TYPE;

	public static void fire(HasYouTubeStateChangedHandler source, YouTubeVideoState state) {
		if (TYPE != null) {
			YouTubeStateChangedEvent event = new YouTubeStateChangedEvent(state);
			source.fireEvent(event);
		}
	}

	public static Type<YouTubeStateChangedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<YouTubeStateChangedHandler>());
	}

	private YouTubeVideoState state;

	protected YouTubeStateChangedEvent(YouTubeVideoState state) {
		this.state = state;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Type<YouTubeStateChangedHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	public YouTubeVideoState getState() {
		return state;
	}

	@Override
	protected void dispatch(YouTubeStateChangedHandler handler) {
		handler.onStateChanged(this);
	}
}