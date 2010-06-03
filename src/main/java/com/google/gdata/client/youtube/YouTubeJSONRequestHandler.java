package com.google.gdata.client.youtube;

import sk.seges.acris.json.client.IJsonizer;

import com.google.gdata.client.GoogleJsonizerBuilder;
import com.google.gdata.client.deserialize.SourceDeserializer;
import com.google.gdata.client.json.JSONRequestHandler;
import com.google.gdata.data.Source;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gwt.json.client.JSONObject;

public abstract class YouTubeJSONRequestHandler extends JSONRequestHandler {

	@Override
	public void onRequestComplete(JSONObject json) {
		GoogleJsonizerBuilder jsonizerBuilder = new GoogleJsonizerBuilder();
		jsonizerBuilder.registerDeserializer(Source.class, new SourceDeserializer());
		IJsonizer jsonnizer = jsonizerBuilder.create();
		VideoFeed videoFeed = jsonnizer.fromJson(json, VideoFeed.class);
		
//		VideoFeed videoFeed = new VideoFeed();
//		videoFeed.get(json.get(FEED_ATTRIBUTE).isObject());
		onRequestComplete(videoFeed);
	}

	public abstract void onRequestComplete(VideoFeed videoFeed);
}
