package com.google.gdata.client.youtube;

import sk.seges.acris.json.client.IJsonizer;

import com.google.gdata.client.GoogleJsonizerBuilder;
import com.google.gdata.client.deserialize.SourceDeserializer;
import com.google.gdata.client.json.JSONRequestHandler;
import com.google.gdata.data.Source;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gwt.json.client.JSONObject;

public abstract class YouTubeJSONRequestHandler extends JSONRequestHandler {

	private static final String FEED_ATTRIBUTE = "feed";
	
	@Override
	public void onRequestComplete(JSONObject json) {
		GoogleJsonizerBuilder jsonizerBuilder = new GoogleJsonizerBuilder();
		jsonizerBuilder.registerDeserializer(Source.class, new SourceDeserializer());
		IJsonizer<Source> jsonnizer = jsonizerBuilder.create();
		Source source = jsonnizer.fromJson(json.get(FEED_ATTRIBUTE), Source.class);

		VideoFeed videoFeed = new VideoFeed();
//		videoFeed.get(json.get(FEED_ATTRIBUTE).isObject());
		onRequestComplete(videoFeed);
	}

	public abstract void onRequestComplete(VideoFeed videoFeed);
}
