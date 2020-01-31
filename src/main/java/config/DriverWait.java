package config;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverWait {

    private final static long POLLING_TIME = 100;
    private final static long ELEMENT_WAIT_TIMEOUT = 45;
    private final static long COMPLEX_RUN_MTM_ELEMENT__TIMEOUT = 400;
    private final static long PAGE_WAIT_TIMEOUT = 80;

    public static Driver driver;

    public static void waitElementPresent(By by) {
        FluentWait<Driver> wait = new FluentWait<Driver>(Driver.getDriver()).withTimeout(ELEMENT_WAIT_TIMEOUT, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .pollingEvery(2, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForInvisibilityElement(By by) {
        //resetImplicitWait();
        Wait<WebDriver> wait = new WebDriverWait(Driver.getDriver(), 90);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        //restoreImplicitWait();
    }

}

