package com.gui.pom;

import com.shaft.driver.SHAFT;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class MyAccountPage {
    /****************************************************************************
     *  >>	Variables
     *****************************************************************************/
    private static SHAFT.GUI.WebDriver  driver;


    /****************************************************************************
     *  >>	Constructor
     *****************************************************************************/
    public MyAccountPage(SHAFT.GUI.WebDriver  driver)
    {
        MyAccountPage.driver =driver;
    }

    /****************************************************************************
     *  >>	Locators
     *****************************************************************************/
    private final static By login_Btn = AppiumBy.accessibilityId("LOGIN");
    private final static By register_Btn = AppiumBy.accessibilityId("REGISTER");
    private final static By accountName_Text = AppiumBy.xpath("//android.view.View[contains(@content-desc,\"Hello\")]");

    /****************************************************************************
     *  >>	Keywords
     *****************************************************************************/
    public LoginPage tapOnLogin()
    {
        driver.element()
                .click(login_Btn);
        return new LoginPage(driver);
    }

    public RegisterPage tapOnRegister()
    {
        driver.element()
                .click(register_Btn);
        return new RegisterPage(driver);
    }

    public void verifyAccountName(String expectedName){
        driver.verifyThat()
                .element(accountName_Text)
                .attribute("content-desc")
                .contains(expectedName)
                .perform();
    }
    public String getAccountName(){
        return driver.element()
                .getAttribute(accountName_Text,"content-desc");
    }
}

