package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CategoryPage extends BasePage{

    public CategoryPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@class='s-page-heading__title']")
    WebElement header;
    @FindBy(xpath = "//main[@id='s-main-container']")
    WebElement container;
    @FindBy(xpath = "//button[@class='s-load-more__button']")
    WebElement weitereProdukteLaden;

    public void chooseSubCategory(String subCategoryName){
        WebElement subCategory = driver.findElement(By.xpath("//*[text()='"+subCategoryName+"']"));
        subCategory.click();
    }
    public void waiteForLoading(){
        getWait().forVisibility(header);
        Assert.assertTrue(header.isDisplayed());
        getWait().forVisibility(container);
        Assert.assertTrue(container.isDisplayed());
    }
    public String getTitleFromCategoryPage(){
        String title = header.getText();
        return title;
    }
    public void clickOnMoreProducts(){
        getWait().forVisibility(weitereProdukteLaden);
        weitereProdukteLaden.click();
    }
    public void sort(String sort){
        WebElement element = driver.findElement(By.xpath("//span[@class='s-sorts-flyout__label']"));
        WebElement sortCategory = driver.findElement(By.xpath("//a[normalize-space()='"+sort+"']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).moveToElement(sortCategory).click().perform();
    }
}
