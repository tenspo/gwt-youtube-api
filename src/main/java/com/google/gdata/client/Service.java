package com.google.gdata.client;

import com.google.gdata.util.Version;
import com.google.gdata.util.VersionRegistry;

public class Service {
	/**
	 * The Versions class defines {@link Version} constants representing the set of active versions of the GData core
	 * protocol and common data model classes.
	 */
	public static class Versions {

		/**
		 * Version 1. GData core protocol released in May 2006 and is still in use by version 1 of some GData services.
		 */
		public static final Version V1 = new Version(Service.class, 1, 0);

		/**
		 * Version 2. GData core protocol release that brings full alignment with the now standard Atom Publishing
		 * Protocol specification and migrates to OpenSearch 1.1.
		 */
		public static final Version V2 = new Version(Service.class, 2, 0);

		/**
		 * Version {@code 2.1}. Support new gd:kind attribute on feeds and entries.
		 */
		public static final Version V2_1 = new Version(Service.class, 2, 1);

		/**
		 * Version {@code 2.2}. Unreleased next minor version of the GData protocol.
		 */
		public static final Version V2_2 = new Version(Service.class, 2, 2);

		/**
		 * Version 3. Unreleased next major version of the GData protocol that will default to structured error
		 * messages.
		 */
		public static final Version V3 = new Version(Service.class, 3, 0);

		private Versions() {
		}
	}

	/**
	 * Returns the current {@link Version} of the GData core protocol.
	 * 
	 * @return protocol version.
	 */
	public static Version getVersion() {
		return VersionRegistry.get().getVersion(Service.class);
	}

}
