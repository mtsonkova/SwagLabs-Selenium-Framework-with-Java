package pageObjects;

import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebElement btnAdd;
    private WebElement btnRemove;

    @FindBy(className = "title")
    WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    WebElement sort;

    private Select productsSorter;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCart;

    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(className = "inventory_item_description")
    List<WebElement> inventoryItemsDescription;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductsPageTitle() {
        return pageTitle.getText();
    }

    public void sortProductsByNameFromAtoZ() {
        productsSorter = new Select(sort);
        productsSorter.selectByVisibleText("Name (A to Z)");
    }

    public void sortProductsByNameFromZtoA() {
        productsSorter = new Select(sort);
        productsSorter.selectByVisibleText("Name (Z to A)");
    }

    public void sortProductsByPriceLowToHigh() {
        productsSorter = new Select(sort);
        productsSorter.selectByVisibleText("Price (low to high)");
    }

    public void sortProductsByPriceHighToLow() {
        productsSorter = new Select(sort);
        productsSorter.selectByVisibleText("Price (high to low)");
    }

    public List<WebElement> getAllInventoryItemsDescription() {
        return inventoryItemsDescription;
    }

    private void clickOnAddBtn(WebElement item) {
        btnAdd = item.findElement(By.className("btn_inventory"));

        if (btnAdd.getText().equalsIgnoreCase("add to cart")) {
            btnAdd.click();
        }
    }

    private void clickOnRemoveBtn(WebElement item) {
        btnRemove = item.findElement(By.className("btn_inventory"));

        if (btnRemove.getText().equalsIgnoreCase("Remove")) {
            btnRemove.click();
        }
    }

    public void AddAllProductsToTheShoppingCart() {

        List<WebElement> allProducts = getAllInventoryItemsDescription();
        for (WebElement item : allProducts) {
            clickOnAddBtn(item);
        }
    }

    public void RemoveAllProductsFromTheShoppingCart() {

        for (WebElement item : inventoryItemsDescription) {
            clickOnRemoveBtn(item);
        }
    }

    public void AddProductInTheShoppingCartByName(String productName) {
        for (WebElement item : inventoryItemsDescription) {
            String itemName = item.findElement(By.className("inventory_item_name")).getText();

            if (itemName.equalsIgnoreCase(productName)) {
                clickOnAddBtn(item);
                break;
            }
        }
    }

    public boolean CheckIfAddBtnChangesToRemoveForProductAddedinCart(String productName) {
        boolean isRemoveBtnDisplayed = false;
        for (WebElement item : inventoryItemsDescription) {
            String itemName = item.findElement(By.className("inventory_item_name")).getText();

            if (itemName.equalsIgnoreCase(productName)) {
                String inventoryBtnText = item.findElement(By.className("btn_inventory")).getText();

                if (inventoryBtnText.equals("Remove")) {
                    isRemoveBtnDisplayed = true;
                    break;
                }
            }
        }
        return isRemoveBtnDisplayed;
    }

    public boolean CheckIfRemoveBtnChangesToAddForProductRemovedFromCart(String productName) {
        boolean isAddBtnDisplayed = false;
        for (WebElement item : inventoryItemsDescription) {
            String itemName = item.findElement(By.className("inventory_item_name")).getText();

            if (itemName.equalsIgnoreCase(productName)) {
                String inventoryBtnText = item.findElement(By.className("btn_inventory")).getText();

                if (inventoryBtnText.equals("Add to cart")) {
                    isAddBtnDisplayed = true;
                    break;
                }
            }
        }
        return isAddBtnDisplayed;
    }


    public void RemoveProductFromTheShoppingCartByName(String productName) {
        for (WebElement item : inventoryItemsDescription) {
            String itemName = item.findElement(By.className("inventory_item_name")).getText();

            if (itemName.equalsIgnoreCase(productName)) {
                clickOnRemoveBtn(item);
                break;
            }
        }
    }


    public void purchaseMultipleProducts(JSONArray products) {
        for (int i = 0; i < products.size(); i++) {
            var current = products.get(i);
            for (WebElement item : inventoryItemsDescription) {
                String itemName = item.findElement(By.className("inventory_item_name")).getText();

                if (itemName.equalsIgnoreCase(current.toString())) {
                    clickOnAddBtn(item);
                    break;
                }
            }
        }
    }

    public CartPage clickOnTheShoppingCart() {
        shoppingCart.click();
        return new CartPage(driver);
    }

    public int getShoppingCartBadgeValue() {
        String text = shoppingCartBadge.getText();
        int num = Integer.parseInt(text);
        return num;
    }

    public boolean checkIfShoppingCartBadgeIsDisplayed() {
        boolean isBadgeDisplayed = false;
        try {
            isBadgeDisplayed = shoppingCartBadge.isDisplayed();

        } catch (NoSuchElementException e) {

        } finally {
            return isBadgeDisplayed;
        }
    }

    public ArrayList<String> getProductNames() {
        ArrayList<String> productNames = new ArrayList<String>();

        List<WebElement> items = getAllInventoryItemsDescription();
        for (WebElement item : items) {
            String productName = item.findElement(By.className("inventory_item_name")).getText();
            productNames.add(productName);
        }
        return productNames;
    }

    public String getFirstProduct() {
        String firstProductName = getAllInventoryItemsDescription().getFirst().getText();
        return firstProductName;
    }

    public String getLastProduct() {
        String lastProductName = getAllInventoryItemsDescription().getLast().getText();
        return lastProductName;
    }

    public String getProductByItsIndex(int index) {
        String name = "";
        int size = getAllInventoryItemsDescription().size();
        if (index >= 0 && index < size) {
            name = getAllInventoryItemsDescription().get(index).getText();
        }

        return name;
    }
}
