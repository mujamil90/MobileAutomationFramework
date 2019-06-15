package com.ui.qa.genes.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.ui.qa.genes.extentreport.ExtentReport;

import io.appium.java_client.MobileElement;

public class DealsPage extends BasePageObject {

	By dealsType = By.xpath("//android.widget.LinearLayout[@index='0']");
	By deals = By.className("android.widget.TextView");
	By itemName = By.id("item_title");

	public List<String> getItemsName() {
		List<String> productsNames = new ArrayList<>();

		List<MobileElement> products = getListOfElements(itemName);

		for (MobileElement product : products) {
			String productName = product.getText();
			verifyElement(product, productName);
			Assert.assertTrue(product.isEnabled(), "Element is not clickable.");
			ExtentReport.logInfo("Product name is  : " + productName);
			productsNames.add(productName);
		}
		return productsNames;
	}

	public List<String> getAllDealsTypes() {

		List<String> dealsTypes = new ArrayList<>();
		MobileElement dealsContainer = getMobileElement(dealsType);
		List<MobileElement> allDeals = dealsContainer.findElements(deals).subList(0, 4);

		for (MobileElement deals : allDeals) {
			String dealsName = deals.getText();
			verifyElement(deals, dealsName);
			Assert.assertTrue(deals.isEnabled(), "Element is not clickable.");
			ExtentReport.logInfo("Deals name is  : " + dealsName);
			dealsTypes.add(dealsName);
		}

		return dealsTypes;

	}

}
