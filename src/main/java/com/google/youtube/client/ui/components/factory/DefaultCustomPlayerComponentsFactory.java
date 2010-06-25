package com.google.youtube.client.ui.components.factory;

import com.google.gdata.client.youtube.ui.DefaultUISchemeConstructor;
import com.google.gdata.client.youtube.ui.UISchemeConstructor;
import com.google.gdata.client.youtube.ui.YouTubeMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.youtube.client.YouTubeVideoComponents;
import com.google.youtube.client.ui.components.DefaultCustomPlayerComponents;
import com.google.youtube.client.ui.components.EOperations;
import com.google.youtube.client.ui.components.IDefaultCustomPlayerComponentsPanel;
import com.google.youtube.client.ui.components.PauseButton;
import com.google.youtube.client.ui.components.PlayButton;
import com.google.youtube.client.ui.components.SeekFlowWidget;
import com.google.youtube.client.ui.components.StopButton;
import com.google.youtube.client.ui.components.api.ICustomPlayerComponents;
import com.google.youtube.client.ui.components.api.IPauseWidget;
import com.google.youtube.client.ui.components.api.IPlayWidget;
import com.google.youtube.client.ui.components.api.IPositionWidget;
import com.google.youtube.client.ui.components.api.ISeekWidget;
import com.google.youtube.client.ui.components.api.IStopWidget;

/**
 * @author PSimun
 *
 */
public class DefaultCustomPlayerComponentsFactory implements IDefaultCustomPlayerComponentsFactory {

	private static YouTubeVideoComponents youTubeVideoComponents = GWT.create(YouTubeVideoComponents.class);
	private static YouTubeMessages youTubeMessages = GWT.create(YouTubeMessages.class);

	private UISchemeConstructor getDefaultUIConstructor() {
		return new DefaultUISchemeConstructor();
	}

	private static final String PAUSE_BUTTON_STYLE_NAME = "youtube-pause-button";
	private static final String PLAY_BUTTON_STYLE_NAME = "youtube-play-button";
	private static final String STOP_BUTTON_STYLE_NAME = "youtube-stop-button";

	protected IDefaultCustomPlayerComponentsPanel createDefaultComponentsPanel() {
		return new DefaultCustomPlayerComponents();
	}

	private UISchemeConstructor uiSchemeConstructor;
	
	
	public void setUiSchemeConstructor(UISchemeConstructor uiSchemeConstructor) {
		this.uiSchemeConstructor = uiSchemeConstructor;
	}

	protected UISchemeConstructor ensureUIConstructor() {
		if (uiSchemeConstructor == null) {
			uiSchemeConstructor = getDefaultUIConstructor();
		}
		return uiSchemeConstructor;
	}
	
	public IPlayWidget createPlayWidget() {
		Button button = ensureUIConstructor().constructButton(youTubeVideoComponents.play());
		button.setStyleName(PLAY_BUTTON_STYLE_NAME);
		return new PlayButton(button);
	}

	public IStopWidget createStopWidget() {
		Button button = ensureUIConstructor().constructButton(youTubeVideoComponents.stop());
		button.setStyleName(STOP_BUTTON_STYLE_NAME);
		return new StopButton(button);
	}

	public IPauseWidget createPauseWidget() {
		Button button = ensureUIConstructor().constructButton(youTubeVideoComponents.pause());
		button.setStyleName(PAUSE_BUTTON_STYLE_NAME);
		return new PauseButton(button);
	}

	protected ISeekWidget createSeekToWidget() {
		SeekFlowWidget seekFlowWidget = new SeekFlowWidget();
		seekFlowWidget.setSeekValueBox(ensureUIConstructor().constructTextBox());
		seekFlowWidget.setSeekButton(ensureUIConstructor().constructButton(youTubeVideoComponents.go()));
		seekFlowWidget.setSeekLabel(new Label(youTubeMessages.seconds()));
		return seekFlowWidget;
	}

	@Override
	public IPositionWidget createPositionWidget() {
		// TODO Auto-generated method stub
		return null;
	}

//	public ISeekWidget createSeekToWidget(UISchemeConstructor uiConstructor, final YouTubePlayer youTubePlayer) {
//		final ISeekWidget seekWidget = createSeekToWidget(uiConstructor);
//		seekWidget.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				Integer seekTime = seekWidget.getSeekTime();
//				if (seekTime != null) {
//					youTubePlayer.setPlayPosition(seekTime);
//				}
//			}
//		});
//		return seekWidget;
//	}

	/*
	 * Position indicator
	 */

//	public IPositionWidget createPositionWidget(UISchemeConstructor uiConstructor, final YouTubePlayer youTubePlayer) {
//		final PositionWidget positionWidget = new PositionWidget(60/* youTubePlayer.getMediaDuration() */);
//		positionWidget.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//			}
//		});
//		return positionWidget;
//	}

	/*
	 * Volume control
	 */

	/*
	 * Size control
	 */
}