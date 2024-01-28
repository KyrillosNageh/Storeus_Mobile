package com.gui.pom;

import com.shaft.driver.SHAFT;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginPage {
    /****************************************************************************
     *  >>	Variables
     *****************************************************************************/
    private static SHAFT.GUI.WebDriver  driver;

    /****************************************************************************
     *  >>	Constructor
     *****************************************************************************/
    public LoginPage (SHAFT.GUI.WebDriver  driver)
    {
        LoginPage.driver =driver;
    }

    /****************************************************************************
     *  >>	Locators
     *****************************************************************************/
    private final static By email_TextBox = AppiumBy.xpath("//*[@content-desc='Email Address']//following-sibling::android.widget.EditText[1]");
            //By.xpath("//*[@text='Enter your registered email address']");
    private final static By password_TextBox = AppiumBy.xpath("//*[@content-desc='Email Address']//following-sibling::android.widget.EditText[2]");
                    //By.xpath("//*[@text='Enter your password']");
    private final static By signIn_Btn =AppiumBy.accessibilityId("SIGN IN");
    /****************************************************************************
     *  >>	Keywords
     *****************************************************************************/
    public HomePage login(String email, String password){
        driver.element()
                .waitToBeReady(signIn_Btn)
                .click(email_TextBox)
                .type(email_TextBox,email)
                .click(password_TextBox)
                .type(password_TextBox,password)
                .click(signIn_Btn);
        return new HomePage(driver);
    }

    public void verifyWarningMsgIsVisible(WarningMessage message){
        driver.element().waitToBeReady(AppiumBy.accessibilityId(message.getMessage()));
        driver.verifyThat()
                .element(AppiumBy.accessibilityId(message.getMessage()))
                .exists()
                .perform();
    }

    public enum WarningMessage {
        INCORRECT_PASSWORD("Incorrect password. Please try again."),
        INVALID_EMAIL("Invalid login or password"),
        EMAIL_REQUIRED("Email is required"),
        PASSWORD_REQUIRED("Password is required");

        private final String message;

        WarningMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
