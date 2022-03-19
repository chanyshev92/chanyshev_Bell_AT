package steps;

import custom.drivers.Manager;
import io.qameta.allure.Step;

public class Steps {

    @Step("Переходим на страницу {url}")
    public static void goPage(String url) {
        Manager.getCurrentDriver().get(url);
    }
}
