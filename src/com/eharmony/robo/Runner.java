package com.eharmony.robo;

import com.eharmony.robo.tests.rq.TestRQ;
import com.eharmony.test.TestDashboard;
import com.eharmony.test.TestSignUp;

import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;
import junit.framework.TestSuite;

public class Runner extends InstrumentationTestRunner {
private InstrumentationTestSuite mainSuite = new InstrumentationTestSuite(this);;
    @Override
    public TestSuite getAllTests(){
    	InstrumentationTestSuite suite = new InstrumentationTestSuite(this);
        suite.addTestSuite(TestSignUp.class);
        suite.addTestSuite(TestRQ.class);
        mainSuite.addTest(suite);
        return mainSuite;
    }
    
    public TestSuite existingUserTests(){
    	InstrumentationTestSuite suite = new InstrumentationTestSuite(this);
        suite.addTestSuite(TestDashboard.class);
        //suite.addTestSuite(TestSubscription.class);
        mainSuite.addTest(suite);
        return mainSuite;
    }

    @Override
    public ClassLoader getLoader() {
        return Runner.class.getClassLoader();
    }

}