package com.google.gdata.client;


public class QueryPage {

	public static QueryPage UNLIMITED_PAGE = new QueryPage(1, Query.UNDEFINED, Query.UNDEFINED);

	public int totalResults = Query.UNDEFINED;

	public int startIndex = Query.UNDEFINED;

	public int itemsPerPage = Query.UNDEFINED;

	public QueryPage(int startIndex, int itemsPerPage, int totalResults) {
		this.itemsPerPage = itemsPerPage;
		this.startIndex = startIndex;
		this.totalResults = totalResults;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
}