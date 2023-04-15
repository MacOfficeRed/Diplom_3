package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {
    public static final String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    private final ElementsCollection
            profileLinks = $$(".Account_listItem__35dAP");

    private final SelenideElement
            exitButton = $(".Account_button__14Yp3"),
            constructorLink = $(".AppHeader_header__linkText__3q_va"),
            mainLogoLink = $(".AppHeader_header__logo__2D0X2");

    @Step("Wait until Profile page will be loaded")
    public void profilePageShouldBeLoaded() {
        profileLinks.shouldHave(CollectionCondition.texts("Профиль", "История заказов", "Выход"));
    }

    @Step("Click Logout button")
    public LoginPage clickLogout() {
        exitButton.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loginPageShouldBeLoaded();
        return loginPage;
    }

    @Step("Click Constructor link")
    public MainPage clickConstructor() {
        constructorLink.click();
        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.mainPageShouldBeLoaded();
        return mainPage;
    }

    @Step("Click on the main logo link (logo with Stellar burgers)")
    public MainPage clickStellarBurgersLogo() {
        mainLogoLink.click();
        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.mainPageShouldBeLoaded();
        return mainPage;
    }
}
