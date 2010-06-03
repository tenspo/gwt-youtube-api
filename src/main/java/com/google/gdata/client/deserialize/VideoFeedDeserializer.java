package com.google.gdata.client.deserialize;

import sk.seges.acris.json.client.context.DeserializationContext;

import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class VideoFeedDeserializer extends BaseFeedDeserializer<VideoFeed, VideoEntry> {

	private static final String FEED_JSON_KEY = "feed";
	private static final String ITEM_JSON_KEY = "items";
	
	private SourceDeserializer sourceDeserializer = new SourceDeserializer();
	
	@Override
	public VideoFeed deserialize(JSONObject s, DeserializationContext context) {
		return new VideoFeed() {
			public VideoFeed fromJson(JSONObject s, DeserializationContext context) {
				JSONValue jsonValue = s.get(FEED_JSON_KEY);
				if (jsonValue != null && jsonValue.isObject() != null) {
					VideoFeedDeserializer.this.fromJson(this.feedState, this.entries, jsonValue.isObject(), context);
					sourceDeserializer.fromJson(this.srcState, jsonValue.isObject(), context);
				} else {
					jsonValue = s.get(ITEM_JSON_KEY);
					if (jsonValue != null && jsonValue.isObject() != null) {
						VideoFeedDeserializer.this.fromJson(this.feedState, this.entries, jsonValue.isObject(), context);
						sourceDeserializer.fromJson(this.srcState, jsonValue.isObject(), context);
					}
					//No feed, no fun
				}
				return this;
			};
		}.fromJson(s, context);
	}

	@Override
	protected Class<VideoEntry> getEntryClass() {
		return VideoEntry.class;
	}
}