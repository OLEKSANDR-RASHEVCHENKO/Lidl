package e2e.tests;

import e2e.enums.NavigationMenu;
import e2e.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTestPrice_Test_005 extends BaseTest{
    StartPage startPage;
    LoginPage loginPage;
    MyPage myPage;
    CategoryPage categoryPage;
    ItemPage itemPage;
    CartIcon cartIcon;
    @Test
    public void priceComparison(){
        String email = "rashevchenkoo@gmail.com";
        String password = "Gazmanov_1234";
        String category = "Multimedia";
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
        categoryPage.clickOnItem(3);
        itemPage = new ItemPage(app.driver);
        itemPage.waitForLoading();
        String itemTitleOnItemPage  = itemPage.getItemTitleFromItemPage();
        String itemPriceOnItemPage  = itemPage.getPrice();
        itemPage.addItemToCard(true);
        itemPage.clickOnNavi(NavigationMenu.SHOPPING_CART_ICON);
        cartIcon = new CartIcon(app.driver);
        cartIcon.waitForLoading();
        String itemTitleOnCartIconPage=cartIcon.getTitleFromCartItemPage(itemTitleOnItemPage);
        String itemPriceONCartIconPage=cartIcon.getPriceFromCartItemPage(itemTitleOnItemPage);
        Assert.assertEquals(itemTitleOnItemPage,itemTitleOnCartIconPage);
        Assert.assertEquals(itemPriceOnItemPage,itemPriceONCartIconPage);

        String versand=cartIcon.getVersandKosten();
        String totalPrice=cartIcon.getTotalPrice();

        // Удаляем все символы, кроме цифр и одной точки
        String cleanedVersand = versand.replaceAll("[^\\d.]", "");
        String cleanedTotalPrice = totalPrice.replaceAll("[^\\d.]", "");

// Убираем все точки, кроме последней, чтобы избежать "multiple points" ошибки
        cleanedVersand = cleanedVersand.replaceAll("(?<=\\d)\\.(?=\\d)", "");
        cleanedTotalPrice = cleanedTotalPrice.replaceAll("(?<=\\d)\\.(?=\\d)", "");

        double itemPriceOnItemPages = Double.valueOf(itemPriceOnItemPage);
        double versandKosten = Double.parseDouble(cleanedVersand);
        double totalPriceExpected = Double.parseDouble(cleanedTotalPrice);

        System.out.println("itemPriceOnItemPages: " + itemPriceOnItemPages); // 14.99
        System.out.println("versandKosten: " + versandKosten); // 5.95
        System.out.println("totalPriceExpected: " + totalPriceExpected); // 20.94

        double totalPriceActual = itemPriceOnItemPages + versandKosten;

        System.out.println("totalPriceActual: " + totalPriceActual); // Должно быть 20.94

        Assert.assertEquals(totalPriceExpected, totalPriceActual);










    }

}
