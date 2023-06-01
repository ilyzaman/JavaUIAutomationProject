package pageObjects.CheckOutPages;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.MyAccountPage;

public class CartLoginArea {
    WebDriver driver;
    public CartLoginArea (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="email")
    WebElement userEmail;
    @FindBy(id="passwd")
    WebElement userPassword;
    @FindBy(id="SubmitLogin")
    WebElement submitButton;

    public AddressPage login(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submitButton.click();
        AddressPage addressPage = new AddressPage(driver);
        return addressPage;
    }




}
