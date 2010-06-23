package com.google.youtube.client.ui;

import com.google.gdata.client.youtube.ui.DefaultUISchemeConstructor;
import com.google.gdata.client.youtube.ui.UISchemeConstructor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.youtube.client.YouTubeVideoComponents;

public class DefaultCustomPlayerComponentsFactory {

	private static YouTubeVideoComponents youTubeVideoComponents = GWT.create(YouTubeVideoComponents.class);

	public static HasClickHandlers createPlayWidget() {
		return createPlayWidget(new DefaultUISchemeConstructor());
	}

	public static HasClickHandlers createPlayWidget(UISchemeConstructor uiConstructor) {
		return uiConstructor.constructButton(youTubeVideoComponents.play());
	}

	public static HasClickHandlers createStopWidget() {
		return createStopWidget(new DefaultUISchemeConstructor());
	}

	public static HasClickHandlers createStopWidget(UISchemeConstructor uiConstructor) {
		return uiConstructor.constructButton(youTubeVideoComponents.stop());
	}

	public static HasClickHandlers createPauseWidget() {
		return createPauseWidget(new DefaultUISchemeConstructor());
	}

	public static HasClickHandlers createPauseWidget(UISchemeConstructor uiConstructor) {
		return uiConstructor.constructButton(youTubeVideoComponents.pause());
	}
}
