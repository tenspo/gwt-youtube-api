package com.google.youtube.client.ui.controls.panel.youtube;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.youtube.client.event.chromeless.PauseMediaEvent;
import com.google.youtube.client.event.chromeless.PlayMediaEvent;
import com.google.youtube.client.event.chromeless.handler.PauseMediaHandler;
import com.google.youtube.client.event.chromeless.handler.PlayMediaHandler;
import com.google.youtube.client.event.chromeless.handler.StopMediaHandler;
import com.google.youtube.client.event.chromeless.handler.VolumeChangeHandler;
import com.google.youtube.client.ui.controls.api.IPauseControl;
import com.google.youtube.client.ui.controls.api.IResizeControl;
import com.google.youtube.client.ui.controls.api.IVolumeControl;
import com.google.youtube.client.ui.controls.panel.AbstractPlayerController;

public class YouTubePlayerController extends AbstractPlayerController implements IYouTubePlayerController {

	private IVolumeControl volumeControl;
	
	public void addPauseControl(IPauseControl pauseControl) {
		handleWidgetType(pauseControl, "Pause");
		this.pauseControl = pauseControl;
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		if (playControl != null) {
			playControl.addPlayHandler(new PlayMediaHandler() {
				@Override
				public void onPlay(PlayMediaEvent event) {
					insert((Widget)pauseControl, 0);
					((Widget)playControl).removeFromParent();
				}
			});
		}

		if (pauseControl != null){ 
			pauseControl.addPauseHandler(new PauseMediaHandler() {
				
				@Override
				public void onPause(PauseMediaEvent event) {
					insert((Widget)playControl, 0);
					((Widget)pauseControl).removeFromParent();
				}
			});
		}
	}
	
	@Override
	public HandlerRegistration addStopHandler(StopMediaHandler handler) {
		throw new IllegalArgumentException("Stop control is not supported for youtube player.");
	}

	@Override
	public void addVolumeControl(IVolumeControl volumeControl) {
		handleWidgetType(volumeControl, "Volume");
		this.volumeControl = volumeControl;
		add((Widget)volumeControl);
	}

	@Override
	public HandlerRegistration addVolumeHandler(VolumeChangeHandler handler) {
		return volumeControl.addVolumeHandler(handler);
	}

	@Override
	public void addResizeControl(IResizeControl resizeControl) {
	}
}
