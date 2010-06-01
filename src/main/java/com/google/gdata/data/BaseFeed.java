package com.google.gdata.data;

import java.util.LinkedList;
import java.util.List;

import sk.seges.acris.json.client.annotation.Field;

import com.google.gdata.client.Query;
import com.google.gdata.client.Service;
import com.google.gdata.json.JsonObject;

public abstract class BaseFeed<F extends BaseFeed, E extends BaseEntry> extends Source implements IFeed {

	/**
	 * The FeedState class provides a simple structure that encapsulates the attributes of an Atom feed that should be
	 * shared with a shallow copy if the feed is adapted to a more specific BaseFeed subtypes.
	 * <p>
	 * <b>Note: Feed entries are not part of feed shared state, because the entry lists will need to be typed
	 * differently for adapted instances.</b> This means that entries that are created, updated, or deleted in an
	 * adapted feed will not be reflected in the base feed used to construct it. The reverse is also true: changes made
	 * to a base feed will not be reflected in any adapted instances of the feed.
	 * 
	 * @see BaseFeed#BaseFeed(Class, BaseFeed)
	 */
	@JsonObject
	protected static class FeedState {

		/** Service associated with the feed. */
		public Service service;

		/** Specifies whether the feed can be posted to. */
		public boolean canPost = true;

		/** OpenSearch: number of search results (feed entries). */
		public int totalResults = Query.UNDEFINED;

		/** OpenSearch: start index. */
		public int startIndex = Query.UNDEFINED;

		/** OpenSearch: items per page. */
		public int itemsPerPage = Query.UNDEFINED;

		/**
		 * Etag. Etag. See RFC 2616, Section 3.11. If there is no entity tag, this variable is null.
		 */
		public String etag;

		/**
		 * gd:fields. This is the field selection associated with this feed. If fields attribute is present, the feed is
		 * a partial feed.
		 */
		@Field
		public String fields;

		/** gd:kind. This is the kind attribute for this entry. */
		@Field
		public String kind;

		/** Resource version id to use when generating etag. */
		@Field
		public String versionId;
	}

	/**
	 * Basic state for this feed. May be shared across multiple adapted instances associated with the same logical feed.
	 */
	protected FeedState feedState;

	/**
	 * Class used to construct new entry instance, initialized at construction.
	 */
	protected Class<? extends E> entryClass;

	/** Feed entries. */
	protected List<E> entries = new LinkedList<E>();

	/**
	 * Copy constructor that initializes a new BaseFeed instance to have identical contents to another instance, using a
	 * shared reference to the same {@link FeedState}. subclasses of {@code BaseFeed} can use this
	 * constructor to create adaptor instances of an entry that share state with the original.
	 * 
	 * @param entryClass
	 *            Class used to construct new Entry instances for the Feed.
	 */
	protected BaseFeed(Class<? extends E> entryClass) {
		feedState = new FeedState();
		this.entryClass = entryClass;
	}

	/**
	 * Copy constructor that initializes a new BaseFeed instance to have identical contents to another instance, using a
	 * shared reference to the same {@link FeedState}. Subclasses of {@code BaseFeed} can use this
	 * constructor to create adaptor instances of a feed that share state with the original.
	 */
	protected BaseFeed(Class<? extends E> entryClass, BaseFeed<?, ?> sourceFeed) {

		super(sourceFeed);
		feedState = sourceFeed.feedState;
		this.entryClass = entryClass;

	}

	/**
	 * Returns that GData {@link Service} instance associated with this feed.
	 */
	public Service getService() {
		return feedState.service;
	}

	/**
	 * Sets that GData {@link Service} instance associated with this feed.
	 */
	public void setService(Service v) {
		feedState.service = v;

		// Propagate service information to nested entries
		for (E entry : entries) {
			entry.setService(v);
		}
	}

	/**
	 * Gets the property that indicates if it is possible to post new entries to the feed.
	 */
	public boolean getCanPost() {
		return feedState.canPost;
	}

	/**
	 * Sets the property that indicates if it is possible to post new entries to the feed.
	 */
	public void setCanPost(boolean v) {
		feedState.canPost = v;
	}

	/**
	 * Sets the entity version for this feed. This value will be used to compute a weak etag for the feed. If {@code
	 * null} the last modified date is used to generate the etag.
	 */
	public void setVersionId(String v) {
		feedState.versionId = v;
	}

	/**
	 * Returns the entity version for this feed.
	 */
	public String getVersionId() {
		return feedState.versionId;
	}

