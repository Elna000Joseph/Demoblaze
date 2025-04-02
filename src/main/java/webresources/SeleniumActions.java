/** This is the class developed for all the actions/Activities to be performed by the application. These methods are reusable
 *@author elna.joseph
 */
package webresources;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import reusefunctions.ReportScreenShot;

public class SeleniumActions  {
	boolean iselementpresent = false;
	boolean isNotelementpresent = false;
	boolean iselementdisplayed =false;
	public static WebDriver driver;
	
	public WebDriver initializeDriver() throws IOException {

		
		boolean blnremoteRun = false;
		
		String driverpath = "C:/Users/User/eclipse-workspace/Product_Store/chromedriver/chromedriver.exe";
		
			ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            if(blnremoteRun )
            {
               WebDriverManager.chromedriver().setup();  
                options.addArguments("--window-size=1400,600");
                options.addArguments("--headless"); 
                
            }
            else
            {
                System.setProperty("webdriver.chrome.driver", driverpath);                
            }
            options.addArguments("--disable-extensions");
            driver = new ChromeDriver(options);
   
		
		if(null!=driver)
		{
			driver.manage().window().maximize();
		}
		else
		{
			System.out.println("Driver is null");
			Assert.assertEquals("driver is null", "Driver should not be null");
		}
		return driver;
	}



	public void closeDriver() {
		if(null!=driver)
		{
			//driver.close();
		}
	}
	
	public void pageRefersh()
	{
		driver.navigate().refresh();
	}
	public void openUrl(String url) {
		//WebDriverFactory objw= new WebDriverFactory();
		try {
			initializeDriver();
			driver.get(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (WebDriverException w) {
			w.printStackTrace();
		}
		catch(NullPointerException n) {
			n.printStackTrace();
		}
	}


	public Boolean elementPresent(By locator)
	{
		iselementpresent=false;
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(7));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			//System.out.println("264 line");
			iselementpresent=driver.findElements((locator)).size()!=0;
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return iselementpresent;
	}
	
	
	
	
	public void verifyElementPresent(By locator, String expectedResult, String step)
	{
		iselementpresent=elementPresent(locator);
		if(iselementpresent==true){
			ReportScreenShot.report(LogStatus.PASS, expectedResult, step,true);
		}
		else
		{
			ReportScreenShot.report(LogStatus.FAIL, expectedResult, step,true);
			Assert.assertEquals("The "+locator +" is not present", "Verify the "+locator +" is not present");
		}
	}
	
	public void verifyElementNotPresent(By locator, String expectedResult, String step)
	{
		iselementpresent=elementPresent(locator);
		if(iselementpresent==false){
			ReportScreenShot.report(LogStatus.PASS, expectedResult, step,false);
		}
		else
		{
			ReportScreenShot.report(LogStatus.FAIL, expectedResult, step,true);
			Assert.assertEquals("The "+locator +" is present", "Verify the "+locator +" is present");
		}
	}
	
	public  void enterText(By locator,String text,String locatorDescription,boolean screenShotRequired) {
		iselementpresent=elementPresent(locator);
		if(iselementpresent==true) {
			if(!(text.isBlank()))
			{
			driver.findElement(locator).sendKeys(text);
			ReportScreenShot.report(LogStatus.PASS, "Entered "+locatorDescription+" : " + "\""+ text + "\"", "Enter "+locatorDescription,screenShotRequired);
		
			}
		}
		else {
			
			ReportScreenShot.report(LogStatus.FAIL, locatorDescription + "field is not found", "Enter "+locatorDescription,screenShotRequired);
			Assert.assertEquals(false,true,locatorDescription + "field is not found");
		}

	}

	
	public void failWithoutStop(String expectedResult,String stepName )
	{
		ReportScreenShot.report(LogStatus.FAIL,expectedResult,stepName ,  true);
		
	}
	
	public void fail(String expectedResult,String stepName )
	{
		ReportScreenShot.report(LogStatus.FAIL,expectedResult,stepName ,  true);
		Assert.assertEquals(false,true,"Failed "+expectedResult);
	}
	public void info(String message,String stepName)
	{
		ReportScreenShot.report(LogStatus.INFO, message, stepName, true);
	}
	
	public void pass(String message,String stepName)
	{
		ReportScreenShot.report(LogStatus.PASS, message, stepName, true);
	}
	
	
	public boolean verifyEmailFormat(String email)
	{
		String regex = "^[A-Za-z0-9.]+@[A-Za-z0-9]+.[A-Za-z]{2,6}";  

		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(email);  
        return matcher.matches();
	}
	
	public int verifyPasswordLength(String password)
	{
		int length=password.length();
		return length;
	}
	
	public void waitforload(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getAttribute(By locator, String attribute)
	{
		String value = null;
		iselementpresent=elementPresent(locator);
		if(iselementpresent==true)
		{
			 value=driver.findElement(locator).getAttribute(attribute);
			
		}
		else
		{
			fail("Locator is not present", "Verify Locator present");
		}
		
		return value;
	}
	
	public void compareValues(String expValue, String actualValue, String expectedResult)
	{
		try {
			expValue =expValue.trim();
			actualValue =actualValue.trim();
			if(expValue.equalsIgnoreCase(actualValue)) {
				ReportScreenShot.report(LogStatus.PASS, expectedResult +" is correct and it is : "+"\""+ actualValue + "\"" ,"Verify the "+expectedResult,false);
			}
			else
			{
				ReportScreenShot.report(LogStatus.FAIL, expectedResult +" is not correct.It is "+"\""+ actualValue  ,"Verify the "+expectedResult,true);
				Assert.assertEquals(actualValue, expValue,expectedResult +" is not correct and it should be "+"\" "+ expectedResult + "\" ");
			}
		}
		catch(NullPointerException e) {
			System.err.println(e);
		}
	}
	
	
	public void click(By locator, String description)
	{
		iselementpresent=elementPresent(locator);
		if(iselementpresent==true)
		{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		try {
			  driver.findElement(locator).click();
			}
		catch(ElementClickInterceptedException e)
			{
			  fail("Element is not clickable","Verify Element is not clickable");
			}
		
		}
		else
		{
			fail("Locator is not present", "Verify Locator present");
		}
		
	}
	
	
	public void clear(By locator,  String locatorDescription)
	{
		iselementpresent=elementPresent(locator);
		if(iselementpresent==true){
			try {
				driver.findElement(locator).clear();
		}
		
		catch(Exception e)
		{
			
			driver.findElement(locator).sendKeys(Keys.CONTROL + "a");
			driver.findElement(locator).sendKeys(Keys.DELETE);
		}
		}
		else
		{
			ReportScreenShot.report(LogStatus.FAIL, locatorDescription +" is not present","Verify the "+locatorDescription,false);
			Assert.fail(locatorDescription +" is not present");
		}
	}
	
	
	public void clickRecaptch(By frameLoc, By recaptch, String locatorDescription)
	{
		iselementdisplayed=elementPresent(frameLoc);
		
		if(iselementdisplayed==true)
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLoc));
       
        wait.until(ExpectedConditions.elementToBeClickable(recaptch)).click();
        
        
		}
		
