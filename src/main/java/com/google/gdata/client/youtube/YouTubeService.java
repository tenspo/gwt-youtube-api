package com.google.gdata.client.youtube;

import com.google.gdata.client.Query;
import com.google.gdata.client.Service;
import com.google.gdata.client.json.JSONRequest;
import com.google.gdata.client.json.JSONRequestHandler;
import com.google.gdata.data.IFeed;

public class YouTubeService extends Service {

	public YouTubeService(String clientId) {

	}

	public <T extends IFeed> T query(Query query, Class<T> clazz, JSONRequestHandler handler) {

		JSONRequest jsonRequest = new JSONRequest();
		jsonRequest.get(query.getUrl(), handler);
		return null;
	}
}