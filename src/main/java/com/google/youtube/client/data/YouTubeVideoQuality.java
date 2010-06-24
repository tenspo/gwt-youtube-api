package com.google.youtube.client.data;

public enum YouTubeVideoQuality {

	SMALL("small"), MEDIUM("medium"), LARGE("large"), HDT20("hd720");

	private String name;

	YouTubeVideoQuality(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static YouTubeVideoQuality get(String name) {
		for (YouTubeVideoQuality quality : YouTubeVideoQuality.values()) {
			if (quality.getName().equals(name)) {
				return quality;
			}
		}

		return null;
	}
}
