package com.google.youtube.client.ui.controls.manager;

import com.google.youtube.client.api.IMediaPlayer;
import com.google.youtube.client.event.chromeless.ChangeMediaSeekTimeEvent;
import com.google.youtube.client.event.chromeless.PauseMediaEvent;
import com.google.youtube.client.event.chromeless.PlayMediaEvent;
import com.google.youtube.client.event.chromeless.StopMediaEvent;
import com.google.youtube.client.event.chromeless.handler.ChangeMediaSeekTimeHandler;
import com.google.youtube.client.event.chromeless.handler.PauseMediaHandler;
import com.google.youtube.client.event.chromeless.handler.PlayMediaHandler;
import com.google.youtube.client.event.chromeless.handler.StopMediaHandler;
import com.google.youtube.client.ui.controls.api.IPlayerControler;

public class PlayerControlsManager {

	private final IPlayerControler playerComponents;
	private final IMediaPlayer mediaPlayer;

	public PlayerControlsManager(IPlayerControler playerComponents, final IMediaPlayer mediaPlayer) {
		this.playerComponents = playerComponents;
		this.mediaPlayer = mediaPlayer;
	}

	public void manageEvents() {
		playerComponents.addPlayHandler(new PlayMediaHandler() {

			@Override
			public void onPlay(PlayMediaEvent event) {
				mediaPlayer.playMedia();
			}
		});

		playerComponents.addStopHandler(new StopMediaHandler() {

			@Override
			public void onStop(StopMediaEvent event) {
				mediaPlayer.stopMedia();
			}
		});

		playerComponents.addPauseHandler(new PauseMediaHandler() {

			@Override
			public void onPause(PauseMediaEvent event) {
				mediaPlayer.pauseMedia();
			}
		});
		
		playerComponents.addChangeMediaSeekTimeHandler(new ChangeMediaSeekTimeHandler() {
			
			@Override
			public void onChangeSeekTime(ChangeMediaSeekTimeEvent event) {
				mediaPlayer.setPlayPosition(event.getPosition());
			}
		});
	}
}