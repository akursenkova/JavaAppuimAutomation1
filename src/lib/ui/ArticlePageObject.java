package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",

        OPTIONS_CHANGE_LANGUAGE = "//*[@text='Change language']",
        OPTIONS_SHARE_LINK = "//*[@text='Share link']",
        OPTIONS_FIND_IN_PAGE = "//*[@text='Find in page']",
        OPTIONS_SIMILAR_PAGES = "//*[@text='Similar pages']",
        OPTIONS_ADD_TO_MY_LIST = "//*[@text='Add to reading list']",
        OPTIONS_FONT_AND_THEME = "//*[@text='Font and theme']",

        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']";


    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementPresent(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForMenuToRender(
                By.xpath(OPTIONS_CHANGE_LANGUAGE),
                By.xpath(OPTIONS_SHARE_LINK),
                By.xpath(OPTIONS_ADD_TO_MY_LIST),
                By.xpath(OPTIONS_FIND_IN_PAGE),
                By.xpath(OPTIONS_SIMILAR_PAGES),
                By.xpath(OPTIONS_FONT_AND_THEME),
                "Cannot find in menu option number ",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST),
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle(){
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                5
        );
    }

    public void addArticleToExistingList(String name_of_folder){
        this.waitForElementPresent(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForMenuToRender(
                By.xpath(OPTIONS_CHANGE_LANGUAGE),
                By.xpath(OPTIONS_SHARE_LINK),
                By.xpath(OPTIONS_ADD_TO_MY_LIST),
                By.xpath(OPTIONS_FIND_IN_PAGE),
                By.xpath(OPTIONS_SIMILAR_PAGES),
                By.xpath(OPTIONS_FONT_AND_THEME),
                "Cannot find in menu option number ",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST),
                "Cannot find option to add article to reading list",
                5
        );

        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find created folder to add article",
                5
        );
    }
}
