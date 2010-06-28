package com.google.youtube.client.ui.controls.builder;

import com.google.youtube.client.ui.controls.api.IPlayerControler;
import com.google.youtube.client.ui.controls.factory.IDefaultPlayerControlsFactory;
import com.google.youtube.client.ui.controls.panel.basic.DefaultPlayerControler;
import com.google.youtube.client.ui.controls.panel.basic.IDefaultPlayerControler;

public class DefaultPlayerControlerBuilder {
	
	private IDefaultPlayerControlsFactory defaultPlayerControlsFactory;
	
	public DefaultPlayerControlerBuilder(IDefaultPlayerControlsFactory defaultPlayerControlsFactory) {
		this.defaultPlayerControlsFactory = defaultPlayerControlsFactory;
	}
	
	protected IDefaultPlayerControler createDefaultComponentsPanel() {
		return new DefaultPlayerControler();
	}

	public IPlayerControler createPlayerComponents(EOperations[] operations) {
		
		IDefaultPlayerControler customPlayerComponents = createDefaultComponentsPanel();

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