package com.eharmony.robo.test;

import android.test.ActivityInstrumentationTestCase2;

import com.eharmony.ui.SplashActivity;
import com.robotium.solo.Solo;


public class TestLogIn extends
		ActivityInstrumentationTestCase2<SplashActivity> {
	private static boolean toggle = false;
	private Solo solo;

	

	public TestLogIn() {
		super(SplashActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();
	}

	public void testRun() {
		// Wait for activity: 'com.eharmony.ui.SplashActivity'
		solo.waitForActivity(SplashActivity.class, 2000);
		// Wait for activity:
		// 'com.eharmony.ui.registration.initial.RegistrationChoiceActivity'
		assertTrue(
				"com.eharmony.ui.registration.initial.RegistrationChoiceActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.registration.initial.RegistrationChoiceActivity.class));
		
		login();
	}

    private void login(){
        // Wait for activity: 'com.eharmony.ui.LoginActivity'
        assertTrue("com.eharmony.ui.LoginActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.LoginActivity.class));
        resetPassword();
        solo.goBack();
    }

    private void resetPassword(){
        // Click on Forgot Password?
        solo.clickOnView(solo.getView(com.eharmony.R.id.reset_button));
        // Wait for activity: 'com.eharmony.ui.ResetPasswordActivity'
        assertTrue(
                "com.eharmony.ui.ResetPasswordActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.ResetPasswordActivity.class));
    }


}
