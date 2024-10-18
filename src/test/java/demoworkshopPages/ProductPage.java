package demoworkshopPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    WebDriver driver;

    @FindBy(id = "product_attribute_74_5_26_82")
    WebElement fastProcessor;

    @FindBy(id = "product_attribute_74_6_27_85")
    WebElement ram8gb;

    @FindBy(id = "product_attribute_74_3_28_87")
    WebElement hdd400gb;

    @FindBy(id = "product_attribute_74_8_29_89")
    WebElement officeSuite;

    @FindBy(id = "addtocart_74_EnteredQuantity")
    WebElement quantity;

    @FindBy(id = "add-to-cart-button-74")
    WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void configureProduct() {
        fastProcessor.click();
        ram8gb.click();
        hdd400gb.click();
        officeSuite.click();
    }

    public void setQuantity(String qty) {
        quantity.clear();
        quantity.sendKeys(qty);
    }

    public void addToCart() {
        addToCartButton.click();
    }
}

