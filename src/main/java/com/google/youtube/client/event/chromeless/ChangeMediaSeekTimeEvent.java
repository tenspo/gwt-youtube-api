package com.google.youtube.client.event.chromeless;

import com.google.gwt.event.shared.GwtEvent;
import com.google.youtube.client.event.chromeless.handler.ChangeMediaSeekTimeHandler;
import com.google.youtube.client.event.chromeless.handler.HasChangeMediaSeekTimeHandler;

public class ChangeMediaSeekTimeEvent extends GwtEvent<ChangeMediaSeekTimeHandler> {
	
	private static Type<ChangeMediaSeekTimeHandler> TYPE;

	public static void fire(HasChangeMediaSeekTimeHandler source, int position) {
		if (TYPE != null) {
			ChangeMediaSeekTimeEvent event = new ChangeMediaSeekTimeEvent(position);
			source.fireEvent(event);
		}
	}

	public static Type<ChangeMediaSeekTimeHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<ChangeMediaSeekTimeHandler>());
	}

	private int position;
	
	protected ChangeMediaSeekTimeEvent(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public final Type<ChangeMediaSeekTimeHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(ChangeMediaSeekTimeHandler handler) {
		handler.onChangeSeekTime(this);
	}
}