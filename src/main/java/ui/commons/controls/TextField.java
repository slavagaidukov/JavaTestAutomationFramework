package ui.commons.controls;

import config.Driver;
import config.DriverWait;
import config.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class TextField extends BaseControl {

    public TextField(By by) {
        super(by);
    }

    public TextField(Element element) {
        super(element);
    }

    private By itemListXpath = By.xpath("//div[contains(@class, 'ListView')]/div[contains(@class, '-item')]");

    public void setValueToTextField(String text) {
        clearField();
        getElement().sendKeys(text);
        sleep(1);
        getElement().sendKeys(Keys.ENTER);
        logger.info(text + " was entered");
    }

    public void clearField() {
        getElement().clear();
    }
}

