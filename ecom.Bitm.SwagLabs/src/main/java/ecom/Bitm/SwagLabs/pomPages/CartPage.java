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
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("title"), "Your Cart"));
        String actualTitle = driver.findElement(By.className("title")).getText();
        Assert.assertEquals(actualTitle, "Your Cart");
        System.out.println("Cart page opened successfully");
    }

    public void verifyCartBadgeCount(String expectedCount) {
    if (expectedCount == null || expectedCount.isEmpty()) {
        wait.until(d -> !isCartBadgeDisplayed());
        Assert.assertFalse(isCartBadgeDisplayed(), "Cart badge should not be displayed");
        return;
    }

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"), expectedCount));

    Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), expectedCount);
    System.out.println("Cart badge count: " + expectedCount);
}

    public boolean isCartBadgeDisplayed() {
        List<WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));
        return !badges.isEmpty() && badges.get(0).isDisplayed();
    }

    public int getCartItemCount() {
    wait.until(ExpectedConditions.urlContains("cart.html"));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));

    return wait.until(d -> {
        List<WebElement> items = d.findElements(By.className("cart_item"));
        return items.size();
    });
}

    public void verifyProductInCart(String productName) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));

    WebElement item = wait.until(d -> getCartItem(productName));
    Assert.assertNotNull(item, "Product not found in cart: " + productName);

    String actualName = item.findElement(By.className("inventory_item_name")).getText();
    Assert.assertEquals(actualName, productName);

    System.out.println(productName + " is present");
}

    public boolean isProductInCart(String productName) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));

    for (WebElement item : driver.findElements(By.className("cart_item"))) {
        String name = item.findElement(By.className("inventory_item_name")).getText();

        if (name.equals(productName)) {
            return true;
        }
    }

    return false;
}

    public void removeProduct(String productName) {
    WebElement item = wait.until(d -> getCartItem(productName));

    Assert.assertNotNull(item, "Product not found in cart: " + productName);

    wait.until(ExpectedConditions.elementToBeClickable(item.findElement(By.tagName("button")))).click();

    wait.until(d -> !isProductInCart(productName));

    System.out.println(productName + " removed from cart");
}

    public void verifyProductRemoved(String productName) {
    wait.until(d -> !isProductInCart(productName));

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
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));

    for (WebElement product : driver.findElements(By.xpath("//div[@class='inventory_item_name']"))) {
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
