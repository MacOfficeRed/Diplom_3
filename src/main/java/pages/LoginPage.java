package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    private final SelenideElement
            loginButton = $(".button_button__33qZ0"),
            loginHeader = $(byTagAndText("h2", "Вход")),
            passwordInput = $("[name='Пароль']"),
            emailInput = $("[name='name']"),
            registerLink = $(byLinkText("Зарегистрироваться")),
            forgoPasswordLink = $(byLinkText("Восстановить пароль"));

    @Step("Wait until Login page will be loaded")
    public void loginPageShouldBeLoaded() {
        loginHeader.shouldBe(visible);
    }

    @Step("Set a user's email")
    public void setUserEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Set a user's password")
    public void setUserPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Login button click")
    public MainPage clickLogin() {
        loginButton.click();
        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.mainPageShouldBeLoaded();
        return mainPage;
    }

    @Step("Register link click")
    public RegisterPage clickRegister() {
        registerLink.click();
        RegisterPage registerPage = Selenide.page(RegisterPage.class);
        registerPage.registerPageShouldBeLoaded();
        return registerPage;
    }

    @Step("Forgot password link click")
    public ForgotPasswordPage clickForgotPassword() {
        forgoPasswordLink.click();
        ForgotPasswordPage forgotPasswordPage = Selenide.page(ForgotPasswordPage.class);
        forgotPasswordPage.forgotPasswordPageShouldBeLoaded();
        return forgotPasswordPage;
    }
}
