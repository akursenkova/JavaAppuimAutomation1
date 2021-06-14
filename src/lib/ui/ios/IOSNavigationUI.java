package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = "id:Saved";
    }

    public IOSNavigationUI (AppiumDriver driver){
        super(driver);
    }
}
