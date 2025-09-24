package com.example.appium.tests;

import com.example.appium.config.AppiumConfig;
import com.example.appium.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class iOSTest {
    private AppiumDriver driver;
    
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        driver = AppiumConfig.getIOSDriver();
    }
    
    @Test(description = "Test iOS app launch")
    @Description("Verify that the iOS app launches successfully")
    @Severity(SeverityLevel.CRITICAL)
    public void testAppLaunch() {
        // Add your test logic here
        System.out.println("iOS app launched successfully");
        TestUtils.takeScreenshot(driver, "ios_app_launch");
    }
    
    @Test(description = "Test iOS swipe functionality")
    @Description("Verify swipe gestures work correctly on iOS")
    @Severity(SeverityLevel.NORMAL)
    public void testSwipeFunctionality() {
        // Test swipe up
        TestUtils.swipeUp(driver);
        TestUtils.waitForSeconds(2);
        
        // Test swipe down
        TestUtils.swipeDown(driver);
        TestUtils.waitForSeconds(2);
        
        // Test swipe left
        TestUtils.swipeLeft(driver);
        TestUtils.waitForSeconds(2);
        
        // Test swipe right
        TestUtils.swipeRight(driver);
        TestUtils.waitForSeconds(2);
        
        TestUtils.takeScreenshot(driver, "ios_swipe_test");
        System.out.println("iOS swipe functionality test completed");
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            AppiumConfig.quitDriver();
        }
    }
}
