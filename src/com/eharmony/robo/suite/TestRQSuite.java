package com.eharmony.robo.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.eharmony.robo.test.TestDashboard;
import com.eharmony.robo.test.TestRQ;

/**
* Example for a Junit4 test suite
*
* @author Daniel Thommes
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestRQ.class, TestDashboard.class })
public class TestRQSuite {

}
