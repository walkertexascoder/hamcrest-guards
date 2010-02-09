package com.donaldewalker.hamcrestguards;

import static com.donaldewalker.hamcrestguards.HamcrestGuards.requireThat;
import static com.donaldewalker.hamcrestguards.matchers.HamcrestGuardMatchers.alphabeticalString;
import static com.donaldewalker.hamcrestguards.matchers.HamcrestGuardMatchers.blankString;
import static com.donaldewalker.hamcrestguards.matchers.HamcrestGuardMatchers.emptyString;
import static com.donaldewalker.hamcrestguards.matchers.HamcrestGuardMatchers.matchedBy;
import static com.donaldewalker.hamcrestguards.matchers.HamcrestGuardMatchers.numericalString;
import static com.donaldewalker.hamcrestguards.matchers.HamcrestGuardMatchers.whitespaceString;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hamcrest.Matcher;
import org.junit.Test;

/**
 * Verify messages produced for clarity of expression.
 * 
 * @author Don Walker (don@walkertexascoder.com)
 */
public class HamcrestGuardsFailureDetailMessageTest {
   @Test
   public void should_describe_alphabetical_string_failure() throws Exception {
      assertEquals(
         describeFalsityThat("1", is(alphabeticalString())),
         "Expected value to be an alphabetical string but was \"1\".");
   }
   
   @Test
   public void should_describe_blank_string_failure() throws Exception {
      assertEquals(
         describeFalsityThat("1", is(blankString())),
         "Expected value to be a blank string but was \"1\".");
   }
   
   @Test
   public void should_describe_empty_string_failure() throws Exception {
      assertEquals(
         describeFalsityThat("1", is(emptyString())),
         "Expected value to be an empty string but was \"1\".");      
   }
   
   @Test
   public void should_describe_numerical_string_failure() throws Exception {
      assertEquals(
         describeFalsityThat("a", is(numericalString())),
         "Expected value to be a numerical string but was \"a\".");    
   }
   
   @Test
   public void should_describe_whitespace_string_failure() throws Exception {
      assertEquals(
         describeFalsityThat("a", is(whitespaceString())),
         "Expected value to be a whitespace string but was \"a\".");  
   }
   
   @Test
   public void should_describe_not_null_value_failure() throws Exception {
      assertEquals(
         describeFalsityThat(null, is(notNullValue())),
         "Expected value to not be null but was null."); 
   }

   @Test
   public void should_describe_not_empty_array_failure() throws Exception {
      assertEquals(
         describeFalsityThat(new String[0], is(not(emptyArray()))),
         "Expected value to not be an empty array but was []."); 
   }
   
   @Test
   public void should_describe_has_size_failure() throws Exception {
      assertEquals(
         describeFalsityThat(newArrayList(), hasSize(1)),
         "Expected value to be a collection with size <1> but collection size was <0>.");       
   }
   
   @Test
   public void should_describe_failure_when_has_size_is_provided_a_null_value() throws Exception {
      assertEquals(
         describeFalsityThat(null, hasSize(1)),
         "Expected value to be a collection with size <1> but was null.");   
   }
      
   @Test
   public void should_describe_a_matched_by_failure() throws Exception {
      assertEquals(
         describeFalsityThat("A", is(matchedBy("\\d"))),
         "Expected value to be a string matching <\\d> but was \"A\".");   
   }
   
   @Test
   public void should_describe_an_empty_array_failure() throws Exception {
      assertEquals(
         describeFalsityThat(new String[] { "A" }, is(emptyArray())),
         "Expected value to be an empty array but was [\"A\"].");   
   }
   
   //
   // Messages could use some improvement.
   //

   @Test
   public void should_describe_an_array_with_size_failure() throws Exception {
      assertEquals(
         describeFalsityThat(new String[0], is(arrayWithSize(1))),
         // TODO: "Expected value to be an array with size <1> but size was <0>"
         "Expected value to be an array with size <1> but array size was <0>.");       
   }
      
   @Test
   public void should_describe_a_contains_in_any_order_failure() throws Exception {
      assertEquals(
         describeFalsityThat(newArrayList("C", "A", "B"), containsInAnyOrder("A", "B", "C", "D")),
         // TODO: "Expected value to contain [\"A\", \"B\", \"C\", \"D\"] in any order but \"D\" was not found in [\"C\", \"A\", \"B\"]"
         "Expected value to be iterable over [\"A\", \"B\", \"C\", \"D\"] in any order but No item matches: \"D\" in [\"C\", \"A\", \"B\"].");
   }
   
   @Test
   public void should_describe_a_has_item_in_array_failure() throws Exception {
      assertEquals(
         describeFalsityThat(new String[] { "a", "b", "c" }, hasItemInArray("z")),
         // TODO: "Expected value to be an array containing \"z\" but was [\"a\", \"b\", \"c\"]"
         "Expected value to be an array containing \"z\" but was a java.util.Arrays$ArrayList (<[a, b, c]>).");    
   }
 
   //
   // Utility
   //
 
   private String describeFalsityThat(Object value, Matcher<?> matcher) {
      try {
         requireThat(value, matcher);
      }
      catch (IllegalArgumentException e) {
         return e.getMessage();
      }
      fail("Expected an illegal argument exception");
      return ""; // Compiler isn't smart enough to know this will never occur.
   }
}