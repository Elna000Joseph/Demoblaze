package testcases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dataresource.TestDataResource;
import pages.LoginAndRegisterPage;
import reusefunctions.ReportScreenShot;


public class TC_AddProductAndPlaceOrder {

	public static String testcaseName="TC_AddProductAndPlaceOrder";
	String testcaseDescription= "Add the product and do the purchase";
	LoginAndRegisterPage login=new LoginAndRegisterPage();
	
	
		@BeforeTest
		public void Setup()
		{
			ReportScreenShot.testcaseName=testcaseName;
			ReportScreenShot.logger=ReportScreenShot.report.startTest(testcaseName, testcaseDescription);

		}
		

@Test(priority = 1)

public void AddProductAndPlaceOrder()
{
	TestDataResource.readTestdataExcel("PurchaseOrder", "TC_AddProductAndPlaceOrder");
	String category=TestDataResource.TEST_DATA.get("CATEGORY").get(0);
	String product=TestDataResource.TEST_DATA.get("PRODUCT").get(0);
	
	login.selectCategoryAndProduct(category,product);
	login.clickOnADDTocart();
	login.clickOnCart();
	
}
@Test(priority = 2)
public void PurchaseTheProduct()
{
	TestDataResource.readTestdataExcel("PurchaseOrder", "TC_PurchaseDetails");
	String name=TestDataResource.TEST_DATA.get("NAME").get(0);
	String country=TestDataResource.TEST_DATA.get("COUNTRY").get(0);
	String city=TestDataResource.TEST_DATA.get("CITY").get(0);
	String card=TestDataResource.TEST_DATA.get("CARD").get(0);
	String month=TestDataResource.TEST_DATA.get("MONTH").get(0);
	String year=TestDataResource.TEST_DATA.get("YEAR").get(0);
	login.clickOnPlaceOrder();
	login.enterPurchaseDetails(name, country, city, card, month, year);
	login.clickOnPurchase();

	
}
@Test(priority = 3)
public void LogOut()
{
	login.logOut();
}

}
