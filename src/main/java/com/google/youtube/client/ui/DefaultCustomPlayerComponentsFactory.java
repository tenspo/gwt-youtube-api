package com.google.youtube.client.ui;

import com.google.gdata.client.youtube.ui.DefaultUISchemeConstructor;
import com.google.gdata.client.youtube.ui.UISchemeConstructor;
import com.google.gdata.client.youtube.ui.YouTubeMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.youtube.client.YouTubePlayer;
import com.google.youtube.client.YouTubeVideoComponents;

public class DefaultCustomPlayerComponentsFactory {

	private static YouTubeVideoComponents youTubeVideoComponents = GWT.create(YouTubeVideoComponents.class);
	private static YouTubeMessages youTubeMessages = GWT.create(YouTubeMessages.class);

	private UISchemeConstructor getDefaultUIConstructor() {
		return new DefaultUISchemeConstructor();
	}

	private static final String PAUSE_BUTTON_STYLE_NAME = "youtube-pause-button";
	private static final String PLAY_BUTTON_STYLE_NAME = "youtube-play-button";
	private static final String STOP_BUTTON_STYLE_NAME = "youtube-stop-button";
	
	/*
	 * Play widget section
	 */
	public Button createPlayWidget(YouTubePlayer youTubePlayer) {
		return createPlayWidget(getDefaultUIConstructor(), youTubePlayer);
	}

	protected Button createPlayWidget(UISchemeConstructor uiConstructor) {
		Button button = uiConstructor.constructButton(youTubeVideoComponents.play());
		button.setStyleName(PLAY_BUTTON_STYLE_NAME);
		return button;
	}

