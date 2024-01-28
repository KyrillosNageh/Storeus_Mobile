package com.gui.pom;

import com.shaft.driver.SHAFT;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class HomePage {
    /****************************************************************************
     *  >>	Variables
     *****************************************************************************/
    private static SHAFT.GUI.WebDriver  driver;


    /****************************************************************************
     *  >>	Constructor
     *****************************************************************************/
    public HomePage (SHAFT.GUI.WebDriver  driver)
    {
        HomePage.driver =driver;
    }

    /****************************************************************************
     *  >>	Locators
     *****************************************************************************/
    private final static By skip_Btn = AppiumBy.accessibilityId("Skip");
    private final static By myAccount_Btn = AppiumBy.accessibilityId("My Account");
    /****************************************************************************
     *  >>	Keywords
     *****************************************************************************/
    public MyAccountPage tapOnMyAccount()
    {
        driver.element()
                .waitToBeReady(myAccount_Btn)
                .click(myAccount_Btn);
        return new MyAccountPage(driver);
    }
}
