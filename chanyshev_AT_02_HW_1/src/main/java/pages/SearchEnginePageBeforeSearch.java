package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Поисковая страница до поиска
 */
public class SearchEnginePageBeforeSearch {
    protected WebDriver chromeDriver;
    protected WebElement searchField;
    protected WebElement searchButton;

    public SearchEnginePageBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchField = chromeDriver.findElement(By.xpath("//input[@name='text']"));
        this.searchButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
    }

    /**
     * Функция поиска
     *
     * @param keysFind Ключевая строка
     */
    public void find(String keysFind) {
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }
}
