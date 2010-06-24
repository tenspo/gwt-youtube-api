package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.chromeless.PlayEvent;

public interface PlayHandler extends EventHandler {
	void onPlay(PlayEvent event);
}