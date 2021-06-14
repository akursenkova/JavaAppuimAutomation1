package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
                TITLE = "id:org.wikipedia:id/view_page_title_text";
                FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
                OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";

                OPTIONS_CHANGE_LANGUAGE = "xpath://*[@text='Change language']";
                OPTIONS_SHARE_LINK = "xpath://*[@text='Share link']";
                OPTIONS_FIND_IN_PAGE = "xpath://*[@text='Find in page']";
                OPTIONS_SIMILAR_PAGES = "xpath://*[@text='Similar pages']";
                OPTIONS_ADD_TO_MY_LIST = "xpath://*[@text='Add to reading list']";
                OPTIONS_FONT_AND_THEME = "xpath://*[@text='Font and theme']";

                ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
                MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
                MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
                CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
    }

    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
