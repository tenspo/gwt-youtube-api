package com.google.youtube.client.ui.controls.factory.basic;

import com.google.youtube.client.ui.controls.api.IPauseControl;
import com.google.youtube.client.ui.controls.api.IPlayControl;
import com.google.youtube.client.ui.controls.api.ISeekTimeControl;
import com.google.youtube.client.ui.controls.api.IStopControl;
import com.google.youtube.client.ui.controls.api.ITimeControl;

public interface IDefaultPlayerControlsFactory {
	
	IPlayControl createPlayControl();

	IStopControl createStopControl();

	IPauseControl createPauseControl();

	ISeekTimeControl createSeekTimeControl();
	
	ITimeControl createTimeControl();
}