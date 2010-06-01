package com.google.gdata.client.deserialize;

import sk.seges.acris.json.client.IJsonizer;
import sk.seges.acris.json.client.context.DeserializationContext;
import sk.seges.acris.json.client.deserialization.JsonDeserializer;

import com.google.gdata.data.Source;
import com.google.gwt.json.client.JSONValue;

public class SourceDeserializer extends JsonDeserializer<Source, JSONValue> {

	@Override
	public Source deserialize(JSONValue s, DeserializationContext context) {
		return new Source() {
			public Source fromJson(JSONValue s, DeserializationContext context) {
				IJsonizer<SourceState> sourceStateJsonizer = context.getJsonizer();
				sourceStateJsonizer.fromJson(s, this.srcState);
				return this;
			};
		}.fromJson(s, context);
	}
}
