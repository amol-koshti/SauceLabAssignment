package com.testproject.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "screenshots/" + screenshotName + "_" + timestamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void attachScreenshotToReport(String filePath) {
        Reporter.log("<br><a href='" + filePath + "' target='_blank'><img src='" + filePath + "' height='200'/></a><br>");
    }
} 