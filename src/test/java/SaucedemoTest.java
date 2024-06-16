import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

// ini adalah kode sebelum mengimplementasikan POM

public class SaucedemoTest {
    @Test
    public void successfullyLogin(){
        // start session atau open browser
        WebDriver driver = WebDriverManager.chromedriver().create();

        // set browser fullscreen
        driver.manage().window().maximize();

        // implicity wait 15 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // navigate to website
        driver.get("https://www.saucedemo.com/");

        // input username
        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        // input password
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");

        // click login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // assert1 : header logo
        WebElement headerLogo = driver.findElement(By.xpath("//div[contains(@class, 'app_logo')]"));
        Assert.assertEquals(headerLogo.getText(), "Swag Labs");

        // assert2 : url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        // assert3 : add to cart logo
        WebElement addToCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        addToCart.isDisplayed();

        // assert4 : product item
        WebElement productItem = driver.findElement(By.xpath("//div[@class='inventory_item']"));
        productItem.isDisplayed();

        // assert5 : product name
        WebElement productName = driver.findElement(By.id("item_4_title_link"));
        Assert.assertEquals(productName.getText(), "Sauce Labs Backpack");

        // end session atau close browser
        driver.quit();
    }

    @Test
    public void failedLogin(){
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://www.saucedemo.com/");

        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sau");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // assert1 : stay on the login page
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

        // assert2 : error message
        WebElement errorMessage = driver.findElement(By.xpath("//*[contains(@class, 'error-message-container')]"));
        Assert.assertEquals(errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");

        driver.quit();
    }

    @Test
    public void sortingItemHightoLow() {
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://www.saucedemo.com/");

        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // filter the price from dropdown : high to low
        WebElement selectDropdown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        selectDropdown.click();

        WebElement optionValue = driver.findElement(By.xpath("//option[@value='hilo']"));
        optionValue.click();

        // get the prices after filter
        List<WebElement> FilterPrice = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        List<Double> FilterPriceList = new ArrayList<>();
        for (WebElement priceElement : FilterPrice) {
            FilterPriceList.add(Double.valueOf(priceElement.getText().replace("$", "")));
        }

        // assert : compare the price of the first item with the second item after filter
        double firstPrice = FilterPriceList.get(0);
        double secondPrice = FilterPriceList.get(1);
        Assert.assertTrue( firstPrice > secondPrice , "comparing first price greater than second price");

        driver.quit();

    }

    @Test
    public void checkOut() {
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://www.saucedemo.com/");

        WebElement inputUsername = driver.findElement(By.id("user-name"));
        inputUsername.sendKeys("standard_user");

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();


        // 1. select item add to cart
        WebElement item1 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        item1.click();

        WebElement item2 = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        item2.click();


        // 2. go to cart page
        WebElement chartButton = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        chartButton.click();

        // assert1 : url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");

        // assert2 : page title
        WebElement checkoutPageTitle = driver.findElement(By.xpath("//span[@class='title']"));
        Assert.assertEquals(checkoutPageTitle.getText(), "Your Cart");

        // assert3 : total items in the cart
        List<WebElement> cartItem = driver.findElements(By.xpath("//div[@class='cart_item']"));
        Assert.assertEquals(cartItem.stream().count(), 2);

        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();


        // 3. checkout : your information page
        WebElement inputFirstName = driver.findElement(By.id("first-name"));
        inputFirstName.sendKeys("Satu");

        WebElement inputLastName = driver.findElement(By.id("last-name"));
        inputLastName.sendKeys("Dua");

        WebElement inputPostalCode = driver.findElement(By.id("postal-code"));
        inputPostalCode.sendKeys("12312");

        // assert1 : url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

        // assert2 : page title
        WebElement checkoutInformationTitle = driver.findElement(By.xpath("//span[@class='title']"));
        Assert.assertEquals(checkoutInformationTitle.getText(), "Checkout: Your Information");

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();


        // 4. go to checkout : overview page
        // assert1: summary info is displayed
        WebElement summaryInfo = driver.findElement(By.xpath("//div[@class='summary_info']"));
        summaryInfo.isDisplayed();

        // assert2: payment information value
        WebElement paymentValue = driver.findElement(By.xpath("//div[@data-test='payment-info-value']"));
        paymentValue.isDisplayed();

        // assert3 : shipping information value
        WebElement shippingValue = driver.findElement(By.xpath("//div[@data-test='shipping-info-value']"));
        shippingValue.isDisplayed();

        // assert4 : validate total price is correct
        // first, get information total price item
        List<WebElement> cartItemPrice = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        List<Double> cartItemPriceList = new ArrayList<>();
        for (WebElement priceElement : cartItemPrice){
            cartItemPriceList.add(Double.valueOf(priceElement.getText().replace("$", "")));
        }

        WebElement priceTotal = driver.findElement(By.xpath("//div[@data-test='subtotal-label']"));
        double actualPriceTotal = Double.parseDouble(priceTotal.getText().replace("Item total: $", ""));
        Assert.assertEquals(actualPriceTotal, cartItemPriceList.get(0) + cartItemPriceList.get(1));

        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();


        // 5. go to checkout : complete page

        // assert1: url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");

        // assert2: page title
        WebElement checkoutCompleteTitle = driver.findElement(By.xpath("//span[@class='title']"));
        Assert.assertEquals(checkoutCompleteTitle.getText(), "Checkout: Complete!");

        // assert3: logo
        WebElement completeLogo = driver.findElement(By.xpath("//img[@class='pony_express']"));
        completeLogo.isDisplayed();

        // assert4: button back home
        WebElement backHomeButton = driver.findElement(By.id("back-to-products"));
        backHomeButton.isDisplayed();



        driver.quit();
    }

}
