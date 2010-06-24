/**
 * 
 */
package com.google.youtube.client.data;

public enum YouTubeVideoState {
	UNSTARTED(-1), ENDED(0), PLAYING(1), PAUSED(2), BUFFERING(3), CUED(5);
	
	private int value;
	
	YouTubeVideoState(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static YouTubeVideoState get(int state) {
		for (YouTubeVideoState videoState : YouTubeVideoState.values()) {
			if (videoState.getValue() == state) {
				return videoState;
			}
		}
		
		return null;
	}
}