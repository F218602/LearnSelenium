package demoworkshopPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class CartSummaryPage {
    WebDriver driver;
    
    @FindBy(xpath = "//a[@class='ico-cart']")
    WebElement goToCartButton;

    @FindBy(id = "CountryId")
    WebElement countryDropdown;

    @FindBy(id = "termsofservice")
    WebElement termsCheckbox;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public CartSummaryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void goToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(goToCartButton));
        
        goToCartButton.click();
        System.out.println("Click on \"Go to Cart\" - Successful");
    }

    public void selectCountry(String country) {
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText(country);
    }

    public void agreeToTerms() {
        termsCheckbox.click();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }
}

