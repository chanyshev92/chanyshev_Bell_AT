package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WikiTableSearch {

    protected WebDriver chromeDriver;

    public WikiTableSearch(WebDriver chromeDriver) {
        this.chromeDriver=chromeDriver;
    }

    public List<WebElement> getTableRows(String rowKey) {
        return chromeDriver.findElements(By.xpath("//tr[contains(th,'"+rowKey+"')]/../tr"));
    }
}
