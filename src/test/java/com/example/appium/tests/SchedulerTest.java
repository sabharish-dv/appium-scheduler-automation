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

public class SchedulerTest {
    private AppiumDriver driver;
    
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        driver = AppiumConfig.getAndroidDriver();
    }
    
    @Test(description = "Launch Delhivery Consignee App, wait 30 seconds, copy screen text and quit")
    @Description("Test to launch the Delhivery Consignee App, wait for 30 seconds, copy all text present on screen and quit the app")
    @Severity(SeverityLevel.CRITICAL)
    public void testAppLaunchAndCopyText() {
        try {
            // Launch the Delhivery Consignee App (already launched in setUp)
            System.out.println("Delhivery Consignee App launched successfully");
            TestUtils.takeScreenshot(driver, "delhivery_app_launched");
            
            // Wait for 30 seconds
            System.out.println("Waiting for 30 seconds...");
            TestUtils.waitForSeconds(30);
            
            // Copy text from screen
            System.out.println("Copying text from Delhivery Consignee App screen...");
            String screenText = TestUtils.copyTextFromScreen(driver);
            
            // Log the copied text (first 500 characters for readability)
            String preview = screenText.length() > 500 ? 
                screenText.substring(0, 500) + "..." : screenText;
            System.out.println("Copied text preview: " + preview);
            System.out.println("Total text length: " + screenText.length() + " characters");
            
            // Take a screenshot after copying text
            TestUtils.takeScreenshot(driver, "delhivery_text_copied");
            
            System.out.println("Delhivery Consignee App test completed successfully");
            
        } catch (Exception e) {
            System.err.println("Test failed with error: " + e.getMessage());
            TestUtils.takeScreenshot(driver, "delhivery_test_failed");
            throw e;
        }
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("Quitting the Delhivery Consignee App...");
            AppiumConfig.quitDriver();
            System.out.println("Delhivery Consignee App quit successfully");
        }
    }
}
