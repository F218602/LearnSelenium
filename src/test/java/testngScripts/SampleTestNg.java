package testngScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.ImageUtils;

import java.io.IOException;

public class SampleTestNg {
	
		WebDriver driver;
		
		
		@BeforeClass
	    public void setUp() {
	        System.setProperty("webdriver.chrome.driver", "E:\\Downloads\\Setups\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        System.out.println("BeforeClass: Browser setup completed.");
	    }

	    @BeforeMethod
	    public void navigateToPage() {
	        driver.get("https://butterfly-conservation.org/butterflies/identify-a-butterfly");
	        System.out.println("BeforeMethod: Identify a Butterfly.");
	    }

	    @Test
	    public void searchTest() throws IOException{
	        System.out.println("Test: Searching Adonis Blue");
	        
	        driver.findElement(By.linkText("Adonis Blue")).click();
	        
	        // Locate the image element by its alt text
	        WebElement imgElement = driver.findElement(By.xpath("//img[@alt='Adonis Blue (male/upperwing) by Peter Eeles']"));
	        
	        // Get the src URL of the image
	        String imgUrl = imgElement.getAttribute("src");
	        System.out.println("Image URL: " + imgUrl);
	        
	        // Download the image
	        ImageUtils.downloadImage(imgUrl, "target/adonis_blue_male_upperwing.jpg");
            System.out.println("Image saved successfully.");
        }

	    @AfterMethod
	    public void postTestActions() {
	        System.out.println("AfterMethod: Test case completed.");
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	        System.out.println("AfterClass: Browser closed and resources cleaned up.");
	    }
		
	}
