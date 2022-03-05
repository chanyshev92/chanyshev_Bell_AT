package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Поиск таблицы на странице
 */
public class SearchTableOnPage {

    protected WebDriver chromeDriver;

    public SearchTableOnPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /**
     * Получение списка строк таблицы(вместе с "шапкой")
     *
     * @param headerRowKey ключевая фраза для поиска в "шапке"
     * @return список строк таблицы
     */
    public List<WebElement> getTableRows(String headerRowKey) {
        return chromeDriver.findElements(By.xpath("//tr[contains(th,'" + headerRowKey + "')]/../tr"));
    }
}
