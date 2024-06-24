package e2e.tests;

import com.github.javafaker.Faker;
import e2e.pages.LoginPage;
import e2e.pages.StartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLogginProzess_Test_002 extends BaseTest  {

    StartPage startPage;
    LoginPage loginPage;
    Faker faker;

    public void negativePostMethod(String email,String password,boolean wrongEmail,boolean wrongPassword){
        String emailErrorReference = "Diese E-Mail-Adresse gehört zu keinem Lidl Konto. Versuche es erneut oder erstelle ein Konto.";
        String passwordErrorReference = "Falsches Passwort. Versuche es erneut oder tippe auf \"Passwort vergessen\", um es zu ändern.";
        startPage = new StartPage(app.driver);
        startPage.waitForLoadingStartPage();
        startPage.cookies();
        startPage.clickOnLoginButtonToLoginIntoSystem();
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoginPage();
        loginPage.emailInput(email);
        if (wrongEmail){
            loginPage.emailErrorIsVisible();
            String extrahierteEmailError = loginPage.getEmailError();
            Assert.assertEquals(extrahierteEmailError,emailErrorReference);
        }if (wrongPassword){
            loginPage.waitForPasswordBlock();
            loginPage.passwordInput(password);
            loginPage.passwordErrorIsVisible();
        }
    }
    @Test
    public void userCannotLoginIntoSystemWithWrongEmail(){
        negativePostMethod("rashevchenkoooo@gmail.com","Gazmanov1234",true,false);
    }
    @Test
    public void userCannotLoginIntoSystemWithWrongPassword(){
        negativePostMethod("rashevchenkoo@gmail.com","Gazmanovd1234",false,true);
    }
}
