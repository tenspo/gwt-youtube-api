package com.google.youtube.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.YouTubeStateChangedEvent;

public interface YouTubeStateChangedHandler extends EventHandler {
	void onStateChanged(YouTubeStateChangedEvent event);
}