package ru.yandex.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.yandex.pages.SubCategoryPage;
import ru.yandex.pages.YandexMainPage;
import ru.yandex.pages.YandexMarketPage;
import ru.yandex.steps.Steps;

public class StepDefs extends Steps {



    @Given("Открыть браузер на странице yandex.ru")
    public void openYandexRu(){

         openYandexMainPage();
    }
    @When("Перейти к странице market.yandex.ru")
    public void openYandexMarket(){

        openYandexMarketPage();

    }
    @When("Перейти на страницу Смартфоны")
    public void goToSubCategoryPage(){

       goSmartPhonePage();
    }

    @Then("Выбрать производителя {string}")
    public void choiceMaker(String manufacturer){

        filterChoice(manufacturer);
    }
    @Then("Проверить что все результаты содержат {string}")
    public void checkMaker(String manufacturer){

        checkManufacturerByNextPage(manufacturer);
    }

}
