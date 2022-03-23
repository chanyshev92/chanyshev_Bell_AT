package pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 *Page factory для стартовой страницы яндекса
 */
@Data
public class YandexStartPageFactory {
    /**
     * Экземпляр драйвера
     */
    private WebDriver chromeDriver;

    /**
     * Поле для поиска
     */
    @FindBy(how = How.XPATH,xpath = "//input[@name='text']")
    private WebElement searchField;
    /**
     * Кнопка поиск
     */
    @FindBy(how = How.XPATH,xpath = "//button[@type='submit']")
    private WebElement searchButton;
    /**
     * Элемент Яндекс.Маркета
     */
    @FindBy(how = How.XPATH,xpath = "//a[@data-id='market']")
    private WebElement marketElement;

    /**
     * Конструктор
     * @param chromeDriver Драйвер из WebDriverProvider
     */
    public YandexStartPageFactory(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
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
