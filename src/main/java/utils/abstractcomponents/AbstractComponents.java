package utils.abstractcomponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.pageobjects.CartPage;
import utils.pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponents {
    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*=cart]")
    WebElement submitCartButton;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement submitOrdersButton;

    public CartPage goToCart() throws InterruptedException {
        Thread.sleep(5000);
        submitCartButton.click();
        return new CartPage(driver);
    }

    public OrderPage goToOrders() throws InterruptedException {
        Thread.sleep(3000);
        submitOrdersButton.click();
        return new OrderPage(driver);
    }

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForElementToAppear(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
