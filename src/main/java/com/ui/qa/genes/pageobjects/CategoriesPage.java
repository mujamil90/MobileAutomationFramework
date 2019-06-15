package com.ui.qa.genes.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.ui.qa.genes.extentreport.ExtentReport;

import io.appium.java_client.MobileElement;

public class CategoriesPage extends BasePageObject {

	By category = By.id("textview_category");

	public List<String> getCategoriesName() {
		List<String> categoriesNames = new ArrayList<>();

		List<MobileElement> categories = getListOfElements(category);

		for (MobileElement productCategory : categories) {
			String categoryName = productCategory.getText();
			verifyElement(productCategory, categoryName);
			Assert.assertTrue(productCategory.isEnabled(), "Element is not clickable.");
			ExtentReport.logInfo("Category name is  : " + categoryName);
			categoriesNames.add(categoryName);
		}
		return categoriesNames;
	}

}
