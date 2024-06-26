package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class StartPage extends BasePage {

    public StartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='n-navigation__top-menu-wrapper']")
    WebElement header;
    @FindBy(xpath = "//*[@id='onetrust-accept-btn-handler']")
    WebElement cookies;
    @FindBy(xpath = "//a[@class='n-navigation__menu-nav--link n-header__icon-link']//span[@class='n-navigation__menu-nav--image-wrapper']//span[@role='img']")
    WebElement loginButton;

    public void waitForLoadingStartPage(){
        getWait().forVisibility(header);
        Assert.assertTrue(header.isDisplayed());
        getWait().forVisibility(loginButton);
        Assert.assertTrue(loginButton.isDisplayed());
    }
    public void clickOnLoginButtonToLoginIntoSystem(){
        loginButton.click();
    }
    public void cookies(){
        cookies.click();
    }
    public void clickOnOneFromCategory(String category)
    {
        WebElement categorys  = driver.findElement(By.xpath("//*[@class='ux-base-slider__slide']//*[text()='"+category+"']"));
        if (!categorys.isDisplayed()){
            WebElement weiter = driver.findElement(By.xpath("//div[@class='ux-base-slider']//button[@title='Weiter']"));
            weiter.click();
            categorys.click();
        }else {
            categorys.click();
        }
    }
}
