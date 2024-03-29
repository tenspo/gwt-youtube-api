package com.google.gdata.data.media.mediarss;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sk.seges.acris.json.client.extension.ExtensionDescription;
import sk.seges.acris.json.client.extension.ExtensionPoint;
import sk.seges.acris.json.client.extension.ExtensionProfile;

public class MediaGroup extends ExtensionPoint {
	/** Describes the tag to an {@link com.google.gdata.data.ExtensionProfile}. */
	public static ExtensionDescription getDefaultDescription() {
		return ExtensionDescription.getDefaultDescription(MediaGroup.class);
	}

	@Override
	public void declareExtensions(ExtensionProfile extProfile) {
		super.declareExtensions(extProfile);
		MediaRssNamespace.declareAll(extProfile);
	}

	public List<MediaContent> getContents() {
		return getRepeatingExtension(MediaContent.class);
	}

	public void clearContents() {
		getContents().clear();
	}

	public void addContent(MediaContent content) {
		addRepeatingExtension(content);
	}

	public List<MediaCategory> getCategories() {
		return getRepeatingExtension(MediaCategory.class);
	}

	/**
	 * Returns all the found categories of the given scheme. If the given scheme parameter is null it returns all the
	 * categories that do not have a scheme set.
	 * 
	 * @param scheme
	 *            scheme to search for, can be null.
	 * @return the found categories that are of the given scheme, it may be an empty set if no such categories were
	 *         specified, but never null.
	 */
	public Set<MediaCategory> getCategoriesWithScheme(String scheme) {
		Set<MediaCategory> result = new HashSet<MediaCategory>();
		for (MediaCategory category : getCategories()) {
			if ((scheme == null && category.getScheme() == null)
					|| (scheme != null && scheme.equals(category.getScheme()))) {
				result.add(category);
			}
		}

		return Collections.unmodifiableSet(result);
	}

	public void clearCategories() {
		getCategories().clear();
	}

	public void addCategory(MediaCategory category) {
		getCategories().add(category);
	}

	public MediaCopyright getCopyright() {
		return getExtension(MediaCopyright.class);
	}

	public void setCopyright(MediaCopyright copyright) {
		if (copyright == null) {
			removeExtension(MediaCopyright.class);
		} else {
			setExtension(copyright);
		}
	}

	public List<MediaCredit> getCredits() {
		return getRepeatingExtension(MediaCredit.class);
	}

	public void clearCredits() {
		getCredits().clear();
	}

	public void addCredit(MediaCredit credit) {
		getCredits().add(credit);
	}

	public MediaHash getHash() {
		return getExtension(MediaHash.class);
	}

	public void setHash(MediaHash hash) {
		if (hash == null) {
			removeExtension(MediaHash.class);
		} else {
			setExtension(hash);
		}
	}

	public MediaKeywords getKeywords() {
		return getExtension(MediaKeywords.class);
	}

	public void setKeywords(MediaKeywords keywords) {
		if (keywords == null) {
			removeExtension(MediaKeywords.class);
		} else {
			setExtension(keywords);
		}
	}

	public MediaPlayer getPlayer() {
		return getExtension(MediaPlayer.class);
	}

	public void setPlayer(MediaPlayer player) {
		if (player == null) {
			removeExtension(MediaPlayer.class);
		} else {
			setExtension(player);
		}
	}

	public List<MediaRating> getRatings() {
		return getRepeatingExtension(MediaRating.class);
	}

	public void clearRatings() {
		getRatings().clear();
	}

	public void addRating(MediaRating rating) {
		getRatings().add(rating);
	}

	public List<MediaThumbnail> getThumbnails() {
		return getRepeatingExtension(MediaThumbnail.class);
	}

	public void clearThumbnails() {
		getThumbnails().clear();
	}

	public void addThumbnail(MediaThumbnail thumbnail) {
		getThumbnails().add(thumbnail);
	}

	public List<MediaText> getTexts() {
		return getRepeatingExtension(MediaText.class);
	}

	public void clearTexts() {
		getTexts().clear();
	}

	public void addText(MediaText text) {
		getTexts().add(text);
	}

	public List<MediaRestriction> getRestrictions() {
		return getRepeatingExtension(MediaRestriction.class);
	}

	public void clearRestrictions() {
		getRestrictions().clear();
	}

	public void addRestriction(MediaRestriction restriction) {
		getRestrictions().add(restriction);
	}

	public void setTitle(MediaTitle title) {
		if (title == null) {
			removeExtension(MediaTitle.class);
		} else {
			setExtension(title);
		}
	}

	public MediaTitle getTitle() {
		return getExtension(MediaTitle.class);
	}

	public void setDescription(MediaDescription description) {
		if (description == null) {
			removeExtension(MediaDescription.class);
		} else {
			setExtension(description);
		}
	}

	public MediaDescription getDescription() {
		return getExtension(MediaDescription.class);
	}

}
