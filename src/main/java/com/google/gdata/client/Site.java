package com.google.gdata.client;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gdata.client.youtube.YouTubeManager;
import com.google.gdata.client.youtube.YouTubeVideo;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.youtube.client.YouTubeEmbeddedPlayer;

public class Site implements EntryPoint {

	private VerticalPanel vp = new VerticalPanel();

	@Override
	public void onModuleLoad() {
		final FlowPanel hp = new FlowPanel();
		final TextBox textBox = new TextBox();
		textBox.setWidth("300px");
		DOM.setStyleAttribute(hp.getElement(), "textAlign", "center");
		hp.add(textBox);
		textBox.setText("NKoNu4_7P_I");

		Button search = new Button();
		search.setText("I'm Feeling Lucky");
		search.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				vp.clear();
				YouTubeEmbeddedPlayer youTubeEmbeddedPlayer = new YouTubeEmbeddedPlayer(textBox.getValue());
				vp.add(youTubeEmbeddedPlayer);
				
				YouTubeManager youTubeManager = new YouTubeManager(textBox.getValue());
				List<YouTubeVideo> videos = youTubeManager.retrieveVideos();
			}
		});

		hp.add(search);
		hp.add(vp);
		RootPanel.get().add(hp);

		
	}
}
