package net.mobile.testing.demo.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import net.mobile.testing.demo.reusable.ReusableOperations;

import java.util.List;

public class ProductsPage {
    AndroidDriver<AndroidElement> driver;

    public ProductsPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public void selectProduct(String productName) {
        ReusableOperations.scrollTo(productName,driver);
        List<AndroidElement> products = driver.findElementsById("com.androidsample.generalstore:id/productName");

        for(int i=0; i<products.size();i++) {
            if(products.get(i).getText().equalsIgnoreCase(productName)) {
                driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i).click();
                break;
            }
        }
    }

    public void goToCart() {
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
    }
}
