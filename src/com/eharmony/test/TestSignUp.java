package com.eharmony.test;

import android.test.ActivityInstrumentationTestCase2;

import com.eharmony.R;
import com.eharmony.ui.SplashActivity;
import com.robotium.solo.Solo;

public class TestSignUp extends
        ActivityInstrumentationTestCase2<SplashActivity> {
    private static boolean toggle = false;
    private Solo solo;

    public TestSignUp() {
        super(SplashActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        //this.solo = solo;
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
                solo.waitForActivity(SplashActivity.class));
        solo.clickOnView(solo.getView(R.id.sign_up));
        // Wait for activity: 'com.eharmony.ui.LoginActivity'
        assertTrue("com.eharmony.ui.LoginActivity is not found!", solo.waitForActivity(com.eharmony.ui.registration.initial.RegisterPageOneActivity.class));
        startSignUp();
    }
    private void startSignUp(){
        startPageOne();
        startPageTwo();
        startPageThree();
    }

    private void startPageOne() {
        // Wait for activity:
        // 'com.eharmony.ui.registration.initial.RegisterPageOneActivity'
        assertTrue(
                "com.eharmony.ui.registration.initial.RegisterPageOneActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.initial.RegisterPageOneActivity.class));
        enterFirstName("Test");
        selectGender(0);
        finishPageOne();
    }
    private void startPageTwo(){
        assertTrue(
                "com.eharmony.ui.registration.initial.RegisterPageTwoActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.initial.RegisterPageTwoActivity.class));
        enterZipCode(90210);
        selectCountry("United States");
        selectHeardAbout(1);
        finishPageTwo();
    }
    private void startPageThree(){
        enterEmailAddress("email@junu.me");
        enterPassword("password");
        finishPageThree();
    }


    public void logOut() {
        // Click on Log Out
        solo.clickInList(1, 0);
    }

    private void enterFirstName(String firstName) {
        // Enter the text: 'First name'
        solo.clearEditText((android.widget.EditText) solo
                .getView(R.id.first_name_edit_text));
        solo.enterText((android.widget.EditText) solo
                .getView(R.id.first_name_edit_text), firstName);
    }

    private void selectGender(int gender) {
        if (gender == 0) {
            // Click on woman
            solo.clickOnView(solo.getView(R.id.my_gender_female));
            selectGenderPreference(R.id.my_gender_preference_male);
        } else {
            solo.clickOnView(solo.getView(R.id.my_gender_male));
            selectGenderPreference(R.id.my_gender_preference_female);
        }
    }

    private void selectGenderPreference(int genderPreference) {
        // Click on man
        solo.clickOnView(solo
                .getView(genderPreference));
    }



    private void finishPageOne() {
        // Click on Continue
        solo.clickOnView(solo.getView(R.id.page_one_next_button));
        // Wait for activity:
        // 'com.eharmony.ui.registration.initial.RegisterPageTwoActivity'
        assertTrue(
                "com.eharmony.ui.registration.initial.RegisterPageTwoActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.initial.RegisterPageTwoActivity.class));
    }
    private void finishPageTwo() {
        // Click on Continue
        solo.clickOnView(solo.getView(R.id.page_two_next_button));
        // Wait for activity:
        // 'com.eharmony.ui.registration.initial.RegisterPageThreeActivity'
        assertTrue(
                "com.eharmony.ui.registration.initial.RegisterPageThreeActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.initial.RegisterPageThreeActivity.class));
    }
    private void finishPageThree() {
        // Click on Find My Matches
        solo.clickOnView(solo.getView(R.id.page_three_next_button));
        submitDialog();
    }
    private void submitDialog() {
        // Wait for dialog
        solo.waitForDialogToOpen(5000);
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
    }
    private void enterZipCode(int zipCode) {
        // Click on Empty Text View
        solo.clickOnView(solo
                .getView(R.id.my_postal_code_edit_text));
        // Enter the text: '90001'
        solo.clearEditText((android.widget.EditText) solo
                .getView(R.id.my_postal_code_edit_text));
        solo.enterText((android.widget.EditText) solo
                .getView(R.id.my_postal_code_edit_text), String.valueOf(zipCode));
    }

    private void selectCountry(String country) {
        // Click on United States
        solo.clickOnView(solo.getView(R.id.my_country_text_button));
        // Click on United States
        solo.clickOnView(solo.getView(R.id.dropdown_textview));
    }

    private void selectHeardAbout(int index) {
        // Click on Select One@
        solo.clickOnView(solo
                .getView(R.id.heard_about_text_button));
        // Click on Television
        solo.clickOnView(solo.getView(R.id.dropdown_textview, index));
    }

    private void enterEmailAddress(String email) {
        // Enter the text: 'f23@junu.me'
        solo.clearEditText((android.widget.EditText) solo
                .getView(R.id.my_email_edit_text));
        solo.enterText((android.widget.EditText) solo
                .getView(R.id.my_email_edit_text), email);
    }

    private void enterPassword(String password) {
        // Click on Empty Text View
        solo.clickOnView(solo.getView(R.id.my_password_edit_text));
        // Enter the text: 'password'
        solo.clearEditText((android.widget.EditText) solo
                .getView(R.id.my_password_edit_text));
        solo.enterText((android.widget.EditText) solo
                .getView(R.id.my_password_edit_text), "password");
        enterPasswordConfirmation(password);
    }

    private void enterPasswordConfirmation(String password) {
        // Click on Empty Text View
        solo.clickOnView(solo
                .getView(R.id.my_password_confirmation_edit_text));
        // Enter the text: 'password'
        solo.clearEditText((android.widget.EditText) solo
                .getView(R.id.my_password_confirmation_edit_text));
        solo.enterText((android.widget.EditText) solo
                        .getView(R.id.my_password_confirmation_edit_text),
                "password"
        );
    }


}
