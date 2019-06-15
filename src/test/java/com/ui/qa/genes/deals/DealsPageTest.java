package com.ui.qa.genes.deals;

import org.testng.annotations.Test;

import com.ui.qa.genes.extentreport.ExtentReport;

public class DealsPageTest extends ExtentReport {

	@Test
	public void testVisibleProduct() {
		activateApp();
		String dealsTab = prop.getProperty("DEALS_TAB");
		homePage.navigateToTabByName(dealsTab);
		dealsPage.getItemsName();

	}

	@Test
	public void testDealsType() {
		activateApp();
		String dealsTab = prop.getProperty("DEALS_TAB");
		homePage.navigateToTabByName(dealsTab);
		dealsPage.getAllDealsTypes();

	}

}
