package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PageFactoryYandexWiki {

    private WebDriver chromeDriver;

    @FindBy(how = How.XPATH, xpath = "//input[@name='text']")
    WebElement searchField;

    @FindBy(how = How.XPATH,xpath = "//button[@type='submit']")
    WebElement searchButton;

    @FindBy(how = How.XPATH, xpath = "//ul[@id='search-result']//li//a")
    List<WebElement> results;

    @FindBy(how = How.XPATH, xpath = "//tr[contains(th,'Фамилия')]/../tr")
    List<WebElement> tableRowsByLastName;

    public PageFactoryYandexWiki(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public List<WebElement> getResults() {
        return results;
    }

    public List<WebElement> getTableRowsByLastName() {
        return tableRowsByLastName;
    }

    public void find(String keysFind){

        searchField.click();
        searchField.sendKeys(keysFind);
        searchButton.click();
    }

}
