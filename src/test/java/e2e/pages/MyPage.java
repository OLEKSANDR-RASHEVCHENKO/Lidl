package e2e.pages;

import e2e.enums.NavigationMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MyPage extends BasePage{

    public MyPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//h3[normalize-space()='Mein Lidl Konto']")
    WebElement header;
    @FindBy(xpath = "//span[normalize-space()='Meine Bestellungen']")
    WebElement meineBestellungen;
    @FindBy(xpath = "//span[normalize-space()='Kassenbons']")
    WebElement kassenbons;
    @FindBy(xpath = "//span[normalize-space()='Merkliste']")
    WebElement merkliste;
    @FindBy(xpath = "//img[@alt='Lidl Logo Deutschland']")
    WebElement lidlLogo;

    public void waiteForLoading(){
        getWait().forVisibility(header);
        Assert.assertTrue(header.isDisplayed());
        getWait().forVisibility(meineBestellungen);
        Assert.assertTrue(meineBestellungen.isDisplayed());
        getWait().forVisibility(kassenbons);
        Assert.assertTrue(kassenbons.isDisplayed());
        getWait().forVisibility(merkliste);
        Assert.assertTrue(merkliste.isDisplayed());
        getWait().forVisibility(lidlLogo);
        Assert.assertTrue(lidlLogo.isDisplayed());
    }
//    public void clickOnLidlLogo(){
//        lidlLogo.click();
//    }
    public void clickOnLidlLogo(NavigationMenu navigationMenu){
        WebElement lidlLogo = driver.findElement(By.xpath(navigationMenu.getLocator()));
        lidlLogo.click();
    }
}
