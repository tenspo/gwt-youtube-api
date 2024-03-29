package com.google.gdata.util.common.base;

public abstract class CharEscaper implements Escaper {
	/**
	   * Returns the escaped form of a given literal string.
	   *
	   * @param string the literal string to be escaped
	   * @return the escaped form of {@code string}
	   * @throws NullPointerException if {@code string} is null
	   */
	  public String escape(String string) {
	    // Inlineable fast-path loop which hands off to escapeSlow() only if needed
	    int length = string.length();
	    for (int index = 0; index < length; index++) {
	      if (escape(string.charAt(index)) != null) {
	        return escapeSlow(string, index);
	      }
	    }
	    return string;
	  }

	  /**
	   * Returns the escaped form of a given literal string, starting at the given
	   * index.  This method is called by the {@link #escape(String)} method when it
	   * discovers that escaping is required.  It is protected to allow subclasses
	   * to override the fastpath escaping function to inline their escaping test.
	   * See {@link CharEscaperBuilder} for an example usage.
	   *
	   * @param s the literal string to be escaped
	   * @param index the index to start escaping from
	   * @return the escaped form of {@code string}
	   * @throws NullPointerException if {@code string} is null
	   */
	  protected String escapeSlow(String s, int index) {
	    int slen = s.length();

	    // Get a destination buffer and setup some loop variables.
	    char[] dest = DEST_TL.get();
	    int destSize = dest.length;
	    int destIndex = 0;
	    int lastEscape = 0;

	    // Loop through the rest of the string, replacing when needed into the
	    // destination buffer, which gets grown as needed as well.
	    for (; index < slen; index++) {

	      // Get a replacement for the current character.
	      char[] r = escape(s.charAt(index));

	      // If no replacement is needed, just continue.
	      if (r == null) continue;

	      int rlen = r.length;
	      int charsSkipped = index - lastEscape;  // Characters we skipped over.

	      // This is the size needed to add the replacement, not the full
	      // size needed by the string.  We only regrow when we absolutely must.
	      int sizeNeeded = destIndex + charsSkipped + rlen;
	      if (destSize < sizeNeeded) {
	        destSize = sizeNeeded + (slen - index) + DEST_PAD;
	        dest = growBuffer(dest, destIndex, destSize);
	      }

	      // If we have skipped any characters, we need to copy them now.
	      if (charsSkipped > 0) {
	        s.getChars(lastEscape, index, dest, destIndex);
	        destIndex += charsSkipped;
	      }

	      // Copy the replacement string into the dest buffer as needed.
	      if (rlen > 0) {
	        System.arraycopy(r, 0, dest, destIndex, rlen);
	        destIndex += rlen;
	      }
	      lastEscape = index + 1;
	    }

	    // Copy leftover characters if there are any.
	    int charsLeft = slen - lastEscape;
	    if (charsLeft > 0) {
	      int sizeNeeded = destIndex + charsLeft;
	      if (destSize < sizeNeeded) {

	        // Regrow and copy, expensive!  No padding as this is the final copy.
	        dest = growBuffer(dest, destIndex, sizeNeeded);
	      }
	      s.getChars(lastEscape, slen, dest, destIndex);
	      destIndex = sizeNeeded;
	    }
	    return new String(dest, 0, destIndex);
	  }


	  /**
	   * Returns the escaped form of the given character, or {@code null} if this
	   * character does not need to be escaped. If an empty array is returned, this
	   * effectively strips the input character from the resulting text.
	   *
	   * <p>If the character does not need to be escaped, this method should return
	   * {@code null}, rather than a one-character array containing the character
	   * itself. This enables the escaping algorithm to perform more efficiently.
	   *
	   * <p>An escaper is expected to be able to deal with any {@code char} value,
	   * so this method should not throw any exceptions.
	   *
	   * @param c the character to escape if necessary
	   * @return the replacement characters, or {@code null} if no escaping was
	   *     needed
	   */
	  protected abstract char[] escape(char c);

	  /**
	   * Helper method to grow the character buffer as needed, this only happens
	   * once in a while so it's ok if it's in a method call.  If the index passed
	   * in is 0 then no copying will be done.
	   */
	  private static final char[] growBuffer(char[] dest, int index, int size) {
	    char[] copy = new char[size];
	    if (index > 0) {
	      System.arraycopy(dest, 0, copy, 0, index);
	    }
	    return copy;
	  }

	  /**
	   * The amount of padding to use when growing the escape buffer.
	   */
	  private static final int DEST_PAD = 32;

	  public static class CharWrapper {
		  
		  private char[] chars = new char[1024];
		  
		  public char[] get() {
			  return chars;
		  }
		  
		  public void set(char[] chars) {
			  this.chars = chars;
		  }
	  }
	  
	  private static final CharWrapper DEST_TL = new CharWrapper();

}
