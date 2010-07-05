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

import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.IFeed;

/**
 * A feed that contains a list of {@link FriendEntry}.
 *
 * 
 */
public class FriendFeed extends BaseFeed<FriendEntry> implements IFeed{

  public FriendFeed() {
    super(FriendEntry.class);
  }

  public FriendFeed(BaseFeed<?> base) {
    super(FriendEntry.class, base);
  }

  /** Returns a link to the profile of this user. */
  public Link getUserProfileLink() {
    return getLink(Link.Rel.RELATED, Link.Type.ATOM);
  }

}
