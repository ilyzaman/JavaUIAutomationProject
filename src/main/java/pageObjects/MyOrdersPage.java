package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyOrdersPage {
    WebElement driver;

    public MyOrdersPage (WebElement driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"order-list\"]/tbody/tr[1]/td[3]/span")
    WebElement lastOrderPrice;
    public String getPrice(){
        return lastOrderPrice.getText();
    }


}
