package com.google.gdata.client;

public class QueryPage {

	int maxResults = Query.UNDEFINED;

	int startIndex = Query.UNDEFINED;

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
}