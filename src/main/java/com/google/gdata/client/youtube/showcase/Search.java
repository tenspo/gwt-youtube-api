package com.google.gdata.client.youtube.showcase;

import com.google.gdata.client.Query;
import com.google.gdata.client.QueryPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Search extends AbstractShowcase implements EntryPoint {

	protected QueryPage getDefaultQueryPage() {
		return new QueryPage(1, 3, Query.UNDEFINED);
	}

	@Override
	public void onModuleLoad() {
		RootPanel.get().add(createSearchPanel());
	}
}