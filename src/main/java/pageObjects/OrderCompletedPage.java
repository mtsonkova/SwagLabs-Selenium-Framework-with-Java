package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletedPage {

    private WebDriver driver;

    public OrderCompletedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h2")
    WebElement thankYouHeader;

    @FindBy(className = "complete-text")
    WebElement completeTextPlaceholder;

    @FindBy(id = "back-to-products")
    WebElement btnBackHome;

    public String getHeaderText() {
        return thankYouHeader.getText();
    }

    public String getPurchaseCompleteText() {
        return completeTextPlaceholder.getText();
    }

    public void clickOnBackHomeBtn() {
        btnBackHome.click();
    }
}
