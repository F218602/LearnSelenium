package seleniumScripts;

//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercise1 {

	public static void main(String[] args) {
		System.setProperty("webDriver.chrome.driver", "E:\\Downloads\\Setups\\chromedriver-win64\\chromedriver-win64");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demowebshop.tricentis.com/");
		
		driver.manage().window().maximize();
		
		String title = driver.getTitle();
		
		System.out.println("Page Title: " + title);
		
		int titleLength = title.length();
		
		System.out.println("Title Length: " + titleLength);
		
		String currentUrl = driver.getCurrentUrl();
		
		String expectedUrl = "https://demowebshop.tricentis.com/";
		
		if(currentUrl.equals(expectedUrl)) {
            System.out.println("You are on the correct page.");
        } else {
            System.out.println("You are NOT on the correct page.");
        }
		
		String pageSource = driver.getPageSource();
		
		int pageSourceLength = pageSource.length();
		
		System.out.println("Page Source Length: " + pageSourceLength);
		
		driver.quit();
	}

}
