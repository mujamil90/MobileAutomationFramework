package com.ui.qa.genes.homepage;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.qa.genes.extentreport.ExtentReport;

public class HomePageTest extends ExtentReport {

	@Test
	public void verifySellingTab() {
		String sellingTab = prop.getProperty("SELLING_TAB");
		homePage.verifyTabByName(sellingTab);
	}

	@Test
	public void verifyDealsTab() {
		String dealsTab = prop.getProperty("DEALS_TAB");
		homePage.verifyTabByName(dealsTab);
	}

	@Test
	public void verifyCategoriesTab() {
		String categoriesTab = prop.getProperty("CATEGORIES_TAB");
		homePage.verifyTabByName(categoriesTab);
	}

	@Test
	public void verifySavedTab() {
		String savedTab = prop.getProperty("SAVED_TAB");
		homePage.verifyTabByName(savedTab);
	}

	@Test
	public void verifyMenueOptionsSelling() {
		String sellingTab = prop.getProperty("SELLING_TAB");
		homePage.navigateToHeaderMenu();
		List<String> menuOptions = homePage.getAllMenuItems();
		boolean isContains = menuOptions.stream().anyMatch(sellingTab::equalsIgnoreCase);
		Assert.assertTrue(isContains);

	}
}
