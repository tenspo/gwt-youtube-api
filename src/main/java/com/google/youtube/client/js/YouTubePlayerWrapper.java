package com.google.youtube.client.js;

import com.google.gwt.core.client.JavaScriptObject;

public class YouTubePlayerWrapper extends JavaScriptObject {

	protected YouTubePlayerWrapper() {
	}

	/**
	 * Loads the specified video's thumbnail and prepares the player to play the video. The player does not request the
	 * FLV until playVideo() or seekTo() is called.
	 * 
	 * <ul>
	 * <li>The required videoId parameter specifies the YouTube Video ID of the video to be played. In YouTube Data API
	 * video feeds, the <yt:videoId> tag specifies the ID.</li>
	 * <li>The optional startSeconds parameter accepts a float/integer and specifies the time from which the video
	 * should start playing when playVideo() is called. If you specify a startSeconds value and then call seekTo(), then
	 * the player plays from the time specified in the seekTo() call. When the video is cued and ready to play, the
	 * player will broadcast a video cued event (5).</li>
	 * <li>The optional suggestedQuality parameter specifies the suggested playback quality for the video. Please see
	 * the definition of the setPlaybackQuality function for more information about playback quality.</li>
	 * </ul>
	 */
	public final native void cueVideoById(String videoId, int startSeconds, String suggestedQuality) /*-{
		this.cueVideoById(videoId, startSeconds, suggestedQuality);
	}-*/;

	/**
	 * Loads and plays the specified video.
	 * <ul>
	 * <li>The required videoId parameter specifies the YouTube Video ID of the video to be played. In YouTube Data API
	 * video feeds, the <yt:videoId> tag specifies the ID.</li>
	 * <li>The optional startSeconds parameter accepts a float/integer. If it is specified, then the video will start
	 * from the closest keyframe to the specified time.</li>
	 * <li>The optional suggestedQuality parameter specifies the suggested playback quality for the video. Please see
	 * the definition of the setPlaybackQuality function for more information about playback quality.</li>
	 * </ul>
	 */
	public final native void loadVideoById(String videoId, int startSeconds, String suggestedQuality) /*-{
		this.loadVideoById(videoId, startSeconds, suggestedQuality);
	}-*/;

	/**
	 * Loads the specified video's thumbnail and prepares the player to play the video. The player does not request the
	 * FLV until playVideo() or seekTo() is called.
	 * <ul>
	 * <li>The mediaContentUrl must be a fully qualified YouTube player URL in the format
	 * http://www.youtube.com/v/VIDEO_ID. In YouTube Data API video feeds, the url attribute of the <media:content> tag
	 * contains a fully qualified player URL when the tag's format attribute has a value of 5.</li>
	 * <li>startSeconds accepts a float/integer and specifies the time from which the video should start playing when
	 * playVideo() is called. If you specify startSeconds and then call seekTo(), then the player plays from the time
	 * specified in the seekTo() call. When the video is cued and ready to play, the player will broadcast a video cued
	 * event (5).</li>
	 * </ul>
	 */
	public final native void cueVideoByUrl(String mediaContentUrl, int startSeconds) /*-{
		this.cueVideoByUrl(mediaContentUrl, startSeconds);
	}-*/;

	/**
	 * Loads and plays the specified video.
	 * <ul>
	 * <li>The mediaContentUrl must be a fully qualified YouTube player URL in the format
	 * http://www.youtube.com/v/VIDEO_ID. In YouTube Data API video feeds, the url attribute of the <media:content> tag
	 * contains a fully qualified player URL when the tag's format attribute has a value of 5.</li>
	 * <li>startSeconds accepts a float/integer and specifies the time from which the video should start playing. If
	 * startSeconds (number can be a float) is specified, the video will start from the closest keyframe to the
	 * specified time.</li>
	 * </ul>
	 */
	public final native void loadVideoByUrl(String mediaContentUrl, int startSeconds) /*-{
		this.loadVideoByUrl(mediaContentUrl, startSeconds);
	}-*/;

	/**
	 * Plays the currently cued/loaded video. The final player state after this function executes will be playing (1).
	 */
	public final native void playVideo() /*-{
		this.playVideo();
	}-*/;

	/**
	 * Pauses the currently playing video. The final player state after this function executes will be paused (2) unless
	 * the player is in the ended (0) state when the function is called, in which case the player state will not change.
	 */
	public final native void pauseVideo() /*-{
		this.pauseVideo();
	}-*/;

	/**
	 * Stops and cancels loading of the current video. This function should be reserved for rare situations when you
	 * know that the user will not be watching additional video in the player. If your intent is to pause the video, you
	 * should just call the pauseVideo function. If you want to change the video that the player is playing, you can
	 * call one of the queueing functions without calling stopVideo first.
	 * 
	 * <b>Important</b>: Unlike the pauseVideo function, which leaves the player in the paused (2) state, the stopVideo
	 * function could put the player into any not-playing state, including ended (0), paused (2), video cued (5) or
	 * unstarted (-1).
	 */
	public final native void stopVideo() /*-{
		this.stopVideo();
	}-*/;
}