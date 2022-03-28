package ru.yandex.stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import ru.yandex.steps.Steps;

public class Hooks extends Steps {
    @Before
    public void before(){
        initBrowser();
    }
    @After
    public void after(){
        closeBrowser();
    }
}
