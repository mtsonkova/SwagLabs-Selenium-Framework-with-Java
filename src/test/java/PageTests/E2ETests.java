package PageTests;

import base.BaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

import java.time.Duration;
import java.util.List;

public class E2ETests extends BaseTest {


    By storeItems = By.className("inventory_item_name");
    private LoginPage loginPage;
    private String firstName = "Angela";
    private String lastName = "Ramirez";
    private String postCode = "3000";

    private JSONObject jsonObject = getJsonObject();

    @BeforeMethod
    public void setTest() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void PurchaseAllProductsAndCheckOrder() {
        ProductsPage productsPage = loginPage.validLogin();
        productsPage.AddAllProductsToTheShoppingCart();
        CartPage shoppingCart = productsPage.clickOnTheShoppingCart();
        CheckoutInformationPage checkoutInformationPage = shoppingCart.clickOnCheckoutBtn();
        checkoutInformationPage.enterInformationData(firstName, lastName, postCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickOnContinueBtn();
        OrderCompletedPage orderCompletedPage = checkoutOverviewPage.clickOnFinishButton();
        String header = orderCompletedPage.getHeaderText();
        Assert.assertTrue(header.contains("Thank you"));
    }

    @Test(dataProvider = "products")
    public void PurchaseMultipleProductsAndCheckOrder(JSONArray products) {
        ProductsPage productsPage = loginPage.validLogin();
        productsPage.purchaseMultipleProducts(products);
        CartPage shoppingCart = productsPage.clickOnTheShoppingCart();
        CheckoutInformationPage checkoutInformationPage = shoppingCart.clickOnCheckoutBtn();
        checkoutInformationPage.enterInformationData(firstName, lastName, postCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickOnContinueBtn();
        OrderCompletedPage orderCompletedPage = checkoutOverviewPage.clickOnFinishButton();
        String header = orderCompletedPage.getHeaderText();
        Assert.assertTrue(header.contains("Thank you"));
    }

    @Test
    public void performMultipleProductsFiltersAndPurchaseMultipleProducts() {
        ProductsPage productsPage = loginPage.validLogin();
        productsPage.sortProductsByNameFromZtoA();
        String firstProduct = productsPage.getFirstProduct();
        productsPage.AddProductInTheShoppingCartByName(firstProduct);
        productsPage.sortProductsByPriceHighToLow();
        String product = productsPage.getProductByItsIndex(3);
        productsPage.AddProductInTheShoppingCartByName(product);
        CartPage shoppingCart = productsPage.clickOnTheShoppingCart();
        shoppingCart.clickOnContinueShoppingBtn();
        String lastProduct = productsPage.getLastProduct();
        productsPage.AddProductInTheShoppingCartByName(lastProduct);
        productsPage.clickOnTheShoppingCart();
        CheckoutInformationPage checkoutInformationPage = shoppingCart.clickOnCheckoutBtn();
        checkoutInformationPage.enterInformationData(firstName, lastName, postCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickOnContinueBtn();
        OrderCompletedPage orderCompletedPage = checkoutOverviewPage.clickOnFinishButton();
        orderCompletedPage.clickOnBackHomeBtn();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(currentUrl, expectedUrl);
    }

    //TODO
    // solve removeLastProductFromTheCart
    // solve removeProductFromTheCartByIndex

    @Test
    public void purchaseMultipleProductsAndRemoveProductsFromCart() {
        ProductsPage productsPage = loginPage.validLogin();
        productsPage.AddAllProductsToTheShoppingCart();
        CartPage shoppingCart = productsPage.clickOnTheShoppingCart();
        shoppingCart.removeFirstProductFromTheCart();
        shoppingCart.removeLastProductFromTheCart();
        shoppingCart.removeProductFromTheCartByIndex(2);
        CheckoutInformationPage checkoutInformationPage = shoppingCart.clickOnCheckoutBtn();
        checkoutInformationPage.enterInformationData(firstName, lastName, postCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickOnContinueBtn();
        OrderCompletedPage orderCompletedPage = checkoutOverviewPage.clickOnFinishButton();
        String header = orderCompletedPage.getHeaderText();
        Assert.assertTrue(header.contains("Thank you"));
    }
}

