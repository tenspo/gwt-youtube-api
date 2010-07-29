package com.google.youtube.client.ui.controls.youtube;

import com.google.youtube.client.ui.controls.SeekTimeControl;
import com.google.youtube.client.ui.controls.common.MouseAwareFlowPanel;


public class PositionControl extends SeekTimeControl {

	private static final String PANEL_STYLE_NAME = "youtube-time-position-panel";
	
	public PositionControl(MouseAwareFlowPanel sliderPanel) {
		super();
		addStyleName(PANEL_STYLE_NAME);
	}

}
