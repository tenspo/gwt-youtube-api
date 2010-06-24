package com.google.youtube.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.YouTubeErrorEvent;

public interface YouTubeErrorHandler extends EventHandler {
	void onError(YouTubeErrorEvent event);
}