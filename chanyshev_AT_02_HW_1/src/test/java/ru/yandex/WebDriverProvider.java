package ru.yandex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

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
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * Выполняется после каждого теста
     */
    @AfterEach
    public void after() {

        chromeDriver.quit();
    }
}
