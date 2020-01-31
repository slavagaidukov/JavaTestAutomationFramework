package ui.commons.pages;

import config.Driver;
import config.DriverWait;
import org.openqa.selenium.By;
import ui.commons.controls.Button;

import java.util.logging.Logger;

import static config.DriverWait.waitElementPresent;
import static config.DriverWait.waitForInvisibilityElement;

public class BasePage {
    private String url;
    public Logger logger = Logger.getLogger(getClass().getSimpleName().getClass().getName());
    public Button saveBtn = new Button(By.id("saveAccountsBtn"));
    public Button mainPageBtn = new Button(By.xpath("//div[@class=' lyteItem']"));

    protected BasePage(String url) {
        this.url = url;
    }
    public void open() {
        Driver.getDriver().navigate().to(url);
    }

    public void clickOnSave() {
        saveBtn.pressBtn();
        waitForInvisibilityElement(saveBtn.getLocator());
        logger.info("Save button was clicked");
    }
    public void refreshPage() {
        Driver.getDriver().navigate().refresh();
        waitElementPresent(mainPageBtn.getLocator());
    }
}
