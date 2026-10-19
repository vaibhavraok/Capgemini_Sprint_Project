
package com.Bitm.NinzaHRM.pomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SettingsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Settings']")
    private WebElement settingsLink;

    @FindBy(xpath = "//label[normalize-space()='Token Lifespan']/following-sibling::select")
    private WebElement tokenLifespanDropdown;

    @FindBy(xpath = "//button[normalize-space()='Generate New Token']")
    private WebElement generateTokenButton;
    
	@FindBy(xpath = "//*[contains(normalize-space(),'Token Generated Successfully')]")
    private WebElement message;
    public SettingsPage openSettingsPage() throws Exception {
        LoginPage login = new LoginPage(driver);
        login.login();

        wait.until(ExpectedConditions.urlContains("dashboard"));

        getSettings();

        wait.until(ExpectedConditions.urlContains("settings"));

        return this;
    }

    public void getSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsLink)).click();
    }

    public boolean isSettingsLinkDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(settingsLink)).isDisplayed();
    }

    public void selectTokenLifespan(String lifespan) {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(tokenLifespanDropdown));

        new Select(dropdown).selectByVisibleText(lifespan);
    }

    public String getSelectedTokenLifespan() {
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOf(tokenLifespanDropdown));

        return new Select(dropdown).getFirstSelectedOption().getText();
    }


	private By getPermissionLocator(String module, String permission) {
	    return By.xpath(
	        "//div[contains(@class,'formGroup')]" +
	        "[label[normalize-space()='" + module + "']]" +
	        "//input[@type='checkbox' and " +
	        "translate(@id,'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
	        "'abcdefghijklmnopqrstuvwxyz')='" +
	        permission.toLowerCase() + "']");
	}


    public void selectPermission(String module, String permission) {
        By locator = getPermissionLocator(module, permission);

        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(locator));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public boolean isPermissionSelected(String module, String permission) {
        By locator = getPermissionLocator(module, permission);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isSelected();
    }
    
    public boolean isGenerateTokenButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(generateTokenButton)).isDisplayed();
    }
    
    public void clickGenerateNewToken() {
        wait.until(ExpectedConditions.elementToBeClickable(generateTokenButton)).click();
    }

    public boolean isTokenGeneratedSuccessfully() {
        return wait.until(ExpectedConditions.visibilityOf(message)).isDisplayed();
    }
}

