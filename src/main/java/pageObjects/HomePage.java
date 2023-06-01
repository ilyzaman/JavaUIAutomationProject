package pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.CheckOutPages.CartSummaryPage;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends AbstractComponent {

    WebDriver driver;
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    By dressesBy = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a");
    By cartButton = By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a");
    By loginBy = By.className("login");
    By accountButton = By.className("account");
    By searchField = By.id("search_query_top");
    By searchButton = By.className("button-search");
    By alertMessage = By.className("alert");
    By suggestionsList = By.cssSelector(".ac_results li");
    @FindBy(css = ".ac_results li")
    List<WebElement> suggestions;


    public LoginPage goToLogin(){
        click(loginBy);
        LoginPage loginPage = new LoginPage(driver);
        return loginPage;
    }
    public DressesCatalogue goToDressesCatalogue(){
        click(dressesBy); // Will land to DressesCatalogue
        DressesCatalogue dressesCatalogue = new DressesCatalogue(driver);
        return dressesCatalogue;
    }
    public CartSummaryPage goToCart(){
        click(cartButton);
        CartSummaryPage cartSummaryPage = new CartSummaryPage(driver);
        return cartSummaryPage;
    }
    public MyAccountPage goToMyAccount(){
        click(accountButton);
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        return myAccountPage;
    }

    public void searchArticle(String text){
        click(searchField);
        writeText(searchField,text);
        click(searchButton);

    }

    public List<String> getSuggestionsList (){
        waitForElementToAppearBy(suggestionsList);
        List<String> s = suggestions.stream().map(x -> x.getText()).collect(Collectors.toList());
        return s;
    }

    public Boolean matchMySuggestion(String suggest){
        boolean match = getSuggestionsList().stream().anyMatch(s -> s.contains(suggest));
        return match;
    }


    public void showSuggestions (String text) {
        click(searchField);
        writeText(searchField, text);
        System.out.println(getSuggestionsList());
    }

    public String getErrorMessage(){
        return getText(alertMessage);
    }

    public void goHome(){
        driver.get("http://automationpractice.pl/index.php");
    }

}
