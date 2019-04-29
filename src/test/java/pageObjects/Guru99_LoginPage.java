package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Guru99_LoginPage {

    public WebDriver driver;

    public Guru99_LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }

    @FindBy(xpath = "//input[@name='uid']")
    public WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@name='btnLogin']")
    public WebElement loginButton;


}
