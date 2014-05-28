package com.eharmony.robo.test;

import com.eharmony.ui.dash.HomeActivity;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class TestDashboard extends
        ActivityInstrumentationTestCase2<HomeActivity> {
    private static boolean toggle = false;
    private Solo solo;

    public TestDashboard() {
        super(HomeActivity.class);
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

    public void testDashboard() {
        solo.waitForActivity(HomeActivity.class, 2000);
        solo.waitForDialogToClose(5000);
        assertTrue(
                "com.eharmony.ui.registration.initial.RegistrationChoiceActivity is not found!",
                solo.waitForActivity(HomeActivity.class));
    }


    public void testMatches() {
        // Wait for activity: 'com.eharmony.ui.matchlist.MatchesListActivity'
        assertTrue(
                "com.eharmony.ui.matchlist.MatchesListActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.matchlist.MatchesListActivity.class));
        solo.goBack();
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
        // Press menu back key
        solo.goBack();
        // Wait for activity: 'com.eharmony.ui.matchlist.MatchesListActivity'
        assertTrue(
                "com.eharmony.ui.matchlist.MatchesListActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.matchlist.MatchesListActivity.class));
        // Wait for dialog
        solo.waitForDialogToOpen(5000);
        // Press menu back key
        solo.goBack();
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
        // Press menu back key
        solo.goBack();
    }

    public void testActivityFeed() {
        // Wait for activity: 'com.eharmony.ui.ActivityFeedActivity'
        assertTrue(
                "com.eharmony.ui.ActivityFeedActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.ActivityFeedActivity.class));
        // Press menu back key
        solo.goBack();
    }

    public void startWhatIf() {
        // Wait for activity: 'com.eharmony.ui.whatif.WhatIfSubscribeActivity'
        assertTrue(
                "com.eharmony.ui.whatif.WhatIfSubscribeActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.whatif.WhatIfSubscribeActivity.class));
        // Press menu back key
        solo.goBack();
    }

    public void startMyProfile() {
        // Wait for activity: 'com.eharmony.ui.profileedit.EditProfileActivity'
        assertTrue(
                "com.eharmony.ui.profileedit.EditProfileActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.profileedit.EditProfileActivity.class));
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
        // Take screenshot
        solo.takeScreenshot();
        // Press menu back key
        solo.goBack();
    }

    public void startSettingsMenu() {
        // Click on Settings
        solo.clickInList(2, 0);
        // Wait for activity: 'com.eharmony.ui.settings.SettingsActivity'
        assertTrue(
                "com.eharmony.ui.settings.SettingsActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.settings.SettingsActivity.class));
        // Click on Empty cache (could increase photo loading time)
        solo.clickOnView(solo.getView(com.eharmony.R.id.clear_cache_button));
        // Wait for dialog
        solo.waitForDialogToOpen(5000);
        // Click on Ok
        solo.clickOnView(solo.getView(android.R.id.button1));
        // Click on Privacy Policy
        solo.clickOnView(solo.getView(com.eharmony.R.id.policy_link));
        // Click on Terms and Conditions of Service
        solo.clickOnView(solo.getView(com.eharmony.R.id.tos_link));
        // Press menu back key
        solo.goBack();
    }

    public void startPersonalityProfile() {
        // Wait for activity:
        // 'com.eharmony.ui.personalityprofile.PersonalityProfileActivity'
        assertTrue(
                "com.eharmony.ui.personalityprofile.PersonalityProfileActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.personalityprofile.PersonalityProfileActivity.class));
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
        // Click on Personality Profile The Book of You
        // NonStretchableFrameLayout First Name
        solo.clickOnView(solo
                .getView(com.eharmony.R.id.personality_profile_book_cover_layout));
        // Press menu back key
        solo.goBack();
    }

    public void subscribeWhatIf() {
        // Wait for activity: 'com.eharmony.ui.whatif.WhatIfSubscribeActivity'
        assertTrue(
                "com.eharmony.ui.whatif.WhatIfSubscribeActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.whatif.WhatIfSubscribeActivity.class));
        // Click on Subscribe Now
        solo.clickOnView(solo.getView(com.eharmony.R.id.subscribe_now_button));
        // Wait for activity:
        // 'com.eharmony.ui.subscription.SubscriptionActivity'
        assertTrue(
                "com.eharmony.ui.subscription.SubscriptionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.subscription.SubscriptionActivity.class));
        // Click on Empty Text View
        solo.clickOnView(solo.getView(com.eharmony.R.id.subscribe_button));
        // Press menu back key
        solo.goBack();
    }

    public void subscribeDashboard() {
        // Wait for activity: 'com.eharmony.ui.dash.HomeActivity'
        assertTrue("com.eharmony.ui.dash.HomeActivity is not found!",
                solo.waitForActivity(HomeActivity.class));
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
        // Click on Upgrade your Membership
        solo.clickOnView(solo
                .getView(com.eharmony.R.id.upgrade_membership_button));
        // Wait for activity: 'com.eharmony.ui.subscription.BillingActivity'
        assertTrue(
                "com.eharmony.ui.subscription.BillingActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.subscription.BillingActivity.class));
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
        // Click on ImageView
        solo.clickOnView(solo.getView(com.eharmony.R.id.home_button));
        // Wait for activity: 'com.eharmony.ui.dash.HomeActivity'
        assertTrue("com.eharmony.ui.dash.HomeActivity is not found!",
                solo.waitForActivity(HomeActivity.class));
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
        // Click on Upgrade your Membership
        solo.clickOnView(solo
                .getView(com.eharmony.R.id.upgrade_membership_button));
        // Wait for activity: 'com.eharmony.ui.subscription.BillingActivity'
        assertTrue(
                "com.eharmony.ui.subscription.BillingActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.subscription.BillingActivity.class));
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
    }

}