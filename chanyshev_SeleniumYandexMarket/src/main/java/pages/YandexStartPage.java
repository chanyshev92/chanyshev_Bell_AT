package pages;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
@Data
public class YandexStartPage {
    protected WebDriver chromeDriver;
    protected WebElement searchField;
    protected WebElement searchButton;
    protected WebElement marketElement;

    public YandexStartPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchField = chromeDriver.findElement(By.xpath("//input[@name='text']"));
        this.searchButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
        this.searchButton = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
        this.marketElement = chromeDriver.findElement(By.xpath("//a[@data-id='market']"));
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
