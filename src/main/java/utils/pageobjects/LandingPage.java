package utils.pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.abstractcomponents.AbstractComponents;


public class LandingPage extends AbstractComponents {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement loginId;

    @FindBy(id = "userPassword")
    WebElement loginPassword;

    @FindBy(id = "login")
    WebElement submitButton;

    @FindBy(css = "div[aria-label='Incorrect email or password.']")
    WebElement errorMessage;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCatalogue loginApp(String email, String password) {
        loginId.sendKeys(email);
        loginPassword.sendKeys(password);
        submitButton.click();
        return new ProductCatalogue(driver);
    }

    public String getErrorMessage() {
        waitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}
