package com.chanyshev92;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.YandexMarketPage;
import pages.YandexMarketProductListPage;
import pages.YandexStartPage;

import java.util.List;

public class Test extends WebDriverProvider {
    @DisplayName("Поиск ноутбуков HP и Lenovo на Я.Маркете в диапазоне 10-900к")
    @org.junit.jupiter.api.Test
    public void testForNotebooksOnYM() throws InterruptedException {

        chromeDriver.get("https://yandex.ru");
        YandexStartPage yandexStartPage = new YandexStartPage(chromeDriver);

        String hrefMarket = yandexStartPage.getMarketElement().getAttribute("href");

        chromeDriver.switchTo().newWindow(WindowType.TAB);
        chromeDriver.get(hrefMarket);

        YandexMarketPage yandexMarketPage = new YandexMarketPage(chromeDriver);
        String hrefSubcategory = yandexMarketPage.findSubCategory("Компьютеры", "Ноутбуки").getAttribute("href");
        chromeDriver.get(hrefSubcategory);

        YandexMarketProductListPage listPage=new YandexMarketProductListPage(chromeDriver);
        Thread.sleep(6000);
        listPage.setPriceFrom("10000");
        Thread.sleep(6000);
        listPage.setPriceTo("900000");
        Thread.sleep(6000);
        listPage.checkboxOfManufacturer("Производитель","HP");
        listPage.checkboxOfManufacturer("Производитель","Lenovo");
        Thread.sleep(6000);
        listPage.changeShowCount("Показывать по 12");
        Thread.sleep(6000);
        List<WebElement> listPageResults = listPage.getResults();
        Assertions.assertEquals(listPageResults.size(), 12, "Результатов поиска не 12");
        String beforeItem = listPageResults.get(0).getText();
        Thread.sleep(6000);
        listPage.findFirstItem(beforeItem);
        Thread.sleep(6000);
        Assertions.assertTrue(beforeItem.contains(listPage.getResults().get(0).getText()),"Наименование товара не соответвует сохраненному значению");


    }
}
