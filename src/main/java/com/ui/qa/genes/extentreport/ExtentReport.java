package com.ui.qa.genes.extentreport;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.ui.qa.genes.actions.DriverActions;

/***
 * 
 * @author Muzzamil
 * 
 * This class is responsible to generate extent report for the framework.
 *
 */
public class ExtentReport extends DriverActions {

	@BeforeTest
	public  static void InitObjects() throws Exception {
		
		
		// Initialize extent report and basic structure
		extent = new ExtentReports(Reports + FILE_NAME, true);
		extent.addSystemInfo("Author", "Muzzamil");
		extent.addSystemInfo("Mobile OS", mobileOS);
		extent.addSystemInfo("OS", OSName);
		extent.addSystemInfo("OS Version", OSVersion);
		extent.addSystemInfo("OS Architecture", OSArchitecture);
		extent.addSystemInfo("OS Bit", OSBit);
		extent.addSystemInfo("JAVA Version", System.getProperty("java.version"));
		extent.addSystemInfo("User Name", System.getProperty("user.name"));
		extent.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
		extent.addSystemInfo("Host IP Address", InetAddress.getLocalHost().getHostAddress());
	}

	@AfterMethod
	public void testCaseResult(ITestContext ctx, ITestResult result) {
		try {

			if (result.getStatus() == ITestResult.FAILURE) {
				System.out.println("Failed");
				test.log(LogStatus.FAIL, "Case Id not Generated", takeScreenshotOnTestFailure(result));
				test.log(LogStatus.FAIL, result.getMethod().getMethodName() + " :Test failure error message",
						result.getThrowable().getMessage());
				logResult();

			}
			if (result.getStatus() == ITestResult.SUCCESS) {
				System.out.println("Passed");
				test.log(LogStatus.PASS, "Case Id Generated", takeScreenshot(result));
				logResult();

			}

			if (result.getStatus() == ITestResult.SKIP) {
				System.out.println("Skipped");
				test.log(LogStatus.SKIP, "Case Id Skipped", takeScreenshotOnTestSkipping(result));
				logResult();
			}

			if (result.getStatus() == ITestResult.SUCCESS_PERCENTAGE_FAILURE) {

				test.log(LogStatus.FAIL, "Test passed with percentage failure", takeScreenshotOnTestFailure(result));
				test.log(LogStatus.FAIL, result.getMethod().getMethodName() + " :Test failure error message",
						result.getThrowable().getMessage());
				logResult();

			}

		} catch (Exception e) {
			String testCaseName = ctx.getCurrentXmlTest().getParameter("TestCaseName");
			System.out.println("Failed.." + testCaseName);
			try {
				takeScreenshotOnTestFailure(result);
				test.log(LogStatus.FAIL, "Case Id not Generated", takeScreenshotOnTestFailure(result));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void logResult() {
		extent.endTest(test);
		extent.flush();

	}

	public String takeScreenshotOnTestSkipping(ITestResult result) throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date date = new Date();
		String img = reportImages + result.getMethod().getMethodName() + dateFormat.format(date) + "SKIP.png";
		FileUtils.moveFile(scrFile, new File(img));

		String image = test.addScreenCapture(img);
		return image;

	}

	public String takeScreenshot(ITestResult result) throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date date = new Date();
		String img = reportImages + result.getMethod().getMethodName() + "_" + dateFormat.format(date) + "_"
				+ "PASS.png";
		FileUtils.moveFile(scrFile, new File(img));

		String image = test.addScreenCapture(img);
		return image;

	}

	public String takeScreenshotOnTestFailure(ITestResult result) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date date = new Date();
		// String img = root + "/screenshots/" + result.getMethod().getMethodName() +
		// dateFormat.format(date) + "FAIL.png";

		String img = reportImages + result.getMethod().getMethodName() + dateFormat.format(date) + "FAIL.png";
		FileUtils.moveFile(scrFile, new File(img));
		// LogAndroid.info("FAILED - " + result.getMethod().getMethodName());
		String image = test.addScreenCapture(img);
		return image;

	}

	@BeforeMethod(alwaysRun = true)
	public void getTestCaseName(Method method) {

		test = extent.startTest(method.getName());

	}

	public static void logInfo(String message) {
		Reporter.log(message);
		test.log(LogStatus.INFO, message);
	}

}
