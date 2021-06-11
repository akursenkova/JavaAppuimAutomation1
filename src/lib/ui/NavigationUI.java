package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final String
            MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";


    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void clickMyList(){
        this.waitForElementPresent(
                MY_LISTS_LINK,
                "Cannot find navigation button to My Lists",
                5
        );

        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My Lists",
                5
        );
    }

}
