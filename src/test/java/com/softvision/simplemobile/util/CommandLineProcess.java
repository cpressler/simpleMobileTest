package com.softvision.simplemobile.util;

import lombok.Data;

@Data
public class CommandLineProcess {

    private  String platform ;
    private  String testsite ;
    private  String capability ;


    public  void process(){
        platform = System.getProperty("platform");
        testsite = System.getProperty("testsite");
        capability = System.getProperty("capability");
        System.out.println("platform property is " + platform);
        System.out.println("testsite property is " + testsite);
        System.out.println("capability property is " + capability);
    }
}
