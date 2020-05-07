package com.softvision.simplemobile.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PropertyConfiguration {
    private static ResourceBundle properties = ResourceBundle.getBundle("config");

    public static ResourceBundle loadProperties(String propertyFileName) {
        properties = ResourceBundle.getBundle(propertyFileName);
        return properties;
    }

    public static ResourceBundle fromClassLoader(String dir, String bundleName) throws MalformedURLException {
        File file = new File(dir);
        URL[] urls = {file.toURI().toURL()};
        ClassLoader loader = new URLClassLoader(urls);
        // Properties file name = test.properties. The .properties extension is appended to bundle name
        return properties = ResourceBundle.getBundle(bundleName, Locale.getDefault(), loader);
    }

    //This method doesn't use localization
    public static ResourceBundle fromFile(String dir, String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(dir + "/" + fileName);
        try {
            return properties = new PropertyResourceBundle(fis);
        } finally {
            fis.close();
        }
    }
}
