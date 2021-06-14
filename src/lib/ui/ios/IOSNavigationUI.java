package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = "xpath://XCUIElementTypeButton[@name='Saved']";
    }

    public IOSNavigationUI (AppiumDriver driver){
        super(driver);
    }
}
