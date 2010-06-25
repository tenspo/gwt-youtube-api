/**
 * 
 */
package com.google.youtube.client.ui.components.api;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface IPositionWidget extends HasClickHandlers {

	int getCurrentTime();

	void setCurrentTime(int time);

	void setWidth(String width);

	void setHeight(String height);

	void setDimension(String width, String height);
}