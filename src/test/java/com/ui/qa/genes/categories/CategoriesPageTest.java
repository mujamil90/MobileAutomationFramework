package com.ui.qa.genes.categories;

import org.testng.annotations.Test;

import com.ui.qa.genes.extentreport.ExtentReport;

public class CategoriesPageTest extends ExtentReport {

	@Test
	public void testCategoryType() {
		activateApp();
		String categoriesTab = prop.getProperty("CATEGORIES_TAB");
		homePage.navigateToTabByName(categoriesTab);
		categoriesPage.getCategoriesName();

	}

}
