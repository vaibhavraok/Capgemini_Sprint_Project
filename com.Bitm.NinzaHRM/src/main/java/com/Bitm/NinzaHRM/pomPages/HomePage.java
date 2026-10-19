
package com.Bitm.NinzaHRM.pomPages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Home")
    private WebElement home;

    @FindBy(xpath = "//*[normalize-space()='User Id']")
    private WebElement userIdLabel;

    @FindBy(xpath = "//*[normalize-space()='Username']")
    private WebElement usernameLabel;

    @FindBy(xpath = "//*[normalize-space()='Name']")
    private WebElement nameLabel;

    @FindBy(xpath = "//*[normalize-space()='Email']")
    private WebElement emailLabel;

    @FindBy(xpath = "//*[normalize-space()='Phone']")
    private WebElement phoneLabel;

    @FindBy(xpath = "//*[normalize-space()='Profession']")
    private WebElement professionLabel;

    @FindBy(xpath = "//*[normalize-space()='Experience']")
    private WebElement experienceLabel;

    @FindBy(xpath = "//*[normalize-space()='Role']")
    private WebElement roleLabel;

    @FindBy(xpath = "//img")
    private WebElement profileImage;

    public void getHome() {
        wait.until(ExpectedConditions.elementToBeClickable(home)).click();
    }

    public boolean isHomeDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(home)).isDisplayed();
    }

    public boolean isProfileDetailsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(userIdLabel)).isDisplayed()
                && usernameLabel.isDisplayed()
                && nameLabel.isDisplayed()
                && emailLabel.isDisplayed()
                && phoneLabel.isDisplayed()
                && professionLabel.isDisplayed()
                && experienceLabel.isDisplayed()
                && roleLabel.isDisplayed();
    }

    public boolean isProfileImageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(profileImage)).isDisplayed();
    }

    public boolean isHomePageLoaded() {
        return wait.until(ExpectedConditions.urlContains("/welcome"));
    }
}
