package testcases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dataresource.TestDataResource;
import pages.LoginAndRegisterPage;
import reusefunctions.ReportScreenShot;


public class TC_RegisterNewUSerAndSignIn {

	public static String testcaseName="TC_RegisterNewUSerAndSignIn";
	String testcaseDescription= "Register new user and log in";
	LoginAndRegisterPage login=new LoginAndRegisterPage();
	
	
		@BeforeTest
		public void Setup()
		{
			ReportScreenShot.testcaseName=testcaseName;
			ReportScreenShot.logger=ReportScreenShot.report.startTest(testcaseName, testcaseDescription);

		}
		

@Test(priority = 1)

public void RegisterNewUSer()
{
	TestDataResource.readTestdataExcel("PurchaseOrder", "TC_RegisterNewUSer");
	String username=TestDataResource.TEST_DATA.get("USERNAME").get(0);
	String password=TestDataResource.TEST_DATA.get("PASSWORD").get(0);
	login.clickOnSignUpButton();
	login.registerNewUSer(username, password);
	login.clickOnLogin();
	login.loginUser(username, password);
}


	

}
