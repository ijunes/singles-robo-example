package com.eharmony.test;

import android.test.ActivityInstrumentationTestCase2;

import com.eharmony.R;
import com.eharmony.R.id;
import com.eharmony.ui.SplashActivity;
import com.robotium.solo.Solo;



public class SplashActivityTest1 extends ActivityInstrumentationTestCase2<SplashActivity> {
  	private Solo solo;
  	
  	public SplashActivityTest1() {
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
		// Wait for activity: 'com.eharmony.ui.registration.initial.RegistrationChoiceActivity'
		assertTrue("com.eharmony.ui.registration.initial.RegistrationChoiceActivity is not found!", solo.waitForActivity(com.eharmony.ui.registration.initial.RegistrationChoiceActivity.class));
		// Click on Log In
		solo.clickOnView(solo.getView(R.id.log_in));
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
		solo.takeScreenshot();
		// Click on Log In
		solo.clickOnView(solo.getView(R.id.login_button));
		// Take screenshot
		solo.takeScreenshot();
		// Wait for dialog
		solo.waitForDialogToOpen(5000);
		// Click on Ok
		solo.clickOnView(solo.getView(R.id.ok_button));
		// Click on Log In
		solo.clickOnView(solo.getView(R.id.login_button));
		// Wait for activity: 'com.eharmony.ui.dash.HomeActivity'
		assertTrue("com.eharmony.ui.dash.HomeActivity is not found!", solo.waitForActivity(com.eharmony.ui.dash.HomeActivity.class));
		// Take screenshot
		solo.takeScreenshot();
		// Wait for dialog to close
		solo.waitForDialogToClose(5000);
		// Take screenshot
		solo.takeScreenshot();
		// Take screenshot
		solo.takeScreenshot();
		}
		catch(Exception e){
			solo.clickOnMenuItem("Log Out");
		}
	}
}
