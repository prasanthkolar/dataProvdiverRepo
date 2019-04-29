package testScripts;

import baseTest.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Guru99_HomePage;

public class HomeTest extends BaseTest {

    @Test
    public void verifyHomePageTitle()
    {
        String expectedTitle="Guru99 Bank Home Page";
        String actualTitle=driver.getTitle();
        Allure.addDescription("Expected Title: "+expectedTitle);
        Allure.addDescription("Actual Title: "+actualTitle);
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @Test(enabled = false)
    public void addNewCustomer()
    {
    homePage.addNewCustomerLink.click();
    homePage.customerName.sendKeys("");
    homePage.gender.click();
    homePage.dob.sendKeys("");
    homePage.address.sendKeys("");
    homePage.city.sendKeys("");
    homePage.state.sendKeys("");
    homePage.pin.sendKeys("");
    homePage.emailId.sendKeys("");
    homePage.password.sendKeys("");
    homePage.submitButton.click();

    }
}
