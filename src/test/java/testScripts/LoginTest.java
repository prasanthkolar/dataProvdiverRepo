package testScripts;

import baseTest.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.Guru99_LoginPage;

public class LoginTest extends BaseTest {

    @Parameters({"userName","password"})
    @Test
    public void loginTest(String uid,String pass)
    {


        loginPage.username.sendKeys(uid);
        loginPage.password.sendKeys(pass);
        loginPage.loginButton.click();
    }


}
