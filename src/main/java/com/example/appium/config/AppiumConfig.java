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
            // Check if running on LambdaTest
            String lambdatest = System.getProperty("lambdatest");
            if ("true".equals(lambdatest)) {
                properties.load(new FileInputStream("src/main/resources/lambdatest.properties"));
                logger.info("Loaded LambdaTest configuration");
            } else {
                properties.load(new FileInputStream("src/main/resources/appium.properties"));
                logger.info("Loaded local Appium configuration");
            }
        } catch (IOException e) {
            logger.error("Failed to load properties file", e);
        }
    }
    
    public static AppiumDriver getAndroidDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        String lambdatest = System.getProperty("lambdatest");
        if ("true".equals(lambdatest)) {
            // LambdaTest configuration
            capabilities.setCapability("platformName", properties.getProperty("lambdatest.platform.name"));
            capabilities.setCapability("deviceName", properties.getProperty("lambdatest.device.name"));
            capabilities.setCapability("platformVersion", properties.getProperty("lambdatest.platform.version"));
            capabilities.setCapability("app", properties.getProperty("lambdatest.app.url"));
            capabilities.setCapability("appPackage", properties.getProperty("lambdatest.app.package"));
            capabilities.setCapability("appActivity", properties.getProperty("lambdatest.app.activity"));
            
            // LambdaTest specific capabilities
            capabilities.setCapability("build", properties.getProperty("lambdatest.build.name"));
            capabilities.setCapability("name", properties.getProperty("lambdatest.session.name"));
            capabilities.setCapability("project", properties.getProperty("lambdatest.project.name"));
            capabilities.setCapability("network", Boolean.parseBoolean(properties.getProperty("lambdatest.network")));
            capabilities.setCapability("visual", Boolean.parseBoolean(properties.getProperty("lambdatest.visual")));
            capabilities.setCapability("video", Boolean.parseBoolean(properties.getProperty("lambdatest.video")));
            capabilities.setCapability("console", Boolean.parseBoolean(properties.getProperty("lambdatest.console")));
            capabilities.setCapability("devicelog", Boolean.parseBoolean(properties.getProperty("lambdatest.device.logs")));
            
            // LambdaTest credentials
            String username = System.getProperty("lt.username") != null ? 
                System.getProperty("lt.username") : System.getenv("LT_USERNAME");
            String accessKey = System.getProperty("lt.accesskey") != null ? 
                System.getProperty("lt.accesskey") : System.getenv("LT_ACCESS_KEY");
            
            capabilities.setCapability("user", username);
            capabilities.setCapability("accessKey", accessKey);
            
            String gridUrl = System.getProperty("lt.grid.url") != null ? 
                System.getProperty("lt.grid.url") : System.getenv("LT_GRID_URL");
            
            URL serverUrl = new URL("https://" + username + ":" + accessKey + "@" + gridUrl);
            
            driver = new AndroidDriver(serverUrl, capabilities);
            logger.info("LambdaTest Android driver initialized successfully");
            
        } else {
            // Local configuration
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", properties.getProperty("device.name"));
            capabilities.setCapability("platformVersion", properties.getProperty("device.version"));
            capabilities.setCapability("udid", properties.getProperty("device.udid"));
            capabilities.setCapability("appPackage", properties.getProperty("app.package"));
            capabilities.setCapability("appActivity", properties.getProperty("app.activity"));
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("fullReset", false);
            capabilities.setCapability("automationName", "UiAutomator2");
            
            URL serverUrl = new URL(properties.getProperty("appium.server.url") + 
                                   properties.getProperty("appium.server.path"));
            
            driver = new AndroidDriver(serverUrl, capabilities);
            logger.info("Local Android driver initialized successfully");
        }
        
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicit.wait"))));
        
        return driver;
    }
    
    public static AppiumDriver getIOSDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        String lambdatest = System.getProperty("lambdatest");
        if ("true".equals(lambdatest)) {
            // LambdaTest iOS configuration
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", properties.getProperty("lambdatest.device.name"));
            capabilities.setCapability("platformVersion", properties.getProperty("lambdatest.platform.version"));
            capabilities.setCapability("app", properties.getProperty("lambdatest.app.url"));
            capabilities.setCapability("bundleId", properties.getProperty("lambdatest.app.package"));
            
            // LambdaTest specific capabilities
            capabilities.setCapability("build", properties.getProperty("lambdatest.build.name"));
            capabilities.setCapability("name", properties.getProperty("lambdatest.session.name"));
            capabilities.setCapability("project", properties.getProperty("lambdatest.project.name"));
            
            String username = System.getProperty("lt.username") != null ? 
                System.getProperty("lt.username") : System.getenv("LT_USERNAME");
            String accessKey = System.getProperty("lt.accesskey") != null ? 
                System.getProperty("lt.accesskey") : System.getenv("LT_ACCESS_KEY");
            
            capabilities.setCapability("user", username);
            capabilities.setCapability("accessKey", accessKey);
            
            String gridUrl = System.getProperty("lt.grid.url") != null ? 
                System.getProperty("lt.grid.url") : System.getenv("LT_GRID_URL");
            
            URL serverUrl = new URL("https://" + username + ":" + accessKey + "@" + gridUrl);
            
            driver = new IOSDriver(serverUrl, capabilities);
            logger.info("LambdaTest iOS driver initialized successfully");
            
        } else {
            // Local iOS configuration
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", properties.getProperty("device.name"));
            capabilities.setCapability("platformVersion", properties.getProperty("device.version"));
            capabilities.setCapability("udid", properties.getProperty("device.udid"));
            capabilities.setCapability("bundleId", properties.getProperty("app.package"));
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("fullReset", false);
            capabilities.setCapability("automationName", "XCUITest");
            
            URL serverUrl = new URL(properties.getProperty("appium.server.url") + 
                                   properties.getProperty("appium.server.path"));
            
            driver = new IOSDriver(serverUrl, capabilities);
            logger.info("Local iOS driver initialized successfully");
        }
        
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicit.wait"))));
        
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
