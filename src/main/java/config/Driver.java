package config;

//import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Driver implements WebDriver, JavascriptExecutor, HasInputDevices {

    public RemoteWebDriver remoteWebDriver;
    private static ThreadLocal<Driver> driverStorage = new ThreadLocal<Driver>();

    private Driver(RemoteWebDriver remoteWebDriver) {
        this.remoteWebDriver = remoteWebDriver;
//        this.remoteWebDriver = getRemoteDriver();
        driverStorage.set(this);
    }

    public static Driver getDriver() {
        Driver driver;
        if ((driver = driverStorage.get()) != null) {
            return driver;
        }
        ChromeOptions options;
        options = new ChromeOptions();
        options.addArguments("enable-automation");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        driver = getLocalDriver(options);
        return driver;
    }


    public static Driver initializeDriver() throws Exception {
        return getDriver();
    }

    private static Driver getLocalDriver(ChromeOptions options) {
        String root = new File("").getAbsolutePath();
        DriverService driverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(root + "\\src\\main\\resources\\chromedriver.exe").getAbsoluteFile())
                .usingAnyFreePort().build();
        return new Driver(new ChromeDriver((ChromeDriverService) driverService,
                (ChromeOptions) options));
    }

    @Override
    public Object executeScript(String s, Object... objects) {
        return remoteWebDriver.executeScript(s, objects);
    }

    @Override
    public Object executeAsyncScript(String s, Object... objects) {
        return remoteWebDriver.executeAsyncScript(s, objects);
    }

    @Override
    public void get(String s) {
        remoteWebDriver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return remoteWebDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return remoteWebDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return remoteWebDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return remoteWebDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return remoteWebDriver.getPageSource();
    }

    @Override
    public void close() {
        remoteWebDriver.close();
    }

    @Override
    public void quit() {
        driverStorage.remove();
        remoteWebDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return remoteWebDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return remoteWebDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return remoteWebDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return remoteWebDriver.navigate();
    }

    @Override
    public Options manage() {
        return remoteWebDriver.manage();
    }

    public void getScreenShot(String testName, String screenshotsResultsPath, String currentDate) throws IOException {
        File scrFile = ((TakesScreenshot) remoteWebDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotsResultsPath + File.separator + "screenshot_" + testName + "_" + currentDate
                + ".png"));
    }

    public File getScreenShotWithoutSaving() throws IOException {
        File scrFile = ((TakesScreenshot) remoteWebDriver).getScreenshotAs(OutputType.FILE);
        return scrFile;
    }

    @Override
    public Keyboard getKeyboard() {
        return remoteWebDriver.getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return remoteWebDriver.getMouse();
    }

    public void getScreenShot(String screenshotsResultsPath) throws IOException {
        File scrFile = ((TakesScreenshot) remoteWebDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshotsResultsPath));
    }
}

