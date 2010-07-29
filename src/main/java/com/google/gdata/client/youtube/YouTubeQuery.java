package com.google.gdata.client.youtube;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gdata.client.GDataProtocol;
import com.google.gdata.client.Query;
import com.google.gdata.data.geo.impl.GeoRssWhere;

public class YouTubeQuery extends Query {

	private static final String TIME = "time";
	private static final String FORMAT = "format";
	private static final String ORDERBY = "orderby";
	private static final String LANGUAGE_RESTRICT = "lr";
	private static final String RESTRICTION = "restriction";
	private static final String LOCATION = "location";
	private static final String LOCATION_RADIUS = "location-radius";
	private static final String SAFE_SEARCH = "safeSearch";
	private static final String UPLOADER = "uploader";

	private static final String COUNTRY_CODE_PATTERN = "[a-zA-Z]{2}";
	private static final String IP_V4_PATTERN = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
	private static final String LOCATION_RADIUS_PATTERN = "\\d+(ft|mi|m|km)";

	public static enum Time {
		TODAY("today"), THIS_WEEK("this_week"), THIS_MONTH("this_month"), ALL_TIME("all_time");

		private final String value;

		private Time(String value) {
			this.value = value;
		}

		/** Returns the corresponding parameter value. */
		public String toParameterValue() {
			return value;
		}

		public static Time fromParameterValue(String value) {
			if (value == null) {
				return null;
			}
			Time time = PARAMETER_TO_TIME.get(value);
			if (time == null) {
				throw new IllegalStateException("Cannot convert time value: " + value);
			}
			return time;
		}

		private static Map<String, Time> PARAMETER_TO_TIME;
		static {
			Map<String, Time> map = new HashMap<String, Time>();
			for (Time time : Time.values()) {
				map.put(time.toParameterValue(), time);
			}
			PARAMETER_TO_TIME = Collections.unmodifiableMap(map);
		}
	}

	/**
	 * Standard values for the {@code orderby} parameter.
	 */
	public static enum OrderBy {
		/**
		 * Entries are ordered by their relevance to a search query. This is the default setting for video search
		 * results feeds.
		 */
		RELEVANCE("relevance"),
		/**
		 * Entries are returned in reverse chronological order. This is the default value for video feeds other than
		 * search results feeds.
		 */
		VIEW_COUNT("viewCount"),
		/**
		 * Entries are ordered from most views to least views.
		 */
		RATING("rating"),
		/**
		 * Entries are ordered from highest rating to lowest rating.
		 */
		PUBLISHED("published");

		private final String value;

		private OrderBy(String value) {
			this.value = value;
		}

		/** Returns the corresponding parameter value. */
		public String toParameterValue() {
			return value;
		}

		public static OrderBy fromParameterValue(String value) {
			if (value == null) {
				return null;
			}
			OrderBy orderBy = PARAMETER_TO_ORDERBY.get(value);
			if (orderBy == null) {
				throw new IllegalStateException("Cannot convert orderBy value: " + value);
			}
			return orderBy;
		}

		private static Map<String, OrderBy> PARAMETER_TO_ORDERBY;
		static {
			Map<String, OrderBy> map = new HashMap<String, OrderBy>();
			for (OrderBy orderBy : OrderBy.values()) {
				map.put(orderBy.toParameterValue(), orderBy);
			}
			PARAMETER_TO_ORDERBY = Collections.unmodifiableMap(map);
		}
	}

	/**
	 * Standard values for the {@code safeSearch} parameter.
	 */
	public static enum SafeSearch {
		NONE("none"), MODERATE("moderate"), STRICT("strict");

		private final String value;

		private SafeSearch(String value) {
			this.value = value;
		}

		/** Returns the corresponding parameter value. */
		public String toParameterValue() {
			return value;
		}

		public static SafeSearch fromParameterValue(String value) {
			if (value == null) {
				return null;
			}
			SafeSearch safeSearch = PARAMETER_TO_SAFESEARCH.get(value);
			if (safeSearch == null) {
				throw new IllegalStateException("Cannot convert safeSearch value: " + value);
			}
			return safeSearch;
		}

