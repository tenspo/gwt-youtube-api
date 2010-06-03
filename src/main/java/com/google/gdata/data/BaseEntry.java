package com.google.gdata.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.gwttime.time.DateTime;

import sk.seges.acris.json.client.annotation.DateTimePattern;
import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionPoint;

import com.google.gdata.client.Service;

/**
 * The BaseEntry class is an abstract base class that defines the in-memory object model for GData entries.
 * <p>
 * It is capable of parsing the Atom XML for an {@code <atom:entry>} element as well as any contained Extension
 * elements. It can generate both Atom and RSS 2.0 representations of the entry from the object model.
 * <p>
 * The BaseEntry class implements the Kind.Adaptable interface, meaning it is possible to create new Kind.Adaptor
 * subtypes that defines a custom extension model (and associated convenience APIs) for a BaseEntry subtypes that use
 * Atom/RSS extensions to extend the content model for a particular type of data.
 * <p>
 * An Kind.Adaptor subclass of BaseEntry should do the following:
 * <ul>
 * <li>Include a Kind.Term annotation on the class declaration that defines the {@link Category} term value for the
 * GData kind handled by the adaptor.</li>
 * <li>Provide a constructor that takes a single BaseEntry parameter as an argument that is used when adapting a generic
 * entry type to a more specific one.</li>
 * <li>Implement the Kind.Adaptor#declareExtensions(ExtensionProfile) method and use it to declare the extension model
 * for the adapted instance within the profile passed as a parameter. This is used to auto-extend an extension profile
 * when kind Category tags are found during parsing of content.</li>
 * <li>Expose convenience APIs to retrieve and set extension attributes, with an implementions that delegates to
 * {@link ExtensionPoint} methods to store/retrieve the extension data.
 * </ul>
 * 
 * Here is the Relax-NG schema that represents an Atom 1.0 entry:
 * 
 * <pre>
 * atomEntry =
 *   element atom:entry {
 *     atomCommonAttributes,
 *     (atomAuthor*
 *     & atomCategory*
 *     & atomContent?
 *     & atomContributor*
 *     & atomId
 *     & atomLink*
 *     & atomPublished?
 *     & atomRights?
 *     & atomSource?
 *     & atomSummary?
 *     & atomTitle
 *     & atomUpdated
 *     & extensionElement*)
 * </pre>
 * 
 * @param <E>
 *            the entry type associated with the bound subtype.
 * 
 * 
 */
public abstract class BaseEntry extends ExtensionPoint implements IEntry {
	/**
	 * The EntryState class provides a simple structure that encapsulates the attributes of an Atom entry that should be
	 * shared with a shallow copy if the entry is adapted to a more specific BaseEntry Kind.Adaptor subtypes.
	 * 
	 * @see BaseEntry#BaseEntry(BaseEntry)
	 */
	@JsonObject
	public static class EntryState {

		/** Entry id. */
		@Field
		public String id;

		/**
		 * Version ID. This is a unique number representing this particular entry. Every update changes the version ID
		 * (unless the update doesn't modify anything, in which case it's permissible for version ID to stay the same).
		 * Services are free to interpret this string in the most convenient way. Some services may choose to use a
		 * monotonically increasing sequence of version IDs. Other services may compute a hash of entry properties and
		 * use that.
		 * <p>
		 * This property is only used for services to communicate the current version ID back to the servlet. It is NOT
		 * set when entries are parsed (either from requests or from arbitrary XML).
		 */
		@Field
		public String versionId;

		/**
		 * Etag. See RFC 2616, Section 3.11. If there is no entity tag, this variable is null. Etags are provided not
		 * only on top-level entries, but also on entries within feeds (in the form of a gd:etag attribute).
		 */
		@Field
		public String etag;

		/**
		 * gd:fields. This is the field selection associated with this entry. If not {@code null} then this entry
		 * represents a partial entry.
		 */
		@Field
		public String fields;

		/**
		 * gd:kind. This is the kind attribute for this entry. If there is no kind attribute for this entry, this
		 * variable is null.
		 */
		@Field
		public String kind;

		/** Creation timestamp. Ignored on updates. */
		@Field
		@DateTimePattern(Source.DATE_TIME_PATTERN)
		public DateTime published;

		/** Last updated timestamp. */
		@Field
		@DateTimePattern(Source.DATE_TIME_PATTERN)
		public DateTime updated;

		/** Last edit timestamp */
		@Field
		@DateTimePattern(Source.DATE_TIME_PATTERN)
		public DateTime edited;

