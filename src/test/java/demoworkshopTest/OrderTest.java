package demoworkshopTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import demoworkshopPages.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import org.apache.log4j.Logger;

public class OrderTest {
    private static final Logger logger = Logger.getLogger(OrderTest.class);
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ProductPage productPage;
    CartSummaryPage cartSummaryPage;
    CheckoutPage checkoutPage;
    OrderConfirmationPage orderConfirmationPage;
    
    Properties properties = new Properties();

    @BeforeTest
    public void setUp() throws IOException {
        // Load properties file
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Jeni\\eclipse\\Selenium\\LearnSelenium\\src\\test\\java\\resources\\testData.properties");
        properties.load(inputStream);
        
        String userHome = System.getProperty("user.home");
        String chromeDriverPath = userHome + "\\eclipse\\Selenium\\LearnSelenium\\Drivers\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/login");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartSummaryPage = new CartSummaryPage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        
        logger.info("Browser launched and navigated to login page.");
    }

    @Test
    public void testOrderProcess() {
        // Login Page
        loginPage.login(properties.getProperty("login.email"), properties.getProperty("login.password"));
        logger.info("Login Successful");

        // Home Page
        homePage.printCategories();
        homePage.scrollPage();
        homePage.takeScreenshot("screenshot");
        homePage.clickExpensiveComputer();
        logger.info("Build Your Own Expensive Computer Clicked");

        // Product Page
        productPage.configureProduct();

        // Randomly select quantity
        String selectedQuantity = getRandomValue(properties.getProperty("product.quantities").split(","));
        productPage.setQuantity(selectedQuantity);
        productPage.addToCart();
        logger.info("Product added to cart with quantity: " + selectedQuantity);

        // Cart Summary Page
        cartSummaryPage.goToCart();
        
        // Randomly select shipping details
        String selectedCountry = getRandomValue(properties.getProperty("shipping.countries").split(","));
        cartSummaryPage.selectCountry(selectedCountry);
        cartSummaryPage.agreeToTerms();
        cartSummaryPage.proceedToCheckout();
        logger.info("Proceed to checkout for country: " + selectedCountry);

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
    }
}
