package pageObjects.CheckOutPages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressPage extends AbstractComponent {

    WebDriver driver;

    public AddressPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }


    //By nextButton = By.cssSelector("//*[@id=\"center_column\"]/form/p/button/span");
    @FindBy(xpath = "//*[@id=\"center_column\"]/form/p/button/span")
    WebElement nextButton;

    public ShippingPage goToShipping(){
        nextButton.click();
        ShippingPage shippingPage = new ShippingPage(driver);
        return shippingPage;
    }

}
