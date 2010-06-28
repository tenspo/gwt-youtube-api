package com.google.youtube.client.ui.controls.panel.youtube;

import com.google.youtube.client.ui.controls.api.IPauseControl;
import com.google.youtube.client.ui.controls.api.IPlayControl;
import com.google.youtube.client.ui.controls.api.IPlayerControler;
import com.google.youtube.client.ui.controls.api.IResizeControl;
import com.google.youtube.client.ui.controls.api.ISeekTimeControl;
import com.google.youtube.client.ui.controls.api.ITimeControl;
import com.google.youtube.client.ui.controls.api.IVolumeControl;

public interface IYouTubePlayerControler extends IPlayerControler {
	
	void addPlayControl(IPlayControl playControl);

	void addPauseControl(IPauseControl pauseControl);

	void addVolumeControl(IVolumeControl volumeControl);

	void addTimeControl(ITimeControl timeControl);

	void addSeekTimeControl(ISeekTimeControl positionControl);
	
	void setSeekTime(int time);

	void addResizeControl(IResizeControl resizeControl);
}