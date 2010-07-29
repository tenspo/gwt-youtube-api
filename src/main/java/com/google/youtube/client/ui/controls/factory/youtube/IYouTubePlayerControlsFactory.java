package com.google.youtube.client.ui.controls.factory.youtube;

import com.google.youtube.client.ui.controls.api.IVolumeControl;
import com.google.youtube.client.ui.controls.factory.basic.IDefaultPlayerControlsFactory;

public interface IYouTubePlayerControlsFactory extends IDefaultPlayerControlsFactory {
	IVolumeControl createVolumeControl();
}
