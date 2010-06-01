package com.google.gdata.client;

import sk.seges.acris.json.client.JsonizerBuilder;

import com.google.gdata.client.deserialize.StringDeserializer;

public class GoogleJsonizerBuilder extends JsonizerBuilder {

	@Override
	protected void registerDefaultDeserializers() {
		registerDeserializer(String.class, new StringDeserializer());
	}
}