package com.google.youtube.client.showcase;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.youtube.client.YouTubeEmbeddedPlayer;
import com.google.youtube.client.api.YouTubePlayer;
import com.google.youtube.client.ui.DefaultCustomPlayerComponentsFactory;

/**
 * @author PSimun
 */
public class ChromelessPlayerShowcase implements EntryPoint {

	private static final String DEMO_VIDEO_ID = "hqXUKxJiDls";
	
	@Override
	public void onModuleLoad() {
		
		HorizontalPanel panelHolder = new HorizontalPanel();
		RootPanel.get().add(panelHolder);
		
		VerticalPanel container = new VerticalPanel();
		panelHolder.add(container);
		
		Label label = new Label("YouTube Player Demo");
		container.add(label);
		label = new Label("This page demonstrates the YouTube Player API's functions for Google Web Toolkit.");
		container.add(label);
		
		YouTubeEmbeddedPlayer player = new YouTubeEmbeddedPlayer(DEMO_VIDEO_ID);
		player.setEnableJSAPI(true);
		player.setVersion(3);
		player.setPlayerAPIId("gwt-yt-api");
		player.setWidth("640px");
		player.setHeight("320px");
		container.add(player);

		final YouTubePlayer youTubePlayer = player.getPlayer();
		
		label = new Label("Player controls");
		container.add(label);
		
		HasClickHandlers playWidget = DefaultCustomPlayerComponentsFactory.createPauseWidget();
		playWidget.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				youTubePlayer.playMedia();
			}
		});
		container.add(label);

		HasClickHandlers stopWidget = DefaultCustomPlayerComponentsFactory.createStopWidget();
		stopWidget.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				youTubePlayer.stopMedia();
			}
		});
		container.add(label);
	}
}