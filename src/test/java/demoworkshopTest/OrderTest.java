package demoworkshopTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import demoworkshopPages.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class OrderTest {
    private static final Logger logger = Logger.getLogger(OrderTest.class);
    private ExtentReports extent;
    private ExtentTest test;

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ProductPage productPage;
    CartSummaryPage cartSummaryPage;
    CheckoutPage checkoutPage;
    OrderConfirmationPage orderConfirmationPage;

    Properties properties = new Properties();
    String userHome = System.getProperty("user.home");

    @BeforeTest
    public void setUp() throws IOException {
        // Load properties file
        String propertyFilePath = userHome + "\\eclipse\\Selenium\\LearnSelenium\\src\\test\\java\\resources\\testData.properties";
        try (FileInputStream inputStream = new FileInputStream(propertyFilePath)) {
            properties.load(inputStream);
        }

        // Load log4j properties
        String log4jPropertyFilePath = userHome + "\\eclipse\\Selenium\\LearnSelenium\\log4j.properties";
        PropertyConfigurator.configure(log4jPropertyFilePath);

        // Initialize ExtentReports
        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/MyExtentReport.html", true);
        extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));

        // Driver path
        String chromeDriverPath = userHome + "\\eclipse\\Selenium\\LearnSelenium\\Drivers\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/login");

        // Initialize page objects
        initializePageObjects();

        logger.info("Browser launched and navigated to login page.");
        test = extent.startTest("Order Test", "Test to validate order process.");
        test.log(LogStatus.INFO, "Browser launched and navigated to login page.");
    }

    private void initializePageObjects() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartSummaryPage = new CartSummaryPage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
    }

    @Test
    public void testOrderProcess() {
        // Login Page
        loginPage.login(properties.getProperty("login.email"), properties.getProperty("login.password"));
        logger.info("Login Successful");
        test.log(LogStatus.INFO, "Login Successful");

        // Home Page
        homePage.printCategories();
        homePage.scrollPage();
        homePage.takeScreenshot("screenshot");
        homePage.clickExpensiveComputer();
        logger.info("Build Your Own Expensive Computer Clicked");
        test.log(LogStatus.INFO, "Build Your Own Expensive Computer Clicked");

        // Product Page
        productPage.configureProduct();

        // Randomly select quantity
        String selectedQuantity = getRandomValue(properties.getProperty("product.quantities").split(","));
        productPage.setQuantity(selectedQuantity);
        productPage.addToCart();
        logger.info("Product added to cart with quantity: " + selectedQuantity);
        test.log(LogStatus.INFO, "Product added to cart with quantity: " + selectedQuantity);

        // Cart Summary Page
        cartSummaryPage.goToCart();

        // Randomly select shipping details
        String selectedCountry = getRandomValue(properties.getProperty("shipping.countries").split(","));
        cartSummaryPage.selectCountry(selectedCountry);
        cartSummaryPage.agreeToTerms();
        cartSummaryPage.proceedToCheckout();
        logger.info("Proceed to checkout for country: " + selectedCountry);
        test.log(LogStatus.INFO, "Proceed to checkout for country: " + selectedCountry);

        // Checkout Page
        String selectedCity = getRandomValue(properties.getProperty("shipping.cities").split(","));
        String selectedAddress = getRandomValue(properties.getProperty("shipping.addresses").split(","));
        String selectedPostalCode = getRandomValue(properties.getProperty("shipping.postalCodes").split(","));
        String selectedPhone = getRandomValue(properties.getProperty("shipping.phones").split(","));

        checkoutPage.fillBillingDetails(selectedCountry, selectedCity, selectedAddress, selectedPostalCode, selectedPhone);
        checkoutPage.continueCheckout();
        checkoutPage.continueToShipping();
        checkoutPage.continueToShippingMethod();
        checkoutPage.continueToPaymentMethod();        
        checkoutPage.continueToPaymentInfo();        
        checkoutPage.confirmOrder();

        // Order Confirmation Page
        Assert.assertEquals(orderConfirmationPage.getOrderConfirmationMessage(), "Your order has been successfully processed!");
        String orderId = orderConfirmationPage.getOrderNumber();
        logger.info("Order ID: " + orderId);
        test.log(LogStatus.INFO, "Order ID: " + orderId);
    }

    // Helper method to get a random value from an array
    private String getRandomValue(String[] array) {
        Random random = new Random();
        int index = random.nextInt(array.length);
        return array[index];
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        logger.info("Browser closed.");
        test.log(LogStatus.INFO, "Browser closed.");
        extent.endTest(test); 
        extent.flush(); 
        extent.close(); 
    }
}
