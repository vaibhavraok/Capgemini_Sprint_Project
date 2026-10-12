
package ecom.Bitm.SwagLabs.baseClass;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import ecom.Bitm.SwagLabs.Utility.PropertyFileUtility;

public class BaseClass01 {
    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("Execution Started");
    }
    
    @BeforeTest(alwaysRun = true)
    public void Bt() {
    	System.out.println("Pre-conditions");
    }
    
    @Parameters("browser")
    @BeforeClass(alwaysRun = true,groups = {"smoke","integration","functional","system","pos","neg"})
    public void beforeClass(@Optional("chrome") String browser) throws IOException {
//        String browser = PropertyFileUtility.getData("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            // Disable Chrome browser notifications
            options.addArguments("--disable-notifications");
            // Disable Save Password popup
            options.addArguments("--disable-save-password-bubble");
            // Disable Chrome Password Manager
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_leak_detection", false);
            options.setExperimentalOption("prefs", prefs);
            // Launch Chrome
            driver = new ChromeDriver(options);
        }else if (browser.equalsIgnoreCase("edge")) {
 
        	Map<String, Object> edgePrefs = new HashMap<>();
            edgePrefs.put("credentials_enable_service", false);
            edgePrefs.put("profile.password_manager_enabled", false);
            edgePrefs.put("profile.password_manager_leak_detection", false);

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setExperimentalOption("prefs", edgePrefs);

            driver = new EdgeDriver(edgeOptions);

        }  else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            driver = new FirefoxDriver(firefoxOptions);
        }
        
        else {

            throw new IllegalArgumentException("Unsupported Browser: " + browser);
        }

        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       
        System.out.println("Browser launched: " + browser);
    }

    @BeforeMethod(groups = {"smoke","integration","functional","system","pos","neg"})
    public void beforeMethod() throws IOException {
        driver.get(PropertyFileUtility.getData("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        System.out.println("Test Method Completed");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @AfterTest(alwaysRun = true)
    public void At() {
    	System.out.println("Post-condition");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("Execution Completed");
    }

}
