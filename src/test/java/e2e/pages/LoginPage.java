package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='login-form content-block']")
    WebElement loginBlock;
    @FindBy(xpath = "//input[@id='field_EmailOrPhone']")
    WebElement loginInput;
    @FindBy(xpath = "//button[@id='button_btn_submit_email']")
    WebElement weiterButton;
    @FindBy(xpath = "//label[normalize-space()='Passwort']")
    WebElement passwordBlock;
    @FindBy(xpath = "//*[@class='content-header']//label")
    WebElement emailAfterInputEmail;
    @FindBy(xpath = "//input[@id='field_Password']")
    WebElement passwordInput;
    @FindBy(xpath = "//button[@id='button_submit']")
    WebElement weiterButtonAfterInputPassword;

    @FindBy(xpath = "//p[@class='error_EmailOrPhone']")
    WebElement emailError;
    @FindBy(xpath = "//p[@class='error_Password']")
    WebElement passwordError;
    public void waitForLoginPage(){
        getWait().forVisibility(loginBlock);
        Assert.assertTrue(loginBlock.isDisplayed());
    }
    public void emailInput(String email){
        getWait().forVisibility(loginInput);
        Assert.assertTrue(loginInput.isDisplayed());
        loginInput.sendKeys(email);
        weiterButton.click();
    }
    public String getEmail(){
        String email=emailAfterInputEmail.getText();
        return  email;
    }
    public void waitForPasswordBlock(){
        getWait().forVisibility(passwordBlock);
        Assert.assertTrue(passwordBlock.isDisplayed());
    }
    public void passwordInput(String password){
        getWait().forVisibility(passwordBlock);
        Assert.assertTrue(passwordBlock.isDisplayed());
        passwordInput.sendKeys(password);
        weiterButtonAfterInputPassword.click();
    }

    public void emailErrorIsVisible(){
        getWait().forVisibility(emailError);
        Assert.assertTrue(emailError.isDisplayed());
    }
    public String getEmailError(){
        String emailErrorText= emailError.getText();
        return emailErrorText;
    }
    public void passwordErrorIsVisible(){
        getWait().forVisibility(passwordError);
        Assert.assertTrue(passwordError.isDisplayed());
    }
    public String getPasswordError(){
        String passwordErrorText = passwordError.getText();
        return passwordErrorText;
    }


}
