package com.donaldewalker.hamcrestguards;

import org.hamcrest.Matcher;

/**
 * Guards utilizing Hamcrest Matchers.
 * 
 * <pre>
 *    requireThat("some value", is(notNullValue()));
 *    // throw new IllegalArgumentException("Expected some value to not be null but was null");
 * </pre>
 * 
 * <pre>
 *    requireThat("some state", is(nullValue()), elseThrow(IllegalStateException.class));
 *    // throw new IllegalStateException("Expected some state to be null but was not null");
 * </pre>
 * 
 * @author Don Walker (don@walkertexascoder.com)
 */
public class HamcrestGuards {
   private static final String DEFAULT_VALUE_NAME = "value";
   
   /**
    * Throw an IllegalArgumentException unless the value satisfies the matcher. 
    * 
    * <pre>
    *    requireThat(someMethodParameter, is(notNullValue()));
    * </pre>
    * 
    * @param value
    * @param matcher
    */
   public static void requireThat(Object value, Matcher<?> matcher) {
      requireThat(value, DEFAULT_VALUE_NAME, matcher);
   }

   /**
    * Throw a runtime exception of exception type unless value satisfies the matcher.
    * 
    * <pre>
    *    requireThat(someState, is(notNullValue()), IllegalStateException.class);
    * </pre>
    * 
    * @param value
    * @param matcher
    * @param exceptionType
    */
   public static void requireThat(Object value, Matcher<?> matcher, Class<? extends RuntimeException> exceptionType) {
      requireThat(value, DEFAULT_VALUE_NAME, matcher, exceptionType);
   }
   
   /**
    * Throw an IllegalArgumentException unless the value satisfies the matcher.
    * 
    * <pre>
    *    requireThat(someMethodParameter, "essential value", is(notNullValue()));
    * </pre>
    * 
    * @param value
    * @param valueName contextual name of the object used in the exception text if
    *        matching fails
    * @param matcher
    */
   public static void requireThat(Object value, String valueName, Matcher<?> matcher) {
      new Guard(valueName, matcher).frisk(value);
   }

   /**
    * Throw a runtime exception of exception type unless the value satisfies the matcher.
    * 
    * <pre>
    *    requireThat(someState, "essential state", is(notNullValue()), IllegalStateException.class);
    * </pre>
    * 
    * @param value
    * @param valueName
    * @param matcher
    * @param exceptionType
    */
   public static void requireThat(Object value, String valueName, Matcher<?> matcher, Class<? extends RuntimeException> exceptionType) {
      new Guard(valueName, matcher, exceptionType).frisk(value);
   }
   
   /**
    * Syntactic sugar for scenarios where an explicit runtime exception type is specified.
    * 
    * <pre>
    *    requireThat(someState, is(nullValue()), elseThrow(IllegalStateException.class));
    * </pre>
    * 
    * @param exceptionType
    * @return exceptionType
    */
   public static Class<? extends RuntimeException> elseThrow(Class<? extends RuntimeException> exceptionType) {
      return exceptionType;
   }
}   