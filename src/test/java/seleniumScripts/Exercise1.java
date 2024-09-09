package seleniumScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercise1 {

	public static void main(String[] args) {
		System.setProperty("webDriver.chrome.driver", "E:\\Downloads\\Setups\\chromedriver-win64\\chromedriver-win64");
		WebDriver driver = new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
		driver.quit();
	}

}
