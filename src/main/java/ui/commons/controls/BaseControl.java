package ui.commons.controls;

import config.Driver;
import config.DriverWait;
import config.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.awt.*;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class BaseControl {

    private Element element;
    private int index;


    protected Logger logger = Logger.getLogger(getClass().getName());

    public BaseControl(Element element) {
        this.element = element;
    }

    public BaseControl(By by) {
        element = new Element(by);
    }

    public Element getElement() {
        return element;
    }

    public boolean isEnabled() {
        return element.isEnabled();
    }

    public boolean isDisabled() {
        return element.isDisabled();
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }


    public By getLocator(){
        return element.get_by();
    }

    protected void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
        }
    }

    public void changeCheckBoxState(Boolean state) {
        if (getState() == state) {
            logger.info("Checkbox was not clicked. Current state is equal modified state");
            return;
        }
        getElement().click();
        sleep(1);
        logger.info("Checkbox state was changed to " + state);
    }


    public boolean getState() {
        return getElement().isSelected();
    }

    private Element getCheckBox(String text) {
        return getElement().findElement(By.xpath(".//tr[child::td/div[contains(@class,'-cellInner') and text()='" + text + "']]"));
    }

    public String getRadioBtnText() {
        return getElement().findElement(By.xpath(getElement().get_by()+"/following-sibling::label")).getText();
    }

}
