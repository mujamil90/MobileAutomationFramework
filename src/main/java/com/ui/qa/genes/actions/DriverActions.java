package com.ui.qa.genes.actions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ui.qa.genes.extentreport.ExtentReport;
import com.ui.qa.genes.factory.BrowserFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author Muzzamil 
 * 
 * Class containing all the function that can be used along the
 *         UI interaction and will use in data engine.
 */
public class DriverActions extends BrowserFactory {


	public int getTimeOut() {
	    	 int  TIMEOUT = Integer.parseInt(androidSearchTimeout) ;
	    	
	    	 return TIMEOUT;
		}
	    
	public static int getWaitTime() {

	   	 int  WAIT = Integer.parseInt(implicitWait) ;
	   	
	   	 return WAIT;
		}
	    
	public void implictWait()
	{
		
	   	int  WAIT = Integer.parseInt(implicitWait) ;
		driver.manage().timeouts().implicitlyWait(WAIT, TimeUnit.SECONDS);
	}
	
	public static void mobileObjectClick(By by, String objLogicalName) {

		MobileElement mobileElement=null;
		try
		{
		 mobileElement= (MobileElement) driver.findElement(by);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		mobileElement.click();
		ExtentReport.logInfo(objLogicalName + " is clicked ");
	}
	
	public void mobileObjectClick(MobileElement mobileElement, String objLogicalName) {

		mobileElement.click();
		ExtentReport.logInfo(objLogicalName + " is clicked ");
	}


	public void enterValue(By by, String value,String objLogicalName) {

		MobileElement mobileElement=null;
		try
		{
		 mobileElement= (MobileElement) driver.findElement(by);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		mobileElement.click();
		Actions actions = new Actions(driver);
		actions.sendKeys(value);
		actions.perform();
		//mobileElement.sendKeys(value);
		ExtentReport.logInfo(value + " entered in " + objLogicalName);
	}
	
	
	public void clearAndEnterValue(By by, String value,String objLogicalName) {

		MobileElement mobileElement=null;
		try
		{
		 mobileElement= (MobileElement) driver.findElement(by);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		mobileElement.click();
		mobileElement.clear();
		mobileElement.sendKeys(value);
		//mobileElement.setValue(value);
		/*Actions actions = new Actions(driver);
		 * actions.sendKeys(value);
		actions.perform();*/
		ExtentReport.logInfo(value + " entered in " + objLogicalName);
	}


    public static void waitForMobileElementVisible(AppiumDriver<?> driver, By by)
    {

           WebDriverWait wait = new WebDriverWait(driver, getWaitTime());
           wait.until(ExpectedConditions.visibilityOfElementLocated(by));

     }

    public void waitForMobileElementVisible(AppiumDriver<?> driver, MobileElement element)
    {

           WebDriverWait wait = new WebDriverWait(driver, getWaitTime());
           wait.until(ExpectedConditions.visibilityOf(element));

     }


    public void waitForMobileElementNotVisible(AppiumDriver<?> driver, By by)
    {

           WebDriverWait wait = new WebDriverWait(driver, getWaitTime());
           wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

     }


    public static void waitForMobileElementClickable(AppiumDriver<?>driver, By by)
    {
           WebDriverWait wait = new WebDriverWait(driver, getWaitTime());
           wait.until(ExpectedConditions.elementToBeClickable(by));
     }

    public void waitForMobileElementClickable(AppiumDriver<?>driver, MobileElement element)
    {
           WebDriverWait wait = new WebDriverWait(driver, getWaitTime());
           wait.until(ExpectedConditions.elementToBeClickable(element));
     }

    public static  boolean checkObjectExist(By by, String objectlogicalname)  {
		boolean objectExist = false;
		try {
			objectExist = driver.findElement(by).isDisplayed();
			ExtentReport.logInfo(objectlogicalname + " is displayed");
		} catch (NoSuchElementException e) {
			return false;
		}

		return objectExist;
	}
    
    public boolean checkObjectExist(MobileElement element, By by, String objectlogicalname)  {
		boolean objectExist = false;
		try {
			objectExist = element.findElement(by).isDisplayed();
			ExtentReport.logInfo(objectlogicalname + " is displayed");
		} catch (NoSuchElementException e) {
			return false;
		}

		return objectExist;
	}

    public boolean checkMobileElementExist(MobileElement element, String objectlogicalname)  {
		boolean objectExist = false;
		try {
			objectExist =element.isDisplayed();
			ExtentReport.logInfo(objectlogicalname + " is displayed");
		} catch (NoSuchElementException e) {
			return false;
		}

		return objectExist;
	}


    public static void verifyElement(By by, String objectlogicalname) {

    	waitForMobileElementVisible(driver, by);
    	Assert.assertTrue(checkObjectExist(by, objectlogicalname));
    	ExtentReport.logInfo(objectlogicalname + " is verified.");
	}

    public void verifyElement(MobileElement element, String objectlogicalname) {

    	waitForMobileElementVisible(driver, element);
    	Assert.assertTrue(checkMobileElementExist(element, objectlogicalname));
    	ExtentReport.logInfo(objectlogicalname + " is verified.");
	}


    public static String getElementText(By by, String objectlogicalname) {

    	waitForMobileElementVisible(driver, by);
    	WebElement mobileElement= driver.findElement(by);
    	String elementText=mobileElement.getText();
    	ExtentReport.logInfo("Text is displayed for "+objectlogicalname+ " "+ elementText);
    	return elementText;
	}


    public String getElementText(MobileElement element, String objectlogicalname) {

    	waitForMobileElementVisible(driver, element);
    	String elementText=element.getText();
    	ExtentReport.logInfo("Text is displayed for "+objectlogicalname+ " "+ elementText);
    	return elementText;
	}


	@SuppressWarnings("unchecked")
	public  static List<MobileElement> getListOfElements(By by) {
    	List<MobileElement> listOfElements=null;
    	try {
			 listOfElements= (List<MobileElement>) driver.findElements(by);
			 ExtentReport.logInfo("Total number of elements are : "+ listOfElements.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfElements;

	}

    public static void  objectClickWithExplicitWait(By by, String objectlogicalname) {

    	waitForMobileElementVisible(driver, by);
		waitForMobileElementClickable(driver, by);
		mobileObjectClick(by, objectlogicalname);

	}
    
    public void objectClickWithExplicitWait(MobileElement mobile, String objectlogicalname) {

    	waitForMobileElementVisible(driver, mobile);
		waitForMobileElementClickable(driver, mobile);
		mobileObjectClick(mobile, objectlogicalname);

	}

	public void enterValueWithExplicitWait(By by, String value, String objectlogicalname) {
		waitForMobileElementVisible(driver, by);
		waitForMobileElementClickable(driver, by);
		clearAndEnterValue(by, value, objectlogicalname);

	}
	
	public void clearAndEnterValueWithExplicitWait(By by, String value, String objectlogicalname) {
		waitForMobileElementVisible(driver, by);
		waitForMobileElementClickable(driver, by);
		clearAndEnterValue(by, value, objectlogicalname);

	}

	public static MobileElement getMobileElement(By by) {

		MobileElement mobileElement=null;
		try
		{
		 mobileElement= (MobileElement) driver.findElement(by);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		return mobileElement;
	}

    public void minimizeKeyboard() {
    	try
    	{
    	driver.hideKeyboard();
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}

	}

    public void navigateBack() {
    	driver.navigate().back();
    	ExtentReport.logInfo("Back button is pressed.");
	}


	public void pressKeyboardKey(Keys key, String keyName) {
    	try
    	{
    	driver.getKeyboard().pressKey(key);
    	ExtentReport.logInfo(keyName + " is pressed.");
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("rawtypes")
	public void pressKeyWithKeyEvent(AndroidKey key, String keyName) {
		try
    	{
		((AndroidDriver)driver).pressKey(new KeyEvent(key));
		ExtentReport.logInfo(keyName + " is pressed.");
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendKeysByKeyboard(By by, String value, String objectName) {
    	try
    	{
    	objectClickWithExplicitWait(by, objectName);	
    	driver.getKeyboard().sendKeys(value);
    	ExtentReport.logInfo(value + " is entered.");
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}

	}

    public void switchToFrame(String Loctor) {

    	driver.switchTo().frame(Loctor);

	}

    public void refreshPage() {
    	driver.navigate().refresh();

	}


    @SuppressWarnings("rawtypes")
	public void TapByText(String buttonText) {
    	String buttonName="new UiSelector().text(\"MY_TEXT\")".replace("MY_TEXT", buttonText);
    	@SuppressWarnings("unchecked")
		List<WebElement> el = (List<WebElement>) driver.findElements(MobileBy.AndroidUIAutomator(buttonName));
    	System.out.println(el.size());
        if (el.size()==0) {
        	ExtentReport.logInfo("element with 'Use this location' not found");

        }
        new TouchAction(driver).press(ElementOption.element(el.get(0))).waitAction().release().perform();
        ExtentReport.logInfo(buttonText +" is clicked");

	}


    public void scrollAndTapByText(String menuText) {

        try {
            // driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""+menuText+"\"));")).click();
        	 driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + menuText + "\").instance(0));")).click(); 
        	ExtentReport.logInfo("Tap menu by text: "+menuText);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    public static MobileElement getElementByText(String menuText) {
    	MobileElement findElement = null;
        try {
        	
        	findElement= (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + menuText + "\").instance(0));")); 
        	ExtentReport.logInfo("Get element by text: "+menuText);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return  findElement;
    }
    
    public void scrollByID(String Id, int index) {

        try {
            // driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\""+Id+"\").instance("+index+"));"));
        	 driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\""+Id+"\").instance("+index+"));")); 
        	ExtentReport.logInfo("Tap menu by Id: "+Id);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void SwipeByElements(MobileElement fromElement, MobileElement toElement) {
       
        @SuppressWarnings("rawtypes")
		TouchAction dragNDrop = new TouchAction(driver)
                        .longPress(ElementOption.element(fromElement))
                        .moveTo(ElementOption.element(toElement))
                        .release();
        dragNDrop.perform();
        ExtentReport.logInfo("Element is swiped");

	}
    
    public MobileElement getMobileElementByIDWithUiSelector(String Id, int index) {
    	 MobileElement Element= null;
    	  try {
              Element= (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\""+Id+"\").instance("+index+"));"));
              ExtentReport.logInfo("Tap menu by Id: "+Id);
         } catch (Exception e) {
            e.printStackTrace();
         }
    	  
    	  return Element;
     }

    public MobileElement getMobileElementByText(String menuText) {
    	 MobileElement Element= null;
        try {
        	Element= (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""+menuText+"\"));"));
             ExtentReport.logInfo("Element is find by text: "+menuText);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return Element;
    }
    
    @SuppressWarnings("unchecked")
	public static void launchActivity(Activity activityName) {
    	
    	((AndroidDriver<MobileElement>) driver).startActivity(activityName);

	}
    
    public String getCurrentActivity() {
	
    	@SuppressWarnings("unchecked")
		String activity = ((AndroidDriver<MobileElement>) driver).currentActivity();
    	return activity;
	}
	
    public static void backGroundApp(int seconds) {
    	driver.runAppInBackground(Duration.ofSeconds(seconds)); 	
    	/*if(conditionalElement)
    	{
    		activateApp();
    		
    	}*/
    	//activateApp();
    	
	}
    
    public void waitforTwoElements(By element1, By element2) {
    	WebDriverWait wait = new WebDriverWait(driver, getWaitTime());
    	wait.until(ExpectedConditions.or(
    		    ExpectedConditions.presenceOfElementLocated(element1),
    		    ExpectedConditions.presenceOfElementLocated(element2)));
	}
    
    public void waitforMultipleElements(By element1, By element2, By element3) {
    	WebDriverWait wait = new WebDriverWait(driver, getWaitTime());
    	wait.until(ExpectedConditions.or(
    		    ExpectedConditions.presenceOfElementLocated(element1),
    		    ExpectedConditions.presenceOfElementLocated(element2),
    		    ExpectedConditions.presenceOfElementLocated(element3)));
	}
    
   /* public void compareTwoImages(byte[] screenshot1, byte[] screenshot2) {

		SimilarityMatchingResult result = driver
		        .getImagesSimilarity(screenshot1, screenshot2, new SimilarityMatchingOptions().withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.0)));
    }*/
    
    
    public boolean imagesEqual(File f1,File f2)
	{
		BufferedImage i1,i2;
 
		try
		{
			i1 = ImageIO.read(f1);
			i2 = ImageIO.read(f2);
		}
		catch(Exception e){ e.printStackTrace(); return false; }
 
		if(i1.getWidth() != i2.getWidth() || i1.getHeight() != i2.getHeight())
			return false;
 
		for(int x = 0; x < i1.getWidth(); x++)
			for(int y = 0; y < i1.getWidth(); y++)
				if(i1.getRGB(x,y) != i2.getRGB(x,y))
					return false;
 
		return true;
	}
    
    @SuppressWarnings("rawtypes")
	public void tapTwiceOnScreenCenter() {
    	Dimension size = driver.manage().window().getSize();
 		int halfHeight = (int) (size.getHeight() * 0.5);
 		int halfWidth = (int) (size.getWidth() * 0.5);
 		new TouchAction(driver).press(ElementOption.point(halfWidth,halfHeight)).release().perform().press(ElementOption.point(halfWidth,halfHeight)).release().perform();
 		new TouchAction(driver).press(ElementOption.point(halfWidth,halfHeight)).release().perform().press(ElementOption.point(halfWidth,halfHeight)).release().perform();

	}
    
    public void SwipeOnElementRightToLeft(By by) {
    	MobileElement element= getMobileElement(by);
    	Dimension size = element.getSize();
    	int star_x = (int) (size.width * 0.80);
    	int end_x = (int) (size.width * 0.20);
    	int y = element.getLocation().getY();
        @SuppressWarnings("rawtypes")
		TouchAction dragNDrop = new TouchAction(driver)
                        .press(PointOption.point(star_x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                        .moveTo(PointOption.point(end_x, y))
                        .release();
        dragNDrop.perform();
        ExtentReport.logInfo("Swipe is done.");
	}
    
    public void SwipeOnElementLeftToRight(By by) {
    	MobileElement element= getMobileElement(by);
    	Dimension size = element.getSize();
    	int star_x = (int) (size.width * 0.20);
    	int end_x = (int) (size.width * 0.80);
    	int y = element.getLocation().getY();
        @SuppressWarnings("rawtypes")
		TouchAction dragNDrop = new TouchAction(driver)
                        .press(PointOption.point(star_x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                        .moveTo(PointOption.point(end_x, y))
                        .release();
        dragNDrop.perform();
        ExtentReport.logInfo("Swipe is done.");
	}
    
    public static boolean isPageContainsElement(By by) {
		return driver.findElements(by).isEmpty();

	}
    
    public boolean isPageContainsElement(MobileElement element,By by, String objectLogicalName) {
    	ExtentReport.logInfo(objectLogicalName+ "  visiblity is " + element.findElements(by).isEmpty());
		return element.findElements(by).isEmpty();

	}
    
    public static void activateApp()
    {
    	String bundleId = prop.getProperty("ANDROID_APP_PACKAGE");
    	String homeActivity = prop.getProperty("ANDROID_MAIN_ACTIVITY");
    	launchActivity(new Activity(bundleId, bundleId+"."+homeActivity));
    	ExtentReport.logInfo("App is activated.");
    	
	}
    
    public void scrollToBottom(double d, double e) {
    	
    	int  x = driver.manage().window().getSize().width / 2;
    	int y = driver.manage().window().getSize().getHeight();
    	int start_y = (int) (y * d);
    	int end_y = (int) (y * e);
		@SuppressWarnings("rawtypes")
		TouchAction dragNDrop = new TouchAction(driver)
                        .press(PointOption.point(x,end_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                        .moveTo(PointOption.point(x, start_y))
                        .release();
        dragNDrop.perform();
        ExtentReport.logInfo("Swiped  from "+start_y + " to" + end_y);
	}
    
    
    public void scrollToBottom(double d, MobileElement element) {
    	
        	int  x = driver.manage().window().getSize().width / 2;
        	int y = driver.manage().window().getSize().getHeight();
        	int start_y = (int) (y * d);
        	int end_y = element.getLocation().getY();
        	
    		@SuppressWarnings("rawtypes")
			TouchAction dragNDrop = new TouchAction(driver)
                            .press(PointOption.point(x,end_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                            .moveTo(PointOption.point(x, start_y))
                            .release();
            dragNDrop.perform();
            ExtentReport.logInfo("Swiped  from "+start_y + " to" + end_y);
    	}
    
    
    public void waitWithTimeOut(By by) {
    	 WebDriverWait wait = new WebDriverWait(driver, getTimeOut());
         wait.until(ExpectedConditions.visibilityOfElementLocated(by));
         wait.until(ExpectedConditions.elementToBeClickable(by));

	}
    
    public void elementNotVisibleWithTimeOut(By by) {
   	 WebDriverWait wait = new WebDriverWait(driver, getTimeOut());
     wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

	}
 }
