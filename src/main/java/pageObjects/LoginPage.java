package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "user-name")
    WebElement userNamePlaceholder;

    @FindBy(id = "password")
    WebElement passwordPlaceholder;

    @FindBy(id = "login-button")
    WebElement btn_Login;

    @FindBy(css = "div h3")
    WebElement errMsgContainer;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String userName) {
        userNamePlaceholder.sendKeys(userName);
    }

    public void enterPassword(String password) {
        passwordPlaceholder.sendKeys(password);
    }

    public void clickLoginButton() {
        btn_Login.click();
    }

    public String getErrorMessage() {
        String errorMessage = errMsgContainer.getText();
        return errorMessage;
    }

    public ProductsPage validLogin() {
        enterUserName("standard_user");
        enterPassword("secret_sauce");
        clickLoginButton();
        return new ProductsPage(driver);
    }
}
