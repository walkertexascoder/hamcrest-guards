package com.donaldewalker.hamcrestguards;

import org.apache.commons.beanutils.ConstructorUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/**
 * <p>Guard enforcing some contraint, in the form of a Hamcrest Matcher, upon a 
 * value. If the constraint fails a customizable runtime exception is thrown that
 * includes a detail message driven by the Hamcrest Matcher's mismatch description.</p>
 * 
 * <p>Some of the default mismatch descriptions provided by Hamcrest Matchers are
 * a little awkward, so Guards also do a little translation to make these a little
 * clearer.</p>
 * 
 * <pre>
 *    new Guard("some value", is(notNullValue())).frisk(null); 
 *    // throw new IllegalArgumentException("Expected some value to not be null but was null");
 * </pre>
 * 
 * <pre>
 *    new Guard("some state", is(nullValue()), IllegalStateException.class).frisk(new Object()); 
 *    // throw new IllegalStateException("Expected some state to be null but was not null");
 * </pre>
 * 
 * @author Don Walker (don@walkertexascoder.com)
 */
public class Guard {
   private String valueName;
   private Matcher<?> matcher;
   private Class<? extends RuntimeException> exceptionType;

   /**
    * @param valueName
    * @param matcher
    */
   public Guard(String valueName, Matcher<?> matcher) {
      this(valueName, matcher, IllegalArgumentException.class);
   }
   
   /**
    * @param valueName
    * @param matcher
    * @param exceptionType
    */
   public Guard(String valueName, Matcher<?> matcher, Class<? extends RuntimeException> exceptionType) {
      if (matcher == null) {
         throw new IllegalArgumentException("Expected matcher to be not null but was null.");
      }
      if (! hasASingleStringConstructor(exceptionType)) {
         throw new IllegalArgumentException("Expected exception type to have a single string constructor.");
      }

      this.valueName = valueName;
      this.matcher = matcher;
      this.exceptionType = exceptionType;
   }
   
   /**
    * Throw a runtime exception if the guard does not approve the value.
    * 
    * @param value
    */
   public void frisk(Object value) {
      if (! getMatcher().matches(value)) {
         throwOut(value);
      }
   }
   
   /**
    * Throw a runtime exception describing why the value was not approved.
    * 
    * @param value
    */
   private void throwOut(Object value) {
      String detail = detailMessage(value, getValueName(), getMatcher());
      
      RuntimeException toThrow = null;
      try {
         toThrow = (RuntimeException) ConstructorUtils.invokeConstructor(getExceptionType(), detail);
      }
      catch (Exception e) {
         throw new IllegalStateException(
               "Failed to build exception of type \"" + exceptionType + "\" " + 
               "with detail message \"" + detail + "\"", e);
      }
      throw toThrow;
   }

   /**
    * @param value
    * @param valueName
    * @param matcher
    * @return exception text describing why value, referred to by value name,
    *         was not matched by matcher
    */
   private String detailMessage(Object value, String valueName, Matcher<?> matcher) {
      if (valueName == null) {
         valueName = "value";
      }

      StringBuilder buf = new StringBuilder();
      buf.append("Expected ")
         .append(valueName)
         .append(" ")
         .append(describe(matcher))
         .append(" but ")
         .append(describeMismatch(matcher, value))
         .append(".");

      return buf.toString();
   }

   /**
    * Hamcrest describes some of its matchers in a way that results in an
    * awkward English sentence. This attempts to fix them.
    * 
    * @param matcher
    * @return description of the matcher applied
    */
   private String describe(Matcher<?> matcher) {
      // TODO: This is going to get very unwiedly and should eventually be 
      // pulled into a ruleset, or at the very least a separate class.
      
      String description = matcher.toString();

      // "is" is a prefix added by the Is matcher. It results in awkward English
      // such as
      // "expected value is an array with size <1> but array size was <0>".
      description = description.replaceFirst("^is ", "");
      description = description.replaceAll("\\(is ", "(");

      // "to be" has proven to apply to all matchers researched thus far and
      // results in more natural English such as "expected value to be an array
      // with size <1> but array size was <0>".
      if (description.startsWith("not")) {
         description = description.replaceFirst("^not", "to not be");
      } else {
         description = "to be " + description;
      }

      return description;
   }

   /**
    * @param matcher
    * @param value
    * @return description of why value was not matched by matcher
    */
   private String describeMismatch(Matcher<?> matcher, Object value) {
      Description desc = new StringDescription();
      matcher.describeMismatch(value, desc);
      return desc.toString();
   }

   /**
    * @param klass
    * @return true iff klass has a constructor accepting a single string
    */
   private boolean hasASingleStringConstructor(Class<?> klass) {
      return ConstructorUtils.getAccessibleConstructor(klass, String.class) != null;
   }

   public String getValueName() {
      return valueName;
   }

   public Matcher<?> getMatcher() {
      return matcher;
   }
   
   public Class<? extends RuntimeException> getExceptionType() {
      return exceptionType;
   }
}