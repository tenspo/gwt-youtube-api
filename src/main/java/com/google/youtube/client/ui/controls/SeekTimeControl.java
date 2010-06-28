/**
 * 
 */
package com.google.youtube.client.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.youtube.client.event.chromeless.ChangeMediaSeekTimeEvent;
import com.google.youtube.client.event.chromeless.handler.ChangeMediaSeekTimeHandler;
import com.google.youtube.client.ui.controls.api.ISeekTimeControl;

public class SeekTimeControl extends FlowPanel implements ISeekTimeControl {

	static final String POSITION_PANEL_STYLE_NAME = "youtube-position-panel";
	static final String POSITION_PANEL_PLAYED_STYLE_NAME = "youtube-position-played-panel";
	static final String POSITION_PANEL_REST_STYLE_NAME = "youtube-position-rest-panel";

	private FlowPanel played = new FlowPanel();
	private FlowPanel rest = new FlowPanel();

	private double duration;
	private double currentTime;

	private String width;

	public SeekTimeControl() {
		setStyleName(POSITION_PANEL_STYLE_NAME);
		played.setStyleName(POSITION_PANEL_PLAYED_STYLE_NAME);
		rest.setStyleName(POSITION_PANEL_REST_STYLE_NAME);

		DOM.setStyleAttribute(played.getElement(), "height", "100%");
		DOM.setStyleAttribute(rest.getElement(), "height", "100%");
	}

	public double getCurrentTime() {
		return currentTime;
	}

    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

	@Override
	protected void onLoad() {
		add(played);
		add(rest);
		addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				int x = getElement().getAbsoluteLeft();
				int xCoord = event.getNativeEvent().getClientX();
				
				Integer widthInt = getStringDimension(width);
				
				currentTime = ((xCoord - x) * duration) / widthInt;
				calculateSizes();
				
				ChangeMediaSeekTimeEvent.fire(SeekTimeControl.this, (int)currentTime);
			}
		});
		super.onLoad();
	}

	private void calculateSizes() {
		double ratio = currentTime == 0 ? 0 : (duration / (currentTime));

		double playedPercentage = ratio == 0 ? 0 : (100 / ratio);
		played.setWidth(playedPercentage + "%");
		rest.setWidth((100-playedPercentage) + "%");
	}

	public Integer getStringDimension(String dimension) {
		// percentage or other units are not allowed
		if (dimension.indexOf("px") != -1) {
			String dimensionNumber = dimension.replaceAll("px", "").trim();
			try {
				return Integer.parseInt(dimensionNumber);
			} catch (NumberFormatException e) {
			}
		}

		try {
			return Integer.parseInt(dimension);
		} catch (NumberFormatException e) {
		}

		return null;
	}

	@Override
	public void setSeekTime(int time) {
		this.currentTime = time;
		calculateSizes();
	}

	@Override
	public void setWidth(String width) {
		super.setWidth(width);
		this.width = width;
		calculateSizes();
	}

	public void setDimension(String width, String height) {
		super.setWidth(width);
		this.width = width;
		setHeight(height);
		calculateSizes();
	}

	@Override
	public HandlerRegistration addChangeMediaSeekTimeHandler(ChangeMediaSeekTimeHandler handler) {
		return addHandler(handler, ChangeMediaSeekTimeEvent.getType());
	}

	@Override
	public void setDuration(int duration) {
		this.duration = duration;
	}
}