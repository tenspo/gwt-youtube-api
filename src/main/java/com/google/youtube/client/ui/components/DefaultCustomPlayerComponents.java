package com.google.youtube.client.ui.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.youtube.client.event.chromeless.handler.PauseMediaHandler;
import com.google.youtube.client.event.chromeless.handler.PlayMediaHandler;
import com.google.youtube.client.event.chromeless.handler.StopMediaHandler;
import com.google.youtube.client.ui.components.api.ICustomPlayerComponents;
import com.google.youtube.client.ui.components.api.IPauseWidget;
import com.google.youtube.client.ui.components.api.IPlayWidget;
import com.google.youtube.client.ui.components.api.IPositionWidget;
import com.google.youtube.client.ui.components.api.ISeekWidget;
import com.google.youtube.client.ui.components.api.IStopWidget;

public class DefaultCustomPlayerComponents extends FlowPanel implements IDefaultCustomPlayerComponentsPanel {

	private IPlayWidget playWidget;
	private IStopWidget stopWidget;
	private IPauseWidget pauseWidget;
	
	private void handleWidgetType(Object widget, String name) {
		if (widget instanceof Widget) {
			throw new RuntimeException(name + " widget is not a regular GWT Widget. " + name + " widget should extends Widget component.");
		}
	}
	
	public void addPlayWidget(IPlayWidget playWidget) {
		handleWidgetType(playWidget, "Play");
		this.playWidget = playWidget;
		add((Widget)playWidget);
	}

	@Override
	public HandlerRegistration addPlayHandler(PlayMediaHandler handler) {
		return playWidget.addPlayHandler(handler);
	}

	public void addStopWidget(IStopWidget stopWidget) {
		handleWidgetType(stopWidget, "Stop");
		this.stopWidget = stopWidget;
		add((Widget)stopWidget);
	}

	@Override
	public HandlerRegistration addStopHandler(StopMediaHandler handler) {
		return stopWidget.addStopHandler(handler);
	}

	public void addPauseWidget(IPauseWidget pauseWidget) {
		handleWidgetType(pauseWidget, "Pause");
		this.pauseWidget = pauseWidget;
		add((Widget)pauseWidget);
	}

	@Override
	public HandlerRegistration addPauseHandler(PauseMediaHandler handler) {
		return pauseWidget.addPauseHandler(handler);
	}

	@Override
	public void addPositionWidget(IPositionWidget positionWidget) {
		if (positionWidget instanceof Widget) {
			throw new RuntimeException("Position widget is not a regular GWT Widget. Position widget should extends Widget component.");
		}
		
		positionWidget.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//TODO position changed ... how ? :)
			}
		});
		
		add((Widget)positionWidget);
	}

	@Override
	public void addSeekWidget(ISeekWidget seekWidget) {
	}
}