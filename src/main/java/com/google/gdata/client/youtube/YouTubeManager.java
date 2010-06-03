package com.google.gdata.client.youtube;

import java.util.List;

import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class YouTubeManager {
	private static final String YOUTUBE_URL = "http://gdata.youtube.com/feeds/api/";
	private static final String VIDEO_FEED_URL = YOUTUBE_URL + "videos";
	private static final String STANDARD_FEED_URL = YOUTUBE_URL + "standardfeeds/";
	private static final String TOP_RATED_FEED_URL = STANDARD_FEED_URL + "top_rated";
	private static final String MOST_RECENT_FEED_URL = STANDARD_FEED_URL + "most_recent";
	private static final String YOUTUBE_EMBEDDED_URL = "http://www.youtube.com/v/";

	public YouTubeManager() {
	}

	public void retrieveVideo(String videoID, AsyncCallback<List<VideoEntry>> callback) {
		retrieveVideos(VIDEO_FEED_URL, videoID, -1, false, callback);
	}

	public void retrieveTopRated(AsyncCallback<List<VideoEntry>> callback) {
		retrieveVideos(TOP_RATED_FEED_URL, null, -1, false, callback);
	}

	public void retrieveMostRecent(AsyncCallback<List<VideoEntry>> callback) {
		retrieveVideos(MOST_RECENT_FEED_URL, null, -1, false, callback);
	}

	private void retrieveVideos(String url, String textQuery, int maxResults, boolean filter,
			final AsyncCallback<List<VideoEntry>> callback) {

		YouTubeService service = new YouTubeService();
		// service.setConnectTimeout(timeout); // millis
		YouTubeQuery query = new YouTubeQuery(url);

		query.setOrderBy(YouTubeQuery.OrderBy.VIEW_COUNT);
		if (textQuery != null) {
			query.setFullTextQuery(textQuery);
		}
		query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}

		service.query(query, VideoFeed.class, new YouTubeJSONRequestHandler() {
			
			@Override
			public void onRequestComplete(VideoFeed videoFeed) {
				if (videoFeed.getEntries() != null && videoFeed.getEntries().size() > 0) {
					callback.onSuccess(videoFeed.getEntries());
				} else {
					callback.onFailure(null);
				}
			}
		});
	}
}