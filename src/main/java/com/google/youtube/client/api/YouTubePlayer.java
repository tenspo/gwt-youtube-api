package com.google.youtube.client.api;

import com.google.youtube.client.js.YouTubePlayerWrapper;

public class YouTubePlayer implements IMediaPlayer {
	
	private YouTubePlayerWrapper youTubePlayerWrapper;
	
	public YouTubePlayer(YouTubePlayerWrapper youTubePlayerWrapper) {
		this.youTubePlayerWrapper = youTubePlayerWrapper;
	}
	
	public void onStateChanged(int state) {
	}

	@Override
	public int getLoopCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMediaDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPlayPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVideoHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVideoWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isControllerVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void loadMedia(String mediaURL) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pauseMedia() {
		youTubePlayerWrapper.pauseVideo();
	}

	@Override
	public void playMedia() {
		youTubePlayerWrapper.playVideo();
	}

	@Override
	public void setControllerVisible(boolean show) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoopCount(int loop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlayPosition(double position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVolume(double volume) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopMedia() {
		youTubePlayerWrapper.stopVideo();
	}
}
