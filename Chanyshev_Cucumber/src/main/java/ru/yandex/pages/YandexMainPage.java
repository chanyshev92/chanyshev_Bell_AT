package ru.yandex.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class YandexMainPage extends BasePage {

    /**
     * Переход на страницу Яндекс. Элемента
     * @param dataId Id искомого элемента
     * @param typeNextPage Имя типа возвращаемой страницы
     * @param <T> Дженерик типа возвращаемого значения
     * @return Страница нового типа
     */
    @Step("Переходим по имени {dataId}")
    public <T extends BasePage> T goYandexElement(String dataId, Class<T> typeNextPage){
        $x("//a[@data-id='"+dataId+"']").click();
        switchTo().window(1);
        return typeNextPage.cast(page(typeNextPage));
    }
}
