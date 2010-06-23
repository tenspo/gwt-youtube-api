package com.google.youtube.client.js;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

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

	/**
	 * Seeks to a specified time in the video.
	 * <ul>
	 * <li>The seconds parameter identifies the time from which the video should start playing.
	 * <ul>
	 * <li>If the player has already buffered the portion of the video to which the user is advancing, then the player
	 * will start playing the video at the closest keyframe to the specified time. This behavior is governed by the
	 * seek() method of the Flash player's NetStream object. In practice, this means that the player could advance to a
	 * keyframe just before or just after the specified time. (For more information, see Adobe's documentation for the
	 * NetStream class.)</li>
	 * <li>If the player has not yet buffered the portion of the video to which the user is seeking, then the player
	 * will start playing the video at the closest keyframe before the specified time.</li>
	 * </ul>
	 * </li>
	 * <li>The allowSeekAhead parameter determines whether the player will make a new request to the server if seconds
	 * parameter specifies a time outside of the currently buffered video data. We recommend that you set this parameter
	 * to false while the user is dragging the mouse along a video progress bar and then set the parameter to true when
	 * the user releases the mouse.
	 * 
	 * This approach lets the user scroll to different points of the video without requesting new video streams by
	 * scrolling past unbuffered points in the video. Then, when the user releases the mouse button, the player will
	 * advance to the desired point in the video, only requesting a new video stream if the user is seeking to an
	 * unbuffered point in the stream.
	 * 
	 * </li>
	 * </ul>
	 * The final player state after this function executes will be playing (1) unless the value of the seconds parameter
	 * is greater than the video length, in which case the final player state will be ended (0).
	 */
	public final native void seekTo(int seconds, boolean allowSeekAhead) /*-{
		this.seekTo(seconds, allowSeekAhead);
	}-*/;

	/**
	 * Clears the video display. This function is useful if you want to clear the video remnant after calling
	 * stopVideo(). Note that this function has been deprecated in the ActionScript 3.0 Player API.
	 */
	public final native void clearVideo() /*-{
		this.clearVideo();
	}-*/;

	/**
	 * Mutes the player.
	 */
	public final native void mute() /*-{
		this.mute();
	}-*/;

	/**
	 * Unmutes the player
	 */
	public final native void unMute() /*-{
		this.unMute();
	}-*/;

	/**
	 * Returns true if the player is muted, false if not.
	 */
	public final native boolean isMuted() /*-{
		return this.isMuted();
	}-*/;

	/**
	 * Sets the volume. Accepts an integer between 0 and 100.
	 */
	public final native void setVolume(int volume) /*-{
		this.setVolume(volume);
	}-*/;

	/**
	 * Returns the player's current volume, an integer between 0 and 100. Note that getVolume() will return the volume
	 * even if the player is muted.}
	 */
	public final native int getVolume() /*-{
		return this.getVolume();
	}-*/;

	/**
	 * Sets the size in pixels of the player. You should not have to use this method in JavaScript as the player will
	 * automatically resize when the containing elements in the embed code have their height and width properties
	 * modified.
	 */
	public final native void setSize(int width, int height) /*-{
		this.setSize(width, height);
	}-*/;

	/**
	 * Returns the number of bytes loaded for the current video.
	 */
	public final native int getVideoBytesLoaded() /*-{
		return this.getVideoBytesLoaded();
	}-*/;

	/**
	 * Returns the size in bytes of the currently loaded/playing video.
	 */
	public final native int getVideoBytesTotal() /*-{
		return this.getVideoBytesTotal();
	}-*/;

	/**
	 * Returns the number of bytes the video file started loading from. Example scenario: the user seeks ahead to a
	 * point that hasn't loaded yet, and the player makes a new request to play a segment of the video that hasn't
	 * loaded yet.
	 */
	public final native int getVideoStartBytes() /*-{
		return this.getVideoStartBytes();
	}-*/;

	/**
	 * Returns the state of the player. Possible values are unstarted (-1), ended (0), playing (1), paused (2),
	 * buffering (3), video cued (5).
	 */
	public final native int getPlayerState() /*-{
		return this.getPlayerState();
	}-*/;

	/**
	 * Returns the elapsed time in seconds since the video started playing.
	 */
	public final native int getCurrentTime() /*-{
		return this.getCurrentTime();
	}-*/;

	/**
	 * This function retrieves the actual video quality of the current video. It returns undefined if there is no
	 * current video. Possible return values are hd720, large, medium and small.
	 */
	public final native String getPlaybackQuality() /*-{
		return this.getPlaybackQuality();
	}-*/;

	/**
	 * This function sets the suggested video quality for the current video. The function causes the video to reload at
	 * its current position in the new quality. If the playback quality does change, it will only change for the video
	 * being played. <br/>
	 * <br/>
	 * Calling this function does not guarantee that the playback quality will actually change. If the playback quality
	 * does change, it will only change for the video being played. At that time, the onPlaybackQualityChange event will
	 * fire, and your code should respond to the event rather than the fact that it called the setPlaybackQuality
	 * function. <br/>
	 * <br/>
	 * The suggestedQuality parameter value can be small, medium, large, hd720 or default. Setting the parameter value
	 * to default instructs YouTube to select the most appropriate playback quality, which will vary for different
	 * users, videos, systems and other playback conditions. <br/>
	 * <br/>
	 * When you suggest a playback quality for a video, the suggested quality will only be in effect for that video. You
	 * should select a playback quality that corresponds to the size of your video player. For example, if your page
	 * displays a 640px by 360px video player, a medium quality video will actually look better than a large quality
	 * video. The following list shows recommended playback quality levels for different player sizes:
	 * <ul>
	 * <li>Quality level small: Player resolution less than 640px by 360px.</li>
	 * <li>Quality level medium: Minimum player resolution of 640px by 360px.</li>
	 * <li>Quality level large: Minimum player resolution of 854px by 480px.</li>
	 * <li>Quality level hd720: Minimum player resolution of 1280px by 720px.</li>
	 * <li>Quality level default: YouTube selects the appropriate playback quality. This setting effectively reverts the
	 * quality level to the default state and nullifies any previous efforts to set playback quality using the
	 * cueVideoById, loadVideoById or setPlaybackQuality functions.</li>
	 * </ul>
	 * 
	 * If you call the setPlaybackQuality function with a suggestedQuality level that is not available for the video,
	 * then the quality will be set to the next lowest level that is available. For example, if you request a quality
	 * level of large, and that is unavailable, then the playback quality will be set to medium (as long as that quality
	 * level is available). <br/>
	 * <br/>
	 * In addition, setting suggestedQuality to a value that is not a recognized quality level is equivalent to setting
	 * suggestedQuality to default.
	 */
	public final native void setPlaybackQuality(String suggestedQuality) /*-{
		this.getPlaybackQuality(suggestedQuality);
	}-*/;

	/**
	 * This function returns the set of quality formats in which the current video is available. You could use this
	 * function to determine whether the video is available in a higher quality than the user is viewing, and your
	 * player could display a button or other element to let the user adjust the quality. <br/>
	 * The function returns an array of strings ordered from highest to lowest quality. Possible array element values <br/>
	 * are hd720, large, medium and small. This function returns an empty array if there is no current video. <br/>
	 * <br/>
	 * Your client should not automatically switch to use the highest (or lowest) quality video or to any unknown format
	 * name. YouTube could expand the list of quality levels to include formats that may not be appropriate in your
	 * player context. Similarly, YouTube could remove quality options that would be detrimental to the user experience.
	 * By ensuring that your client only switches to known, available formats, you can ensure that your client's
	 * performance will not be affected by either the introduction of new quality levels or the removal of quality
	 * levels that are not appropriate for your player context.
	 */
	public final native JsArrayString getAvailableQualityLevels() /*-{
		return this.getAvailableQualityLevels();
	}-*/;

	/**
	 * Returns the duration in seconds of the currently playing video. Note that getDuration() will return 0 until the
	 * video's metadata is loaded, which normally happens just after the video starts playing.
	 */
	public final native int getDuration() /*-{
		return this.getDuration();
	}-*/;

	/**
	 * Returns the YouTube.com URL for the currently loaded/playing video.
	 */
	public final native String getVideoUrl() /*-{
		return this.getVideoUrl();
	}-*/;

	/**
	 * Returns the embed code for the currently loaded/playing video.
	 */
	public final native String getVideoEmbedCode() /*-{
		return this.getVideoEmbedCode();
	}-*/;
}