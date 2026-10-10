package ecom.Bitm.SwagLabs.pomPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ecom.Bitm.SwagLabs.Utility.ExcelUtility;

import java.io.IOException;
import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public void openCart() {
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));
        String actualTitle = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(actualTitle, "Your Cart");
        System.out.println("Cart page opened successfully");
    }

    public void verifyCartBadgeCount(String expectedCount) {
        if (expectedCount == null || expectedCount.isEmpty()) {
            Assert.assertFalse(isCartBadgeDisplayed(), "Cart badge should not be displayed");
            return;
        }
        wait.until(ExpectedConditions.visibilityOf(cartBadge));
        Assert.assertEquals(cartBadge.getText(), expectedCount);
        System.out.println("Cart badge count: " + expectedCount);
    }

    public boolean isCartBadgeDisplayed() {
        List<WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));
        return !badges.isEmpty() && badges.get(0).isDisplayed();
    }

    public int getCartItemCount() {
        return driver.findElements(By.className("cart_item")).size();
    }

    public void verifyProductInCart(String productName) {
        WebElement item = getCartItem(productName);
        Assert.assertNotNull(item, "Product not found in cart: " + productName);
        String actualName = item.findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(actualName, productName);
        System.out.println(productName + " is present");
    }

    public boolean isProductInCart(String productName) {
        for (WebElement item : driver.findElements(By.className("cart_item"))) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void removeProduct(String productName) {
        WebElement item = getCartItem(productName);
        Assert.assertNotNull(item, "Product not found in cart: " + productName);
        item.findElement(By.tagName("button")).click();
        wait.until(d -> !isProductInCart(productName));
        System.out.println(productName + " removed from cart");
    }

    public void verifyProductRemoved(String productName) {
        Assert.assertFalse(isProductInCart(productName), productName + " is still present in cart");
        System.out.println(productName + " is removed successfully");
    }

    public void continueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        System.out.println("Returned to Products page");
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
        System.out.println("Checkout page opened successfully");
    }

    private WebElement getCartItem(String productName) {
        for (WebElement item : driver.findElements(By.className("cart_item"))) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(productName)) {
                return item;
            }
        }
        return null;
    }
    
    public void verifyProductNotInCart(String productName) {
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        for (WebElement product : products) {
            if (product.getText().equals(productName)) {
                Assert.fail("Product is still present in the cart: " + productName);
            }
        }
    }
    
    public String backpack() throws IOException {
        return ExcelUtility.getData("Sheet2", 0, 0);
    }

    public String bikeLight() throws IOException {
        return ExcelUtility.getData("Sheet2", 0, 1);
    }
    
}
