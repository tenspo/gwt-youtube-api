package com.google.youtube.client.ui.components;

import com.google.youtube.client.ui.components.api.ICustomPlayerComponents;
import com.google.youtube.client.ui.components.api.IPauseWidget;
import com.google.youtube.client.ui.components.api.IPlayWidget;
import com.google.youtube.client.ui.components.api.IPositionWidget;
import com.google.youtube.client.ui.components.api.ISeekWidget;
import com.google.youtube.client.ui.components.api.IStopWidget;

public interface IDefaultCustomPlayerComponentsPanel extends ICustomPlayerComponents {
	
	void addPlayWidget(IPlayWidget playWidget);

	void addStopWidget(IStopWidget stopWidget);

	void addPauseWidget(IPauseWidget pauseWidget);
	
	void addSeekWidget(ISeekWidget seekWidget);
	
	void addPositionWidget(IPositionWidget positionWidget);
}
