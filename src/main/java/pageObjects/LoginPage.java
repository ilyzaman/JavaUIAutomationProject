package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static net.andreinc.mockneat.unit.user.Emails.emails;

public class LoginPage extends AbstractComponent {

    // In abstraction whatever variable will be applicable in your class

    WebDriver driver;

    public LoginPage (WebDriver driver){
        super(driver);

        // initialization
        // When you write "this.driver", it refers to the current class instance variable. Line 9
        this.driver=driver; // we are assigning the HomePage variable to the local class variable.

        // ALWAYS CHECK "local class variable vs instance variable" ON GOOGLE

        PageFactory.initElements(driver, this);
    }

    //WebElement userEmail = driver.findElement(By.id("email"));

    //PageFactory
    @FindBy(id="email")
    WebElement userEmail;
    @FindBy(id="passwd")
    WebElement userPassword;
    @FindBy(id="SubmitLogin")
    WebElement submitButton;
    @FindBy(id = "email_create")
    WebElement emailCreation;
    @FindBy(id = "SubmitCreate")
    WebElement submitCreateButton;
    @FindBy(css = "#create_account_error li")
    WebElement creationErrorMessage;
    @FindBy(css = ".alert-danger li")
    WebElement loginErrorMessage;

    String email = emails().get();






    public MyAccountPage login(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submitButton.click();
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        return myAccountPage;
    }



    public RegisterPage createNewAccount(){
        emailCreation.sendKeys(email);
        submitCreateButton.click();
        RegisterPage registerPage = new RegisterPage(driver);
        return registerPage;

    }
    public RegisterPage createAccount(String email){
        emailCreation.sendKeys(email);
        submitCreateButton.click();
        RegisterPage registerPage = new RegisterPage(driver);
        return registerPage;

    }

    public String getCreationErrorMessage(){
        waitForElementToAppear(creationErrorMessage);
        return creationErrorMessage.getText();
    }

    public String getLoginErrorMessage(){
        waitForElementToAppear(loginErrorMessage);
        return loginErrorMessage.getText();
    }




}
