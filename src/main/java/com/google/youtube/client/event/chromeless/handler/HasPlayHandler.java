package com.google.youtube.client.event.chromeless.handler;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasPlayHandler extends HasHandlers {
	HandlerRegistration addPlay(PlayHandler handler);
}
