package com.google.youtube.client.ui.controls.panel.basic;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.youtube.client.event.chromeless.StopMediaEvent;
import com.google.youtube.client.event.chromeless.handler.StopMediaHandler;
import com.google.youtube.client.ui.controls.api.IStopControl;
import com.google.youtube.client.ui.controls.panel.AbstractPlayerController;

public class DefaultPlayerController extends AbstractPlayerController implements IDefaultPlayerController {

	private IStopControl stopControl;

		
	public DefaultPlayerController() {
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

	@Override
	protected void onLoad() {
		super.onLoad();

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
	}
	
}