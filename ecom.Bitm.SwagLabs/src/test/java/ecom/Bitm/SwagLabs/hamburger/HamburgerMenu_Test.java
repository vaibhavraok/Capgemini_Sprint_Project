
package ecom.Bitm.SwagLabs.hamburger;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ecom.Bitm.SwagLabs.Utility.PropertyFileUtility;
import ecom.Bitm.SwagLabs.baseClass.BaseClass01;
import ecom.Bitm.SwagLabs.pomPages.CartPage;
import ecom.Bitm.SwagLabs.pomPages.HamburgerMenuPage;
import ecom.Bitm.SwagLabs.pomPages.LoginPage;
import ecom.Bitm.SwagLabs.pomPages.ProductsPage;

public class HamburgerMenu_Test extends BaseClass01 {

    private LoginPage loginPage;
    private ProductsPage productPage;
    private CartPage cartPage;
    private HamburgerMenuPage hamburgerMenuPage;

    @BeforeMethod(alwaysRun = true)
    public void loginBeforeHamburgerTest() throws IOException {
        loginPage = new LoginPage(driver);
        productPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        hamburgerMenuPage = new HamburgerMenuPage(driver);
        loginPage.login();
    }

    @Test(priority = 1, groups = {"functional", "smoke"})
    public void verifyHamburgerMenuOptions() {
        hamburgerMenuPage.openMenu();
        Assert.assertTrue(hamburgerMenuPage.isAllItemsDisplayed(), "All Items option is not displayed");
        Assert.assertTrue(hamburgerMenuPage.isAboutDisplayed(), "About option is not displayed");
        Assert.assertTrue(hamburgerMenuPage.isLogoutDisplayed(), "Logout option is not displayed");
        Assert.assertTrue(hamburgerMenuPage.isResetAppStateDisplayed(), "Reset App State option is not displayed");
        Reporter.log("Hamburger menu options verified successfully", true);
    }

    @Test(priority = 2, groups = {"functional"})
    public void verifyCloseHamburgerMenu() {
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.closeMenu();
        Assert.assertTrue(driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(), "Hamburger menu button is not displayed after closing");
        Reporter.log("Hamburger menu opened and closed successfully", true);
    }

    @Test(priority = 3, groups = {"functional", "integration"})
    public void verifyAllItemsNavigation() {
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.clickAllItems();
        productPage.verifyProductsPage();
        Reporter.log("All Items navigation verified successfully", true);
    }

    @Test(priority = 4, groups = {"functional", "integration"})
    public void verifyResetAppStateRemovesProduct() {
        productPage.addBackpackToCart();
        Assert.assertTrue(cartPage.isCartBadgeDisplayed(), "Cart badge should appear after adding the backpack");
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.clickResetAppState();
        hamburgerMenuPage.closeMenuIfOpen();
        productPage.clickCartIcon();
        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty after Reset App State");
        Assert.assertFalse(cartPage.isCartBadgeDisplayed(), "Cart badge should disappear after Reset App State");
        Reporter.log("Reset App State cleared the cart successfully", true);
    }

    @Test(priority = 5, groups = {"functional", "smoke"})
    public void verifyLogout() throws IOException {
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.clickLogout();
        Assert.assertEquals(driver.getCurrentUrl(), PropertyFileUtility.getData("url"), "User was not redirected to the login page");
        loginPage.displayloginpage();
        Reporter.log("Logout verified successfully", true);
    }

    @Test(priority = 6, groups = {"functional", "smoke"})
    public void verifyDynamicCatalogOptions() {
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.expandDynamicCatalog();
        Assert.assertTrue(hamburgerMenuPage.isLazyLoadDisplayed(), "Lazy Load option is not displayed");
        Assert.assertTrue(hamburgerMenuPage.isSpinnerDisplayed(), "Spinner option is not displayed");
        Assert.assertTrue(hamburgerMenuPage.isSliderDisplayed(), "Slider option is not displayed");
        Reporter.log("Dynamic Catalog options verified successfully", true);
    }

    @Test(priority = 7, groups = {"functional", "integration"})
    public void verifyLazyLoadNavigation() {
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.expandDynamicCatalog();
        String previousUrl = driver.getCurrentUrl();
        hamburgerMenuPage.clickLazyLoad();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.not(ExpectedConditions.urlToBe(previousUrl)));
        Assert.assertNotEquals(driver.getCurrentUrl(), previousUrl, "Lazy Load navigation did not change the URL");
        Reporter.log("Lazy Load navigation verified successfully", true);
    }

    @Test(priority = 8, groups = {"functional", "integration"})
    public void verifySpinnerNavigation() {
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.expandDynamicCatalog();
        String previousUrl = driver.getCurrentUrl();
        hamburgerMenuPage.clickSpinner();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.not(ExpectedConditions.urlToBe(previousUrl)));
        Assert.assertNotEquals(driver.getCurrentUrl(), previousUrl, "Spinner navigation did not change the URL");
        Reporter.log("Spinner navigation verified successfully", true);
    }

    @Test(priority = 9, groups = {"functional", "integration"})
    public void verifySliderNavigation() {
        hamburgerMenuPage.openMenu();
        hamburgerMenuPage.expandDynamicCatalog();
        String previousUrl = driver.getCurrentUrl();
        hamburgerMenuPage.clickSlider();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.not(ExpectedConditions.urlToBe(previousUrl)));
        Assert.assertNotEquals(driver.getCurrentUrl(), previousUrl, "Slider navigation did not change the URL");
        Reporter.log("Slider navigation verified successfully", true);
    }
}
