package com.softvision.simplemobile;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import static java.lang.Thread.sleep;


//Junit4
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
    static String myApp = "myApp";
    //static String androidApp = "/Users/chesterpressler/development/appium/loginSimpleExample/android/LoginDemo/app/build/outputs/apk/debug/app-debug.apk";
    //static String iosApp = "/Users/chesterpressler/Downloads/appium-ios-basic-master/final/build/Release-iphonesimulator/AppiumTest.app";
    static String androidApp = "src/test/resources/android/app-debug.apk";
    static String iosApp = "src/test/resources/ios/AppiumTest.app";

    static MobileDriver mobileDriver;
    private static String APPIUM_URL = "http://localhost:4723/wd/hub/";
    private static String mobilePlatform = "android";
    private static String attributeTextValue = "text";
    private static String attributeEnabledValue = "enabled";

    @BeforeClass
    public static void init() throws MalformedURLException {
        String platform = System.getProperty("platform");
        System.out.println("platform property is " + platform);
        System.out.println("appium.java.client.version property is " + System.getProperty("appium.java.client.version"));

        if ( platform != null ) {
            mobilePlatform = platform;
        }
        if ( "android".equalsIgnoreCase(mobilePlatform)){
            initAndroid();
        } else if ( "ios".equalsIgnoreCase(mobilePlatform)){
            initIOS();
        } else {
            System.out.println("mobilePlatform is not valid. ios or android are acdeptable values");
        }
    }

    public static void initIOS() throws MalformedURLException {
        System.out.println("initIOS() method called");
        attributeTextValue = "value";
        attributeEnabledValue = "wdVisible";
        File app = new File(iosApp);
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.4");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        mobileDriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    public static void initAndroid() throws MalformedURLException {
        System.out.println("initAndroid() method called");
        attributeTextValue = "text";
        attributeEnabledValue = "enabled";
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, androidApp);

        mobileDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterClass
    public static void afterTest( ) {
        mobileDriver.quit();
    }

    @Test
    public void emailFieldInput() throws InterruptedException {

        MobileElement emailTF = (MobileElement) mobileDriver.findElement(MobileBy.AccessibilityId("emailTextField"));

        emailTF.sendKeys("");
        emailTF.sendKeys("validEmail");
        //emailTF.sendKeys(Keys.RETURN);
        sleep(1000);
        String fieldValue = emailTF.getAttribute(attributeTextValue);
        System.out.println("emailValue " + fieldValue);
        assertEquals(fieldValue , "validEmail");
        mobileDriver.resetApp();
    }


    @Test
    public void passwordFieldInput() throws InterruptedException {
        MobileElement passwordTF = (MobileElement) mobileDriver.findElement(MobileBy.AccessibilityId("passwordTextField"));

        passwordTF.sendKeys("");
        passwordTF.sendKeys("validPW");
        sleep(1000);
        String fieldValue = passwordTF.getAttribute(attributeTextValue);
        System.out.println("passwordValue " + fieldValue);

        assertNotEquals(fieldValue, "validPW");
        mobileDriver.resetApp();
    }


    @Test
    public void loginButtonInput() throws InterruptedException {
        emailFieldInput();
        passwordFieldInput();
        mobileDriver.findElement(MobileBy.AccessibilityId("loginButton")).click();
        sleep(1000);
        MobileElement smiley = (MobileElement) mobileDriver.findElement(MobileBy.AccessibilityId("smileyImage"));
        assertTrue(Boolean.parseBoolean(smiley.getAttribute(attributeEnabledValue)));
        mobileDriver.resetApp();
    }

}
