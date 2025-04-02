/**
 *  This is the class is developed to initalize the Report before the suite runs and flush  the report after the suite ends
 *@author elna.joseph
 */


package listenerspackage;

import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import reusefunctions.ReportScreenShot;
import webresources.SeleniumActions;


public class CustomListener implements ISuiteListener  {
	public static String suiteName=null;
	public static String role;


	@Override
	public void onStart(ISuite suite) {
	
		String suiteName=suite.getXmlSuite().getName();

		ReportScreenShot.report = ReportScreenShot.Instance(suiteName);
	}


	@Override
	public void onFinish(ISuite suite) {

		ReportScreenShot.report.endTest(ReportScreenShot.logger);
		ReportScreenShot.report.flush();
		SeleniumActions driverobj = new SeleniumActions();
		driverobj.closeDriver();
	}
}
