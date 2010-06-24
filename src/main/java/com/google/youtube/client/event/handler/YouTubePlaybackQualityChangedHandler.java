package com.google.youtube.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.YouTubePlaybackQualityChangedEvent;

public interface YouTubePlaybackQualityChangedHandler extends EventHandler {
	void onPlaybackQualityChanged(YouTubePlaybackQualityChangedEvent event);
}