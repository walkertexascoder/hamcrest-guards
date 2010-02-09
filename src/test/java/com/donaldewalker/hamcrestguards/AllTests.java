package com.donaldewalker.hamcrestguards;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.donaldewalker.hamcrestguards.matchers.AlphabeticalStringTest;
import com.donaldewalker.hamcrestguards.matchers.BlankStringTest;
import com.donaldewalker.hamcrestguards.matchers.EmptyStringTest;
import com.donaldewalker.hamcrestguards.matchers.MatchedByTest;
import com.donaldewalker.hamcrestguards.matchers.NumericalStringTest;
import com.donaldewalker.hamcrestguards.matchers.WhitespaceStringTest;

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