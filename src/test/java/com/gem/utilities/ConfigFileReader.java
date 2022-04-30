package com.gem.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private static Properties properties = new Properties();

    static {
        String path = "src/test/resources/configuration.properties";

        try {
            FileInputStream file = new FileInputStream(path);
            properties.load(file);
            file.close();
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("File Was Not found");
        }
    }
    public static String getProperty(String keyWord){
        return properties.getProperty(keyWord);
    }
}
