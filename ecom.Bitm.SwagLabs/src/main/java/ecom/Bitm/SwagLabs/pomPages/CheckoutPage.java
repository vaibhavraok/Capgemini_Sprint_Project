package ecom.Bitm.SwagLabs.pomPages;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "summary_info")
    private WebElement summaryInfo;

    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotal;

    @FindBy(className = "summary_tax_label")
    private WebElement tax;

    @FindBy(className = "summary_total_label")
    private WebElement total;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement confirmationHeader;

    @FindBy(className = "complete-text")
    private WebElement confirmationMessage;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public void verifyCheckoutInformationPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        Assert.assertEquals(driver.findElement(By.className("title")).getText(), "Checkout: Your Information");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
        System.out.println("Checkout information page is displayed");
    }

    public void enterCustomerInformation(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name"))).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code"))).sendKeys(postalCode);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();
        wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.className("summary_info")), ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))));
    }

    public void verifyCheckoutOverviewDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        Assert.assertEquals(driver.findElement(By.className("title")).getText(), "Checkout: Overview");
        System.out.println("Checkout Overview is displayed");
    }

    public void verifyProductInOverview(String productName, String expectedPrice) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        boolean found = false;
        for (WebElement product : products) {
            if (product.getText().equals(productName)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Product is not present in Checkout Overview: " + productName);
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        boolean priceFound = false;
        for (WebElement price : prices) {
            if (price.getText().equals(expectedPrice)) {
                priceFound = true;
                break;
            }
        }
        Assert.assertTrue(priceFound, "Expected price is not displayed: " + expectedPrice);
        System.out.println("Product and price are correct in Checkout Overview");
    }

    public void verifyPaymentAndShippingInformation() {
        String summary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_info"))).getText();
        Assert.assertTrue(summary.contains("Payment Information"));
        Assert.assertTrue(summary.contains("Shipping Information"));
        System.out.println("Payment and shipping information are displayed");
    }

    public BigDecimal getItemTotal() {
        return parseSummaryValue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_subtotal_label"))).getText());
    }

    public BigDecimal getTax() {
        return parseSummaryValue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_tax_label"))).getText());
    }

    public BigDecimal getTotal() {
        return parseSummaryValue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_total_label"))).getText());
    }

    public void verifyTotals() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_subtotal_label")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_tax_label")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_total_label")));

        BigDecimal item = getItemTotal();
        BigDecimal taxAmount = getTax();
        BigDecimal finalTotal = getTotal();

        System.out.println("Item Total: $" + item);
        System.out.println("Tax: $" + taxAmount);
        System.out.println("Total: $" + finalTotal);

        Assert.assertTrue(item.compareTo(BigDecimal.ZERO) > 0, "Item total should be greater than zero");
        Assert.assertTrue(taxAmount.compareTo(BigDecimal.ZERO) >= 0, "Tax should not be negative");
        Assert.assertEquals(finalTotal.compareTo(item.add(taxAmount)), 0, "Total calculation is incorrect");

        System.out.println("Item total, tax and final total verified successfully");
    }

    public void verifyMandatoryFieldError() {
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))).getText();
        Assert.assertFalse(message.isEmpty());
        System.out.println("Checkout validation error: " + message);
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))).getText();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();
        wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
    }

    public void verifyOrderConfirmation() {
        String header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header"))).getText();
        Assert.assertEquals(header, "Thank you for your order!");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-text")));
        System.out.println("Order confirmation displayed successfully");
    }

    public void verifyCompletedPage() {
        wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title"))).getText();
        Assert.assertEquals(title, "Checkout: Complete!");
        System.out.println("Checkout Complete page is displayed");
    }

    public void clickBackHome() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products"))).click();
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cancel"))).click();
    }

    private BigDecimal parseSummaryValue(String text) {
        String value = text.substring(text.lastIndexOf("$") + 1).trim();
        return new BigDecimal(value);
    }
    
    public CheckoutPage openCheckoutWithBackpack() throws Exception {
        ProductsPage products = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);

        products.addProductToCart(cart.backpack());

        cart.openCart();
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.verifyCheckoutInformationPage();
        return checkout;
    }

    public CheckoutPage openOverviewWithBackpack(String firstName, String lastName, String postalCode) throws Exception {
        CheckoutPage checkout = openCheckoutWithBackpack();
        checkout.enterCustomerInformation(firstName, lastName, postalCode);
        checkout.clickContinue();
        checkout.verifyCheckoutOverviewDisplayed();
        return checkout;
    }
    
}
