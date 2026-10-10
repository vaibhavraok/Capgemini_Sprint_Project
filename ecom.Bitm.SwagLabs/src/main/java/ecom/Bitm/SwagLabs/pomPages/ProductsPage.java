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
        remove.click();
    }

    public void Visibledata() {
        for (WebElement i : inventoryItems) {
            Assert.assertTrue(i.isDisplayed());
        }
        System.out.println("Product data is visible");
    }

    public void productrefresh() {
        driver.navigate().refresh();
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
        Assert.assertEquals(inventoryItems.size(), 6);
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
        String text = clickproduct.getText();
        clickproduct.click();
        Assert.assertEquals(text, "Sauce Labs Backpack");
        System.out.println("Corresponding page opened");
    }

    public void getExpectedproductdata() {
        String text = clickproduct.getText();
        Assert.assertEquals(text, "Sauce Labs Backpack");
        System.out.println("Expected Product is Visible");
    }

    public boolean checkproduct(String expectedProduct) {
        for (WebElement productItem : productNames) {
            if (productItem.getText().equals(expectedProduct)) {
                return true;
            }
        }
        return false;
    }
    
    public void getProduct() {
        Assert.assertTrue(product.isDisplayed());
        System.out.println("Product is Displayed");
    }

    public void verifyProductsPage() {
        Assert.assertTrue(productsText.isDisplayed());
        System.out.println("Inventory Homepage Displayed");
    }

    public void addBackpackToCart() {
        backpackAddToCartBtn.click();
    }

    public void verifyCartContainsOneItem() throws InterruptedException {
    	Thread.sleep(4000);
        Assert.assertTrue(cartBadge.isDisplayed() && cartBadge.getText().equals("1"));
        System.out.println("cart badge reflect the added item count");
    }
    
    public void clickCartIcon() {
        cartIcon.click();
    }

    public void clickCart() throws InterruptedException {
    	Thread.sleep(3000);
        cartIcon.click();
        Thread.sleep(2000);
        Assert.assertTrue(cartpagetitle.isDisplayed());
        System.out.println("Cart navigation is successfull");
    }

    public void verifyBackpackAddToCartVisible() {
        Assert.assertTrue(backpackAddToCartBtn.isDisplayed() && backpackAddToCartBtn.isEnabled());
        System.out.println("Backpack Add to cart button is visible and enabled");
    }

    public void verifyBackpackRemoveButtonVisible() {
        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertTrue(removeButton.isDisplayed());
        System.out.println("Backpack button changed to Remove");
    }

    public void addBikeLightToCart() {
        bikeLightAddToCartBtn.click();
    }

    public void addProductToCart(String productName) {
        for (WebElement item : inventoryItems) {
            String actualName = item.findElement(By.className("inventory_item_name")).getText();
            if (actualName.equals(productName)) {
                item.findElement(By.tagName("button")).click();
                return;
            }
        }
        Assert.fail("Product not found: " + productName);
    }

    public void verifyAddToCartButtonVisible(String productName) {
        for (WebElement item : inventoryItems) {
            String actualName = item.findElement(By.className("inventory_item_name")).getText();
            if (actualName.equals(productName)) {
                WebElement button = item.findElement(By.tagName("button"));
                Assert.assertTrue(button.isDisplayed());
                Assert.assertTrue(button.isEnabled());
                System.out.println(productName + " Add to cart button is visible and enabled");
                return;
            }
        }
        Assert.fail("Product not found: " + productName);
    }

    public void verifyRemoveButtonVisible(String productName) {
        for (WebElement item : inventoryItems) {
            String actualName = item.findElement(By.className("inventory_item_name")).getText();
            if (actualName.equals(productName)) {
                WebElement button = item.findElement(By.tagName("button"));
                Assert.assertEquals(button.getText(), "Remove");
                System.out.println(productName + " button changed to Remove");
                return;
            }
        }
        Assert.fail("Product not found: " + productName);
    }

    public String getProductPrice(String productName) {
        for (WebElement item : inventoryItems) {
            String actualName = item.findElement(By.className("inventory_item_name")).getText();
            if (actualName.equals(productName)) {
                return item.findElement(By.className("inventory_item_price")).getText();
            }
        }
        Assert.fail("Product not found: " + productName);
        return "";
    }
}
