package com.google.youtube.client.ui.controls.panel.basic;

import com.google.youtube.client.ui.controls.api.IPauseControl;
import com.google.youtube.client.ui.controls.api.IPlayControl;
import com.google.youtube.client.ui.controls.api.IPlayerController;
import com.google.youtube.client.ui.controls.api.ISeekTimeControl;
import com.google.youtube.client.ui.controls.api.IStopControl;
import com.google.youtube.client.ui.controls.api.ITimeControl;

public interface IDefaultPlayerController extends IPlayerController {
	
	void addPlayControl(IPlayControl playControl);

	void addStopControl(IStopControl stopControl);

	void addPauseControl(IPauseControl pauseControl);
	
	void addSeekTimeControl(ISeekTimeControl positionControl);
	
	void setSeekTime(int time);

	void addTimeControl(ITimeControl timeControl);
}