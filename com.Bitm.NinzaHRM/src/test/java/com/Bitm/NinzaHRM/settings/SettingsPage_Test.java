package com.Bitm.NinzaHRM.settings;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.Bitm.NinzaHRM.baseClass.BaseClass01;
import com.Bitm.NinzaHRM.pomPages.SettingsPage;

public class SettingsPage_Test extends BaseClass01 {

    @Test(priority = 1, groups = {"smoke", "pos"})
    public void verifySettingsPageNavigation() throws Exception {
        SettingsPage s = new SettingsPage(driver).openSettingsPage();
        Assert.assertTrue(s.isSettingsLinkDisplayed(),"Settings link is not displayed.");
        Assert.assertTrue(s.isGenerateTokenButtonDisplayed(),"Generate New Token button is not displayed.");
        Reporter.log("verifySettingsPageNavigation test case completed", true);
    }

    @Test(priority = 2, groups = {"functional", "pos"})
    public void verifyTokenLifespanSelection() throws Exception {
        SettingsPage s = new SettingsPage(driver).openSettingsPage();
        s.selectTokenLifespan("10 min");
        Assert.assertEquals(s.getSelectedTokenLifespan(),"10 min","Token lifespan was not selected correctly.");
        Reporter.log("verifyTokenLifespanSelection test case completed", true);
    }

    @Test(priority = 3, groups = {"functional", "pos"})
    public void verifyAuthorizationPermissionSelection() throws Exception {
        SettingsPage s = new SettingsPage(driver).openSettingsPage();
        s.selectPermission("Projects", "read");
        s.selectPermission("Projects", "create");
        s.selectPermission("Projects", "update");
        s.selectPermission("Projects", "delete");
        Assert.assertTrue(s.isPermissionSelected("Projects", "read"),"Projects Read permission was not selected.");
        Assert.assertTrue(s.isPermissionSelected("Projects", "create"),"Projects Create permission was not selected.");
        Assert.assertTrue(s.isPermissionSelected("Projects", "update"),"Projects Update permission was not selected.");
        Assert.assertTrue(s.isPermissionSelected("Projects", "delete"),"Projects Delete permission was not selected.");
        Reporter.log("verifyAuthorizationPermissionSelection test case completed",true);
    }
    
    @Test(priority = 4, groups = {"functional", "pos"})
    public void verifyNewTokenGeneration() throws Exception {
        SettingsPage s = new SettingsPage(driver).openSettingsPage();
        s.selectPermission("Projects", "read");
        s.clickGenerateNewToken();
        Assert.assertTrue(s.isTokenGeneratedSuccessfully(), "Token Generated Successfully message was not displayed.");
        Reporter.log("verifyNewTokenGeneration test case completed", true);
    }

}