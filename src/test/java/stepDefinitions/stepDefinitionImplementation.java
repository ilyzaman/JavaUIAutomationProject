package stepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.CheckOutPages.*;
import pageObjects.DressesCatalogue;
import pageObjects.HomePage;
import pageObjects.LoginPage;

import java.io.IOException;
import java.util.List;

public class stepDefinitionImplementation extends BaseTest{

    public HomePage homePage;
    public DressesCatalogue dressesCatalogue;
    public LoginPage loginPage;

    public PaymentPage paymentPage;
    @Given("I landed on ecommerce page")
    public void i_landed_on_ecommerce_page() throws IOException {

       homePage = launchApplication();
    }

    @Given("^logged in with email (.+) and password (.+)$")
    public void logged_in_with_email_and_password(String email, String password) throws IOException {


        homePage = launchApplication();
        loginPage = homePage.goToLogin();
        loginPage.login(email, password);
    }

    @When("^I add product (.+) from cart$")
    public void i_add_product_from_cart(String productName){
        dressesCatalogue = homePage.goToDressesCatalogue();
        dressesCatalogue.addNamedProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_product_and_submit_order(String productName){

        CartSummaryPage cartSummaryPage = homePage.goToCart();
        Boolean match = cartSummaryPage.matchMyArticle(productName);
        Assert.assertTrue(match);
        AddressPage addressPage = cartSummaryPage.goToAddressPage();
        ShippingPage shippingPage = addressPage.goToShipping();
        paymentPage = shippingPage.goToPayment();

    }

    @Then("{string} message is displayed on ConfirmationPage")

    public void message_is_displayed (String string){

        ConfirmationPage confirmationPage = paymentPage.pay();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        System.out.println(confirmMessage);
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();

    }


        @Then("^\"([^\"]*)\" message is displayed$")
        public void something_message_is_displayed(String message) throws Throwable {
            Assert.assertEquals(loginPage.getLoginErrorMessage(),message);
            driver.close();

        }






}
