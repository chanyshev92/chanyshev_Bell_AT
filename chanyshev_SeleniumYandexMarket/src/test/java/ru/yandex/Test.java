package ru.yandex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import pages.YandexMarketPageFactory;
import pages.YandexStartPageFactory;

import java.util.List;

public class Test extends WebDriverProvider {

    @DisplayName("Поиск ноутбуков HP и Lenovo на Я.Маркете в диапазоне 10-900к")
    @org.junit.jupiter.api.Test
    public void testPF() {

        chromeDriver.get("https://yandex.ru");
        YandexStartPageFactory yandexStartPage = PageFactory.initElements(chromeDriver,YandexStartPageFactory.class);

        String hrefMarket = yandexStartPage.getMarketElement().getAttribute("href");

        chromeDriver.switchTo().newWindow(WindowType.TAB);
        chromeDriver.get(hrefMarket);

        YandexMarketPageFactory marketPageFactory=PageFactory.initElements(chromeDriver,YandexMarketPageFactory.class);
        String hrefSubcategory = marketPageFactory.findSubCategory("Компьютеры", "Ноутбуки").getAttribute("href");
        chromeDriver.get(hrefSubcategory);
        marketPageFactory.setPriceFrom("10000");
        marketPageFactory.setPriceTo("900000");
        marketPageFactory.checkboxOfManufacturer("Производитель","HP");
        marketPageFactory.checkboxOfManufacturer("Производитель","Lenovo");
        marketPageFactory.changeShowCount("Показывать по 12");
        List<WebElement> listPageResults = marketPageFactory.getResults();
        Assertions.assertEquals(listPageResults.size(), 12, "Результатов поиска не 12");
        String beforeItem = listPageResults.get(0).getText();
        marketPageFactory.findItem(beforeItem);
        Assertions.assertTrue(marketPageFactory.getResults().stream().anyMatch(s -> beforeItem.contains(s.getText())),"Наименование товара не соответвует сохраненному значению");

    }

}
