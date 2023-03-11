import client.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static helpers.WebDriverUtils.getCurrentUrl;
import static model.User.getRandomUser;
import static org.junit.Assert.assertEquals;
import static pages.LoginPage.LOGIN_PAGE_URL;
import static pages.MainPage.MAIN_PAGE_URL;

public class RegistrationTest {
    MainPage mainPage;
    RegisterPage registerPage;
    LoginPage loginPage;
    User user;
    UserClient userClient;

    @Before
    public void setup() {
        user = getRandomUser();
        userClient = new UserClient();
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void cleanup() {
        userClient.deleteUserUsingApi(user);
    }

    @Test
    @Tag("registration")
    @Description("Регистрация. Проверь:\n" +
            "Успешную регистрацию.")
    @DisplayName("Check successful user registration")
    public void checkSuccessfulUserRegistration() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        registerPage.setUserName(user.getName());
        registerPage.setUserEmail(user.getEmail());
        registerPage.setUserPassword(user.getPassword());
        registerPage.clickRegisterButton();
        loginPage.loginPageShouldBeLoaded();
        assertEquals(LOGIN_PAGE_URL, getCurrentUrl());
        closeWindow();
    }

    @Test
    @Tag("registration")
    @Description("Регистрация. Проверь:\n" +
            "Ошибку для некорректного пароля. Минимальный пароль — шесть символов.")
    @DisplayName("Check error message when password is less than 6 symbols")
    public void checkErrorMessageWhenPasswordIsInvalid() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        user.setPassword("123");
        registerPage.setUserName(user.getName());
        registerPage.setUserEmail(user.getEmail());
        registerPage.setUserPassword(user.getPassword());
        registerPage.clickRegisterButton();
        registerPage.errorPasswordMessageShouldBeVisible();
        closeWindow();
    }
}
