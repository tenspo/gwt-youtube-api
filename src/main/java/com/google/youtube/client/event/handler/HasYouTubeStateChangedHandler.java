package com.google.youtube.client.event.handler;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasYouTubeStateChangedHandler extends HasHandlers {
	HandlerRegistration addStateChangedHandler(YouTubeStateChangedHandler handler);
}
