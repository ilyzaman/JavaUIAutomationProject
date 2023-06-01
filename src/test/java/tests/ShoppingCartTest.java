package tests;

import TestComponents.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;
import pageObjects.CheckOutPages.*;
import org.testng.Assert;
import java.util.HashMap;

public class ShoppingCartTest extends BaseTest {

    String price;
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void orderAsAuthenticatedUser(HashMap<String,String> input) {

        String productName = "Printed Chiffon Dress";
        //HomePage homePage = launchApplication();
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login(input.get("email"),input.get("password"));
        DressesCatalogue dressesCatalogue = homePage.goToDressesCatalogue();
        //List<WebElement> dresses = dressesCatalogue.getDressesList();
        dressesCatalogue.addRandomProductToCart();
        dressesCatalogue.addNamedProductToCart(productName);
        //CartPage cartPage = new CartPage(driver); // INSTEAD OF DOING THIS
        CartSummaryPage cartSummaryPage = homePage.goToCart(); // <=== DO THIS
        Boolean match = cartSummaryPage.matchMyArticle(productName);
        Assert.assertTrue(match);
        AddressPage addressPage = cartSummaryPage.goToAddressPage();
        ShippingPage shippingPage = addressPage.goToShipping();
        PaymentPage paymentPage = shippingPage.goToPayment();
        price = paymentPage.getPrice();
        ConfirmationPage confirmationPage = paymentPage.pay();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Your order on My Store is complete."));
    }

    @Test
    public void orderAsUnauthenticatedUser(){
        String productName = "Printed Chiffon Dress";
        DressesCatalogue dressesCatalogue = homePage.goToDressesCatalogue();
        dressesCatalogue.addRandomProductToCart();
        dressesCatalogue.addNamedProductToCart(productName);
        CartSummaryPage cartSummaryPage = homePage.goToCart();
        Boolean match = cartSummaryPage.matchMyArticle(productName);
        Assert.assertTrue(match);
        CartLoginArea cartLoginArea = cartSummaryPage.goToLogin();
        AddressPage addressPage = cartLoginArea.login("ilyas@email.com","password");
        ShippingPage shippingPage = addressPage.goToShipping();
        PaymentPage paymentPage = shippingPage.goToPayment();
        ConfirmationPage confirmationPage = paymentPage.pay();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Your order on My Store is complete."));
}
/*
@Test(dependsOnMethods = {"orderAsAuthenticatedUser"})
    public void orderHistoryTest(){
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("tata@email.com", "password");
        MyAccountPage myAccountPage = homePage.goToMyAccount();
        MyOrdersPage myOrdersPage = myAccountPage.goToMyOrders();
        Assert.assertEquals(myOrdersPage.getPrice(),price);
    }

 */

    @DataProvider
    public Object[][] getData(){


        HashMap <String,String> map = new HashMap<>();
        map.put("email", "ilyas@email.com");
        map.put("password","password");
        //map.put("productName","Printed Chiffon Dress");

        HashMap <String,String> map1 = new HashMap<>();
        map1.put("email", "tata@email.com");
        map1.put("password","password");
        //map1.put("productName","Printed Summer Dress");

        //return new Object [][]{{"ilyas@email.com","password"},{"tata@email.com","password"}};
        return new Object [][]{ {map},{map1}};

    }


}
