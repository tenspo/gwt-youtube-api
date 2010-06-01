package com.google.gdata.data;


public class JSON {

	protected static boolean eq(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	/**
	 * @param o
	 *            given object
	 * @return true if the given object is not null and is the same concrete class as this one
	 */
	protected boolean sameClassAs(Object o) {
		return o != null && getClass().equals(o.getClass());
	}
}
