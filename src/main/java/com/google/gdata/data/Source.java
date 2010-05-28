package com.google.gdata.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.gwttime.time.DateTime;

import com.google.gdata.json.DateTimePattern;
import com.google.gdata.json.Field;
import com.google.gdata.json.JsonObject;
import com.google.gwt.json.client.JSONObject;

public class Source extends JSON implements IJSONProcessor {

	@JsonObject
	protected static class SourceState {

		/** Feed ID. */
		@Field
		public String id;

		/** Last updated timestamp. */
		@DateTimePattern(UPDATED_DATE_PATTERN)
		public DateTime updated;

		/** Categories. */
		@Field
		public HashSet<Category> categories = new HashSet<Category>();

		/** Title. */
		@Field
		public TextConstruct title;

		/** Subtitle. */
		@Field
		public TextConstruct subtitle;

		/** Rights. */
		@Field
		public TextConstruct rights;

		/** Icon URI. */
		@Field
		public String icon;

		/** Logo image URI. */
		@Field
		public String logo;

		/** Links. */
		@Field
		public LinkedList<Link> links = new LinkedList<Link>();

		/** Authors. */
		@Field
		public LinkedList<Person> authors = new LinkedList<Person>();

		/** Contributors. */
		@Field
		public LinkedList<Person> contributors = new LinkedList<Person>();

		/** Generator. */
		@Field
		public Generator generator;
	}

	/**
	 * Basic state for this source. May be shared across multiple adapted instances associated with the same logical
	 * source.
	 */
	protected SourceState srcState;

	/**
	 * Constructs a new {@link Source} instance with no initial state.
	 */
	public Source() {
		srcState = new SourceState();
	}

	/**
	 * Copy constructor that initializes a new Source instance to have identical contents to another instance, using a
	 * shared reference to the same {@link SourceState}.
	 */
	protected Source(Source sourceSource) {
		srcState = sourceSource.srcState;
	}

	public String getId() {
		return srcState.id;
	}

	public void setId(String v) {
		srcState.id = v;
	}

	public DateTime getUpdated() {
		return srcState.updated;
	}

	public void setUpdated(DateTime v) {
		srcState.updated = v;
	}

	public Set<Category> getCategories() {
		return srcState.categories;
	}

	public TextConstruct getTitle() {
		return srcState.title;
	}

	public void setTitle(TextConstruct v) {
		srcState.title = v;
	}

	public TextConstruct getSubtitle() {
		return srcState.subtitle;
	}

	public void setSubtitle(TextConstruct v) {
		srcState.subtitle = v;
	}

	public TextConstruct getRights() {
		return srcState.rights;
	}

	public void setRights(TextConstruct v) {
		srcState.rights = v;
	}

	public String getIcon() {
		return srcState.icon;
	}

	public void setIcon(String v) {
		srcState.icon = v;
	}

	public String getLogo() {
		return srcState.logo;
	}

	public void setLogo(String v) {
		srcState.logo = v;
	}

	public List<Link> getLinks() {
		return srcState.links;
	}

	public List<Person> getAuthors() {
		return srcState.authors;
	}

	public List<Person> getContributors() {
		return srcState.contributors;
	}

	public Generator getGenerator() {
		return srcState.generator;
	}

	public void setGenerator(Generator v) {
		srcState.generator = v;
	}

	public Generator setGenerator(String version, String uri, String name) {
		Generator gen = new Generator();
		gen.setVersion(version);
		gen.setUri(uri);
		gen.setName(name);
		setGenerator(gen);
		return gen;
	}

	/**
	 * Retrieves the first link with the supplied {@code rel} and/or {@code type} value.
	 * <p>
	 * If either parameter is {@code null}, doesn't return matches for that parameter.
	 */
	public Link getLink(String rel, String type) {

		for (Link link : srcState.links) {
			if (link.matches(rel, type)) {
				return link;
			}
		}

		return null;
	}

