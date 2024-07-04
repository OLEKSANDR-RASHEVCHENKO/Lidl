package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CartIcon extends BasePage{

    public CartIcon(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//section[@class='page-container']")
    WebElement pageContainer;
    @FindBy(xpath = "//div[contains(text(), 'Zwischensumme')]/following-sibling::div[@class='base-price__price']")
    WebElement zwischensumme;
    @FindBy(xpath = "//div[contains(text(), ' Versandkosten ')]/following-sibling::div[@class='base-price__price']")
    WebElement versandkosten;
    @FindBy(xpath = "//div[contains(text(), ' Insgesamt ')]/following-sibling::div[@class='base-price__price base-price__price--total']")
    WebElement totalPrice;




    public void waitForLoading(){
        getWait().forVisibility(pageContainer);
        Assert.assertTrue(pageContainer.isDisplayed());
    }

    public String getTitleFromCartItemPage(String item){
        String element = driver.findElement(By.xpath("//*[contains(text(), ' "+item+" ')]")).getText();
        return element;
    }

    public String getPriceFromCartItemPage(String item){
        String element = driver.findElement(By.xpath("//*[contains(text(), ' "+item+" ')]/ancestor::div[@class='cartitem cuke-cartitem cart-item']//*[@class='cartitem__informations-content']//*[@class='base-price__price']")).getText();
        return element;
    }
    public void removeItem(String item){
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), ' "+item+" ')]/ancestor::div[@class='cartitem cuke-cartitem cart-item']//*[@data-ee='cart-item-remove-item']"));
        element.click();
    }
    public void visibilityOfItem(String item,boolean elementVisible){
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), ' "+item+" ')]/ancestor::div[@class='cartitem cuke-cartitem cart-item']"));
        if (elementVisible){
            getWait().forVisibility(element);
            Assert.assertTrue(element.isDisplayed());
        }else {
            getWait().forInvisibility(element);
            Assert.assertFalse(element.isDisplayed());
        }
    }

}
