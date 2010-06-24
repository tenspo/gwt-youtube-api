package com.google.youtube.client;

import com.google.youtube.client.api.AbstractYouTubePlayer;

/**
 * @author psimun
 * 
 */
public class YouTubeEmbeddedPlayer extends AbstractYouTubePlayer {

	public YouTubeEmbeddedPlayer(String videoId) {
		super(videoId);
	}

	protected String getURL() {
		String url = "http://www.youtube.com/v/" + getVideoId();

		String urlParams = getURLParams();
		if (urlParams.length() > 0) {
			return url + "?" + urlParams;
		}
		return url;
	}

}