package com.google.youtube.client;

import com.google.youtube.client.api.AbstractYouTubePlayer;

public class ChromelessYouTubePlayer extends AbstractYouTubePlayer {

	public ChromelessYouTubePlayer(String videoId) {
		super(videoId);
	}

	protected String getURLParams() {
		String params = super.getURLParams();
		params = addStringParam(getVideoId(), "video_id", params);
		return params;
	}
	
	protected String getURL() {
		String url = "http://www.youtube.com/apiplayer";
			
		String urlParams = getURLParams();
		if (urlParams.length() > 0) {
			return url + "?" + urlParams;
		}
		
		return url;
	}

}