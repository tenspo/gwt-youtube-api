package com.google.gdata.client.json;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public abstract class JSONRequestHandler {
	
	public void onRequestComplete(JavaScriptObject jso) {
		onRequestComplete(new JSONObject(jso));
	}

	public abstract void onRequestComplete(JSONObject json);
}