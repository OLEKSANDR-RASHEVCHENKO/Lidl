package e2e.enums;

public enum NavigationMenu {
    LIDL_LOGO("//img[@alt='Lidl Logo Deutschland']"),
    ONLINE_SHOP("//a[@class='n-navigation__menu-nav--link hide-to-sm-max']//span[@class='n-navigation__menu-nav--label'][normalize-space()='Onlineshop']"),
    FILIAL_ANGEBOTE("//a[@class='n-navigation__menu-nav--link hide-to-sm-max']//span[@class='n-navigation__menu-nav--label'][normalize-space()='Filial-Angebote']"),
    SEARCH_INPUT("//input[@id='s-search-input-field']"),
    FLYER_ICON("//span[@class='m-icon m-icon--flyer']"),
    ACCOUNT_ICON("//a[@class='n-navigation__menu-nav--link n-header__icon-link']//span[@class='n-navigation__menu-nav--image-wrapper']//span[@role='img']"),
    SHOPPING_CART_ICON("//span[@class='m-icon m-icon--shopping-cart']");

    private final String locator;

    NavigationMenu(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
