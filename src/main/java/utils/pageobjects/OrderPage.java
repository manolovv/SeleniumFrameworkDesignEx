package utils.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.abstractcomponents.AbstractComponents;

import java.util.List;

public class OrderPage extends AbstractComponents {
    WebDriver driver;

    @FindBy(css = ".ng-star-inserted td:nth-child(3)")
    List<WebElement> productNames;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyOrders(String productName) {
        return productNames.stream().anyMatch(e -> e.getText().equalsIgnoreCase(productName));
    }

}
