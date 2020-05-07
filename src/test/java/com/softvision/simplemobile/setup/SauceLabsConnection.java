package com.softvision.simplemobile.setup;

import com.softvision.simplemobile.config.PropertyConfiguration;
import lombok.Data;

import java.util.ResourceBundle;

@Data
public class SauceLabsConnection {

    private  String seleniumURI = "@ondemand.saucelabs.com:443";
    private  String buildTag = System.getenv("BUILD_TAG");
    private  String username = System.getenv("SAUCE_USERNAME");
    private  String accesskey = System.getenv("SAUCE_ACCESS_KEY");
    private  String testObjectURI = "https://appium.testobject.com/wd/hub";

    public SauceLabsConnection() {
        ResourceBundle properties = PropertyConfiguration.loadProperties("saucelabs");
        this.seleniumURI = properties.getString("seleniumURI");
        this.testObjectURI = properties.getString("testObjectURI");
    }

    public SauceLabsConnection(String seleniumURI) {
        this.seleniumURI = seleniumURI;
    }

    public SauceLabsConnection(String seleniumURI, String buildTag, String username, String accesskey) {
        this.seleniumURI = seleniumURI;
        this.buildTag = buildTag;
        this.username = username;
        this.accesskey = accesskey;
    }

    public String getSauceLabsConnectionUserAccess() {
        username = "wgentry";
        accesskey= "5a215b5e-c9a2-4041-92d3-9c4a39a4c3e7";
        String connectionUrl = "https://" + username + ":" + accesskey + "@" + seleniumURI +"/wd/hub";
        return  connectionUrl;
    }

    public String getSauceLabsConnection() {
        String connectionUrl = testObjectURI;
        return  connectionUrl;
    }
}
