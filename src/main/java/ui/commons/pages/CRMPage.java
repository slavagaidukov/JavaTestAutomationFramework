package ui.commons.pages;

import config.Driver;
import config.DriverWait;
import org.openqa.selenium.By;
import ui.commons.controls.Button;
import ui.commons.controls.TextField;

public class CRMPage extends BasePage {
    public static final String PAGE_URL = "https://crm.zoho.eu/crm/org20069020553/tab/Home/begin";
    public Button accountsBtn = new Button(By.xpath("//div[@data-value='Accounts']"));
    public Button createAccountBtn = new Button(By.xpath("//button[@class='newwhitebtn customPlusBtn ']"));
    public TextField accountNameTextField = new TextField(By.id("Crm_Accounts_ACCOUNTNAME"));
    public CRMPage() {
        super(PAGE_URL);
    }

    public void clickOnAccounts() {
        accountsBtn.pressBtn();
        DriverWait.waitElementPresent(createAccountBtn.getLocator());
        logger.info("Accounts menu was opened");
    }
    public void createAccount(String accountName) {
        createAccountBtn.pressBtn();
        DriverWait.waitForInvisibilityElement(createAccountBtn.getLocator());
        logger.info("Creating account menu was opened");
        accountNameTextField.setValueToTextField(accountName);
    }
    public void isAccountCreated() {
        if ()
    }

}
