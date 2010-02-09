package com.walkertexascoder.hamcrestguards.matchers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Don Walker (don@walkertexascoder.com)
 */
public class WhitespaceStringTest {
   @Test
   public void should_not_match_a_string_that_contains_a_non_whitespace_character() throws Exception {
      assertFalse(new WhitespaceString().matches("a\nb "));
   }
   
   @Test
   public void should_match_a_string_that_contains_only_whitespace_characters() throws Exception {
      assertTrue(new WhitespaceString().matches("   \t\n "));
   }
}
