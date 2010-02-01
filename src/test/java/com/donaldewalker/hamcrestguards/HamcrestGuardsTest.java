package com.donaldewalker.hamcrestguards;

import static com.donaldewalker.hamcrestguards.HamcrestGuards.requireThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author "Don Walker (don@donaldewalker.com)"
 */
public class HamcrestGuardsTest {
   @Test
   public void should_not_throw_an_exception_if_requirement_is_met() throws Exception {
      requireThat(null, is(nullValue()));
   }

   @Test(expected = IllegalArgumentException.class)
   public void should_throw_illegal_argument_exception_if_requirement_not_met() throws Exception {
      requireThat(null, is(notNullValue()));
   }

   @Test
   public void should_provide_named_field_in_first_part_of_exception_text() throws Exception {
      try {
         requireThat(null, "alazar", is(notNullValue()));
         fail("Expected illegal argument exception");
      }
      catch (IllegalArgumentException e) {
         assertThat(e.getMessage(), startsWith("alazar"));
      }
   }
   
   @Test
   public void should_use_term__value__if_no_object_name_provided() throws Exception {
      try {
         requireThat(null, is(notNullValue()));
         fail("Expected illegal argument exception");
      }
      catch (IllegalArgumentException e) {
         assertThat(e.getMessage(), startsWith("value"));
      }
   }

   @Test
   public void should_throw_illegal_argument_exception_if_matcher_is_null() throws Exception {
      try {
         requireThat(null, null);
         fail("Expected illegal argument exception");
      }
      catch (IllegalArgumentException e) {
         assertThat(e.getMessage(), is("matcher may not be null"));
      }
   }
}