import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;


public class HomeWork extends CoreTestCase {

    //Ex 2
    @Test
    public void testSearchInputContainsText(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
    }


    //Ex 3
    @Test
    public void testClearSearchInput(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultsAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultsDisappear();
    }


    //Ex 4
    @Test
    public void testSearchResultsContainText(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String searched_text = "Java";
        SearchPageObject.typeSearchLine(searched_text);
        SearchPageObject.compareSearchResults(searched_text);
    }


    //Ex 5
    @Test
    public void testSaveArticlesToList(){

        String first_article = "Object-oriented programming language";
        String first_article_title = "Java (programming language)";
        String second_article = "Island of Indonesia";
        String second_article_title = "Java";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring(first_article);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring(second_article);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToExistingList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.clickArticleByTitle(second_article_title);
        MyListsPageObject.clickArticleAndCompareTitle(second_article_title);
    }


    //Ex 6
    @Test //this test will be failed, because article title doesn't have time to load
    public void testAssertTitle(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertTitlePresent();
    }

}