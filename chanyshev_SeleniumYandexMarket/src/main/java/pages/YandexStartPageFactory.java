package pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Data
public class YandexStartPageFactory {
    protected WebDriver chromeDriver;

    @FindBy(how = How.XPATH,xpath = "//input[@name='text']")
    protected WebElement searchField;
    @FindBy(how = How.XPATH,xpath = "//button[@type='submit']")
    protected WebElement searchButton;
    @FindBy(how = How.XPATH,xpath = "//a[@data-id='market']")
    protected WebElement marketElement;

    public YandexStartPageFactory(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchField = chromeDriver.findElement(By.xpath("//input[@name='text']"));
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
