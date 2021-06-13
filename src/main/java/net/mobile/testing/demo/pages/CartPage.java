package net.mobile.testing.demo.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.List;

public class CartPage {
    AndroidDriver<AndroidElement> driver;

    public CartPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public String readTotalAmount() {
        return driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
    }

    public Double readAndSumEachProductPrice() {
        List<AndroidElement> productPrices = driver.findElementsById("com.androidsample.generalstore:id/productPrice");

        double sumOfProductPrices = 0;

        System.out.println(productPrices.size());
        for (int i = 0; i < productPrices.size(); i++) {
            List<AndroidElement> productPricess = driver.findElementsById("com.androidsample.generalstore:id/productPrice");
            System.out.println(productPricess.get(i).getText());
            System.out.println(productPricess.get(i).getText().replace("$", ""));

            sumOfProductPrices += Double.parseDouble(productPricess.get(i).getText().replace("$", ""));
        }
        return sumOfProductPrices;
    }
}
