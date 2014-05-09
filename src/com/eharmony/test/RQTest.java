package com.eharmony.test;

import java.util.ArrayList;
import java.util.Date;

import com.eharmony.ui.SplashActivity;
import com.eharmony.widgets.views.registration.NumberSlider;
import com.robotium.solo.*;
import com.squareup.spoon.Spoon;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;

public class RQTest extends ActivityInstrumentationTestCase2<SplashActivity> {
	private Solo solo;
	private Activity mActivity;
	private boolean toggle = false;
	private int networkTimeout = 20000;
	private int uiTimeout = 2000;
	private int logTimeout = 500;

	boolean male = false;
	String username = "f02@junu.me";
	Date birthday = new Date();
	int currentChapter = 0;
	int myDrinkIndex = 4;
	int mySmokeIndex = 4;
	int minAge = 18;
	int maxAge = 38;

	public RQTest() {
		super(SplashActivity.class);
	}

	public void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation());
		this.mActivity = getActivity();
	}

	@Override
	public void tearDown() throws Exception {
		solo.clickOnMenuItem("Log Out");
		solo.finishOpenedActivities();
		super.tearDown();
	}

	public void testRun() {
		// Wait for activity: 'com.eharmony.ui.SplashActivity'
		solo.waitForActivity(com.eharmony.ui.SplashActivity.class, uiTimeout);
		assertTrue(
				"com.eharmony.ui.registration.initial.RegistrationChoiceActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.registration.initial.RegistrationChoiceActivity.class));

		Spoon.screenshot(mActivity, "app_launched");

		testLogin();

		Spoon.screenshot(mActivity, "rq_start");
		// Wait for activity:
		// 'com.eharmony.ui.registration.rq.RelationshipQuestionnaireStartActivity'
		assertTrue(
				"com.eharmony.ui.registration.rq.RelationshipQuestionnaireStartActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.registration.rq.RelationshipQuestionnaireStartActivity.class));
		solo.waitForDialogToClose(networkTimeout);

		solo.waitForLogMessage("What is currentSection?", networkTimeout);
		for (int current = this.currentChapter; current < 10; current++) {
			String logMessage = "What is currentSection?";
			logMessage.concat(String.valueOf(current));
			if (solo.waitForLogMessage(logMessage, logTimeout)) {
				this.currentChapter = current;
				break;
			}
		}

		testChapterOne();
		testChapterTwo();
		testChapterThree();
		testChapterFour();
		testChapterFive();
		testChapterSix();
		testChapterSeven();
		testChapterEight();
	}

	private void testHeight() {
		// Click on Height Settings
		solo.clickOnView(solo.getView(com.eharmony.R.id.date_button));
		solo.waitForDialogToOpen(uiTimeout);

		solo.clickOnView(solo.getView(com.eharmony.R.id.increment));
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement));
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement));
		solo.clickOnView(solo.getView(com.eharmony.R.id.increment));
		solo.clickOnView(solo.getView(com.eharmony.R.id.increment, 1));
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement, 1));
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement, 1));
		solo.clickOnView(solo.getView(com.eharmony.R.id.increment, 1));
		solo.clickOnView(solo.getView(com.eharmony.R.id.increment, 2));
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement, 2));
		solo.clickOnView(solo.getView(com.eharmony.R.id.decrement, 2));
		solo.clickOnView(solo.getView(com.eharmony.R.id.increment, 2));
		solo.clickOnView(solo.getView(android.R.id.button1));

	}

	private void setHeight(int centi) {

	}

	private void testLogin() {
		// Click on Log In
		solo.clickOnView(solo.getView(com.eharmony.R.id.log_in));
		assertTrue("com.eharmony.ui.LoginActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.LoginActivity.class));
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.username_text));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.username_text), username);
		solo.clickOnView(solo.getView(com.eharmony.R.id.password_text));
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.password_text));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.password_text), "password");
		Spoon.screenshot(mActivity, "pre_login");
		solo.clickOnView(solo.getView(com.eharmony.R.id.login_button));
		submitDialog();
	}

	private void testChapterOne() {
		startChapter();
		Spoon.screenshot(mActivity, "chapter_completion");

		answerGender(this.male);

		// Birthday
		solo.clickOnView(solo.getView(com.eharmony.R.id.date_button));
		solo.waitForDialogToOpen(uiTimeout);
		solo.clickOnButton(0);
		solo.waitForDialogToClose(uiTimeout);
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		solo.clickOnView(solo.getView(com.eharmony.R.id.answer_edit_text));
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text), "City");
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		// Click on Never Married
		solo.clickOnText(java.util.regex.Pattern.quote("Never Married"));
		// solo.clickOnText(java.util.regex.Pattern.quote("Divorced"));
		// solo.clickOnText(java.util.regex.Pattern.quote("Separated"));
		// solo.clickOnText(java.util.regex.Pattern.quote("Widowed"));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		// Zero Children advance
		solo.clickOnView(solo.getView(0x1, 1));
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));
		// Click on No
		solo.clickOnView(solo.getView(0x1, 1));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));
		// Click on No
		solo.clickOnView(solo.getView(0x1, 2));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));
		finishChapter();
	}

	private void testChapterTwo() {
		// Chapter 2
		startChapter();
		answerRangeQuestions(7);

		assertTrue(
				"com.eharmony.ui.registration.rq.QuestionActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
		// Click on Good Listener
		solo.clickOnView(solo.getView(com.eharmony.R.id.check_box, 4));
		// Click on Modest
		solo.clickOnView(solo.getView(com.eharmony.R.id.check_box, 1));
		// Click on Respectful
		solo.clickOnView(solo.getView(com.eharmony.R.id.check_box, 2));
		// Click on Affectionate
		solo.clickOnView(solo.getView(com.eharmony.R.id.check_box, 3));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		finishChapter();
	}

	private void testChapterThree() {
		// Chapter 3
		// 35% Complete
		startChapter();
		answerRangeQuestions(7);
		finishChapter();
	}

	private void testChapterFour() {
		// 50%
		// On to my feeligns
		startChapter();
		answerRangeQuestions(7);
		finishChapter();
	}

	private void testChapterFive() {
		// 55%
		// Let's Do This
		startChapter();
		answerRangeQuestions(7);
		finishChapter();
	}

	private void testChapterSix() {
		// 65%
		// Keep it Moving
		startChapter();
		answerRangeQuestions(7);
		finishChapter();
	}

	private void testChapterSeven() {
		// 75%
		// Build My Profile
		startChapter();
		solo.clickOnView(solo.getView(0x2)); // Click on Bachelors
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		// Enter the text: 'Occupation'
		solo.clickOnView(solo.getView(com.eharmony.R.id.answer_edit_text));
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text), "Occupation");
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));

		// Click on $250,000 +
		solo.clickOnView(solo.getView(0x5, 1));
		assertTrue(solo
				.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast"));
		//Test Height Selection
		testHeight();
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
		// Click on LinearLayout
		solo.clickOnView(solo.getView(com.eharmony.R.id.nav_forward));
		finishChapter();
	}

	private void testChapterEight() {
		// 90%
		// Click on Let's do this
		solo.clickOnView(solo.getView(com.eharmony.R.id.continue_button));
		// Click on Somewhat important
		solo.clickOnView(solo.getView(0x1));
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

	private void submitDialog() {
		solo.waitForDialogToOpen(5000);
		solo.waitForDialogToClose(5000);
	}

	private void answerGender(boolean male) {
		solo.waitForLogMessage("GenderQuestionView called");
		View gender;
		if (male) {
			gender = solo.getView(com.eharmony.R.id.male_choice);
		} else {
			gender = solo.getView(com.eharmony.R.id.female_choice);
		}
		solo.clickOnView(gender);
		solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
	}

	private void finishRQ() {
		// Enter the text: '55555555552'
		solo.clearEditText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text));
		solo.enterText((android.widget.EditText) solo
				.getView(com.eharmony.R.id.answer_edit_text), "55555555552");
		// Click on LinearLayout
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
		assertTrue(
				"com.eharmony.ui.registration.rq.QuestionActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.registration.rq.ChapterCompletionActivity.class));
		if (!checkNewChapter()) {
			finishRQ();
		}
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
		solo.clickOnButton(0);
		assertTrue(
				"com.eharmony.ui.registration.rq.QuestionActivity is not found!",
				solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
	}

	private void answerRangeQuestions(int maxAnswer) {
		boolean lastQuestion = false;
		int answerViewIndex = 0;
		ArrayList<NumberSlider> numberSliders = solo
				.getCurrentViews(NumberSlider.class);
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
			solo.clickOnView(solo.getCurrentViews(ImageView.class,
					numberSliders.get(current)).get(selection));
			current++;
			if (current > size) {
				lastQuestion = true;
			}
			solo.waitForLogMessage("QuestionBroadcast Receiver, received a broadcast");
		}

	}

	private boolean toggleAnswer() {
		toggle = !toggle;
		return toggle;
	}
}
