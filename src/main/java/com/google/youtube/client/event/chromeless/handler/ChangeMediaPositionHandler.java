package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.youtube.client.event.chromeless.ChangeMediaPositionEvent;

public interface ChangeMediaPositionHandler extends EventHandler {
	void onChangePosition(ChangeMediaPositionEvent event);
}