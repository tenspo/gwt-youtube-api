package com.google.youtube.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.youtube.client.api.FlashPlayer;

public class VideoPlayer extends FlashPlayer {

	public static int VIDEO_CONTROLS_SIZE = 43;

	private String videoFile = null;
	private String thumbFile = null;

	private boolean autoPlay = false;
	private boolean loop = false;

	private boolean embedded = false;

	public VideoPlayer(String videoFile, String thumbFile, int width, int height, boolean loop, boolean autoPlay) {
		super();

		objectElement = Document.get().createObjectElement();
		objectElement.setAttribute("data", "video/flashplayer.swf");
		objectElement.setAttribute("type", "application/x-shockwave-flash");
		objectElement.getStyle().setWidth(width, Unit.PX);
		objectElement.getStyle().setHeight(height, Unit.PX);
		embedElement = Document.get().createElement("embed");

		this.videoFile = videoFile;
		this.thumbFile = thumbFile;

		this.loop = loop;
		this.autoPlay = autoPlay;
	}

	public void embed() {
		if (!embedded) {
			setEmbedParam("movie", "video/flashplayer.swf");
			setEmbedParam("bgcolor", "FFFFFF");
			setEmbedParam("scale", "noscale");
			setEmbedParam("salign", "tl");
			setEmbedParam("base", ".");
			setEmbedParam("allowfullscreen", "true");

			String flashVars = "";

			flashVars += "video=../" + videoFile;
			flashVars += "&preview=../" + thumbFile;
			flashVars += "&loop=" + String.valueOf(loop);
			flashVars += "&autoplay=" + String.valueOf(autoPlay);

			setEmbedParam("flashvars", flashVars);

			if (objectMode) {
				objectElement.appendChild(embedElement);
				getElement().appendChild(objectElement);
			} else {
				String outer = objectElement.getString();
				String inner = embedElement.getString();
				String result = outer.replace("</object>", "").replace("</OBJECT>", "") + inner + "</object>";
				getElement().setInnerHTML(result);
			}
		}
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		embed();
	}
}
