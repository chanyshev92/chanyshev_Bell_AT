package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Поисковая страница после Поиска
 */
public class SearchEnginePageAfterSearch extends SearchEnginePageBeforeSearch {

    WebDriverWait wait = new WebDriverWait(chromeDriver, 120);

    public SearchEnginePageAfterSearch(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    /**
     * Функция получения списка результатов поиска
     *
     * @return список ссылок с результатами
     */
    public List<WebElement> getResults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='search-result']")));
        return chromeDriver.findElements(By.xpath("//ul[@id='search-result']//li//a"));
    }

}
