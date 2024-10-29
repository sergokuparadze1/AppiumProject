package org.example;



import java.io.*;
import java.util.Properties;

public class Config extends Properties {
    private static Config _instance;
    private Config() {
        load();
    }



    private void load() {
        try {
            InputStream inputStream = new FileInputStream("config.properties"); // config file location
            load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(String key) {
        return getProperty(key);
    }

    public static Config getInstance() {
        if (_instance == null) {
            _instance = new Config();
        }

        return _instance;
    }

}

