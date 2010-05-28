package com.google.gdata.data.media;

import com.google.gdata.client.Service;
import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.Link;
import com.google.gdata.data.MediaContent;
import com.google.gdata.util.ContentType;

public abstract class MediaEntry<E extends BaseEntry<E>> extends BaseEntry<E> implements IMediaEntry {
	/**
	 * Constructs a new BaseEntry instance.
	 */
	protected MediaEntry() {
		super();
	}

	/**
	 * Copy constructor that initializes a new BaseEntry instance to have identical contents to another instance, using
	 * a shared reference to the same entry state. {@link com.google.gdata.data.Kind.Adaptor} subclasses of {@code
	 * BaseEntry} can use this constructor to create adaptor instances of an entry that share state with the original.
	 */
	protected MediaEntry(BaseEntry<?> sourceEntry) {
		super(sourceEntry);
	}

	@Override
	public void setService(Service v) {

		if (!(v instanceof MediaService)) {
			throw new IllegalArgumentException("Service does not support media");
		}
		super.setService(v);
	}

	public void setMediaSource(MediaSource mediaSource) {
		MediaContent content;
		if (state.content == null) {
			content = new MediaContent();
			state.content = content;
		} else if (state.content instanceof MediaContent) {
			content = (MediaContent) state.content;
		} else {
			throw new IllegalArgumentException("Cannot set media source on entry " + "with existing non-MediaContent: "
					+ state.content);
		}
		content.setMediaSource(mediaSource);
		content.setMimeType(new ContentType(mediaSource.getType(), mediaSource.getSubType()));
	}

	public MediaSource getMediaSource() {
		if (state.content instanceof MediaContent) {
			MediaContent mediaContent = (MediaContent) state.content;
			if (mediaContent != null) {
				return mediaContent.getMediaSource();
			}
		}
		return null;
	}

	/** Retrieves the media resource edit link. */
	@Override
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

}