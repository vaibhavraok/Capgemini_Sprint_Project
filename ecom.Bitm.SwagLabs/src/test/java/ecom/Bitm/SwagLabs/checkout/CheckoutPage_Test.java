package ecom.Bitm.SwagLabs.checkout;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ecom.Bitm.SwagLabs.Utility.PropertyFileUtility;
import ecom.Bitm.SwagLabs.Utility.WebDriverUtility;
import ecom.Bitm.SwagLabs.baseClass.BaseClass01;
import ecom.Bitm.SwagLabs.pomPages.CartPage;
import ecom.Bitm.SwagLabs.pomPages.CheckoutPage;
import ecom.Bitm.SwagLabs.pomPages.LoginPage;
import ecom.Bitm.SwagLabs.pomPages.ProductsPage;

public class CheckoutPage_Test extends BaseClass01 {

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() throws EncryptedDocumentException, IOException {
        FileInputStream file = new FileInputStream("./src/test/resources/TestData.xlsx");
        Workbook wb = WorkbookFactory.create(file);
        Sheet sh = wb.getSheet("Sheet1");

        Assert.assertNotNull(sh, "Checkout data sheet is not available");

        int rowCount = sh.getLastRowNum();
        Object[][] obj = new Object[rowCount][3];

        for (int row = 1; row <= rowCount; row++) {
            obj[row - 1][0] = sh.getRow(row).getCell(0).getStringCellValue();
            obj[row - 1][1] = sh.getRow(row).getCell(1).getStringCellValue();
            obj[row - 1][2] = String.valueOf((int) sh.getRow(row).getCell(2).getNumericCellValue());
        }

        wb.close();
        file.close();
        return obj;
    }

