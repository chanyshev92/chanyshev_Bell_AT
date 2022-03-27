package ru.yandex;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.YandexMainPage;
import pages.YandexMarketPage;

import static com.codeborne.selenide.Selenide.open;

/**
 * @author Rustem Chanyshev
 * @version 1.0
 */
public class Tests extends BaseTests {

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка что все смартфоны из результатов поиска произведены одним производителем")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"Apple","HONOR","Google","HUAWEI","Nokia","OnePlus","OPPO","realme","Samsung","vivo","Xiaomi","ZTE"})
    public void testSelenide(String manufacturer){
        open("https://yandex.ru/",YandexMainPage.class)

                .goYandexElement("market",YandexMarketPage.class)

                .goToSubCategoryPage("Электроника","Смартфоны")

                .filterChoice("Производитель",manufacturer)

                .changeShowCountIfNotEquals(12)

                .checkManufacturerByNextPage(manufacturer);
    }
}
