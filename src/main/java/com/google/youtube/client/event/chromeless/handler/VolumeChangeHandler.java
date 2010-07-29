package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.chromeless.VolumeChangeEvent;

public interface VolumeChangeHandler extends EventHandler {
	void onVolumeChanged(VolumeChangeEvent event);
}