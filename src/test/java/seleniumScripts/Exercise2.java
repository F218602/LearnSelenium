package seleniumScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercise2 {

	public static void main(String[] args) {
		System.setProperty("webDriver.chrome.driver", "E:\\Downloads\\Setups\\chromedriver-win64\\chromedriver-win64");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demowebshop.tricentis.com/login");
		
		driver.manage().window().maximize();

		WebElement registerLink= driver.findElement(By.linkText("Register"));
		
		registerLink.click();
		
		driver.navigate().back();
		
		driver.navigate().forward();

		driver.navigate().to("https://demowebshop.tricentis.com/login");
		
		driver.navigate().refresh();
		
		driver.quit();


		
	}

}
