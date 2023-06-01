package AbstractComponents;

import pageObjects.CheckOutPages.CartSummaryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent  {
    // It will be the parent class to all PageObjects classes because it holds all reusable stuff.
    WebDriver driver; // Create a local object
    WebDriverWait wait;

    By cartButton = By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a");

    public AbstractComponent(WebDriver driver) {
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void waitForElementToAppearBy(By FindBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
    }
    public void waitForElementToAppear(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }


    public WebElement waitVisibility(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void click(By by) {
        waitVisibility(by).click();
    }

    public void  writeText(By by, String text){
        waitVisibility(by).sendKeys(text);
    }

    public String getText(By by){
        return waitVisibility(by).getText();
    }



}
