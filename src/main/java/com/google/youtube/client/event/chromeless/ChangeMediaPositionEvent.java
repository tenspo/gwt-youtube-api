package com.google.youtube.client.event.chromeless;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.event.chromeless.handler.ChangeMediaPositionHandler;
import com.google.youtube.client.event.chromeless.handler.HasChangeMediaPositionHandler;

public class ChangeMediaPositionEvent extends GwtEvent<ChangeMediaPositionHandler> {
	
	private static Type<ChangeMediaPositionHandler> TYPE;

	public static void fire(HasChangeMediaPositionHandler source) {
		if (TYPE != null) {
			ChangeMediaPositionEvent event = new ChangeMediaPositionEvent();
			source.fireEvent(event);
		}
	}

	public static Type<ChangeMediaPositionHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<ChangeMediaPositionHandler>());
	}

	protected ChangeMediaPositionEvent() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Type<ChangeMediaPositionHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(ChangeMediaPositionHandler handler) {
		handler.onChangePosition(this);
	}
}