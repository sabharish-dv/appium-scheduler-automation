# Appium Automation Project

This Maven project is configured for mobile app automation testing using Appium.

## Prerequisites

1. **Java 17** - Make sure Java 17 is installed
2. **Maven** - Build tool for the project
3. **Appium Server** - Install Appium server globally or use Appium Desktop
4. **Android SDK** - For Android testing
5. **Xcode** - For iOS testing (macOS only)

## Setup Instructions

### 1. Install Appium Server
```bash
npm install -g appium
npm install -g appium-doctor
```

### 2. Install Appium Drivers
```bash
appium driver install uiautomator2  # For Android
appium driver install xcuitest      # For iOS
```

### 3. Verify Installation
```bash
appium-doctor --android  # Check Android setup
appium-doctor --ios      # Check iOS setup (macOS only)
```

### 4. Configure Device Settings
Update `src/main/resources/appium.properties` with your device details:
- Device name
- Platform version
- UDID
- App package/activity

## Project Structure

```
src/
├── main/
│   ├── java/com/example/appium/
│   │   ├── config/          # Appium configuration
│   │   ├── pages/           # Page Object Model classes
│   │   ├── tests/           # Test classes
│   │   └── utils/           # Utility classes
│   └── resources/
│       └── appium.properties # Configuration file
└── test/
    └── java/com/example/appium/tests/ # Test classes
```

## Running Tests

### Run all tests
```bash
mvn test
```

### Run specific test suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Generate Allure report
```bash
mvn allure:serve
```

## Dependencies Included

- **Appium Java Client** - Core Appium functionality
- **Selenium WebDriver** - WebDriver implementation
- **TestNG** - Test framework
- **JUnit 5** - Additional testing support
- **Allure** - Test reporting
- **ExtentReports** - Detailed reporting
- **Jackson** - JSON processing
- **Apache Commons** - Utility functions
- **WebDriverManager** - Automatic driver management
- **Apache POI** - Excel file handling
- **Logback** - Logging

## Configuration

The project uses `appium.properties` for configuration. Key settings include:
- Appium server URL
- Device capabilities
- App details
- Timeouts
- Screenshot settings

## Best Practices

1. Use Page Object Model for better maintainability
2. Implement proper wait strategies
3. Take screenshots on test failures
4. Use meaningful test names and descriptions
5. Implement proper cleanup in @AfterMethod
6. Use Allure annotations for better reporting

## Troubleshooting

1. Ensure Appium server is running before executing tests
2. Check device connectivity and capabilities
3. Verify app installation on device/emulator
4. Check logs for detailed error information
