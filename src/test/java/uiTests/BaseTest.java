package uiTests;

import config.Driver;
import entity.User;
import org.junit.After;
import org.junit.Before;
import ui.commons.pages.ApplicationsPage;
import ui.commons.pages.CRMPage;
import ui.commons.pages.LoginPage;
import ui.commons.pages.MainPage;

public class BaseTest {
    protected Driver driver;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected ApplicationsPage applicationsPage;
    protected CRMPage crmPage;

    @Before
    public void setUp() throws Exception {
        driver = Driver.initializeDriver();
        loginPage = new LoginPage();
        mainPage = new MainPage();
        applicationsPage = new ApplicationsPage();
        crmPage = new CRMPage();
        driver.get("https://www.zoho.com/");
        driver.manage().window().maximize();
    }


    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        if (driver!=null) {
            driver.quit();
        }
    }

 //   public MainPage loginAsQA() {
 //       User user = new User();
 //       return loginAs(User.getUser());
  //  }

    //public MainPage loginAs(User user) {
     //   LoginPage loginPage = new LoginPage();
      //  loginPage.setUserLoginAndPassword(user.getLogin(), user.getPassword());
    //    return new MainPage();
 //   }
}
