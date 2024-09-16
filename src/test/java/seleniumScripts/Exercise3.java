package seleniumScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Exercise3 {

	public static void main(String[] args) throws InterruptedException {
        
        System.setProperty("webdriver.chrome.driver", "E:\\Downloads\\Setups\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();

        driver.get("https://demo.automationtesting.in/Alerts.html");

        Thread.sleep(5000); 
        
        // The below try block is not necessary if the script is ran in office network
        try {
            
            WebElement consentButton = driver.findElement(By.cssSelector(".fc-button.fc-cta-consent.fc-primary-button"));
            consentButton.click();
            System.out.println("Clicked the 'Consent' button.");
        } catch (Exception e) {
            System.out.println("Consent button not found or failed to click.");
            e.printStackTrace();
        }

        
        driver.findElement(By.xpath("//a[text()='Alert with OK & Cancel ']")).click();

        driver.findElement(By.xpath("//button[text()='click the button to display a confirm box ']")).click();

        Alert alert = driver.switchTo().alert();
        
        String alertMessage = alert.getText();
        
        System.out.println("Alert message: " + alertMessage);
        
        alert.dismiss(); 

        driver.findElement(By.xpath("//a[text()='Alert with Textbox ']")).click();

        
        driver.findElement(By.xpath("//button[text()='click the button to demonstrate the prompt box ']")).click();

        
        Alert promptAlert = driver.switchTo().alert();
        
        promptAlert.sendKeys("ABC");  

        Thread.sleep(5000); 
        
        promptAlert.accept(); 

        Thread.sleep(5000);
        
        WebElement resultText = driver.findElement(By.id("demo1"));
        
        String capturedText = resultText.getText();
        
        System.out.println("Captured Text: " + capturedText);

        driver.quit();
    }
}
