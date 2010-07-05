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


package com.google.gdata.data.extensions;

import sk.seges.acris.json.client.annotation.Field;
import sk.seges.acris.json.client.annotation.JsonObject;
import sk.seges.acris.json.client.extension.Extension;
import sk.seges.acris.json.client.extension.ExtensionDescription;
import sk.seges.acris.json.client.extension.ExtensionPoint;

import com.google.gdata.util.Namespaces;

/**
 * GData schema extension describing an email address.
 *
 * 
 */
public class Email extends ExtensionPoint implements Extension {

  /** The email type. */
  @JsonObject													//PLEASE REVIEW THIS 'Rel' BLOCK
  public static final class Rel {
    public static final String GENERAL = null;
    public static final String HOME = Namespaces.gPrefix + "home";
    public static final String WORK = Namespaces.gPrefix + "work";
    public static final String OTHER = Namespaces.gPrefix + "other";
  }

  /** The email type. */
  @Field
  protected String rel;
  public String getRel() { return rel; }
  public void setRel(String v) { rel = v; }

  /** Label. */
  @Field
  protected String label;
  public String getLabel() { return label; }
  public void setLabel(String v) { label = v; }

  /** Email address. */
  @Field
  protected String address;
  public String getAddress() { return address; }
  public void setAddress(String v) { address = v; }

  /** Email quota. */
  @Field
  protected String quota;
  public String getQuota() { return quota; }
  public void setQuota(String v) { quota = v; }

  /** Whether this is the primary email address */
  @Field
  protected boolean primary;
  public boolean getPrimary() { return primary; }
  public void setPrimary(boolean p) { primary = p; }

  /** Display name of the email address */
  @Field
  protected String displayName;
  public String getDisplayName() { return displayName; }
  public void setDisplayName(String n) { displayName = n; }

  /** Returns the suggested extension description. */
  public static ExtensionDescription getDefaultDescription() {
    ExtensionDescription desc = new ExtensionDescription();
    desc.setExtensionClass(Email.class);
	desc.setPointName(Namespaces.gAlias + "$email");
    desc.setRepeatable(true);
    return desc;
  }
}
