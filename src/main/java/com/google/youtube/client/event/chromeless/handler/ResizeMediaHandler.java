package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.chromeless.ResizeMediaEvent;

public interface ResizeMediaHandler extends EventHandler {
	void onResize(ResizeMediaEvent event);
}