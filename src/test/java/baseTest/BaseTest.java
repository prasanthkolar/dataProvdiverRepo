package baseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.Guru99_HomePage;
import pageObjects.Guru99_LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public Guru99_LoginPage loginPage;
    public Guru99_HomePage homePage;

    @Parameters({"nodeUrl","typeOfBrowser"})
    @BeforeClass
        public  void setUp(String url, String browserName) throws MalformedURLException, InterruptedException {
        System.out.println("In before class");
        driver= new RemoteWebDriver(new URL(url), getBrowserCapabilities(browserName));
        driver.navigate().to("http://demo.guru99.com/V4/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginPage= PageFactory.initElements(driver,Guru99_LoginPage.class);
        homePage= PageFactory.initElements(driver,Guru99_HomePage.class);

    }
    public void explicitWait(WebElement element)
    {
        //WebElement element=driver.findElement(By.xpath(xpath));
        WebDriverWait wait=new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public  DesiredCapabilities getBrowserCapabilities(String browserType) {
        switch (browserType) {
            case "firefox":
                System.out.println("Opening firefox driver");
                return DesiredCapabilities.firefox();
            case "chrome":
                System.out.println("Opening chrome driver");
                return DesiredCapabilities.chrome();
            case "IE":
                System.out.println("Opening IE driver");
                return DesiredCapabilities.internetExplorer();
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
                return DesiredCapabilities.firefox();
        }
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}


