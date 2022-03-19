package custom.drivers;

import custom.properties.TestData;
import custom.utils.Actions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class Manager {
    private static WebDriver currentDriver;

    public static WebDriver getCurrentDriver() {
        return currentDriver;
    }

    public static void initChrome() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(List.of("start-maximized"));
        try {
            currentDriver = new ChromeDriver(options);
        } catch (SessionNotCreatedException e) {
            Assertions.fail("Данный драйвер не совместим с текущим браузером. Используйте другой драйвер");
        }
        setDriverDefaultSettings();
        reinitStaticVariables();
    }

    private static void setDriverDefaultSettings() {
        currentDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestData.driverProps.defaultTimeout()));//.implicitlyWait(TestData.driverProps.defaultTimeout(), TimeUnit.SECONDS);
        currentDriver.manage().deleteAllCookies();
    }

    //Статические переменные можно переинициализировать и в драйвер менеджере
    private static void reinitStaticVariables() {
        Actions.initActions();
        Waits.initWait();
    }

    public static void killCurrentDriver() {
        if (currentDriver != null) {
            currentDriver.quit();
            currentDriver = null;
        }
    }
}
