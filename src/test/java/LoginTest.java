import client.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static helpers.WebDriverUtils.getCurrentUrl;
import static model.User.getRandomUser;
import static org.junit.Assert.assertEquals;
import static pages.LoginPage.LOGIN_PAGE_URL;
import static pages.MainPage.MAIN_PAGE_URL;

public class LoginTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private ProfilePage profilePage;
    private User user;
    private UserClient userClient;

    @Before
    public void setup() {
        user = getRandomUser();
        userClient = new UserClient();
        userClient.createUserUsingApi(user);
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void cleanup() {
        profilePage = mainPage.clickProfileButtonAfterAuth();
        profilePage.clickLogout();
        assertEquals(LOGIN_PAGE_URL, getCurrentUrl());
        closeWindow();
        userClient.deleteUserUsingApi(user);
    }

    @Test
    @Tag("LogIn")
    @Description("Проверь:\n" +
            "вход по кнопке «Войти в аккаунт» на главной")
    @DisplayName("Check logIn on the Main page")
    public void checkLogInOnMainPage() {
        loginPage = mainPage.clickLoginButton();
        loginPage.setUserEmail(user.getEmail());
        loginPage.setUserPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        assertEquals(MAIN_PAGE_URL, getCurrentUrl());
    }

    @Test
    @Tag("LogIn")
    @Description("Проверь:\n" +
            "вход через кнопку «Личный кабинет»")
    @DisplayName("Check logIn on the Profile page")
    public void checkLogInOnProfilePage() {
        loginPage = mainPage.clickProfileButtonBeforeAuth();
        loginPage.setUserEmail(user.getEmail());
        loginPage.setUserPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        assertEquals(MAIN_PAGE_URL, getCurrentUrl());
    }

    @Test
    @Tag("LogIn")
    @Description("Проверь:\n" +
            "вход через кнопку в форме регистрации")
    @DisplayName("Check logIn on the Register page")
    public void checkLogInOnRegisterPage() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        loginPage = registerPage.clickLogin();
        loginPage.setUserEmail(user.getEmail());
        loginPage.setUserPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        assertEquals(MAIN_PAGE_URL, getCurrentUrl());
    }

    @Test
    @Tag("LogIn")
    @Description("Проверь:\n" +
            "вход через кнопку в форме восстановления пароля.")
    @DisplayName("Check logIn on the Forgot password page")
    public void checkLogInOnForgotPasswordPage() {
        loginPage = mainPage.clickLoginButton();
        forgotPasswordPage = loginPage.clickForgotPassword();
        loginPage = forgotPasswordPage.clickLogin();
        loginPage.setUserEmail(user.getEmail());
        loginPage.setUserPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        assertEquals(MAIN_PAGE_URL, getCurrentUrl());
    }
}
