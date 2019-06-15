# Mobile Automation Framework-Datadriven

[<img src="https://avatars3.githubusercontent.com/u/3221291?v=3&s=200" align="left" width="100">](http://appium.io)

**This is a Mobile automation Framework supports Android based application. It is a data driven framework. Please check 'MobileConfig.properties' file and provide required details for your device and AUT such device ID and Main activity for Application. Prerequisite for AUT (Application under Test), you can provide APK file path if you have '.apk' file for AUT and Main Activity OR you can provide 'App Package' and 'Main activity'.**

### Tools and Technologu used
 - Designed and written in **JAVA**
 - Framework used **Appium**
 - Implemented using **TestNG**
 - Build Tools - **Maven**
 - Test data source file type- **Properties files** (no need to change code just add file and use key to fetch data)
 - Implemented with  **Page Object Model Design Pattern**
 - Video Recording - **Automation-Remarks**

### Mobile OS Supported
 - Android

### Platform Support
 - Windows
 - Linux
 - Macintosh

---
### Reporting
 - [Extent Reporting](http://extentreports.com/)
 
---
### Usage
```sh
$ mvn clean test
```
---
### Browser Setup
 - Navigate to *.\src\main\resources* change *BrowserType* in the ZalandoConfig.properties
 or use Maven to invoke different browsers
---

### Report Details

**Report Location** `System.getProperty("user.dir") + "/src/test/resources/Reports/";`

**Failed Screenshots** `System.getProperty("user.dir") + "/src/test/resources/Reports/Images";`

**Video Location** `System.getProperty("user.dir") + "videos";`

**Test Data** `System.getProperty("user.dir") + "/src/test/resources/TestData";`

---


### Video Feature
 > _enabledALL_ -> To Save recorded video for All test case. Stored in `"./src/test/resources/Reports/Videos"`
 
 > _enabledFAILED_ -> To Save recorded video for ONLY FAILED test case. Stored in  `"./src/test/resources/Reports/Videos"`
 
 > _disabled_ -> To not save the video
 
 ** Required ADB Commands to get Device and AUT related information:**
 - adb devices --> To get list of connected devices.
 - adb install <path to apk>  --> Install apk file on device.
 - adb uninstall io.appium.uiautomator2.server --> Clear cache of UiAutomator2  from server directory
 - adb uninstall io.appium.uiautomator2.server.test --> Clear cache of UiAutomator2  from test directory
 - adb shell pm clear <Package/bundle ID of AUT> --> Clear application's data
 - adb shell dumpsys window windows | grep -E ‘mCurrentFocus’ --> To get AppPackage and Main activity (before this command open AUT and corresponding page).
