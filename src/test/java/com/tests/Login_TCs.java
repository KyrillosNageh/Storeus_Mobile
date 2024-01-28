package com.tests;

import com.gui.pom.HomePage;
import com.gui.pom.LoginPage;
import com.shaft.driver.SHAFT;
import io.appium.java_client.remote.AutomationName;
import org.testng.annotations.*;

public class Login_TCs {
    SHAFT.GUI.WebDriver  driver ;
    SHAFT.TestData.JSON testData;
    SHAFT.TestData.EXCEL excelTestData;

    @Test
    public void verifySignInSuccessfully() {
        new LoginPage(driver).login(testData.getTestData("email")
                        ,testData.getTestData("password"))
                .tapOnMyAccount()
                .verifyAccountName(testData.getTestData("accountName"));
    }

    @Test
    public void verifyInvalidEmailWarningMsg() {
        new LoginPage(driver).login("tes@test.com","12345678");
        new LoginPage(driver).verifyWarningMsgIsVisible(LoginPage.WarningMessage.INVALID_EMAIL);
    }

    @Test
    public void verifyInvalidPasswordWarningMsg() {
        new LoginPage(driver).login(testData.getTestData("email"),"123456");
        new LoginPage(driver).verifyWarningMsgIsVisible(LoginPage.WarningMessage.INCORRECT_PASSWORD);
    }
    @Test
    public void verifyRequiredFieldsMsg() {
        new LoginPage(driver).login("","");
        new LoginPage(driver).verifyWarningMsgIsVisible(LoginPage.WarningMessage.EMAIL_REQUIRED);
        new LoginPage(driver).verifyWarningMsgIsVisible(LoginPage.WarningMessage.PASSWORD_REQUIRED);
    }

    @BeforeClass
    public void beforeClass() {
        SHAFT.Properties.mobile.set().automationName(AutomationName.ANDROID_UIAUTOMATOR2);
        SHAFT.Properties.platform.set().executionAddress("localhost:4723");
        SHAFT.Properties.browserStack.set().deviceName("Pixel4");
        SHAFT.Properties.mobile.set().app("src/test/resources/app/StoreUs_2.1.54.apk");

        testData = new SHAFT.TestData.JSON("loginUsers.json");
        //excelTestData =new  SHAFT.TestData.EXCEL("");
    }

    @BeforeMethod
    public void beforeMethod()
    {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver).tapOnMyAccount()
                .tapOnLogin();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        driver.quit();
    }
}
