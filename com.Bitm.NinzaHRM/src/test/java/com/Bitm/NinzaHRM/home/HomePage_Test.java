
package com.Bitm.NinzaHRM.home;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.Bitm.NinzaHRM.baseClass.BaseClass01;
import com.Bitm.NinzaHRM.pomPages.HomePage;
import com.Bitm.NinzaHRM.pomPages.LoginPage;

public class HomePage_Test extends BaseClass01 {

    @Test(priority = 1, groups = {"smoke","integration", "pos"})
    public void verifyHomePageNavigation() throws Exception {
        LoginPage l = new LoginPage(driver);
        l.login();
        HomePage h = new HomePage(driver);
        Assert.assertTrue(h.isHomeDisplayed(),"Home link is not displayed.");
        h.getHome();
        Assert.assertTrue(h.isHomePageLoaded(),"Home page navigation failed.");
        Reporter.log("verifyHomePageNavigation test case completed", true);
    }

    @Test(priority = 2, groups = {"functional", "pos"})
    public void verifyHomeLinkIsVisible() throws Exception {
        LoginPage l = new LoginPage(driver);
        l.login();
        HomePage h = new HomePage(driver);
        Assert.assertTrue(h.isHomeDisplayed(),"Home link is not visible.");
        Reporter.log("verifyHomeLinkIsVisible test case completed", true);
    }

    @Test(priority = 3, groups = {"system", "pos"})
    public void verifyHomePageAfterRefresh() throws Exception {
        LoginPage l = new LoginPage(driver);
        l.login();
        HomePage h = new HomePage(driver);
        h.getHome();
        Assert.assertTrue(h.isHomePageLoaded(),"Home page did not open.");
        driver.navigate().refresh();
        Assert.assertTrue(h.isHomePageLoaded(),"Home page did not remain accessible after refresh.");
        Assert.assertTrue(h.isProfileDetailsDisplayed(),"Profile details are not displayed after refresh.");
        Reporter.log("verifyHomePageAfterRefresh test case completed", true);
    }

    @Test(priority = 4, groups = {"functional", "pos"})
    public void verifyUserProfileDetailsAreDisplayed() throws Exception {
        LoginPage l = new LoginPage(driver);
        l.login();
        HomePage h = new HomePage(driver);
        h.getHome();
        Assert.assertTrue(h.isProfileDetailsDisplayed(),"One or more profile details are not displayed.");
        Reporter.log("verifyUserProfileDetailsAreDisplayed test case completed", true);
    }

    @Test(priority = 5, groups = {"functional", "pos"})
    public void verifyUserProfileImageIsDisplayed() throws Exception {
        LoginPage l = new LoginPage(driver);
        l.login();
        HomePage h = new HomePage(driver);
        h.getHome();
        Assert.assertTrue(h.isProfileImageDisplayed(),"Profile image is not displayed.");
        Reporter.log("verifyUserProfileImageIsDisplayed test case completed", true);
    }
}
