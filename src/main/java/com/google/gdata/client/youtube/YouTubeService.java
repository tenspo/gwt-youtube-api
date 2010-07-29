package com.google.gdata.client.youtube;

import com.google.gdata.client.Query;
import com.google.gdata.client.Service;
import com.google.gdata.client.json.JSONRequest;
import com.google.gdata.client.json.JSONRequestHandler;

public class YouTubeService extends Service {

	public YouTubeService() {
	}

	public <T> void query(Query query, JSONRequestHandler handler) {
		JSONRequest jsonRequest = new JSONRequest();
		jsonRequest.get(query.getUrl(), handler);
	}
}