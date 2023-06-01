package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends AbstractComponent {

    WebDriver driver;

    public MyAccountPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By ordersBy = By.className("icon-list-ol");

    @FindBy(className = ".info-account")
    WebElement accountMessage;
    @FindBy(className = "alert-success")
    WebElement alertMessage;


    public String getAccountMessage(){
        waitForElementToAppear(accountMessage);
        return accountMessage.getText();
    }
    public String getAlertMessage(){
        waitForElementToAppear(alertMessage);
        return alertMessage.getText();
    }

    public MyOrdersPage goToMyOrders(){
        click(ordersBy);
        MyOrdersPage myOrdersPage = new MyOrdersPage((WebElement) driver);
        return myOrdersPage;
    }






}
