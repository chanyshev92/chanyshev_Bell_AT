package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class YandexMarketPageFactory {

    private final WebDriver chromeDriver;

    private final WebDriverWait wait;

    @FindBy(how = How.ID,id ="catalogPopupButton")
    private WebElement catalogPopupButton;

    @FindBy(how = How.XPATH, xpath ="//input[@name='text']")
    private WebElement searchFieldMarket;

    @FindBy(how = How.XPATH,xpath = "//button[@type='submit']")
    private WebElement searchButtonMarket;

    @FindBy(how = How.XPATH,xpath ="//input[@name='Цена от']")
    private WebElement searchPriceFrom;

    @FindBy(how = How.XPATH,xpath ="//input[@name='Цена до']")
    WebElement searchPriceTo;

    @FindBy(how = How.XPATH,xpath = "//button[@aria-haspopup='true']")
    WebElement showButton;

    public YandexMarketPageFactory(WebDriver chromeDriver) {

        this.chromeDriver = chromeDriver;
        this.wait=new WebDriverWait(chromeDriver, Duration.ofSeconds(30));

    }

    public void checkboxOfManufacturer(String legendName,String checkBoxName){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']//h3/a")));//Located(By.xpath("//article[@data-zone-name='snippet-card']//h3/a")));
        chromeDriver.findElement(By.xpath("//legend[text()='" + legendName + "']/..//li//input[@name='" + legendName + " " + checkBoxName + "']/../div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']//h3/a")));

    }
    public void changeShowCount(String showCountString){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-tid='67d9be0a']")));

        if (!Objects.equals(showButton.getText(), showCountString)){
            wait.until(ExpectedConditions.elementToBeClickable(showButton));
            showButton.click();
            chromeDriver.findElement(By.xpath("//button[text()='"+showCountString+"']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-tid='67d9be0a']")));
        }
    }

    public  void setPriceFrom(String priceFrom){

        wait.until(ExpectedConditions.elementToBeClickable(searchPriceFrom));
        searchPriceFrom.click();
        searchPriceFrom.sendKeys(priceFrom);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']")));
    }
    public  void setPriceTo(String priceTo){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']")));
        searchPriceTo.click();
        searchPriceTo.sendKeys(priceTo);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']")));
    }

    public List<WebElement> getResults() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']")));
        return chromeDriver.findElements(By.xpath("//article[@data-zone-name='snippet-card']//h3/a"));
    }


    public WebElement findSubCategory(String categoryName, String subcategoryName){

        catalogPopupButton.click();
        chromeDriver.findElement(By.xpath("//*[@alt='"+categoryName+"']/../.."));

        return chromeDriver.findElement(By.xpath("//a[text()='"+subcategoryName+"']"));
    }
    public void findItem(String ItemString){
        searchFieldMarket.click();
        searchFieldMarket.sendKeys(ItemString);
        searchButtonMarket.click();
    }

}
