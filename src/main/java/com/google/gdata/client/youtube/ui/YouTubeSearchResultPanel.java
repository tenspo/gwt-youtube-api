package com.google.gdata.client.youtube.ui;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.gwttime.time.DateTime;

import com.google.gdata.client.Query;
import com.google.gdata.client.QueryPage;
import com.google.gdata.client.util.DateTimeHelper;
import com.google.gdata.client.youtube.YouTubeManager;
import com.google.gdata.client.youtube.ui.YouTubePaginator.PagingEvent;
import com.google.gdata.data.Category;
import com.google.gdata.data.Person;
import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.media.mediarss.MediaDescription;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class YouTubeSearchResultPanel extends Composite {

	private static final String FAILURE_LABEL_STYLE = "youtube-search-failure";
	private static final String TITLE_STYLE = "youtube-video-title";
	private static final String SUMMARY_STYLE = "youtube-video-summary";
	private static final String AUTHOR_STYLE = "youtube-video-author";
	private static final String UPLOADED_STYLE = "youtube-video-uploaded";
	private static final String VIEWS_STYLE = "youtube-video-views";
	private static final String TEXT_STYLE = "youtube-video-text";
	private static final String THUMBNAIL_STYLE = "youtube-video-thumbnail";

	private YouTubeMessages messages = GWT.create(YouTubeMessages.class);

	private class YouTubeSearchResult {
		private String videoId;
		private String thumbnailUrl;
		private String title;
		private String description;
		private Set<String> authors;
		private DateTime uploadDate;
		private long viewCount;

		public YouTubeSearchResult(VideoEntry entry) {

			YouTubeMediaGroup mediaGroup = entry.getMediaGroup();

			if (mediaGroup != null) {
				videoId = mediaGroup.getVideoId();

				MediaThumbnail thumbnail = getLargestThumbnail(mediaGroup.getThumbnails());
				if (thumbnail != null) {
					this.thumbnailUrl = thumbnail.getUrl();
				}

				MediaDescription description = mediaGroup.getDescription();

				if (description != null) {
					this.description = description.getPlainTextContent();
				}

			}

			TextConstruct title = entry.getTitle();

			if (title != null) {
				this.title = title.getPlainText();
			}

			if (this.description == null) {
				Set<Category> categories = entry.getCategories();
				if (categories != null && categories.size() > 0) {
					Iterator<Category> categoryIterator = categories.iterator();

					while (categoryIterator.hasNext()) {
						Category category = categoryIterator.next();
						if (category != null && category.getScheme() != null
								&& category.getScheme().indexOf("keywords") > -1 && category.getTerm() != null
								&& category.getTerm().length() > 0) {
							String label = category.getTerm();
							this.description += " \"" + label + "\"";
						}
					}

					if (this.description != null) {
						this.description = this.description.trim();
					}
				}
			}

			List<Person> persons = entry.getAuthors();

			authors = new HashSet<String>();

			if (persons != null) {
				for (Person person : persons) {
					authors.add(person.getName());
				}
			}

			uploadDate = entry.getUpdated();

			if (entry.getStatistics() != null) {
				viewCount = entry.getStatistics().getViewCount();
			}
		}

		private MediaThumbnail getLargestThumbnail(List<MediaThumbnail> thumbnails) {

			if (thumbnails == null || thumbnails.size() == 0) {
				return null;
			}

			MediaThumbnail chosen = null;

			for (MediaThumbnail mediaThumbnail : thumbnails) {
				if (chosen == null) {
					chosen = mediaThumbnail;
				} else {
					if (chosen.getWidth() < mediaThumbnail.getWidth()) {
						chosen = mediaThumbnail;
					}
				}
			}

			return chosen;
		}

		public String getVideoId() {
			return videoId;
		}

		public String getThumbnailUrl() {
			return thumbnailUrl;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		public Set<String> getAuthors() {
			return authors;
		}

		public DateTime getUploadDate() {
			return uploadDate;
		}

		public long getViewCount() {
			return viewCount;
		}
	}

	private FlowPanel container;

	private static final QueryPage DEFAULT_QUERY_PAGE = new QueryPage(1, 5, Query.UNDEFINED);
	
	public YouTubeSearchResultPanel() {
		container = new FlowPanel();
		initWidget(container);
	}

	public void showResults(String textQuery) {
		showResults(textQuery, DEFAULT_QUERY_PAGE);
	}

	public void showResults(final String textQuery, final QueryPage pageQuery) {

		YouTubeManager youTubeManager = new YouTubeManager();

		AsyncCallback<VideoFeed> callback = new AsyncCallback<VideoFeed>() {

			@Override
			public void onFailure(Throwable caught) {
				prepareErrorUI(messages.youtubeServiceCommucationError());
			}

			@Override
			public void onSuccess(VideoFeed feed) {
				showResults(textQuery, pageQuery, feed);
			}
		};

		youTubeManager.retrieveVideo(textQuery, pageQuery, callback);
	}

	private void showResults(final String textQuery, final QueryPage pageQuery, final VideoFeed feed) {
		
		if (feed == null || feed.getEntries() == null) {
			return;
		}

		List<VideoEntry> result = feed.getEntries();
		
		Set<YouTubeSearchResult> searchResult = new HashSet<YouTubeSearchResult>();
		if (result != null) {
			for (VideoEntry videoEntry : result) {
				searchResult.add(new YouTubeSearchResult(videoEntry));
			}
		}
		
		container.clear();
		container.add(prepareUI(searchResult));
		YouTubePaginator youTubePaginator = new YouTubePaginator();
		youTubePaginator.addPagingHandler(new YouTubePaginator.PagingHandler<YouTubePaginator>() {
			
			@Override
			public void onPage(PagingEvent<YouTubePaginator> event) {
				showResults(textQuery, new QueryPage((event.getPageIndex() - 1) * pageQuery.getItemsPerPage() + 1, pageQuery.getItemsPerPage(), feed.getTotalResults()));
			}
		});
		
		youTubePaginator.setQueryPage(pageQuery);
		youTubePaginator.showPagingButtons();
		container.add(youTubePaginator);
	}
	
	private Label createTitle(final String title, final String videoId) {
		Label label = new Label(title);
		label.setStyleName(TITLE_STYLE);
		label.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				playVideo(title, videoId);
			}
		});
		return label;
	}

	protected void playVideo(String title, String videoId) {
		final DialogBox dialog = GWT.create(DialogBox.class);
		YouTubeVideoPanel videoPanel = new YouTubeVideoPanel();
		videoPanel.playVideo(videoId, 640, 480);

		dialog.setText(title);
		FlowPanel fp = new FlowPanel();
		fp.add(videoPanel);
		Button closeButton = GWT.create(Button.class);
		closeButton.setText("Close");
		closeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dialog.hide();
			}
		});
		fp.add(closeButton);
		dialog.add(fp);
		dialog.center();
	}

	private Label createDescription(String desc) {
		Label label = new Label(desc);
		label.setStyleName(SUMMARY_STYLE);
		return label;
	}

	private Label createAuthorText(String text) {
		Label label = new Label(text);
		label.setStyleName(AUTHOR_STYLE);
		return label;
	}

	private Label createUpdatedText(String text) {
		Label label = new Label(text);
		label.setStyleName(UPLOADED_STYLE);
		return label;
	}

	private Label createViews(String text) {
		Label label = new Label(text);
		label.setStyleName(VIEWS_STYLE);
		return label;
	}

	private Label createText(String text) {
		Label label = new Label(text);
		label.setStyleName(TEXT_STYLE);
		return label;
	}

	protected FlowPanel prepareErrorUI(String errorMessage) {
		FlowPanel flowPanel = new FlowPanel();
		Label label = new Label();
		label.setStyleName(FAILURE_LABEL_STYLE);
		label.setText(errorMessage);
		flowPanel.add(label);
		return flowPanel;
	}

	protected FlowPanel prepareUI(Set<YouTubeSearchResult> youTubeSearchResults) {
		FlowPanel flowPanel = new FlowPanel();

		for (YouTubeSearchResult youTubeSearchResult : youTubeSearchResults) {
			HorizontalPanel videoPanel = new HorizontalPanel();

			Image img = new Image(youTubeSearchResult.getThumbnailUrl());
			img.setStyleName(THUMBNAIL_STYLE);
			videoPanel.add(img);

			VerticalPanel descriptionPanel = new VerticalPanel();
			descriptionPanel.add(createTitle(youTubeSearchResult.getTitle(), youTubeSearchResult.getVideoId()));
			descriptionPanel.add(createDescription(youTubeSearchResult.getDescription()));
			HorizontalPanel horizontalPanel = new HorizontalPanel();

			if (youTubeSearchResult.getAuthors().size() > 0) {
				horizontalPanel.add(createAuthorText(messages.by()));
			}

			for (String author : youTubeSearchResult.getAuthors()) {

				horizontalPanel.add(createAuthorText(author));
				Label separator = createText("|");
				horizontalPanel.add(separator);
			}

			horizontalPanel.add(createUpdatedText(DateTimeHelper.toString(youTubeSearchResult.getUploadDate())));

			horizontalPanel.add(createText("|"));

			horizontalPanel.add(createViews(youTubeSearchResult.getViewCount() + " "
					+ (youTubeSearchResult.getViewCount() == 1 ? messages.view() : messages.views())));

			descriptionPanel.add(horizontalPanel);
			videoPanel.add(descriptionPanel);

			flowPanel.add(videoPanel);
		}

		return flowPanel;
	}
}