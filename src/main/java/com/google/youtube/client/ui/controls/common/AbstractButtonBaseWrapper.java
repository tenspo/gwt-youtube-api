package com.google.youtube.client.ui.controls.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.Composite;

public abstract class AbstractButtonBaseWrapper extends Composite {

	protected HandlerRegistration clickHandlerRegistration;

	protected ButtonBase button;

	public AbstractButtonBaseWrapper(ButtonBase button) {
		this.button = button;
		initWidget(button);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		clickHandlerRegistration = button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fireEvent(event);
			}
		});
	}

	protected abstract void fireEvent(ClickEvent event);
	
	@Override
	protected void onUnload() {
		clickHandlerRegistration.removeHandler();
		clickHandlerRegistration = null;
		super.onUnload();
	}
}