package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import testcomponents.BaseTest;
import testcomponents.Retry;
import utils.pageobjects.CartPage;
import utils.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() {

        landingPage.loginApp("mam96@abv.bg", "Manolov13*()");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test(groups = {"ErrorHandling"})
    public void productErrorValidation() throws InterruptedException {

        String neededProductName = "IPHONE 13 PRO";
        ProductCatalogue productCatalogue = landingPage.loginApp("mam96@abv.bg", "Manolov123*()");
        productCatalogue.addToCart(neededProductName);
        CartPage cartPage = productCatalogue.goToCart();

        boolean isMatch = cartPage.verifyDisplayedProduct("IPHONE 13 PO");
        Assert.assertFalse(isMatch);

    }
}
