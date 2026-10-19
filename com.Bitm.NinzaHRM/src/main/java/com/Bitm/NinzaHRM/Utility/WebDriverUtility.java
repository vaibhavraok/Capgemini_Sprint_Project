package com.Bitm.NinzaHRM.Utility;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class WebDriverUtility {

    public void takingScreenshot(String fileName, WebDriver driver) throws Exception {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File temp = ts.getScreenshotAs(OutputType.FILE);
        // Create screenshots folder if it does not exist
        File folder = new File("./screenshots");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        // Screenshot destination
        File per = new File(folder, fileName + ".png");
        FileHandler.copy(temp, per);
    }
}