package com.donaldewalker.hamcrestguards.matchers;

import java.util.regex.Pattern;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest matcher that verifies a string matches a given regular expression.
 * Supports all regular expression supported by the Java core library.
 * 
 * @author "Don Walker (don@donaldewalker.com)"
 */
public class MatchedBy extends TypeSafeMatcher<String> {
   private Pattern pattern;

   public MatchedBy(String regex) {
      setPattern(Pattern.compile(regex));
   }

   public static MatchedBy matchedBy(String regex) {
      return new MatchedBy(regex);
   }

   @Override
   public boolean matchesSafely(String item) {
      return getPattern().matcher(item).matches();
   }

   public void describeTo(Description description) {
      description.appendText("string matching ").appendValue(pattern);
   }

   private void setPattern(Pattern pattern) {
      this.pattern = pattern;
   }

   public Pattern getPattern() {
      return pattern;
   }
}
