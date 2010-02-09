package com.donaldewalker.hamcrestguards.matchers;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest Matcher that verifies a string is blank.
 * 
 * @author Don Walker (don@walkertexascoder.com)
 */
public class BlankString extends TypeSafeMatcher<String> {
   @Override
   public boolean matchesSafely(String item) {
      return StringUtils.isBlank(item);
   }

   public void describeTo(Description description) {
      description.appendText("a blank string");
   }
}