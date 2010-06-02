package com.google.gdata.client;

import sk.seges.acris.json.client.JsonizerBuilder;

import com.google.gdata.client.deserialize.SourceDeserializer;
import com.google.gdata.client.deserialize.StringDeserializer;
import com.google.gdata.client.deserialize.TextConstructDeserializer;
import com.google.gdata.client.deserialize.VideoEntryDeserializer;
import com.google.gdata.client.deserialize.VideoFeedDeserializer;
import com.google.gdata.data.Source;
import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;

public class GoogleJsonizerBuilder extends JsonizerBuilder {

	@Override
	protected void registerDefaultDeserializers() {
		super.registerDefaultDeserializers();
		registerDeserializer(String.class, new StringDeserializer());
		registerDeserializer(TextConstruct.class, new TextConstructDeserializer());

		registerDeserializer(Source.class, new SourceDeserializer());

		registerDeserializer(VideoEntry.class, new VideoEntryDeserializer());
		registerDeserializer(VideoFeed.class, new VideoFeedDeserializer());
	}
}