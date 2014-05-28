package com.eharmony.robo.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.eharmony.R;
import com.eharmony.R.id;
import com.eharmony.ui.SplashActivity;
import com.robotium.solo.Solo;
import com.squareup.spoon.Spoon;



public class TestSplashActivity extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;
	private Activity mActivity;
  	
  	public TestSplashActivity() {
		super(SplashActivity.class);
  	}

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
		try{
		// Wait for activity: 'com.eharmony.ui.SplashActivity'
		solo.waitForActivity(SplashActivity.class, 2000);
		Spoon.screenshot(mActivity, "registration_choice");
		// Wait for activity: 'com.eharmony.ui.registration.initial.RegistrationChoiceActivity'
		assertTrue("com.eharmony.ui.registration.initial.RegistrationChoiceActivity is not found!", solo.waitForActivity(com.eharmony.ui.registration.initial.RegistrationChoiceActivity.class));
		// Click on Log In
		solo.clickOnView(solo.getView(R.id.log_in));
		Spoon.screenshot(mActivity, "login screen");
		// Wait for activity: 'com.eharmony.ui.LoginActivity'
		assertTrue("com.eharmony.ui.LoginActivity is not found!", solo.waitForActivity(com.eharmony.ui.LoginActivity.class));
		// Enter the text: 'f23@junu.me'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.username_text));
		solo.enterText((android.widget.EditText) solo.getView(R.id.username_text), "f23@junu.me");
		// Click on Empty Text View
		solo.clickOnView(solo.getView(R.id.password_text));
		// Enter the text: 'password'
		solo.clearEditText((android.widget.EditText) solo.getView(R.id.password_text));
		solo.enterText((android.widget.EditText) solo.getView(R.id.password_text), "password");
		// Take screenshot
		Spoon.screenshot(mActivity, "login_details_filled");
		// Click on Log In
		solo.clickOnView(solo.getView(R.id.login_button));
		// Take screenshot
		Spoon.screenshot(mActivity, "logging screen");
		// Wait for dialog
		solo.waitForDialogToOpen(5000);
		// Click on Ok
		solo.clickOnView(solo.getView(R.id.ok_button));
		// Click on Log In
		solo.clickOnView(solo.getView(R.id.login_button));
		// Wait for activity: 'com.eharmony.ui.dash.HomeActivity'
		assertTrue("com.eharmony.ui.dash.HomeActivity is not found!", solo.waitForActivity(com.eharmony.ui.dash.HomeActivity.class));

		// Wait for dialog to close
		solo.waitForDialogToClose(5000);
		Spoon.screenshot(mActivity, "dashboard_started");

		solo.clickOnMenuItem("Log Out");
		Spoon.screenshot(mActivity, "logout");
		}
		catch(Exception e){
		
		}
	}
}
