package com.google.gdata.client.deserialize;

import java.util.List;

import sk.seges.acris.json.client.IJsonizer;
import sk.seges.acris.json.client.context.DeserializationContext;
import sk.seges.acris.json.client.deserialization.JsonDeserializer;

import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.BaseFeed.FeedState;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public abstract class BaseFeedDeserializer<T extends BaseFeed<?>, E extends BaseEntry> extends JsonDeserializer<T, JSONObject> {

	public static final String ENTRY_JSON_KEY = "entry";
	
	protected abstract Class<E> getEntryClass();
	
	protected void fromJson(FeedState state, List<E> entries, JSONObject s, DeserializationContext context) {
		IJsonizer feedStateJsonizer = context.getJsonizer();
		feedStateJsonizer.fromJson(s, state, context);
		
		JSONValue jsonEntryValue = s.isObject().get(ENTRY_JSON_KEY);
		if (jsonEntryValue != null && jsonEntryValue.isArray() != null) {
			IJsonizer videoEntryJsonizer = context.getJsonizer();
			videoEntryJsonizer.fromJson(jsonEntryValue.isArray(), getEntryClass(), entries, context);
		}
	}
}