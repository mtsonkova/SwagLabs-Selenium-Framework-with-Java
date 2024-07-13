package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutOverviewPage {
    private WebDriver driver;

    @FindBy(className = "cart_item_label")
    private List<WebElement> cartItems;


    @FindBy(className = "summary_total_label")
    private WebElement totalPrice;

    @FindBy(id = "cancel")
    private WebElement btn_Cancel;

    @FindBy(id = "finish")
    private WebElement btn_Finish;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public Double getTotalPrice() {
        String midPrice = totalPrice.getText();
        Double totalPrice = Double.parseDouble(midPrice.substring(8));
        return totalPrice;
    }

    public void clickOnCancelButton() {
        btn_Cancel.click();
    }

    public OrderCompletedPage clickOnFinishButton() {
        btn_Finish.click();
        return new OrderCompletedPage(driver);
    }
}

