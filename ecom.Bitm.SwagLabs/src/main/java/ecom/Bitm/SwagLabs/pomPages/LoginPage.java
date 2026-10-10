package ecom.Bitm.SwagLabs.pomPages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ecom.Bitm.SwagLabs.Utility.PropertyFileUtility;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id = "user-name")
	private WebElement userNameTF;
	
	@FindBy(id = "password")
	private WebElement passwordTF;
	
	@FindBy(id = "login-button")
	private WebElement Loginbtn;
	
	public void getUserNameTF(String username) {
		userNameTF.sendKeys(username);
	}

	public void getPasswordTF(String password) {
		passwordTF.sendKeys(password);
	}

	public void getLoginbtn() {
		Loginbtn.click();
	}
	
	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement error;
	
	public boolean getError(String i) {
		
		boolean r;
		 
		 if(i.equals("Epic sadface: Username and password do not match any user in this service")) {
			  r=true;
		 }
		 else if(i.equals("Epic sadface: Username is required")){
			 r=true;
		 }
		 else if(i.equals("Epic sadface: Password is required")){
			 r=true;
		 }
		 else {
			 r=false;
		 }
		 return r;
	}
	
	public void ErrorDisplay() {
		String i=error.getText();
		Assert.assertTrue(getError(i));
		System.out.println(i);
	}
	public void displayloginpage() {
		boolean u=userNameTF.isDisplayed();
		boolean p=passwordTF.isDisplayed();
		boolean l=Loginbtn.isDisplayed();
		Assert.assertTrue(u && p && l);
		System.out.println("Login Page Displayed");
	}
	 public void login() throws IOException {
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.getUserNameTF(PropertyFileUtility.getData("username"));
	        loginPage.getPasswordTF(PropertyFileUtility.getData("password"));
	        loginPage.getLoginbtn();
	    }
	 
}
