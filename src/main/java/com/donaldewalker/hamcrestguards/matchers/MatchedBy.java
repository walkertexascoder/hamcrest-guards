package com.swacorp.bservices.mona;

import static com.swacorp.bservices.mona.CollectionUtil.append;

import java.util.regex.Pattern;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class MatchedBy extends TypeSafeMatcher<String>
{
   private Pattern pattern;
   
   public MatchedBy(String regex)
   {
      setPattern(Pattern.compile(regex));
   }
   
   public static MatchedBy matchedBy(String regex)
   {
      return new MatchedBy(regex);
   }
   
   @Override
   protected boolean matchesSafely(String item)
   {
      return getPattern().matcher(item).matches();
   }

   public void describeTo(Description description)
   {
      description.appendText("string matching ").appendValue(pattern);
   }
   
   @Override
   protected void describeMismatchSafely(String item, Description mismatchDescription)
   {
      mismatchDescription
         .appendValue(item)
         .appendText(" does not match ")
         .appendValue(getPattern());
   }
   
   private void setPattern(Pattern pattern)
   {
      this.pattern = pattern;
   }

   public Pattern getPattern()
   {
      return pattern;
   }
}
