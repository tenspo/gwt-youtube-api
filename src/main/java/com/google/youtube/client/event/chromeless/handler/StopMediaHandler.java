package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.chromeless.StopMediaEvent;

public interface StopMediaHandler extends EventHandler {
	void onStop(StopMediaEvent event);
}