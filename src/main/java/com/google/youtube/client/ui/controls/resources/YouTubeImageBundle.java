package com.google.youtube.client.ui.controls.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface YouTubeImageBundle extends ClientBundle {

	@Source("play.png")
	ImageResource playButton();

	@Source("play_hover.png")
	ImageResource playButtonHover();

	@Source("pause.png")
	ImageResource pauseButton();

	@Source("pause_hover.png")
	ImageResource pauseButtonHover();

	@Source("sound_full.png")
	ImageResource volumeButtonLevel4();

	@Source("sound_full_hover.png")
	ImageResource volumeButtonLevel4Hover();

	@Source("sound_3.png")
	ImageResource volumeButtonLevel3();

	@Source("sound_3_hover.png")
	ImageResource volumeButtonLevel3Hover();

	@Source("sound_2.png")
	ImageResource volumeButtonLevel2();

	@Source("sound_2_hover.png")
	ImageResource volumeButtonLevel2Hover();

	@Source("sound_1.png")
	ImageResource volumeButtonLevel1();

	@Source("sound_1_hover.png")
	ImageResource volumeButtonLevel1Hover();

	@Source("no_sound.png")
	ImageResource volumeButtonNoSound();

	@Source("sound_slider.png")
	ImageResource volumeSlider();

	@Source("position_slider.png")
	ImageResource positionSlider();

	@Source("position_slider_hover.png")
	ImageResource positionSliderHover();
}