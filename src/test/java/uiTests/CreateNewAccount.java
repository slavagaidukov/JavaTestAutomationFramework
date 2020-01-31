package uiTests;

import org.junit.Test;


public class CreateNewAccount extends BaseTest {
    public String accountName = "defaultAccount";

    @Test
    public void test() {
        //1. Login the app
        mainPage.goToLoginTab();
        loginPage.loginApp();
        //2. Go to CRM
        applicationsPage.goToCRM();
        //3. Go to Accounts
        crmPage.clickOnAccounts();
        //4. Create an account
        crmPage.createAccount(accountName);
        crmPage.clickOnSave();
        //5. Refresh the page and check that the default account has been saved
        crmPage.clickOnAccounts();

    }

}
