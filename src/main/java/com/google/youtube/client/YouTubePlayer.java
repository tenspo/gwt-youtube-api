package com.google.youtube.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.youtube.client.api.IMediaPlayer;
import com.google.youtube.client.data.YouTubeError;
import com.google.youtube.client.data.YouTubeVideoQuality;
import com.google.youtube.client.data.YouTubeVideoState;
import com.google.youtube.client.event.YouTubeErrorEvent;
import com.google.youtube.client.event.YouTubePlaybackQualityChangedEvent;
import com.google.youtube.client.event.YouTubeStateChangedEvent;
import com.google.youtube.client.event.handler.HasYouTubeErrorHandler;
import com.google.youtube.client.event.handler.HasYouTubePlaybackQualityChangedHandler;
import com.google.youtube.client.event.handler.HasYouTubeStateChangedHandler;
import com.google.youtube.client.event.handler.YouTubeErrorHandler;
import com.google.youtube.client.event.handler.YouTubePlaybackQualityChangedHandler;
import com.google.youtube.client.event.handler.YouTubeStateChangedHandler;
import com.google.youtube.client.js.YouTubePlayerWrapper;

public class YouTubePlayer extends Widget implements IMediaPlayer, HasYouTubeStateChangedHandler,
		HasYouTubePlaybackQualityChangedHandler, HasYouTubeErrorHandler {

	private YouTubePlayerWrapper youTubePlayerWrapper;

	private String videoId;
	
	public YouTubePlayer(String videoId, YouTubePlayerWrapper youTubePlayerWrapper) {
		this.youTubePlayerWrapper = youTubePlayerWrapper;
	}

	public String getVideoId() {
		return videoId;
	}
	
	@Override
	public int getMediaDuration() {
		return youTubePlayerWrapper.getDuration();
	}

	@Override
	public int getPlayPosition() {
		return youTubePlayerWrapper.getCurrentTime();
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
	public int getVolume() {
		return youTubePlayerWrapper.getVolume();
	}

	@Override
	public void loadMedia(String mediaURL) {
		youTubePlayerWrapper.loadVideoByUrl(mediaURL, 0);
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
	public void setPlayPosition(int position) {
		youTubePlayerWrapper.seekTo(position, false);
	}

	@Override
	public void setVolume(int volume) {
		youTubePlayerWrapper.setVolume(volume);
	}

	@Override
	public void stopMedia() {
		youTubePlayerWrapper.stopVideo();
	}

	/**
	 * This event is fired whenever the player's state changes. Possible values are unstarted (-1), ended (0), playing
	 * (1), paused (2), buffering (3), video cued (5). When the SWF is first loaded it will broadcast an unstarted (-1)
	 * event. When the video is cued and ready to play it will broadcast a video cued event (5).
	 */
	public void onStateChanged(int changeCode) {
		YouTubeStateChangedEvent.fire(this, YouTubeVideoState.get(changeCode));
	}

	/**
	 * This event is fired when an error in the player occurs. The possible error codes are 100, 101, and 150. The 100
	 * error code is broadcast when the video requested is not found. This occurs when a video has been removed (for any
	 * reason), or it has been marked as private. The 101 error code is broadcast when the video requested does not
	 * allow playback in the embedded players. The error code 150 is the same as 101, it's just 101 in disguise!
	 */
	public void onError(int errorCode) {
		YouTubeErrorEvent.fire(this, YouTubeError.get(errorCode));
	}

	/**
	 * This event is fired whenever the video playback quality changes. For example, if you call the
	 * setPlaybackQuality(suggestedQuality) function, this event will fire if the playback quality actually changes.
	 * Your code should respond to the event and should not assume that the quality will automatically change when the
	 * setPlaybackQuality(suggestedQuality) function is called. Similarly, your code should not assume that playback
	 * quality will only change as a result of an explicit call to setPlaybackQuality or any other function that allows
	 * you to set a suggested playback quality. <br/>
	 * <br/>
	 * The value that the event broadcasts is the new playback quality. Possible values are "small", "medium", "large"
	 * and "hd720".
	 */
	public void onPlaybackQualityChanged(String quality) {
		YouTubePlaybackQualityChangedEvent.fire(this, YouTubeVideoQuality.get(quality));
	}

	@Override
	public HandlerRegistration addStateChangedHandler(YouTubeStateChangedHandler handler) {
		return addHandler(handler, YouTubeStateChangedEvent.getType());
	}

	@Override
	public HandlerRegistration addStateChangedHandler(YouTubePlaybackQualityChangedHandler handler) {
		return addHandler(handler, YouTubePlaybackQualityChangedEvent.getType());
	}

	@Override
	public HandlerRegistration addErrorHandler(YouTubeErrorHandler handler) {
		return addHandler(handler, YouTubeErrorEvent.getType());
	}
}
