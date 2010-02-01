package com.donaldewalker.hamcrestguards;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/**
 * Guards utilizing Hamcrest Matchers.
 * 
 * @author "Don Walker (don@donaldewalker.com)"
 */
public class HamcrestGuards {
   /**
    * Throw an IllegalArgumentException unless obj is matched by matcher. 
    * 
    * <code>
    *    requireThat(someMethodArg, is(notNullValue()));
    * </code>
    * 
    * @param obj
    * @param matcher
    */
   public static void requireThat(Object obj, Matcher<?> matcher) {
      requireThat(obj, "", matcher);
   }

   /**
    * Throw an IllegalArgumentException unless obj is matched by matcher. 
    * 
    * <code>
    *    requireThat(someMethodArg, "essential value", is(notNullValue()));
    * </code>
    * 
    * @param obj
    * @param objName contextual name of the object used in the exception text if
    *        matching fails
    * @param matcher
    */
   public static void requireThat(Object obj, String objName, Matcher<?> matcher) {
      if (matcher == null) {
         throw new IllegalArgumentException("matcher may not be null");
      }
      if (objName == null) {
         throw new IllegalArgumentException("object name may not be null");
      }

      if (! matcher.matches(obj)) {
         Description failureDescription = new StringDescription();
         // matcher.describeMismatch(obj, failureDescription);
         throw new IllegalArgumentException(buildExceptionText(objName, failureDescription));
      }
   }

   /**
    * @param objName
    * @param failureDescription
    * @return text including the object name, if provided, otherwise just the
    *         failure description
    */
   private static String buildExceptionText(String objName, Description failureDescription) {
      return objName + " " + failureDescription; // Not really heavy enough for
                                                 // StringBuilder
   }
}
