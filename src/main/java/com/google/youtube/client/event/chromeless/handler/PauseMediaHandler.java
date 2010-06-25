package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.chromeless.PauseMediaEvent;

public interface PauseMediaHandler extends EventHandler {
	void onPause(PauseMediaEvent event);
}