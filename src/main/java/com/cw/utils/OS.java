package com.cw.utils;

public class OS
{
    private static OSType type;
    
    public enum OSType
    {
        Windows, MacOSX, Linux, Other;
    }



    
    static {
        if (System.getProperty("os.name").contains("Windows")) {
            
            type = OSType.Windows;
        }
        else if (System.getProperty("os.name").contains("OS X")) {
            
            type = OSType.MacOSX;
        }
        else if (System.getProperty("os.name").contains("Linux")) {
            
            type = OSType.Linux;
        }
        else {
            
            type = OSType.Other;
        } 
    }





    
    public static OSType getType() {
        return type;
    }
}