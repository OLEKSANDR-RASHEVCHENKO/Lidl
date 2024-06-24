package e2e.tests;

import e2e.pages.LoginPage;
import e2e.pages.StartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveLoginProzess_Test_001 extends BaseTest{
    StartPage startPage;
    LoginPage loginPage;
    @Test
    public void loginTest(){
        String email = "rashevchenkoo@gmail.com";
        String password = "Gazmanov_1234";
        String category = "Mode";
        startPage = new StartPage(app.driver);
        startPage.waitForLoadingStartPage();
        startPage.cookies();
        startPage.clickOnLoginButtonToLoginIntoSystem();
        loginPage=new LoginPage(app.driver);
        loginPage.waitForLoginPage();
        loginPage.emailInput(email);
        loginPage.waitForPasswordBlock();
        String extrahierteEmail = loginPage.getEmail();
        Assert.assertEquals(extrahierteEmail,email);
        loginPage.passwordInput(password);
        startPage.waitForLoadingStartPage();
        startPage.clickOnOneFromCategory(category);
    }
}
