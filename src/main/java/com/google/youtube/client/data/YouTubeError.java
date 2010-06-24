package com.google.youtube.client.data;

public enum YouTubeError {

	/**
	 * This occurs when a video has been removed (for any reason), or it has been marked as private
	 */
	VIDEO_NOT_FOUND(100),
	/**
	 * This occurs when the video requested does not allow playback in the embedded players.
	 */
	PLAYBACK_NOT_ALLOWED(101),

	/**
	 * The error code 150 is the same as 101, it's just 101 in disguise!
	 */
	ANOTHER_PLAYBACK_NOT_ALLOWED(150);

	private int errorCode;

	YouTubeError(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public static YouTubeError get(int errorCode) {
		for (YouTubeError error : YouTubeError.values()) {
			if (error.getErrorCode() == errorCode) {
				return error;
			}
		}

		return null;
	}
}
