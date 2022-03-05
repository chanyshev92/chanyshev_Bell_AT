package ru.yandex;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import pages.PageFactoryYandexWiki;
import pages.SearchTableOnPage;
import pages.SearchEnginePageAfterSearch;
import pages.SearchEnginePageBeforeSearch;

/**
 * Основной класс с тестами
 */
public class Tests extends WebDriverProvider {

    /**
     * @param searchEnginePage Параметр страницы поиска
     * @param keysFind         Ключевое слово для поиска
     * @param targetHeader     Заголовок искомого результата поиска
     * @param keyRow           Ключевая строка, которую ищем в "шапке" таблицы
     * @param firstRowKey      Ключевая фраза, которую ищем в первой строке(строка, следующая за "шапкой")
     * @param lastRowKey       Ключевая фраза, которую ищем в первой строке(строка, следующая за "шапкой")
     */
    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска первой и последней строк таблицы c помощью PO по запросу с заданным заголовком")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"https://yandex.ru,таблица,Таблица — Википедия,Фамилия,Сергей Владимирович,Сергей Адамович"})
    public void testPageObject(String searchEnginePage, String keysFind, String targetHeader, String keyRow, String firstRowKey, String lastRowKey) {

        chromeDriver.get(searchEnginePage);

        SearchEnginePageBeforeSearch yandexBeforeSearch = new SearchEnginePageBeforeSearch(chromeDriver);
        yandexBeforeSearch.find(keysFind);
        SearchEnginePageAfterSearch yandexAfterSearch = new SearchEnginePageAfterSearch(chromeDriver);

        Assertions.assertTrue(yandexAfterSearch.getResults()
                .stream()
                .anyMatch(s -> s.getText().equals(targetHeader)), String.format("%s не найдено", targetHeader));

        String targetLink = yandexAfterSearch.getResults()
                .stream()
                .filter(s -> s.getText().equals(targetHeader))
                .map(s -> s.getAttribute("href"))
                .findFirst()
                .orElse(searchEnginePage);
        chromeDriver.switchTo().newWindow(WindowType.TAB);
        chromeDriver.get(targetLink);

        SearchTableOnPage wikiTableSearch = new SearchTableOnPage(chromeDriver);

        Assertions.assertTrue(wikiTableSearch.getTableRows(keyRow)
                .stream()
                .skip(1).findFirst().get()
                .getText().contains(firstRowKey), String.format("%s не найдено", firstRowKey));

        Assertions.assertTrue(wikiTableSearch.getTableRows(keyRow)
                .stream()
                .skip(wikiTableSearch.getTableRows(keyRow).size() - 1)
                .findFirst().get()
                .getText().contains(lastRowKey), String.format("%s не найдено", lastRowKey));
    }

    /**
     * @param searchEnginePage Параметр страницы поиска
     * @param keysFind         Ключевое слово для поиска
     * @param targetHeader     Заголовок искомого результата поиска
     * @param firstRowKey      Ключевая фраза, которую ищем в первой строке(строка, следующая за "шапкой")
     * @param lastRowKey       Ключевая фраза, которую ищем в первой строке(строка, следующая за "шапкой")
     */
    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска первой и последней строк таблицы c помощью PF по запросу с заданным заголовком")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"https://yandex.ru,таблица,Таблица — Википедия,Сергей Владимирович,Сергей Адамович"})
    public void testPageFactory(String searchEnginePage, String keysFind, String targetHeader, String firstRowKey, String lastRowKey) {

        chromeDriver.get(searchEnginePage);


        PageFactoryYandexWiki pageFactoryYandexWiki = PageFactory.initElements(chromeDriver, PageFactoryYandexWiki.class);
        pageFactoryYandexWiki.find(keysFind);

        Assertions.assertTrue(pageFactoryYandexWiki.getResults()
                .stream()
                .anyMatch(s -> s.getText().equals(targetHeader)), String.format("%s не найдено", targetHeader));

        String targetLink = pageFactoryYandexWiki.getResults()
                .stream()
                .filter(s -> s.getText().equals(targetHeader))
                .map(s -> s.getAttribute("href"))
                .findFirst()
                .orElse(searchEnginePage);
        chromeDriver.switchTo().newWindow(WindowType.TAB);
        chromeDriver.get(targetLink);

        Assertions.assertTrue(pageFactoryYandexWiki.getTableRowsByLastName()
                .stream()
                .skip(1).findFirst().get()
                .getText().contains(firstRowKey), String.format("%s не найдено", firstRowKey));

        Assertions.assertTrue(pageFactoryYandexWiki.getTableRowsByLastName()
                .stream()
                .skip(pageFactoryYandexWiki.getTableRowsByLastName().size() - 1)
                .findFirst().get()
                .getText().contains(lastRowKey), String.format("%s не найдено", lastRowKey));
    }
}
