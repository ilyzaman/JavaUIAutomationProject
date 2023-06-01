package pageObjects.CheckOutPages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public ConfirmationPage (WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "alert")
    WebElement alert;

    public String getConfirmationMessage(){
        return alert.getText();

    }
}
