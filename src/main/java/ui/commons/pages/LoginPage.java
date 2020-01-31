package ui.commons.pages;

import config.DriverWait;
import entity.User;
import org.openqa.selenium.By;
import ui.commons.controls.Button;
import ui.commons.controls.TextField;

import static config.DriverWait.waitElementPresent;
import static config.DriverWait.waitForInvisibilityElement;

public class LoginPage extends BasePage {
    public static final String PAGE_URL = "https://accounts.zoho.eu/signin?servicename=ZohoHome&signupurl=https://www.zoho.com/signup.html";
    public TextField loginTextField = new TextField(By.id("login_id"));
    public TextField passwordTextField = new TextField(By.id("password"));
    public Button nextBtn = new Button(By.id("nextbtn"));

    // TODO DriverWait Class
    public LoginPage() {
        super(PAGE_URL);
    }

    public void loginApp() {
        loginTextField.setValueToTextField(User.getLogin());
        nextBtn.pressBtn();
        waitElementPresent(passwordTextField.getLocator());
        passwordTextField.setValueToTextField(User.getPassword());
        nextBtn.pressBtn();
        waitForInvisibilityElement(nextBtn.getLocator());
        logger.info("User was logged in");
    }

}
