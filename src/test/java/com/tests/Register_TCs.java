package com.tests;

import com.gui.pom.HomePage;
import com.gui.pom.RegisterPage;
import com.shaft.driver.SHAFT;
import io.appium.java_client.remote.AutomationName;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Register_TCs {
    SHAFT.GUI.WebDriver  driver ;
    SHAFT.TestData.JSON testData;

    @Test
    public void verifySignUpSuccessfully() {
        new RegisterPage(driver).signUp(testData.getTestData("firstName"),
                        testData.getTestData("firstName"),
                        testData.getTestData("lastName"),
                        testData.getTestData("email"),
                        testData.getTestData("password"))
                .tapOnMyAccount()
                .verifyAccountName(testData.getTestData("firstName"));
    }

    @Test
    public void verifyRequiredFieldsMsg() {
        new RegisterPage(driver).signUp("","","","","");
        new RegisterPage(driver).verifyWarningMsgIsVisible(RegisterPage.WarningMessage.FIRST_NAME_REQUIRED);
        new RegisterPage(driver).verifyWarningMsgIsVisible(RegisterPage.WarningMessage.LAST_NAME_REQUIRED);
        new RegisterPage(driver).verifyWarningMsgIsVisible(RegisterPage.WarningMessage.EMAIL_REQUIRED);
        new RegisterPage(driver).verifyWarningMsgIsVisible(RegisterPage.WarningMessage.PASSWORD_REQUIRED);
        new RegisterPage(driver).verifyWarningMsgIsVisible(RegisterPage.WarningMessage.CONFIRMATION_PASSWORD_REQUIRED);
    }

    @BeforeClass
    public void beforeClass() {
        SHAFT.Properties.mobile.set().automationName(AutomationName.ANDROID_UIAUTOMATOR2);
        SHAFT.Properties.platform.set().executionAddress("localhost:4723");
        SHAFT.Properties.browserStack.set().deviceName("Pixel4");
        SHAFT.Properties.mobile.set().app("src/test/resources/app/StoreUs_2.1.54.apk");

        testData = new SHAFT.TestData.JSON("registerUsers.json");
    }

    @BeforeMethod
    public void beforeMethod()
    {
        driver = new SHAFT.GUI.WebDriver();
        new HomePage(driver).tapOnMyAccount()
                .tapOnRegister();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        driver.quit();
    }
}