		private static Map<String, SafeSearch> PARAMETER_TO_SAFESEARCH;
		static {
			Map<String, SafeSearch> map = new HashMap<String, SafeSearch>();
			for (SafeSearch safeSearch : SafeSearch.values()) {
				map.put(safeSearch.toParameterValue(), safeSearch);
			}
			PARAMETER_TO_SAFESEARCH = Collections.unmodifiableMap(map);
		}
	}

	/**
	 * Standard values for the {@code uploader} parameter.
	 */
	public static enum Uploader {
		PARTNER("partner");

		private final String value;

		private Uploader(String value) {
			this.value = value;
		}

		/** Returns the corresponding parameter value. */
		public String toParameterValue() {
			return value;
		}

		public static Uploader fromParameterValue(String value) {
			if (value == null) {
				return null;
			}
			Uploader uploader = PARAMETER_TO_UPLOADER.get(value);
			if (uploader == null) {
				throw new IllegalStateException("Cannot convert uploader value: " + value);
			}
			return uploader;
		}

		private static Map<String, Uploader> PARAMETER_TO_UPLOADER;
		static {
			Map<String, Uploader> map = new HashMap<String, Uploader>();
			for (Uploader uploader : Uploader.values()) {
				map.put(uploader.toParameterValue(), uploader);
			}
			PARAMETER_TO_UPLOADER = Collections.unmodifiableMap(map);
		}
	}

	/**
	 * Prefix for specifying relevance by language.
	 */
	private static final String RELEVANCE_LANGUAGE_PATTERN = "_lang_([^_]+)";

	public YouTubeQuery(String url) {
		super(url);
		useCallbackFunction();
	}

	/**
	 * The list of custom parameters associated with the query.
	 */
	private List<CustomParameter> customParameters = new ArrayList<CustomParameter>();

	/**
	 * Adds a new CustomParameter.
	 * 
	 * @param customParameter
	 *            the new custom parameter to add.
	 */
	public void addCustomParameter(CustomParameter customParameter) {
		if (customParameter == null) {
			throw new NullPointerException("Null custom parameter");
		}
		customParameters.add(customParameter);
	}

	/**
	 * Gets the value of the {@code time} parameter.
	 * 
	 * @return value of the {@code time} parameter
	 * @throws IllegalStateException
	 *             if a time value was found in the query that cannot be transformed into {@link YouTubeQuery.Time}
	 */
	public Time getTime() {
		return Time.fromParameterValue(getCustomParameterValue(TIME));
	}

	/**
	 * Sets the value of the {@code time} parameter.
	 * 
	 * @param time
	 *            time value, {@code null} to remove the parameter
	 */
	public void setTime(Time time) {
		overwriteCustomParameter(TIME, time == null ? null : time.toParameterValue());
	}

	/**
	 * Gets the value of the {@code format} parameter.
	 * 
	 * @return all defined formats, might be empty but not null
	 * @throws NumberFormatException
	 *             if the current value is invalid.
	 */
	public Set<Integer> getFormats() {
		String value = getCustomParameterValue(FORMAT);
		if (value == null) {
			return Collections.emptySet();
		}

		Set<Integer> retval = new LinkedHashSet<Integer>();

		String[] formats = value.trim().split(" *, *");
		for (String format : formats) {
			retval.add(new Integer(format));
		}
		return retval;
	}

	/**
	 * Sets the value of the {@code format} parameter.
	 * 
	 * See the documentation for a description of the different formats that are be available.
	 * 
	 * @param formats
	 *            integer id of all the formats you are interested in. Videos will be returned if and only if they have
	 *            downloadable content for at least one of these formats. No formats removes the parameter.
	 */
	public void setFormats(int... formats) {
		Set<Integer> formatSet = new LinkedHashSet<Integer>();
		for (int format : formats) {
			formatSet.add(format);
		}
		setFormats(formatSet);
	}

