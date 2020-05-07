package com.softvision.simplemobile.setup;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.Data;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Data
public class IOSPlatform extends GenericPlatform {

    public IOSPlatform(){

        super("value","wdVisible");
    }

    @Override
    public MobileDriver getDriver (String testLocation, DesiredCapabilities capabilities) {
    String appiumUrl ;
        if ("local".equalsIgnoreCase(testLocation)) {
            appiumUrl = "http://127.0.0.1:4723/wd/hub";
            System.out.println("local appiumUrl =" + appiumUrl);
        } else if ("saucelabs".equalsIgnoreCase("saucelabs")) {
            SauceLabsConnection sauceLabsConnection = new SauceLabsConnection();
            appiumUrl = sauceLabsConnection.getSauceLabsConnection();
            System.out.println("remote appiumUrl =" + appiumUrl);
        } else {
            appiumUrl = "http://127.0.0.1:4723/wd/hub";
        }
        try {
            return new IOSDriver(new URL(appiumUrl), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
