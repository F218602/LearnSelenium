package demoworkshopPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;

    @FindBy(id = "BillingNewAddress_CountryId")
    WebElement billingCountry;

    @FindBy(id = "BillingNewAddress_City")
    WebElement city;

    @FindBy(id = "BillingNewAddress_Address1")
    WebElement address1;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    WebElement zipCode;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    WebElement phoneNumber;

    @FindBy(css = "input.button-1.new-address-next-step-button[value='Continue']")
    WebElement continueButton;

    @FindBy(xpath = "(//input[@class='button-1 new-address-next-step-button' and @value='Continue'])[2]")
    WebElement continueShippingButton;

    @FindBy(xpath = "//input[@class='button-1 shipping-method-next-step-button' and @value='Continue']")
    WebElement continueShippingMethodButton;

    @FindBy(xpath = "//input[@class='button-1 payment-method-next-step-button' and @value='Continue']")
    WebElement continuePaymentMethodButton;

    @FindBy(xpath = "//input[@class='button-1 payment-info-next-step-button' and @value='Continue']")
    WebElement continuePaymentInfoButton;

    @FindBy(xpath = "//input[@class='button-1 confirm-order-next-step-button' and @value='Confirm']")
    WebElement confirmOrderButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillBillingDetails(String country, String cityName, String street, String postalCode, String phone) {
        Select billingCountrySelect = new Select(billingCountry);
        billingCountrySelect.selectByVisibleText(country);
        city.sendKeys(cityName);
        address1.sendKeys(street);
        zipCode.sendKeys(postalCode);
        phoneNumber.sendKeys(phone);
        System.out.println("12 Billing Address - Successful");
    }

    public void continueCheckout() {
        continueButton.click();
    }

    public void continueToShipping() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(continueShippingButton));
        continueShippingButton.click();
        System.out.println("13 Shipping Address - Successful");
    }

    public void continueToShippingMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(continueShippingMethodButton));
        wait.until(ExpectedConditions.elementToBeClickable(continueShippingMethodButton));
        continueShippingMethodButton.click();
        System.out.println("14 Shipping Method - Successful");
    }
    
    public void continueToPaymentMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(continuePaymentMethodButton)); 
        continuePaymentMethodButton.click();
        System.out.println("15 Payment Method - Successful");
    }

    public void continueToPaymentInfo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(continuePaymentInfoButton)); 
        continuePaymentInfoButton.click();
        System.out.println("16 Confirm Order - Successful");
    }
    
    public void confirmOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)); 
        confirmOrderButton.click();
        System.out.println("16 Confirm Order - Successful");
    }

}
