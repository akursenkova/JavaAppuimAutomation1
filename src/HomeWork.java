import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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

}