package com.google.youtube.client.api;

import com.google.gdata.data.Color;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class FlashPlayer extends Widget {

	protected Element embedElement;

	public FlashPlayer() {
		setElement(Document.get().createObjectElement());
		embedElement = Document.get().createElement("embed");
//		getElement().appendChild(embedElement);
		embedElement.setAttribute("type", "application/x-shockwave-flash");
	}

	/**
	 * Specifies the width of the movie in either pixels or percentage of browser window
	 */
	@Override
	public void setWidth(String width) {
		super.setWidth(width);
		setEmbedAttribute("width", width);
	}

	/**
	 * Specifies the height of the movie in either pixels or percentage of browser window.
	 */
	@Override
	public void setHeight(String height) {
		super.setHeight(height);
		setEmbedAttribute("height", height);
	}

	protected void setEmbedAttribute(String name, String value) {
		embedElement.setAttribute(name, value);
	}

	protected void setObjectAttribute(String name, String value) {
		Element paramElement = Document.get().createElement("param");
		paramElement.setAttribute("name", name);
		paramElement.setAttribute("value", value);
		getElement().appendChild(paramElement);
	}

	/**
	 * Identifies the ActiveX control for the browser.
	 */
	public void setClassid(String classid) {
		setObjectAttribute("classid", classid);
	}

	/**
	 * Identifies the location of the Flash Player ActiveX control so that the browser can automatically download it if
	 * it is not already installed.
	 */
	public void setCodebase(String codebase) {
		setObjectAttribute("codebase", codebase);
	}

	/**
	 * Specifies the location (URL) of the movie to be loaded.
	 */
	public void setUrl(String url) {
		setObjectAttribute("movie", url);
		setEmbedAttribute("src", url);
	}

	/**
	 * Identifies the location of the Flash Player plug-in so that the user can download it if it is not already
	 * installed. EMBED only.
	 */
	public void setPluginsPage(String pluginsPage) {
		setEmbedAttribute("pluginspage", pluginsPage);
	}

	/**
	 * Movie Identifier. Identifies the Flash movie to the host environment (a web browser, for example) so that it can
	 * be referenced using a scripting language.
	 */
	public void setId(String id) {
		getElement().setAttribute("id", id);
	}

	/**
	 * Movie name. Identifies the Flash movie to the host environment (a web browser, typically) so that it can be
	 * referenced using a scripting language such as JavaScript or VBScript.
	 */
	public void setName(String name) {
		setEmbedAttribute("name", name);
	}

	/**
	 * Possible values: true, false. Specifies whether the browser should start Java when loading the Flash Player for
	 * the first time. The default value is false if this attribute is omitted. If you use JavaScript and Flash on the
	 * same page, Java must be running for the FSCommand to work.
	 */
	public void setSWLiveConnect(boolean value) {
		setBothParams("swliveconnect", convertBooleanParam(value));
	}

	/**
	 * Possible values: true, false. Specifies whether the movie begins playing immediately on loading in the browser.
	 * The default value is true if this attribute is omitted.
	 */
	public void setPlay(boolean play) {
		setBothParams("play", convertBooleanParam(play));
	}

	/**
	 * Possible values: true, false. Specifies whether the movie repeats indefinitely or stops when it reaches the last
	 * frame. The default value is true if this attribute is omitted.
	 */
	public void setLoop(boolean loop) {
		setBothParams("loop", convertBooleanParam(loop));
	}

	/**
	 * Possible values: true, false. true displays the full menu, allowing the user a variety of options to enhance or
	 * control playback. false displays a menu that contains only the Settings option and the About Flash option.
	 */
	public void showFullMenu(boolean show) {
		setBothParams("menu", convertBooleanParam(show));
	}

	public static enum PlaybackQuality {
		/**
		 * favors playback speed over appearance and never uses anti-aliasing.
		 */
		LOW,
		/**
		 * emphasizes speed at first but improves appearance whenever possible. Playback begins with anti-aliasing
		 * turned off. If the Flash Player detects that the processor can handle it, anti-aliasing is turned on.
		 */
		AUTOLOW,
		/**
		 * emphasizes playback speed and appearance equally at first but sacrifices appearance for playback speed if
		 * necessary. Playback begins with anti-aliasing turned on. If the actual frame rate drops below the specified
		 * frame rate, anti-aliasing is turned off to improve playback speed. Use this setting to emulate the View >
		 * Antialias setting in Flash.
		 */
		AUTOHIGH,
		/**
		 * applies some anti-aliasing and does not smooth bitmaps. It produces a better quality than the Low setting,
		 * but lower quality than the High setting.
		 */
		MEDIUM,
		/**
		 * favors appearance over playback speed and always applies anti-aliasing. If the movie does not contain
		 * animation, bitmaps are smoothed; if the movie has animation, bitmaps are not smoothed.
		 */
		HIGH,
		/**
		 * provides the best display quality and does not consider playback speed. All output is anti-aliased and all
		 * bitmaps are smoothed.
		 */
		BEST;
	}

	/**
	 * Sets the playback quality
	 */
	public void setQuality(PlaybackQuality quality) {
		setBothParams("quality", quality.name().toLowerCase());
	}

	public static enum PlaybackScale {
		/**
		 * Default - makes the entire movie visible in the specified area without distortion, while maintaining the
		 * original aspect ratio of the movie. Borders may appear on two sides of the movie
		 */
		SHOWALL,
		/**
		 * scales the movie to fill the specified area, without distortion but possibly with some cropping, while
		 * maintaining the original aspect ratio of the movie.
		 */
		NOORDER,
		/**
		 * makes the entire movie visible in the specified area without trying to preserve the original aspect ratio.
		 * Distortion may occur.
		 */
		EXACTFIT;
	}

	/**
	 * Sets the playback scale
	 */
	public void setScale(PlaybackScale scale) {
		setBothParams("scale", scale.name().toLowerCase());
	}

	public static enum Align {

		TOP("t"), LEFT("l"), RIGHT("r");

		private String name;

		Align(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * Possible values: l, t, r.
	 * <ul>
	 * <li>Default centers the movie in the browser window and crops edges if the browser window is smaller than the
	 * movie.</li>
	 * <li>l (left), r (right), and t (top) align the movie along the corresponding edge of the browser window and crop
	 * the remaining three sides as needed.</li>
	 * </ul>
	 * </p>
	 */
	public void setAlign(Align align) {
		getElement().setAttribute("align", align.getName());
		setEmbedAttribute("align", align.getName());
	}

	public static enum SAlign {
		TOP("t"), LEFT("l"), RIGHT("r"), TOPLEFT("tl"), TOPRIGHT("tr");

		private String name;

		SAlign(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * Possible values: l, t, r, tl, tr.
	 * <ul>
	 * <li>l, r, and t align the movie along the left, right, or top edge, respectively, of the browser window and crop
	 * the remaining three sides as needed.</li>
	 * <li>tl and tr align the movie to the top left and top right corner, respectively, of the browser window and crop
	 * the bottom and remaining right or left side as needed.</li>
	 * </ul>
	 */
	public void setSAlign(SAlign salign) {
		setBothParams("salign", salign.getName());
	}

	public static enum WMode {
		/**
		 * movie plays in its own rectangular window on a web page.
		 */
		WINDOW,

		/**
		 * the movie hides everything on the page behind it.
		 */
		OPAQUE,

		/**
		 * the background of the HTML page shows through all transparent portions of the movie, this may slow animation
		 * performance.
		 */
		TRANSPARENT;
	}

	/**
	 * Possible values: window, opaque, transparent. Sets the Window Mode property of the Flash movie for transparency,
	 * layering, and positioning in the browser.
	 */
	public void setWMode(WMode wmode) {
		setBothParams("wmode", wmode.name().toLowerCase());
	}

	/**
	 * [ hexadecimal RGB value] in the format #RRGGBB . Specifies the background color of the movie. Use this attribute
	 * to override the background color setting specified in the Flash file. This attribute does not affect the
	 * background color of the HTML page.
	 */
	public void setBackgroundColor(String hexColor) throws Exception {
		Color backgroundColor = new Color();
		backgroundColor.setHex(hexColor);
		setBackgroundColor(backgroundColor);
	}

	/**
	 * Specifies the background color of the movie. Use this attribute to override the background color setting
	 * specified in the Flash file. This attribute does not affect the background color of the HTML page.
	 */
	public void setBackgroundColor(Color backgroundColor) {
		setBothParams("bgcolor", backgroundColor.getHex().toUpperCase());
	}

	/**
	 * Specifies the background color of the movie. Use this attribute to override the background color setting
	 * specified in the Flash file. This attribute does not affect the background color of the HTML page.
	 */
	public void setBackgroundHSVColor(int h, int s, int v) throws Exception {
		Color backgroundColor = new Color();
		backgroundColor.setHSV(h, s, v);
		setBackgroundColor(backgroundColor);
	}

	/**
	 * Specifies the background color of the movie. Use this attribute to override the background color setting
	 * specified in the Flash file. This attribute does not affect the background color of the HTML page.
	 */
	public void setBackgroundColor(int r, int g, int b) throws Exception {
		Color backgroundColor = new Color();
		backgroundColor.setRGB(r, g, b);
		setBackgroundColor(backgroundColor);
	}

	/**
	 * Specifies the base directory or URL used to resolve all relative path statements in the Flash Player movie. This
	 * attribute is helpful when your Flash Player movies are kept in a different directory from your other files
	 */
	public void setBase(String base) {
		setBothParams("base", base);
	}

	/**
	 * Possible values: variable to pass to Flash Player. Requires Macromedia Flash Player 6 or later.
	 * <ul>
	 * <li>Used to send root level variables to the movie. The format of the string is a set of name=value combinations
	 * separated by '&'.</li>
	 * <li>Browsers will support string sizes of up to 64KB (65535 bytes) in length.</li>
	 * <li>For more information on FlashVars, please refer to "Using FlashVars to pass variables to a SWF"</li>
	 * </ul>
	 */
	public void setFlashVars(String flashvars) {
		setBothParams("flashvars", flashvars);
	}

	/**
	 * The AllowScriptAccess parameter in the HTML code that loads a SWF file controls the ability to perform outbound
	 * URL access from within the SWF file. Set this parameter inside the PARAM or EMBED tag. If no value is set for
	 * AllowScriptAccess, the SWF file and the HTML page can communicate only if both are from the same domain.
	 * 
	 * @author psimuns
	 */
	public static enum ScriptAccess {
		/**
		 * The SWF file can communicate with the HTML page in which it is embedded even when the SWF file is from a
		 * different domain than the HTML page.
		 */
		ALWAYS("always"),

		/**
		 * The SWF file can communicate with the HTML page in which it is embedded only when the SWF file is from the
		 * same domain as the HTML page. This is the default value for AllowScriptAccess. Use this setting, or do not
		 * set a value for AllowScriptAccess, to prevent a SWF file hosted from one domain from accessing a script in an
		 * HTML page that comes from another domain.
		 */
		SAMEDOMAIN("sameDomain"),

		/**
		 * the SWF file cannot communicate with any HTML page. Using this value is deprecated and not recommended, and
		 * shouldn't be necessary if you don't serve untrusted SWF files from your own domain. If you do need to serve
		 * untrusted SWF files, Adobe recommends that you create a distinct subdomain and place all untrusted content
		 * there.
		 */
		NEVER("never");

		private String name;

		ScriptAccess(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * Sets the allow script access The AllowScriptAccess parameter can have one of three possible values: "always",
	 * "sameDomain", or "never":
	 */
	public void setAllowScriptAccess(ScriptAccess scriptAccess) {
		setBothParams("allowScriptAccess", scriptAccess.name().toLowerCase());
	}

	public void setAllowFullScreen(boolean fullScreen) {
		setObjectAttribute("allowFullScreen", convertBooleanParam(fullScreen));
	}
	
	protected void setBothParams(String name, String value) {
		setObjectAttribute(name, value);
		setEmbedAttribute(name, value);
	}

	protected String convertBooleanParam(Boolean booleanParam) {
		return booleanParam ? "true" : "false";
	}
}