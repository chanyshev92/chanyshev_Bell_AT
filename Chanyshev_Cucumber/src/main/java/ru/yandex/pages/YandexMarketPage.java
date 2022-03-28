package ru.yandex.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class YandexMarketPage extends BasePage{

    /**
     * Переход на страницу подкатегории
     * @param categoryName Имя категории
     * @param subCategoryName Имя подкатегории
     * @return Страница подкатегории
     */
    @Step("Ищем категорию{categoryName} и подкатегорию {subCategoryName} и переходим на ее страницу")
    public SubCategoryPage goToSubCategoryPage(String categoryName, String subCategoryName){
        $(By.xpath("//button[@id='catalogPopupButton']")).click();

        $$(By.xpath("li[@data-zone-name='category-link']"))
                .findBy(text(categoryName));

        $$(By.xpath("//ul[@data-autotest-id='subItems']/li"))
                .findBy(text(subCategoryName))
                .click();
        //$x("//*[@data-zone-name='list-page']//aside").should(appear);
        $x("//*[@data-zone-name='list-page']").shouldBe(visible);

     return page(SubCategoryPage.class);
    }



}
