package tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RegisterPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class RegisterTests extends BaseTest {

    @Test
    public void registerWithNewEmail(){
        LoginPage loginPage = homePage.goToLogin();
        RegisterPage registerPage = loginPage.createNewAccount();
        MyAccountPage myAccountPage = registerPage.register("Ilyas","Zaman","password");
        Assert.assertEquals(myAccountPage.getAlertMessage(),"Your account has been created.");
    }

    @Test(groups =  {"ErrorHandling"})
    public void registerWithExistingEmail(){
        LoginPage loginPage = homePage.goToLogin();
        loginPage.createAccount("ilyas@email.com");
        Assert.assertTrue(loginPage.getCreationErrorMessage().contains("Please enter a valid password or request a new one."));
    }

    /*
    @Test

    public void checkMandatoryFields(){
        String firstName = "";
        String lastName = "zaman";
        String password = "password";
        LoginPage loginPage = homePage.goToLogin();
        RegisterPage registerPage = loginPage.createAccount("toto@email.com");

        registerPage.register(firstName,lastName,password);
        if(firstName == "") {
            Assert.assertEquals(registerPage.getFnameAlertText(), "firstname is required.");
        }
        else if (lastName == ""){
            Assert.assertEquals(registerPage.getLnameAlertText(),"lastndddame is required.");
        }

        */

}

