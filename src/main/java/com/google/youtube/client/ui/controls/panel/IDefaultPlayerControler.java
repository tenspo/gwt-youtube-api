package com.google.youtube.client.ui.controls.panel;

import com.google.youtube.client.ui.controls.api.IPauseControl;
import com.google.youtube.client.ui.controls.api.IPlayControl;
import com.google.youtube.client.ui.controls.api.IPlayerControler;
import com.google.youtube.client.ui.controls.api.ISeekTimeControl;
import com.google.youtube.client.ui.controls.api.IStopControl;
import com.google.youtube.client.ui.controls.api.ITimeControl;

public interface IDefaultPlayerControler extends IPlayerControler {
	
	void addPlayControl(IPlayControl playControl);

	void addStopControl(IStopControl stopControl);

	void addPauseControl(IPauseControl pauseControl);
	
	void addSeekTimeControl(ISeekTimeControl positionControl);
	
	void setSeekTime(int time);

	void addTimeControl(ITimeControl timeControl);
}