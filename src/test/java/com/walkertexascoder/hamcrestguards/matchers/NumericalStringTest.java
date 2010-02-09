package com.walkertexascoder.hamcrestguards.matchers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Don Walker (don@walkertexascoder.com)
 */
public class NumericalStringTest {
   @Test
   public void should_not_match_a_string_that_contains_a_non_numerical_character() throws Exception {
      assertFalse(new NumericalString().matches("1a3"));
   }
   
   @Test
   public void should_match_a_string_that_contains_only_numerical_characters() throws Exception {
      assertTrue(new NumericalString().matches("123"));
   }
   
   @Test
   public void should_match_the_empty_string() throws Exception {
      assertTrue(new NumericalString().matches(""));
   }
}
