package ecom.Bitm.SwagLabs.product;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ecom.Bitm.SwagLabs.Utility.WebDriverUtility;
import ecom.Bitm.SwagLabs.baseClass.BaseClass01;
import ecom.Bitm.SwagLabs.pomPages.LoginPage;
import ecom.Bitm.SwagLabs.pomPages.ProductsPage;

public class ProductPage_Test extends BaseClass01 {

    @DataProvider
    public Object[][] loginDetails() throws EncryptedDocumentException, IOException {
        FileInputStream file = new FileInputStream("./src/test/resources/TestData.xlsx");
        Workbook wb = WorkbookFactory.create(file);
        Sheet sh = wb.getSheet("Sheet1");
        int rowCount = sh.getLastRowNum();
        Object[][] obj = new Object[rowCount][1];
        for (int row = 1; row <= rowCount; row++) {
            obj[row - 1][0] = sh.getRow(row).getCell(0).getStringCellValue();
        }
        wb.close();
        file.close();
        return obj;
    }

    @Test(priority = 1, groups = {"smoke", "pos"})
    public void Productpage1() throws IOException, InterruptedException {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProductsPage productPage = new ProductsPage(driver);
        Thread.sleep(3000);
        productPage.verifyProductsPage();
        Reporter.log("Product page is visible", true);
    }

    @Test(priority = 2, groups = {"smoke", "pos"})
    public void productvisible() throws IOException, InterruptedException {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProductsPage productpage = new ProductsPage(driver);
        Thread.sleep(3000);
        productpage.getProduct();
        Reporter.log("Products are visible", true);
    }

    @Test(priority = 3, groups = {"integration", "pos"})
    public void CorrespondingProductPage() throws IOException, InterruptedException {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProductsPage productpage = new ProductsPage(driver);
        Thread.sleep(3000);
        productpage.getClickproduct();
        Reporter.log("Corresponding Product is navigated", true);
    }

    @Test(priority = 4, groups = {"integration", "pos"})
    public void VerifyCartBadge() throws IOException, InterruptedException {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProductsPage productpage = new ProductsPage(driver);
        Thread.sleep(4000);
        productpage.getAddtocart();
        Thread.sleep(4000);
        productpage.verifyCartContainsOneItem();
        Reporter.log("Product cart page is updated", true);
    }

    @Test(priority = 5, groups = {"functional", "pos"})
    public void productCartControl() throws IOException, InterruptedException {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProductsPage productpage = new ProductsPage(driver);
        Thread.sleep(4000);
        productpage.getInventoryItems();
        Reporter.log("Every product has cart control", true);
    }

    @Test(priority = 6, groups = {"functional", "pos"})
    public void productcartnavigation() throws Exception {
        LoginPage l = new LoginPage(driver);
        l.login();

        ProductsPage productpage = new ProductsPage(driver);
        Thread.sleep(3000);
        productpage.clickCart();

        Reporter.log("Cart icon is clickable and cart page is opened", true);
    }
 

    @Test(priority = 7, groups = {"functional", "pos"})
    public void productNavigationMenu() throws IOException, InterruptedException {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProductsPage productsPage = new ProductsPage(driver);
        Thread.sleep(4000);
        productsPage.menu();
        Reporter.log("Navigation menu is working", true);
    }

    @Test(priority = 8, groups = {"system", "pos"})
    public void ExpectedProduct() throws IOException, InterruptedException {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProductsPage productspage = new ProductsPage(driver);
        Thread.sleep(4000);
        productspage.getExpectedproductdata();
        Reporter.log("Expected product is visible", true);
    }

    @Test(priority = 9, groups = {"system", "pos"})
    public void productVisibleData() throws IOException, InterruptedException {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProductsPage productpage = new ProductsPage(driver);
        Thread.sleep(4000);
        productpage.Visibledata();
        Reporter.log("product Data is visible", true);
    }

    @Test(priority = 10, groups = {"functional", "pos"})
    public void Productpagerefresh() throws IOException, InterruptedException {
        LoginPage l = new LoginPage(driver);
        l.login();
        ProductsPage productspage = new ProductsPage(driver);
        Thread.sleep(4000);
        productspage.productrefresh();
        Reporter.log("User is logged in After refresh", true);
    }

}
