package com.google.youtube.client.ui.controls.panel.basic;

import com.google.gdata.data.youtube.VideoEntry;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.youtube.client.event.chromeless.ChangeMediaSeekTimeEvent;
import com.google.youtube.client.event.chromeless.PauseMediaEvent;
import com.google.youtube.client.event.chromeless.PlayMediaEvent;
import com.google.youtube.client.event.chromeless.StopMediaEvent;
import com.google.youtube.client.event.chromeless.handler.ChangeMediaSeekTimeHandler;
import com.google.youtube.client.event.chromeless.handler.PauseMediaHandler;
import com.google.youtube.client.event.chromeless.handler.PlayMediaHandler;
import com.google.youtube.client.event.chromeless.handler.StopMediaHandler;
import com.google.youtube.client.ui.controls.api.IPauseControl;
import com.google.youtube.client.ui.controls.api.IPlayControl;
import com.google.youtube.client.ui.controls.api.ISeekTimeControl;
import com.google.youtube.client.ui.controls.api.IStopControl;
import com.google.youtube.client.ui.controls.api.ITimeControl;

public class DefaultPlayerControler extends FlowPanel implements IDefaultPlayerControler {

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

	private PlayTimer playTimer = new PlayTimer();

	private IPlayControl playControl;
	private IStopControl stopControl;
	private IPauseControl pauseControl;
	private ISeekTimeControl seekTimeControl;
	private ITimeControl timeControl;
	
	private int currentTime = 0;
	private int duration = 0;
	
	public DefaultPlayerControler() {
	}
	
	private void handleWidgetType(Object widget, String name) {
		if (!(widget instanceof Widget)) {
			throw new RuntimeException(name + " control is not a regular GWT Widget. " + name + " control should extends Widget component.");
		}
	}
	
	public void addPlayControl(IPlayControl playControl) {
		handleWidgetType(playControl, "Play");
		this.playControl = playControl;
		add((Widget)playControl);
	}

	@Override
	public HandlerRegistration addPlayHandler(PlayMediaHandler handler) {
		return playControl.addPlayHandler(handler);
	}

	public void addStopControl(IStopControl stopControl) {
		handleWidgetType(stopControl, "Stop");
		this.stopControl = stopControl;
		add((Widget)stopControl);
	}

	@Override
	public HandlerRegistration addStopHandler(StopMediaHandler handler) {
		return stopControl.addStopHandler(handler);
	}

	public void addPauseControl(IPauseControl pauseControl) {
		handleWidgetType(pauseControl, "Pause");
		this.pauseControl = pauseControl;
		add((Widget)pauseControl);
	}

	@Override
	public HandlerRegistration addPauseHandler(PauseMediaHandler handler) {
		return pauseControl.addPauseHandler(handler);
	}

	@Override
	public void addSeekTimeControl(ISeekTimeControl positionControl) {
		handleWidgetType(positionControl, "Position");
		this.seekTimeControl = positionControl;
		add((Widget)positionControl);
	}

	@Override
	public HandlerRegistration addChangeMediaSeekTimeHandler(ChangeMediaSeekTimeHandler handler) {
		return seekTimeControl.addChangeMediaSeekTimeHandler(handler);
	}

	@Override
	public void setSeekTime(int time) {
		seekTimeControl.setSeekTime(time);
		timeControl.setCurrentTime(time);
	}

	@Override
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
		
		if (stopControl != null) {
			stopControl.addStopHandler(new StopMediaHandler() {
				
				@Override
				public void onStop(StopMediaEvent event) {
					playTimer.cancel();
					currentTime = 0;
					if (seekTimeControl != null) {
						seekTimeControl.setSeekTime(0);
					}
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

	@Override
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