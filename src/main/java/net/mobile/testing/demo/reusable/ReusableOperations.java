package net.mobile.testing.demo.reusable;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ReusableOperations {
    public static void hideKeyBoard(AndroidDriver<AndroidElement> driver) {
        driver.hideKeyboard();
    }

    public static void scrollTo(String country, AndroidDriver<AndroidElement> driver) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""+country+"\").instance(0))"));
    }
}
