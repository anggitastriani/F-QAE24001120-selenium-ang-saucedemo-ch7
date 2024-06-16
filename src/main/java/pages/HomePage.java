package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    WebDriver driver;

    By logoTextHeader = By.xpath("//div[contains(@class, 'app_logo')]");
    By addToCartLogo = By.xpath("//a[@class='shopping_cart_link']");
    By pageTitle = By.xpath("//span[@class='title']");
    By productName = By.id("item_4_title_link");
    By sortingButton = By.xpath("//select[@class='product_sort_container']");
    By sortingHightoLowOption = By.xpath("//option[@value='hilo']");
    By priceAfterSorting = By.xpath("//div[@class='inventory_item_price']");
    By addToCartItem1 = By.id("add-to-cart-sauce-labs-backpack");
    By addToCartItem2 = By.id("add-to-cart-sauce-labs-bike-light");
    By shoppingCartBadge = By.xpath("//span[@class='shopping_cart_badge']");
    By cartButton = By.xpath("//a[@class='shopping_cart_link']");


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getLogoTextHeader(){
        return driver.findElement(logoTextHeader).getText();
    }

    public void AddToCartLogoIsDisplayed(){
        driver.findElement(addToCartLogo).isDisplayed();
    }

    public String getPageTitle(){
        return driver.findElement(pageTitle).getText();
    }

    public String getProductName(){
        return driver.findElement(productName).getText();
    }

    public void clickSortingButton(){
        driver.findElement(sortingButton).click();
    }

    public void clickSortingHightoLowOption(){
        driver.findElement(sortingHightoLowOption).click();
    }

    public List<Double> getItemPriceAfterSorting(){
        List<WebElement> itemPriceAfterSorting = driver.findElements(priceAfterSorting);
        List<Double> itemPriceAfterSortingList = new ArrayList<>();
        for (WebElement priceElement : itemPriceAfterSorting) {
            itemPriceAfterSortingList.add(Double.valueOf(priceElement.getText().replace("$", "")));
        }

        return itemPriceAfterSortingList;
    }

    public void clickAddToCartItem1(){
        driver.findElement(addToCartItem1).click();
    }

    public void clickAddToCartItem2(){
        driver.findElement(addToCartItem2).click();
    }

    public void shoppingCartBadgeIsDisplayed(){
        driver.findElement(shoppingCartBadge).isDisplayed();
    }

    public void clickCartButtton(){
        driver.findElement(cartButton).click();
    }
}
