package com.espark.adarsh.apiai.util;

import java.io.*;
import java.util.Properties;

public class PropertiesReader {

    private static final Properties properties=new Properties();

    static{
        try{
            File file =new File("src\\main\\resources\\application.properties");
            if(file.exists()){
                properties.load(new FileInputStream(file));
            }else{
                throw new FileNotFoundException("application configuration not found ");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
