package com.donaldewalker.hamcrestguards.matchers;

import org.hamcrest.Matcher;

/**
 * Sweet, syntactic sugar for using these matchers in a Guard.
 * 
 * @author Don Walker (don@walkertexascoder.com)
 */
public class HamcrestGuardMatchers {
   public static Matcher<?> alphabeticalString() {
      return new AlphabeticalString();
   }
   
   public static Matcher<?> blankString() {
      return new BlankString();
   }
   
   public static Matcher<?> emptyString() {
      return new EmptyString();
   }
   
   public static Matcher<?> matchedBy(String regex) {
      return new MatchedBy(regex);
   }
   
   public static Matcher<?> numericalString() {
      return new NumericalString();
   }
   
   public static Matcher<?> whitespaceString() {
      return new WhitespaceString();
   }
}
