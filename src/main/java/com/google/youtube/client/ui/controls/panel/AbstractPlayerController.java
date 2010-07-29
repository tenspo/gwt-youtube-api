package com.google.youtube.client.ui.controls.panel;

import com.google.gdata.data.youtube.VideoEntry;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.youtube.client.event.chromeless.ChangeMediaSeekTimeEvent;
import com.google.youtube.client.event.chromeless.PauseMediaEvent;
import com.google.youtube.client.event.chromeless.PlayMediaEvent;
import com.google.youtube.client.event.chromeless.handler.ChangeMediaSeekTimeHandler;
import com.google.youtube.client.event.chromeless.handler.PauseMediaHandler;
import com.google.youtube.client.event.chromeless.handler.PlayMediaHandler;
import com.google.youtube.client.ui.controls.api.IPauseControl;
import com.google.youtube.client.ui.controls.api.IPlayControl;
import com.google.youtube.client.ui.controls.api.ISeekTimeControl;
import com.google.youtube.client.ui.controls.api.ITimeControl;

public class AbstractPlayerController extends FlowPanel {
	
	protected IPlayControl playControl;
	protected IPauseControl pauseControl;
	protected ISeekTimeControl seekTimeControl;
	protected ITimeControl timeControl;

	protected class PlayTimer extends Timer {
		
		public PlayTimer() {
		}
		
		@Override
		public void run() {
			currentTime++;
			if (seekTimeControl != null) {
				seekTimeControl.setSeekTime(currentTime);
			}
			
			if (timeControl != null) {
				timeControl.setCurrentTime(currentTime);
			}
			if (currentTime >= duration) {
				cancel();
			}
		}		
	};

	protected PlayTimer playTimer = new PlayTimer();

	protected int currentTime = 0;
	protected int duration = 0;

	public HandlerRegistration addPlayHandler(PlayMediaHandler handler) {
		return playControl.addPlayHandler(handler);
	}

	public HandlerRegistration addPauseHandler(PauseMediaHandler handler) {
		return pauseControl.addPauseHandler(handler);
	}

	public HandlerRegistration addChangeMediaSeekTimeHandler(ChangeMediaSeekTimeHandler handler) {
		return seekTimeControl.addChangeMediaSeekTimeHandler(handler);
	}

	protected void handleWidgetType(Object widget, String name) {
		if (!(widget instanceof Widget)) {
			throw new RuntimeException(name + " control is not a regular GWT Widget. " + name + " control should extends Widget component.");
		}
	}
	
	public void addPlayControl(IPlayControl playControl) {
		handleWidgetType(playControl, "Play");
		this.playControl = playControl;
		add((Widget)playControl);
	}

	public void addPauseControl(IPauseControl pauseControl) {
		handleWidgetType(pauseControl, "Pause");
		this.pauseControl = pauseControl;
		add((Widget)pauseControl);
	}

	public void addSeekTimeControl(ISeekTimeControl positionControl) {
		handleWidgetType(positionControl, "Position");
		this.seekTimeControl = positionControl;
		add((Widget)positionControl);
	}

	public void setSeekTime(int time) {
		seekTimeControl.setSeekTime(time);
		timeControl.setCurrentTime(time);
	}

	public void addTimeControl(ITimeControl timeControl) {
		handleWidgetType(timeControl, "Time");
		this.timeControl = timeControl;
		add((Widget)timeControl);
	}
		
	@Override
	protected void onLoad() {
		super.onLoad();

		if (playControl != null) {
			playControl.addPlayHandler(new PlayMediaHandler() {
				@Override
				public void onPlay(PlayMediaEvent event) {
					playTimer.scheduleRepeating(1000);
				}
			});
		}
				
		if (pauseControl != null){ 
			pauseControl.addPauseHandler(new PauseMediaHandler() {
				
				@Override
				public void onPause(PauseMediaEvent event) {
					playTimer.cancel();
				}
			});
		}
		
		if (seekTimeControl != null) {
			seekTimeControl.addChangeMediaSeekTimeHandler(new ChangeMediaSeekTimeHandler() {
				
				@Override
				public void onChangeSeekTime(ChangeMediaSeekTimeEvent event) {
					currentTime = event.getPosition();
				}
			});
		}
	}
	
	@Override
	protected void onUnload() {
		playTimer.cancel();
		super.onUnload();
	}

	public void initializeFromEntry(VideoEntry entry) {
		this.duration = entry.getMediaGroup().getDuration().intValue();
		if (seekTimeControl != null) {
			seekTimeControl.setDuration(duration);
		}
		if (timeControl != null) {
			timeControl.setDuration(duration);
		}
	}
}
