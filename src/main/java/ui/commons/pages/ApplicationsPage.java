package ui.commons.pages;

import config.DriverWait;
import org.openqa.selenium.By;
import ui.commons.controls.Button;

import static config.DriverWait.waitForInvisibilityElement;

public class ApplicationsPage extends BasePage {
    public static final String PAGE_URL = "https://home.zoho.eu/home";
    public Button CRMBtn = new Button(By.xpath("//div[text()='CRM']"));

    public ApplicationsPage() {
        super(PAGE_URL);
    }

    public void goToCRM() {
        CRMBtn.pressBtn();
        waitForInvisibilityElement(CRMBtn.getLocator());
        logger.info("CRM Page was opened");
    }
}
