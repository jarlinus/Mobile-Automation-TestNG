package net.mobile.testing.demo.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import net.mobile.testing.demo.reusable.ReusableOperations;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    AndroidDriver<AndroidElement> driver;

    public HomePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }


    public void enterYourName() {
        driver.findElementByAndroidUIAutomator("text(\"Enter name here\")").sendKeys("Hello");
        ReusableOperations.hideKeyBoard(driver);
    }

    public void selectGender() {
        driver.findElementByAndroidUIAutomator("text(\"Female\")").click();
    }

    public void clickCountry(String country) {
        driver.findElementByAndroidUIAutomator("text(\"" + country + "\")").click();
    }

    public void scrollTo(String country) {
        ReusableOperations.scrollTo(country, driver);
    }

    public void selectCountry(String country) {
        clickCountry("Afghanistan");
        scrollTo(country);
        driver.findElementByAndroidUIAutomator("text(\"" + country + "\")").click();
    }

    public void clickLetsShop() {
        driver.findElementByAndroidUIAutomator("text(\"Let's  Shop\")").click();
    }
}
