package seleniumScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class Exercise4 {

    public static void main(String[] args) {

    	System.setProperty("webDriver.chrome.driver", "E:\\Downloads\\Setups\\chromedriver-win64\\chromedriver-win64");
		WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        
        driver.get("https://demowebshop.tricentis.com/login");
        
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("Number of links on the page: " + links.size());

        for (WebElement link : links) {
            System.out.println("Link text: " + link.getText());
        }

        driver.quit();
    }
}

