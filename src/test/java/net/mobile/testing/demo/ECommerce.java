package net.mobile.testing.demo;

import net.mobile.testing.demo.engine.TestContext;
import net.mobile.testing.demo.pages.CartPage;
import net.mobile.testing.demo.pages.HomePage;
import net.mobile.testing.demo.pages.ProductsPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ECommerce {

    TestContext testContext;

    public ECommerce(TestContext testContext) {
        this.testContext = testContext;
    }

    @BeforeClass
    public void testSetup() {
        testContext.getServerStart();
        testContext.getEmulator();
    }

    @AfterClass
    public void tearDown() {
        testContext.tearDown();
    }

    @Test
    public void amountTest() {
        HomePage homePage = new HomePage(testContext.getDriver());
        homePage.enterYourName();
        homePage.selectGender();
        homePage.selectCountry("Antarctica");
        homePage.clickLetsShop();

        ProductsPage productsPage = new ProductsPage(testContext.getDriver());
        productsPage.selectProduct("Air Jordan 4 Retro");
        productsPage.selectProduct("Jordan 6 Rings");

        productsPage.goToCart();

        CartPage cartPage = new CartPage(testContext.getDriver());
        String totalAmount = cartPage.readTotalAmount();
        Double sumOfProductPrices = cartPage.readAndSumEachProductPrice();
        assertEquals(totalAmount.replace("$ ", ""), Double.toString(sumOfProductPrices));
    }
}
