package ru.yandex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import pages.YandexMarketPageFactory;
import pages.YandexStartPageFactory;

import java.util.List;

/**
 * Основной класс с тестами
 */
public class Test extends WebDriverProvider {

    /**
     * Тест на основе паттерна Page Factory
     */
    @DisplayName("Поиск ноутбуков HP и Lenovo на Я.Маркете в диапазоне 10-900к")
    @ParameterizedTest(name = "{displayName}")
    @CsvSource({"Компьютеры,Ноутбуки,10000,90000,Производитель,HP,Lenovo,Показывать по 12"})
    public void testPF(String categoryName,String subCategoryName,String priceFrom, String priceTo,String legendName, String checkboxName1,String checkboxName2,String showCountString ) {

        chromeDriver.get("https://yandex.ru");
        YandexStartPageFactory yandexStartPage = PageFactory.initElements(chromeDriver,YandexStartPageFactory.class);

        String hrefMarket = yandexStartPage.getMarketElement().getAttribute("href");

        chromeDriver.switchTo().newWindow(WindowType.TAB);
        chromeDriver.get(hrefMarket);

        YandexMarketPageFactory marketPageFactory=PageFactory.initElements(chromeDriver,YandexMarketPageFactory.class);
        String hrefSubcategory = marketPageFactory.findSubCategory(categoryName, subCategoryName).getAttribute("href");
        chromeDriver.get(hrefSubcategory);
        marketPageFactory.setPriceFrom(priceFrom);
        marketPageFactory.setPriceTo(priceTo);
        marketPageFactory.checkboxChoice(legendName,checkboxName1);
        marketPageFactory.checkboxChoice(legendName,checkboxName2);
        marketPageFactory.changeShowCountIfNotEquals(showCountString);
        List<WebElement> listPageResults = marketPageFactory.getResults();
        Assertions.assertEquals(listPageResults.size(), 12, "Результатов поиска не 12");
        String beforeItem = listPageResults.get(0).getText();
        marketPageFactory.findItem(beforeItem);
        Assertions.assertTrue(marketPageFactory.getResults().stream().anyMatch(s -> beforeItem.contains(s.getText())),"Наименование товара не соответвует сохраненному значению");

    }

}
