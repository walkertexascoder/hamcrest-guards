package com.walkertexascoder.hamcrestguards.matchers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Don Walker (don@walkertexascoder.com)
 */
public class EmptyStringTest {
   @Test
   public void should_not_match_a_string_that_contains_a_character() throws Exception {
      assertFalse(new EmptyString().matches("a"));
   }
   
   @Test
   public void should_not_match_a_string_that_contains_only_whitespace_characters() throws Exception {
      assertFalse(new EmptyString().matches(" "));
   }
   
   @Test
   public void should_match_null() throws Exception {
      assertTrue(new EmptyString().matches(null));
   }
   
   @Test
   public void should_match_the_empty_string() throws Exception {
      assertTrue(new EmptyString().matches(""));
   }
}