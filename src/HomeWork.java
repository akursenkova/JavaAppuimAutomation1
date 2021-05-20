import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class HomeWork {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/home/user/work/JavaAppuimAutomation1/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

//    @Test
//    public void searchInputContainsText(){
//
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "Cannot find search input with text 'Search Wikipedia'",
//                5
//        );
//
//        assertElementHasText(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Search…",
//                "Cannot find search input with text 'Search…'",
//                5
//        );
//    }

//    @Test
//    public void clearSearchInput(){
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "Cannot find search input with text 'Search Wikipedia'",
//                5
//        );
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                "Java",
//                "Cannot find search input",
//                5
//        );
//
//        waitForElementPresent(
//                By.id("org.wikipedia:id/page_list_item_container"),
//                "Cannot find search results",
//                15
//        );
//
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "Cannot find X to cancel search",
//                5
//        );
//
//        waitForElementNotPresent(
//                By.id("org.wikipedia:id/page_list_item_container"),
//                "Search results are still present on the page",
//                5
//        );
//    }

    @Test
    public void testSearchResultsContainText(){

        //Find search input
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input with text 'Search Wikipedia'",
                5
        );

        //Input text for search
        WebElement search_input_element = waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        //Make searched text a variable for convenience
        String searched_text = search_input_element.getAttribute("text");

        //Check if there are search results for our text
        waitForElementPresent(
                By.xpath("//*[contains(@resource-id, 'org.wikipedia:id/page_list_item_container')]//*[contains(@text, '" + searched_text + "')]"),
                "Cannot find search results for " + searched_text,
                15
        );

        //Compare number of all search results with number of results containing our text
        assertAllSearchResultsContainSomeText(
                By.id("org.wikipedia:id/page_list_item_container"),
                By.xpath("//*[contains(@resource-id, 'org.wikipedia:id/page_list_item_container')]//*[contains(@text, '" + searched_text + "')]"),
                "Number of results with text '" + searched_text + "' doesn't equals number of all search results"
        );
    }

    @Test
    public void saveArticlesToList(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input with text 'Search Wikipedia'",
                5
        );

        String article_title = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                article_title,
                "Cannot find search input",
                5
        );

        String first_article = "//*[contains(@resource-id, org.wikipedia:id/page_list_item_container)]//*[contains(@text, 'Object-oriented programming language')]";
        String first_article_title = "Java (programming language)";
        String second_article = "//*[contains(@resource-id, org.wikipedia:id/page_list_item_container)]//*[contains(@text, 'Island of Indonesia')]";
        String second_article_title = "Java";
        waitForElementAndClick(
                By.xpath(first_article),
                "Cannot find article " + first_article_title,
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );


        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForMenuToRender(
                By.xpath("//*[@text='Change language']"),
                By.xpath("//*[@text='Share link']"),
                By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//*[@text='Find in page']"),
                By.xpath("//*[@text='Similar pages']"),
                By.xpath("//*[@text='Font and theme']"),
                "Cannot find in menu option number ",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input with text 'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                article_title,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath(second_article),
                "Cannot find article " + second_article_title,
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );


        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForMenuToRender(
                By.xpath("//*[@text='Change language']"),
                By.xpath("//*[@text='Share link']"),
                By.xpath("//*[@text='Add to reading list']"),
                By.xpath("//*[@text='Find in page']"),
                By.xpath("//*[@text='Similar pages']"),
                By.xpath("//*[@text='Font and theme']"),
                "Cannot find in menu option number ",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder to add article",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My Lists",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='" + first_article_title + "']"),
                "Cannot find saved article " + first_article_title
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='" + first_article_title + "']"),
                "Cannot delete saved article",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='" + second_article_title + "']"),
                "Cannot find article " + second_article_title,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + second_article_title + "']"),
                "Cannot find article " + second_article_title,
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String actual_title_name = title_element.getAttribute("text");

        Assert.assertEquals(
                "Title of opened article doesn't equals " + second_article_title,
                actual_title_name,
                second_article_title
        );
    }

    @Test //this test will be failed, because article title doesn't have time to load
    public void testAssertTitle(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input with text 'Search Wikipedia'",
                5
        );

        String article_title = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                article_title,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id, org.wikipedia:id/page_list_item_container)]//*[contains(@text, 'Object-oriented programming language')]"),
                "Cannot find article with text 'Object-oriented programming language'",
                5
        );

        assertElementPresent(
                "Cannot find article title"
                By.xpath("org.wikipedia:id/view_page_title_text")
        );
    }










    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private void assertElementHasText(By by, String expected_text, String error_message, long timeoutInSeconds){
        WebElement text_element = waitForElementPresent(by, error_message, timeoutInSeconds);
        String actual_text = text_element.getAttribute("text");

        Assert.assertEquals(
                error_message,
                expected_text,
                actual_text
        );
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    //Method compares number of all search results with number of results containing some text
    private void assertAllSearchResultsContainSomeText(By by1, By by2, String error_message){

        List<WebElement> listOfAllResults = driver.findElements(by1);
        int number_of_all_results = listOfAllResults.size();

        List<WebElement> listOfSearchResults = driver.findElements(by2);
        int number_of_results_with_text = listOfSearchResults.size();

        Assert.assertEquals(
                error_message,
                number_of_all_results,
                number_of_results_with_text
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void waitForMenuToRender(By by1, By by2, By by3, By by4, By by5, By by6, String error_message, long timeOutInSeconds){
        waitForElementPresent(by1, error_message + "1", timeOutInSeconds);
        waitForElementPresent(by2, error_message + "2", timeOutInSeconds);
        waitForElementPresent(by3, error_message + "3", timeOutInSeconds);
        waitForElementPresent(by4, error_message + "4", timeOutInSeconds);
        waitForElementPresent(by5, error_message + "5", timeOutInSeconds);
        waitForElementPresent(by6, error_message + "6", timeOutInSeconds);
    }

    protected void swipeElementToLeft (By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();
    }

    private void assertElementPresent(By by, String error_message){
        List elements = driver.findElements(by);
        int number_of_elements = elements.size();

        Assert.assertTrue(
                error_message,
                number_of_elements == 1
        );
    }

}