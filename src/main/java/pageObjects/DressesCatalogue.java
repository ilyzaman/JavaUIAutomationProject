package pageObjects;

import AbstractComponents.AbstractComponent;
import com.google.common.collect.Iterables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DressesCatalogue extends AbstractComponent {
    WebDriver driver;
    public DressesCatalogue(WebDriver driver){
        super(driver); // Send the driver from children to parent
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Web Elements
     */
    @FindBy(className = "product-container")
    List<WebElement> dresses;
    By dressesBy = By.className("product-container");
    //By addToCart = By.linkText("Add to cart");
    By addToCart = By.cssSelector(".ajax_add_to_cart_button");
    By continueShopping = By.cssSelector(".continue span");
    By selectBy = By.id("selectProductSort");

    By articleName = By.cssSelector(".right-block h5");

    By price = By.cssSelector(".right-block div:first-of-type");
    @FindBy(css = ".right-block div:first-of-type")
    List <WebElement> pricesList;



    /**
     * Page Methods
     */

    public List<WebElement> getDressesList (){
        waitForElementToAppearBy(dressesBy);
        return dresses;
    }

    public List<String> getPricesList () {
        waitForElementToAppearBy(price);
        List<String> prices = pricesList.stream().map(x -> x.getText()).collect(Collectors.toList());
        System.out.println(prices);
        return prices;
    }

    public List <String> getProductNameList(){
        waitVisibility(dressesBy);
        List<String> names = dresses.stream().map(x -> x.findElement(articleName).getText()).collect(Collectors.toList());
        return names;
    }

    public void sortBy (String on){
        Select sortingSelect = new Select(driver.findElement(selectBy));
        sortingSelect.selectByVisibleText(on);
    }


    public String getLastArticlePrice () {
        String lastArticle = Iterables.getLast(getPricesList());
        return lastArticle;
    }

    public String getLastArticleName () {

        String lastArticle = Iterables.getLast(getProductNameList());
        return lastArticle;
    }

    public WebElement getDressByName(String productName){
        WebElement dress = getDressesList().stream().filter(dr ->
                dr.findElement(articleName).getText().equals(productName)).findFirst().orElse(null);
        return dress;
    }

    public WebElement getDressRandomly(){
        Random rand = new Random();
        WebElement dress = dresses.get(rand.nextInt(dresses.size()));
        return dress;
    }

    public void addNamedProductToCart(String productName){
        WebElement dress = getDressByName(productName);
        dress.click();
        dress.findElement(addToCart).click();
        click(continueShopping);

    }

    public void addRandomProductToCart(){
        WebElement dress = getDressRandomly();
        dress.click();
        dress.findElement(addToCart).click();
        click(continueShopping);
    }







}