	public Button createPlayWidget(UISchemeConstructor uiConstructor, final YouTubePlayer youTubePlayer) {
		Button playWidget = createPlayWidget(uiConstructor);
		playWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				youTubePlayer.playMedia();
			}
		});
		return playWidget;
	}

	/*
	 * Stop widget section
	 */
	public Button createStopWidget(YouTubePlayer youTubePlayer) {
		return createStopWidget(getDefaultUIConstructor(), youTubePlayer);
	}

	protected Button createStopWidget(UISchemeConstructor uiConstructor) {
		Button button = uiConstructor.constructButton(youTubeVideoComponents.stop());
		button.setStyleName(STOP_BUTTON_STYLE_NAME);
		return button;
	}

	public Button createStopWidget(UISchemeConstructor uiConstructor, final YouTubePlayer youTubePlayer) {
		Button stopWidget = createStopWidget(uiConstructor);
		stopWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				youTubePlayer.stopMedia();
			}
		});
		return stopWidget;
	}

	/*
	 * Pause widget section
	 */
	public Button createPauseWidget(YouTubePlayer youTubePlayer) {
		return createPauseWidget(getDefaultUIConstructor(), youTubePlayer);
	}

	protected Button createPauseWidget(UISchemeConstructor uiConstructor) {
		Button button = uiConstructor.constructButton(youTubeVideoComponents.pause());
		button.setStyleName(PAUSE_BUTTON_STYLE_NAME);
		return button;
	}

	public Button createPauseWidget(UISchemeConstructor uiConstructor, final YouTubePlayer youTubePlayer) {
		Button pauseWidget = createPauseWidget(uiConstructor);
		pauseWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				youTubePlayer.pauseMedia();
			}
		});
		return pauseWidget;
	}

	/*
	 * Seek control
	 */

	public static interface ISeekWidget extends HasClickHandlers {

		Integer getSeekTime();
	}

	public static class SeekFlowWidget extends FlowPanel implements ISeekWidget {

		private Button seekButton;
		private TextBox seekValueBox;
		private Label seekLabel;

		public Label getSeekLabel() {
			return seekLabel;
		}

		public void setSeekLabel(Label seekLabel) {
			this.seekLabel = seekLabel;
		}

		public Button getSeekButton() {
			return seekButton;
		}

		public void setSeekButton(Button seekButton) {
			this.seekButton = seekButton;
		}

		public TextBox getSeekValueBox() {
			return seekValueBox;
		}

		public void setSeekValueBox(TextBox seekValueBox) {
			this.seekValueBox = seekValueBox;
		}

		@Override
		protected void onLoad() {

			if (seekValueBox != null) {
				add(seekValueBox);
				seekValueBox.setWidth("60px");
				seekValueBox.setText("0");
			}

			if (seekLabel != null) {
				add(seekLabel);
			}

			if (seekButton != null) {
				add(seekButton);
			}
			super.onLoad();
		}

		@Override
		public HandlerRegistration addClickHandler(ClickHandler handler) {
			return seekButton.addClickHandler(handler);
		}

		@Override
		public Integer getSeekTime() {
			if (seekValueBox != null) {
				String seekValue = seekValueBox.getText();
				try {
					return Integer.parseInt(seekValue);
				} catch (NumberFormatException ex) {
					return null;
				}
			}

			return null;
		}
	}

	public ISeekWidget createSeekToWidget(YouTubePlayer youTubePlayer) {
		return createSeekToWidget(getDefaultUIConstructor(), youTubePlayer);
	}

	protected ISeekWidget createSeekToWidget(UISchemeConstructor uiConstructor) {
		SeekFlowWidget seekFlowWidget = new SeekFlowWidget();
		seekFlowWidget.setSeekValueBox(uiConstructor.constructTextBox());
		seekFlowWidget.setSeekButton(uiConstructor.constructButton(youTubeVideoComponents.go()));
		seekFlowWidget.setSeekLabel(new Label(youTubeMessages.seconds()));
		return seekFlowWidget;
	}

	public ISeekWidget createSeekToWidget(UISchemeConstructor uiConstructor, final YouTubePlayer youTubePlayer) {
		final ISeekWidget seekWidget = createSeekToWidget(uiConstructor);
		seekWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Integer seekTime = seekWidget.getSeekTime();
				if (seekTime != null) {
					youTubePlayer.setPlayPosition(seekTime);
				}
			}
		});
		return seekWidget;
	}

	/*
	 * Position indicator
	 */

	public static interface IPositionWidget extends HasClickHandlers {

		int getCurrentTime();

		void setCurrentTime(int time);

		void setWidth(String width);

		void setHeight(String height);

		void setDimension(String width, String height);
	}

	private static final String POSITION_PANEL_STYLE_NAME = "youtube-position-panel";
	private static final String POSITION_PANEL_PLAYED_STYLE_NAME = ".youtube-position-played-panel";
	private static final String POSITION_PANEL_REST_STYLE_NAME = ".youtube-position-rest-panel";

	public static class PositionWidget extends FlowPanel implements IPositionWidget {

		private FlowPanel played = new FlowPanel();
		private FlowPanel rest = new FlowPanel();

		private int duration;
		private int currentTime;

		private String width;

		public PositionWidget(int duration) {
			this.duration = duration;
			
			setStyleName(POSITION_PANEL_STYLE_NAME);
			played.setStyleName(POSITION_PANEL_PLAYED_STYLE_NAME);
			rest.setStyleName(POSITION_PANEL_REST_STYLE_NAME);
			
			DOM.setStyleAttribute(played.getElement(), "height", "100%");
			DOM.setStyleAttribute(rest.getElement(), "height", "100%");
		}

		public int getCurrentTime() {
			return currentTime;
		}

		@Override
		protected void onLoad() {
			add(played);
			add(rest);
			super.onLoad();
		}

		@Override
		public HandlerRegistration addClickHandler(ClickHandler handler) {
			return null;
		}

		private void calculateSizes() {
			double ratio = currentTime == 0 ? 0 : (duration / currentTime);
			
			Integer widthInt = getStringDimension(width);
			
			double playedWidth = widthInt * ratio;
			played.setWidth(((int)playedWidth) + "px");
		}

		public Integer getStringDimension(String dimension) {
			//percentage or other units are not allowed
			if (dimension.indexOf("px") != -1) {
				String dimensionNumber = dimension.replaceAll("px", "").trim();
				try {
					return Integer.parseInt(dimensionNumber);
				} catch (NumberFormatException e) {
				}
			}

			try {
				return Integer.parseInt(dimension);
			} catch (NumberFormatException e) {
			}
			
			return null;
		}

		@Override
		public void setCurrentTime(int time) {
			this.currentTime = time;
			calculateSizes();
		}
		
		@Override
		public void setWidth(String width) {
			super.setWidth(width);
			this.width = width;
			calculateSizes();
		}

		@Override
		public void setDimension(String width, String height) {
			super.setWidth(width);
			this.width = width;
			setHeight(height);
			calculateSizes();
		}
	}

	public IPositionWidget createPositionWidget(YouTubePlayer youTubePlayer) {
		return createPositionWidget(getDefaultUIConstructor(), youTubePlayer);
	}

	public IPositionWidget createPositionWidget(UISchemeConstructor uiConstructor, final YouTubePlayer youTubePlayer) {
		final PositionWidget positionWidget = new PositionWidget(60/*youTubePlayer.getMediaDuration()*/);
		positionWidget.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
			}
		});
		return positionWidget;
	}

	/*
	 * Volume control
	 */

	/*
	 * Size control
	 */
}