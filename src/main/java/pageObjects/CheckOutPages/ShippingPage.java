package pageObjects.CheckOutPages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShippingPage extends AbstractComponent {
    WebDriver driver;

    public ShippingPage(WebDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    //By checkBox = By.id("cgv");
    //By nextButton = By.xpath("//*[@id=\"form\"]/p/button/span");

    @FindBy(id="cgv")
    WebElement checkBox;
    @FindBy(xpath = "//*[@id=\"form\"]/p/button/span")
    WebElement nextButton;



    public PaymentPage goToPayment(){
        checkBox.click();
        nextButton.click();
        PaymentPage paymentPage = new PaymentPage(driver);
        return paymentPage;
    }
}