	/**
	 * Sets the value of the {@code format} parameter.
	 * 
	 * See the documentation for a description of the different formats that are be available.
	 * 
	 * @param formats
	 *            integer id of all the formats you are interested in. Videos will be returned if and only if they have
	 *            downloadable content for at least one of these formats. {@code null} or an empty set removes the
	 *            parameter
	 */
	public void setFormats(Set<Integer> formats) {
		if (formats == null || formats.isEmpty()) {
			overwriteCustomParameter(FORMAT, null);
			return;
		}

		StringBuilder stringValue = new StringBuilder();
		boolean isFirst = true;
		for (int format : formats) {
			if (isFirst) {
				isFirst = false;
			} else {
				stringValue.append(',');
			}
			stringValue.append(format);
		}
		overwriteCustomParameter(FORMAT, stringValue.toString());
	}

	/**
	 * Sets the value of the {@code lr} parameter.
	 * 
	 * This parameters restricts the videos that are returned to videos with its title, description and tags mostly in
	 * the specified language. It might be different from the language of the video itself.
	 * 
	 * @param languageCode
	 *            <a href="http://www.loc.gov/standards/iso639-2/php/code_list.php" > ISO 639-1 2-letter language
	 *            code</a>. {@code zh-Hans} for simplified chinese, {@code zh-Hant} for traditional chinese.
	 */
	public void setLanguageRestrict(String languageCode) {
		overwriteCustomParameter(LANGUAGE_RESTRICT, languageCode);
	}

	/**
	 * Gets the value of the {@code lr} parameter.
	 * 
	 * @return value of the {@code lr} parameter; a language code
	 */
	public String getLanguageRestrict() {
		return getCustomParameterValue(LANGUAGE_RESTRICT);
	}

	/**
	 * Gets the value of the {@code orderby} parameter.
	 * 
	 * @return value of the {@code orderby} parameter.
	 * @throws IllegalStateException
	 *             if a time value was found in the query that cannot be transformed into {@link YouTubeQuery.OrderBy}
	 */
	public OrderBy getOrderby() {
		String stringValue = getCustomParameterValue(ORDERBY);
		if (stringValue != null && stringValue.startsWith("relevance_")) {
			return OrderBy.RELEVANCE;
		}
		return OrderBy.fromParameterValue(stringValue);
	}

	/**
	 * Sets the value of the {@code orderby} parameter.
	 * 
	 * @param orderBy
	 *            value of the {@code orderby} parameter, {@code null} to remove the parameter
	 */
	public void setOrderBy(OrderBy orderBy) {
		overwriteCustomParameter(ORDERBY, orderBy == null ? null : orderBy.toParameterValue());
	}

	/**
	 * Sets order by relevance with results optimized for a specific language.
	 * 
	 * @param languageCode
	 *            {@code null} or <a href="http://www.loc.gov/standards/iso639-2/php/code_list.php" > ISO 639-1 2-letter
	 *            language code</a>. {@code zh-Hans} for simplified chinese, {@code zh-Hant} for traditional chinese.
	 */
	public void setOrderByRelevanceForLanguage(String languageCode) {
		overwriteCustomParameter(ORDERBY, languageCode == null ? OrderBy.RELEVANCE.toParameterValue()
				: "relevance_lang_" + languageCode);
	}

	/**
	 * Gets the language for which relevance ordering is optimized.
	 * 
	 * @return a language code as specified to {@link #setOrderByRelevanceForLanguage} or {@code null}
	 * @throws IllegalStateException
	 *             if ordering is not set to relevance
	 */
	public String getOrderByRelevanceForLanguage() {
		String stringValue = getCustomParameterValue(ORDERBY);

		if (stringValue == null) {
			// Default: order by relevance, no specific language
			return null;
		}

		if (getOrderby() != OrderBy.RELEVANCE) {
			throw new IllegalStateException("Not ordering by relevance. Please" + " check with getOrderBy() first");
		}

		Matcher matcher = Pattern.compile(RELEVANCE_LANGUAGE_PATTERN).matcher(stringValue);
		if (matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();

			return stringValue.substring(start, end);
		}
		return null;
	}

