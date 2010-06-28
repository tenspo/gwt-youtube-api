package com.google.youtube.client.event.chromeless;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.event.chromeless.handler.HasVolumeChangeHandler;
import com.google.youtube.client.event.chromeless.handler.VolumeChangeHandler;

public class VolumeEvent extends GwtEvent<VolumeChangeHandler> {
	
	private static Type<VolumeChangeHandler> TYPE;

	public static void fire(HasVolumeChangeHandler source) {
		if (TYPE != null) {
			VolumeEvent event = new VolumeEvent();
			source.fireEvent(event);
		}
	}

	public static Type<VolumeChangeHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<VolumeChangeHandler>());
	}

	protected VolumeEvent() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Type<VolumeChangeHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(VolumeChangeHandler handler) {
		handler.onVolumeChanged(this);
	}
}