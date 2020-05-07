package com.softvision.simplemobile;
import com.softvision.simplemobile.setup.AndroidPlatform;
import com.softvision.simplemobile.setup.GenericPlatform;
import com.softvision.simplemobile.setup.IOSPlatform;
import com.softvision.simplemobile.setup.TestCapabilityResource;
import com.softvision.simplemobile.util.CommandLineProcess;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Ignore;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

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
    private static String testLocation = "local";
    private static String testCapabilityFile = "capabilities/local-android-nexus-s-api28.json";
    private static String mobilePlatform = "android";
    private static GenericPlatform genericPlatform;
    private static CommandLineProcess commandLineProcess = new CommandLineProcess();

    @BeforeClass
    public static void initProcessCommandLine() throws IOException {

        commandLineProcess.process(); // process properties passed on command line


        if ( commandLineProcess.getPlatform() != null ) {
            mobilePlatform = commandLineProcess.getPlatform();
        }
        if ( commandLineProcess.getTestsite() != null ) {
            testLocation = commandLineProcess.getTestsite();
        }
        if ( commandLineProcess.getCapability() != null ) {
            testCapabilityFile = commandLineProcess.getCapability();
        }

        if ( "android".equalsIgnoreCase(mobilePlatform)){
            initAndroid(testLocation, testCapabilityFile);
        } else if ( "ios".equalsIgnoreCase(mobilePlatform)){
            initIOS(testLocation, testCapabilityFile);
        } else {
            System.out.println("mobilePlatform is not valid. ios or android are acdeptable values");
        }
        mobileDriver.resetApp();
    }

    public static void initIOS(String testLocation, String testCapabilityFile) throws MalformedURLException {
        genericPlatform = new IOSPlatform();
        //genericPlatform = iosPlatform;
        System.out.println("initIOS() method called");


        capabilities = genericPlatform.getCapabilities(testCapabilityFile);
        mobileDriver = genericPlatform.getDriver(testLocation,capabilities);
    }

    public static void initAndroid(String testLocation, String testCapabilityFile) throws MalformedURLException {
        System.out.println("initAndroid() method called");
        genericPlatform = new AndroidPlatform();
        //genericPlatform = androidPlatform;

        capabilities = genericPlatform.getCapabilities(testCapabilityFile);
        mobileDriver = genericPlatform.getDriver(testLocation,capabilities);
    }

    @AfterClass
    public static void afterTest( ) {
        mobileDriver.quit();
    }


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
