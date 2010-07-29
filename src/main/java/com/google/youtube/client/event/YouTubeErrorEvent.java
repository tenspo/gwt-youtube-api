package com.google.youtube.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.data.YouTubeError;
import com.google.youtube.client.event.handler.YouTubeErrorHandler;
import com.google.youtube.client.event.handler.HasYouTubeErrorHandler;

public class YouTubeErrorEvent extends GwtEvent<YouTubeErrorHandler> {
	
	private static Type<YouTubeErrorHandler> TYPE;

	public static void fire(HasYouTubeErrorHandler source, YouTubeError error) {
		if (TYPE != null) {
			YouTubeErrorEvent event = new YouTubeErrorEvent(error);
			source.fireEvent(event);
		}
	}

	public static Type<YouTubeErrorHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<YouTubeErrorHandler>());
	}

	private YouTubeError error;

	protected YouTubeErrorEvent(YouTubeError error) {
		this.error = error;
	}

	@Override
	public final Type<YouTubeErrorHandler> getAssociatedType() {
		return (Type<YouTubeErrorHandler>) TYPE;
	}

	public YouTubeError getError() {
		return error;
	}

	@Override
	protected void dispatch(YouTubeErrorHandler handler) {
		handler.onError(this);
	}
}