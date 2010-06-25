package com.google.youtube.client.ui.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;

public abstract class AbstractButtonWrapper extends Composite {

	protected HandlerRegistration clickHandlerRegistration;

	protected Button button;

	public AbstractButtonWrapper(Button button) {
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