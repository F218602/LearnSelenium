package seleniumScripts;


import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Textbox {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webDriver.chrome.driver", "E:\\Downloads\\Setups\\chromedriver-win64\\chromedriver-win64");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();

		driver.get("https://demo.automationtesting.in/Alerts.html");
		
		driver.findElement(By.xpath("//a[text()='Alert with Textbox ']")).click();
		
		driver.findElement(By.xpath("//button[text()='click the button to demonstrate the prompt box ']")).click();
		
		Alert promptAlert=driver.switchTo().alert();

		promptAlert.sendKeys("ABC");
		
		Thread.sleep(5000);
		
		promptAlert.accept();
		
		Thread.sleep(5000);
		
//		alert.accept();
//		
//		alert.dismiss();
//				
		driver.quit();
//		
		

	}

}