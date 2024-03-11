package utils.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.abstractcomponents.AbstractComponents;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button")
    WebElement checkoutBut;
    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    public boolean verifyDisplayedProduct(String productName) {
        return cartProducts.stream().anyMatch(e -> e.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout() {
        checkoutBut.click();
        return new CheckoutPage(driver);
    }
}
