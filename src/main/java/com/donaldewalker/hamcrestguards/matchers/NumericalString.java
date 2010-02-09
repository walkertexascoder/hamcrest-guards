package com.donaldewalker.hamcrestguards.matchers;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest Matcher that verifies a string is composed 
 * only of numerical characters.
 * 
 * @author Don Walker (don@walkertexascoder.com)
 */
public class NumericalString extends TypeSafeMatcher<String> {
   @Override
   public boolean matchesSafely(String item) {
      return StringUtils.isNumeric(item);
   }

   public void describeTo(Description description) {
      description.appendText("a numerical string");
   }
}