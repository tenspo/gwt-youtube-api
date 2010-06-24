package com.google.youtube.client;

import com.google.gwt.i18n.client.Messages;

public interface YouTubeVideoComponents extends Messages {

	String play();

	String stop();

	String pause();
	
	String go();
	
	String mute();
	
	String unmute();
	
	String setVolume();
	
	String width();
	
	String height();
	
	String seekTo();
	
	String aspectRatio();
	
	String updateSize();
	
	String playerType();
	
	String playerVersion();
	
	String startAt();
	
	String setQuality();
	
	String loadVideo();
	
	String cueVideo();
}