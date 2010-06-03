package com.google.gdata.client.deserialize;

import sk.seges.acris.json.client.context.DeserializationContext;

import com.google.gdata.data.youtube.VideoEntry;
import com.google.gwt.json.client.JSONValue;

public class VideoEntryDeserializer extends MediaEntryDeserializer<VideoEntry> {

	@Override
	public VideoEntry deserialize(JSONValue s, DeserializationContext context) {
		return new VideoEntry() {
			public VideoEntry fromJson(JSONValue s, DeserializationContext context) {
				VideoEntryDeserializer.this.fromJson(this.state, s, context);
				return (VideoEntry)this;
			};
		}.fromJson(s, context);
	}
}
