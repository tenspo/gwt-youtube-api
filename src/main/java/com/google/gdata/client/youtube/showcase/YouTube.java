package com.google.gdata.client.youtube.showcase;

import java.util.List;

import org.gwttime.time.format.DateTimeFormat;

import com.google.gdata.client.youtube.YouTubeManager;
import com.google.gdata.client.youtube.ui.YouTubeVideoPanel;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class YouTube extends AbstractShowcase implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel.get().add(createSearchPanel());

		Label label = new Label();
		label.setText("Recommended for You");
		DOM.setStyleAttribute(label.getElement(), "fontWeight", "bold");
		DOM.setStyleAttribute(label.getElement(), "fontFamily", "Arial,Helvetica,sans-serif");
		DOM.setStyleAttribute(label.getElement(), "fontSize", "16px");
		DOM.setStyleAttribute(label.getElement(), "color", "#0033CC");
		DOM.setStyleAttribute(label.getElement(), "marginLeft", "150px");
		DOM.setStyleAttribute(label.getElement(), "marginTop", "20px");
		RootPanel.get().add(label);

		Grid gridTopRated = new Grid(2, 4);
		fillVideoGrid(gridTopRated, false);
		RootPanel.get().add(gridTopRated);

		label = new Label();
		label.setText("Most recent videos");
		DOM.setStyleAttribute(label.getElement(), "fontWeight", "bold");
		DOM.setStyleAttribute(label.getElement(), "fontFamily", "Arial,Helvetica,sans-serif");
		DOM.setStyleAttribute(label.getElement(), "fontSize", "16px");
		DOM.setStyleAttribute(label.getElement(), "color", "#0033CC");
		DOM.setStyleAttribute(label.getElement(), "marginLeft", "150px");
		DOM.setStyleAttribute(label.getElement(), "marginTop", "20px");
		RootPanel.get().add(label);

		Grid gridRecent = new Grid(2, 4);
		fillVideoGrid(gridRecent, true);
		RootPanel.get().add(gridRecent);
	}

	private void playVideo(String id) {
		RootPanel.get().clear();
		RootPanel.get().add(createSearchPanel());
		YouTubeVideoPanel videoPanel = new YouTubeVideoPanel();
		videoPanel.setStyleName(YouTubeVideoPanel.PLAYER_STYLE);
		videoPanel.playVideo(id, 640, 480);
		RootPanel.get().add(videoPanel);
	}
	
	private void fillVideoGrid(final Grid grid, boolean mostRecent) {
		YouTubeManager youTubeManager = new YouTubeManager();

		DOM.setStyleAttribute(grid.getElement(), "borderTop", "1px solid #666666");
		DOM.setStyleAttribute(grid.getElement(), "marginLeft", "150px");

		AsyncCallback<VideoFeed> callback = new AsyncCallback<VideoFeed>() {

			@Override
			public void onSuccess(VideoFeed feed) {
				
				if (feed == null || feed.getEntries() == null) {
					return;
				}
				List<VideoEntry> result = feed.getEntries();
				
				int maxColumns = 4;

				int index = 0;

				for (VideoEntry entry : result) {
					YouTubeMediaGroup mediaGroup = entry.getMediaGroup();

					VerticalPanel vp = new VerticalPanel();
					vp.setWidth("120px");
					DOM.setStyleAttribute(vp.getElement(), "margin", "10px");

					if (mediaGroup != null) {
						List<MediaThumbnail> thumbnails = mediaGroup.getThumbnails();

						if (thumbnails != null && thumbnails.size() > 0) {
							final String videoId = entry.getMediaGroup().getVideoId();
							Image img = new Image(thumbnails.get(0).getUrl());
							DOM.setStyleAttribute(img.getElement(), "border", "1px solid #666666");
							DOM.setStyleAttribute(img.getElement(), "padding", "1px");
							img.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									playVideo(videoId);
								}
							});
							vp.add(img);
						}

						Label label = new Label();
						label.setText(entry.getTitle().getPlainText());
						DOM.setStyleAttribute(label.getElement(), "color", "#0033CC");
						DOM.setStyleAttribute(label.getElement(), "fontWeight", "bold");
						DOM.setStyleAttribute(label.getElement(), "fontFamily", "Arial,Helvetica,sans-serif");
						DOM.setStyleAttribute(label.getElement(), "fontSize", "11px");
						vp.add(label);
						label = new Label();
						label.setText(entry.getUpdated().toString(DateTimeFormat.longDate()));
						DOM.setStyleAttribute(label.getElement(), "fontFamily", "Arial,Helvetica,sans-serif");
						DOM.setStyleAttribute(label.getElement(), "color", "#666666");
						DOM.setStyleAttribute(label.getElement(), "fontSize", "11px");
						vp.add(label);
						label = new Label();
						label.setText(entry.getStatistics().getViewCount() + " views");
						DOM.setStyleAttribute(label.getElement(), "fontFamily", "Arial,Helvetica,sans-serif");
						DOM.setStyleAttribute(label.getElement(), "color", "#666666");
						DOM.setStyleAttribute(label.getElement(), "fontSize", "11px");
						vp.add(label);
					}
					grid.setWidget(index / maxColumns, index % maxColumns, vp);
					index++;
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Exception occured while trying to display video", caught);
			}
		};

		if (mostRecent) {
			youTubeManager.retrieveMostRecent(callback);
		} else {
			youTubeManager.retrieveTopRated(callback);
		}

	}
}