package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {
    private final SelenideElement
            loginLink = $(".Auth_link__1fOlj"),
            forgotHeader = $(byTagAndText("h2", "Восстановление пароля"));

    @Step("Wait until Forgot password page will be loaded")
    public void forgotPasswordPageShouldBeLoaded() {
        forgotHeader.shouldBe(visible);
    }

    @Step("Login click")
    public LoginPage clickLogin() {
        loginLink.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.loginPageShouldBeLoaded();
        return loginPage;
    }
}
