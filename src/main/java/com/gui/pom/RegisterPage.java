package com.gui.pom;

import com.shaft.driver.SHAFT;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class RegisterPage {
    /****************************************************************************
     *  >>	Variables
     *****************************************************************************/
    private static SHAFT.GUI.WebDriver  driver;

    /****************************************************************************
     *  >>	Constructor
     *****************************************************************************/
    public RegisterPage (SHAFT.GUI.WebDriver  driver)
    {
        RegisterPage.driver =driver;
    }

    /****************************************************************************
     *  >>	Locators
     *****************************************************************************/
    private final static By firstName_TextBox =  AppiumBy.xpath("//*[@content-desc='Create an account']//following-sibling::android.widget.EditText[1]");
    private final static By lastName_TextBox =  AppiumBy.xpath("//*[@content-desc='Create an account']//following-sibling::android.widget.EditText[2]");
    private final static By email_TextBox =  AppiumBy.xpath("//*[@content-desc='Create an account']//following-sibling::android.widget.EditText[3]");
    private final static By password_TextBox =  AppiumBy.xpath("//*[@content-desc='Create an account']//following-sibling::android.widget.EditText[4]");
    private final static By passwordConfirmation_TextBox =  AppiumBy.xpath("//*[@content-desc='Create an account']//following-sibling::android.widget.EditText[5]");
    private final static By create_Btn = AppiumBy.accessibilityId("SIGN UP");

    /****************************************************************************
     *  >>	Keywords
     *****************************************************************************/
    public RegisterPage tapOnSignUp(){
        driver.element()
                .click(create_Btn);
        return this;
    }

    public HomePage signUp(String firstName, String lastName, String email, String password, String confirmationPassword){
        driver.element()
                .click(firstName_TextBox)
                .type(firstName_TextBox,firstName)
                .click(lastName_TextBox)
                .type(lastName_TextBox,lastName)
                .click(email_TextBox)
                .type(email_TextBox,email)
                .touch()
                .hideNativeKeyboard()
                .tap(password_TextBox)
                .element()
                .type(password_TextBox,password)
                .touch()
                .hideNativeKeyboard()
                .element()
                .click(passwordConfirmation_TextBox)
                .type(passwordConfirmation_TextBox,confirmationPassword)
                .touch()
                .hideNativeKeyboard()
                .tap(create_Btn);
        return  new HomePage(driver);
    }

    public void verifyWarningMsgIsVisible(RegisterPage.WarningMessage message){
        driver.element().waitToBeReady(AppiumBy.accessibilityId(message.getMessage()));
        driver.verifyThat()
                .element(AppiumBy.accessibilityId(message.getMessage()))
                .exists()
                .perform();
    }

    public enum WarningMessage {
        FIRST_NAME_REQUIRED("First name is required"),
        LAST_NAME_REQUIRED("Last Name is required"),
        EMAIL_REQUIRED("Email is required"),
        PASSWORD_REQUIRED("Password is required"),
        CONFIRMATION_PASSWORD_REQUIRED("Confirm password is required");
        private final String message;

        WarningMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
