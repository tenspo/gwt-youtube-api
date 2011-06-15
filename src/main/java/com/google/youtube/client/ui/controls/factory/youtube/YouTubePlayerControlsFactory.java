package com.google.youtube.client.ui.controls.factory.youtube;

import sk.seges.acris.widget.client.form.IconButton;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.youtube.client.ui.CustomizableUIComponent;
import com.google.youtube.client.ui.controls.PauseButton;
import com.google.youtube.client.ui.controls.PlayButton;
import com.google.youtube.client.ui.controls.TimeControl;
import com.google.youtube.client.ui.controls.api.IPauseControl;
import com.google.youtube.client.ui.controls.api.IPlayControl;
import com.google.youtube.client.ui.controls.api.ISeekTimeControl;
import com.google.youtube.client.ui.controls.api.IStopControl;
import com.google.youtube.client.ui.controls.api.ITimeControl;
import com.google.youtube.client.ui.controls.api.IVolumeControl;
import com.google.youtube.client.ui.controls.common.MouseAwareFlowPanel;
import com.google.youtube.client.ui.controls.resources.YouTubeImageBundle;
import com.google.youtube.client.ui.controls.youtube.PositionControl;
import com.google.youtube.client.ui.controls.youtube.VolumeControl;

/**
 * @author PSimun
 */
public class YouTubePlayerControlsFactory extends CustomizableUIComponent implements IYouTubePlayerControlsFactory {

	private static YouTubeImageBundle youTubeImageBundle = GWT.create(YouTubeImageBundle.class);
	
	private static final String VOLUME_BUTTON_STYLE_NAME = "youtube-volume-button";
	private static final String PAUSE_BUTTON_STYLE_NAME = "youtube-pause-button";
	private static final String PLAY_BUTTON_STYLE_NAME = "youtube-play-button";

	private IconButton createButton(final ImageResource normal, final ImageResource hover) {
		final IconButton button = ensureUIConstructor().constructIconButton(normal);
		button.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent arg0) {
				button.setImage(hover);
			}
		});
		button.addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(MouseOutEvent arg0) {
				button.setImage(normal);
			}
		});
		
		return button;
	}
	
	public IPlayControl createPlayControl() {
		final IconButton button = createButton(youTubeImageBundle.playButton(), youTubeImageBundle.playButtonHover());
		button.setStyleName(PLAY_BUTTON_STYLE_NAME);
		return new PlayButton(button);
	}

	public IStopControl createStopControl() {
		throw new RuntimeException("Stop control is not supported for youtube player");
	}

	public IPauseControl createPauseControl() {
		IconButton button = createButton(youTubeImageBundle.pauseButton(), youTubeImageBundle.pauseButtonHover());
		button.setStyleName(PAUSE_BUTTON_STYLE_NAME);
		return new PauseButton(button);
	}

	public ISeekTimeControl createSeekTimeControl() {
//		PositionControl positionWidget = new PositionControl();
//		positionWidget.setWidth("300px");
//		positionWidget.setHeight("10px");
//		return positionWidget;
		return null;
	}

	@Override
	public ITimeControl createTimeControl() {
		TimeControl timeControl = new TimeControl(0);
		timeControl.setCurrentTime(0);
		return timeControl;
	}

	protected MouseAwareFlowPanel createPanelWithSlider(ImageResource sliderImageResource, String styleName) {
		Image slider = new Image(sliderImageResource);
		MouseAwareFlowPanel sliderPanel = new MouseAwareFlowPanel();
		AbsolutePanel dragPanel = new AbsolutePanel();
		dragPanel.setStyleName(styleName);
		sliderPanel.add(dragPanel);
		dragPanel.add(slider);
		PickupDragController dragController = new PickupDragController(dragPanel, true);
		dragController.setBehaviorConstrainedToBoundaryPanel(true);
		dragController.makeDraggable(slider);
		return sliderPanel;
	}
	
	protected MouseAwareFlowPanel createTimePositioner() {
		return createPanelWithSlider(youTubeImageBundle.positionSlider(), "youtube-time-slider-panel");
	}
	
	@Override
	public IVolumeControl createVolumeControl() {
		IconButton button = createButton(youTubeImageBundle.volumeButtonLevel4(), youTubeImageBundle.volumeButtonLevel4Hover());
		button.setStyleName(VOLUME_BUTTON_STYLE_NAME);
		return new VolumeControl(button, createVolumePositioner());
	}
	
	protected MouseAwareFlowPanel createVolumePositioner() {
		return createPanelWithSlider(youTubeImageBundle.volumeSlider(), "youtube-volume-slider-drag-panel");
	}
}