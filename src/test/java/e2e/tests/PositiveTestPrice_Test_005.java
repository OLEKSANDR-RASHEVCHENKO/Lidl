package e2e.tests;

import e2e.enums.NavigationMenu;
import e2e.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositiveTestPrice_Test_005 extends BaseTest{
    public static String extractPrice(String text) {
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new NumberFormatException("No valid number found in text: " + text);
    }
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
        //Assert.assertEquals(itemTitleOnItemPage,itemTitleOnCartIconPage);
        Assert.assertEquals(itemPriceOnItemPage,itemPriceONCartIconPage);

        String versand=cartIcon.getVersandKosten();
        String totalPrice=cartIcon.getTotalPrice();
        String cleanedVersand = extractPrice(versand);
        String cleanedTotalPrice = extractPrice(totalPrice);

        double itemPriceOnItemPages = Double.parseDouble(itemPriceOnItemPage);
        double versandKosten = Double.parseDouble(cleanedVersand);
        double totalPriceExpected = Double.parseDouble(cleanedTotalPrice);

        System.out.println("itemPriceOnItemPages: " + itemPriceOnItemPages);
        System.out.println("versandKosten: " + versandKosten);
        System.out.println("totalPriceExpected: " + totalPriceExpected);
        double totalPriceActual = itemPriceOnItemPages + versandKosten;
        System.out.println("totalPriceActual: " + totalPriceActual);
        Assert.assertEquals(totalPriceExpected, totalPriceActual);
        cartIcon.removeItem(itemTitleOnItemPage);


    }

}
