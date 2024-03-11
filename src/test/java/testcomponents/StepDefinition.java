package testcomponents;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utils.pageobjects.*;

import java.io.IOException;

public class StepDefinition extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void i_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApp();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void login_in_username_and_password(String username, String password) {
        productCatalogue = landingPage.loginApp(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        productCatalogue.addToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) throws InterruptedException {
        CartPage cartPage = productCatalogue.goToCart();
        boolean isMatch = cartPage.verifyDisplayedProduct(productName);
        Assert.assertTrue(isMatch);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Bulgaria");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String msg) {
        String confirmMassage = confirmationPage.getConfirmMassage();
        Assert.assertTrue(confirmMassage.equalsIgnoreCase(msg));
        driver.close();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void something_message_is_displayed(String msg) {
        Assert.assertEquals(msg, landingPage.getErrorMessage());
        driver.close();
    }
}
