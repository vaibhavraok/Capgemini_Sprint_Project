package ecom.Bitm.SwagLabs.pomPages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Products']")
    private WebElement productsText;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpackAddToCartBtn;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = "[class='inventory_item_img']")
    private WebElement product;

    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']")
    private WebElement clickproduct;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addtocart;

    @FindBy(css = "[class='inventory_item']")
    private List<WebElement> inventoryItems;

    @FindBy(xpath = "//span[text()='Your Cart']")
    private WebElement cartpagetitle;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuclick;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement menucancel;
    
    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement bikeLightAddToCartBtn;
    
    @FindBy(xpath = "//button[text()='Remove']")
    private WebElement remove;
    
    public void removecart() {
    wait.until(ExpectedConditions.elementToBeClickable(remove)).click();
}

    public void Visibledata() {
    wait.until(ExpectedConditions.visibilityOfAllElements(inventoryItems));

    for (WebElement i : inventoryItems) {
        wait.until(ExpectedConditions.visibilityOf(i));
        Assert.assertTrue(i.isDisplayed());
    }

    System.out.println("Product data is visible");
}

    public void productrefresh() {
    driver.navigate().refresh();

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item_img")));

    Assert.assertTrue(product.isDisplayed());
    System.out.println("User is still logged in");
}

    public void menu() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(menuclick));
        wait.until(ExpectedConditions.elementToBeClickable(menuclick)).click();

        wait.until(ExpectedConditions.visibilityOf(menucancel));
        wait.until(ExpectedConditions.elementToBeClickable(menucancel)).click();

        System.out.println("Navigation Menu successfully opens and closes");
    }

    public void getInventoryItems() {
    wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".inventory_item"), 6));

    Assert.assertEquals(driver.findElements(By.cssSelector(".inventory_item")).size(), 6);
    System.out.println("Every Product has cart control");
}

    public void getAddtocart() throws InterruptedException {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
    }

    public void setAddtocart(WebElement addtocart) {
        this.addtocart = addtocart;
    }

    public void getClickproduct() {
    String text = wait.until(ExpectedConditions.visibilityOf(clickproduct)).getText();

    wait.until(ExpectedConditions.elementToBeClickable(clickproduct)).click();

    Assert.assertEquals(text, "Sauce Labs Backpack");
    System.out.println("Corresponding page opened");
}

    public void getExpectedproductdata() {
    String text = wait.until(ExpectedConditions.visibilityOf(clickproduct)).getText();

    Assert.assertEquals(text, "Sauce Labs Backpack");
    System.out.println("Expected Product is Visible");
}

    public boolean checkproduct(String expectedProduct) {
    wait.until(ExpectedConditions.visibilityOfAllElements(productNames));

    for (WebElement productItem : productNames) {
        if (productItem.getText().equals(expectedProduct)) {
            return true;
        }
    }

    return false;
}
    
    public void getProduct() {
    wait.until(ExpectedConditions.visibilityOf(product));

    Assert.assertTrue(product.isDisplayed());
    System.out.println("Product is Displayed");
}

    public void verifyProductsPage() {
    wait.until(ExpectedConditions.visibilityOf(productsText));

    Assert.assertTrue(productsText.isDisplayed());
    System.out.println("Inventory Homepage Displayed");
}

    public void addBackpackToCart() {
    wait.until(ExpectedConditions.elementToBeClickable(backpackAddToCartBtn)).click();
}

    public void verifyCartContainsOneItem() throws InterruptedException {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"), "1"));

    Assert.assertTrue(cartBadge.isDisplayed() && cartBadge.getText().equals("1"));
    System.out.println("cart badge reflect the added item count");
}
    
    public void clickCartIcon() {
    wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
}

    public void clickCart() throws InterruptedException {
    wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();

    wait.until(ExpectedConditions.urlContains("cart.html"));
    wait.until(ExpectedConditions.visibilityOf(cartpagetitle));

    Assert.assertTrue(cartpagetitle.isDisplayed());
    System.out.println("Cart navigation is successfull");
}

    public void verifyBackpackAddToCartVisible() {
    wait.until(ExpectedConditions.visibilityOf(backpackAddToCartBtn));
    wait.until(ExpectedConditions.elementToBeClickable(backpackAddToCartBtn));

    Assert.assertTrue(backpackAddToCartBtn.isDisplayed() && backpackAddToCartBtn.isEnabled());
    System.out.println("Backpack Add to cart button is visible and enabled");
}

    public void verifyBackpackRemoveButtonVisible() {
    WebElement removeButton = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack"))
    );

    Assert.assertTrue(removeButton.isDisplayed());
    System.out.println("Backpack button changed to Remove");
}

    public void addBikeLightToCart() {
    wait.until(ExpectedConditions.elementToBeClickable(bikeLightAddToCartBtn)).click();
}
	private void waitForInventoryPage() {
    wait.until(ExpectedConditions.urlContains("inventory.html"));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_list")));
    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".inventory_item"), 0));
}
    public void addProductToCart(String productName) {
    waitForInventoryPage();

    String expectedName = productName.trim();

    By buttonLocator = By.xpath(
        "//div[contains(@class,'inventory_item')]" +
        "[.//div[contains(@class,'inventory_item_name') " +
        "and normalize-space()='" + expectedName + "']]//button"
    );

    try {
        WebElement button = wait.until(
            ExpectedConditions.elementToBeClickable(buttonLocator)
        );

        // Avoid adding the same product again if it is already in the cart.
        if (button.getText().trim().equals("Remove")) {
            return;
        }

        // Click the Add to cart button.
        button.click();

        // Wait until the button changes to Remove.
        wait.until(driver -> {
            try {
                WebElement updatedButton =
                    driver.findElement(buttonLocator);

                return updatedButton.isDisplayed() && updatedButton.getText().trim().equals("Remove");

            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                return false;

            } catch (org.openqa.selenium.NoSuchElementException e) {
                return false;
            }
        });

    } catch (org.openqa.selenium.TimeoutException e) {
        Assert.fail("Failed to add product to cart or verify its Remove button: "+ expectedName,e);
    }
}

    public void verifyAddToCartButtonVisible(String productName) {
    waitForInventoryPage();

    for (WebElement item : driver.findElements(By.cssSelector(".inventory_item"))) {
        String actualName = item.findElement(By.className("inventory_item_name")).getText().trim();

        if (actualName.equals(productName.trim())) {
            WebElement button = item.findElement(By.tagName("button"));

            wait.until(ExpectedConditions.visibilityOf(button));
            wait.until(ExpectedConditions.elementToBeClickable(button));

            Assert.assertTrue(button.isDisplayed(), "Add to Cart button is not visible");
            Assert.assertTrue(button.isEnabled(), "Add to Cart button is not enabled");

            return;
        }
    }

    Assert.fail("Product not found: " + productName);
}

    public void verifyRemoveButtonVisible(String productName) {
    waitForInventoryPage();

    for (WebElement item : driver.findElements(By.cssSelector(".inventory_item"))) {
        String actualName = item.findElement(By.className("inventory_item_name")).getText().trim();

        if (actualName.equals(productName.trim())) {
            By removeButton = By.id(
                "remove-" + productName.trim().toLowerCase().replace(" ", "-")
            );

            WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(removeButton)
            );

            Assert.assertEquals(button.getText().trim(), "Remove");

            return;
        }
    }

    Assert.fail("Product not found: " + productName);
}

    public String getProductPrice(String productName) {
    waitForInventoryPage();

    for (WebElement item : driver.findElements(By.cssSelector(".inventory_item"))) {
        String actualName = item.findElement(By.className("inventory_item_name")).getText().trim();

        if (actualName.equals(productName.trim())) {
            WebElement price = wait.until(
                ExpectedConditions.visibilityOf(
                    item.findElement(By.className("inventory_item_price"))
                )
            );

            return price.getText();
        }
    }

    Assert.fail("Product not found: " + productName);
    return "";
}
}
