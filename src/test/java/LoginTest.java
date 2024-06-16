import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;

    @BeforeTest
    public void setUp(){
        //WebDriver driver = WebDriverManager.chromiumdriver().create();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void loginWithValidCredentialsTest(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLoginButton();

        // assertion1 : validate url
        Assert.assertEquals(homePage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        // assertion2 : validate logo title
        Assert.assertEquals(homePage.getLogoTextHeader(), "Swag Labs");

        // assertion3 : validate add to cart logo is displayed
        homePage.AddToCartLogoIsDisplayed();

        // assertion4 : validate header title
        Assert.assertEquals(homePage.getPageTitle(), "Products");

        // assertion5 : vallidate product name
        Assert.assertEquals(homePage.getProductName(), "Sauce Labs Backpack");

    }

    @Test
    public void loginWithInvalidCredentialsTest(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUsername("standard-user");
        loginPage.inputPassword("wrong_pass");
        loginPage.clickLoginButton();

        // assertion1 : validate url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

        // assertion2 : validate error message
        Assert.assertEquals(loginPage.errorMessageFailedLogin(), "Epic sadface: Username and password do not match any user in this service");

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
