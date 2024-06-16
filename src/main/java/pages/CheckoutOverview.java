package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverview {

    WebDriver driver;

    By titlePage = By.xpath("//span[@class='title']");
    By inventoryItemPrice = By.xpath("//div[@class='inventory_item_price']");
    By paymentInfoValue = By.xpath("//div[@data-test='payment-info-value']");
    By shippingInformationValue = By.xpath("//div[@data-test='shipping-info-value']");
    By subTotal = By.xpath("//div[@data-test='subtotal-label']");
    By cancelButton = By.id("cancel");
    By finishButton = By.id("finish");

    public CheckoutOverview(WebDriver driver){
        this.driver = driver;
    }

    public String getTitlePage(){
        return driver.findElement(titlePage).getText();
    }

    public void paymentInfoValueIsDisplayed(){
        driver.findElement(paymentInfoValue).isDisplayed();
    }

    public void shippingInformationValueIsDisplayed(){
        driver.findElement(shippingInformationValue).isDisplayed();
    }

    public List<Double> getInventoryItemPrice(){
        List<WebElement> cartItemPrice = driver.findElements(inventoryItemPrice);
        List<Double> cartItemPriceList = new ArrayList<>();
        for (WebElement priceElement : cartItemPrice){
            cartItemPriceList.add(Double.valueOf(priceElement.getText().replace("$", "")));
        }

        return cartItemPriceList;
    }

    public Double getSubTotal(){
        return Double.valueOf(driver.findElement(subTotal).getText().replace("Item total: $", ""));
    }

    public void clickCancelButton(){
        driver.findElement(cancelButton).click();
    }

    public void clickFinishButton(){
        driver.findElement(finishButton).click();
    }
}
