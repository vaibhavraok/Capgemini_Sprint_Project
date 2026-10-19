package com.Bitm.NinzaHRM.login;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Bitm.NinzaHRM.Utility.ExcelUtility;
import com.Bitm.NinzaHRM.Utility.WebDriverUtility;
import com.Bitm.NinzaHRM.baseClass.BaseClass01;
import com.Bitm.NinzaHRM.pomPages.LoginPage;

public class LoginPage_Test extends BaseClass01 {
	
	@DataProvider(name = "wrongCredentials")
    public Object[][] wrongCredentials() throws Exception {
        return new Object[][] {
            {
                ExcelUtility.getData("Sheet2", 1, 0),
                ExcelUtility.getData("Sheet2", 1, 1),
            }
        };
    }
	
    @Test(priority = 1, groups = {"smoke", "pos"})
    public void verifyLoginWithValidCredentials() throws IOException {
        LoginPage l = new LoginPage(driver);
        l.login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains("dashboard")),"Login failed or Dashboard page was not displayed.");
        Reporter.log("verifyLoginWithValidCredentials test case completed", true);
    }

	@Test(priority = 2, groups = {"functional","neg"},dataProvider = "wrongCredentials")
	public void verifyLoginWithInvalidCredentials(String invaliduser,String invalidpassword) throws Exception {
	    LoginPage l = new LoginPage(driver);
	    l.getUserNameTF(invaliduser);
        l.getPasswordTF(invalidpassword);
	    l.getSigninbutton();
	    Thread.sleep(1000);
	    Alert alert = driver.switchTo().alert();
	    String alertText = alert.getText();
	    alert.accept();
	    Assert.assertEquals(alertText,"Invalid Credentials","Unexpected alert message for invalid username and password");
	    WebDriverUtility w = new WebDriverUtility();
	    w.takingScreenshot("InvalidCredentialsValidation", driver);
	    Reporter.log("verifyLoginWithInvalidCredentials test case completed", true);
	}

	@Test(priority = 3, groups = {"functional","neg"})
	public void verifyLoginWithBlankCredentials() throws Exception {
	    LoginPage l = new LoginPage(driver);
	    l.getSigninbutton();
	    Thread.sleep(1000);
	    Assert.assertEquals(driver.getTitle(),"Ninza HRM","Unexpected page after empty credentials login");
	    WebDriverUtility w = new WebDriverUtility();
	    w.takingScreenshot("BlankCredentialsValidation", driver);
	    Reporter.log("verifyLoginWithBlankCredentials test case completed", true);
	}
	
	@Test(priority = 4,groups = {"smoke", "pos"})
    public void verifySuccessfulLoginNavigatesToDashboard() throws IOException {
        LoginPage login = new LoginPage(driver);
        login.login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains("/dashboard/overview")),"Successful login did not navigate to the Dashboard page.");
        Reporter.log("verifySuccessfulLoginNavigatesToDashboard test case completed",true);
    }
}