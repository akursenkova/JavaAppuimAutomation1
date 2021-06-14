package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        CLOSE_ALERT_BUTTON = "id:Close";
        DELETE_ARTICLE_BUTTON = "id:swipe action delete";
        //ARTICLE_TITLE = "org.wikipedia:id/view_page_title_text";
    }

    public IOSMyListsPageObject (AppiumDriver driver){
        super(driver);
    }
}
