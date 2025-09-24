# Appium Scheduler Automation

This project demonstrates automated testing with Appium, featuring a SchedulerTest that runs on LambdaTest via GitHub Actions.

## 🚀 Features

- **SchedulerTest**: Automated test that launches an app, waits 30 seconds, copies screen text, and quits
- **LambdaTest Integration**: Cloud-based testing on real devices
- **GitHub Actions**: Automated scheduling and execution
- **Cross-platform**: Supports both Android and iOS testing

## �� Prerequisites

- Java 17+
- Maven 3.6+
- LambdaTest account with credentials
- GitHub repository with Actions enabled

## 🛠️ Setup Instructions

### 1. LambdaTest Setup

✅ **Already configured with your credentials:**
- **Username**: ranjisabharish
- **App URL**: lt://APP1016032111758469753275919
- **Device**: Galaxy.* (Android 14)
- **Platform**: Android

### 2. GitHub Secrets Configuration

Add the following secrets to your GitHub repository:

1. Go to your repository → Settings → Secrets and variables → Actions
2. Add these repository secrets:

```
LT_USERNAME=ranjisabharish
LT_ACCESS_KEY=LT_5choxWTgS6Y4sSzwze82apqgHFFJiJr1d8XFxFznFBrLLt4
LT_GRID_URL=mobile-hub.lambdatest.com/wd/hub
```

### 3. App Configuration

✅ **Already configured:**
- **App Package**: com.delhiveryConsigneeApp
- **App Activity**: com.delhiveryConsigneeApp.MainActivity
- **App URL**: lt://APP1016032111758469753275919

## 🕐 Scheduling

The GitHub Actions workflow is configured to run:

- **Daily at 9:45 PM UTC** (customizable in `.github/workflows/lambdatest-scheduler.yml`)
- **On push** to main/master branch
- **Manually** via GitHub Actions tab

### Customizing Schedule

Edit the cron expression in `.github/workflows/lambdatest-scheduler.yml`:

```yaml
schedule:
  - cron: '45 21 * * *'  # 9:45 PM UTC daily
```

## 🧪 Running Tests

### Local Execution

```bash
# Run SchedulerTest locally
mvn test -Dtest=SchedulerTest

# Run on LambdaTest
mvn test -Dtest=SchedulerTest -Dlambdatest=true \
  -Dlt.username=ranjisabharish \
  -Dlt.accesskey=LT_5choxWTgS6Y4sSzwze82apqgHFFJiJr1d8XFxFznFBrLLt4 \
  -Dlt.grid.url=mobile-hub.lambdatest.com/wd/hub
```

### GitHub Actions

1. **Automatic**: Runs on schedule or push
2. **Manual**: Go to Actions tab → LambdaTest Scheduler Test → Run workflow

## 📊 Test Results

- **Screenshots**: Saved in `screenshots/` directory
- **Test Reports**: Available in GitHub Actions artifacts
- **LambdaTest Dashboard**: View detailed results at [automation.lambdatest.com](https://automation.lambdatest.com/logs/)

## 🔧 Configuration Files

- `src/main/resources/appium.properties` - Local Appium configuration
- `src/main/resources/lambdatest.properties` - LambdaTest configuration
- `.github/workflows/lambdatest-scheduler.yml` - GitHub Actions workflow
- `testng.xml` - TestNG suite configuration

## 📱 Supported Platforms

- **Android**: API 14+ (Galaxy devices)
- **iOS**: iOS 14+ (iPhone 12, iPhone 13, etc.)

## 🎯 Current Configuration

- **App**: Delhivery Consignee App
- **Device**: Galaxy.* (Android 14)
- **Schedule**: Daily at 9:45 PM UTC
- **Build**: Appium Scheduler Test Build
- **Session**: SchedulerTest Session

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test locally and on LambdaTest
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 🆘 Support

- **Issues**: Create an issue in this repository
- **LambdaTest**: [Documentation](https://www.lambdatest.com/support/docs/)
- **Appium**: [Documentation](https://appium.io/docs/en/about-appium/intro/)

---

**Happy Testing! 🎉**
