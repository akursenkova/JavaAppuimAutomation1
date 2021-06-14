import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class HomeWork extends CoreTestCase {

    //Ex 2
    @Test
    public void testSearchInputContainsText(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.assertElementHasText();
    }


    //Ex 3
    @Test
    public void testClearSearchInput(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultsAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultsDisappear();
    }


    //Ex 4
    @Test
    public void testSearchResultsContainText(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String searched_text = "Java";
        SearchPageObject.typeSearchLine(searched_text);
        SearchPageObject.waitForSearchResult(searched_text);
        SearchPageObject.compareSearchResults(searched_text);
    }


    //Ex 5. Added adaptation for iOS
    @Test
    public void testSaveArticlesToList(){

        String first_article = "Object-oriented programming language";
        String first_article_title = "Java (programming language)";
        String second_article = "Island of Indonesia";
        String second_article_ios = "Indonesian island";
        String second_article_title = "Java";
        String name_of_folder = "Learning programming";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring(first_article);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        if (Platform.getInstance().isAndroid()){
            SearchPageObject.clickByArticleWithSubstring(second_article);
        } else {
            SearchPageObject.clickByArticleWithSubstring(second_article_ios);
        }

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToExistingList(name_of_folder);
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isIOS()){
            MyListsPageObject.closeAlertSyncArticles();
        } else {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(first_article_title);

        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.clickArticleAndCompareTitle(second_article_title);
        } else {
            MyListsPageObject.waitForArticleToAppear(second_article_title);
        }

    }


    //Ex 6
    @Test //this test will be failed, because article title doesn't have time to load
    public void testAssertTitle(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertTitlePresent();
    }

}