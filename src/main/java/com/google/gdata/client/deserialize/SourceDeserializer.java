package com.google.gdata.client.deserialize;

import sk.seges.acris.json.client.IJsonizer;
import sk.seges.acris.json.client.context.DeserializationContext;
import sk.seges.acris.json.client.deserialization.JsonDeserializer;

import com.google.gdata.data.Source;
import com.google.gdata.data.Source.SourceState;
import com.google.gwt.json.client.JSONValue;

public class SourceDeserializer extends JsonDeserializer<Source, JSONValue> {

	protected SourceState fromJson(SourceState state, JSONValue s, DeserializationContext context) {
		IJsonizer sourceStateJsonizer = context.getJsonizer();
		sourceStateJsonizer.fromJson(s, state);
		return state;
	}

	@Override
	public Source deserialize(JSONValue s, DeserializationContext context) {
		return new Source() {
			public Source fromJson(JSONValue s, DeserializationContext context) {
				SourceDeserializer.this.fromJson(this.srcState, s, context);
				return this;
			};
		}.fromJson(s, context);
	}
}
