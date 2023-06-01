package pageObjects.CheckOutPages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends AbstractComponent {

    WebDriver driver;

    public PaymentPage (WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "bankwire")
    WebElement bankWire;
    @FindBy(css = ".cart_navigation button")
    WebElement confirmOrder;
    @FindBy(id = "total_price")
    WebElement price;



    public ConfirmationPage pay(){
        bankWire.click();
        confirmOrder.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;

    }
    public String getPrice(){
        System.out.println(price.getText());
        waitForElementToAppear(price);
        return price.getText();
    }
}
