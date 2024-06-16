import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.nio.DoubleBuffer;
import java.time.Duration;

public class SortingItemTest {

    WebDriver driver;

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void sortingHightoLowTest(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLoginButton();

        homePage.clickSortingButton();
        homePage.clickSortingHightoLowOption();

        // assertion : compare the price of the first item with the second item after filter
        Double firstItemPrice = homePage.getItemPriceAfterSorting().get(0);
        Double secondItemPrice = homePage.getItemPriceAfterSorting().get(1);
        Assert.assertTrue(firstItemPrice > secondItemPrice, "comparing first price greater than second price");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
