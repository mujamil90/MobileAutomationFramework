package com.ui.qa.genes.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import com.ui.qa.genes.actions.DriverActions;
import com.ui.qa.genes.extentreport.ExtentReport;

import io.appium.java_client.MobileElement;

public class BasePageObject extends ExtentReport {

	By menuButton = By.id("home");
	By cartButton = By.id("menu_cart");
	By searchButton = By.id("menu_search");
	By menuOptions = By.id("design_menu_item_text");
	By pageTitle = By.id("title");

	public void navigateToHeaderMenu() {
		DriverActions.objectClickWithExplicitWait(menuButton, "Header menu");

	}

	public void openSearch() {
		if (!DriverActions.isPageContainsElement(searchButton)) {
			DriverActions.objectClickWithExplicitWait(searchButton, "Search button");
		}

	}

	public void clickOnCart() {
		DriverActions.objectClickWithExplicitWait(cartButton, "Cart button");

	}

	public List<String> getAllMenuItems() {
		List<String> menuNames = new ArrayList<String>();
		List<MobileElement> allmenuOptions = DriverActions.getListOfElements(menuOptions);

		for (MobileElement menuOption : allmenuOptions) {

			ExtentReport.logInfo("Menu Option is visible by name : " + menuOption.getText());
			menuNames.add(menuOption.getText());
		}

		return menuNames;
	}

	public String getPageTitle() {
		return DriverActions.getElementText(pageTitle, "Page Title").trim();
	}

}
