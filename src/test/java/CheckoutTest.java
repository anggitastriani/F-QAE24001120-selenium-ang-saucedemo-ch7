import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class CheckoutTest {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void successCheckoutTes() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutInformationFormPage checkoutInformationFormPage = new CheckoutInformationFormPage(driver);
        CheckoutOverview checkoutOverview = new CheckoutOverview(driver);
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLoginButton();

        // 1. (home page) select item add to cart
        homePage.clickAddToCartItem1();
        homePage.clickAddToCartItem2();

        // assertion1 : validate shopping cart badge is displayed
        homePage.shoppingCartBadgeIsDisplayed();

        homePage.clickCartButtton();

        // ========================================================================================================
        // 2. (cart page)

        // assertion1 : validate url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");

        // assertion2 : validate page title
        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart");

        // assertion3 : validate total items in the cart
        Assert.assertEquals(cartPage.getTotalItemInTheCart(), 2);

        cartPage.clickCheckoutButton();

        // ========================================================================================================
        // 3. (checkout : your information page)

        // assertion1 : validate url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

        // assertion2 : validate page title
        Assert.assertEquals(checkoutInformationFormPage.getTitlePage(), "Checkout: Your Information");

        checkoutInformationFormPage.inputFirstName("Nathan");
        checkoutInformationFormPage.inputLastName("Tjoe");
        checkoutInformationFormPage.inputPostalCode("12345");
        checkoutInformationFormPage.clickContinueButton();

        // ========================================================================================================
        // 4. (checkout : overview page)
        // assertion1 : validate url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");

        // assertion2 : validate page title
        Assert.assertEquals(checkoutOverview.getTitlePage(), "Checkout: Overview");

        // assertion3 : validate payment information value is displayed
        checkoutOverview.paymentInfoValueIsDisplayed();

        // assertion4 : validate shipping information value is displayed
        checkoutOverview.shippingInformationValueIsDisplayed();

        // assertion5 : validate subtotal price is correct
        Double itemPrice1 = checkoutOverview.getInventoryItemPrice().get(0);
        Double itemPrice2 = checkoutOverview.getInventoryItemPrice().get(1);
        Assert.assertEquals(checkoutOverview.getSubTotal(),  itemPrice1 + itemPrice2);

        checkoutOverview.clickFinishButton();

        // ========================================================================================================
        // 5. (checkout : complete page)
        // assertion1 : validate url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");

        // assertion2 : validate page title
        Assert.assertEquals(checkoutCompletePage.getPageTitle(), "Checkout: Complete!");

        // assertion3 : validate complete logo is displayed
        checkoutCompletePage.completeLogoIsDisplayed();

        // assertion3 : validate complete text
        Assert.assertEquals(checkoutCompletePage.getCompleteText(), "Thank you for your order!");

        // assertion4 : validate description complete text
        Assert.assertEquals(checkoutCompletePage.getDescCompleteText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");

        // assertion5 : validate back home button
        checkoutCompletePage.backHomeButtonIsDisplayed();

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
