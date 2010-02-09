package com.walkertexascoder.hamcrestguards;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.walkertexascoder.hamcrestguards.matchers.AlphabeticalStringTest;
import com.walkertexascoder.hamcrestguards.matchers.BlankStringTest;
import com.walkertexascoder.hamcrestguards.matchers.EmptyStringTest;
import com.walkertexascoder.hamcrestguards.matchers.MatchedByTest;
import com.walkertexascoder.hamcrestguards.matchers.NumericalStringTest;
import com.walkertexascoder.hamcrestguards.matchers.WhitespaceStringTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   HamcrestGuardsTest.class,
   HamcrestGuardsFailureDetailMessageTest.class,
   AlphabeticalStringTest.class,
   BlankStringTest.class,
   EmptyStringTest.class,
   MatchedByTest.class,
   NumericalStringTest.class,
   WhitespaceStringTest.class
})
public class AllTests
{
}