	/**
	 * Returns the current entity tag value for this feed. A value of {@code null} indicates the value is unknown.
	 */
	public String getEtag() {
		return feedState.etag;
	}

	/**
	 * Sets the current entity tag value for this feed. A value of {@code null} indicates the value is unknown.
	 */
	public void setEtag(String v) {
		feedState.etag = v;
	}

	/**
	 * Returns the current fields selection for this partial feed. A value of {@code null} indicates the feed is not a
	 * partial feed.
	 */
	public String getSelectedFields() {
		return feedState.fields;
	}

	/**
	 * Sets the current fields selection for this partial feed. A value of {@code null} indicates the feed is not a
	 * partial feed.
	 */
	public void setSelectedFields(String fields) {
		feedState.fields = fields;
	}

	/**
	 * Returns the current gd:kind attribute value for this feed. A value of {@code null} indicates the value is
	 * unknown.
	 */
	public String getKind() {
		return feedState.kind;
	}

	/**
	 * Sets the current gd:kind attribute value for this feed. A value of {@code null} indicates the value is unknown.
	 */
	public void setKind(String v) {
		feedState.kind = v;
	}

	/**
	 * Gets the total number of results associated with this feed. The value may be larger than the number of contained
	 * entries for paged feeds. A value of {@link Query#UNDEFINED} indicates the total size is undefined.
	 */
	public int getTotalResults() {
		return feedState.totalResults;
	}

	/**
	 * Sets the total number of results associated with this feed. The value may be larger than the number of contained
	 * entries for paged feeds. A value of {@link Query#UNDEFINED} indicates the total size is undefined.
	 */
	public void setTotalResults(int v) {
		feedState.totalResults = v;
	}

	/**
	 * Gets the starting index of the contained entries for paged feeds. A value of {@link Query#UNDEFINED} indicates
	 * the start index is undefined.
	 */
	public int getStartIndex() {
		return feedState.startIndex;
	}

	/**
	 * Sets the starting index of the contained entries for paged feeds. A value of {@link Query#UNDEFINED} indicates
	 * the start index is undefined.
	 */
	public void setStartIndex(int v) {
		feedState.startIndex = v;
	}

	/**
	 * Gets the number of items that will be returned per page for paged feeds. A value of {@link Query#UNDEFINED}
	 * indicates the page item count is undefined.
	 */
	public int getItemsPerPage() {
		return feedState.itemsPerPage;
	}

	/**
	 * Sets the number of items that will be returned per page for paged feeds. A value of {@link Query#UNDEFINED}
	 * indicates the page item count is undefined.
	 */
	public void setItemsPerPage(int v) {
		feedState.itemsPerPage = v;
	}

	/** Returns the list of entries in this feed */
	public List<E> getEntries() {
		return entries;
	}

	/** Sets the list to use for storing the entry list */
	public void setEntries(List<E> entryList) {
		this.entries = entryList;
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
	
	  /** Returns the entry post link for the feed. */
	  public Link getEntryPostLink() {
	    Link postLink = getLink(Link.Rel.ENTRY_POST, Link.Type.ATOM);
	    return postLink;
	  }

	  /** Returns the self link for the feed. */
	  public Link getSelfLink() {
	    Link postLink = getLink(Link.Rel.SELF, Link.Type.ATOM);
	    return postLink;
	  }

	  /**
	   * Returns the link that provides the URI of next page in a paged feed.
	   *
	   * @return Link that provides the URI of next page in a paged feed or {@code
	   *     null} for none.
	   */
	  public Link getNextLink() {
	    return getLink(Link.Rel.NEXT, Link.Type.ATOM);
	  }

	  /**
	   * Returns the link that provides the URI of previous page in a paged feed.
	   *
	   * @return Link that provides the URI of previous page in a paged feed or
	   *     {@code null} for none.
	   */
	  public Link getPreviousLink() {
	    return getLink(Link.Rel.PREVIOUS, Link.Type.ATOM);
	  }

	  /**
	   * Returns the link that provides the URI that can be used to batch operations
	   * to query, insert, update and delete entries on this feed.
	   *
	   * @return Link that provides the URI that can be used to batch operations to
	   *     query, insert, update and delete entries on this feed or {@code null}
	   *     for none.
	   */
	  public Link getFeedBatchLink() {
	    return getLink(Link.Rel.FEED_BATCH, Link.Type.ATOM);
	  }

}