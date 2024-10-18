package homework1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Homework1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webDriver.chrome.driver", "E:\\Downloads\\Setups\\chromedriver-win64\\chromedriver-win64");
		WebDriver driver = new ChromeDriver();
		//Login Page
		driver.get("https://demowebshop.tricentis.com/login");
		
		driver.manage().window().maximize();
		
		System.out.println("1 Navigation Successful");
		
		driver.findElement(By.xpath("//input[@id='Email' and @name='Email']")).sendKeys("komorebimiku45@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='Password' and @name='Password']")).sendKeys("Nagareboshi45*");
		
		driver.findElement(By.xpath("//input[@class='button-1 login-button' and @value='Log in']")).click();
		
		System.out.println("2 Login Successful");
		//Home page
		WebElement firstListbox = driver.findElement(By.xpath("(//div[@class='listbox'])[1]"));
		List<WebElement> links = firstListbox.findElements(By.xpath(".//a"));
		System.out.println("CATEGORIES");
		for (WebElement link : links) {
		    System.out.println(link.getText());
		}
		
		System.out.println("Number of links on the page: " + links.size());
		
        System.out.println("3 Menu Items Successful");
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0, 600);");
	    
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File srcFile = ts.getScreenshotAs(OutputType.FILE);
	    File destFile = new File("C://Users//Jeni//eclipse//Selenium//LearnSelenium//Screenshot//screenshot.png"); 
	    try {
	    	Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
        
	    System.out.println("4 Screenshot Successful");
	    
	    driver.findElement(By.linkText("Build your own expensive computer")).click();
		
	    System.out.println("5 Build your own expensive Computer - Successful");
	    //Product Details Page
	    WebElement processorFastRadioButton = driver.findElement(By.id("product_attribute_74_5_26_82"));
	    if (!processorFastRadioButton.isSelected()) {
	    	processorFastRadioButton.click();
	    }
	    
	    WebElement Ram8gbRadioButton = driver.findElement(By.id("product_attribute_74_6_27_85"));
	    if (!Ram8gbRadioButton.isSelected()) {
	    	Ram8gbRadioButton.click();
	    }
	    
	    WebElement Hhd400GbRadioButton = driver.findElement(By.id("product_attribute_74_3_28_87"));
	    if (!Hhd400GbRadioButton.isSelected()) {
	    	Hhd400GbRadioButton.click();
	    }
	    
	    WebElement softwareOfficeSuiteCheckbox = driver.findElement(By.id("product_attribute_74_8_29_89"));
	    if (!softwareOfficeSuiteCheckbox.isSelected()) {
	    	softwareOfficeSuiteCheckbox.click();
	    }

	    WebElement quantityTextbox = driver.findElement(By.id("addtocart_74_EnteredQuantity"));
	    quantityTextbox.clear(); 
	    quantityTextbox.sendKeys("2"); 
	    
	    System.out.println("6 Select Processor, RAM, HDD, Software and Change Qty to 2 - Successful");
	    
	    driver.findElement(By.id("add-to-cart-button-74")).click();
	    
	    System.out.println("7 Click on Add to Cart - Successful");
	    
	    WebElement cartLabel = driver.findElement(By.xpath("(//span[contains(text(), 'Shopping cart')])[1]"));

	    Actions actions = new Actions(driver);
	    actions.moveToElement(cartLabel).perform();

	    System.out.println("8 Mouse over on shopping cart - Successful");
	    
	    Thread.sleep(500);

	    WebElement goToCartButton = driver.findElement(By.xpath("//input[@value='Go to cart']"));

	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", goToCartButton);


	    System.out.println("9 Click on \"Go to Cart\" - Successful");
        //Shopping Cart Summary Page
	    WebElement countryDropdown = driver.findElement(By.xpath("//select[@id='CountryId' and @name='CountryId']"));
	    
	    Select selectcountryDropdown = new Select(countryDropdown);
	    
	    selectcountryDropdown.selectByVisibleText("United Kingdom");
	    
	    WebElement termsOfServiceCheckbox = driver.findElement(By.id("termsofservice"));
	    if (!termsOfServiceCheckbox.isSelected()) {
	    	termsOfServiceCheckbox.click();
	    }
	    
	    System.out.println("10 Country and Terms Of Service Selection - Successful");
	    
	    driver.findElement(By.xpath("//button[@id='checkout' and @value='checkout']")).click();
	    
	    System.out.println("11 Checkout - Successful");
	    
	    //Checkout Page
	    WebElement billingCountryDropdown = driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId' and @name='BillingNewAddress.CountryId']"));
	    
	    Select billingCountrySelect = new Select(billingCountryDropdown);
	    
	    billingCountrySelect.selectByVisibleText("United Kingdom");
	    
	    driver.findElement(By.xpath("//input[@id='BillingNewAddress_City' and @name='BillingNewAddress.City']")).sendKeys("Winchester");
	    driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1' and @name='BillingNewAddress.Address1']")).sendKeys("AA Road");
	    driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode' and @name='BillingNewAddress.ZipPostalCode']")).sendKeys("SO22 0XY");
	    driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber' and @name='BillingNewAddress.PhoneNumber']")).sendKeys("07442345678");
	  
	    driver.findElement(By.cssSelector("input.button-1.new-address-next-step-button[value='Continue']")).click();
	    
	    System.out.println("12 Billing Address - Successful");

	    Thread.sleep(700);
	    
	    driver.findElement(By.xpath("(//input[@class='button-1 new-address-next-step-button' and @value='Continue'])[2]")).click();

	    Thread.sleep(700);
	    	    
	    driver.findElement(By.xpath("//input[@class='button-1 shipping-method-next-step-button' and @value='Continue']")).click();
	    
	    System.out.println("13 Shipping Method - Successful");
	    
	    Thread.sleep(700);
	    
	    driver.findElement(By.xpath("//input[@class='button-1 payment-method-next-step-button' and @value='Continue']")).click();
	    
	    System.out.println("14 Payment Method - Successful");
	    
	    Thread.sleep(700);
	    
	    driver.findElement(By.xpath("//input[@class='button-1 payment-info-next-step-button' and @value='Continue']")).click();
	    
	    System.out.println("15 Payment Information - Successful");
	    
	    Thread.sleep(700);
	    
	    driver.findElement(By.xpath("//input[@class='button-1 confirm-order-next-step-button' and @value='Confirm']")).click();
	    
	    System.out.println("16 Confirm Order - Successful");
	    
	    Thread.sleep(800);
        //Order Confirmation Page
	    WebElement messageElement = driver.findElement(By.xpath("//div[@class='title']/strong"));
	    String displayedMessage = messageElement.getText();
	    String expectedMessage = "Your order has been successfully processed!";
	    if (displayedMessage.equals(expectedMessage)) {
	        System.out.println("19 Verify Message - Successful");
	    } else {
	        System.out.println("Message verification failed.");
	    }

	    WebElement orderNumberElement = driver.findElement(By.xpath("//ul[@class='details']/li[1]"));
	    String orderNumberText = orderNumberElement.getText();
	    String orderId = orderNumberText.replace("Order number: ", "").trim();
	    System.out.println("Order ID: " + orderId);

	    System.out.println("20 Order ID Display - Successful");

	    WebElement continueButton = driver.findElement(By.xpath("//input[@class='button-2 order-completed-continue-button' and @value='Continue']"));
	    continueButton.click();

	    Thread.sleep(5000);
	    
		driver.quit();
	}

}
