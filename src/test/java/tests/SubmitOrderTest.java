package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testcomponents.BaseTest;
import utils.pageobjects.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String neededProductName = "IPHONE 13 PRO";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));

        productCatalogue.addToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCart();

        boolean isMatch = cartPage.verifyDisplayedProduct(input.get("product"));
        Assert.assertTrue(isMatch);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Bulgaria");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMassage = confirmationPage.getConfirmMassage();
        Assert.assertTrue(confirmMassage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTests() throws InterruptedException {

        OrderPage orderPage = landingPage.loginApp("mam96@abv.bg", "Manolov123*()")
                .goToOrders();
        Assert.assertTrue(orderPage.verifyOrders(neededProductName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonToMap(System.getProperty("user.dir")
                + "\\src\\main\\java\\resources\\purchase.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}, {data.get(2)}};
    }

}

