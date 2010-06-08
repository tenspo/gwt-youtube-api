package com.google.gdata.client.youtube;

import com.google.gdata.client.QueryPage;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class YouTubeManager {
	private static final String YOUTUBE_URL = "http://gdata.youtube.com/feeds/api/";
	private static final String VIDEO_FEED_URL = YOUTUBE_URL + "videos";
	private static final String STANDARD_FEED_URL = YOUTUBE_URL + "standardfeeds/";
	private static final String TOP_RATED_FEED_URL = STANDARD_FEED_URL + "top_rated";
	private static final String MOST_RECENT_FEED_URL = STANDARD_FEED_URL + "most_recent";

	public YouTubeManager() {
	}

	public void retrieveVideo(String textQuery, AsyncCallback<VideoFeed> callback) {
		retrieveVideos(VIDEO_FEED_URL, textQuery, null, callback);
	}

	public void retrieveVideo(String textQuery, QueryPage queryPage, AsyncCallback<VideoFeed> callback) {
		retrieveVideos(VIDEO_FEED_URL, textQuery, queryPage, callback);
	}

	public void retrieveTopRated(AsyncCallback<VideoFeed> callback) {
		retrieveVideos(TOP_RATED_FEED_URL, null, null, callback);
	}

	public void retrieveMostRecent(AsyncCallback<VideoFeed> callback) {
		retrieveVideos(MOST_RECENT_FEED_URL, null, null, callback);
	}

	private void retrieveVideos(String url, String textQuery, QueryPage queryPage,
			final AsyncCallback<VideoFeed> callback) {

		YouTubeService service = new YouTubeService();
		// service.setConnectTimeout(timeout); // millis
		YouTubeQuery query = new YouTubeQuery(url);

		query.setOrderBy(YouTubeQuery.OrderBy.VIEW_COUNT);
		if (textQuery != null) {
			query.setFullTextQuery(textQuery);
		}
		query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
		if (queryPage != null) {
			query.setQueryPage(queryPage);
		}

		service.query(query, VideoFeed.class, new YouTubeJSONRequestHandler() {
			
			@Override
			public void onRequestComplete(VideoFeed videoFeed) {
				callback.onSuccess(videoFeed);
			}
		});
	}
}