	/**
	 * Gets the value of the {@code safeSearch} parameter.
	 * 
	 * @return value of the {@code safeSearch} parameter.
	 * @throws IllegalStateException
	 *             if a value was found in the query that cannot be transformed into {@link YouTubeQuery.SafeSearch}
	 */
	public SafeSearch getSafeSearch() {
		String stringValue = getCustomParameterValue(SAFE_SEARCH);
		return SafeSearch.fromParameterValue(stringValue);
	}

	/**
	 * Sets the value of the {@code safeSearch} parameter.
	 * 
	 * @param safeSearch
	 *            value of {@code safeSearch} parameter, {@code null} to remove the parameter
	 */
	public void setSafeSearch(SafeSearch safeSearch) {
		overwriteCustomParameter(SAFE_SEARCH, safeSearch == null ? null : safeSearch.toParameterValue());
		overwriteCustomParameter(GDataProtocol.Query.VERSION, "2");
	}

	/**
	 * Sets the value of the {@code location} parameter.
	 * 
	 * @param where
	 *            A {@link com.google.gdata.data.geo.impl.GeoRssWhere} element describing the center of where to search.
	 */
	public void setLocation(GeoRssWhere where) {

		StringBuilder location = new StringBuilder();

		if (where != null) {
			location.append(where.getLatitude()).append(",").append(where.getLongitude());
		}

		if (hasRestrictLocation()) {
			location.append("!");
		}

		overwriteCustomParameter(LOCATION, location.toString().equals("") ? null : location.toString());
	}

