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
import static pages.ProfilePage.PROFILE_PAGE_URL;

public class OpenProfileTest {
    private MainPage mainPage;
    private LoginPage loginPage;
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
    @Tag("Opening profile")
    @Description("Переход в личный кабинет\n" +
            "Проверь переход по клику на «Личный кабинет».")
    @DisplayName("Check opening the Profile page")
    public void profilePageShouldBeOpened() {
        loginPage = mainPage.clickLoginButton();
        loginPage.setUserEmail(user.getEmail());
        loginPage.setUserPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        mainPage.clickProfileButtonAfterAuth();
        assertEquals(PROFILE_PAGE_URL, getCurrentUrl());
    }
}
