package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject{

    private static final String
        STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "xpath://XCUIElementTypeButton[@name='Add or edit preferred languages']",
        STEP_LEARN_MORE_ABOUT_DATA_LINK = "xpath://XCUIElementTypeButton[@name='Learn more about data collected']",
        NEXT_LINK = "xpath://XCUIElementTypeButton[@name='Next']",
        GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Get started']",
        SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWayToExploreText(){
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' text", 10);
    }

    public void waitForAddOrEditPreferredLangLink(){
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_LINK, "Cannot find 'Learn more about data collected' link", 10);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' link", 10);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get started' button", 10);
    }

    public void clickSkip(){
        this.waitForElementAndClick(SKIP, "Cannot find skip button", 5);
    }

}
