package com.Bitm.NinzaHRM.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {	
    public static String getData(String key) throws IOException {
        FileInputStream fis =new FileInputStream("./src/main/resources/DD/config1.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop.getProperty(key);
    }

}
