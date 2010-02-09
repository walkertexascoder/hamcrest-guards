package com.walkertexascoder.hamcrestguards.matchers;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest Matcher that verifies a string is composed 
 * only of whitespace characters.
 * 
 * @author Don Walker (don@walkertexascoder.com)
 */
public class WhitespaceString extends TypeSafeMatcher<String> {
   @Override
   public boolean matchesSafely(String item) {
      return StringUtils.isWhitespace(item);
   }

   public void describeTo(Description description) {
      description.appendText("a whitespace string");
   }
}