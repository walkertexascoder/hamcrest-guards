package com.walkertexascoder.hamcrestguards.matchers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Don Walker (don@walkertexascoder.com)
 */
public class AlphabeticalStringTest {
   @Test
   public void should_not_match_a_string_that_contains_a_non_alphabetical_character() throws Exception {
      assertFalse(new AlphabeticalString().matches("a1c"));
   }
   
   @Test
   public void should_match_a_string_that_contains_only_alphabetical_characters() throws Exception {
      assertTrue(new AlphabeticalString().matches("abc"));
   }
   
   @Test
   public void should_match_the_empty_string() throws Exception {
      assertTrue(new AlphabeticalString().matches(""));
   }
}
