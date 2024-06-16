package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationFormPage {
    WebDriver driver;

    By titlePage = By.xpath("//span[@class='title']");
    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By cancelButton = By.id("cancel");
    By continueButton = By.id("continue");

    public CheckoutInformationFormPage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitlePage(){
        return driver.findElement(titlePage).getText();
    }

    public void inputFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void inputPostalCode(String postalCode){
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickCancelButton(){
        driver.findElement(cancelButton).click();
    }

    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
}
