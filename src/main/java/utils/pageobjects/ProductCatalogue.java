package utils.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.abstractcomponents.AbstractComponents;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".col-lg-4")
    List<WebElement> products;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }
    public WebElement productSearch(String productName) {
        return getProductList().stream()
                .filter(e -> e.findElement(By.cssSelector("b"))
                        .getText()
                        .equals(productName))
                .findFirst().orElseThrow(null);
    }

    public void addToCart(String productName) {
        WebElement neededProductToCart = productSearch(productName);
        neededProductToCart.findElement(addToCart).click();
    }
}
