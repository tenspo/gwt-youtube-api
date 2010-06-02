package com.google.gdata.client.deserialize;

import com.google.gdata.data.TextConstruct;
import com.google.gwt.json.client.JSONValue;

import sk.seges.acris.json.client.context.DeserializationContext;
import sk.seges.acris.json.client.deserialization.BaseJsonDeserializer;

public class TextConstructDeserializer extends BaseJsonDeserializer<TextConstruct> {

	@Override
	public TextConstruct deserialize(JSONValue s, DeserializationContext context) {
		String textConstructString = _deserialize(s);
		if (textConstructString == null) {
			return null;
		}

		return TextConstruct.plainText(textConstructString);
	}
}