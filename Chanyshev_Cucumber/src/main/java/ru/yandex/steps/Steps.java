package ru.yandex.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.yandex.pages.SubCategoryPage;
import ru.yandex.pages.YandexMainPage;
import ru.yandex.pages.YandexMarketPage;

import static com.codeborne.selenide.Selenide.open;

public class Steps {

    private YandexMainPage yandexMainPage;
    private YandexMarketPage yandexMarketPage;
    private SubCategoryPage smartPhonePage;

    public void initBrowser(){

        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        Configuration.timeout = 6000;
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

    }

    public void openYandexMainPage(){
        yandexMainPage = open("https://yandex.ru/",YandexMainPage.class);

    }

    public void openYandexMarketPage(){

        yandexMarketPage=yandexMainPage.goYandexElement("market",YandexMarketPage.class);
    }

    public void goSmartPhonePage(){

        smartPhonePage=yandexMarketPage.goToSubCategoryPage("Электроника","Смартфоны");
    }

    public void filterChoice(String manufacturer){

        smartPhonePage.filterChoice("Производитель",manufacturer);
    }

    public void checkManufacturerByNextPage (String manufacturer){

        smartPhonePage.checkManufacturerByNextPage(manufacturer);
    }
    public void closeBrowser(){

        WebDriverRunner.closeWebDriver();
    }


}
