package com.testproject.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestDataUtil {
    // Read value from a .properties file
    public static String getProperty(String filePath, String key) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            prop.load(fis);
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Read value from a JSON file
    public static String getJsonValue(String filePath, String key) {
        JSONParser parser = new JSONParser();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(new java.io.InputStreamReader(fis));
            return (String) jsonObject.get(key);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
} 