package com.Bitm.NinzaHRM.projects;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Bitm.NinzaHRM.Utility.ExcelUtility;
import com.Bitm.NinzaHRM.baseClass.BaseClass01;
import com.Bitm.NinzaHRM.pomPages.LoginPage;
import com.Bitm.NinzaHRM.pomPages.ProjectsPage;

public class ProjectsPage_Test extends BaseClass01 {

    @DataProvider(name = "projectData")
    public Object[][] projectData() throws Exception {
        return new Object[][] {
            {
                ExcelUtility.getData("Sheet1", 1, 0),
                ExcelUtility.getData("Sheet1", 1, 1),
            }
        };
    }
    
    @DataProvider(name = "searchProjectData")
    public Object[][] searchProjectData() throws Exception {
        return new Object[][] {
            { ExcelUtility.getData("Sheet1", 1, 0) }
        };
    }
    
    @DataProvider(name = "updateProjectData")
    public Object[][] updateProjectData() throws Exception {
        return new Object[][] {
            {
                ExcelUtility.getData("Sheet1", 1, 0),
                ExcelUtility.getData("Sheet1", 2, 0),
                ExcelUtility.getData("Sheet1", 2, 1)
            }
        };
    }
    
    @Test(priority = 1, groups = {"smoke", "pos"})
    public void verifyProjectsPageNavigation() throws Exception {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProjectsPage p = new ProjectsPage(driver);
        p.getProjects();
        Assert.assertTrue(p.isProjectsPageDisplayed(),"Projects page navigation failed.");
        Reporter.log("verifyProjectsPageNavigation test case completed", true);
    }

    @Test(priority = 2, groups = {"system", "pos"})
    public void verifyProjectsPageAfterRefresh() throws Exception {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProjectsPage p = new ProjectsPage(driver);
        p.getProjects();
        driver.navigate().refresh();
        Assert.assertTrue(p.isProjectsPageDisplayed(),"Projects page did not remain accessible after refresh.");
        Reporter.log("verifyProjectsPageAfterRefresh test case completed", true);
    }


	@Test(priority = 3, groups = {"functional","pos"}, dataProvider = "projectData")
	public void verifyProjectCreation(String projectName, String projectManager) throws Exception {
	    LoginPage l = new LoginPage(driver);
	    l.login();
	    ProjectsPage p = new ProjectsPage(driver);
	    p.getProjects();
	    p.getCreateproject();
	    p.getProjectname(projectName);
	    p.getProjectmanager(projectManager);
	    p.getStatus();
	    p.getAddproject();
	    Assert.assertTrue(p.isProjectAddedSuccessfully(projectName),"Project creation failed: " + projectName);
	    Reporter.log("verifyProjectCreation test case completed", true);
	}
	
	@Test(priority = 4, groups = {"functional","pos"}, dataProvider = "searchProjectData")
	public void verifyProjectSearch(String projectName) throws Exception {
	    LoginPage l = new LoginPage(driver);
	    l.login();
	    ProjectsPage p = new ProjectsPage(driver);
	    p.getProjects();
	    p.getSearchproject(projectName);
	    Assert.assertTrue(p.isProjectSearchResultDisplayed(projectName),"Searched project was not displayed: " + projectName);
	    Reporter.log("verifyProjectSearch test case completed", true);
	}

	@Test(priority = 5, groups = {"functional","pos"}, dataProvider = "updateProjectData")
	public void verifyProjectUpdate(String ProjectName,String updatedProjectName,String updatedProjectManager) throws Exception {
	    LoginPage l = new LoginPage(driver);
	    l.login();
	    ProjectsPage p = new ProjectsPage(driver);
	    p.getProjects();
	    p.clickEditProject(ProjectName);
	    p.updateProjectName(updatedProjectName);
	    p.updateProjectManager(updatedProjectManager);
	    p.clickUpdateProject();
	    Assert.assertTrue(p.isProjectDisplayed(updatedProjectName),"Updated project was not displayed: " + updatedProjectName);
	    Reporter.log("verifyProjectUpdate test case completed", true);
	}
	
	@Test(priority = 6, groups = {"functional","pos"}, dataProvider = "updateProjectData")
	public void verifyProjectDeletion(String ProjectName,String updatedProjectName,String updatedProjectManager) throws Exception {
	    LoginPage l = new LoginPage(driver);
	    l.login();
	    ProjectsPage p = new ProjectsPage(driver);
	    p.getProjects();
	    p.clickDeleteProject(updatedProjectName);
	    p.confirmProjectDeletion();
	    Assert.assertTrue(p.isProjectRemoved(updatedProjectName),"Project was not deleted: " + updatedProjectName);
	    Reporter.log("verifyProjectDeletion test case completed", true);
	}
	

	@Test(priority = 7, groups = {"functional", "neg"})
	public void verifyProjectCreationBlankData() throws Exception {
	    LoginPage l = new LoginPage(driver);
	    l.login();
	    ProjectsPage p = new ProjectsPage(driver);
	    p.getProjects();
	    p.getCreateproject();
	    p.getAddproject();
	    Assert.assertEquals(p.getProjectNameValidationMessage(), "Please fill out this field.","Blank-field validation message was not displayed.");
	    Reporter.log("verifyProjectCreationBlankData test case completed", true);
	}


}
