package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasPauseMediaHandler extends HasHandlers {
	HandlerRegistration addPauseHandler(PauseMediaHandler handler);
}
