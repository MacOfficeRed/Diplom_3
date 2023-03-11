import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static pages.MainPage.MAIN_PAGE_URL;

public class ConstructorTabsTest {
    MainPage mainPage;

    @Before
    public void setup() {
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @Test
    @Tag("Constructor section")
    @Description("Проверь, что работают переходы к разделам:\n" +
            "«Булки»")
    @DisplayName("Check opening BunsTab")
    public void openBunsTabAndCheck() {
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        mainPage.selectedTabShouldHaveText("Булки");
    }

    @Test
    @Tag("Constructor section")
    @Description("Проверь, что работают переходы к разделам:\n" +
            "«Соусы»")
    @DisplayName("Check opening SaucesTab")
    public void openSaucesTabAndCheck() {
        mainPage.clickSaucesTab();
        mainPage.selectedTabShouldHaveText("Соусы");
    }

    @Test
    @Tag("Constructor section")
    @Description("Проверь, что работают переходы к разделам:\n" +
            "«Начинки».")
    @DisplayName("Check opening FillingsTab")
    public void openFillingsTabAndCheck() {
        mainPage.clickFillingsTab();
        mainPage.selectedTabShouldHaveText("Начинки");
    }
}
