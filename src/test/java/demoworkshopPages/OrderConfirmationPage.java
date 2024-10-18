package demoworkshopPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderConfirmationPage {
    WebDriver driver;

    @FindBy(xpath = "//div[@class='title']/strong")
    WebElement confirmationMessage;

    @FindBy(xpath = "//ul[@class='details']/li[1]")
    WebElement orderNumber;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getOrderConfirmationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));         
        String message = confirmationMessage.getText(); 
        System.out.println(message);    
        return message; 
    }


    public String getOrderNumber() {
        return orderNumber.getText().replace("Order number: ", "").trim();
    }
}