	/**
	 * Returns the value of the {@code location} parameter.
	 * 
	 * @return A {@link com.google.gdata.data.geo.impl.GeoRssWhere} element describing the center of where to search.
	 */
	public GeoRssWhere getLocation() {
		String location = getCustomParameterValue(LOCATION);
		location = location.replaceAll("!", "");
		String[] parts = location.split(",");
		return new GeoRssWhere(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
	}

	/**
	 * Sets the value of the {@code location-radius} parameter. Format is "100km". Valid units of measurement are "ft",
	 * "mi", "m", and "km".
	 * 
	 * @param locationRadius
	 *            The requested search radius.
	 * @throws InvalidArgumentException
	 *             if the given string is not a properly formatted location radius.
	 */
	public void setLocationRadius(String locationRadius) {
		if (locationRadius != null && !Pattern.matches(LOCATION_RADIUS_PATTERN, locationRadius)) {
			throw new IllegalArgumentException("Invalid location radius: " + locationRadius);
		}
		overwriteCustomParameter(LOCATION_RADIUS, locationRadius);
	}

	/**
	 * Sets the value of the {@code location-radius} parameter. Format is "100km". Valid units of measurement are "ft",
	 * "mi", "m", and "km".
	 * 
	 * @return The current search radius.
	 */
	public String getLocationRadius() {
		return getCustomParameterValue(LOCATION_RADIUS);
	}

	/**
	 * Set/unset the location restrict.
	 * 
	 * @param isRestrictLocation
	 *            {@code true} if only videos that have latitude and longitude information are to be returned, {@code
	 *            false} otherwise.
	 */
	public void setRestrictLocation(boolean isRestrictLocation) {

		String location = getCustomParameterValue(LOCATION);

		if (location == null) {
			location = "";
		}

		if (isRestrictLocation) {
			if (!location.endsWith("!")) {
				overwriteCustomParameter(LOCATION, location + "!");
			}
		} else {
			location = location.replaceAll("!", "");
			// if we have no lat/long then remove the query parameter.
			if (location.length() == 0) {
				location = null;
			}
			overwriteCustomParameter(LOCATION, location);
		}
	}

	/**
	 * Returns {@code true} if the query only wants results that have latitude and longitude information.
	 * 
	 * @return {@code true} if the query is restricted by location, {@code false} otherwise.
	 */
	public boolean hasRestrictLocation() {
		String location = getCustomParameterValue(LOCATION);
		return location != null && location.endsWith("!");
	}

	/**
	 * Retrieves the country restriction set on the current query, if any.
	 * 
	 * @return the current country restriction as a two letter ISO 3166 country code, or {@code null} if no country
	 *         restriction is set.
	 * 
	 * @see #getIpRestriction()
	 */
	public String getCountryRestriction() {
		String restriction = getCustomParameterValue(RESTRICTION);
		if (restriction == null) {
			return null;
		}

		return Pattern.matches(COUNTRY_CODE_PATTERN, restriction) ? restriction : null;
	}

	/**
	 * Sets the {@code restriction} parameter to a country code.
	 * <p>
	 * This parameter restricts the returned results to content available for clients in the specified country.
	 * <p>
	 * Only one of the {@link #setCountryRestriction(String)} or {@link #setIpRestriction(String)} should be used, using
	 * both will only take into consideration the last used.
	 * 
	 * @param countryCode
	 *            a two letter ISO-3166 country code, may be {@code null} to mean no restriction at all.
	 * @throws IllegalArgumentException
	 *             if the given country code is not a well formated two letter country code.
	 */
	public void setCountryRestriction(String countryCode) {
		if (countryCode != null && !Pattern.matches(COUNTRY_CODE_PATTERN, countryCode)) {
			throw new IllegalArgumentException("Invalid country code: " + countryCode);
		}

		overwriteCustomParameter(RESTRICTION, countryCode);
	}

	/**
	 * Retrieves the IP restriction set on the current query, if any.
	 * 
	 * @return the current IP v4 restriction or {@code null} if no IP restriction is set.
	 * 
	 * @see #getCountryRestriction()
	 */
	public String getIpRestriction() {
		String restriction = getCustomParameterValue(RESTRICTION);
		if (restriction == null) {
			return null;
		}

		return Pattern.matches(IP_V4_PATTERN, restriction) ? restriction : null;
	}

	/**
	 * Sets the {@code restriction} parameter to an IP v4 address.
	 * <p>
	 * This parameter restricts the returned results to content available for clients in the country that the provided
	 * IP address belongs to.
	 * <p>
	 * Only one of the {@link #setCountryRestriction(String)} or {@link #setIpRestriction(String)} should be used, using
	 * both will only take into consideration the last used.
	 * 
	 * @param ip
	 *            a v4 IP address and may be {@code null} to mean no restriction at all.
	 * @throws IllegalArgumentException
	 *             if the given address is not a well formated IP v4 address.
	 */
	public void setIpRestriction(String ip) {
		if (ip != null && !Pattern.matches(IP_V4_PATTERN, ip)) {
			throw new IllegalArgumentException("Invalid IP v4 address: " + ip);
		}

		overwriteCustomParameter(RESTRICTION, ip);
	}

	String getCustomParameterValue(String parameterName) {
		List<CustomParameter> customParams = getCustomParameters(parameterName);
		if (customParams.isEmpty()) {
			return null;
		}
		return customParams.get(0).getValue();
	}

	/**
	 * Gets the value of the {@code uploader} parameter.
	 * 
	 * @return value of the {@code uploader} parameter.
	 * @throws IllegalStateException
	 *             if a value was found in the query that cannot be transformed into {@link YouTubeQuery.Uploader}
	 */
	public Uploader getUploader() {
		String stringValue = getCustomParameterValue(UPLOADER);
		return Uploader.fromParameterValue(stringValue);
	}

	/**
	 * Sets the value of the {@code uploader} parameter.
	 * 
	 * @param uploader
	 *            value of the {@code uploader} parameter, {@code null} to remove the parameter
	 */
	public void setUploader(Uploader uploader) {
		overwriteCustomParameter(UPLOADER, uploader == null ? null : uploader.toParameterValue());
	}
}