package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,

        OPTIONS_CHANGE_LANGUAGE,
        OPTIONS_SHARE_LINK,
        OPTIONS_FIND_IN_PAGE,
        OPTIONS_SIMILAR_PAGES,
        OPTIONS_ADD_TO_MY_LIST,
        OPTIONS_FONT_AND_THEME,

        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        FOLDER_BY_NAME_TPL;


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
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.swipeUpTitleElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
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

    public void addArticleToMySaved(){
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST, "Cannot find option to add article to my list", 5);
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
