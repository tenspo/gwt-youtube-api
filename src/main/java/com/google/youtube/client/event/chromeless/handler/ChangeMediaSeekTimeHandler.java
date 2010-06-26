package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.chromeless.ChangeMediaSeekTimeEvent;

public interface ChangeMediaSeekTimeHandler extends EventHandler {
	void onChangeSeekTime(ChangeMediaSeekTimeEvent event);
}