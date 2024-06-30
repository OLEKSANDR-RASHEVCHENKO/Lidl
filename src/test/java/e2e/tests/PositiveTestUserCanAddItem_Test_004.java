package e2e.tests;

import e2e.enums.NavigationMenu;
import e2e.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTestUserCanAddItem_Test_004 extends BaseTest {
    StartPage startPage;
    LoginPage loginPage;
    MyPage myPage;
    CategoryPage categoryPage;
    ItemPage itemPage;
    @Test
    public void addItem(){
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
        myPage.clickOnNavi(NavigationMenu.LIDL_LOGO);
        startPage.waitForLoadingStartPage();
        startPage.clickOnOneFromCategory(category);
        categoryPage = new CategoryPage(app.driver);
        categoryPage.waiteForLoading();
        String titleFromCategory = categoryPage.getTitleFromCategoryPage();
        Assert.assertEquals(category,titleFromCategory);
        categoryPage.clickOnItem(7);
        itemPage = new ItemPage(app.driver);
        itemPage.waitForLoading();
        String itemTitle  = itemPage.getItemTitleFromItemPage();
        String itemPrice  = itemPage.getPrice();
        itemPage.addItemToCard(true);
        itemPage.clickOnNavi(NavigationMenu.SHOPPING_CART_ICON);
        System.out.println(itemTitle);
        System.out.println(itemPrice);

    }

}
