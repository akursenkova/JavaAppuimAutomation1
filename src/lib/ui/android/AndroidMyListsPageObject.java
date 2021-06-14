package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
        ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text";
    }

    public AndroidMyListsPageObject (AppiumDriver driver){
        super(driver);
    }
}
