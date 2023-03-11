package helpers;

import com.codeborne.selenide.WebDriverRunner;

public class WebDriverUtils {
    public static String getCurrentUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }
}
