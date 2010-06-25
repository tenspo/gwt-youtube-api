package com.google.youtube.client.showcase;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.youtube.client.ChromelessYouTubePlayer;
import com.google.youtube.client.YouTubePlayer;
import com.google.youtube.client.ui.components.api.IPositionWidget;
import com.google.youtube.client.ui.components.factory.DefaultCustomPlayerComponentsFactory;

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
		
		ChromelessYouTubePlayer player = new ChromelessYouTubePlayer(DEMO_VIDEO_ID);
		player.setEnableJSAPI(true);
		player.setVersion(3);
		player.setPlayerAPIId("gwt-yt-api");
		player.setWidth("640px");
		player.setHeight("320px");
		container.add(player);

		final YouTubePlayer youTubePlayer = player.getPlayer();
		
		label = new Label("Player controls");
		container.add(label);
		
		DefaultCustomPlayerComponentsFactory componentsFactory = new DefaultCustomPlayerComponentsFactory();
		
		container.add(componentsFactory.createPlayWidget(youTubePlayer));
		container.add(componentsFactory.createPauseWidget(youTubePlayer));
		container.add(componentsFactory.createStopWidget(youTubePlayer));
		IPositionWidget positionWidget = componentsFactory.createPositionWidget(youTubePlayer);
		positionWidget.setWidth("300px");
		positionWidget.setHeight("10px");
		container.add((Widget)positionWidget);
		container.add((Widget)componentsFactory.createSeekToWidget(youTubePlayer));
	}
}