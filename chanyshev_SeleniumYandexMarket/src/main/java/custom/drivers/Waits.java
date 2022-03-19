package custom.drivers;

import custom.properties.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    public static WebDriverWait wait;

    public static void initWait() {
        wait = new WebDriverWait(Manager.getCurrentDriver(), Duration.ofSeconds(TestData.driverProps.defaultTimeout()));//WebDriverWait(Manager.getCurrentDriver(), TestData.driverProps.defaultTimeout());
    }


    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitUntilElementNotExist(String xpath) {
        int timer = 0;
        for (; timer == TestData.driverProps.defaultTimeout(); ++timer) {
            if (Manager.getCurrentDriver().findElements(By.xpath(xpath)).size() == 0)
                break;
            sleep(1);
        }
        if (timer == TestData.driverProps.defaultTimeout()) {
            throw new TimeoutException("Элемент с селектором " + xpath + " не исчез за " + TestData.driverProps.defaultTimeout() + " секунд");
        }
    }

    public static void waitUntilElementClickable(String xpath) {
        Waits.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public static void waitUntilElementPresents(String xpath) {
        Waits.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public static void waitUntilAttributeWillBe(WebElement element, String attribute, String value) {
        Waits.wait.until((ExpectedCondition<Boolean>) driver -> element.getAttribute(attribute).contains(value));
    }

    public static void waitUntilElementTextContainsByLocator(By locator, String text) {
        new WebDriverWait(Manager.getCurrentDriver(), TestData.driverProps.defaultTimeout())
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static void waitUntilElementTextContains(WebElement element, String text) {
        new WebDriverWait(Manager.getCurrentDriver(), TestData.driverProps.defaultTimeout() / 6)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitUntilElementTextContains(WebElement element, String text, int timeout) {
        new WebDriverWait(Manager.getCurrentDriver(), timeout)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

}
