package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.chromeless.PlayMediaEvent;

public interface PlayMediaHandler extends EventHandler {
	void onPlay(PlayMediaEvent event);
}