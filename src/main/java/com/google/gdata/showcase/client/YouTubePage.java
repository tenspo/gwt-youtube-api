package com.google.gdata.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.youtube.client.YouTubeEmbeddedPlayer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class YouTubePage implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		YouTubeEmbeddedPlayer youTubeEmbeddedPlayer = new YouTubeEmbeddedPlayer("hqXUKxJiDls");
		youTubeEmbeddedPlayer.setWidth("427px");
		youTubeEmbeddedPlayer.setHeight("320px");
		RootPanel.get().add(youTubeEmbeddedPlayer);

	}
}
