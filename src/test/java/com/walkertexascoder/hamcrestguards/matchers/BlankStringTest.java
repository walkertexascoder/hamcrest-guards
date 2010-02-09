package com.walkertexascoder.hamcrestguards.matchers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Don Walker (don@walkertexascoder.com)
 */
public class BlankStringTest {
   @Test
   public void should_not_match_a_string_that_contains_a_character() throws Exception {
      assertFalse(new BlankString().matches("a"));
   }
   
   @Test
   public void should_not_match_null() throws Exception {
      assertFalse(new BlankString().matches(null));
   }
   
   @Test
   public void should_match_a_string_that_contains_only_whitespace_characters() throws Exception {
      assertTrue(new BlankString().matches(" "));
   }
   
   @Test
   public void should_match_the_empty_string() throws Exception {
      assertTrue(new BlankString().matches(""));
   }
}