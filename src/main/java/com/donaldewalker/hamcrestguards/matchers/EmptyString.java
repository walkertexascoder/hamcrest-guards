package com.donaldewalker.hamcrestguards.matchers;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * Hamcrest Matcher that verifies a string is empty.
 * 
 * @author Don Walker (don@walkertexascoder.com)
 */
public class EmptyString extends BaseMatcher<String> {
   public boolean matches(Object item) {
      return (item == null || item instanceof String) && StringUtils.isEmpty((String) item);
   }

   public void describeTo(Description description) {
      description.appendText("an empty string");
   }
}