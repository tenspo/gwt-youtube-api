package com.google.youtube.client.ui.controls.factory.basic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.youtube.client.YouTubeVideoComponents;
import com.google.youtube.client.ui.CustomizableUIComponent;
import com.google.youtube.client.ui.controls.PauseButton;
import com.google.youtube.client.ui.controls.PlayButton;
import com.google.youtube.client.ui.controls.SeekTimeControl;
import com.google.youtube.client.ui.controls.StopButton;
import com.google.youtube.client.ui.controls.TimeControl;
import com.google.youtube.client.ui.controls.api.IPauseControl;
import com.google.youtube.client.ui.controls.api.IPlayControl;
import com.google.youtube.client.ui.controls.api.ISeekTimeControl;
import com.google.youtube.client.ui.controls.api.IStopControl;
import com.google.youtube.client.ui.controls.api.ITimeControl;

/**
 * @author PSimun
 * 
 */
public class DefaultPlayerControlsFactory extends CustomizableUIComponent implements IDefaultPlayerControlsFactory {

	private static YouTubeVideoComponents youTubeVideoComponents = GWT.create(YouTubeVideoComponents.class);

	private static final String PAUSE_BUTTON_STYLE_NAME = "default-pause-button";
	private static final String PLAY_BUTTON_STYLE_NAME = "default-play-button";
	private static final String STOP_BUTTON_STYLE_NAME = "default-stop-button";

	public IPlayControl createPlayControl() {
		Button button = ensureUIConstructor().constructButton(youTubeVideoComponents.play());
		button.setStyleName(PLAY_BUTTON_STYLE_NAME);
		return new PlayButton(button);
	}

	public IStopControl createStopControl() {
		Button button = ensureUIConstructor().constructButton(youTubeVideoComponents.stop());
		button.setStyleName(STOP_BUTTON_STYLE_NAME);
		return new StopButton(button);
	}

	public IPauseControl createPauseControl() {
		Button button = ensureUIConstructor().constructButton(youTubeVideoComponents.pause());
		button.setStyleName(PAUSE_BUTTON_STYLE_NAME);
		return new PauseButton(button);
	}

	public ISeekTimeControl createSeekTimeControl() {
		SeekTimeControl seekFlowWidget = new SeekTimeControl();
		seekFlowWidget.setWidth("300px");
		seekFlowWidget.setHeight("10px");
		return seekFlowWidget;
	}

	@Override
	public ITimeControl createTimeControl() {
		TimeControl timeControl = new TimeControl(60);
		timeControl.setCurrentTime(0);
		return timeControl;
	}
}