
package ecom.Bitm.SwagLabs.pomPages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HamburgerMenuPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamBurger;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeMenuButton;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItems;

    @FindBy(id = "about_sidebar_link")
    private WebElement about;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppState;

    @FindBy(id = "dynamic_catalog_sidebar_link")
    private WebElement dynamicCatalog;

    @FindBy(id = "dynamic_catalog_submenu")
    private WebElement dynamicCatalogSubmenu;

    @FindBy(xpath = "//*[@id='dynamic_catalog_submenu']//*[normalize-space(text())='Lazy Load']")
    private WebElement lazyLoad;

    @FindBy(xpath = "//*[@id='dynamic_catalog_submenu']//*[normalize-space(text())='Spinner']")
    private WebElement spinner;

    @FindBy(xpath = "//*[@id='dynamic_catalog_submenu']//*[normalize-space(text())='Slider']")
    private WebElement slider;

    public void openMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(hamBurger)).click();
        wait.until(ExpectedConditions.visibilityOf(allItems));
    }

    public void closeMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(closeMenuButton)).click();
        wait.until(ExpectedConditions.invisibilityOf(closeMenuButton));
    }

    public void closeMenuIfOpen() {
        List<WebElement> buttons = driver.findElements(By.id("react-burger-cross-btn"));

        if (!buttons.isEmpty() && buttons.get(0).isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(buttons.get(0))).click();
            wait.until(ExpectedConditions.invisibilityOf(buttons.get(0)));
        }
    }

    public void clickAllItems() {
        wait.until(ExpectedConditions.elementToBeClickable(allItems)).click();
    }

    public void clickAbout() {
        wait.until(ExpectedConditions.elementToBeClickable(about)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }

    public void clickResetAppState() {
        wait.until(ExpectedConditions.elementToBeClickable(resetAppState)).click();
    }

    public boolean isAllItemsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(allItems)).isDisplayed();
    }

    public boolean isAboutDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(about)).isDisplayed();
    }

    public boolean isLogoutDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(logout)).isDisplayed();
    }

    public boolean isResetAppStateDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(resetAppState)).isDisplayed();
    }

    public void expandDynamicCatalog() {
        wait.until(ExpectedConditions.elementToBeClickable(dynamicCatalog));

        if (!"true".equals(dynamicCatalog.getAttribute("aria-expanded"))) {
            dynamicCatalog.click();
        }

        wait.until(ExpectedConditions.visibilityOf(dynamicCatalogSubmenu));
        wait.until(ExpectedConditions.visibilityOf(lazyLoad));
        wait.until(ExpectedConditions.visibilityOf(spinner));
        wait.until(ExpectedConditions.visibilityOf(slider));
    }

    public void clickLazyLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(lazyLoad)).click();
    }

    public void clickSpinner() {
        wait.until(ExpectedConditions.elementToBeClickable(spinner)).click();
    }

    public void clickSlider() {
        wait.until(ExpectedConditions.elementToBeClickable(slider)).click();
    }

    public boolean isDynamicCatalogDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(dynamicCatalog)).isDisplayed();
    }

    public boolean isLazyLoadDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(lazyLoad)).isDisplayed();
    }

    public boolean isSpinnerDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(spinner)).isDisplayed();
    }

    public boolean isSliderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(slider)).isDisplayed();
    }
}
