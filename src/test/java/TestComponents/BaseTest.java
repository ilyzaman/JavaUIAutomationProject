package TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;

    public HomePage homePage; //Initially it is null, but will take life inside launchApplication
    public WebDriver initializeDriver() throws IOException {

        Properties properties = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
        properties.load(file);
        String browserName =  System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }
        else if (browserName.equalsIgnoreCase("firefox")){
            //System.setProperty("webdriver.gecko.driver","C:\\Users\\izaman\\Documents\\GitHub\\JavaUIAutomation\\JavaUIAutomationProject\\drivers\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("edge")){
            //System.setProperty("webdriver.edge.driver","C:\\Users\\izaman\\Documents\\GitHub\\JavaUIAutomation\\JavaUIAutomationProject\\drivers\\msedgedriver.exe");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver; // Once it is ready, this whole method will prepare a driver for us


    }
    @BeforeMethod(alwaysRun = true)
    public HomePage launchApplication() throws IOException {
        driver = initializeDriver();
        homePage = new HomePage(driver); //Object is local, put it on top
        homePage.goHome();
        return homePage;

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//screenshots//" + testCaseName + ".jpeg");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

    }



}
