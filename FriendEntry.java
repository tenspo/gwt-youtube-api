/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.google.gdata.data.youtube;

import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.ExtensionProfile;

import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.IEntry;
import com.google.gdata.data.Link;
import com.google.gdata.data.extensions.Email;

/**
 * An entry that corresponds to a youtube contact/friend as 
 * displayed on the user contacts feed.
 *
 * 
 */
@JsonObject(group = YouTubeNamespace.PREFIX, value = "friend")

public class FriendEntry extends BaseEntry implements IEntry {

  public FriendEntry() {
  }

  public FriendEntry(BaseEntry base) {
    super(base);
  }
  
  /** Gets the youtube username of the contact (for youtube user contacts). */
  public String getUsername() {
    YtUsername username = getExtension(YtUsername.class);
    return username == null ? null : username.getContent();
  }

  /** Sets the youtube username of the contact (for youtube user contacts). */
  public void setUsername(String name) {
    if (name == null) {
      removeExtension(YtUsername.class);
    } else {
      setExtension(new YtUsername(name));
    }
  }

  /** Sets the contact status. */
  public void setStatus(YtStatus.Value status) {
    if (status == null) {
      removeExtension(YtStatus.class);
    } else {
      setExtension(new YtStatus(status));
    }
  }

  /** Gets the contact status. */
  public YtStatus.Value getStatus() {
    YtStatus status = getExtension(YtStatus.class);
    return status == null ? null : status.getStatus();
  }

  /** Gets the url pointing to the user profile entry of this contact. */
  public String getUserProfileLink() {
    Link link = getLink(Link.Rel.RELATED, Link.Type.ATOM);
    return link == null ? null : link.getHref();
  }

  /** Adds a link to the user profile. */
  public void addUserProfileLink(String url) {
    Link link = new Link();
    link.setRel(Link.Rel.RELATED);
    link.setType(Link.Type.ATOM);
    link.setHref(url);
    getLinks().add(link);
  }

  /** Gets the contact e-mail address (for e-mail contacts.) */
  public Email getEmail() {
    return getExtension(Email.class);
  }

  /** Sets the contact e-mail address (for e-mail contacs.) */
  public void setEmail(Email email) {
    if (email == null) {
      removeExtension(Email.class);
    } else {
      setExtension(email);
    }
  }

  /** Declares extensions used in this entry. */
  @Override
  public void declareExtensions(ExtensionProfile extProfile) {
    extProfile.declare(FriendEntry.class, YtStatus.class);
    extProfile.declare(FriendEntry.class, YtUsername.class);
    
    ExtensionDescription emailExtension = Email.getDefaultDescription();
    emailExtension.setRepeatable(false);
    extProfile.declare(FriendEntry.class, emailExtension);

  }
}
