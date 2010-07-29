package com.google.youtube.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.data.YouTubeVideoQuality;
import com.google.youtube.client.event.handler.HasYouTubeStateChangedHandler;
import com.google.youtube.client.event.handler.YouTubePlaybackQualityChangedHandler;

public class YouTubePlaybackQualityChangedEvent extends GwtEvent<YouTubePlaybackQualityChangedHandler> {
	
	private static Type<YouTubePlaybackQualityChangedHandler> TYPE;

	public static void fire(HasYouTubeStateChangedHandler source, YouTubeVideoQuality state) {
		if (TYPE != null) {
			YouTubePlaybackQualityChangedEvent event = new YouTubePlaybackQualityChangedEvent(state);
			source.fireEvent(event);
		}
	}

	public static Type<YouTubePlaybackQualityChangedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<YouTubePlaybackQualityChangedHandler>());
	}

	private YouTubeVideoQuality quality;

	protected YouTubePlaybackQualityChangedEvent(YouTubeVideoQuality quality) {
		this.quality = quality;
	}

	@Override
	public final Type<YouTubePlaybackQualityChangedHandler> getAssociatedType() {
		return (Type<YouTubePlaybackQualityChangedHandler>) TYPE;
	}

	public YouTubeVideoQuality getQuality() {
		return quality;
	}

	@Override
	protected void dispatch(YouTubePlaybackQualityChangedHandler handler) {
		handler.onPlaybackQualityChanged(this);
	}
}