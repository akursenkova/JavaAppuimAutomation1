package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject{

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULTS = "org.wikipedia:id/page_list_item_container",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[contains(@resource-id, 'org.wikipedia:id/page_list_item_container')]//*[contains(@text, '{SUBSTRING}')]";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input after clicking search init element");
    }

    public void assertElementHasText(){
        String expected_text = "Search…";
        String error_message = "Cannot find search input with text 'Search…'";
        WebElement text_element = waitForElementPresent(By.xpath(SEARCH_INPUT), error_message, 5);
        String actual_text = text_element.getAttribute("text");

        Assert.assertEquals(
                error_message,
                expected_text,
                actual_text
        );
    }

    public void waitForCancelButtonAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void waitForSearchResultsAppear(){
        this.waitForElementPresent(By.id(SEARCH_RESULTS), "Cannot find search results", 15);
    }

    public void waitForSearchResultsDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_RESULTS), "Still find search results", 15);
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public void compareSearchResults(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.assertAllSearchResultsContainSomeText(
                By.id(SEARCH_RESULTS),
                By.xpath(search_result_xpath),
                "Number of results with text '" + substring + "' doesn't equals number of all search results"
        );
    }
}
