package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {
    private WebDriver driver;

    @FindBy(tagName = "img")
    WebElement productPicture;

    @FindBy(className = "inventory_details_name")
    WebElement name;

    @FindBy(className = "inventory_details_desc")
    WebElement description;

    @FindBy(className = "inventory_details_price")
    WebElement priceString;

    @FindBy(className = "btn_inventory")
    WebElement button;

    @FindBy(className = "inventory_details_img")
    WebElement image;

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return name.getText();
    }

    public String getProductDescription() {
        return description.getText();
    }

    public double getProductPrice() {
        double price = Double.parseDouble(priceString.getText().substring(1));
        return price;
    }

    public String getBtnText() {
        return button.getText();
    }

    public void clickOnTheButton() {
        button.click();
    }

    public WebElement getImage() {
        return image;
    }
}
