package com.example.appium.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class AppiumConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppiumConfig.class);
    private static Properties properties;
    private static AppiumDriver driver;
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/appium.properties"));
        } catch (IOException e) {
            logger.error("Failed to load properties file", e);
        }
    }
    
    public static AppiumDriver getAndroidDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        // Basic capabilities
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", properties.getProperty("device.name"));
        capabilities.setCapability("platformVersion", properties.getProperty("device.version"));
        capabilities.setCapability("udid", properties.getProperty("device.udid"));
        
        // App capabilities
        capabilities.setCapability("appPackage", properties.getProperty("app.package"));
        capabilities.setCapability("appActivity", properties.getProperty("app.activity"));
        
        // Additional capabilities
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("automationName", "UiAutomator2");
        
        URL serverUrl = new URL(properties.getProperty("appium.server.url") + 
                               properties.getProperty("appium.server.path"));
        
        driver = new AndroidDriver(serverUrl, capabilities);
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicit.wait"))));
        
        logger.info("Android driver initialized successfully");
        return driver;
    }
    
    public static AppiumDriver getIOSDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        // Basic capabilities
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", properties.getProperty("device.name"));
        capabilities.setCapability("platformVersion", properties.getProperty("device.version"));
        capabilities.setCapability("udid", properties.getProperty("device.udid"));
        
        // App capabilities
        capabilities.setCapability("bundleId", properties.getProperty("app.package"));
        
        // Additional capabilities
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("automationName", "XCUITest");
        
        URL serverUrl = new URL(properties.getProperty("appium.server.url") + 
                               properties.getProperty("appium.server.path"));
        
        driver = new IOSDriver(serverUrl, capabilities);
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicit.wait"))));
        
        logger.info("iOS driver initialized successfully");
        return driver;
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver quit successfully");
        }
    }
    
    public static AppiumDriver getDriver() {
        return driver;
    }
}
