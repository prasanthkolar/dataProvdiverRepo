package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Guru99_HomePage {

    public WebDriver driver;

    public Guru99_HomePage(WebDriver driver)
    {
        this.driver=driver;
    }

    @FindBy(linkText = "New Customer")
    public WebElement addNewCustomerLink;

    @FindBy(name = "name")
    public WebElement customerName;

    @FindBy(xpath = "//input[@type='radio' and @value='m']")
    public WebElement gender;

    @FindBy(id = "dob")
    public WebElement dob;

    @FindBy(name = "addr")
    public WebElement address;

    @FindBy(name = "city")
    public WebElement city;

    @FindBy(name = "state")
    public WebElement state;

    @FindBy(name = "pinno")
    public WebElement pin;

    @FindBy(name = "telephoneno")
    public WebElement phoneNumber;

    @FindBy(name = "emailid")
    public WebElement emailId;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(name = "sub")
    public WebElement submitButton;

    @FindBy(linkText = "Log out")
    public WebElement logOut;
}
