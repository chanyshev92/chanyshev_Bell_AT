package custom.utils;

import custom.drivers.Manager;
import org.openqa.selenium.By;

import java.util.function.Consumer;

public class Actions {
    public static org.openqa.selenium.interactions.Actions action;

    public static void initActions() {
        action = new org.openqa.selenium.interactions.Actions(Manager.getCurrentDriver());
    }

    public static Consumer<By> hover = (By by) -> {
        action.moveToElement(Manager.getCurrentDriver().findElement(by))
                .perform();
    };
}
