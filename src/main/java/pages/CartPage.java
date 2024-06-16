package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    WebDriver driver;

    By pageTitle = By.xpath("//span[@class='title']");
    By totalItemInTheCart = By.xpath("//div[@class='cart_item']");
    By continueShoppingButton = By.id("continue-shopping");
    By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public String getPageTitle(){
        return driver.findElement(pageTitle).getText();
    }

    public long getTotalItemInTheCart(){
        List<WebElement> totalCartItem = driver.findElements(totalItemInTheCart);
        return totalCartItem.stream().count();
    }

    public void clickContinueShoppingButton(){
        driver.findElement(continueShoppingButton).click();
    }

    public void clickCheckoutButton(){
        driver.findElement(checkoutButton).click();
    }
}