	/**
	 * Return the links that match the given {@code rel} and {@code type} values.
	 * 
	 * @param relToMatch
	 *            {@code rel} value to match or {@code null} to match any {@code rel} value.
	 * @param typeToMatch
	 *            {@code type} value to match or {@code null} to match any {@code type} value.
	 * @return matching links.
	 */
	public List<Link> getLinks(String relToMatch, String typeToMatch) {
		List<Link> result = new ArrayList<Link>();
		for (Link link : srcState.links) {
			if (link.matches(relToMatch, typeToMatch)) {
				result.add(link);
			}
		}
		return result;
	}

	public void addLink(Link link) {
		srcState.links.add(link);
	}

	public Link addLink(String rel, String type, String href) {
		Link link = new Link(rel, type, href);
		addLink(link);
		return link;
	}

	/**
	 * Remove all links that match the given {@code rel} and {@code type} values.
	 * 
	 * @param relToMatch
	 *            {@code rel} value to match or {@code null} to match any {@code rel} value.
	 * @param typeToMatch
	 *            {@code type} value to match or {@code null} to match any {@code type} value.
	 */
	public void removeLinks(String relToMatch, String typeToMatch) {
		for (Iterator<Link> iterator = srcState.links.iterator(); iterator.hasNext();) {
			Link link = iterator.next();
			if (link.matches(relToMatch, typeToMatch)) {
				iterator.remove();
			}
		}
	}

	/**
	 * Removes all links.
	 */
	public void removeLinks() {
		srcState.links.clear();
	}

	/**
	 * Adds a link pointing to an HTML representation.
	 * 
	 * @param htmlUri
	 *            link URI
	 * 
	 * @param lang
	 *            optional language code
	 * 
	 * @param title
	 *            optional title
	 */
	public void addHtmlLink(String htmlUri, String lang, String title) {

		Link link = new Link();
		link.setRel(Link.Rel.ALTERNATE);
		link.setType(Link.Type.HTML);
		link.setHref(htmlUri);

		if (lang != null) {
			link.setHrefLang(lang);
		}

		if (title != null) {
			link.setTitle(title);
		}

		srcState.links.add(link);
	}

	/**
	 * Retrieves the first HTML link.
	 * 
	 * @return the link
	 */
	public Link getHtmlLink() {
		Link htmlLink = getLink(Link.Rel.ALTERNATE, Link.Type.HTML);
		return htmlLink;
	}

	private static final String ID_ATTRIBUTE = "id";
	private static final String UPDATED_ATTRIBUTE = "updated";
	private static final String UPDATED_DATE_PATTERN = "y-M-d'T'H:m:s.SSSZ";
	//yyyy-MM-ddTHH:mm:ss.SSSZZ
	private static final String TITLE_ATTRIBUTE = "title";
	private static final String SUBTITLE_ATTRIBUTE = "subtitle";
	private static final String RIGHTS_ATTRIBUTE = "rights";
	private static final String ICON_ATTRIBUTE = "icon";
	private static final String LOGO_ATTRIBUTE = "logo";
	
	@Override
	public void get(JSONObject jsonObject) {
		
		srcState.id = getString(jsonObject, ID_ATTRIBUTE);
		srcState.updated = getDateTime(jsonObject, UPDATED_ATTRIBUTE, UPDATED_DATE_PATTERN);
		
//		public HashSet<Category> categories = new HashSet<Category>();

		srcState.title = getText(jsonObject, TITLE_ATTRIBUTE);
		srcState.subtitle = getText(jsonObject, SUBTITLE_ATTRIBUTE);
		srcState.rights = getText(jsonObject, RIGHTS_ATTRIBUTE);
		srcState.icon = getString(jsonObject, ICON_ATTRIBUTE);
		srcState.logo = getString(jsonObject, LOGO_ATTRIBUTE);
//
//		/** Links. */
//		public LinkedList<Link> links = new LinkedList<Link>();
//
//		/** Authors. */
//		public LinkedList<Person> authors = new LinkedList<Person>();
//
//		/** Contributors. */
//		public LinkedList<Person> contributors = new LinkedList<Person>();
//
//		/** Generator. */
//		public Generator generator;	
		}

}