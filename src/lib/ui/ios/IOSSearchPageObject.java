package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
        SEARCH_RESULTS = "id:org.wikipedia:id/page_list_item_container";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT = "id:No results found";
        //SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_BY_TWO_SUBSTRINGS_TPL = "xpath://*[contains(@resource-id, 'org.wikipedia:id/page_list_item_container')]//*[contains(@text, '{TITLE}')]/../*[contains(@text, '{DESCRIPTION}')]";
        SEARCH_RESULT_BY_TWO_SUBSTRINGS_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{TITLE}')]/../XCUIElementTypeStaticText[contains(@name, '{DESCRIPTION}')]";
    }

    public IOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }

}
