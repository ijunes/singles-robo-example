package com.eharmony.robo.test;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.eharmony.model.UserProfile;
import com.eharmony.ui.SplashActivity;
import com.eharmony.ui.registration.rq.QuestionActivity;
import com.eharmony.ui.registration.rq.RelationshipQuestionnaireStartActivity;
import com.eharmony.widgets.views.registration.GenderQuestionView;
import com.eharmony.widgets.views.registration.NumberSlider;
import com.robotium.solo.*;
import com.robotium.solo.Solo.Config;
import com.squareup.spoon.Spoon;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.test.ActivityInstrumentationTestCase2;
import android.test.FlakyTest;
import android.view.View;
import android.widget.ImageView;

public class TestRQ extends ActivityInstrumentationTestCase2<SplashActivity> {
	// Define Robotium RunnerParams
	private Solo solo;
	private Activity mActivity;
	private boolean toggle = false;
	private int networkTimeout = 10000;
	private int uiTimeout = 2000;
	private int logTimeout = 500;
	private Instrumentation instrumentation;
	String m_androidId;
	UserProfile userProfile;

	boolean male;
	String username;
	String phoneNumber;
	String city = "Test City";
	Date birthday = new Date();
	int currentChapter = 0;
	int myDrinkIndex = 4;
	int mySmokeIndex = 4;
	int minAge = 18;
	int maxAge = 38;

	WifiManager wifiman;

	public void toggleWifi() {
		this.wifiman.setWifiEnabled(false);
	}

