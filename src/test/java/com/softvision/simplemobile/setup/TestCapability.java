package com.softvision.simplemobile.setup;

import lombok.Data;

@Data
public class TestCapability {

   private String  platformName;
   private String  platformVersion;
   private String  deviceName;
   private String  automationName;
   private String  deviceOrientation;
   private String  browserName;
   private String  appiumVersion;
   private String  app;
}
