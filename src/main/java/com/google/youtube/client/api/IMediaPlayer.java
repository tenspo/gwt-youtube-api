package com.google.youtube.client.api;

public interface IMediaPlayer {

	void loadMedia(String mediaURL);

	void playMedia();

	void stopMedia();

	void pauseMedia();

	double getMediaDuration();

	double getPlayPosition();

	void setPlayPosition(double position);

	double getVolume();

	void setVolume(double volume);

	void setControllerVisible(boolean show);

	boolean isControllerVisible();

	void setLoopCount(int loop);

	int getLoopCount();

	int getVideoHeight();

	int getVideoWidth();
}
