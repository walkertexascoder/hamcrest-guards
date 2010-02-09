package com.donaldewalker.hamcrestguards.matchers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

/**
 * @author Don Walker (don@walkertexascoder.com)
 */
public class MatchedByTest {
   @Test
   public void should_throw_an_illegal_argument_exception_if_initialized_with_a_null_regular_expression() throws Exception {
      try {
         new MatchedBy(null);
         fail("expected an illegal argument exception");
      }
      catch (IllegalArgumentException e) {
         assertThat(e.getMessage(), is("Expected regular expression to not be null but was null."));
      }
   }
   
   @Test
   public void should_not_match_a_string_that_does_not_match_the_initializing_regular_expression() throws Exception {
      assertThat(new MatchedBy("\\w{2}").matches("A"), is(false));
   }
   
   @Test
   public void should_match_a_string_that_does_match_the_initializing_regular_expression() throws Exception {
      assertThat(new MatchedBy("\\w{2}").matches("AA"), is(true));
   }
   
   @Test
   public void should_provide_regular_expression_being_matched_in_the_description() throws Exception {
      Description description = new StringDescription();
      new MatchedBy("abc").describeTo(description);
      assertThat(description.toString(), is("a string matching <abc>"));
   }
}
