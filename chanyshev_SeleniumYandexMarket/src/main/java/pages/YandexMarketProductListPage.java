package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class YandexMarketProductListPage extends YandexMarketPage {

    WebDriverWait wait = new WebDriverWait(chromeDriver,Duration.ofSeconds(10));

   //By(how = How.XPATH,xpath ="//input[@name='Цена от']")
    WebElement searchPriceFrom;

    //@FindBy(how = How.XPATH,xpath ="//input[@name='Цена до']")
    WebElement searchPriceTo;

    public YandexMarketProductListPage(WebDriver chromeDriver) {
        super(chromeDriver);
        this.searchPriceFrom=chromeDriver.findElement(By.xpath("//input[@name='Цена от']"));
        this.searchPriceTo=chromeDriver.findElement(By.xpath("//input[@name='Цена до']"));
    }

    public void checkboxOfManufacturer(String legendName,String checkBoxName){

        chromeDriver.findElement(By.xpath("//legend[text()='" + legendName + "']/..//li//input[@name='" + legendName + " " + checkBoxName + "']/../div")).click();

    }
    public void changeShowCount(String showCountString){

        //String text = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-apiary-widget-name='@MarketNode/SearchPager']//button[@aria-haspopup='true']"))).findElement(By.xpath("/span/span")).getText();
        WebElement element = chromeDriver.findElement(By.xpath("//*[@data-apiary-widget-name='@MarketNode/SearchPager']//button[@aria-haspopup='true']"));
        final String text=chromeDriver.findElement(By.xpath("//button[@aria-haspopup='true']/span/span")).getText();
        if (!Objects.equals(text, showCountString)){

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//article[@data-zone-name='snippet-card']")));
            element.click();

            chromeDriver.findElement(By.xpath("//button[text()='"+showCountString+"']")).click();
        }
    }

    public  void setPriceFrom(String priceFrom){

        searchPriceFrom.click();
        searchPriceFrom.sendKeys(priceFrom);
    }
    public  void setPriceTo(String priceFrom){

        searchPriceTo.click();
        searchPriceTo.sendKeys(priceFrom);
    }

    public List<WebElement> getResults() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//article[@data-zone-name='snippet-card']")));
        return chromeDriver.findElements(By.xpath("//article[@data-zone-name='snippet-card']//h3/a"));
    }
    public void findFirstItem(String ItemString){
        searchField.click();
        searchField.sendKeys(ItemString);
        searchButton.click();
    }
}
