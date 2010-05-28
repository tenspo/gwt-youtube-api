package com.google.gdata.data;

import org.gwttime.time.DateTime;
import org.gwttime.time.format.DateTimeFormat;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class JSON {

	protected DateTime getDateTime(JSONObject jsonObject, String name, String pattern) {
		String dateTimeString = getString(jsonObject, name);
		if (dateTimeString == null) {
			return null;
		}

		return DateTimeFormat.forPattern(pattern).parseDateTime(dateTimeString);
	}

	protected TextConstruct getText(JSONObject jsonObject, String name) {
		String text = getString(jsonObject, name);
		if (text == null) {
			return null;
		}
		return TextConstruct.create(TextConstruct.Type.TEXT, text);
	}

	protected static final String TEXT_PROPERTY_EXPRESSION = "$t";

	protected String getString(JSONObject jsonObject, String name) {
		if (!jsonObject.containsKey(name)) {
			return null;
		}
		JSONObject jsonStringObject = jsonObject.get(name).isObject();
		if (jsonStringObject == null) {
			return null;
		}

		JSONString jsonString1Try = jsonStringObject.isString();
		
		if (jsonString1Try != null) {
			return jsonString1Try.stringValue();
		}
		
		if (!jsonStringObject.containsKey(TEXT_PROPERTY_EXPRESSION)) {
			return null;
		}

		JSONString jsonString = jsonStringObject.get(TEXT_PROPERTY_EXPRESSION).isString();

		if (jsonString == null) {
			return null;
		}

		return jsonString.stringValue();
	}

	protected static boolean eq(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	/**
	 * @param o
	 *            given object
	 * @return true if the given object is not null and is the same concrete class as this one
	 */
	protected boolean sameClassAs(Object o) {
		return o != null && getClass().equals(o.getClass());
	}
}
