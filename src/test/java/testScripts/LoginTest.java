package testScripts;

import Model.LoginModel;
import baseTest.BaseTest;
import dataSource.DataProvider;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.Guru99_LoginPage;
import readExcel.TabToUse;

public class LoginTest extends BaseTest {

   // @Parameters({"userName","password"})
    @TabToUse({"Login"})
    @Test(dataProvider = "LoginModel", dataProviderClass = DataProvider.class, alwaysRun = true)
    public void loginTest(String uid,String pass)
    {
LoginModel loginModel=new LoginModel();
System.out.println("Test started");
        loginPage.username.sendKeys(loginModel.getUsername());
        loginPage.password.sendKeys(loginModel.getPassword());
        loginPage.loginButton.click();
        homePage.logOut.click();
        driver.switchTo().alert().accept();
    }


}
