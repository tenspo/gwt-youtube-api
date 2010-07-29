package com.google.youtube.client.ui.controls.builder;

import com.google.youtube.client.ui.controls.api.IPlayerController;
import com.google.youtube.client.ui.controls.factory.basic.IDefaultPlayerControlsFactory;
import com.google.youtube.client.ui.controls.panel.basic.DefaultPlayerController;
import com.google.youtube.client.ui.controls.panel.basic.IDefaultPlayerController;

public class DefaultPlayerControllerBuilder {
	
	private IDefaultPlayerControlsFactory defaultPlayerControlsFactory;
	
	public DefaultPlayerControllerBuilder(IDefaultPlayerControlsFactory defaultPlayerControlsFactory) {
		this.defaultPlayerControlsFactory = defaultPlayerControlsFactory;
	}
	
	protected IDefaultPlayerController createDefaultComponentsPanel() {
		return new DefaultPlayerController();
	}

	public IPlayerController createPlayerComponents(EOperations[] operations) {
		
		IDefaultPlayerController customPlayerComponents = createDefaultComponentsPanel();

		for (EOperations supportedOperation : operations) {
			switch (supportedOperation) {
			case PLAY:
				customPlayerComponents.addPlayControl(defaultPlayerControlsFactory.createPlayControl());
				break;
			case PAUSE:
				customPlayerComponents.addPauseControl(defaultPlayerControlsFactory.createPauseControl());
				break;
			case STOP:
				customPlayerComponents.addStopControl(defaultPlayerControlsFactory.createStopControl());
				break;
			case SEEK:
				customPlayerComponents.addSeekTimeControl(defaultPlayerControlsFactory.createSeekTimeControl());
				break;
			case TIME:
				customPlayerComponents.addTimeControl(defaultPlayerControlsFactory.createTimeControl());
				break;
			};
		}

		return customPlayerComponents;
	}
}