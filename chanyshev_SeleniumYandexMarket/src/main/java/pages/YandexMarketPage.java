package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YandexMarketPage {

    protected WebDriver chromeDriver;

    protected WebElement catalogPopupButton;

    protected WebElement searchField;

    protected WebElement searchButton;

    public YandexMarketPage(WebDriver chromeDriver) {

        this.chromeDriver = chromeDriver;
        this.catalogPopupButton=chromeDriver.findElement(By.id("catalogPopupButton"));
        this.searchField=chromeDriver.findElement(By.xpath("//input[@name='text']"));
        this.searchButton=chromeDriver.findElement(By.xpath("//button[@type='submit']"));

    }

    public WebElement findSubCategory(String categoryName, String subcategoryName){

        catalogPopupButton.click();
        chromeDriver.findElement(By.xpath("//*[@alt='"+categoryName+"']/../.."));

        return chromeDriver.findElement(By.xpath("//a[text()='"+subcategoryName+"']"));
    }
}
