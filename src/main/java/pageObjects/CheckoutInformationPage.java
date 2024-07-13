package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage {
    private WebDriver driver;

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    WebElement firstNamePlaceholder;

    @FindBy(id = "last-name")
    WebElement lastNamePlaceholder;

    @FindBy(id = "postal-code")
    WebElement postalCodePlaceholder;

    @FindBy(css = "div.error-message-container h3")
    WebElement errorMsgContainer;

    @FindBy(id = "cancel")
    WebElement btnCancel;

    @FindBy(id = "continue")
    WebElement btnContinue;

    public void enterInformationData(String firstName, String lastName, String postCode) {
        firstNamePlaceholder.sendKeys(firstName);
        lastNamePlaceholder.sendKeys(lastName);
        postalCodePlaceholder.sendKeys(postCode);
    }

    public void clickOnCancelBtn() {
        btnCancel.click();
    }

    public CheckoutOverviewPage clickOnContinueBtn() {
        btnContinue.click();
        return new CheckoutOverviewPage(driver);
    }

    public String getErrMsgText() {
        return errorMsgContainer.getText();
    }
}
