package com.google.gdata.client.youtube.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.youtube.client.YouTubeEmbeddedPlayer;

public class YouTubeVideoPanel extends Composite {

	public static final String PLAYER_STYLE = "youtube-embeded-player"; 
	
	private FlowPanel fp;
	
	public YouTubeVideoPanel() {
		fp = new FlowPanel();
		initWidget(fp);
	}

	public void playVideo(String videoId, int width, int height) {
		YouTubeEmbeddedPlayer youTubeEmbeddedPlayer = new YouTubeEmbeddedPlayer(videoId);
		youTubeEmbeddedPlayer.setWidth(width + "px");
		youTubeEmbeddedPlayer.setHeight(height + "px");
		fp.add(youTubeEmbeddedPlayer);
	}
}
