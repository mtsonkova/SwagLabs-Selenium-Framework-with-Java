package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    WebElement cartPageTitle;

    @FindBy(className = "cart_item")
    List<WebElement> productsInCart;

    @FindBy(id = "checkout")
    WebElement btnCheckout;

    @FindBy(id = "continue-shopping")
    WebElement btnContinueShopping;

    @FindBy(className = "cart_button")
    WebElement btnRemove;

    public String getCartPageTitle() {
        return cartPageTitle.getText();
    }

    public void clickOnContinueShoppingBtn() {
        btnContinueShopping.click();
    }

    public CheckoutInformationPage clickOnCheckoutBtn() {
        btnCheckout.click();
        return new CheckoutInformationPage(driver);
    }

    public String getCartProductName(WebElement element) {
        return element.getText();
    }

    public void clickOnRemoveBtn(WebElement element) {
        //element.findElement(By.tagName("button")).click();
        btnRemove.click();
    }

    public WebElement getBtnRemove() {
        return btnRemove;
    }

    public List<WebElement> getAllProductsFromCart() {
        return productsInCart;
    }

    public void removeProductFromTheCartByName(String productName) {
        List<WebElement> cartProducts = getAllProductsFromCart();
        WebElement product = cartProducts
                .stream()
                .filter(cartProduct -> getCartProductName(cartProduct).equals(productName))
                .findFirst()
                .orElse(null);
        clickOnRemoveBtn(product);
    }

    public void removeProductFromTheCartByIndex(int index) {
        List<WebElement> cartProducts = getAllProductsFromCart();
        int size = cartProducts.size();
        if (index >= 0 && index < size) {
            WebElement currentProduct = cartProducts.get(index);
            String name = currentProduct.getText();
            clickOnRemoveBtn(currentProduct);
        }

    }

    public void removeFirstProductFromTheCart() {
        List<WebElement> cartProducts = getAllProductsFromCart();
        WebElement firstProduct = cartProducts.getFirst();
        String name = firstProduct.getText();
        clickOnRemoveBtn(firstProduct);
    }

    public void removeLastProductFromTheCart() {
        List<WebElement> cartProducts = getAllProductsFromCart();
        WebElement lastProduct = cartProducts.getLast();
        String name = lastProduct.getText();
        clickOnRemoveBtn(lastProduct);
    }
}

