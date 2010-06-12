package com.google.gdata.client.youtube.showcase;

import com.google.gdata.client.Query;
import com.google.gdata.client.QueryPage;
import com.google.gdata.client.youtube.ui.YouTubeSearchPanel;
import com.google.gdata.client.youtube.ui.YouTubeSearchResultPanel;
import com.google.gdata.client.youtube.ui.YouTubeSearchPanel.SearchEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

abstract class AbstractShowcase {

	private static final String YOUTUBE_LOGO_STYLE = "youtube-logo";
	private static final String YOUTUBE_SEARCH_PANEL = "youtube-search-panel";

	protected String getYouTubeLogo() {
		return "http://www.youtube.com/img/pic_youtubelogo_123x63.gif";
	}

	protected QueryPage getDefaultQueryPage() {
		return new QueryPage(1, 5, Query.UNDEFINED);
	}
	
	protected HorizontalPanel createSearchPanel() {
		return createSearchPanel(null);
	}
	
	protected HorizontalPanel createSearchPanel(String text) {
		HorizontalPanel hp = new HorizontalPanel();

		Image img = new Image(getYouTubeLogo());
		img.setStyleName(YOUTUBE_LOGO_STYLE);
		hp.add(img);

		final YouTubeSearchPanel youTubeSearchPanel = new YouTubeSearchPanel();
		if (text != null) {
			youTubeSearchPanel.setSearchText(text);
		}			
		youTubeSearchPanel.addPagingHandler(new YouTubeSearchPanel.SearchHandler() {

			public void onPage(SearchEvent event) {
				RootPanel.get().clear();
				RootPanel.get().add(createSearchPanel(event.getSearch()));
				YouTubeSearchResultPanel youTubeSearchResultPanel = new YouTubeSearchResultPanel();
				youTubeSearchResultPanel.setStyleName(YOUTUBE_SEARCH_PANEL);
				RootPanel.get().add(youTubeSearchResultPanel);
				youTubeSearchResultPanel.showResults(event.getSearch(), getDefaultQueryPage());
			}
		});

		hp.add(youTubeSearchPanel);

		RootPanel.get().add(hp);

		return hp;
	}
}