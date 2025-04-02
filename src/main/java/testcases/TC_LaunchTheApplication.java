package testcases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dataresource.TestDataResource;
import pages.LoginAndRegisterPage;
import reusefunctions.ReportScreenShot;


public class TC_LaunchTheApplication {

	public static String testcaseName="TC_LaunchTheApplication";
	String testcaseDescription= "Login to Demoblaze Product Store application";
	LoginAndRegisterPage login=new LoginAndRegisterPage();
	
	
		@BeforeTest
		public void Setup()
		{
			ReportScreenShot.testcaseName=testcaseName;
			ReportScreenShot.logger=ReportScreenShot.report.startTest(testcaseName, testcaseDescription);

		}
		
		@Test(priority = 1)
	
		public void LoginWithValidCredentials() {

			TestDataResource.readTestdataExcel("PurchaseOrder", testcaseName);
			String url=TestDataResource.TEST_DATA.get("URL").get(0);
			login.launchTheApplication(url);
			
		  
		}		
}
