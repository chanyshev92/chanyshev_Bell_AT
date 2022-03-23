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

/**
 * Page Factory для страницы Яндекс.Маркет
 */
public class YandexMarketPageFactory {

    /**
     * Экземпляр драйвера
     */
    private final WebDriver chromeDriver;

    /**
     * Для неявного ожидания
     */
    private final WebDriverWait wait;

    /**
     * Кнопка каталога
     */
    @FindBy(how = How.ID,id ="catalogPopupButton")
    private WebElement catalogPopupButton;

    /**
     * Поле поиска Яндекс.Маркета
     */
    @FindBy(how = How.XPATH, xpath ="//input[@name='text']")
    private WebElement searchFieldMarket;

    /**
     *Кнопка поиска Яндекс.Маркета
     */
    @FindBy(how = How.XPATH,xpath = "//button[@type='submit']")
    private WebElement searchButtonMarket;
    /**
     * Поле поиска "Цена от"
     */
    @FindBy(how = How.XPATH,xpath ="//input[@name='Цена от']")
    private WebElement searchPriceFrom;

    /**
     * Поле поиска "Цена до"
     */
    @FindBy(how = How.XPATH,xpath ="//input[@name='Цена до']")
    WebElement searchPriceTo;
    /**
     * Кпопка "Показывать по"
     */
    @FindBy(how = How.XPATH,xpath = "//button[@aria-haspopup='true']")
    WebElement showButton;

    /**
     * Конструктор Page Factory Y.Market
     * @param chromeDriver Драйвер из WebDriverProvider
     */
    public YandexMarketPageFactory(WebDriver chromeDriver) {

        this.chromeDriver = chromeDriver;
        this.wait=new WebDriverWait(chromeDriver, Duration.ofSeconds(30));

    }

    /**
     * Функция выбора чекбоксов
     * @param legendName Название блока
     * @param checkBoxName Название конкретного чекбокса
     */
    public void checkboxChoice(String legendName, String checkBoxName){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']//h3/a")));//Located(By.xpath("//article[@data-zone-name='snippet-card']//h3/a")));
        chromeDriver.findElement(By.xpath("//legend[text()='" + legendName + "']/..//li//input[@name='" + legendName + " " + checkBoxName + "']/../div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']//h3/a")));

    }

    /**
     * Функция смены количества показываемых элементов
     * @param showCountString Строка вида "Показывать по+кол-во элементов"
     */
    public void changeShowCountIfNotEquals(String showCountString){

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-tid='67d9be0a']")));

        if (!Objects.equals(showButton.getText(), showCountString)){
            wait.until(ExpectedConditions.elementToBeClickable(showButton));
            showButton.click();
            chromeDriver.findElement(By.xpath("//button[text()='"+showCountString+"']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-tid='67d9be0a']")));
        }
    }

    /**
     * Функция установки "Цены от"
     * @param priceFrom Искомое значение
     */
    public  void setPriceFrom(String priceFrom){

        wait.until(ExpectedConditions.elementToBeClickable(searchPriceFrom));
        searchPriceFrom.click();
        searchPriceFrom.sendKeys(priceFrom);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']")));
    }

    /**
     * Функция установки "Цены до"
     * @param priceTo Искомое значение
     */
    public  void setPriceTo(String priceTo){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']")));
        searchPriceTo.click();
        searchPriceTo.sendKeys(priceTo);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']")));
    }

    /**
     * Функция получения результатов
     * @return Список результатов
     */
    public List<WebElement> getResults() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-zone-name='snippet-card']")));
        return chromeDriver.findElements(By.xpath("//article[@data-zone-name='snippet-card']//h3/a"));
    }

    /**
     * Функция поиска подкатегории
     * @param categoryName Имя категории
     * @param subcategoryName Имя подкатегории
     * @return Страница подкатегории
     */
    public WebElement findSubCategory(String categoryName, String subcategoryName){

        catalogPopupButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@alt='"+categoryName+"']/../..")));
        chromeDriver.findElement(By.xpath("//*[@alt='"+categoryName+"']/../.."));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+subcategoryName+"']")));
        return chromeDriver.findElement(By.xpath("//a[text()='"+subcategoryName+"']"));
    }

    /**
     * Поиск на странице Яндекс.Маркета
     * @param ItemString Искомое значение
     */
    public void findItem(String ItemString){
        searchFieldMarket.click();
        searchFieldMarket.sendKeys(ItemString);
        searchButtonMarket.click();
    }

}
