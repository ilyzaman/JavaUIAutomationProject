package pageObjects.CheckOutPages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartSummaryPage extends AbstractComponent {
    WebDriver driver;
    By cartTitle = By.className("cart_title");

    public CartSummaryPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(css = ".cart_item td:nth-of-type(2)")
    List<WebElement> myArticles;
    @FindBy(css = ".cart_navigation a:first-of-type")
    WebElement nextButton;

    By checkoutButton = By.cssSelector(".cart_navigation a:first-of-type");

    public Boolean matchMyArticle(String productName){
        Boolean match = myArticles.stream().anyMatch(cartProduct
                -> cartProduct.findElement(By.cssSelector("p")).getText().equalsIgnoreCase(productName));
        return match;
    }

    public AddressPage  goToAddressPage(){
        nextButton.click();
        AddressPage addressPage = new AddressPage(driver);
        return addressPage;
    }

    public CartLoginArea goToLogin(){
        nextButton.click();
        CartLoginArea cartLoginArea = new CartLoginArea(driver);
        return cartLoginArea;

    }



}
