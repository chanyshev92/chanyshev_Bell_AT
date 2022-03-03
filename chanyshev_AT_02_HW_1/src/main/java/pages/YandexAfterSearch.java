package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class YandexAfterSearch extends YandexBeforeSearch{

    WebDriverWait wait = new WebDriverWait(chromeDriver,120);

    public YandexAfterSearch(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public List<WebElement> getResults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='search-result']")));
        return chromeDriver.findElements(By.xpath("//ul[@id='search-result']//li//a"));
    }

}