		else
		{
			ReportScreenShot.report(LogStatus.FAIL, locatorDescription +" is not present","Verify the "+locatorDescription,false);
			Assert.fail(locatorDescription +" is not present");
		}
	}
	
	public void scroll(By locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			iselementpresent=driver.findElements((locator)).size()!=0;
			if(iselementpresent==true){
				WebElement scrollElement = driver.findElement(locator);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",scrollElement);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public Boolean verifyAlertPresent()
	{
		 try 
		    { 
		        driver.switchTo().alert(); 
		        return true; 
		    }   
		    catch (NoAlertPresentException Ex) 
		    { 
		        return false; 
		    }  
		}  
	
	
	public void alertAccpet()
	{
		Boolean alertPresence=verifyAlertPresent();
	
		 if(alertPresence)
		 {
		driver.switchTo().alert().accept();
		 }
		 else
		 {
			 fail("USer can not see alert box","Verify USer can not see alert box");
		 }
	}
	
	public String alertText()
	{
		Boolean alertPresence=verifyAlertPresent();
		String msg=null;
		 if(alertPresence)
		 {
			  msg=driver.switchTo().alert().getText();
		 }
		 else
		 {
			 fail("USer can not see alert box","Verify USer can not see alert box");
		 }
		
		return msg;
	}
	
	public String getText(By locator, String locatorDescription)
	{
		String value=null;
		iselementpresent=elementPresent(locator);
		if(iselementpresent==true) {
			
			 value=driver.findElement(locator).getText();
			ReportScreenShot.report(LogStatus.PASS, "Text is available for "+locatorDescription, "Verify text is available for "+locatorDescription,false);
		
			}
		
		else {
			
			ReportScreenShot.report(LogStatus.FAIL, locatorDescription + "field is not found", "Enter "+locatorDescription,true);
			Assert.assertEquals(false,true,locatorDescription + "field is not found");
		}
		return value;
	}

}




