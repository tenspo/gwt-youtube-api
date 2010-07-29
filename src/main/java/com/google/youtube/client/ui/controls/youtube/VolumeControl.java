package com.google.youtube.client.ui.controls.youtube;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.youtube.client.event.chromeless.VolumeChangeEvent;
import com.google.youtube.client.event.chromeless.handler.VolumeChangeHandler;
import com.google.youtube.client.ui.controls.api.IVolumeControl;
import com.google.youtube.client.ui.controls.common.MouseAwareFlowPanel;

public class VolumeControl extends FlowPanel implements IVolumeControl {

	protected HandlerRegistration overHandlerRegistration;
	protected HandlerRegistration outHandlerRegistration;
	protected HandlerRegistration overSliderHandlerRegistration;
	protected HandlerRegistration outSliderHandlerRegistration;
	protected HandlerRegistration clickHandlerRegistration;

	protected final ButtonBase button;
	protected final MouseAwareFlowPanel sliderPanel;

	private boolean cancelHideHover = false;

	private static final String SLIDER_PANEL_STYLE_NAME = "youtube-volume-slider-panel";

	public VolumeControl(ButtonBase button, MouseAwareFlowPanel sliderPanel) {
		this.button = button;
		this.sliderPanel = sliderPanel;
		sliderPanel.setStyleName(SLIDER_PANEL_STYLE_NAME);
		add(sliderPanel);
		add(button);
		sliderPanel.setVisible(false);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		overHandlerRegistration = button.addMouseOverHandler(new MouseOverHandler() {

			@Override
			public void onMouseOver(MouseOverEvent event) {
				cancelHideHover = true;
				sliderPanel.setVisible(true);
			}
		});

		overSliderHandlerRegistration = sliderPanel.addMouseOverHandler(new MouseOverHandler() {

			@Override
			public void onMouseOver(MouseOverEvent event) {
				cancelHideHover = true;
				sliderPanel.setVisible(true);
			}
		});

		outHandlerRegistration = button.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				cancelHideHover = false;
				sliderPanel.setVisible(false);
			}
		});

		outSliderHandlerRegistration = sliderPanel.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				cancelHideHover = false;
				showSlider(false);
			}
		});

		clickHandlerRegistration = button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				VolumeChangeEvent.fire(VolumeControl.this);
			}
		});
	}

	protected void showSlider(boolean visible) {
		if (!visible && cancelHideHover) {
			return;
		}
		if (sliderPanel.isVisible() == visible) {
			return;
		}
		sliderPanel.setVisible(visible);
	}

	@Override
	protected void onUnload() {
		overHandlerRegistration.removeHandler();
		overHandlerRegistration = null;
		outHandlerRegistration.removeHandler();
		outHandlerRegistration = null;
		clickHandlerRegistration.removeHandler();
		clickHandlerRegistration = null;
		outSliderHandlerRegistration.removeHandler();
		outSliderHandlerRegistration = null;
		overSliderHandlerRegistration.removeHandler();
		overSliderHandlerRegistration = null;
		super.onUnload();
	}

	@Override
	public HandlerRegistration addVolumeHandler(VolumeChangeHandler handler) {
		return addHandler(handler, VolumeChangeEvent.getType());
	}
}