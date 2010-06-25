package com.google.youtube.client.ui.components.factory;

import com.google.youtube.client.ui.components.api.IPauseWidget;
import com.google.youtube.client.ui.components.api.IPlayWidget;
import com.google.youtube.client.ui.components.api.IPositionWidget;
import com.google.youtube.client.ui.components.api.IStopWidget;

public interface IDefaultCustomPlayerComponentsFactory {
	
	IPlayWidget createPlayWidget();

	IStopWidget createStopWidget();

	IPauseWidget createPauseWidget();

	IPositionWidget createPositionWidget();
}