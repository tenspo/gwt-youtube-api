package com.google.youtube.client.ui;

import com.google.gdata.client.youtube.ui.DefaultUISchemeConstructor;
import com.google.gdata.client.youtube.ui.UISchemeConstructor;
import com.google.gdata.client.youtube.ui.YouTubeMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.youtube.client.YouTubePlayer;
import com.google.youtube.client.YouTubeVideoComponents;

public class DefaultCustomPlayerComponentsFactory {

	private static YouTubeVideoComponents youTubeVideoComponents = GWT.create(YouTubeVideoComponents.class);
	private static YouTubeMessages youTubeMessages = GWT.create(YouTubeMessages.class);
	
	private UISchemeConstructor getDefaultUIConstructor() {
		return new DefaultUISchemeConstructor();
	}
	
	/*
	 * Play widget section
	 */
	public Button createPlayWidget(YouTubePlayer youTubePlayer) {
		return createPlayWidget(getDefaultUIConstructor(), youTubePlayer);
	}

	protected Button createPlayWidget(UISchemeConstructor uiConstructor) {
		return uiConstructor.constructButton(youTubeVideoComponents.play());
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
		return uiConstructor.constructButton(youTubeVideoComponents.stop());
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
		return uiConstructor.constructButton(youTubeVideoComponents.pause());
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
	
	public static class SeekFlowWidget extends Widget implements ISeekWidget {
		private final FlowPanel container;
		
		public SeekFlowWidget() {
			container = new FlowPanel();
			setElement(container.getElement());
		}
		
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
			super.onLoad();

			if (seekValueBox != null) {
				container.add(seekValueBox);
				seekValueBox.setWidth("60px");
			}
			
			if (seekLabel != null) {
				container.add(seekLabel);
			}

			if (seekButton != null) {
				container.add(seekButton);
			}
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
					Integer.parseInt(seekValue);
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
				youTubePlayer.setPlayPosition(seekWidget.getSeekTime());
			}
		});
		return seekWidget;
	}

	/*
	 * Volume control
	 */

	/*
	 * Size control
	 */
}