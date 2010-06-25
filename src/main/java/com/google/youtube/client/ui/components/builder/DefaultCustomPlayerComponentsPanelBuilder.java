package com.google.youtube.client.ui.components.builder;

import com.google.gdata.client.youtube.ui.UISchemeConstructor;
import com.google.youtube.client.ui.components.EOperations;
import com.google.youtube.client.ui.components.IDefaultCustomPlayerComponentsPanel;
import com.google.youtube.client.ui.components.api.ICustomPlayerComponents;

public class DefaultCustomPlayerComponentsPanelBuilder {
	
	public ICustomPlayerComponents createPlayerComponents(EOperations[] operations) {
		return createPlayerComponents(getDefaultUIConstructor(), operations);
	}

	public ICustomPlayerComponents createPlayerComponents(UISchemeConstructor uiConstructor, EOperations[] operations) {
		
		IDefaultCustomPlayerComponentsPanel customPlayerComponents = createDefaultComponentsPanel();

		for (EOperations supportedOperation : operations) {
			switch (supportedOperation) {
			case PLAY:
				customPlayerComponents.addPlayWidget(createPlayWidget(uiConstructor));
				break;
			case PAUSE:
				customPlayerComponents.addPauseWidget(createPauseWidget(uiConstructor));
				break;
			case STOP:
				customPlayerComponents.addStopWidget(createStopWidget(uiConstructor));
				break;
			case POSITION:
				customPlayerComponents.addStopWidget(createStopWidget(uiConstructor));
				break;
			}
			;
		}

		return customPlayerComponents;
	}

}
