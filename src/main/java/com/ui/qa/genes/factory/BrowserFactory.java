package com.ui.qa.genes.factory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.ui.qa.genes.base.PropertiesReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/***
 * 
 * @author Muzzamil
 * 
 *         This class is responsible to setup webdriver instance.
 *
 */
@Listeners(com.automation.remarks.testng.VideoListener.class)
public class BrowserFactory extends PropertiesReader {

	public DesiredCapabilities getDesiredCapabilites() {
		int timeOut = Integer.parseInt(androidSearchTimeout);
		PortNumber = Integer.parseInt(port);
		capabilities = new DesiredCapabilities();

		// ANDROID
		if (mobileOS.equalsIgnoreCase("Android")) {

			//////////////////
			if (deviceType.equalsIgnoreCase("RealDevice")) {
				capabilities.setCapability(MobileCapabilityType.UDID, udid);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			} else if (deviceType.equalsIgnoreCase("VirtualDevice")) {
				capabilities.setCapability("avd", virtualDeviceName);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, virtualDeviceVersion);
			}

			else {
				Assert.fail(
						"Entered device type's value is wrong. Device Type value can be 'RealDevice' and 'VirtualDevice'. Insert correct values.");
			}

			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability(CapabilityType.PLATFORM_NAME, mobileOS);
			capabilities.setCapability("unicodeKeyboard", true);
			capabilities.setCapability("resetKeyboard", true);
			capabilities.setCapability("newCommandTimeout", timeOut);
			capabilities.setCapability("--session-override", true);
			capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
			if (!filePath.isEmpty()) {
				capabilities.setCapability(MobileCapabilityType.APP, ProjectWorkingDirectory + filePath);
				capabilities.setCapability("appWaitActivity", mainActivity);
			}
			capabilities.setCapability("appPackage", appPackage);
			capabilities.setCapability("appWaitPackage", appPackage);
			capabilities.setCapability("appActivity", mainActivity);
			capabilities.setCapability("appWaitActivity", mainActivity);

		} else {
			Assert.fail("Entered mobile's value is wrong. Mobile value can be 'Android'. Insert correct value.");
		}

		return capabilities;
	}

	public AppiumDriver<?> launchApplication() {

		capabilities = getDesiredCapabilites();
		if (!checkIfServerIsRunnning(PortNumber)) {
			startServer(capabilities);
		}
		try {
			driver = new AndroidDriver<MobileElement>(new URL(remoteUrl), capabilities);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return driver;
	}

	public void startServer(DesiredCapabilities capabilities) {

		// Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("0.0.0.0");
		builder.usingPort(PortNumber);
		builder.withCapabilities(capabilities);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

		// Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	public void stopServer() {
		if (service != null) {
			service.stop();
		}
		// ReportAndroid.getMessage("Appium Server is stop.");
	}

	public boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
			// ReportAndroid.getMessage("Appium Server is running on "+port);
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	@BeforeTest
	public void launchApp() {
		launchApplication();

	}

	@AfterTest
	public void tearDown() {

		driver.quit();
		stopServer();
	}

}
