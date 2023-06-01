package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class RegisterPage extends AbstractComponent {

    WebDriver driver;
    public RegisterPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(css = ".radio-inline div")
    List<WebElement> genderTitles;
    By titlesBy = By.cssSelector(".radio-inline div");
    @FindBy(id = "customer_firstname")
    WebElement fNameField;
    @FindBy(id = "customer_lastname")
    WebElement lNameField;
    @FindBy(id="passwd")
    WebElement passwordField;
    @FindBy(id = "submitAccount")
    WebElement submitButton;
    @FindBy(css = ".alert-danger li:nth-of-type(2)")
    WebElement firstNameAlert;
    @FindBy(css=".alert-danger li:first-of-type")
    WebElement lastNameAlert;



    public String getFnameAlertText(){
        waitForElementToAppear(firstNameAlert);
        return firstNameAlert.getText();
    }
    public String getLnameAlertText(){
        waitForElementToAppear(lastNameAlert);
        return lastNameAlert.getText();
    }

    public List<WebElement> getTitlesList(){
        waitForElementToAppearBy(titlesBy);
        return genderTitles;
    }

    public WebElement selectTitle(){
        Random random = new Random();
        WebElement title = getTitlesList().get(random.nextInt(genderTitles.size()));
        return title;
    }
    public MyAccountPage register(String firstName, String lastName, String password){
        selectTitle().click();
        fNameField.sendKeys(firstName);
        lNameField.sendKeys(lastName);
        passwordField.sendKeys(password);
        submitButton.click();
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        return myAccountPage;
    }
}
