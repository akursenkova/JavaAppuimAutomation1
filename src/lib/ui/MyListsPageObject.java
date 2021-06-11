package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListsPageObject extends MainPageObject{

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']",
            ARTICLE_TITLE = "org.wikipedia:id/view_page_title_text";

    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    public void openFolderByName(String name_of_folder){
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresent(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );

        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot click folder " + name_of_folder,
                5
        );
    }

    public void swipeByArticleToDelete(String article_title){
        this.waitForArticleToAppear(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
        this.waitForArticleToDisappear(article_title);
    }

    public void waitForArticleToDisappear(String article_title){
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void waitForArticleToAppear(String article_title){
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void clickArticleByTitle(String article_title){
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        waitForElementPresent(
                article_xpath,
                "Cannot find article " + article_title,
                5
        );

        waitForElementAndClick(
                article_xpath,
                "Cannot find article " + article_title,
                5
        );

    }

    public void compareArticleTitle(String article_title){

        WebElement title_element = waitForElementPresent(
                ARTICLE_TITLE,
                "Cannot find article title",
                15
        );

        String actual_title_name = title_element.getAttribute("text");

        Assert.assertEquals(
                "Title of opened article doesn't equals " + article_title,
                actual_title_name,
                article_title
        );
    }

    public void clickArticleAndCompareTitle(String article_title){
        clickArticleByTitle(article_title);
        compareArticleTitle(article_title);
    }
}
