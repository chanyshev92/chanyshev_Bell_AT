package ru.yandex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * Класс для доступа к браузеру Google Chrome
 */
public class WebDriverProvider {

    protected WebDriver chromeDriver;

    /**
     * Выполняются перед каждым тестом
     */
    @BeforeEach
    public void before() {

        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    /**
     * Выполняется после каждого теста
     */
    @AfterEach
    public void after() {

        chromeDriver.quit();
    }
}