	public TestRQ() {
		super(SplashActivity.class);
	}
	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.instrumentation = getInstrumentation();
		solo = new Solo(this.instrumentation);
		this.mActivity = getActivity();
		// Wait for activity: 'com.eharmony.ui.SplashActivity'
		solo.waitForActivity(com.eharmony.ui.SplashActivity.class, uiTimeout);
		Timeout.setSmallTimeout(1000);
		this.wifiman = (WifiManager) solo.getCurrentActivity()
				.getSystemService(Context.WIFI_SERVICE);
		takeScreenshot("splash_activity");
		m_androidId = Secure.getString(solo.getCurrentActivity()
				.getContentResolver(), Secure.ANDROID_ID);
		setUserData();
	
	}

	private void setUserData() {
		switch (m_androidId) {
		// Graphite s3 TW
		case "4676b827ae38762c":
			username = "f03@junu.me";
			phoneNumber = "5555555555";
			male = false;
			break;
		// White s3 CM
		case "37c40e7caf3cc7c1":
			username = "f02@junu.me";
			phoneNumber = "5555555556";
			male = false;
			break;
		// Graphite s4 TW
		case "11d5f6d6ece7a9df":
			username = "f02@junu.me";
			phoneNumber = "5555555557";
			male = false;
			break;
		// S4 CM
		case "5e488f3155624579":
			username = "f02@junu.me";
			phoneNumber = "5555555558";
			male = true;
			break;
		default:
			username = "f10@junu.me";
			phoneNumber = "5555555551";
		}

	}

	@Override
	public void tearDown() throws Exception {
		// solo.clickOnMenuItem("Log Out");
		solo.finishOpenedActivities();
		super.tearDown();
	}

	public void AInstall_loginIsSuccessful() {
		Class startingClass = solo.getCurrentActivity().getClass();
		if (startingClass == com.eharmony.ui.registration.initial.RegistrationChoiceActivity.class) {

		} else if (startingClass == com.eharmony.ui.dash.HomeActivity.class) {

		}
		// Assert no credentials are cached
		assertTrue(
				"com.eharmony.ui.registration.initial.RegistrationChoiceActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.registration.initial.RegistrationChoiceActivity.class));
		takeScreenshot("app_started");
	}
	
	@Test
	public void testALogin_initialLogin() {
		Spoon.screenshot(solo.getCurrentActivity(), "app_started");
		/*boolean loggedOut = solo
				.waitForActivity(
						com.eharmony.ui.registration.initial.RegistrationChoiceActivity.class,
						5000);
		if (loggedOut) {
			solo.clickOnMenuItem("Log Out");
		}*/
		// Click on Log In
		solo.clickOnView(solo.getView(com.eharmony.R.id.log_in));

		// Assert LoginActivity loaded
		takeScreenshot("pre_login");
		assertTrue("com.eharmony.ui.LoginActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.LoginActivity.class));

		// Enter login credentials
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.username_text));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.username_text), username);
		solo.clickOnView(solo.getView(com.eharmony.R.id.password_text));
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.password_text));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.password_text), "password");
		takeScreenshot("login_details_filled");

		// Begin Login
		solo.clickOnView(solo.getView(com.eharmony.R.id.login_button));
		submitDialog();
		takeScreenshot("logged_in");
	}
	
	@Test
	public void testChapter1() {
		solo.searchButton("Continue");
		takeScreenshot("chapter_completion");
		startChapter();
		solo.waitForView(GenderQuestionView.class);
		answerGender(this.male);

		// Birthday
		solo.waitForView(solo.getView(com.eharmony.R.id.date_button));
		solo.clickOnView(solo.getView(com.eharmony.R.id.date_button));
		solo.waitForDialogToOpen(uiTimeout);
		takeScreenshot("date_picker");
		// DatePicker differentiation in 4.4 and 4.3
		try {
			solo.clickOnButton(1);
		} catch (Exception e) {
			solo.clickOnButton(0);
		}
		solo.waitForDialogToClose(uiTimeout);
		takeScreenshot("date_picked");
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		assertTrue(
				"Failed to answer Birthdate",
				solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		// Enter City
		solo.clickOnView(solo.getView(com.eharmony.R.id.answer_edit_text));
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text), "City");
		takeScreenshot("city_entered");
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		assertTrue(
				"Failed to answer City",
				solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		// Click on Never Married
		takeScreenshot("marriage_status");
		solo.clickOnText(java.util.regex.Pattern.quote("Never Married"));
		// solo.clickOnText(java.util.regex.Pattern.quote("Divorced"));
		// solo.clickOnText(java.util.regex.Pattern.quote("Separated"));
		// solo.clickOnText(java.util.regex.Pattern.quote("Widowed"));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		// Zero Children advance
		takeScreenshot("number of children");
		solo.clickOnView(solo.getView(0x1, 1));
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));
		// Click on No
		takeScreenshot("know_other_members");
		solo.clickOnView(solo.getView(0x1, 1));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));
		// Click on No
		takeScreenshot("online_dater");
		solo.clickOnView(solo.getView(0x1, 2));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));
		finishChapter();
		takeScreenshot("chapter_one_finished");
	}
	@Test
	public void testChapter2() {
		// Chapter 2
		startChapter();
		answerRangeQuestions(7);
		Class<? extends Activity> currentClass = solo.getCurrentActivity()
				.getClass();
		assertTrue(currentClass == QuestionActivity.class);
		solo.waitForView(solo.getView(com.eharmony.R.id.check_box));
		// Click on Good Listener
		solo.clickOnText(java.util.regex.Pattern.quote("Good Listener"));
		// Click on Modest
		solo.clickOnText(java.util.regex.Pattern.quote("Modest"));
		// Click on Respectful
		solo.clickOnText(java.util.regex.Pattern.quote("Respectful"));
		// Click on Affectionate
		solo.clickOnText(java.util.regex.Pattern.quote("Affectionate"));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		finishChapter();
		//solo.searchButton("35% Complete", true);
	}

	public void testChapter3() {
		// Chapter 3
		// 35% Complete
		//
		startChapter();
		answerRangeQuestions(7);
		finishChapter();
	}

	public void testChapter4() {
		// 50%
		// On to my feeligns
		startChapter();
		answerRangeQuestions(7);
		finishChapter();
	}

	public void testChapter5() {
		// 55%
		// Let's Do This
		startChapter();
		answerRangeQuestions(7);
		finishChapter();
		solo.searchButton("Keep it Moving");
	}

	public void testChapter6() {
		// 65%
		// Keep it Moving

		startChapter();
		answerRangeQuestions(7);
		finishChapter();
		// 75%
		solo.searchButton("Build My Profile");
	}

	public void testChapter7() {

		// 75%
		startChapter();

		// EDUCATION Click on Bachelors
		solo.clickOnText(java.util.regex.Pattern.quote("Bachelors"));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		// OCCUPATION Enter the text: 'Occupation'
		solo.clickOnView(solo.getView(com.eharmony.R.id.answer_edit_text));
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text), "Occupation");
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		// SALARY Click on $250,000 +
		solo.clickOnView(solo.getView(0x5, 1));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		// Test Height Selection
		checkHeight();
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));
		answerRangeQuestions(7);

		// Click on White
		solo.clickOnText(java.util.regex.Pattern.quote("White"));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");

		// Click on Christian
		solo.clickOnText(java.util.regex.Pattern.quote("Christian"));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
		// Click on Eastern / Greek Orthodox
		solo.clickOnText(java.util.regex.Pattern
				.quote("Eastern / Greek Orthodox"));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");

		// Click on Daily (My Drinks)
		solo.clickOnView(solo.getView(myDrinkIndex, 5));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
		// Click on Daily (My Smoke)
		solo.clickOnView(solo.getView(mySmokeIndex, 6));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");

		solo.clickOnView(solo.getView(com.eharmony.R.id.answer_edit_text, 1));
		solo.clearEditText((android.widget.EditText) solo.getView(
				com.eharmony.R.id.answer_edit_text, 1));
		solo.enterText(
				(android.widget.EditText) solo.getView(
						com.eharmony.R.id.answer_edit_text, 1),
				"Fusce purus risus, ultrices a elementum ut, mattis non nisi. Sed libero est, accumsan ut rutrum quis, vulputate aliquet nisl. Integer sed orci ac quam pulvinar interdum. Fusce lobortis erat vel euismod dapibus. Duis accumsan aliquet leo id blandit. Pellentesque semper est non elit aliquet, a placerat ante lacinia. Maecenas elit nibh, varius vel aliquet at, commodo sit amet dolor. Nulla blandit ante eu dolor luctus ultrices. Nullam gravida elementum ipsum nec porta. Sed blandit vel lacus vel fringilla. Vestibulum egestas laoreet nisl ac elementum. Pellentesque sit amet laoreet ligula, nec iaculis elit. Quisque ac suscipit erat. Nam at nisl dic");
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");

		solo.clickOnView(solo.getView(com.eharmony.R.id.answer_edit_text, 2));
		solo.clearEditText((android.widget.EditText) solo.getView(
				com.eharmony.R.id.answer_edit_text, 2));
		solo.enterText(
				(android.widget.EditText) solo.getView(
						com.eharmony.R.id.answer_edit_text, 2),
				"Fusce purus risus, ultrices a elementum ut, mattis non nisi. Sed libero est, accumsan ut rutrum quis, vulputate aliquet nisl. Integer sed orci ac quam pulvinar interdum. Fusce lobortis erat vel euismod dapibus. Duis accumsan aliquet leo id blandit. Pellentesque semper est non elit aliquet, a placerat ante lacinia. Maecenas elit nibh, varius vel aliquet at, commodo sit amet dolor. Nulla blandit ante eu dolor luctus ultrices. Nullam gravida elementum ipsum nec porta. Sed blandit vel lacus vel fringilla. Vestibulum egestas laoreet nisl ac elementum. Pellentesque sit amet laoreet ligula, nec iaculis elit. Quisque ac suscipit erat. Nam at nisl dic");
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");

		// Enter the text: 'One'
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text1));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text1), "One");
		// Enter the text: 'Two'
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text2));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text2), "Two");
		// Enter the text: 'Three'
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text3));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text3), "Three");
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		finishChapter();
		
		//TODO verify chapter finish
		assertTrue("90% completion error", solo.waitForText("90%"));
	}

	public void testChapter8() {
		// 90%
		// Click on Let's do this
		solo.clickOnView(solo.getButton(0));
		// Click on Somewhat important
		solo.clickOnText(java.util.regex.Pattern.quote("Somewhat important"));
		// Click on No preference
		solo.clickOnView(solo.getView(com.eharmony.R.id.check_box));
		// Click on LinearLayout
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		// Click on Never
		solo.clickOnText(java.util.regex.Pattern.quote("Never"));
		// Wait for dialog
		solo.waitForDialogToOpen(5000);
		// Click on Ok
		solo.clickOnView(solo.getView(android.R.id.button1));
		// Click on Daily
		solo.clickOnView(solo.getView(0x4));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
		// Click on Daily
		solo.clickOnView(solo.getView(0x4, 1));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
		// Click on No Preference
		solo.clickOnView(solo.getView(com.eharmony.R.id.check_box, 11));
		// Click on LinearLayout
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
		// Click on Somewhat important
		solo.clickOnView(solo.getView(0x1, 3));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
		// Click on Somewhat important
		solo.clickOnView(solo.getView(0x1, 4));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
		// Click on Somewhat important
		solo.clickOnView(solo.getView(0x1, 5));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
		// Click on 37
		solo.clickOnView(solo.getView(com.eharmony.R.id.low_age_spinner));
		// Click on 37
		solo.clickOnText(java.util.regex.Pattern.quote(String.valueOf(minAge)));
		// Click on 49
		solo.clickOnView(solo.getView(com.eharmony.R.id.upper_age_spinner));
		// Click on 49
		solo.clickOnText(java.util.regex.Pattern.quote(String.valueOf(maxAge)));
		// Click on LinearLayout
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");

		// Click on Somewhat important
		solo.clickOnView(solo.getView(0x1, 6));
		// Click on Within 60 miles
		solo.clickOnView(solo.getView(0x1, 7));
		// Click on Maybe
		solo.clickOnView(solo.getView(0x1, 8));
		// Click on Yes
		solo.clickOnView(solo.getView(0x1, 9));
		submitDialog();
		finishRQ();
	}

	private void answerGender(boolean male) {

		if (male) {
			solo.clickOnView(solo.getView(com.eharmony.R.id.male_choice));
		} else {
			solo.clickOnView(solo.getView(com.eharmony.R.id.female_choice));
		}

		takeScreenshot("gender_selected");
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
	}

	private void finishRQ() {
		// Enter phoneNumber
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text), phoneNumber);
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));

		// Wait for dialog
		solo.waitForDialogToOpen(5000);
		// Wait for dialog
		solo.waitForDialogToOpen(5000);
		// Wait for dialog to close
		solo.waitForDialogToClose(5000);
		// Wait for activity:
		// 'com.eharmony.ui.registration.rq.PhotoUploadActivity'
		assertTrue(
				"com.eharmony.ui.registration.rq.PhotoUploadActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.registration.rq.PhotoUploadActivity.class));
		// Wait for dialog to close
		solo.waitForDialogToClose(5000);
		// Assert that last section asks for phone number
		assertTrue(
				"com.eharmony.ui.registration.rq.QuestionActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
	}

	private void finishChapter() {
		// Wait for Flurry Counter
		solo.waitForLogMessage("Event count incremented: singles:mobile:rq.chapter.completed");

		//if (!checkNewChapter()) {
		//	finishRQ();
		//}
	}

	private boolean checkNewChapter() {
		// Is there another Chapter after?
		try {
			assertTrue(
					"com.eharmony.ui.registration.rq.ChapterCompletionActivity is not found!",
					solo.waitForActivity(com.eharmony.ui.registration.rq.ChapterCompletionActivity.class));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void startChapter() {
		solo.waitForView(com.eharmony.R.id.continue_button, 1, uiTimeout);
			solo.clickOnView(solo.getButton(0));
			submitDialog();
			assertTrue(
					"com.eharmony.ui.registration.rq.QuestionActivity is not found!",
					solo.waitForActivity(
							com.eharmony.ui.registration.rq.QuestionActivity.class,
							networkTimeout));
		
	}

	private void checkHeight() {
		// Click on Height Settings
		solo.clickOnView(solo.getView(com.eharmony.R.id.date_button));
		solo.waitForDialogToOpen(uiTimeout);
		takeScreenshot("height");
		solo.clickOnView(solo.getView(com.eharmony.R.id.increment));
		takeScreenshot("height_feet");
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement));
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement));
		takeScreenshot("height_feet_lower");
		solo.clickOnView(solo.getView(com.eharmony.R.id.increment));

		takeScreenshot("height_inches_plus_one");
		solo.clickOnView(solo.getView(com.eharmony.R.id.increment, 1));
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement, 1));
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement, 1));
		takeScreenshot("height_inches_minus_one");
		solo.clickOnView(solo.getView(com.eharmony.R.id.increment, 1));

		solo.clickOnView(solo.getView(com.eharmony.R.id.increment, 2));
		takeScreenshot("height_cm_plus_one");
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement, 2));
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement, 2));
		takeScreenshot("height_cm_minus_one");
		solo.clickOnView(solo.getView(com.eharmony.R.id.increment, 2));
		takeScreenshot("height_final");
		solo.clickOnView(solo.getView(android.R.id.button1));

	}

	private void setHeight(int centi) {

	}

	private void answerRangeQuestions(int maxAnswer) {
		boolean lastQuestion = false;
		assertTrue(
				"com.eharmony.ui.registration.rq.QuestionActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
		int answerViewIndex = 0;
		solo.waitForView(NumberSlider.class);
		ArrayList<ImageView> numberSliders = solo
				.getCurrentViews(ImageView.class);

		int size = numberSliders.size();
		int current = 0;
		while (!lastQuestion) {
			answerViewIndex = answerViewIndex + maxAnswer;
			int selection;

			if (toggleAnswer()) {
				selection = answerViewIndex;
			} else {
				selection = answerViewIndex - 1;
			}
			if (selection < size) {
				solo.clickOnView(solo.getView(android.widget.ImageView.class,
						selection));
			} else {
				lastQuestion = true;
			}

			solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
		}

	}

	private void submitDialog() {
		takeScreenshot("loading_dialog");
		solo.waitForDialogToOpen(networkTimeout);
		solo.waitForDialogToClose(networkTimeout);
	}

	private void takeScreenshot(String imageName) {

		instrumentation.waitForIdleSync();
		try {
			Spoon.screenshot(solo.getCurrentActivity(), imageName);
		} catch (Exception e) {

		}
	}

	private boolean toggleAnswer() {
		toggle = !toggle;
		return toggle;
	}
}
