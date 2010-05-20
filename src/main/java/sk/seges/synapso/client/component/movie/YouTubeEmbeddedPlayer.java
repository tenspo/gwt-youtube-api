package sk.seges.synapso.client.component.movie;

import net.auroris.ColorPicker.client.Color;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class YouTubeEmbeddedPlayer extends Widget {

	/**
	 * Values: 0 or 1. Default is 1. Sets whether the player should load related videos once playback of the initial
	 * video starts. Related videos are displayed in the "genie menu" when the menu button is pressed. The player search
	 * functionality will be disabled if rel is set to 0.
	 */
	private Boolean rel;

	public void setLoadRelatedVideos(Boolean rel) {
		this.rel = rel;
	}

	public boolean isLoadRelatedVideos() {
		if (this.rel == null) {
			return true;
		}
		return this.rel;
	}

	/**
	 * Values: 0 or 1. Default is 0. Sets whether or not the initial video will autoplay when the player loads.
	 */
	private Boolean autoplay;

	public void setAutoplay(Boolean autoplay) {
		this.autoplay = autoplay;
	}

	public boolean isAutoplay() {
		if (this.autoplay == null) {
			return false;
		}
		return this.autoplay;
	}

	/**
	 * Values: 0 or 1. Default is 0. In the case of a single video player, a setting of 1 will cause the player to play
	 * the initial video again and again. In the case of a playlist player (or custom player), the player will play the
	 * entire playlist and then start again at the first video.
	 */
	private Boolean loop;

	public void setLoop(Boolean loop) {
		this.loop = loop;
	}

	public boolean isLoop() {
		if (this.loop == null) {
			return false;
		}

		return this.loop;
	}

	/**
	 * Values: 0 or 1. Default is 0. Setting this to 1 will enable the Javascript API.
	 */
	private Boolean enablejsapi;

	public void setEnableJSAPI(Boolean enablejsapi) {
		this.enablejsapi = enablejsapi;
	}

	public Boolean isEnableJSAPI() {
		if (this.enablejsapi == null) {
			return false;
		}
		return this.enablejsapi;
	}

	/**
	 * Value can be any alphanumeric string. This setting is used in conjunction with the JavaScript API.
	 */
	private String playerapiid;

	public void setPlayerAPIId(String playerapiid) {
		this.playerapiid = playerapiid;
	}

	public String getPlayString() {
		return playerapiid;
	}

	/**
	 * Values: 0 or 1. Default is 0. Setting to 1 will disable the player keyboard controls. Keyboard controls are as
	 * follows: Spacebar: Play / Pause Arrow Left: Jump back 10% in the current video Arrow Right: Jump ahead 10% in the
	 * current video Arrow Up: Volume up Arrow Down: Volume Down
	 */
	private Boolean disablekb;

	public void setDisableKeyboardControls(Boolean disablekb) {
		this.disablekb = disablekb;
	}

	public Boolean isDisableKeyboardControls() {
		if (this.disablekb == null) {
			return false;
		}
		return this.disablekb;
	}

	/**
	 * Values: 0 or 1. Default is 0. Setting to 1 enables the "Enhanced Genie Menu". This behavior causes the genie menu
	 * (if present) to appear when the user's mouse enters the video display area, as opposed to only appearing when the
	 * menu button is pressed.
	 */
	private Boolean egm;

	public void setEnhancedGenieMenu(Boolean egm) {
		this.egm = egm;
	}

	public Boolean isEnhancedGenieMenu() {
		if (this.egm == null) {
			return false;
		}
		return this.egm;
	}

	/**
	 * Values: 0 or 1. Default is 0. Setting to 1 enables a border around the entire video player. The border's primary
	 * color can be set via the color1 parameter, and a secondary color can be set by the color2 parameter.
	 */
	private Boolean border;

	public void setBorder(Boolean border) {
		this.border = border;
	}

	public Boolean isBorder() {
		if (this.border == null) {
			return false;
		}
		return this.border;
	}

	/**
	 * Values: Any RGB value in hexadecimal format. color1 is the primary border color, and color2 is the video control
	 * bar background color and secondary border color.
	 */
	private Color color1;
	private Color color2;

	public Color getBorderColor1() {
		return color1;
	}

	public void setBorderColor1(Color color1) {
		this.color1 = color1;
	}

	/**
	 * @param h
	 *            Hue - valid range is 0-359
	 * @param s
	 *            Saturation - valid range is 0-100
	 * @param v
	 *            Value (Brightness) - valid range is 0-100
	 * @throws java.lang.Exception
	 *             A general exception if the Hue, Saturation, or Value variables are out of range.
	 */
	public void setBorderHSVColor1(int h, int s, int v) throws Exception {
		this.color1 = new Color();
		this.color1.setHSV(h, s, v);
	}

	/**
	 * @param r
	 *            Red - valid range is 0-255
	 * @param g
	 *            Green - valid range is 0-255
	 * @param b
	 *            Blue - valid range is 0-255
	 * @throws java.lang.Exception
	 *             Exception if the Red, Green or Blue variables are out of range.
	 */
	public void setBorderColor1(int r, int g, int b) throws Exception {
		this.color1 = new Color();
		this.color1.setRGB(r, g, b);
	}

	/**
	 * Sets the hexadecimal representation of Red, Green and Blue.
	 * 
	 * @param hex
	 *            The hexadecimal string notation. It must be 6 letters long and consist of the characters 0-9 and A-F.
	 * @throws java.lang.Exception
	 *             Exception if the hexadecimal string cannot be parsed into its Red, Green, and Blue components.
	 */
	public void setBorderColor1(String hexColor) throws Exception {
		this.color1 = new Color();
		this.color1.setHex(hexColor);
	}

	public Color getBorderColor2() {
		return color2;
	}

	public void setBorderColor2(Color color2) {
		this.color2 = color2;
	}

	/**
	 * @param h
	 *            Hue - valid range is 0-359
	 * @param s
	 *            Saturation - valid range is 0-100
	 * @param v
	 *            Value (Brightness) - valid range is 0-100
	 * @throws java.lang.Exception
	 *             A general exception if the Hue, Saturation, or Value variables are out of range.
	 */
	public void setBorderHSVColor2(int h, int s, int v) throws Exception {
		this.color2 = new Color();
		this.color2.setHSV(h, s, v);
	}

	/**
	 * @param r
	 *            Red - valid range is 0-255
	 * @param g
	 *            Green - valid range is 0-255
	 * @param b
	 *            Blue - valid range is 0-255
	 * @throws java.lang.Exception
	 *             Exception if the Red, Green or Blue variables are out of range.
	 */
	public void setBorderColor2(int r, int g, int b) throws Exception {
		this.color2 = new Color();
		this.color2.setRGB(r, g, b);
	}

	/**
	 * Sets the hexadecimal representation of Red, Green and Blue.
	 * 
	 * @param hex
	 *            The hexadecimal string notation. It must be 6 letters long and consist of the characters 0-9 and A-F.
	 * @throws java.lang.Exception
	 *             Exception if the hexadecimal string cannot be parsed into its Red, Green, and Blue components.
	 */
	public void setBorderColor21(String hexColor) throws Exception {
		this.color2 = new Color();
		this.color2.setHex(hexColor);
	}

	/**
	 * Values: A positive integer. This parameter causes the player to begin playing the video at the given number of
	 * seconds from the start of the video. Note that similar to the seekTo function, the player will look for the
	 * closest keyframe to the time you specify. This means sometimes the play head may seek to just before the
	 * requested time, usually no more than ~2 seconds.
	 */
	private Integer start;

	public void setStartSeekTime(Integer start) {
		this.start = start;
	}

	public Integer getStartSeekTime() {
		return this.start;
	}

	/**
	 * Values: 0 or 1. Default is 0. Setting to 1 enables the fullscreen button. This has no effect on the Chromeless
	 * Player. Note that you must include some extra arguments to your embed code for this to work. The bolded parts of
	 * the below example enable fullscreen functionality:
	 * 
	 * <pre>
	 * <object width="425" height="344"> 
	 * 	<param name="movie" value="http://www.youtube.com/v/u1zgFlCw8Aw?fs=1"</param>
	 *  <param name="allowFullScreen" value="true"></param>
	 * 	<param name="allowScriptAccess" value="always"></param>
	 * 	<embed src="http://www.youtube.com/v/u1zgFlCw8Aw?fs=1"
	 * 		   type="application/x-shockwave-flash" allowfullscreen="true"
	 * 		   allowscriptaccess="always" width="425" height="344"> 
	 *  </embed>
	 * </object>
	 * </pre>
	 */
	private Boolean fs;

	/**
	 * Values: 0 or 1. Default is 0. Setting to 1 enables HD playback by default. This has no effect on the Chromeless
	 * Player. This also has no effect if an HD version of the video is not available. If you enable this option, keep
	 * in mind that users with a slower connection may have an sub-optimal experience unless they turn off HD. You
	 * should ensure your player is large enough to display the video in its native resolution.
	 */
	private Boolean hd;

	/**
	 * Values: 0 or 1. Default is 1. Setting to 0 disables the search box from displaying when the video is minimized.
	 * Note that if the rel parameter is set to 0 then the search box will also be disabled, regardless of the value of
	 * showsearch.
	 */
	private Boolean showsearch;

	/**
	 * Values: 0 or 1. Default is 1. Setting to 0 causes the player to not display information like the video title and
	 * rating before the video starts playing.
	 */
	private Boolean showinfo;

	/**
	 * Values: 1 or 3. Default is 1. Setting to 1 will cause video annotations to be shown by default, whereas setting
	 * to 3 will cause video annotation to not be shown by default.
	 */
	private Boolean iv_load_policy;

	/**
	 * Values: 1. Default is based on user preference. Setting to 1 will cause closed captions to be shown by default,
	 * even if the user has turned captions off.
	 */
	private Boolean cc_load_policy;

	private String videoId;

	private Element embedElment;

	public YouTubeEmbeddedPlayer(String videoId) {
		setElement(Document.get().createObjectElement());
		embedElment = Document.get().createElement("embed");
		this.videoId = videoId;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		appendMovieParameter();
		appendEmbedElement();
		appendRelatedParameters();
	}

	private void appendRelatedParameters() {
		if (fs != null && fs == true) {
			Element paramElement = Document.get().createElement("param");
			paramElement.setAttribute("name", "allowFullScreen");
			paramElement.setAttribute("value", "true");
		}
	}
	
	private void appendEmbedElement() {
		embedElment.setAttribute("src", getURL());
		embedElment.setAttribute("type", "application/x-shockwave-flash");
		getElement().appendChild(embedElment);
	}

	private void appendMovieParameter() {
		Element paramElement = Document.get().createElement("param");
		paramElement.setAttribute("name", "movie");
		paramElement.setAttribute("value", getURL());
		getElement().appendChild(paramElement);
	}

	@Override
	public void setWidth(String width) {
		super.setWidth(width);
		embedElment.setAttribute("width", width);
	}

	@Override
	public void setHeight(String height) {
		super.setHeight(height);
		embedElment.setAttribute("height", height);
	}

	private String getURL() {
		String url = "http://www.youtube.com/v/" + videoId;

		String urlParams = getURLParams();
		if (urlParams.length() > 0) {
			return url + "?" + urlParams;
		}
		return url;
	}

	private String getURLParams() {
		String params = "";

		params = addBooleanParam(rel, "rel", params);
		params = addBooleanParam(autoplay, "autoplay", params);
		params = addBooleanParam(loop, "loop", params);
		params = addBooleanParam(enablejsapi, "enablejsapi", params);

		params = addStringParam(playerapiid, "playerapiid", params);

		params = addBooleanParam(disablekb, "disablekb", params);
		params = addBooleanParam(egm, "egm", params);
		params = addBooleanParam(border, "border", params);

		params = addColorParam(color1, "color1", params);
		params = addColorParam(color2, "color2", params);

		params = addIntegerParam(start, "start", params);

		params = addBooleanParam(fs, "fs", params);
		params = addBooleanParam(hd, "hd", params);
		params = addBooleanParam(showsearch, "showsearch", params);
		params = addBooleanParam(showinfo, "showinfo", params);
		params = addBooleanParam(iv_load_policy, "iv_load_policy", params, "1", "3");
		params = addBooleanParam(cc_load_policy, "cc_load_policy", params, "1", "1");

		return params;
	}

	private String appendParam(Object paramValue, String paramName, String params) {
		if (params != null && params.length() > 0 && paramValue != null) {
			return params + "&" + paramName + "=";
		}
		return params;
	}

	private String addBooleanParam(Boolean booleanParam, String paramName, String params) {
		return addBooleanParam(booleanParam, paramName, params, "1", "0");
	}

	private String addBooleanParam(Boolean booleanParam, String paramName, String params, String trueValue,
			String falseValue) {
		params = appendParam(booleanParam, paramName, params);
		return params + (booleanParam == null ? "" : booleanParam ? trueValue : falseValue);
	}

	private String addStringParam(String stringParam, String paramName, String params) {
		params = appendParam(stringParam, paramName, params);
		return params + (stringParam == null ? "" : stringParam);
	}

	private String addIntegerParam(Integer integerParam, String paramName, String params) {
		params = appendParam(integerParam, paramName, params);
		return params + (integerParam == null ? "" : integerParam);
	}

	private String addColorParam(Color colorParam, String paramName, String params) {
		params = appendParam(colorParam, paramName, params);
		return params + (colorParam == null ? "" : "#" + colorParam.getHex());
	}
}