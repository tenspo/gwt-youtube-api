package com.google.gdata.client.youtube;

import com.google.gdata.client.json.JSONRequestHandler;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gwt.json.client.JSONObject;

public abstract class YouTubeJSONRequestHandler extends JSONRequestHandler {

	private static final String FEED_ATTRIBUTE = "feed";
	
	@Override
	public void onRequestComplete(JSONObject json) {
		VideoFeed videoFeed = new VideoFeed();
		videoFeed.get(json.get(FEED_ATTRIBUTE).isObject());
		onRequestComplete(videoFeed);
	}

	public abstract void onRequestComplete(VideoFeed videoFeed);
}