		/** Categories of entry. */
		@Field("category")
		public HashSet<Category> categories = new HashSet<Category>();

		/** Title of entry. */
		@Field
		public TextConstruct title;

		/** Summary of entry. */
		@Field
		public TextConstruct summary;

		/** Rights of entry. */
		@Field
		public TextConstruct rights;

		/** Content of entry. */
		// TODO
		// @Field
		public Content content;

		/** Links of entry. */
		@Field("link")
		public LinkedList<Link> links = new LinkedList<Link>();

		/** Authors of entry. */
		@Field("author")
		public LinkedList<Person> authors = new LinkedList<Person>();

		/** Contributors of entry. */
		@Field("contributor")
		public LinkedList<Person> contributors = new LinkedList<Person>();

	    /**
	     * Atom publication control status, which contains the draft status.
	     */
	    public PubControl pubControl;

		/** Source. */
		public Source source;

		/** Service. */
		public Service service;

		/** {code true} if the entry can be modified by a client. */
		public boolean canEdit = true;

	}

	/**
	 * Basic state for this entry. May be shared across multiple adapted instances associated with the same logical
	 * entry.
	 */
	protected EntryState state;

	/**
	 * Constructs a new BaseEntry instance.
	 */
	protected BaseEntry() {
		state = new EntryState();
	}

	/**
	 * Copy constructor that initializes a new BaseEntry instance to have identical contents to another instance, using
	 * a shared reference to the same {@link EntryState}. Kind.Adaptor subclasses of {@code BaseEntry} can use this
	 * constructor to create adaptor instances of an entry that share state with the original.
	 */
	protected BaseEntry(BaseEntry sourceEntry) {
		state = sourceEntry.state;
	}

	public String getId() {
		return state.id;
	}

	public void setId(String v) {
		if (v != null && "-".equals(v)) {
			// Disallow dash as an entry id. It leads to ambiguity because
			// we use a dash to separate category queries in a feed URI.
			// Does /feeds/feed-id/-/X mean a feed request with a category
			// query "X", or an entry request with "-" as the entry ID
			// and X as the version number. In {@link UriTemplate} we've
			// made the choice that it means a feed request. Therefore "-"
			// cannot be an entry ID.
			throw new IllegalArgumentException("Entry.id must not be equal to '-'.");
		}
		state.id = v;
	}

	public String getVersionId() {
		return state.versionId;
	}

	public void setVersionId(String v) {
		state.versionId = v;
	}

	public String getEtag() {
		return state.etag;
	}

	public void setEtag(String v) {
		state.etag = v;
	}

	/**
	 * Returns the current fields selection for this partial entry. A value of {@code null} indicates the entry is not a
	 * partial entry.
	 */
	public String getSelectedFields() {
		return state.fields;
	}

	/**
	 * Sets the current fields selection for this partial entry. A value of {@code null} indicates the entry is not a
	 * partial entry.
	 */
	public void setSelectedFields(String v) {
		state.fields = v;
	}

	/**
	 * Set draft status. Passing a null value means unsetting the draft status.
	 * 
	 * @param v
	 *            Draft status, or null to unset.
	 */
	public void setDraft(Boolean v) {
		if (state.pubControl == null) {
			if (!Boolean.TRUE.equals(v)) {
				// No need to create a PubControl entry for that
				return;
			}
			state.pubControl = new PubControl();
		}
		state.pubControl.setDraft(v);
	}

	/**
	 * Draft status.
	 * 
	 * @return True if draft status is set and equals true.
	 */
	public boolean isDraft() {
		return state.pubControl != null ? state.pubControl.isDraft() : false;
	}

	/**
	 * Gets the app:control tag.
	 * 
	 * @return pub control tag or null if unset
	 */
	public PubControl getPubControl() {
		return state.pubControl;
	}

	/**
	 * Sets the app:control tag, which usually contains app:draft.
	 * 
	 * @param value
	 *            PubControl the new object or null
	 */
	public void setPubControl(PubControl value) {
		state.pubControl = value;
	}

	public String getKind() {
		return state.kind;
	}

	public void setKind(String v) {
		state.kind = v;
	}

	public DateTime getPublished() {
		return state.published;
	}

	public void setPublished(DateTime v) {
		state.published = v;
	}

	public DateTime getUpdated() {
		return state.updated;
	}

	public void setUpdated(DateTime v) {
		state.updated = v;
	}

	public DateTime getEdited() {
		return state.edited;
	}

	public void setEdited(DateTime v) {
		state.edited = v;
	}

