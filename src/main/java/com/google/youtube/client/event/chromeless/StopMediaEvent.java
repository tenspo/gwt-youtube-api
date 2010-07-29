package com.google.youtube.client.event.chromeless;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.event.chromeless.handler.HasStopMediaHandler;
import com.google.youtube.client.event.chromeless.handler.StopMediaHandler;

public class StopMediaEvent extends GwtEvent<StopMediaHandler> {
	
	private static Type<StopMediaHandler> TYPE;

	public static void fire(HasStopMediaHandler source) {
		if (TYPE != null) {
			StopMediaEvent event = new StopMediaEvent();
			source.fireEvent(event);
		}
	}

	public static Type<StopMediaHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<StopMediaHandler>());
	}

	protected StopMediaEvent() {
	}

	@Override
	public final Type<StopMediaHandler> getAssociatedType() {
		return (Type<StopMediaHandler>) TYPE;
	}

	@Override
	protected void dispatch(StopMediaHandler handler) {
		handler.onStop(this);
	}
}