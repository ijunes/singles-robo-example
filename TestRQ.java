package com.eharmony.robo.tests.rq;

import java.util.Date;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.eharmony.R;
import com.eharmony.ui.registration.rq.RelationshipQuestionnaireStartActivity;
import com.robotium.solo.By;
import com.robotium.solo.Solo;


public class TestRQ extends
        ActivityInstrumentationTestCase2<RelationshipQuestionnaireStartActivity> {
    private static boolean toggle = false;
    private static Solo solo;

    public TestRQ(Class rqActivityClass) {
        super(rqActivityClass);
        this.solo = solo;
        assertTrue(
                "com.eharmony.ui.registration.rq.RelationshipQuestionnaireStartActivity is not found!",
                solo.waitForActivity(RelationshipQuestionnaireStartActivity.class));
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public class ChapterOneTest {
        private Solo solo;

        public ChapterOneTest(Solo solo) {
            this.solo = solo;

        }

        public void testRun() {
            // Wait for activity: 'com.eharmony.ui.SplashActivity'
            solo.waitForActivity(com.eharmony.ui.SplashActivity.class, 2000);
            // Wait for activity:
            // 'com.eharmony.ui.registration.initial.RegistrationChoiceActivity'
            assertTrue(
                    "com.eharmony.ui.registration.initial.RegistrationChoiceActivity is not found!",
                    solo.waitForActivity(com.eharmony.ui.registration.initial.RegistrationChoiceActivity.class));
        }


        public void startChapterOne() {
            ChapterOneTest chapterOneTest = new ChapterOneTest(solo);
            selectGenderConfirm(0);
            selectBirthday(new Date());
            enterCity("City");
            selectMaritalStatus(0);
            selectKids(0);
            knowSuccessCouple(false);
            previousOnlineDater(false);
        }

        public void selectGenderConfirm(int gender) {
            // Click on ImageView
            solo.clickOnView(solo.getView(R.id.female_choice));
        }

        public void selectBirthday(Date date) {
            // Click on July 20, 1969
            solo.clickOnView(solo.getView(R.id.date_button));
            // Wait for dialog
            solo.waitForDialogToOpen(5000);
            // Enter the text: 'Nov'
            solo.clearEditText((EditText) solo
                    .getView("numberpicker_input"));
            solo.enterText(
                    (EditText) solo.getView("numberpicker_input"),
                    "Nov");
            // Enter the text: '1970'
            solo.clearEditText((EditText) solo.getView(
                    "numberpicker_input", 2));
            solo.enterText(
                    (EditText) solo.getView("numberpicker_input", 2),
                    "1970");
            // Click on Set
            solo.clickOnView(solo.getView(android.R.id.button1));
            // Click on LinearLayout
            solo.clickOnView(solo.getView(R.id.nav_forward));
        }

        public void enterCity(String city) {
            // Click on Empty Text View
            solo.clickOnView(solo.getView(R.id.answer_edit_text));
            // Enter the text: 'Los angeles'
            solo.clearEditText((EditText) solo
                    .getView(R.id.answer_edit_text));
            solo.enterText((EditText) solo
                    .getView(R.id.answer_edit_text), city);
            // Click on LinearLayout
            solo.clickOnView(solo.getView(R.id.nav_forward));
        }

        public void selectMaritalStatus(int index) {
            // Click on Never Married
            solo.clickOnText(java.util.regex.Pattern.quote("Never Married"));
        }

        public void selectKids(int count) {
            // Click on com.eharmony.R$id.number_spinner
            solo.clickOnView(solo.getView(R.id.number_spinner, 1));
            // Click on 0
            solo.clickOnText(java.util.regex.Pattern.quote("0"));
            // Click on LinearLayout
            solo.clickOnView(solo.getView(R.id.nav_forward));
        }

        public void knowSuccessCouple(boolean know) {
            // Click on No
            solo.clickOnView(solo.getView(0x1, 1));
        }

        public void previousOnlineDater(boolean dater) {
            // Click on No
            solo.clickOnView(solo.getView(0x1, 2));
            submitDialog();
            // Wait for activity:
            // 'com.eharmony.ui.registration.rq.ChapterCompletionActivity'
            assertTrue(
                    "com.eharmony.ui.registration.rq.ChapterCompletionActivity is not found!",
                    solo.waitForActivity(com.eharmony.ui.registration.rq.ChapterCompletionActivity.class));
        }
    }

    public void startChapterTwo() {
        // Click on Let's go
        solo.clickOnView(solo.getView(R.id.continue_button));
        // Wait for activity: 'com.eharmony.ui.registration.rq.QuestionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.QuestionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
        int answerViewCount = 0;
        for (int i = 0; i < 25; i++) {
            // Click on ImageView
            solo.clickOnView(solo.getView(android.widget.ImageView.class,
                    answerViewCount));
            answerViewCount = answerViewCount + 7;
            if (sameAnswer()) {
                try {
                    // Wait for dialog
                    solo.waitForDialogToOpen(5000);
                    // Click on Ok
                    solo.clickOnView(solo.getView(R.id.ok_button));
                } catch (Exception e) {

                }
            } else {
                if (answerToggle()) {
                    answerViewCount--;
                } else {
                    answerViewCount++;
                }
            }
        }
        submitDialog();
    }

    public void startChapterThree() {
        // Wait for activity: 'com.eharmony.ui.registration.rq.QuestionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.QuestionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
        answerRangeQuestions();
        submitDialog();
        finishChapter();
    }

    private void answerMultiSelect(int count, int index[]) {
        // Click on Modest
        for (int i = 0; i < count; i++) {
            solo.clickOnView(solo.getView(R.id.check_box, index[count]));
        }
        submitDialog();

    }

    public void finishChapter() {
        // Wait for activity:
        // 'com.eharmony.ui.registration.rq.ChapterCompletionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.ChapterCompletionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.ChapterCompletionActivity.class));
    }


    public void startChapterFour() {
        // Click on On to my spin on life
        solo.clickOnView(solo.getView(R.id.continue_button));
        // Wait for activity: 'com.eharmony.ui.registration.rq.QuestionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.QuestionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
        answerRangeQuestions();
        submitDialog();
        finishChapter();
    }


    private boolean sameAnswer() {
        // TODO Auto-generated method stub
        return false;
    }

    public void startChapterFive() {
        // Wait for activity:
        // 'com.eharmony.ui.registration.rq.ChapterCompletionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.ChapterCompletionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.ChapterCompletionActivity.class));
        // Click on Let's do this
        solo.clickOnView(solo.getView(R.id.continue_button));
        // Wait for activity: 'com.eharmony.ui.registration.rq.QuestionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.QuestionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
        answerRangeQuestions();

        submitDialog();
    }

    public void startChapterSix() {
        // Wait for activity:
        // 'com.eharmony.ui.registration.rq.ChapterCompletionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.ChapterCompletionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.ChapterCompletionActivity.class));
        // Click on Keep it moving
        solo.clickOnView(solo.getView(R.id.continue_button));
        // Wait for activity: 'com.eharmony.ui.registration.rq.QuestionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.QuestionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
        answerRangeQuestions();
        submitDialog();
    }

    public void startChapterSeven() {

        // Wait for activity:
        // 'com.eharmony.ui.registration.rq.ChapterCompletionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.ChapterCompletionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.ChapterCompletionActivity.class));
        // Click on Build my profile
        solo.clickOnView(solo.getView(R.id.continue_button));
        // Wait for activity: 'com.eharmony.ui.registration.rq.QuestionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.QuestionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
    }

    public void selectEducation(int index) {
        // Click on High School
        solo.clickOnView(solo.getView(index));
    }

    public void enterOccupation(String occupation) {
        // Enter the text: 'Skew'
        solo.clearEditText((EditText) solo
                .getView(R.id.answer_edit_text));
        solo.enterText((EditText) solo
                .getView(R.id.answer_edit_text), occupation);
        // Click on LinearLayout
        solo.clickOnView(solo.getView(R.id.nav_forward));
    }

    public void selectIncome(int index) {
        // Click on $250,000 +
        solo.clickOnView(solo.getView(0x5, 1));
    }

    public void setHeight() {
        // Click on _ ft. _ in. (_ _ _ cm)
        solo.clickOnView(solo.getView(R.id.date_button));
        // Wait for dialog
        solo.waitForDialogToOpen(5000);

        pickHeightFeet(5);
        pickHeightInches(10);
        pickHeightCenti(183);
        // Click on Save
        solo.clickOnView(solo.getView(android.R.id.button1));
    }

    public void chapterSevenRange() {
        answerRangeQuestions();
    }

    public void setEthnicity() {
        // Click on East Indian
        solo.clickOnView(solo.getView(0x4, 2));
    }

    public void setReligion() {
        // Click on Christian
        solo.clickOnText(java.util.regex.Pattern.quote("Christian"));
    }

    public void setReleigionDomination() {
        // Click on Churches of Christ
        solo.clickOnView(solo.getView(0x9, 2));
    }

    public void setMyDrinks(int index) {
        // Click on Socially
        solo.clickOnView(solo.getView(0x1, 5));
    }

    public void setMySmoke(int index) {
        // Click on Few times a week
        solo.clickOnView(solo.getView(0x3, 6));
    }

    public void answerFreeResponse(String body, int index) {
        // Click on Empty Text View index starts at 1
        solo.clickOnView(solo
                .getView(R.id.answer_edit_text, index));
        // Enter the text: 'Lorem ipsum dolor sit amet, consectetur adipiscing
        // elit. Nam bibendum lorem'
        solo.clearEditText((EditText) solo.getView(
                R.id.answer_edit_text, 1));
        solo.enterText(
                (EditText) solo.getView(
                        R.id.answer_edit_text, 1),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam bibendum lorem et augue hendrerit bibendum. Curabitur scelerisque interdum libero, id mattis risus varius id. Phasellus sit amet ligula arcu. Pellentesque quis placerat urna. Vestibulum id felis ut augue pharetra eleifend et at metus."
        );
        // Click on LinearLayout
        solo.clickOnView(solo.getView(R.id.nav_forward));
    }

    public void answerThreeThankfulItems(String[] items) {
        // Click on Empty Text View
        solo.clickOnView(solo.getView(R.id.answer_edit_text1));
        // Enter the text: 'Oak'
        solo.clearEditText((EditText) solo
                .getView(R.id.answer_edit_text1));
        solo.enterText((EditText) solo
                .getView(R.id.answer_edit_text1), items[0]);
        // Enter the text: 'Neo'
        solo.clearEditText((EditText) solo
                .getView(R.id.answer_edit_text2));
        solo.enterText((EditText) solo
                .getView(R.id.answer_edit_text2), items[1]);
        // Enter the text: 'Neo'
        solo.clearEditText((EditText) solo
                .getView(R.id.answer_edit_text3));
        solo.enterText((EditText) solo
                .getView(R.id.answer_edit_text3), items[2]);
        // Click on LinearLayout
        solo.clickOnView(solo.getView(R.id.nav_forward));
        submitDialog();
        // Wait for activity:
        // 'com.eharmony.ui.registration.rq.ChapterCompletionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.ChapterCompletionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.ChapterCompletionActivity.class));
    }

    public void startChapterNine() {
        // Click on Let's do this
        solo.clickOnView(solo.getView(R.id.continue_button));
        // Wait for activity: 'com.eharmony.ui.registration.rq.QuestionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.QuestionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
        // Click on Not at all important
        solo.clickOnText(java.util.regex.Pattern.quote("Not at all important"));
    }

    public void selectMatchDrink(int index) {
        // Click on Never
        solo.clickOnText(java.util.regex.Pattern.quote("Never"));
        // Click on Few times a week
        solo.clickOnView(solo.getView(index));
        // Click on Few times a week
        solo.clickOnView(solo.getView(index, 1));
    }

    public void confirmWarningDialog() {
        // Wait for dialog
        solo.waitForDialogToOpen(5000);
        // Click on Ok
        solo.clickOnView(solo.getView(android.R.id.button1));
    }

    public void selectEthnicPreference(int index) {
        // Click on No Preference
        solo.clickOnView(solo.getView(R.id.check_box));
        // Click on LinearLayout
        solo.clickOnView(solo.getView(R.id.nav_forward));
    }

    public void answerImportanceQuestions() {
        selectImportance(0x2, 2);
        selectImportance(0x2, 3);
        selectImportance(0x2, 4);
        selectImportance(0x2, 5);
        tapNavForward();
        selectImportance(0x2, 6);
        selectImportance(0x2, 7);
    }

    public void tapNavForward() {
        // Click on LinearLayout
        solo.clickOnView(solo.getView(R.id.nav_forward));
    }

    public void selectImportance(int item, int index) {
        // Click on Very important
        solo.clickOnView(solo.getView(item, index));
    }

    public void selectDistancePreference(int index) {
        // Click on Within 30 miles
        solo.clickOnText(java.util.regex.Pattern.quote("Within 30 miles"));
        if (index == 0) {
            // Wait for dialog
            solo.waitForDialogToOpen(5000);
            // Click on Ok
            solo.clickOnView(solo.getView(android.R.id.button1));
            // Click on LinearLayout
            solo.clickOnView(solo.getView(R.id.nav_forward));
        }
    }

    public void selectWantChildren(boolean bool) {
        // Click on Maybe
        solo.clickOnView(solo.getView(0x1, 8));
    }

    public void selectMeetChildren(boolean bool) {
        // Click on Yes
        solo.clickOnView(solo.getView(0x1, 9));

    }

    public void submitDialog() {
        // Wait for dialog
        solo.waitForDialogToOpen(5000);
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
    }

    public void startFitnessComplete() {
        // Wait for activity: 'com.eharmony.ui.registration.rq.QuestionActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.QuestionActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.QuestionActivity.class));
        enterPhoneNumber(Long.valueOf("5555555555"));
        submitDialog();
        submitDialog();
        submitDialog();
        selectPhotoUpload(0);
    }

    public void enterPhoneNumber(long phoneNumber) {
        solo.clearEditText((EditText) solo
                .getView(R.id.answer_edit_text));
        solo.enterText((EditText) solo
                .getView(R.id.answer_edit_text), String.valueOf(phoneNumber));
        // Click on LinearLayout
        solo.clickOnView(solo.getView(R.id.nav_forward));
    }

    public void selectPhotoUpload(int index) {
        // Wait for activity:
        // 'com.eharmony.ui.registration.rq.PhotoUploadActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.PhotoUploadActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.PhotoUploadActivity.class));
        // Click on Choose From Library
        solo.clickOnView(solo
                .getView(R.id.choose_from_library_button));
        // Wait for dialog
        solo.waitForDialogToOpen(5000);
        // Click on Upload
        solo.clickOnView(solo.getView(android.R.id.button1));
        submitDialog();
        // Wait for activity:
        // 'com.eharmony.ui.registration.rq.RelationshipQuestionnaireCompleteActivity'
        assertTrue(
                "com.eharmony.ui.registration.rq.RelationshipQuestionnaireCompleteActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.registration.rq.RelationshipQuestionnaireCompleteActivity.class));
    }

    public void confirmRQComplete() {
        // Click on Continue
        solo.clickOnView(solo.getView(R.id.continue_button));
        // Wait for activity: 'com.eharmony.ui.subscription.BillingActivity'
        assertTrue(
                "com.eharmony.ui.subscription.BillingActivity is not found!",
                solo.waitForActivity(com.eharmony.ui.subscription.BillingActivity.class));
    }

    public void confirmSubscription() {
        // Wait for dialog to close
        solo.waitForDialogToClose(5000);
        // Click on ImageView
        solo.clickOnView(solo.getView(R.id.home_button));
    }


    private void pickHeightFeet(int feet) {
        solo.clearEditText((EditText) solo
                .getView(R.id.timepicker_input));
        String height = String.valueOf(feet).concat("'");
        solo.enterText((EditText) solo
                .getView(R.id.timepicker_input), height);
        //Validate Increments & Decrements
        int newValue = feet++;
        solo.clickOnView(solo.getView(R.id.increment));
        assertTrue(((EditText) solo.getView(R.id.timepicker_input)).getText().equals(String.valueOf(newValue)));

        solo.clickOnView(solo.getView(R.id.decrement));
        assertTrue(((EditText) solo.getView(R.id.timepicker_input)).getText().equals(String.valueOf(feet)));
    }

    private void pickHeightInches(int inches) {
        solo.clearEditText((EditText) solo.getView(
                R.id.timepicker_input, 1));
        solo.enterText((EditText) solo.getView(
                R.id.timepicker_input, 1), String.valueOf(inches));

        int newValue = inches++;
        solo.clickOnView((EditText) solo.getView(
                R.id.increment, 1));
        assertTrue(((EditText) solo.getView(R.id.timepicker_input)).getText().equals(String.valueOf(newValue)));

        solo.clickOnView((EditText) solo.getView(
                R.id.decrement, 1));
        assertTrue(((EditText)solo.getView(R.id.timepicker_input)).getText().equals(String.valueOf(inches)));
    }

    private void pickHeightCenti(int cm) {
        solo.clearEditText((EditText) solo.getView(
                R.id.timepicker_input, 2));
        String height = String.valueOf(cm).concat("cm");
        solo.enterText((EditText) solo.getView(
                R.id.timepicker_input, 2), height);
        int newValue = cm++;
        solo.clickOnView((EditText) solo.getView(
                R.id.increment, 2));
        assertTrue(((EditText) solo.getView(R.id.timepicker_input)).getText().equals(String.valueOf(newValue)));

        solo.clickOnView((EditText) solo.getView(
                R.id.decrement, 2));
        assertTrue(((EditText) solo.getView(R.id.timepicker_input)).getText().equals(String.valueOf(cm)));

    }

    public void subscriptionWebForm() {
        // Click on $8.95/month
        solo.clickOnWebElement(By.textContent("$8.95/month"));
        // Click on billTo_firstName
        solo.clickOnWebElement(By.name("billTo_firstName"));
        // Clear text in billTo_firstName
        solo.clearTextInWebElement(By.name("billTo_firstName"));
        // Enter text in billTo_firstName
        solo.enterTextInWebElement(By.name("billTo_firstName"), "junu");
        // Click on billTo_street2
        solo.clickOnWebElement(By.name("billTo_street2"));
        // Click on billTo_street2
        solo.clickOnWebElement(By.name("billTo_street2"));
        // Click on billTo_state
        solo.clickOnWebElement(By.name("billTo_state"));
        // Clear text in billTo_state
        solo.clearTextInWebElement(By.name("billTo_state"));
        // Enter text in billTo_state
        solo.enterTextInWebElement(By.name("billTo_state"), "CA");
        // Click on card_accountNumber
        solo.clickOnWebElement(By.name("card_accountNumber"));
        // Click on card_accountNumber
        solo.clickOnWebElement(By.name("card_accountNumber"));
        // Clear text in card_expirationMonth
        solo.clearTextInWebElement(By.name("card_expirationMonth"));
        // Enter text in card_expirationMonth
        solo.enterTextInWebElement(By.name("card_expirationMonth"), "01");
        // Wait for dialog
        solo.waitForDialogToOpen(5000);
        // Click on 03
        solo.clickOnView(solo.getView(android.R.id.text1, 2));
        // Wait for dialog
        solo.waitForDialogToOpen(5000);
        // Scroll to 2025
        android.widget.ListView listView0 = solo.getView(
                android.widget.ListView.class, 0);
        solo.scrollListToLine(listView0, 0);
        // Click on 2025
        solo.clickOnView(solo.getView(android.R.id.text1, 11));
        // Click on card_cvNumber
        solo.clickOnWebElement(By.name("card_cvNumber"));
        // Clear text in card_cvNumber
        solo.clearTextInWebElement(By.name("card_cvNumber"));
        // Enter text in card_cvNumber
        solo.enterTextInWebElement(By.name("card_cvNumber"), "123");
        submitDialog();
    }

    private void answerRangeQuestions(){
        int answerViewCount = 0;
        boolean done = false;
        while(done == false) {
            // Click on ImageView
            answerViewCount = answerViewCount + 7;
            solo.clickOnView(solo.getView(android.widget.ImageView.class,
                    answerViewCount));

            if (sameAnswer()) {
                try {
                    // Wait for dialog
                    solo.waitForDialogToOpen(5000);
                    // Click on Ok
                    solo.clickOnView(solo.getView(R.id.ok_button));
                } catch (Exception e) {

                }
            } else {
                if (answerToggle()) {
                    answerViewCount--;
                } else {
                    answerViewCount++;
                }
            }
            if(solo.getView(android.widget.ImageView.class, answerViewCount + 7)==null){
                done=true;
            }
        }
    }

    private boolean answerToggle() {
        this.toggle = !this.toggle;
        return this.toggle;
    }
}