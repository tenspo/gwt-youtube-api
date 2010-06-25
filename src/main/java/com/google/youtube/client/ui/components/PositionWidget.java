/**
 * 
 */
package com.google.youtube.client.ui.components;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.youtube.client.ui.components.api.IPositionWidget;

public class PositionWidget extends FlowPanel implements IPositionWidget {

	static final String POSITION_PANEL_STYLE_NAME = "youtube-position-panel";
	static final String POSITION_PANEL_PLAYED_STYLE_NAME = ".youtube-position-played-panel";
	static final String POSITION_PANEL_REST_STYLE_NAME = ".youtube-position-rest-panel";

	private FlowPanel played = new FlowPanel();
	private FlowPanel rest = new FlowPanel();

	private int duration;
	private int currentTime;

	private String width;

	public PositionWidget(int duration) {
		this.duration = duration;

		setStyleName(POSITION_PANEL_STYLE_NAME);
		played.setStyleName(POSITION_PANEL_PLAYED_STYLE_NAME);
		rest.setStyleName(POSITION_PANEL_REST_STYLE_NAME);

		DOM.setStyleAttribute(played.getElement(), "height", "100%");
		DOM.setStyleAttribute(rest.getElement(), "height", "100%");
	}

	public int getCurrentTime() {
		return currentTime;
	}

	@Override
	protected void onLoad() {
		add(played);
		add(rest);
		super.onLoad();
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return null;
	}

	private void calculateSizes() {
		double ratio = currentTime == 0 ? 0 : (duration / currentTime);

		Integer widthInt = getStringDimension(width);

		double playedWidth = widthInt * ratio;
		played.setWidth(((int) playedWidth) + "px");
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
	public void setCurrentTime(int time) {
		this.currentTime = time;
		calculateSizes();
	}

	@Override
	public void setWidth(String width) {
		super.setWidth(width);
		this.width = width;
		calculateSizes();
	}

	@Override
	public void setDimension(String width, String height) {
		super.setWidth(width);
		this.width = width;
		setHeight(height);
		calculateSizes();
	}
}