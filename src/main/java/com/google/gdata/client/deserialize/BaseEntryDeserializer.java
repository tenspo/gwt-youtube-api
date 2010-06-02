package com.google.gdata.client.deserialize;

import sk.seges.acris.json.client.IJsonizer;
import sk.seges.acris.json.client.context.DeserializationContext;
import sk.seges.acris.json.client.deserialization.JsonDeserializer;

import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.BaseEntry.EntryState;
import com.google.gwt.json.client.JSONValue;

public abstract class BaseEntryDeserializer<T extends BaseEntry> extends JsonDeserializer<T, JSONValue> {
	
	protected void fromJson(EntryState entryState, JSONValue s, DeserializationContext context) {
		IJsonizer entryStateJsonizer = context.getJsonizer();
		entryStateJsonizer.fromJson(s, entryState);
	}
}
