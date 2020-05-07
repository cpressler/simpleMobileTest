package com.softvision.simplemobile.setup;

import io.appium.java_client.MobileDriver;
import lombok.Data;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

@Data
public abstract class GenericPlatform {

    protected String attributeTextValue = "text";
    protected String attributeEnabledValue = "enabled";
    private String testLocation;
    private DesiredCapabilities capabilities;

    public GenericPlatform(String attributeTextValue, String attributeEnabledValue ) {
        this.attributeTextValue = attributeTextValue;
        this.attributeEnabledValue = attributeEnabledValue;
    }

    public DesiredCapabilities getCapabilities(String testCapabilityFile) {

        DesiredCapabilities capabilities = null;
        try {
            capabilities = TestCapabilityResource.load(testCapabilityFile);
        } catch (IOException e) {
            e.printStackTrace();
            //fixme  write message and load a default set of capabilities
        }

        // if a local file we need to have an absolute path saved
        // if path is relative then change to absolute so Appium can use it
        String appLocation = (String)capabilities.getCapability("app");
        if ( appLocation!= null && appLocation.contains("src") && !appLocation.contains("sauce") && !appLocation.contains("http")) {
            File app = new File(appLocation);
            capabilities.setCapability("app", app.getAbsolutePath());
        }

        return capabilities;
    }


    public  String getApp (String testLocation, String applicationLocation, String applicationName) {
        String appName = "";

        if ("local".equalsIgnoreCase(testLocation)) {
            File app = new File(applicationLocation + applicationName);
            appName = app.getAbsolutePath();
            System.out.println("local appName =" + appName);

            String appLocation = (String)capabilities.getCapability("app");
            app = new File(appLocation);
            capabilities.setCapability("app", app.getAbsolutePath());

        } else if ("saucelabs".equalsIgnoreCase("saucelabs")) {
            appName = "sauce-storage:" + applicationName;
            System.out.println("saucelabs appName =" + appName);
        }
        return appName;
    }

    abstract public MobileDriver getDriver (String testLocation, DesiredCapabilities capabilities);

}
