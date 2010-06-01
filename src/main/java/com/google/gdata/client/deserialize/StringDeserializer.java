package com.google.gdata.client.deserialize;

import sk.seges.acris.json.client.context.DeserializationContext;
import sk.seges.acris.json.client.deserialization.BaseJsonDeserializer;

import com.google.gwt.json.client.JSONValue;

public class StringDeserializer extends BaseJsonDeserializer<String> {

	@Override
	public String deserialize(JSONValue s, DeserializationContext context) {
		return _deserialize(s);
	}
}