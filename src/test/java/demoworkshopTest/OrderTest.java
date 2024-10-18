package demoworkshopTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import demoworkshopPages.*;

public class OrderTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ProductPage productPage;
    CartSummaryPage cartSummaryPage;
    CheckoutPage checkoutPage;
    OrderConfirmationPage orderConfirmationPage;

    @BeforeTest
    public void setUp() {
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
    }

    @Test
    public void testOrderProcess() {
        // Login Page
        loginPage.login("komorebimiku45@gmail.com", "Nagareboshi45*");
        System.out.println("Login Successful");

        // Home Page
        homePage.printCategories();
        homePage.scrollPage();
        homePage.takeScreenshot("screenshot");
        homePage.clickExpensiveComputer();
        System.out.println("Build Your Own Expensive Computer Clicked");

        // Product Page
        productPage.configureProduct();
        productPage.setQuantity("2");
        productPage.addToCart();
        System.out.println("Product added to cart");

        // Cart Summary Page
        cartSummaryPage.goToCart();
        cartSummaryPage.selectCountry("United Kingdom");
        cartSummaryPage.agreeToTerms();
        cartSummaryPage.proceedToCheckout();
        System.out.println("Proceed to checkout");

        // Checkout Page
        checkoutPage.fillBillingDetails("United Kingdom", "Winchester", "AA Road", "SO22 0XY", "07442345678");
        checkoutPage.continueCheckout();
        checkoutPage.continueToShipping();
        checkoutPage.continueToShippingMethod();
        checkoutPage.continueToPaymentMethod();        
        checkoutPage.continueToPaymentInfo();        
        checkoutPage.confirmOrder();
        

        // Order Confirmation Page
        Assert.assertEquals(orderConfirmationPage.getOrderConfirmationMessage(), "Your order has been successfully processed!");
        String orderId = orderConfirmationPage.getOrderNumber();
        System.out.println("Order ID: " + orderId);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