    @Test(priority = 1, groups = {"smoke", "pos"})
    public void checkoutPageOpens() throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
    	CheckoutPage c = new CheckoutPage(driver);
        c.openCheckoutWithBackpack();
        Reporter.log("Checkout page opened successfully", true);
    }

    @Test(priority = 2, groups = {"functional", "pos"}, dataProvider = "checkoutData")
    public void validCustomerInfo(String firstName, String lastName, String postalCode) throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
    	CheckoutPage c = new CheckoutPage(driver);
    	CheckoutPage checkout = c.openCheckoutWithBackpack();
        checkout.enterCustomerInformation(firstName, lastName, postalCode);
        checkout.clickContinue();
        checkout.verifyCheckoutOverviewDisplayed();
        Reporter.log("Valid customer information accepted", true);
    }

    @Test(priority = 3, groups = {"integration", "pos"}, dataProvider = "checkoutData")
    public void cartProductInOverview(String firstName, String lastName, String postalCode) throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
    	ProductsPage products = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);
        String product = cart.backpack();
        String price = products.getProductPrice(product);
        products.addProductToCart(product);

        cart.openCart();
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.verifyCheckoutInformationPage();
        checkout.enterCustomerInformation(firstName, lastName, postalCode);
        checkout.clickContinue();
        checkout.verifyCheckoutOverviewDisplayed();
        checkout.verifyProductInOverview(product, price);
        Reporter.log("Cart product carried into Checkout Overview", true);
    }

    @Test(priority = 4, groups = {"system", "pos"}, dataProvider = "checkoutData")
    public void paymentShippingInfo(String firstName, String lastName, String postalCode) throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
    	CheckoutPage c = new CheckoutPage(driver);
    	CheckoutPage checkout = c.openOverviewWithBackpack(firstName, lastName, postalCode);
        checkout.verifyPaymentAndShippingInformation();
        Reporter.log("Payment and shipping information displayed", true);
    }

    @Test(priority = 6, groups = {"functional", "neg"})
    public void allFieldsMissing() throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
    	CheckoutPage c = new CheckoutPage(driver);
    	CheckoutPage checkout = c.openCheckoutWithBackpack();
        checkout.enterCustomerInformation("", "", "");
        checkout.clickContinue();
        checkout.verifyMandatoryFieldError();
        Reporter.log("Checkout correctly blocked for missing mandatory field", true);
        WebDriverUtility wUtil = new WebDriverUtility();
        wUtil.takingScreenshot("RequiredFieldMissing", driver);
    }

    @Test(priority = 7, groups = {"functional", "neg"}, dataProvider = "checkoutData")
    public void firstNameMissing(String firstName, String lastName, String postalCode) throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
    	CheckoutPage c = new CheckoutPage(driver);
    	CheckoutPage checkout = c.openCheckoutWithBackpack();
        checkout.enterCustomerInformation("", lastName, postalCode);
        checkout.clickContinue();
        checkout.verifyMandatoryFieldError();
        Reporter.log("Checkout correctly blocked for missing first name", true);
        WebDriverUtility wUtil = new WebDriverUtility();
        wUtil.takingScreenshot("FirstNameFieldMissing", driver);
    }

    @Test(priority = 8, groups = {"functional", "neg"}, dataProvider = "checkoutData")
    public void lastNameMissing(String firstName, String lastName, String postalCode) throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
    	CheckoutPage c = new CheckoutPage(driver);
    	CheckoutPage checkout = c.openCheckoutWithBackpack();
        checkout.enterCustomerInformation(firstName, "", postalCode);
        checkout.clickContinue();
        checkout.verifyMandatoryFieldError();
        Reporter.log("Checkout correctly blocked for missing last name", true);
        WebDriverUtility wUtil = new WebDriverUtility();
        wUtil.takingScreenshot("LastNameFieldMissing", driver);
    }

    @Test(priority = 9, groups = {"functional", "neg"}, dataProvider = "checkoutData")
    public void postalCodeMissing(String firstName, String lastName, String postalCode) throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
    	CheckoutPage c = new CheckoutPage(driver);
    	CheckoutPage checkout = c.openCheckoutWithBackpack();
        checkout.enterCustomerInformation(firstName, lastName, "");
        checkout.clickContinue();
        checkout.verifyMandatoryFieldError();
        Reporter.log("Checkout correctly blocked for missing postal code", true);
        WebDriverUtility wUtil = new WebDriverUtility();
        wUtil.takingScreenshot("PostalCodeFieldMissing", driver);
    }

    @Test(priority = 10, groups = {"integration", "pos"}, dataProvider = "checkoutData")
    public void purchaseCompleted(String firstName, String lastName, String postalCode) throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
        CheckoutPage c = new CheckoutPage(driver);
    	CheckoutPage checkout = c.openOverviewWithBackpack(firstName, lastName, postalCode);
        checkout.clickFinish();
        checkout.verifyCompletedPage();
        Reporter.log("Valid purchase completed successfully", true);
    }

    @Test(priority = 11, groups = {"system", "pos"}, dataProvider = "checkoutData")
    public void orderConfirmation(String firstName, String lastName, String postalCode) throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
        CheckoutPage c = new CheckoutPage(driver);
    	CheckoutPage checkout = c.openOverviewWithBackpack(firstName, lastName, postalCode);
        checkout.clickFinish();
        checkout.verifyOrderConfirmation();
        Reporter.log("Order confirmation message displayed", true);
    }

    @Test(priority = 12, groups = {"integration", "pos"}, dataProvider = "checkoutData")
    public void cartClearedAfterPurchase(String firstName, String lastName, String postalCode) throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.login();
        CheckoutPage c = new CheckoutPage(driver);
    	CheckoutPage checkout = c.openOverviewWithBackpack(firstName, lastName, postalCode);
        checkout.clickFinish();
        checkout.verifyCompletedPage();
        checkout.clickBackHome();

        CartPage cart = new CartPage(driver);
        cart.verifyCartBadgeCount("");
        Reporter.log("Completed purchase cleared the cart", true);
    }
}