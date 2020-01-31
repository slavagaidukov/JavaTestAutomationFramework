package ui.commons.pages;

import config.DriverWait;
import org.openqa.selenium.By;
import ui.commons.controls.Button;

import static config.DriverWait.waitForInvisibilityElement;

public class MainPage extends BasePage{
    public static final String PAGE_URL = "https://www.zoho.com/";
    public Button loginBtn = new Button(By.xpath("//a[@class='zh-login']"));
    public MainPage() {
        super(PAGE_URL);
    }

    public void goToLoginTab() {
        loginBtn.pressBtn();
        waitForInvisibilityElement(loginBtn.getLocator());
        logger.info("Login Page was opened");
    }
}
