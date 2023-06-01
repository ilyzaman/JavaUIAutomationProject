package tests;

import TestComponents.BaseTest;
import TestComponents.Retry;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;


public class LoginTests extends BaseTest {

    @Test
    public void loginWithCorrectCredentials(){
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("ilyas@email.com", "password");
        //System.out.println("HERE  " + myAccountPage.getAccountMessage());

    }

    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void loginWithWrongCredentials(){
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("ilyos@email.com","password");
        Assert.assertEquals(loginPage.getLoginErrorMessage(),"Authentication failed.");
    }




}
