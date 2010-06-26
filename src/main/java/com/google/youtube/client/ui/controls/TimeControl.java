package com.google.youtube.client.ui.controls;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.youtube.client.ui.controls.api.ITimeControl;

public class TimeControl extends FlowPanel implements ITimeControl {

	static final String TIME_PANEL_STYLE_NAME = "youtube-time-panel";
	static final String CURRENT_TIME_PANEL_STYLE_NAME = "youtube-current-time-panel";
	static final String DURATION_PANEL_STYLE_NAME = "youtube-duration-panel";

	private Label currentTimeLabel = new Label();
	private Label durationLabel = new Label();
	
	public TimeControl(int duration) {
		currentTimeLabel.setStyleName(CURRENT_TIME_PANEL_STYLE_NAME);
		add(currentTimeLabel);
		durationLabel.setStyleName(DURATION_PANEL_STYLE_NAME);
		add(durationLabel);
		setStyleName(TIME_PANEL_STYLE_NAME);
	}

	protected String formatString(int time, int size) {
		String result = "" + time;
		for (int i = 0; i < size; i++) {
			result = "0" + result;
		}
		
		return result.substring(result.length()-2);
	}
	
	protected String formatTime(int time) {
		int seconds = time % 60;
		time = time / 60;
		int minutes = time % 60;
		time = time / 60;
		int hours = time;
		return (hours > 0 ? formatString(hours,2) + ":" : "") + 
		       formatString(minutes,2) + ":"  + 
		       formatString(seconds,2);
	}
	
	@Override
	public void setCurrentTime(int time) {
		currentTimeLabel.setText(formatTime(time));
	}

	@Override
	public void setDuration(int duration) {
		durationLabel.setText(" / " + formatTime(duration));
	}
}