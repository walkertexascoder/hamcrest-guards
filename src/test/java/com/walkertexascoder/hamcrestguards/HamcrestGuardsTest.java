package com.walkertexascoder.hamcrestguards;

import static com.walkertexascoder.hamcrestguards.HamcrestGuards.requireThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author Don Walker (don@walkertexascoder.com)
 */
public class HamcrestGuardsTest {
   @Test
   public void should_return_passed_exception_type_from_else_throw_method() throws Exception {
      Exception e = new IllegalArgumentException();
      assertThat(e, is(sameInstance(e)));
   }
   
   @Test
   public void should_throw_illegal_argument_exception_if_matcher_is_null() throws Exception {
      try {
         requireThat(null, null);
         fail("Expected illegal argument exception");
      }
      catch (IllegalArgumentException e) {
         assertThat(e.getMessage(), is("Expected matcher to be not null but was null."));
      }
   }
   
   @Test
   public void should_throw_illegal_argument_exception_if_provided_exception_type_does_not_have_a_single_string_constructor() throws Exception {
      try {
         requireThat(null, is(notNullValue()), NoSingleStringConstructorException.class);
         fail("Expected illegal argument exception");
      }
      catch (IllegalArgumentException e) {
         assertThat(e.getMessage(), is("Expected exception type to have a single string constructor."));
      }
   }   
   
   @Test
   public void should_not_throw_an_exception_if_requirement_is_met() throws Exception {
      requireThat(null, is(nullValue()));
   }

   @Test(expected = IllegalArgumentException.class)
   public void should_throw_illegal_argument_exception_instance_if_requirement_not_met_and_no_exception_type_is_provided() throws Exception {
      requireThat(null, is(notNullValue()));
   }

   @Test
   public void should_throw_instance_of_provided_exception_type_if_requirement_is_not_met() throws Exception {
      try {
         requireThat(null, is(notNullValue()), IllegalStateException.class);
         fail("Expected illegal state exception");
      }
      catch (IllegalStateException e) {
         assertThat(e.getMessage(), startsWith("Expected value"));
      }
   }
   
   @Test
   public void should_provide_named_field_in_first_part_of_exception_detail_message_() throws Exception {
      try {
         requireThat(null, "alazar", is(notNullValue()));
         fail("Expected illegal argument exception");
      }
      catch (IllegalArgumentException e) {
         assertThat(e.getMessage(), startsWith("Expected alazar"));
      }
   }
   
   @Test
   public void should_use_term__value__if_no_object_name_provided() throws Exception {
      try {
         requireThat(null, is(notNullValue()));
         fail("Expected illegal argument exception");
      }
      catch (IllegalArgumentException e) {
         assertThat(e.getMessage(), startsWith("Expected value"));
      }
   }
   
   //
   // Utility
   //
   
   private static class NoSingleStringConstructorException extends RuntimeException {
      private static final long serialVersionUID = 1L; 
   }
}