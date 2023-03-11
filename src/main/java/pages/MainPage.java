package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class MainPage {
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private final ElementsCollection
            mainPageTabDivs = $$(".tab_tab__1SPyG"); // 0 Булки, 1 Соусы, 2 Начинки

    private final SelenideElement
            loginButton = $(".button_button__33qZ0"),
            currentTab = $(".tab_tab_type_current__2BEPc"),
            mainPageHeader = $(byTagAndText("h1", "Соберите бургер")),
            accountLink = $(byLinkText("Личный Кабинет"));

    @Step("Wait until Main page will be loaded")
    public void mainPageShouldBeLoaded() {
        mainPageHeader.shouldBe(visible);
    }

    @Step("Login button click")
    public LoginPage clickLoginButton() {
        loginButton.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loginPageShouldBeLoaded();
        return loginPage;
    }

    @Step("Select Buns tab")
    public void clickBunsTab() {
        mainPageTabDivs.get(0).click();
    }

    @Step("Select Sauces tab")
    public void clickSaucesTab() {
        mainPageTabDivs.get(1).click();
    }

    @Step("Select Fillings tab")
    public void clickFillingsTab() {
        mainPageTabDivs.get(2).click();
    }

    @Step("Profile button click after auth")
    public ProfilePage clickProfileButtonAfterAuth() {
        accountLink.click();
        ProfilePage profilePage = Selenide.page(ProfilePage.class);
        profilePage.profilePageShouldBeLoaded();
        return profilePage;
    }

    @Step("Profile button click before auth")
    public LoginPage clickProfileButtonBeforeAuth() {
        accountLink.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loginPageShouldBeLoaded();
        return loginPage;
    }

    @Step("The current tab should have exact text")
    public void selectedTabShouldHaveText(String expectedText) {
        currentTab.shouldHave(exactText(expectedText));
    }
}
