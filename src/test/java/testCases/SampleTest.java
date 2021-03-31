package test.java.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginSuccessPage;

public class SampleTest extends test.java.testCases.BaseTest {
    HomePage homePage;
    LoginSuccessPage loginSuccessPage;

    @Test(enabled = true)
    public void verifyTitle() {
        logger.info("Verify whether the title is : Welcome: Mercury Tours");
        Assert.assertEquals("Welcome: Mercury Tours", driver.getTitle());
    }

    @Test(enabled = true)
    public void loginTest() {
        homePage = new HomePage(driver);
        logger.info("Entering username");
        homePage.enterUsername("qwe");
        logger.info("Entering password");
        homePage.enterPassword("qwe");
        logger.info("Clicking submit button");
        homePage.clickSubmit();
        loginSuccessPage = new LoginSuccessPage(driver);
        Assert.assertTrue(loginSuccessPage.isLoginSuccessLabelDisplayed(), "Login is failed");
        logger.info("Login is successful");
    }

    @Test(enabled = true)
    public void verifyUrl() {
        logger.info("Verify whether the url is : https://demo.guru99.com/test/newtours/index.php");
        Assert.assertEquals("https://demo.guru99.com/test/newtours/index.php", driver.getCurrentUrl());
    }
}
