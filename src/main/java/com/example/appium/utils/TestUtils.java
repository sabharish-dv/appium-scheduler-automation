package com.example.appium.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class TestUtils {
    private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);
    
    public static void takeScreenshot(AppiumDriver driver, String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            String timestamp = String.valueOf(System.currentTimeMillis());
            String fileName = testName + "_" + timestamp + ".png";
            File destFile = new File("./screenshots/" + fileName);
            FileUtils.copyFile(sourceFile, destFile);
            logger.info("Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to take screenshot", e);
        }
    }
    
    public static String copyTextFromScreen(AppiumDriver driver) {
        StringBuilder screenText = new StringBuilder();
        try {
            // Get page source and extract text elements
            String pageSource = driver.getPageSource();
            
            // Try to find all text elements on screen
            List<WebElement> textElements = driver.findElements(By.xpath("//*[@text!='' or @content-desc!='' or @resource-id!='']"));
            
            for (WebElement element : textElements) {
                String text = element.getText();
                String contentDesc = element.getAttribute("content-desc");
                
                if (text != null && !text.trim().isEmpty()) {
                    screenText.append(text).append("\n");
                }
                if (contentDesc != null && !contentDesc.trim().isEmpty()) {
                    screenText.append(contentDesc).append("\n");
                }
            }
            
            String extractedText = screenText.toString();
            
            // Copy to system clipboard
            StringSelection stringSelection = new StringSelection(extractedText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            
            // Save to file as well
            saveTextToFile(extractedText, "screen_text");
            
            logger.info("Text copied from screen. Total characters: " + extractedText.length());
            return extractedText;
            
        } catch (Exception e) {
            logger.error("Failed to copy text from screen", e);
            return "";
        }
    }
    
    private static void saveTextToFile(String text, String fileName) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String fullFileName = fileName + "_" + timestamp + ".txt";
            File textFile = new File("./screenshots/" + fullFileName);
            
            // Create directory if it doesn't exist
            textFile.getParentFile().mkdirs();
            
            try (FileWriter writer = new FileWriter(textFile)) {
                writer.write(text);
            }
            
            logger.info("Screen text saved to: " + textFile.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to save text to file", e);
        }
    }
    
    public static void swipe(AppiumDriver driver, int startX, int startY, int endX, int endY, int duration) {
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(startX, startY))
                  .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                  .moveTo(PointOption.point(endX, endY))
                  .release()
                  .perform();
    }
    
    public static void swipeUp(AppiumDriver driver) {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        swipe(driver, width / 2, (int) (height * 0.8), width / 2, (int) (height * 0.2), 1000);
    }
    
    public static void swipeDown(AppiumDriver driver) {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        swipe(driver, width / 2, (int) (height * 0.2), width / 2, (int) (height * 0.8), 1000);
    }
    
    public static void swipeLeft(AppiumDriver driver) {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        swipe(driver, (int) (width * 0.8), height / 2, (int) (width * 0.2), height / 2, 1000);
    }
    
    public static void swipeRight(AppiumDriver driver) {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        swipe(driver, (int) (width * 0.2), height / 2, (int) (width * 0.8), height / 2, 1000);
    }
    
    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Sleep interrupted", e);
        }
    }
}
