package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegisterPage {
    private final SelenideElement
            registerButton = $(".button_button__33qZ0"),
            invalidPasswordText = $(".input__error"),
            loginLink = $(".Auth_link__1fOlj"),
            passwordInput = $("[name='Пароль']"),
            nameInput = $$("[name='name']").get(0),
            emailInput = $$("[name='name']").get(1),
            registerHeader = $(byTagAndText("h2", "Регистрация"));

    @Step("Wait until Register page will be loaded")
    public void registerPageShouldBeLoaded() {
        registerHeader.shouldBe(visible);
    }

    @Step("Set a user's name")
    public void setUserName(String name) {
        nameInput.setValue(name);
    }

    @Step("Set a user's email")
    public void setUserEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Set a user's password")
    public void setUserPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Click Register button")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Click Login button")
    public LoginPage clickLogin() {
        loginLink.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loginPageShouldBeLoaded();
        return loginPage;
    }

    @Step("Error message 'Некорректный пароль' should be visible")
    public void errorPasswordMessageShouldBeVisible() {
        invalidPasswordText.shouldBe(visible).shouldHave(exactText("Некорректный пароль"));
    }
}
