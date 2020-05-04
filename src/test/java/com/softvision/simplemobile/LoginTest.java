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
    static String androidApp = "app-debug.apk";
    static String iosApp = "AppiumTest.app";
    static String androidAppLocation = "src/test/resources/android/";
    static String iosAppLocation = "src/test/resources/ios/";

    static MobileDriver mobileDriver;
    private static String testLocation = "local";
    private static String appiumUrl = "http://localhost:4723/wd/hub/";
    private static String seleniumURI = "@ondemand.saucelabs.com:443";
    private static String buildTag = System.getenv("BUILD_TAG");
    private static String username = System.getenv("SAUCE_USERNAME");
    private static String accesskey = System.getenv("SAUCE_ACCESS_KEY");
    private static String mobilePlatform = "android";
    private static String attributeTextValue = "text";
    private static String attributeEnabledValue = "enabled";

    @BeforeClass
    public static void init() throws MalformedURLException {
        String platform = System.getProperty("platform");
        String testsite = System.getProperty("testsite");
        System.out.println("platform property is " + platform);
        System.out.println("testsite property is " + testsite);

        if ( platform != null ) {
            mobilePlatform = platform;
        }
        if ( testsite != null ) {
            testLocation = testsite;
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

        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.4");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");
        capabilities.setCapability(MobileCapabilityType.APP, getApp() );

        //mobileDriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        mobileDriver = getDriver();
    }

    public static void initAndroid() throws MalformedURLException {
        System.out.println("initAndroid() method called");
        attributeTextValue = "text";
        attributeEnabledValue = "enabled";

        capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, getApp());
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("appiumVersion", "1.17.0");

//        capabilities.setCapability("appiumVersion", "1.16.0");
//        capabilities.setCapability("deviceName","Samsung Galaxy S9 WQHD GoogleAPI Emulator");
//        capabilities.setCapability("deviceOrientation", "portrait");
//        capabilities.setCapability("browserName", "");
//        capabilities.setCapability("platformVersion","9.0");
//        capabilities.setCapability("platformName","Android");
//        capabilities.setCapability("app","sauce-storage:app-debug.apk");

        mobileDriver = getDriver();
    }

    @AfterClass
    public static void afterTest( ) {
        mobileDriver.quit();
    }

    public static MobileDriver getDriver ()  {
        if ( "local".equalsIgnoreCase(testLocation)) {
            appiumUrl = "http://127.0.0.1:4723/wd/hub";
            System.out.println("local appiumUrl =" + appiumUrl);
        } else if ( "saucelabs".equalsIgnoreCase("saucelabs")) {
            appiumUrl = "https://" + username + ":" + accesskey + seleniumURI +"/wd/hub";
            System.out.println("remote appiumUrl =" + appiumUrl);
        } else {
            appiumUrl = "http://127.0.0.1:4723/wd/hub";
        }
        try {
        if ( "android".equalsIgnoreCase(mobilePlatform)){
            return  new IOSDriver(new URL(appiumUrl), capabilities);
        } else if ( "ios".equalsIgnoreCase(mobilePlatform)){
            return new IOSDriver(new URL(appiumUrl), capabilities);
        } else {
            return null;
        }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static String getApp ()  {
        String appName = "";
        if ( "local".equalsIgnoreCase(testLocation)) {
            if ( "android".equalsIgnoreCase(mobilePlatform)){
                File app = new File(androidAppLocation + androidApp);
                appName = app.getAbsolutePath();
            } else if ( "ios".equalsIgnoreCase(mobilePlatform)){
                File app = new File(iosAppLocation + iosApp);
                appName = app.getAbsolutePath();
            }
            System.out.println("local appName =" + appName);
        } else if ( "saucelabs".equalsIgnoreCase("saucelabs")) {
            if ( "android".equalsIgnoreCase(mobilePlatform)){
                //appName = "sauce-storage:" + androidApp;
                appName = "https://github.com/cpressler/simpleMobileTest/blob/master/src/test/resources/android/app-debug.apk";
            } else if ( "ios".equalsIgnoreCase(mobilePlatform)){
                //appName = iosApp;
                appName = "sauce-storage:" + "myApp.zip";
            }
            System.out.println("saucelabs appName =" + appName);
        }
        return appName;

    }

    @Test
    public void emailFieldInput() throws InterruptedException {

        MobileElement emailTF = (MobileElement) mobileDriver.findElement(MobileBy.AccessibilityId("emailTextField"));

        //emailTF.sendKeys("");
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
