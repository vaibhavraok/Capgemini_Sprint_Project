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

public class ProjectsPage {
    WebDriver driver;
    WebDriverWait wait;

    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Projects']")
    private WebElement projects;

    @FindBy(xpath = "//span[text()='Create Project']")
    private WebElement createproject;

    @FindBy(name = "projectName")
    private WebElement projectname;

    @FindBy(name = "createdBy")
    private WebElement projectmanager;

    @FindBy(xpath = "//div[contains(@class,'modal-body')]//select[@name='status']")
    private WebElement status;

    @FindBy(xpath = "//input[@value='Add Project']")
    private WebElement addproject;

    @FindBy(xpath = "//select[option[@value='projectId']]")
    private WebElement searchtype;

    @FindBy(xpath = "//input[@placeholder='Search by Project Id']")
    private WebElement searchproject;

    @FindBy(xpath = "//span[text()='Export Projects']")
    private WebElement exportprojects;

    @FindBy(id = "fileType")
    private WebElement fileType;

    @FindBy(xpath = "//button[text()='Download File']")
    private WebElement downloadbutton;
    
    @FindBy(xpath = "//a[contains(@class,'edit')]")
    private WebElement editproject;

    @FindBy(xpath = "//a[contains(@class,'delete')]")
    private WebElement deleteproject;
	
	@FindBy(xpath = "//input[@value='Update Project']")
	private WebElement updateproject;
	
	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement confirmdelete;

	@FindBy(xpath = "//div[@id='editProjectModal']//input[@value='Save']")
	private WebElement saveProject;


    public void getProjects() {
        wait.until(ExpectedConditions.elementToBeClickable(projects)).click();
    }

    public void getCreateproject() {
        wait.until(ExpectedConditions.elementToBeClickable(createproject)).click();
    }

    public void getProjectname(String pname) {
        wait.until(ExpectedConditions.visibilityOf(projectname)).sendKeys(pname);
    }

    public void getProjectmanager(String pmname) {
        wait.until(ExpectedConditions.visibilityOf(projectmanager)).sendKeys(pmname);
    }

    public void getStatus() {
        By statusLocator = By.xpath("//select[@name='status']");
        wait.until(ExpectedConditions.presenceOfElementLocated(statusLocator));
        for (WebElement dropdown : driver.findElements(statusLocator)) {
            if (dropdown.isDisplayed() && dropdown.isEnabled()) {
                new Select(dropdown).selectByVisibleText("Created");
                return;
            }
        }
        throw new RuntimeException("Status dropdown is not visible.");
    }

    public void getAddproject() {
        wait.until(ExpectedConditions.elementToBeClickable(addproject)).click();
    }

    public void getSearchproject(String searchitem) {
        wait.until(ExpectedConditions.elementToBeClickable(searchproject)).clear();
        searchproject.sendKeys(searchitem);
    }

    public void selectSearchType(String type) {
        wait.until(ExpectedConditions.visibilityOf(searchtype));
        new Select(searchtype).selectByValue(type);
    }

    public void getExportprojects() {
        wait.until(ExpectedConditions.elementToBeClickable(exportprojects)).click();
    }

    public void selectFileType(String type) {
        wait.until(ExpectedConditions.visibilityOf(fileType));
        new Select(fileType).selectByValue(type);
    }

    public void getDownloadbutton() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadbutton)).click();
    }

    public boolean isProjectDisplayed(String pname) {
        By project = By.xpath("//td[normalize-space()='" + pname + "']");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(project)).isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public boolean isProjectsPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Projects']"))).isDisplayed();
    }

    public boolean isExportPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[contains(normalize-space(),'Export Project Data')] | //h2[contains(normalize-space(),'Export Project Data')]"))).isDisplayed();
    }

    public boolean isDownloadButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(downloadbutton)).isDisplayed();
    }
    
    public void clickEditProject(String projectName) {
        By editButton = By.xpath("//tr[.//td[normalize-space()='" + projectName + "']]//a[contains(@class,'edit') or contains(@title,'Edit')]");
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
    }

    public void clickDeleteProject(String projectName) {
        By deleteButton = By.xpath("//tr[.//td[normalize-space()='" + projectName + "']]//a[contains(@class,'delete') or contains(@title,'Delete')]");
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    public boolean isProjectAddedSuccessfully(String projectName) {
        By successMessage = By.xpath("//*[contains(text(),'Successfully Added') " +"and contains(text(),'" + projectName + "')]" );
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    
    public boolean isProjectSearchResultDisplayed(String projectName) {
        By project = By.xpath("//td[normalize-space()='" + projectName + "']");

        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(project)).isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    
    public boolean isProjectRemoved(String projectName) {
        By project = By.xpath("//tr[.//td[normalize-space()='" + projectName + "']]");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(project));
    }
    
    public void updateProjectName(String name) {
        By nameField = By.xpath("//div[@id='editProjectModal']//label[normalize-space()='Project Name']/following-sibling::input");
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        field.clear();
        field.sendKeys(name);
    }

    public void updateProjectManager(String manager) {
        By managerField = By.xpath("//div[@id='editProjectModal']//label[normalize-space()='Project Manager']/following-sibling::input");
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(managerField));
        field.clear();
        field.sendKeys(manager);
    }

    public void clickUpdateProject() {
        By saveButton = By.xpath("//div[@id='editProjectModal']//input[@value='Save']" );

        wait.until(ExpectedConditions.elementToBeClickable(saveButton) ).click();
    }

    public void confirmProjectDeletion() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmdelete)).click();
    }
    
    public String getProjectNameValidationMessage() {
        return (String) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", projectname);
    }

}
