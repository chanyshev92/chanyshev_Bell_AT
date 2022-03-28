package ru.yandex.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SubCategoryPage extends BasePage{

    /**
     * Серый элемент загрузки
     */
    private final String loadingDiv = "//*[@data-zone-name='SearchResultsPaged']//div[@role='main']/div[not(@data-apiary-widget-name)]";

    /**
     * Получаем список результатов на странице
     * @return Список ссыллок на результаты
     */
    @Step("Получаем список результатов на странице")
    public List<SelenideElement> getResults(){
        return $$x("//article[@data-zone-name='snippet-card']//h3/a");
    }

    /**
     * Фильтрация по имени и значению
     * @param filterName Имя фильтра
     * @param filterValue Значение фильтра
     * @return страница с установленным(снятым) чекбоксом
     */
    @Step("Фильтруем по {filterName} {filterValue}")
    public SubCategoryPage filterChoice(String filterName, String filterValue){

        $x("//legend[text()='"+filterName+"']/..//button").click();

        $x("//*[@name='Поле поиска']").sendKeys(filterValue);

        $x("//input[@name='" + filterName + " " + filterValue + "']/../div").shouldBe(exist).click();

        $x("//legend[text()='"+filterName+"']/..//button").click();

        $x(loadingDiv).should(Condition.disappear);

        return this;
    }

    /**
     * Проверяем, что у всех один производитель
     * @param manufacturer Производитель
     * @return Последняя страница с проверенным производителем
     */
    @Step("Проверяем, что у всех один {manufacturer}")
    public SubCategoryPage checkManufacturerByNextPage(String manufacturer){

        SelenideElement nextPageButton = $x("//a[@aria-label='Следующая страница']");

        while (nextPageButton.isDisplayed()){

            List<String> titles = $$x("//article[@data-zone-name='snippet-card']//h3/a").stream().map(SelenideElement::getText).collect(Collectors.toList());

            Assertions.assertTrue(titles.stream().allMatch(x->x.contains(manufacturer)||x.contains(manufacturer.toUpperCase())), "Несоответствие товара производителю");

            nextPageButton.shouldBe(exist).click();
            $x(loadingDiv).should(Condition.disappear);
        }
        return this;
    }

    /**
     * Проверяем, что у всех один производитель
     * @param manufacturer Производитель
     * @return Страница с полным списком моделей данного производителя
     */
    @Step("Проверяем, что у всех один {manufacturer}")
    public SubCategoryPage checkManufacturerByShowMore(String manufacturer){

        SelenideElement showMoreButton = $x("//div[@data-apiary-widget-name='@MarketNode/SearchPager']/div/button");
        while (showMoreButton.isDisplayed()){
            actions().moveToElement(showMoreButton).click(showMoreButton).perform();
            showMoreButton.shouldNotBe(disabled);
        }

        List<String> titles = getResults().stream().map(SelenideElement::getText).map(String::toUpperCase).collect(Collectors.toList());
        Assertions.assertTrue(titles.stream().allMatch(x->x.contains(manufacturer.toUpperCase())), "Несоответствие товара производителю");

        return this;

    }

    /**
     * Изменяем количество показываемых результатов если не равно желаемому
     * @param showCount Желаемое количество
     * @return Страница с желаемым колиством показываемых результатов
     */
    @Step("Изменить кол-во показываемых результатов на {showCount}")
    public SubCategoryPage changeShowCountIfNotEquals(int showCount){

        SelenideElement popupButton = $x("//button[@aria-haspopup='true']");
        if(popupButton.exists()) {
            if (!Objects.equals(popupButton.getText(), String.format("Показывать по %d", showCount))) {


                popupButton.scrollTo();

                popupButton.shouldBe(visible).click();
                $(By.xpath("//button[text()='Показывать по " + showCount + "']")).click();
                $x(loadingDiv).should(Condition.disappear);
                Assertions.assertEquals(getResults().size(), showCount, "Количество элементов на странице не совпадает");
            }
        }
        return this;
    }

}
