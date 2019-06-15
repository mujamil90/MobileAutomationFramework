package com.ui.qa.genes.base;

import java.awt.Robot;
import java.io.File;
import java.net.URI;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.ui.qa.genes.pageobjects.CategoriesPage;
import com.ui.qa.genes.pageobjects.DealsPage;
import com.ui.qa.genes.pageobjects.HomePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/***
 * 
 * @author Muzzamil
 * 
 * This class is responsible to Initializes all global variables, Class objects and page objects.
 *
 */

public class Initializer  {
	
	public static Properties prop;
	public static String     deviceName;
	public static String   	 udid;
	public static String     platformVersion;
	public static String     androidSearchTimeout;
	public static String     remoteUrl;
	public static String     mainActivity;
	public static String     appPackage;
	public static String     port;
	public static String     mobileOS;
	public static String     deviceType;
	public static String     automationName;
	public static String     virtualDeviceName;
	public static String     virtualDeviceVersion;
	public static String     implicitWait;
	public static String     filePath;
	
    @BeforeSuite
    public static void setdata() {
		PropertiesReader.loadAllFiles(TestData);
		
		deviceName              = prop.getProperty("ANDROID_DEVICE_NAME");
		udid                    = prop.getProperty("ANDROID_UNIQUE_DEVICE_ID");
		platformVersion         = prop.getProperty("ANDROID_PLATFORM_VERSION");
		androidSearchTimeout    = prop.getProperty("ANDRIOD_LOCATOR_SEARCH_TIMEOUT");
		remoteUrl               = prop.getProperty("MOBILE_REMOTE_URL");
		mainActivity            = prop.getProperty("ANDROID_MAIN_ACTIVITY");
		appPackage              = prop.getProperty("ANDROID_APP_PACKAGE");
		filePath                = prop.getProperty("ANDROID_APP_FILEPATH");
		port                    = prop.getProperty("PORT");
		mobileOS                = prop.getProperty("MOBILE_OS_2");
		deviceType              = prop.getProperty("DEVICE_TYPE");
		automationName          = prop.getProperty("ANDROID_AUTOMATION_NAME");
		virtualDeviceName       = prop.getProperty("ANDROID_VIRTUAL_DEVICE_NAME");
		virtualDeviceVersion    = prop.getProperty("ANDROID_VIRTUAL_DEVICE_VERSION");
		implicitWait            = prop.getProperty("ImplicitWait");
		}
	 
	
	
    public static String FS                         = File.separator;
    public static String OSName 	                = System.getProperty("os.name");
	public static String OSArchitecture             = System.getProperty("os.arch");
	public static String OSVersion                  = System.getProperty("os.version");
	public static String OSBit                      = System.getProperty("sun.arch.data.model");
	public static String ProjectWorkingDirectory    = System.getProperty("user.dir");
	public static String TestData                   = "./src/test/resources/ConfigData-TestData/";
	public static String Reports                 	= "./src/test/resources/Reports/";
	public static String reportImages            	= ProjectWorkingDirectory+"\\src\\test\\resources\\Reports\\Images\\";
	public static final String FILE_NAME         	= "Extent_Report.html";
	
	
	// classes refrences used in core framework	
	public static Robot re;
	public static Alert al;
	public static String robotImageName;
	public static Select se;
	public static String FileToUpload;
	public static SoftAssert softAssert;
	public static ITestResult result;
	public static URI uri;
	public static ExtentReports extent;
	public static ExtentTest test;	
	
    
    
 	public static AppiumDriver<?> driver;
	public static AppiumDriverLocalService service;
	public static AppiumServiceBuilder builder;
	public static DesiredCapabilities capabilities;
	public static int PortNumber;
    
   
    // Page Objects
	public static HomePage homePage = new HomePage();
	public static DealsPage dealsPage = new DealsPage();
	public static CategoriesPage categoriesPage = new CategoriesPage();
    
     
}
