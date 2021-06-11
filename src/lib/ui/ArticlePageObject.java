package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLE = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",

        OPTIONS_CHANGE_LANGUAGE = "xpath://*[@text='Change language']",
        OPTIONS_SHARE_LINK = "xpath://*[@text='Share link']",
        OPTIONS_FIND_IN_PAGE = "xpath://*[@text='Find in page']",
        OPTIONS_SIMILAR_PAGES = "xpath://*[@text='Similar pages']",
        OPTIONS_ADD_TO_MY_LIST = "xpath://*[@text='Add to reading list']",
        OPTIONS_FONT_AND_THEME = "xpath://*[@text='Font and theme']",

        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";


    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementPresent(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForMenuToRender(
                OPTIONS_CHANGE_LANGUAGE,
                OPTIONS_SHARE_LINK,
                OPTIONS_ADD_TO_MY_LIST,
                OPTIONS_FIND_IN_PAGE,
                OPTIONS_SIMILAR_PAGES,
                OPTIONS_FONT_AND_THEME,
                "Cannot find in menu option number ",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle(){
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }

    public void addArticleToExistingList(String name_of_folder){
        this.waitForElementPresent(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForMenuToRender(
                OPTIONS_CHANGE_LANGUAGE,
                OPTIONS_SHARE_LINK,
                OPTIONS_ADD_TO_MY_LIST,
                OPTIONS_FIND_IN_PAGE,
                OPTIONS_SIMILAR_PAGES,
                OPTIONS_FONT_AND_THEME,
                "Cannot find in menu option number ",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find option to add article to reading list",
                5
        );

        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        waitForElementAndClick(
                folder_name_xpath,
                "Cannot find created folder to add article",
                5
        );
    }

    public void assertTitlePresent(){
        By title = this.getLocatorByString(TITLE);
        List elements = driver.findElements(title);
        int number_of_elements = elements.size();

        Assert.assertTrue(
                "Cannot find article title",
                number_of_elements == 1
        );
    }
}
