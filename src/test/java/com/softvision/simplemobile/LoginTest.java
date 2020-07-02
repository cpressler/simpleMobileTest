package com.softvision.simplemobile;
import com.softvision.simplemobile.setup.GenericPlatform;
import com.softvision.simplemobile.util.PlatformTestConfigurator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.junit.Ignore;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

import static java.lang.Thread.sleep;


//Junit4
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

// Junit5
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class LoginTest {
    static DesiredCapabilities capabilities;
    static MobileDriver mobileDriver;
    private static GenericPlatform genericPlatform;
    private static PlatformTestConfigurator platformTestConfigurator = new PlatformTestConfigurator();

    @BeforeClass
    public static void initProcessCommandLine() throws Exception {

        platformTestConfigurator.process(); // process properties passed on command line
        platformTestConfigurator.setupTestEnvironment();
        platformTestConfigurator.startDriver();

        mobileDriver = platformTestConfigurator.getMobileDriver();
        genericPlatform = platformTestConfigurator.getGenericPlatform();
        mobileDriver.resetApp();
    }

    @AfterClass
    public static void afterTest( ) throws Exception {
        platformTestConfigurator.processExit();
    }


    @Ignore
    @Test
    public void emailFieldInput() throws InterruptedException {

        MobileElement emailTF = (MobileElement) mobileDriver.findElement(MobileBy.AccessibilityId("emailTextField"));

        //emailTF.sendKeys("");
        emailTF.sendKeys("validEmail");
        //emailTF.sendKeys(Keys.RETURN);
        sleep(1000);
        String fieldValue = emailTF.getAttribute(genericPlatform.getAttributeTextValue());
        System.out.println("emailValue " + fieldValue);
        assertEquals(fieldValue , "validEmail");
    }

    @Ignore
    @Test
    public void passwordFieldInput() throws InterruptedException {
        mobileDriver.resetApp();
        MobileElement passwordTF = (MobileElement) mobileDriver.findElement(MobileBy.AccessibilityId("passwordTextField"));

        passwordTF.sendKeys("");
        passwordTF.sendKeys("validPW");
        sleep(1000);
        String fieldValue = passwordTF.getAttribute(genericPlatform.getAttributeTextValue());
        System.out.println("passwordValue " + fieldValue);

        assertNotEquals(fieldValue, "validPW");
    }


    @Test
    public void loginButtonInput() throws InterruptedException {
        mobileDriver.resetApp();
        emailFieldInput();
        passwordFieldInput();
        mobileDriver.findElement(MobileBy.AccessibilityId("loginButton")).click();
        sleep(1000);
        MobileElement smiley = (MobileElement) mobileDriver.findElement(MobileBy.AccessibilityId("smileyImage"));
        assertTrue(Boolean.parseBoolean(smiley.getAttribute(genericPlatform.getAttributeEnabledValue())));

    }
}
