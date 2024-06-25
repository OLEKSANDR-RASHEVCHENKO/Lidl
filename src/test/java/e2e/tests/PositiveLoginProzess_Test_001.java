package e2e.tests;

import e2e.enums.NavigationMenu;
import e2e.pages.LoginPage;
import e2e.pages.MyPage;
import e2e.pages.StartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveLoginProzess_Test_001 extends BaseTest{
    StartPage startPage;
    LoginPage loginPage;
    MyPage myPage;
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
        myPage = new MyPage(app.driver);
        myPage.waiteForLoading();
        myPage.clickOnLidlLogo(NavigationMenu.LIDL_LOGO);
        startPage.waitForLoadingStartPage();
        startPage.clickOnOneFromCategory(category);
    }
}