	public Set<Category> getCategories() {
		return state.categories;
	}

	public TextConstruct getTitle() {
		return state.title;
	}

	public void setTitle(TextConstruct v) {
		state.title = v;
	}

	public TextConstruct getSummary() {
		return state.summary;
	}

	public void setSummary(TextConstruct v) {
		state.summary = v;
	}

	public TextConstruct getRights() {
		return state.rights;
	}

	public void setRights(TextConstruct v) {
		state.rights = v;
	}

	public Content getContent() {
		return state.content;
	}

	public void setContent(Content v) {
		state.content = v;
	}

	public void setService(Service s) {
		state.service = s;
	}

	public Service getService() {
		return state.service;
	}

	public boolean getCanEdit() {
		return state.canEdit;
	}

	public void setCanEdit(boolean v) {
		state.canEdit = v;
	}

	/**
	 * Assumes the content element's contents are text and returns them as a TextContent.
	 * 
	 * @return A TextContent containing the value of the content tag.
	 * 
	 * @throws IllegalStateException
	 *             If the content element is not a text type.
	 */
	public TextContent getTextContent() {
		Content content = getContent();
		if (!(content instanceof TextContent)) {
			throw new IllegalStateException("Content object is not a TextContent");
		}
		return (TextContent) getContent();
	}

	/**
	 * Assumes the <content> element's contents are plain-text and returns its value as a string
	 * 
	 * @return A string containing the plain-text value of the content tag.
	 * 
	 * @throws IllegalStateException
	 *             If the content element is not a text type.
	 */
	public String getPlainTextContent() {
		TextConstruct textConstruct = getTextContent().getContent();
		if (!(textConstruct instanceof PlainTextConstruct)) {
			throw new IllegalStateException("TextConstruct object is not a PlainTextConstruct");
		}
		return textConstruct.getPlainText();
	}

	public void setContent(TextConstruct tc) {
		state.content = new TextContent(tc);
	}

	/** Retrieves the resource edit link. */
	public Link getEditLink() {
		Link editLink = getLink(Link.Rel.ENTRY_EDIT, Link.Type.ATOM);
		return editLink;
	}

	public List<Link> getLinks() {
		return state.links;
	}

	public void addLink(Link link) {
		state.links.add(link);
	}

	public Link addLink(String rel, String type, String href) {
		Link link = new Link(rel, type, href);
		addLink(link);
		return link;
	}

	public List<Person> getAuthors() {
		return state.authors;
	}

	public List<Person> getContributors() {
		return state.contributors;
	}

	public Source getSource() {
		return state.source;
	}

	public void setSource(Source v) {
		state.source = v;
	}

	/**
	 * Retrieves the first link with the supplied {@code rel} and/or {@code type} value.
	 * <p>
	 * If either parameter is {@code null}, doesn't return matches for that parameter.
	 */
	public Link getLink(String rel, String type) {

		for (Link link : state.links) {
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
		for (Link link : state.links) {
			if (link.matches(relToMatch, typeToMatch)) {
				result.add(link);
			}
		}
		return result;
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
		for (Iterator<Link> iterator = state.links.iterator(); iterator.hasNext();) {
			Link link = iterator.next();
			if (link.matches(relToMatch, typeToMatch)) {
				iterator.remove();
			}
		}
	}

	/**
	 * Remove all links.
	 */
	public void removeLinks() {
		state.links.clear();
	}

	/**
	 * Adds a link pointing to an HTML representation.
	 * 
	 * @param htmlUri
	 *            Link URI.
	 * 
	 * @param lang
	 *            Optional language code.
	 * 
	 * @param title
	 *            Optional title.
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

		state.links.add(link);
	}

	/** Retrieves the media resource edit link. */
	@SuppressWarnings("deprecation")
	public Link getMediaEditLink() {
		Link mediaLink = getLink(Link.Rel.MEDIA_EDIT, null);
		if (mediaLink == null) {
			// Temporary back compat support for old incorrect media link value.
			// to the new value.
			mediaLink = getLink(Link.Rel.MEDIA_EDIT_BACKCOMPAT, null);
		}
		return mediaLink;
	}

	/** Retrieves the media resource resumable upload link. */
	public Link getResumableEditMediaLink() {
		return getLink(Link.Rel.RESUMABLE_EDIT_MEDIA, null);
	}

	/** Retrieves the first HTML link. */
	public Link getHtmlLink() {
		Link htmlLink = getLink(Link.Rel.ALTERNATE, Link.Type.HTML);
		return htmlLink;
	}
}