package ecom.Bitm.SwagLabs.login;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.Bitm.SwagLabs.Utility.PropertyFileUtility;
import ecom.Bitm.SwagLabs.Utility.WebDriverUtility;
import ecom.Bitm.SwagLabs.baseClass.BaseClass01;
import ecom.Bitm.SwagLabs.pomPages.LoginPage;

public class Loginpage_Test extends BaseClass01 {

	@Test(priority = 1, groups = {"smoke","pos"})
	public void loginverify() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.displayloginpage();
		Reporter.log("Login page is displayed with username, password and Login controls", true);
	}

	@Test(priority = 2, groups = {"integration","neg"})
	public void logininvalid() throws Exception {
		LoginPage loginPage = new LoginPage(driver);

		String username = PropertyFileUtility.getData("username1");
		String password = PropertyFileUtility.getData("password1");

		loginPage.getUserNameTF(username);
		loginPage.getPasswordTF(password);
		loginPage.getLoginbtn();

		loginPage.ErrorDisplay();
		Reporter.log("Login rejected", true);
		WebDriverUtility wwUtil=new WebDriverUtility();
	    wwUtil.takingScreenshot("logininvalid", driver);

	}

	@Test(priority = 3,groups = {"functional","neg"})
	public void Usernameinvalid() throws Exception {
		LoginPage loginPage = new LoginPage(driver);

		String username = PropertyFileUtility.getData("username1");
		String password = PropertyFileUtility.getData("password");

		loginPage.getUserNameTF(username);
		loginPage.getPasswordTF(password);
		loginPage.getLoginbtn();

		loginPage.ErrorDisplay();
		Reporter.log("Login rejected by invalid Username", true);
		WebDriverUtility wwUtil=new WebDriverUtility();
	    wwUtil.takingScreenshot("Username invalid", driver);
	}

	@Test(priority = 4,groups = {"functional","neg"})
	public void Passwordinvalid() throws Exception {
		LoginPage loginPage = new LoginPage(driver);

		String username = PropertyFileUtility.getData("username");
		String password = PropertyFileUtility.getData("password1");

		loginPage.getUserNameTF(username);
		loginPage.getPasswordTF(password);
		loginPage.getLoginbtn();

		loginPage.ErrorDisplay();
		Reporter.log("Login rejected by invalid Password", true);
		WebDriverUtility wwUtil=new WebDriverUtility();
	    wwUtil.takingScreenshot("Password invalid", driver);

	}

	@Test(priority = 5,groups = {"functional","neg"})
	public void blankUsernameinvalid() throws Exception {
		LoginPage loginPage = new LoginPage(driver);

		String password = PropertyFileUtility.getData("password");

		loginPage.getPasswordTF(password);
		loginPage.getLoginbtn();

		loginPage.ErrorDisplay();
		Reporter.log("Login rejected by Blank Username", true);
		WebDriverUtility wwUtil=new WebDriverUtility();
	    wwUtil.takingScreenshot("Blank username", driver);
	}

	@Test(priority = 6,groups = {"functional","neg"})
	public void blankPasswordinvalid() throws Exception {
		LoginPage loginPage = new LoginPage(driver);

		String username = PropertyFileUtility.getData("username1");

		loginPage.getUserNameTF(username);
		loginPage.getLoginbtn();

		loginPage.ErrorDisplay();
		Reporter.log("Login rejected by Blank Password", true);
		WebDriverUtility wwUtil=new WebDriverUtility();
	    wwUtil.takingScreenshot("blank Password invalid", driver);
	}

	@Test(priority = 7,groups = {"functional","neg"})
	public void blankPasswordandUsernameinvalid() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getLoginbtn();

		loginPage.ErrorDisplay();
		Reporter.log("Login rejected by both blank username and password", true);
		WebDriverUtility wwUtil=new WebDriverUtility();
	    wwUtil.takingScreenshot("Both Blank un and pw", driver);
	}

	@Test(priority = 8,groups = {"functional","neg"})
	public void leadingspacesusename() throws Exception {
		LoginPage loginPage = new LoginPage(driver);

		String username = PropertyFileUtility.getData("username");
		username += " ";
		String password = PropertyFileUtility.getData("password");

		loginPage.getUserNameTF(username);
		loginPage.getPasswordTF(password);
		loginPage.getLoginbtn();

		loginPage.ErrorDisplay();
		Reporter.log("Login rejected by Leading Space in username", true);
		WebDriverUtility wwUtil=new WebDriverUtility();
	    wwUtil.takingScreenshot("leadingspacesusename", driver);
	}

	@Test(priority = 9,groups = {"functional","neg"})
	public void trailingspacesusename() throws Exception {
		LoginPage loginPage = new LoginPage(driver);

		String username = PropertyFileUtility.getData("username");
		username = " " + username;
		String password = PropertyFileUtility.getData("password");

		loginPage.getUserNameTF(username);
		loginPage.getPasswordTF(password);
		loginPage.getLoginbtn();

		loginPage.ErrorDisplay();
		Reporter.log("Login rejected by Trailing space in Username", true);
		WebDriverUtility wwUtil=new WebDriverUtility();
	    wwUtil.takingScreenshot("trailing spaces usename", driver);

	}

	@Test(priority = 10,groups = {"smoke","functional","pos"})
	public void loginvalid() throws IOException {
		LoginPage loginPage = new LoginPage(driver);

		String username = PropertyFileUtility.getData("username");
		String password = PropertyFileUtility.getData("password");

		loginPage.getUserNameTF(username);
		loginPage.getPasswordTF(password);
		loginPage.getLoginbtn();
		Reporter.log("Login test executed", true);

	}

}