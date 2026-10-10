package ecom.Bitm.SwagLabs.addToCart;

import java.io.IOException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.Bitm.SwagLabs.Utility.WebDriverUtility;
import ecom.Bitm.SwagLabs.baseClass.BaseClass01;
import ecom.Bitm.SwagLabs.pomPages.CartPage;
import ecom.Bitm.SwagLabs.pomPages.LoginPage;
import ecom.Bitm.SwagLabs.pomPages.ProductsPage;

public class CartPage_Test extends BaseClass01 {
    
    @Test(priority = 1, groups = {"functional","pos"})
    public void addToCartButtonVisible() throws IOException {
        LoginPage l = new LoginPage(driver);
        CartPage c = new CartPage(driver);
        ProductsPage p = new ProductsPage(driver);
        l.login();
        p.verifyAddToCartButtonVisible(c.backpack());
        Reporter.log("Add to cart button is visible", true);
    }

    @Test(priority = 2, groups = {"functional","pos"})
    public void addProductToCart() throws IOException {
        LoginPage l = new LoginPage(driver);
        CartPage c = new CartPage(driver);
        ProductsPage p = new ProductsPage(driver);
        l.login();
        p.addProductToCart(c.backpack());
        p.verifyRemoveButtonVisible(c.backpack());
        p.removecart();
        Reporter.log("Product added to cart and button changed to Remove", true);
    }

    @Test(priority = 3, groups = {"smoke","pos"})
    public void cartBadgeUpdated() throws IOException {
        LoginPage l = new LoginPage(driver);
        CartPage c = new CartPage(driver);
        ProductsPage p = new ProductsPage(driver);
        l.login();
        p.addProductToCart(c.backpack());
        c.verifyCartBadgeCount("1");
        p.removecart();
        Reporter.log("Cart badge updated to 1", true);
    }

    @Test(priority = 4, groups = {"integration","pos"})
    public void addedProductDisplayedInCart() throws IOException {
        LoginPage l = new LoginPage(driver);
        CartPage c = new CartPage(driver);
        ProductsPage p = new ProductsPage(driver);
        l.login();
        String product = c.backpack();
        p.addProductToCart(product);
        c.openCart();
        c.verifyProductInCart(product);
        p.removecart();
        Reporter.log("Added product is displayed in cart", true);
    }

    @Test(priority = 5, groups = {"integration","pos"})
    public void productDetailsMatchBetweenProductsAndCart() throws IOException {
        LoginPage l = new LoginPage(driver);
        CartPage c = new CartPage(driver);
        ProductsPage p = new ProductsPage(driver);
        l.login();
        String product = c.backpack();
        p.addProductToCart(product);
        c.openCart();
        c.verifyProductInCart(product);
        p.removecart();
        Reporter.log("Product name and price match", true);
    }

    @Test(priority = 6, groups = {"functional","pos"})
    public void multipleProductsDisplayedInCart() throws IOException {
        LoginPage l = new LoginPage(driver);
        CartPage c = new CartPage(driver);
        ProductsPage p = new ProductsPage(driver);
        l.login();
        String product1 = c.backpack();
        String product2 = c.bikeLight();
        p.addProductToCart(product1);
        p.addProductToCart(product2);
        c.openCart();
        c.verifyCartBadgeCount("2");
        c.verifyProductInCart(product1);
        c.verifyProductInCart(product2);
        p.removecart();
        p.removecart();
        Reporter.log("Multiple products are displayed correctly", true);
    }

    @Test(priority = 7, groups = {"functional","neg"})
    public void sameProductIsNotUnexpectedlyDuplicated() throws Exception {
        LoginPage l = new LoginPage(driver);
        CartPage c = new CartPage(driver);
        ProductsPage p = new ProductsPage(driver);
        l.login();
        String product = c.backpack();
        p.addProductToCart(product);
        p.verifyRemoveButtonVisible(product);
        c.openCart();
        c.verifyCartBadgeCount("1");
        p.removecart();
        Reporter.log("Same product is not unexpectedly duplicated", true);
        WebDriverUtility wUtil = new WebDriverUtility();
        wUtil.takingScreenshot("ProductNotDuplicated", driver);
    }

    @Test(priority = 8, groups = {"functional","pos"})
    public void selectedProductRemainsWhenReturningToCart() throws IOException {
        LoginPage l = new LoginPage(driver);
        CartPage c = new CartPage(driver);
        ProductsPage p = new ProductsPage(driver);
        l.login();
        String product = c.backpack();
        p.addProductToCart(product);
        c.openCart();
        c.continueShopping();
        c.openCart();
        c.verifyProductInCart(product);
        p.removecart();
        Reporter.log("Selected product remains in cart", true);
    }

    @Test(priority = 9, groups = {"functional","pos"})
    public void removeProductFromCart() throws IOException, InterruptedException {
        LoginPage l = new LoginPage(driver);
        ProductsPage p = new ProductsPage(driver);
        l.login();
        p.getAddtocart();
        p.clickCartIcon();
        p.removecart();
        Reporter.log("Product removed successfully", true);
    }
    
    @Test(priority = 10, groups = {"functional", "neg"})
    public void removedProductIsNotPresentInCart() throws Exception {
        LoginPage l = new LoginPage(driver);
        CartPage c = new CartPage(driver);
        ProductsPage p = new ProductsPage(driver);
        l.login();
        String product = c.backpack();
        p.addProductToCart(product);
        c.openCart();
        p.removecart();
        c.verifyProductNotInCart(product);
        Reporter.log("Removed product is not present in the cart", true);
        WebDriverUtility wUtil = new WebDriverUtility();
        wUtil.takingScreenshot("RemovedProductNotInCart", driver);
    }
}
