package com.google.youtube.client.ui.controls.builder;

import com.google.youtube.client.ui.controls.factory.youtube.IYouTubePlayerControlsFactory;
import com.google.youtube.client.ui.controls.panel.youtube.IYouTubePlayerController;
import com.google.youtube.client.ui.controls.panel.youtube.YouTubePlayerController;

public class YouTubePlayerControllerBuilder {
	
	private IYouTubePlayerControlsFactory youtubePlayerControlsFactory;
	
	public YouTubePlayerControllerBuilder(IYouTubePlayerControlsFactory youtubePlayerControlsFactory) {
		this.youtubePlayerControlsFactory = youtubePlayerControlsFactory;
	}
	
	protected IYouTubePlayerController createDefaultComponentsPanel() {
		return new YouTubePlayerController();
	}

	public IYouTubePlayerController createPlayerComponents(EOperations[] operations) {
		
		IYouTubePlayerController customPlayerComponents = createDefaultComponentsPanel();

		for (EOperations supportedOperation : operations) {
			switch (supportedOperation) {
			case PLAY:
				customPlayerComponents.addPlayControl(youtubePlayerControlsFactory.createPlayControl());
				break;
			case PAUSE:
				customPlayerComponents.addPauseControl(youtubePlayerControlsFactory.createPauseControl());
				break;
			case SEEK:
				customPlayerComponents.addSeekTimeControl(youtubePlayerControlsFactory.createSeekTimeControl());
				break;
			case TIME:
				customPlayerComponents.addTimeControl(youtubePlayerControlsFactory.createTimeControl());
				break;
			case VOLUME:
				customPlayerComponents.addVolumeControl(youtubePlayerControlsFactory.createVolumeControl());
				break;
			};
		}

		return customPlayerComponents;
	}
}