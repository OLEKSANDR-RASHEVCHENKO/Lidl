package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ItemPage extends BasePage{

    public ItemPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@data-qa-label='keyfacts-title']")
    WebElement title;
    @FindBy(xpath = "//div[@class='m-price__price']")
    WebElement price;
    @FindBy(xpath = "//button[@id='addToCart']")
    WebElement addToCardButton;
    @FindBy(xpath = "//div[@class=' otc-cart-flyout__buttons pca-row-flex-reverse']//button[@class='otc-cart-flyout__button otc-cart-flyout__button--continue-shopping'][normalize-space()='Weiter einkaufen']")
    WebElement weiterEnkaufenButton;
    @FindBy(xpath = "//div[contains(@class,'otc-cart-flyout__buttons pca-row-flex-reverse')]//button[@class='otc-cart-flyout__button otc-cart-flyout__button--to-checkout'][normalize-space()='Zur Kasse']")
    WebElement zurKasseButton;
    @FindBy(xpath = "//button[normalize-space()='Auf die Merkliste']")
    WebElement aufDieMerkliste;

    public void waitForLoading(){
        getWait().forVisibility(title);
        Assert.assertTrue(title.isDisplayed());
        getWait().forVisibility(price);
        Assert.assertTrue(price.isDisplayed());
        getWait().forVisibility(addToCardButton);
        Assert.assertTrue(addToCardButton.isDisplayed());
    }

    public String getItemTitleFromItemPage(){
        String itemTitle  = title.getText();
        return itemTitle;
    }
    public String getPrice(){
        String itemPrice  = price.getText();
        return itemPrice;
    }

    public void addItemToCard(boolean weiterEnkaufen){
        addToCardButton.click();
        if (weiterEnkaufen){
            getWait().forVisibility(weiterEnkaufenButton);
            weiterEnkaufenButton.click();
            waitForLoading();
        }else {
            getWait().forVisibility(zurKasseButton);
            zurKasseButton.click();
        }
    }
    public void likeItem(){
        aufDieMerkliste.click();
    }
}
