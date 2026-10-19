package com.Bitm.NinzaHRM.pomPages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Bitm.NinzaHRM.Utility.PropertyFileUtility;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "username")
	private WebElement userNameTF;
	
	@FindBy(id = "inputPassword")
	private WebElement passwordTF;
	
	@FindBy(xpath = "//button[text()='Sign in']")
	private WebElement signinbutton;
	
	public void getUserNameTF(String username) {
		userNameTF.sendKeys(username);
	}

	public void getPasswordTF(String password) {
		passwordTF.sendKeys(password);
	}

	public void getSigninbutton() {
		signinbutton.click();
	}

	public void login() throws IOException {
        getUserNameTF(PropertyFileUtility.getData("username"));
        getPasswordTF(PropertyFileUtility.getData("password"));
        getSigninbutton();
    }
}
