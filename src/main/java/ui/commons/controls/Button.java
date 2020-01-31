package ui.commons.controls;

import config.Driver;
import config.DriverWait;
import config.Element;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Button extends BaseControl {

    public Button(By by) {
        super(by);
    }

    public Button(Element element){
        super(element);
    }

    public void pressBtn(){
        String locator = "";
        try{
            locator = getElement().get_by().toString();
        } catch (Exception e) {}

        getElement().click();
        logger.info("Button with locator: '" + locator + "' was clicked");
    }
    public boolean isBtnNotClickable() {
        String cssvalue = getElement().getCssValue("cursor");
        if (cssvalue.equals("default")) {
            logger.info("Button with id: '" + getElement().getAttribute("id") + " is not clickable");
            return true;
        }
        else
            logger.info("Button with id: '" + getElement().getAttribute("id") + " is clickable");
        return false;
    }

    public boolean isBtnDisabled() {
        if(getElement().getAttribute("class").toLowerCase().contains("disabled")){
            return true;
        }
        return new Element(new ByChained(this.getLocator(), By.xpath(".//ancestor::td/div[contains(@class, 'irDisabled')]"))).isDisplayed();
    }
}

