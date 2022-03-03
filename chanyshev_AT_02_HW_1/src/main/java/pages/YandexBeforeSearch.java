package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YandexBeforeSearch {
    protected WebDriver chromeDriver;
    protected WebElement searchField;
    protected WebElement searchButton;

    public YandexBeforeSearch(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.searchField =chromeDriver.findElement(By.xpath("//input[@name='text']"));
        this.searchButton=chromeDriver.findElement(By.xpath("//button[@type='submit']"));
    }
    public void find(String keysFind){
        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }
}
