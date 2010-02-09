package com.donaldewalker.hamcrestguards.matchers;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest Matcher that verifies a string is composed 
 * only of alphabetical characters.
 * 
 * @author Don Walker (don@walkertexascoder.com)
 */
public class AlphabeticalString extends TypeSafeMatcher<String> {
   @Override
   public boolean matchesSafely(String item) {
      return StringUtils.isAlpha(item);
   }

   public void describeTo(Description description) {
      description.appendText("an alphabetical string");
   }
}