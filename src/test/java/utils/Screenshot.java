package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    private WebDriver driver;

    public Screenshot(WebDriver driver) {
        this.driver = driver;
    }

    public void captureScreenshotAfterMouseOver(By elementLocator, String screenshotPath) throws IOException {
        // Locate the element to hover over
        WebElement element = driver.findElement(elementLocator);

        // Perform the mouse hover action
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

        // Capture the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save the screenshot to the specified path
        File destinationFile = new File(screenshotPath);
        FileHandler.copy(screenshot, destinationFile);
    }
}
