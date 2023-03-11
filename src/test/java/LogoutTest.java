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
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static helpers.WebDriverUtils.getCurrentUrl;
import static model.User.getRandomUser;
import static org.junit.Assert.assertEquals;
import static pages.LoginPage.LOGIN_PAGE_URL;
import static pages.MainPage.MAIN_PAGE_URL;

public class LogoutTest {
    MainPage mainPage;
    LoginPage loginPage;
    ProfilePage profilePage;
    User user;
    UserClient userClient;

    @Before
    public void setup() {
        user = getRandomUser();
        userClient = new UserClient();
        userClient.createUserUsingApi(user);
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void cleanup() {
        userClient.deleteUserUsingApi(user);
    }

    @Test
    @Tag("LogOut")
    @Description("Выход из аккаунта\n" +
            "Проверь выход по кнопке «Выйти» в личном кабинете.")
    @DisplayName("Check logOut on the on the Profile page")
    public void checkLogOutOnProfilePage() {
        loginPage = mainPage.clickLoginButton();
        loginPage.setUserEmail(user.getEmail());
        loginPage.setUserPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        profilePage = mainPage.clickProfileButtonAfterAuth();
        profilePage.clickLogout();
        assertEquals(LOGIN_PAGE_URL, getCurrentUrl());
        closeWindow();
    }
}
