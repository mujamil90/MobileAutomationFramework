package com.ui.qa.genes.pageobjects;

import org.testng.Assert;

import com.ui.qa.genes.actions.DriverActions;
import com.ui.qa.genes.extentreport.ExtentReport;

import io.appium.java_client.MobileElement;

public class HomePage extends BasePageObject {

	public void verifyTabByName(String tabName) {
		MobileElement tab = DriverActions.getElementByText(tabName);
		String actualTabName = tab.getText();
		Assert.assertEquals(actualTabName, tabName);
		ExtentReport.logInfo(tabName + "is verified.");

	}

	public void navigateToTabByName(String tabName) {
		MobileElement tab = DriverActions.getElementByText(tabName);
		objectClickWithExplicitWait(tab, tab.getText());
	}

}
