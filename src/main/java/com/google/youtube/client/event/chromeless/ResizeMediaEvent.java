package com.google.youtube.client.event.chromeless;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.event.chromeless.handler.HasResizeMediaHandler;
import com.google.youtube.client.event.chromeless.handler.ResizeMediaHandler;

public class ResizeMediaEvent extends GwtEvent<ResizeMediaHandler> {
	
	public static enum ResizeType {
		NORMAL, FULLSCREEN
	}
	
	private static Type<ResizeMediaHandler> TYPE;

	public static void fire(HasResizeMediaHandler source, ResizeType resizeType) {
		if (TYPE != null) {
			ResizeMediaEvent event = new ResizeMediaEvent(resizeType);
			source.fireEvent(event);
		}
	}

	public static Type<ResizeMediaHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<ResizeMediaHandler>());
	}

	private ResizeType resizeType;
	
	protected ResizeMediaEvent(ResizeType resizeType) {
		this.resizeType = resizeType;
	}

	public ResizeType getResizeType() {
		return resizeType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public final Type<ResizeMediaHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(ResizeMediaHandler handler) {
		handler.onResize(this);
	}
}