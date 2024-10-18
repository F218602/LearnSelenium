package demoworkshopPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class HomePage {
    WebDriver driver;

    // Locate the first listbox containing categories
    WebElement firstListbox = driver.findElement(By.xpath("(//div[@class='listbox'])[1]"));
	List<WebElement> links = firstListbox.findElements(By.xpath(".//a"));
	

    // Locate the link to "Build your own expensive computer"
    @FindBy(linkText = "Build your own expensive computer")
    WebElement expensiveComputerLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to print the categories and return the count
    public int printCategories() {
    	System.out.println("CATEGORIES");
    	for (WebElement link : links) {
    	    System.out.println(link.getText());
    	}
        System.out.println("Number of categories: " + links.size());
        return links.size();
    }

    // Method to scroll the page
    public void scrollPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 600);");
        System.out.println("Page scrolled successfully");
    }

    public void takeScreenshot(String dynamicName) {
        // Use user's home directory for dynamic path
        String userHome = System.getProperty("user.home");
        String basePath = userHome + "\\eclipse\\Selenium\\LearnSelenium\\Screenshot"; // User-specific base path
        new File(basePath).mkdirs(); // Create the directory if it doesn't exist

        String fullPath = getUniqueScreenshotPath(basePath + "\\" + dynamicName + ".png");

        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File(fullPath);

        try {
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot taken successfully: " + fullPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to take screenshot");
        }
    }

    private String getUniqueScreenshotPath(String basePath) {
        if (!basePath.endsWith(".png")) {
            throw new IllegalArgumentException("Base path must end with .png");
        }

        int screenshotNumber = 1;
        String screenshotPath = basePath;

        if (!new File(screenshotPath).exists()) {
            return screenshotPath;
        }

        do {
            screenshotPath = basePath.replace(".png", "_" + screenshotNumber + ".png");
            screenshotNumber++;
        } while (new File(screenshotPath).exists());

        return screenshotPath;
    }

//    private String getUniqueScreenshotPath(String basePath) {
//        int screenshotNumber = 1;
//        String screenshotPath;
//
//        do {
//            screenshotPath = basePath.replace(".png", "_" + screenshotNumber + ".png");
//            screenshotNumber++;
//        } while (new File(screenshotPath).exists());
//
//        return screenshotPath;
//    }   
    

//    public void takeScreenshot(String filePath) {
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File srcFile = ts.getScreenshotAs(OutputType.FILE);
//        File destFile = new File(filePath);
//        try {
//            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            System.out.println("Screenshot taken successfully");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Failed to take screenshot");
//        }
//    }

    // Method to click on the "Build your own expensive computer" link
    public void clickExpensiveComputer() {
        expensiveComputerLink.click();
        System.out.println("Clicked on 'Build your own expensive computer' successfully");
    }

//    // Method to hover over a specific element (cart or other elements if needed)
//    public void hoverOverElement(WebElement element) {
//        Actions actions = new Actions(driver);
//        actions.moveToElement(element).perform();
//        System.out.println("Hovered over element successfully");
//    }
}
