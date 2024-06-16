package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    WebDriver driver;

    By pageTitle = By.xpath("//span[@class='title']");
    By completeLogo = By.xpath("//img[@class='pony_express']");
    By completeText = By.xpath("//h2[@class='complete-header']");
    By descCompleteText = By.xpath("//div[@class='complete-text']");
    By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void completeLogoIsDisplayed(){
        driver.findElement(completeLogo).isDisplayed();
    }

    public String getCompleteText(){
        return driver.findElement(completeText).getText();
    }

    public String getDescCompleteText(){
        return driver.findElement(descCompleteText).getText();
    }

    public void backHomeButtonIsDisplayed(){
        driver.findElement(backHomeButton).isDisplayed();
    }

}
